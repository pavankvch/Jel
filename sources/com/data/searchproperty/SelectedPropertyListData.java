package com.data.searchproperty;

import java.util.Collection;
import java.util.Date;

public class SelectedPropertyListData {
    private String guestCount;
    private boolean isSaved;
    private String locationName;
    private Collection<SearchProperty> propertyList;
    private Date selectedCheckInDate;
    private Date selectedCheckoutDate;

    public Collection<SearchProperty> getPropertyList() {
        return this.propertyList;
    }

    public void setPropertyList(Collection<SearchProperty> collection) {
        this.propertyList = collection;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public void setSaved(boolean z) {
        this.isSaved = z;
    }

    public Date getSelectedCheckInDate() {
        return this.selectedCheckInDate;
    }

    public void setSelectedCheckInDate(Date date) {
        this.selectedCheckInDate = date;
    }

    public Date getSelectedCheckoutDate() {
        return this.selectedCheckoutDate;
    }

    public void setSelectedCheckoutDate(Date date) {
        this.selectedCheckoutDate = date;
    }

    public String getGuestCount() {
        return this.guestCount;
    }

    public void setGuestCount(String str) {
        this.guestCount = str;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String str) {
        this.locationName = str;
    }
}
