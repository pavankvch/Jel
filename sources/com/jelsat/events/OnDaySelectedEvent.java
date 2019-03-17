package com.jelsat.events;

import java.util.Date;
import java.util.List;

public class OnDaySelectedEvent {
    private List<Date> dayList;

    public OnDaySelectedEvent(List<Date> list) {
        this.dayList = list;
    }

    public List<Date> getDayList() {
        return this.dayList;
    }
}
