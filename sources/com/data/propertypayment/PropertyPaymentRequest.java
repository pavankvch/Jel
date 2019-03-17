package com.data.propertypayment;

import com.data.payfort.PayFortData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyPaymentRequest {
    @SerializedName("booking_type")
    @Expose
    private String bookingType;
    @SerializedName("card")
    @Expose
    private String card;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("payload")
    @Expose
    private PayFortData payload;
    @SerializedName("saved_card")
    @Expose
    private boolean savedCard;
    @SerializedName("wallet")
    @Expose
    private String wallet;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String str) {
        this.bookingType = str;
    }

    public String getWallet() {
        return this.wallet;
    }

    public void setWallet(String str) {
        this.wallet = str;
    }

    public String getCard() {
        return this.card;
    }

    public void setCard(String str) {
        this.card = str;
    }

    public boolean isSavedCard() {
        return this.savedCard;
    }

    public void setSavedCard(boolean z) {
        this.savedCard = z;
    }

    public PayFortData getPayload() {
        return this.payload;
    }

    public void setPayload(PayFortData payFortData) {
        this.payload = payFortData;
    }
}
