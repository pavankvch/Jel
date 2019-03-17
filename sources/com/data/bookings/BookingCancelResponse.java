package com.data.bookings;

import com.data.propertypayment.PropertyPaymentResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingCancelResponse {
    @SerializedName("data")
    @Expose
    private PropertyPaymentResponse bookingCancelData;
    @SerializedName("status")
    @Expose
    private boolean status;

    public PropertyPaymentResponse getBookingCancelData() {
        return this.bookingCancelData;
    }

    public void setBookingCancelData(PropertyPaymentResponse propertyPaymentResponse) {
        this.bookingCancelData = propertyPaymentResponse;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean z) {
        this.status = z;
    }
}
