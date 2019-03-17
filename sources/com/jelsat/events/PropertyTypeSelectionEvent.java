package com.jelsat.events;

import com.data.amenitiesandhouserules.CancelPolicy;
import com.data.amenitiesandhouserules.PropertyType;

public class PropertyTypeSelectionEvent {
    private CancelPolicy cancelPolicy;
    private boolean isChecked;
    private int position;
    private PropertyType propertyType;

    public PropertyTypeSelectionEvent(PropertyType propertyType, int i, boolean z) {
        this.propertyType = propertyType;
        this.position = i;
        this.isChecked = z;
    }

    public PropertyTypeSelectionEvent(CancelPolicy cancelPolicy, int i, boolean z) {
        this.cancelPolicy = cancelPolicy;
        this.position = i;
        this.isChecked = z;
    }

    public PropertyType getPropertyType() {
        return this.propertyType;
    }

    public int getPosition() {
        return this.position;
    }

    public boolean isChecked() {
        return this.isChecked;
    }
}
