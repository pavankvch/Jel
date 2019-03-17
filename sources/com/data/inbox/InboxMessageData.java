package com.data.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class InboxMessageData {
    @SerializedName("data")
    @Expose
    private List<MessageData> data;
    @SerializedName("date")
    @Expose
    private String date;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public List<MessageData> getData() {
        return this.data;
    }

    public void setData(List<MessageData> list) {
        this.data = list;
    }
}
