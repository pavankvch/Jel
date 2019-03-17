package com.jelsat.adapters.ListingAdapter;

import com.data.propertylisting.Published;
import com.jelsat.adapters.Inboxmessagesadapter.MessageSectionV2;
import java.util.List;

public class PropertyListingSectionModel implements MessageSectionV2<Published> {
    private List<Published> listingItemArraylist;
    private String sectionLabel;

    public PropertyListingSectionModel(String str, List<Published> list) {
        this.sectionLabel = str;
        this.listingItemArraylist = list;
    }

    public List<Published> getChildItems() {
        return this.listingItemArraylist;
    }

    public String getSectionLabel() {
        return this.sectionLabel;
    }

    public void setSectionLabel(String str) {
        this.sectionLabel = str;
    }

    public List<Published> getListingItemArraylist() {
        return this.listingItemArraylist;
    }

    public void setListingItemArraylist(List<Published> list) {
        this.listingItemArraylist = list;
    }
}
