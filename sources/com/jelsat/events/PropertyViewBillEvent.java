package com.jelsat.events;

import com.data.viewbill.PropertyViewBillResponse;
import java.util.Date;

public class PropertyViewBillEvent {
    private String addressName;
    private Date checkInDate;
    private Date checkOutDate;
    private String locationName;
    private String maxGuestCount;
    private int minimumNights;
    private PropertyViewBillResponse response;

    public PropertyViewBillEvent(PropertyViewBillResponse propertyViewBillResponse) {
        this.response = propertyViewBillResponse;
    }

    public PropertyViewBillResponse getResponse() {
        return this.response;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(Date date) {
        this.checkInDate = date;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(Date date) {
        this.checkOutDate = date;
    }

    public String getMaxGuestCount() {
        return this.maxGuestCount;
    }

    public void setMaxGuestCount(String str) {
        this.maxGuestCount = str;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String str) {
        this.locationName = str;
    }

    public int getMinimumNights() {
        return this.minimumNights;
    }

    public void setMinimumNights(int i) {
        this.minimumNights = i;
    }

    public String getAddressName() {
        return this.addressName;
    }

    public void setAddressName(String str) {
        this.addressName = str;
    }
}
