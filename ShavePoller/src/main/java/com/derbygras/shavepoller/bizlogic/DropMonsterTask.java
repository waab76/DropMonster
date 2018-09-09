package com.derbygras.shavepoller.bizlogic;

import java.util.List;
import java.util.concurrent.Callable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.derbygras.dropmonster.dynamodb.SiteStatus;
import com.derbygras.shavepoller.utils.PageFetcher;
import com.derbygras.shavepoller.utils.PriceCounter;
import com.derbygras.shavepoller.utils.SoldOutCounter;
import com.derbygras.shavepoller.utils.impl.SnsSender;

public class DropMonsterTask implements Callable<Boolean> {
    private final String siteId;
    private final long maxDiscrepancy;
    private final DynamoDBMapper mapper;
    private final PageFetcher pageFetcher;
    private final PriceCounter priceCounter;
    private final SoldOutCounter soldOutCounter;

    public DropMonsterTask(String siteId, long maxDiscrepancy, DynamoDBMapper mapper, PageFetcher pageFetcher,
            PriceCounter priceCounter, SoldOutCounter soldOutCounter) {
        this.siteId = siteId;
        this.maxDiscrepancy = maxDiscrepancy;
        this.mapper = mapper;
        this.pageFetcher = pageFetcher;
        this.priceCounter = priceCounter;
        this.soldOutCounter = soldOutCounter;
    }

    @Override
    public Boolean call() throws Exception {
        // Fetch the previous page status
        SiteStatus statusKey = new SiteStatus();
        statusKey.setSiteId(siteId);
        DynamoDBQueryExpression<SiteStatus> statusQuery = new DynamoDBQueryExpression<SiteStatus>()
                .withHashKeyValues(statusKey);
        List<SiteStatus> statusList = mapper.query(SiteStatus.class, statusQuery);
        if (1 != statusList.size()) {
            throw new RuntimeException("Too many status records for site");
        }
        SiteStatus status = statusList.get(0);

        // Fetch the page
        String pageData = pageFetcher.fetchPage();

        // Count the prices
        long priceCount = priceCounter.getPriceCount(pageData);

        // Count the sold outs
        long soldOutCount = soldOutCounter.getSoldOutCount(pageData);

        // Determine if there are items for sale
        boolean dropActive = (priceCount - soldOutCount) > maxDiscrepancy;

        // If drop status has changed
        if (dropActive != status.isDropActive()) {
            // Update the page status
            status.setDropActive(dropActive);

            // Persist the page status
            mapper.save(status);

            sendNotification(dropActive);
        }

        return dropActive;
    }

    private void sendNotification(boolean dropActive) {
        StringBuffer buf = new StringBuffer();
        buf.append("[").append(siteId).append("] ");
        if (dropActive) {
            buf.append("Drop has started at ").append(pageFetcher.getSiteUrl());
        } else {
            buf.append("Drop has ended");
        }
        SnsSender.sendSnsMessage(buf.toString());
    }

}
