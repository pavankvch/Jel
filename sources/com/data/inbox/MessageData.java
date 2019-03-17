package com.data.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageData {
    @SerializedName("conversation_id")
    @Expose
    private String conversationId;
    @SerializedName("conversation_title")
    @Expose
    private String conversationTitle;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("property_image")
    @Expose
    private String propertyImage;
    @SerializedName("read_message")
    @Expose
    private String readMessage;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("time")
    @Expose
    private String time;

    public String getConversationId() {
        return this.conversationId;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public String getPropertyImage() {
        return this.propertyImage;
    }

    public void setPropertyImage(String str) {
        this.propertyImage = str;
    }

    public String getConversationTitle() {
        return this.conversationTitle;
    }

    public void setConversationTitle(String str) {
        this.conversationTitle = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getReadMessage() {
        return this.readMessage;
    }

    public void setReadMessage(String str) {
        this.readMessage = str;
    }
}
