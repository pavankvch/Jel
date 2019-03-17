package com.data.propertyfavourite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyFavouriteRequest {
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
