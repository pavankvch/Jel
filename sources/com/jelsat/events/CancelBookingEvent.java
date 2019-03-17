package com.jelsat.events;

import com.data.propertypayment.PropertyPaymentResponse;

public class CancelBookingEvent {
    private boolean isFromGuest;
    private PropertyPaymentResponse response;

    public CancelBookingEvent(PropertyPaymentResponse propertyPaymentResponse, boolean z) {
        this.response = propertyPaymentResponse;
        this.isFromGuest = z;
    }

    public PropertyPaymentResponse getResponse() {
        return this.response;
    }

    public boolean isFromGuest() {
        return this.isFromGuest;
    }
}
