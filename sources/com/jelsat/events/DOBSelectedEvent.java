package com.jelsat.events;

import java.util.Calendar;

public class DOBSelectedEvent {
    private Calendar calendar;

    public DOBSelectedEvent(Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }
}
