package com.data.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReadNotificationRequest {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;

    public void setId(String str) {
        this.id = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
