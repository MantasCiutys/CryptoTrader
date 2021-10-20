package com.mantasciutys.cryptotrader.pojo;

public class Product {
    private String id;
    private String base_currency;
    private String quote_currency;
    private String base_min_size;
    private String base_max_size;
    private String quote_increment;
    private String base_increment;
    private String display_name;
    private String min_market_funds;
    private String max_market_funds;
    private boolean margin_enabled;
    private boolean post_only;
    private boolean limit_only;
    private boolean cancel_only;
    private String status;
    private String status_message;
    private boolean trading_disabled;
    private boolean fx_stablecoin;
    private String max_slippage_percentage;
    private boolean auction_mode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBase_currency() {
        return base_currency;
    }

    public void setBase_currency(String base_currency) {
        this.base_currency = base_currency;
    }

    public String getQuote_currency() {
        return quote_currency;
    }

    public void setQuote_currency(String quote_currency) {
        this.quote_currency = quote_currency;
    }

    public String getBase_min_size() {
        return base_min_size;
    }

    public void setBase_min_size(String base_min_size) {
        this.base_min_size = base_min_size;
    }

    public String getBase_max_size() {
        return base_max_size;
    }

    public void setBase_max_size(String base_max_size) {
        this.base_max_size = base_max_size;
    }

    public String getQuote_increment() {
        return quote_increment;
    }

    public void setQuote_increment(String quote_increment) {
        this.quote_increment = quote_increment;
    }

    public String getBase_increment() {
        return base_increment;
    }

    public void setBase_increment(String base_increment) {
        this.base_increment = base_increment;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getMin_market_funds() {
        return min_market_funds;
    }

    public void setMin_market_funds(String min_market_funds) {
        this.min_market_funds = min_market_funds;
    }

    public String getMax_market_funds() {
        return max_market_funds;
    }

    public void setMax_market_funds(String max_market_funds) {
        this.max_market_funds = max_market_funds;
    }

    public boolean isMargin_enabled() {
        return margin_enabled;
    }

    public void setMargin_enabled(boolean margin_enabled) {
        this.margin_enabled = margin_enabled;
    }

    public boolean isPost_only() {
        return post_only;
    }

    public void setPost_only(boolean post_only) {
        this.post_only = post_only;
    }

    public boolean isLimit_only() {
        return limit_only;
    }

    public void setLimit_only(boolean limit_only) {
        this.limit_only = limit_only;
    }

    public boolean isCancel_only() {
        return cancel_only;
    }

    public void setCancel_only(boolean cancel_only) {
        this.cancel_only = cancel_only;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public boolean isTrading_disabled() {
        return trading_disabled;
    }

    public void setTrading_disabled(boolean trading_disabled) {
        this.trading_disabled = trading_disabled;
    }

    public boolean isFx_stablecoin() {
        return fx_stablecoin;
    }

    public void setFx_stablecoin(boolean fx_stablecoin) {
        this.fx_stablecoin = fx_stablecoin;
    }

    public String getMax_slippage_percentage() {
        return max_slippage_percentage;
    }

    public void setMax_slippage_percentage(String max_slippage_percentage) {
        this.max_slippage_percentage = max_slippage_percentage;
    }

    public boolean isAuction_mode() {
        return auction_mode;
    }

    public void setAuction_mode(boolean auction_mode) {
        this.auction_mode = auction_mode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", base_currency='" + base_currency + '\'' +
                ", quote_currency='" + quote_currency + '\'' +
                ", base_min_size='" + base_min_size + '\'' +
                ", base_max_size='" + base_max_size + '\'' +
                ", quote_increment='" + quote_increment + '\'' +
                ", base_increment='" + base_increment + '\'' +
                ", display_name='" + display_name + '\'' +
                ", min_market_funds='" + min_market_funds + '\'' +
                ", max_market_funds='" + max_market_funds + '\'' +
                ", margin_enabled=" + margin_enabled +
                ", post_only=" + post_only +
                ", limit_only=" + limit_only +
                ", cancel_only=" + cancel_only +
                ", status='" + status + '\'' +
                ", status_message='" + status_message + '\'' +
                ", trading_disabled=" + trading_disabled +
                ", fx_stablecoin=" + fx_stablecoin +
                ", max_slippage_percentage='" + max_slippage_percentage + '\'' +
                ", auction_mode=" + auction_mode +
                '}';
    }
}
