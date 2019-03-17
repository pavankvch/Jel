package com.data.hostbookings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HostBookingsResponse {
    @SerializedName("current")
    @Expose
    private List<HostBookingProperty> currentBookings;
    @SerializedName("guest_inbox_count")
    @Expose
    private int guestInboxCount;
    @SerializedName("host_inbox_count")
    @Expose
    private int hostInboxCount;
    @SerializedName("previous")
    @Expose
    private List<HostBookingProperty> previousBookings;
    @SerializedName("upcomming")
    @Expose
    private List<HostBookingProperty> upcomingBookings;

    public List<HostBookingProperty> getPreviousBookings() {
        return this.previousBookings;
    }

    public List<HostBookingProperty> getUpcomingBookings() {
        return this.upcomingBookings;
    }

    public List<HostBookingProperty> getCurrentBookings() {
        return this.currentBookings;
    }

    public int getGuestInboxCount() {
        return this.guestInboxCount;
    }

    public int getHostInboxCount() {
        return this.hostInboxCount;
    }
}
