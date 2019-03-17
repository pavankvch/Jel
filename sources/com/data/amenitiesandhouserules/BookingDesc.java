package com.data.amenitiesandhouserules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingDesc {
    @SerializedName("24hours")
    @Expose
    private String _24hours;
    @SerializedName("instant")
    @Expose
    private String instant;

    public String getInstant() {
        return this.instant;
    }

    public void setInstant(String str) {
        this.instant = str;
    }

    public String get24hours() {
        return this._24hours;
    }

    public void set24hours(String str) {
        this._24hours = str;
    }
}
