package com.jelsat.events;

import com.data.amenitiesandhouserules.PropertyType;
import java.util.Date;
import java.util.Map;

public class DateGuestPropertyDialogEvent {
    private Date checkInDate;
    private Date checkOutDate;
    private String cost;
    private String guestCount;
    private Map<String, PropertyType> selectedPropertyTypes;

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

    public String getGuestCount() {
        return this.guestCount;
    }

    public void setGuestCount(String str) {
        this.guestCount = str;
    }

    public Map<String, PropertyType> getSelectedPropertyTypes() {
        return this.selectedPropertyTypes;
    }

    public void setSelectedPropertyTypes(Map<String, PropertyType> map) {
        this.selectedPropertyTypes = map;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String str) {
        this.cost = str;
    }
}
