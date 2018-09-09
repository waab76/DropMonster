package com.derbygras.dropmonster.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

@DynamoDBTable(tableName = "DropMonsterSiteConfig")
public class SiteConfig {
    private String siteId;
    private long version;
    private String siteUrl;
    private String priceRegex;
    private String soldOutRegex;
    private long maxDiscrepancy;

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

    @DynamoDBAttribute(attributeName = "SiteURL")
    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    @DynamoDBAttribute(attributeName = "PriceRegex")
    public String getPriceRegex() {
        return priceRegex;
    }

    public void setPriceRegex(String priceRegex) {
        this.priceRegex = priceRegex;
    }

    @DynamoDBAttribute(attributeName = "SoldOutRegex")
    public String getSoldOutRegex() {
        return soldOutRegex;
    }

    public void setSoldOutRegex(String soldOutRegex) {
        this.soldOutRegex = soldOutRegex;
    }

    @DynamoDBAttribute(attributeName = "MaxDiscrepancy")
    public long getMaxDiscrepancy() {
        return maxDiscrepancy;
    }

    public void setMaxDiscrepancy(long maxDiscrepancy) {
        this.maxDiscrepancy = maxDiscrepancy;
    }
}
