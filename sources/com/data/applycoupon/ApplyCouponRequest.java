package com.data.applycoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyCouponRequest {
    @SerializedName("coupon")
    @Expose
    private String coupon;
    @SerializedName("type")
    @Expose
    private String type;

    public String getCoupon() {
        return this.coupon;
    }

    public void setCoupon(String str) {
        this.coupon = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
