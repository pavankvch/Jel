package com.jelsat.events;

import java.util.Date;

public class PropertyBookEvent {
    private Date checkInDate;
    private Date checkOutDate;
    private String propertyId;
    private String selectedGuestCount;

    public PropertyBookEvent(String str) {
        this.propertyId = str;
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckInDate(Date date) {
        this.checkInDate = date;
    }

    public void setCheckOutDate(Date date) {
        this.checkOutDate = date;
    }

    public String getSelectedGuestCount() {
        return this.selectedGuestCount;
    }

    public void setSelectedGuestCount(String str) {
        this.selectedGuestCount = str;
    }
}
