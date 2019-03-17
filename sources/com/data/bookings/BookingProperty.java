package com.data.bookings;

import com.data.viewbill.Addon;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BookingProperty {
    @SerializedName("addon")
    @Expose
    private List<Addon> addon = null;
    @SerializedName("base_price")
    @Expose
    private float basePrice;
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
    @SerializedName("coupon_amount")
    @Expose
    private float couponAmount;
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
    @SerializedName("host_feedback")
    @Expose
    private String hostFeedback;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
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
    private float price;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("service_fee")
    @Expose
    private float serviceFee;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("start_day")
    @Expose
    private String startDay;
    @SerializedName("tax")
    @Expose
    private float tax;
    @SerializedName("total")
    @Expose
    private float total;
    @SerializedName("total_guests")
    @Expose
    private int totalGuests;
    @SerializedName("unit_price")
    @Expose
    private float unitPrice;

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

    public String getPropertyId() {
        return this.propertyId;
    }

    public int getTotalGuests() {
        return this.totalGuests;
    }

    public String getName() {
        return this.name;
    }

    public float getPrice() {
        return this.price;
    }

    public float getTotal() {
        return this.total;
    }

    public List<Addon> getAddon() {
        return this.addon;
    }

    public float getTax() {
        return this.tax;
    }

    public String getBookedOn() {
        return this.bookedOn;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public String getBookingStatusCode() {
        return this.bookingStatusCode;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public String getCountry() {
        return this.country;
    }

    public String getImage() {
        return this.image;
    }

    public String getNights() {
        return this.nights;
    }

    public String getLat() {
        return this.lat;
    }

    public String getLng() {
        return this.lng;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public float getUnitPrice() {
        return this.unitPrice;
    }

    public float getBasePrice() {
        return this.basePrice;
    }

    public float getServiceFee() {
        return this.serviceFee;
    }

    public float getCouponAmount() {
        return this.couponAmount;
    }

    public String getStartDay() {
        return this.startDay;
    }

    public String getEndDay() {
        return this.endDay;
    }
}
