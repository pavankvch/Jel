package com.jelsat.events;

import com.data.searchproperty.SearchProperty;
import java.util.Date;

public class PropertyDetailEvent {
    private String bookingId;
    private Date checkInDate;
    private Date checkoutDate;
    private boolean fromBookings;
    private String guestCount;
    private String guestId;
    private String locationName;
    private SearchProperty property;
    private boolean showMessage;

    public PropertyDetailEvent(SearchProperty searchProperty) {
        this.property = searchProperty;
    }

    public SearchProperty getProperty() {
        return this.property;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckoutDate() {
        return this.checkoutDate;
    }

    public String getGuestCount() {
        return this.guestCount;
    }

    public void setCheckInDate(Date date) {
        this.checkInDate = date;
    }

    public void setCheckoutDate(Date date) {
        this.checkoutDate = date;
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

    public String getBookingId() {
        return this.bookingId;
    }

    public void setBookingId(String str) {
        this.bookingId = str;
    }

    public String getGuestId() {
        return this.guestId;
    }

    public void setGuestId(String str) {
        this.guestId = str;
    }

    public boolean isFromBookings() {
        return this.fromBookings;
    }

    public void setFromBookings(boolean z) {
        this.fromBookings = z;
    }

    public boolean isShowMessage() {
        return this.showMessage;
    }

    public void setShowMessage(boolean z) {
        this.showMessage = z;
    }
}
