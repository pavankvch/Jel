package com.data.utils;

import java.util.Date;

public class AvailableDates {
    private Date availableDate;
    private boolean isSelectable;

    public boolean isSelectable() {
        return this.isSelectable;
    }

    public void setSelectable(boolean z) {
        this.isSelectable = z;
    }

    public Date getAvailableDate() {
        return this.availableDate;
    }

    public void setAvailableDate(Date date) {
        this.availableDate = date;
    }
}
