package com.mantasciutys.cryptotrader.pojo;

import java.util.Date;

public class Order {
    private String id;
    private String price;
    private String size;
    private String product_id;
    private String profile_id;
    private String side;
    private String funds;
    private String specified_funds;
    private String type;
    private String time_in_force;
    private Date expire_time;
    private boolean post_only;
    private Date created_at;
    private Date done_at;
    private String done_reason;
    private String reject_reason;
    private String fill_fees;
    private String filled_size;
    private String executed_value;
    private String status;
    private boolean settled;
    private String stop;
    private String stop_price;
    private String funding_amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(String profile_id) {
        this.profile_id = profile_id;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public String getSpecified_funds() {
        return specified_funds;
    }

    public void setSpecified_funds(String specified_funds) {
        this.specified_funds = specified_funds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime_in_force() {
        return time_in_force;
    }

    public void setTime_in_force(String time_in_force) {
        this.time_in_force = time_in_force;
    }

    public Date getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(Date expire_time) {
        this.expire_time = expire_time;
    }

    public boolean isPost_only() {
        return post_only;
    }

    public void setPost_only(boolean post_only) {
        this.post_only = post_only;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getDone_at() {
        return done_at;
    }

    public void setDone_at(Date done_at) {
        this.done_at = done_at;
    }

    public String getDone_reason() {
        return done_reason;
    }

    public void setDone_reason(String done_reason) {
        this.done_reason = done_reason;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public String getFill_fees() {
        return fill_fees;
    }

    public void setFill_fees(String fill_fees) {
        this.fill_fees = fill_fees;
    }

    public String getFilled_size() {
        return filled_size;
    }

    public void setFilled_size(String filled_size) {
        this.filled_size = filled_size;
    }

    public String getExecuted_value() {
        return executed_value;
    }

    public void setExecuted_value(String executed_value) {
        this.executed_value = executed_value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getStop_price() {
        return stop_price;
    }

    public void setStop_price(String stop_price) {
        this.stop_price = stop_price;
    }

    public String getFunding_amount() {
        return funding_amount;
    }

    public void setFunding_amount(String funding_amount) {
        this.funding_amount = funding_amount;
    }
}
