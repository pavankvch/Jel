package com.jelsat.events;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class LatLngListEvent {
    private List<LatLng> locations;

    public LatLngListEvent(List<LatLng> list) {
        this.locations = list;
    }

    public List<LatLng> getLocations() {
        return this.locations;
    }
}
