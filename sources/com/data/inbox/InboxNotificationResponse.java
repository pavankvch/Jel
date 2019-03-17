package com.data.inbox;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class InboxNotificationResponse {
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
}
