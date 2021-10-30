package com.mantasciutys.cryptotrader.enums;

public enum OrderSide {
    BUY("buy"),
    SELL("sell");

    private final String orderSide;

    OrderSide(String orderSide) {
        this.orderSide = orderSide;
    }

    public String getOrderSide() {
        return orderSide;
    }
}
