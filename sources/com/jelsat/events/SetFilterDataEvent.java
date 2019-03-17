package com.jelsat.events;

import com.data.filter.FilterData;
import java.util.List;

public class SetFilterDataEvent {
    private List<String> amenityIds;
    private String bookingType;
    private FilterData request;

    public SetFilterDataEvent(FilterData filterData, String str, List<String> list) {
        this.request = filterData;
        this.bookingType = str;
        this.amenityIds = list;
    }

    public FilterData getRequest() {
        return this.request;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public List<String> getAmenityIds() {
        return this.amenityIds;
    }
}
