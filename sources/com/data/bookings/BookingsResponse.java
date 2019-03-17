package com.data.bookings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BookingsResponse {
    @SerializedName("current")
    @Expose
    private List<BookingProperty> currentBookings;
    @SerializedName("previous")
    @Expose
    private List<BookingProperty> previousBookings;
    @SerializedName("upcomming")
    @Expose
    private List<BookingProperty> upcomingBookings;

    public List<BookingProperty> getPreviousBookings() {
        return this.previousBookings;
    }

    public List<BookingProperty> getUpcomingBookings() {
        return this.upcomingBookings;
    }

    public List<BookingProperty> getCurrentBookings() {
        return this.currentBookings;
    }
}
