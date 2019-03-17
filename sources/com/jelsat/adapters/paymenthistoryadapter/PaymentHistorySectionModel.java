package com.jelsat.adapters.paymenthistoryadapter;

import com.data.paymenthistory.TransactionData;
import com.jelsat.adapters.Inboxmessagesadapter.MessageSectionV2;
import java.util.List;

public class PaymentHistorySectionModel implements MessageSectionV2<TransactionData> {
    private List<TransactionData> itemArrayList;
    private String sectionLabel;

    public PaymentHistorySectionModel(String str, List<TransactionData> list) {
        this.sectionLabel = str;
        this.itemArrayList = list;
    }

    public String getSectionLabel() {
        return this.sectionLabel;
    }

    public List<TransactionData> getItemArrayList() {
        return this.itemArrayList;
    }

    public List<TransactionData> getChildItems() {
        return this.itemArrayList;
    }
}
