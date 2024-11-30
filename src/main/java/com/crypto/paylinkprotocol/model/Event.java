package com.crypto.paylinkprotocol.model;

import java.math.BigInteger;

public class Event {
    private long appId;
    private String purchasedToken;
    private BigInteger purchaseAmount;
    private long customerUserId;
    private String customerUserAddress;

    public Event() {
    }

    public Event(long appId, String purchasedToken, BigInteger purchaseAmount, long customerUserId, String customerUserAddress) {
        this.appId = appId;
        this.purchasedToken = purchasedToken;
        this.purchaseAmount = purchaseAmount;
        this.customerUserId = customerUserId;
        this.customerUserAddress = customerUserAddress;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getPurchasedToken() {
        return purchasedToken;
    }

    public void setPurchasedToken(String purchasedToken) {
        this.purchasedToken = purchasedToken;
    }

    public BigInteger getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(BigInteger purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public long getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(long customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getCustomerUserAddress() {
        return customerUserAddress;
    }

    public void setCustomerUserAddress(String customerUserAddress) {
        this.customerUserAddress = customerUserAddress;
    }

    @Override
    public String toString() {
        return "Event{" +
                "appId=" + appId +
                ", purchasedToken='" + purchasedToken + '\'' +
                ", purchaseAmount=" + purchaseAmount +
                ", customerUserId=" + customerUserId +
                ", customerUserAddress='" + customerUserAddress + '\'' +
                '}';
    }

}