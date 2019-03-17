package com.data.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class InboxNotificationSectionModel {
    @SerializedName("data")
    @Expose
    private List<InboxNotificationData> data;
    @SerializedName("month")
    @Expose
    private String month;

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public List<InboxNotificationData> getData() {
        return this.data;
    }

    public void setData(List<InboxNotificationData> list) {
        this.data = list;
    }
}
