package com.jelsat.adapters.hostpaymentstotalbookingsadapter;

import com.data.payments.totalbookings.HostBookingData;
import com.jelsat.adapters.Inboxmessagesadapter.MessageSectionV2;
import java.util.List;

public class BookingsSectionModel implements MessageSectionV2<HostBookingData> {
    private List<HostBookingData> itemArrayList;
    private String sectionLabel;

    public BookingsSectionModel(String str, List<HostBookingData> list) {
        this.sectionLabel = str;
        this.itemArrayList = list;
    }

    public String getSectionLabel() {
        return this.sectionLabel;
    }

    public List<HostBookingData> getItemArrayList() {
        return this.itemArrayList;
    }

    public List<HostBookingData> getChildItems() {
        return this.itemArrayList;
    }
}
