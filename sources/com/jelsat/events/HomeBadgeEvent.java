package com.jelsat.events;

public class HomeBadgeEvent {
    private int guestInboxCount;
    private int hostInboxCount;

    public int getGuestInboxCount() {
        return this.guestInboxCount;
    }

    public HomeBadgeEvent(int i, int i2) {
        this.guestInboxCount = i;
        this.hostInboxCount = i2;
    }

    public int getHostInboxCount() {
        return this.hostInboxCount;
    }
}
