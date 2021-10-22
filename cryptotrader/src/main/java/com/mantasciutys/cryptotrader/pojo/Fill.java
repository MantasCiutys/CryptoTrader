package com.mantasciutys.cryptotrader.pojo;

import java.util.Date;

public class Fill {
    private Date created_at;
    private Long trade_id;
    private String product_id;
    private String order_id;
    private String user_id;
    private String profile_id;
    private String liquidity;
    private String price;
    private String size;
    private String fee;
    private String side;
    private boolean settled;
    private String usd_volume;

    public Fill() {
    }

    public Fill(Date created_at, Long trade_id, String product_id, String order_id, String user_id, String profile_id, String liquidity, String price, String size, String fee, String side, boolean settled, String usd_volume) {
        this.created_at = created_at;
        this.trade_id = trade_id;
        this.product_id = product_id;
        this.order_id = order_id;
        this.user_id = user_id;
        this.profile_id = profile_id;
        this.liquidity = liquidity;
        this.price = price;
        this.size = size;
        this.fee = fee;
        this.side = side;
        this.settled = settled;
        this.usd_volume = usd_volume;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Long trade_id) {
        this.trade_id = trade_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(String liquidity) {
        this.liquidity = liquidity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public String getUsd_volume() {
        return usd_volume;
    }

    public void setUsd_volume(String usd_volume) {
        this.usd_volume = usd_volume;
    }
}
