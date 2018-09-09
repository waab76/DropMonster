package com.derbygras.dropmonster.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

@DynamoDBTable(tableName = "DropMonsterSiteStatus")
public class SiteStatus {
    private String siteId;
    private boolean dropActive;
    private long dropStarted;
    private long dropEnded;
    private long version;

    @DynamoDBHashKey(attributeName = "SiteId")
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    @DynamoDBVersionAttribute(attributeName = "Version")
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @DynamoDBAttribute(attributeName = "DropActive")
    public boolean isDropActive() {
        return dropActive;
    }

    public void setDropActive(boolean dropActive) {
        if (dropActive != this.dropActive) {
            if (dropActive) {
                dropStarted = System.currentTimeMillis();
            } else {
                dropEnded = System.currentTimeMillis();
            }
        }
        this.dropActive = dropActive;
    }

    @DynamoDBAttribute(attributeName = "DropStarted")
    public long getDropStarted() {
        return dropStarted;
    }

    public void setDropStarted(long dropStarted) {
        this.dropStarted = dropStarted;
    }

    @DynamoDBAttribute(attributeName = "DropEnded")
    public long getDropEnded() {
        return dropEnded;
    }

    public void setDropEnded(long dropEnded) {
        this.dropEnded = dropEnded;
    }
}
