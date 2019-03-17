package com.jelsat.adapters.notificationsadapter;

import com.data.inbox.InboxNotificationData;
import com.jelsat.adapters.Inboxmessagesadapter.MessageSectionV2;
import java.util.List;

public class NotificationSectionModel implements MessageSectionV2<InboxNotificationData> {
    private List<InboxNotificationData> itemArrayList;
    private String sectionLabel;

    public NotificationSectionModel(String str, List<InboxNotificationData> list) {
        this.sectionLabel = str;
        this.itemArrayList = list;
    }

    public String getSectionLabel() {
        return this.sectionLabel;
    }

    public List<InboxNotificationData> getItemArrayList() {
        return this.itemArrayList;
    }

    public List<InboxNotificationData> getChildItems() {
        return this.itemArrayList;
    }
}
