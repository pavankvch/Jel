package com.data.propertycostcalendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCalendarRequest {
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("property_id")
    @Expose
    private int propertyId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("weekly_day")
    @Expose
    private String weeklyDay;

    public int getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(int i) {
        this.propertyId = i;
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getWeeklyDay() {
        return this.weeklyDay;
    }

    public void setWeeklyDay(String str) {
        this.weeklyDay = str;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float f) {
        this.price = f;
    }
}
