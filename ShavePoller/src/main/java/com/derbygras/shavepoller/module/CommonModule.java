package com.derbygras.shavepoller.module;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.derbygras.shavepoller.utils.DropMonsterTaskFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {
    @Provides
    @Singleton
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);
    }

    @Provides
    @Singleton
    public DropMonsterTaskFactory taskFactory() {
        return new DropMonsterTaskFactory(dynamoDBMapper());
    }

    @Provides
    @Singleton
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(dynamoClient());
    }

    public AmazonDynamoDB dynamoClient() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }
}
