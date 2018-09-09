package com.derbygras.shavepoller.bizlogic;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.derbygras.dropmonster.dynamodb.SiteConfig;
import com.derbygras.shavepoller.utils.DropMonsterTaskFactory;
import com.google.common.collect.Lists;

public class DropMonsterImpl implements DropMonster {
    private final DynamoDBMapper mapper;
    private final DropMonsterTaskFactory taskFactory;
    private final ExecutorService executor;

	@Inject
    public DropMonsterImpl(DynamoDBMapper mapper, DropMonsterTaskFactory taskFactory, ExecutorService executor) {
        this.mapper = mapper;
        this.taskFactory = taskFactory;
        this.executor = executor;
	}

    @SuppressWarnings("unchecked")
    @Override
    public void executeLogic() throws Exception {
        // Retrieve all site configs
        List<SiteConfig> siteConfigs = retrieveSiteConfigs();

        // Construct all site checker tasks
        List<Callable<Boolean>> tasks = Lists.newArrayList();
        siteConfigs.forEach(item -> tasks.add(taskFactory.newSiteCheckTask(item)));

        // Execute all site checker tasks
        List<Future<Boolean>> taskResults = executor.invokeAll(tasks);

        // Done
    }

    private List<SiteConfig> retrieveSiteConfigs() {
        DynamoDBScanExpression configScan = new DynamoDBScanExpression();
        return mapper.scan(SiteConfig.class, configScan);
    }
}
