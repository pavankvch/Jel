package com.data.bookings;

import com.google.gson.annotations.SerializedName;

public class BookingCancelRequest {
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("reason")
    private String reason;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }
}
