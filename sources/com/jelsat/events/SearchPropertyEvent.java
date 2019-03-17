package com.jelsat.events;

import com.data.searchtoplocalities.Locality;

public class SearchPropertyEvent {
    private String bookingType;
    private boolean isNearBy;
    private Locality locality;

    public SearchPropertyEvent(Locality locality, boolean z) {
        this.locality = locality;
        this.isNearBy = z;
    }

    public SearchPropertyEvent(String str) {
        this.bookingType = str;
    }

    public boolean isNearBy() {
        return this.isNearBy;
    }

    public Locality getLocality() {
        return this.locality;
    }

    public String getBookingType() {
        return this.bookingType;
    }
}
