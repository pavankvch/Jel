package com.jelsat.events;

public class ChangeBottomTabsEvent {
    private boolean isHostSelected;

    public ChangeBottomTabsEvent(boolean z) {
        this.isHostSelected = z;
    }

    public boolean isHostSelected() {
        return this.isHostSelected;
    }
}
