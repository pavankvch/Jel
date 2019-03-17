package com.jelsat.events;

import com.data.filter.FilterData;

public class ApplyFilterEvent {
    private FilterData filterData;

    public ApplyFilterEvent(FilterData filterData) {
        this.filterData = filterData;
    }

    public FilterData getFilterData() {
        return this.filterData;
    }
}
