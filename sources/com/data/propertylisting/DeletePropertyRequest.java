package com.data.propertylisting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeletePropertyRequest {
    @SerializedName("property_id")
    @Expose
    private int propertyId;

    public int getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(int i) {
        this.propertyId = i;
    }
}
