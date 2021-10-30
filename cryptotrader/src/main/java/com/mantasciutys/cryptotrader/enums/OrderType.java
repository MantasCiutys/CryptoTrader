package com.mantasciutys.cryptotrader.enums;

public enum OrderType {
    LIMIT("limit"),
    MARKET("market"),
    STOP("stop");

    private final String orderType;

    OrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }
}
