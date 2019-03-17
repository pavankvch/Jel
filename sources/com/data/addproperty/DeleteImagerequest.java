package com.data.addproperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteImagerequest {
    @SerializedName("featured")
    @Expose
    private Boolean featured;
    @SerializedName("fid")
    @Expose
    private String fid;
    @SerializedName("property_id")
    @Expose
    private String propertyId;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public Boolean getFeatured() {
        return this.featured;
    }

    public void setFeatured(Boolean bool) {
        this.featured = bool;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }
}
