package com.jelsat.events;

import com.data.addproperty.AddPropertyResponse;

public class DataPlottingInAddpropertyEvent {
    AddPropertyResponse addPropertyResponse;

    public DataPlottingInAddpropertyEvent(AddPropertyResponse addPropertyResponse) {
        this.addPropertyResponse = addPropertyResponse;
    }

    public AddPropertyResponse getAddPropertyResponse() {
        return this.addPropertyResponse;
    }
}
