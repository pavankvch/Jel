package com.jelsat.events;

import com.data.searchproperty.SelectedPropertyListData;

public class PropertiesListEvent {
    private SelectedPropertyListData selectedPropertyListData;

    public PropertiesListEvent(SelectedPropertyListData selectedPropertyListData) {
        this.selectedPropertyListData = selectedPropertyListData;
    }

    public SelectedPropertyListData getSelectedPropertyListData() {
        return this.selectedPropertyListData;
    }
}
