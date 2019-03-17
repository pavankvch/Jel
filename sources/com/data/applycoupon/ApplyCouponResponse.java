package com.data.applycoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyCouponResponse {
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("coupon_amount")
    @Expose
    private int couponAmount;
    @SerializedName("payable_amount")
    @Expose
    private int payableAmount;

    public int getCouponAmount() {
        return this.couponAmount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i) {
        this.amount = i;
    }

    public void setCouponAmount(int i) {
        this.couponAmount = i;
    }

    public int getPayableAmount() {
        return this.payableAmount;
    }

    public void setPayableAmount(int i) {
        this.payableAmount = i;
    }
}
