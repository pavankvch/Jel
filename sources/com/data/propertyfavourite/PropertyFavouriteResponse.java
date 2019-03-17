package com.data.propertyfavourite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyFavouriteResponse {
    @SerializedName("favourite")
    @Expose
    private String favourite;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getFavourite() {
        return this.favourite;
    }

    public void setFavourite(String str) {
        this.favourite = str;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean bool) {
        this.status = bool;
    }
}
