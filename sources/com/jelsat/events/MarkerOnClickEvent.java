package com.jelsat.events;

import com.data.searchproperty.SearchProperty;
import com.google.android.gms.maps.model.LatLng;

public class MarkerOnClickEvent {
    private LatLng latLng;
    private SearchProperty property;

    public MarkerOnClickEvent(LatLng latLng) {
        this.latLng = latLng;
    }

    public MarkerOnClickEvent(LatLng latLng, SearchProperty searchProperty) {
        this.latLng = latLng;
        this.property = searchProperty;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public SearchProperty getProperty() {
        return this.property;
    }
}
