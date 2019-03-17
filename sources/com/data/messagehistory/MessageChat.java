package com.data.messagehistory;

import com.data.inbox.MessageData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MessageChat {
    @SerializedName("data")
    @Expose
    private List<MessageData> chatData;
    @SerializedName("date")
    @Expose
    private String date;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public List<MessageData> getChatData() {
        return this.chatData;
    }

    public void setChatData(List<MessageData> list) {
        this.chatData = list;
    }
}
