package com.data.inbox;

import com.google.gson.annotations.SerializedName;

public class SendMessageRequestHost {
    @SerializedName("conversation_id")
    private String conversationId;
    @SerializedName("guest_id")
    private String guestId;
    private String message;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("property_id")
    private String propertyId;
    private String role;

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public void setConversationId(String str) {
        this.conversationId = str;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String str) {
        this.role = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getGuestId() {
        return this.guestId;
    }

    public void setGuestId(String str) {
        this.guestId = str;
    }
}
