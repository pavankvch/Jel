package com.jelsat.adapters.Inboxmessagesadapter;

import com.data.inbox.MessageData;
import java.util.List;

public class SectionModel implements MessageSectionV2<MessageData> {
    private List<MessageData> itemArrayList;
    private String sectionLabel;

    public SectionModel(String str, List<MessageData> list) {
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
