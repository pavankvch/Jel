package com.data.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class InboxMessageResponse {
    @SerializedName("messages")
    @Expose
    private List<InboxMessageData> messages = null;
    @SerializedName("messages_count")
    @Expose
    private String messagesCount;

    public String getMessagesCount() {
        return this.messagesCount;
    }

    public void setMessagesCount(String str) {
        this.messagesCount = str;
    }

    public List<InboxMessageData> getMessages() {
        return this.messages;
    }

    public void setMessages(List<InboxMessageData> list) {
        this.messages = list;
    }
}
