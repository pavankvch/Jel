package com.data.viewbill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyViewBillRequest {
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("guests")
    @Expose
    private String guests;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("start_date")
    @Expose
    private String startDate;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getGuests() {
        return this.guests;
    }

    public void setGuests(String str) {
        this.guests = str;
    }
}
