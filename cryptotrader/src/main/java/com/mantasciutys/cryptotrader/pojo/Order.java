package com.mantasciutys.cryptotrader.pojo;

import com.sun.org.apache.xpath.internal.operations.Or;

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

    public static class OrderBuilder {
        //required
        private final String id;

        //optional
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

        public OrderBuilder(String id) {
            this.id = id;
        }

        public OrderBuilder price(String price) {
            this.price = price;
            return this;
        }

        public OrderBuilder size(String size) {
            this.size = size;
            return this;
        }

        public OrderBuilder product_id(String product_id) {
            this.product_id = product_id;
            return this;
        }

        public OrderBuilder profile_id(String profile_id) {
            this.profile_id = profile_id;
            return this;
        }

        public OrderBuilder side(String side) {
            this.side = side;
            return this;
        }

        public OrderBuilder funds(String funds) {
            this.funds = funds;
            return this;
        }

        public OrderBuilder specified_funds(String specified_funds) {
            this.specified_funds = specified_funds;
            return this;
        }

        public OrderBuilder type(String type) {
            this.type = type;
            return this;
        }

        public OrderBuilder time_in_force(String time_in_force) {
            this.time_in_force = time_in_force;
            return this;
        }

        public OrderBuilder expire_time(Date expire_time) {
            this.expire_time = expire_time;
            return this;
        }

        public OrderBuilder post_only(boolean post_only) {
            this.type = type;
            return this;
        }

        public OrderBuilder created_at(Date created_at) {
            this.created_at = created_at;
            return this;
        }

        public OrderBuilder done_at(Date done_at) {
            this.done_at = done_at;
            return this;
        }

        public OrderBuilder done_reason(String done_reason) {
            this.done_reason = done_reason;
            return this;
        }

        public OrderBuilder reject_reason(String reject_reason) {
            this.reject_reason = reject_reason;
            return this;
        }

        public OrderBuilder fill_fees(String fill_fees) {
            this.fill_fees = fill_fees;
            return this;
        }

        public OrderBuilder filled_size(String filled_size) {
            this.filled_size = filled_size;
            return this;
        }

        public OrderBuilder executed_value(String executed_value) {
            this.executed_value = executed_value;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder settled(boolean settled) {
            this.settled = settled;
            return this;
        }

        public OrderBuilder stop(String stop) {
            this.stop = stop;
            return this;
        }

        public OrderBuilder stop_price(String stop_price) {
            this.stop_price = stop_price;
            return this;
        }

        public OrderBuilder funding_amount(String funding_amount) {
            this.funding_amount = funding_amount;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public Order() {
    }

    private Order(OrderBuilder orderBuilder) {
        id = orderBuilder.id;
        price = orderBuilder.price;
        size = orderBuilder.size;
        product_id = orderBuilder.product_id;
        profile_id = orderBuilder.profile_id;
        side = orderBuilder.side;
        funds = orderBuilder.funds;
        specified_funds = orderBuilder.specified_funds;
        type = orderBuilder.type;
        time_in_force = orderBuilder.time_in_force;
        expire_time = orderBuilder.expire_time;
        post_only = orderBuilder.post_only;
        created_at = orderBuilder.created_at;
        done_at = orderBuilder.done_at;
        done_reason = orderBuilder.done_reason;
        reject_reason = orderBuilder.reject_reason;
        fill_fees = orderBuilder.fill_fees;
        filled_size = orderBuilder.filled_size;
        executed_value = orderBuilder.executed_value;
        status = orderBuilder.status;
        settled = orderBuilder.settled;
        stop = orderBuilder.stop;
        stop_price = orderBuilder.stop_price;
        funding_amount = orderBuilder.funding_amount;
    }

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

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", price='" + price + '\'' +
                ", size='" + size + '\'' +
                ", product_id='" + product_id + '\'' +
                ", profile_id='" + profile_id + '\'' +
                ", side='" + side + '\'' +
                ", funds='" + funds + '\'' +
                ", specified_funds='" + specified_funds + '\'' +
                ", type='" + type + '\'' +
                ", time_in_force='" + time_in_force + '\'' +
                ", expire_time=" + expire_time +
                ", post_only=" + post_only +
                ", created_at=" + created_at +
                ", done_at=" + done_at +
                ", done_reason='" + done_reason + '\'' +
                ", reject_reason='" + reject_reason + '\'' +
                ", fill_fees='" + fill_fees + '\'' +
                ", filled_size='" + filled_size + '\'' +
                ", executed_value='" + executed_value + '\'' +
                ", status='" + status + '\'' +
                ", settled=" + settled +
                ", stop='" + stop + '\'' +
                ", stop_price='" + stop_price + '\'' +
                ", funding_amount='" + funding_amount + '\'' +
                '}';
    }
}
