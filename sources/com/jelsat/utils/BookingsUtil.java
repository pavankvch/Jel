package com.jelsat.utils;

public class BookingsUtil {
    public static final int CURRENT_BOOKINGS = 1;
    public static final int PREVIOUS_BOOKINGS = 2;
    public static final int UPCOMING_BOOKINGS = 0;
    private int bookingType;

    public BookingsUtil(int i) {
        this.bookingType = i;
    }

    public int getBookingType() {
        return this.bookingType;
    }
}
