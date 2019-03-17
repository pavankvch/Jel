package com.data.payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentsDetailsData {
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("bookings")
    @Expose
    private int bookings;
    @SerializedName("earnings")
    @Expose
    private float earnings;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("name")
    @Expose
    private String propertyName;

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String str) {
        this.propertyName = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(String str) {
        this.paymentStatus = str;
    }

    public int getBookings() {
        return this.bookings;
    }

    public void setBookings(int i) {
        this.bookings = i;
    }

    public float getEarnings() {
        return this.earnings;
    }

    public void setEarnings(float f) {
        this.earnings = f;
    }
}
