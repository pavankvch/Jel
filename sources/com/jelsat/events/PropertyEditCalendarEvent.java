package com.jelsat.events;

public class PropertyEditCalendarEvent {
    private String propertyId;

    public PropertyEditCalendarEvent(String str) {
        this.propertyId = str;
    }

    public String getPropertyId() {
        return this.propertyId;
    }
}
