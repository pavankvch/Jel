package com.data.hostbookings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HostBookingProperty {
    @SerializedName("booked_on")
    @Expose
    private String bookedOn;
    @SerializedName("booking_status")
    @Expose
    private String bookingStatus;
    @SerializedName("booking_status_code")
    @Expose
    private String bookingStatusCode;
    @SerializedName("booking_type")
    @Expose
    private String bookingType;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("end_day")
    @Expose
    private String endDay;
    @SerializedName("guest_feedback")
    @Expose
    private String guestFeedback;
    @SerializedName("guest_id")
    @Expose
    private String guestId;
    @SerializedName("guest_name")
    @Expose
    private String guestName;
    @SerializedName("host_feedback")
    @Expose
    private String hostFeedback;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nights")
    @Expose
    private String nights;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("start_day")
    @Expose
    private String startDay;
    @SerializedName("total_guests")
    @Expose
    private int totalGuests;

    public void setGuestFeedback(String str) {
        this.guestFeedback = str;
    }

    public void setHostFeedback(String str) {
        this.hostFeedback = str;
    }

    public String getGuestFeedback() {
        return this.guestFeedback;
    }

    public String getHostFeedback() {
        return this.hostFeedback;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String str) {
        this.bookingStatus = str;
    }

    public String getBookingStatusCode() {
        return this.bookingStatusCode;
    }

    public void setBookingStatusCode(String str) {
        this.bookingStatusCode = str;
    }

    public String getGuestId() {
        return this.guestId;
    }

    public void setGuestId(String str) {
        this.guestId = str;
    }

    public String getGuestName() {
        return this.guestName;
    }

    public void setGuestName(String str) {
        this.guestName = str;
    }

    public int getTotalGuests() {
        return this.totalGuests;
    }

    public void setTotalGuests(int i) {
        this.totalGuests = i;
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getBookedOn() {
        return this.bookedOn;
    }

    public void setBookedOn(String str) {
        this.bookedOn = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String str) {
        this.bookingType = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getNights() {
        return this.nights;
    }

    public void setNights(String str) {
        this.nights = str;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public String getStartDay() {
        return this.startDay;
    }

    public String getEndDay() {
        return this.endDay;
    }
}
