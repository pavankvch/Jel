package com.jelsat.events;

import com.data.searchproperty.SearchProperty;
import com.google.android.gms.maps.model.LatLng;

public class LatLngEvent {
    private boolean fromBookings;
    private LatLng latLng;
    private SearchProperty searchProperty;

    public LatLngEvent(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLngEvent(LatLng latLng, SearchProperty searchProperty) {
        this.latLng = latLng;
        this.searchProperty = searchProperty;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public SearchProperty getSearchProperty() {
        return this.searchProperty;
    }

    public boolean isFromBookings() {
        return this.fromBookings;
    }

    public void setFromBookings(boolean z) {
        this.fromBookings = z;
    }
}
