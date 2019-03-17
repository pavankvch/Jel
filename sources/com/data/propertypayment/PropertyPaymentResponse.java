package com.data.propertypayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyPaymentResponse {
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("booking_type")
    @Expose
    private String bookingType;
    @SerializedName("check_in_date")
    @Expose
    private String checkInDate;
    @SerializedName("check_out_date")
    @Expose
    private String checkOutDate;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("nights")
    @Expose
    private String nights;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("property_address")
    @Expose
    private String propertyAddress;
    @SerializedName("property_image")
    @Expose
    private String propertyImage;
    @SerializedName("property_name")
    @Expose
    private String propertyName;
    @SerializedName("property_type")
    @Expose
    private String propertyType;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("reviewed")
    @Expose
    private int reviewed;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("no_of_guests")
    @Expose
    private int totalGuests;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;

    public String getTransactionId() {
        return this.transactionId;
    }

    public String getDate() {
        return this.date;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public String getPropertyImage() {
        return this.propertyImage;
    }

    public String getPropertyAddress() {
        return this.propertyAddress;
    }

    public boolean isStatus() {
        return this.status;
    }

    public int getRating() {
        return this.rating;
    }

    public int getReviewed() {
        return this.reviewed;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public String getCheckInDate() {
        return this.checkInDate;
    }

    public String getCheckOutDate() {
        return this.checkOutDate;
    }

    public int getTotalGuests() {
        return this.totalGuests;
    }

    public String getNights() {
        return this.nights;
    }

    public String getOrderId() {
        return this.orderId;
    }
}
