package com.jelsat.events;

public class BadgeEvent {
    private int badgeCount;

    public BadgeEvent(int i) {
        this.badgeCount = i;
    }

    public int getBadgeCount() {
        return this.badgeCount;
    }
}
