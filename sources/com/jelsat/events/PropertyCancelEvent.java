package com.jelsat.events;

public class PropertyCancelEvent {
    private boolean cancelSuccess;

    public PropertyCancelEvent(boolean z) {
        this.cancelSuccess = z;
    }

    public boolean isCancelSuccess() {
        return this.cancelSuccess;
    }

    public void setCancelSuccess(boolean z) {
        this.cancelSuccess = z;
    }
}
