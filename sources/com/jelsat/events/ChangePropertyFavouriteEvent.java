package com.jelsat.events;

import com.data.propertyfavourite.PropertyFavouriteResponse;

public class ChangePropertyFavouriteEvent {
    private boolean isSaved;
    private PropertyFavouriteResponse response;

    public ChangePropertyFavouriteEvent(PropertyFavouriteResponse propertyFavouriteResponse, boolean z) {
        this.response = propertyFavouriteResponse;
        this.isSaved = z;
    }

    public PropertyFavouriteResponse getResponse() {
        return this.response;
    }

    public boolean isSaved() {
        return this.isSaved;
    }
}
