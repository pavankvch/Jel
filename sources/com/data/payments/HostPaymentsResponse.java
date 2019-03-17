package com.data.payments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HostPaymentsResponse {
    @SerializedName("payments")
    @Expose
    private HostPaymentsDetailsResponse paymentsDetails;
    @SerializedName("total")
    @Expose
    private float total;
    @SerializedName("total_bookings")
    @Expose
    private int totalBookings;
    @SerializedName("total_cancels")
    @Expose
    private int totalCancels;
    @SerializedName("total_properties")
    @Expose
    private int totalProperties;

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float f) {
        this.total = f;
    }

    public int getTotalProperties() {
        return this.totalProperties;
    }

    public void setTotalProperties(int i) {
        this.totalProperties = i;
    }

    public int getTotalBookings() {
        return this.totalBookings;
    }

    public void setTotalBookings(int i) {
        this.totalBookings = i;
    }

    public int getTotalCancels() {
        return this.totalCancels;
    }

    public void setTotalCancels(int i) {
        this.totalCancels = i;
    }

    public HostPaymentsDetailsResponse getPaymentsDetails() {
        return this.paymentsDetails;
    }

    public void setPaymentsDetails(HostPaymentsDetailsResponse hostPaymentsDetailsResponse) {
        this.paymentsDetails = hostPaymentsDetailsResponse;
    }
}
