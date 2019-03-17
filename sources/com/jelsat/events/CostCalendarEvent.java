package com.jelsat.events;

public class CostCalendarEvent {
    private String propertyId;

    public CostCalendarEvent(String str) {
        this.propertyId = str;
    }

    public String getPropertyId() {
        return this.propertyId;
    }
}
