package com.data.addproperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormData {
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("tittle")
    @Expose
    private String tittle;
    @SerializedName("type")
    @Expose
    private String type;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getTittle() {
        return this.tittle;
    }

    public void setTittle(String str) {
        this.tittle = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
