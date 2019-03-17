package com.data.inbox;

import com.google.gson.annotations.SerializedName;

public class MessageHistoryRequest {
    @SerializedName("conversation_id")
    private String conversationId;

    public String getConversationId() {
        return this.conversationId;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }
}
