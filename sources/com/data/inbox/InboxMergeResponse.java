package com.data.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class InboxMergeResponse {
    @SerializedName("inbox_count")
    @Expose
    private int inboxCount;
    @SerializedName("messages")
    @Expose
    private List<InboxMessageData> messages;
    @SerializedName("messages_count")
    @Expose
    private int messagesCount;
    @SerializedName("notifications")
    @Expose
    private List<InboxNotificationSectionModel> notifications;
    @SerializedName("notifications_count")
    @Expose
    private String notificationsCount;

    public String getNotificationsCount() {
        return this.notificationsCount;
    }

    public void setNotificationsCount(String str) {
        this.notificationsCount = str;
    }

    public List<InboxNotificationSectionModel> getNotifications() {
        return this.notifications;
    }

    public void setNotifications(List<InboxNotificationSectionModel> list) {
        this.notifications = list;
    }

    public int getMessagesCount() {
        return this.messagesCount;
    }

    public void setMessagesCount(int i) {
        this.messagesCount = i;
    }

    public List<InboxMessageData> getMessages() {
        return this.messages;
    }

    public void setMessages(List<InboxMessageData> list) {
        this.messages = list;
    }

    public int getInboxCount() {
        return this.inboxCount;
    }

    public void setInboxCount(int i) {
        this.inboxCount = i;
    }
}
