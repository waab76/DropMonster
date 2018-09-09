package com.derbygras.shavepoller.utils.impl;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class SnsSender {
    public static final String TOPIC_ARN = "arn:aws:sns:us-east-1:335344888946:BuffleBotNotification";
    private static final String TEST_TOPIC_ARN = "arn:aws:sns:us-east-1:335344888946:DropMonsterTestTopic";

    @SuppressWarnings({ "deprecation", "unused" })
    public static void sendSnsMessage(String notificationMessage) {
        // create a new SNS client and set endpoint
        AmazonSNSClient snsClient = new AmazonSNSClient();
        snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));

        // publish to an SNS topic
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, notificationMessage);
        PublishResult publishResult = snsClient.publish(publishRequest);
    }
}
