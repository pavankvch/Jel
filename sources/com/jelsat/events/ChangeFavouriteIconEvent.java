package com.jelsat.events;

public class ChangeFavouriteIconEvent {
    private int favourite;
    private String propertyId;

    public ChangeFavouriteIconEvent(String str, int i) {
        this.propertyId = str;
        this.favourite = i;
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public int getFavourite() {
        return this.favourite;
    }
}
