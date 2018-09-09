package com.derbygras.shavepoller.utils;

import java.util.concurrent.Callable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.derbygras.dropmonster.dynamodb.SiteConfig;
import com.derbygras.shavepoller.bizlogic.DropMonsterTask;
import com.derbygras.shavepoller.utils.impl.PageFetcherImpl;
import com.derbygras.shavepoller.utils.impl.PriceCounterImpl;
import com.derbygras.shavepoller.utils.impl.SoldOutCounterImpl;

public class DropMonsterTaskFactory {
    private final DynamoDBMapper mapper;

    public DropMonsterTaskFactory(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public Callable<Boolean> newSiteCheckTask(SiteConfig config) {
        return new DropMonsterTask(config.getSiteId(), config.getMaxDiscrepancy(), mapper,
                new PageFetcherImpl(config.getSiteUrl()), new PriceCounterImpl(config.getPriceRegex()),
                new SoldOutCounterImpl(config.getSoldOutRegex()));
    }
}
