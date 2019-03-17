package com.data.propertydetail;

import com.google.gson.annotations.SerializedName;

public class PropertyDetailRequest {
    @SerializedName("check_in")
    private String checkIn;
    @SerializedName("check_out")
    private String checkOut;
    @SerializedName("property_id")
    private String propertyId;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(String str) {
        this.checkIn = str;
    }

    public String getCheckOut() {
        return this.checkOut;
    }

    public void setCheckOut(String str) {
        this.checkOut = str;
    }
}
