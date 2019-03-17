package com.jelsat.events;

public class GuestCountEvent {
    private String guestCount;

    public GuestCountEvent(String str) {
        this.guestCount = str;
    }

    public String getGuestCount() {
        return this.guestCount;
    }
}
