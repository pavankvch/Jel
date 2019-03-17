package com.jelsat.adapters.conversationsadapter;

import com.data.inbox.MessageData;
import com.jelsat.adapters.Inboxmessagesadapter.MessageSectionV2;
import java.util.List;

public class ConversationsSectionModel implements MessageSectionV2<MessageData> {
    private List<MessageData> itemArrayList;
    private String sectionLabel;

    public ConversationsSectionModel(String str, List<MessageData> list) {
        this.sectionLabel = str;
        this.itemArrayList = list;
    }

    public String getSectionLabel() {
        return this.sectionLabel;
    }

    public List<MessageData> getItemArrayList() {
        return this.itemArrayList;
    }

    public List<MessageData> getChildItems() {
        return this.itemArrayList;
    }
}
