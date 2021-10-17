package com.mantasciutys.cryptotrader.pojo;

import java.math.BigDecimal;

public class Account {
    private String id;
    private String currency;
    private BigDecimal balance;
    private BigDecimal available;
    private BigDecimal hold;
    private String profile_id;
    private boolean trading_enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getHold() {
        return hold;
    }

    public void setHold(BigDecimal hold) {
        this.hold = hold;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public boolean isTrading_enabled() {
        return trading_enabled;
    }

    public void setTrading_enabled(boolean trading_enabled) {
        this.trading_enabled = trading_enabled;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", available=" + available +
                ", hold=" + hold +
                ", profile_id='" + profile_id + '\'' +
                ", trading_enabled=" + trading_enabled +
                '}';
    }
}
