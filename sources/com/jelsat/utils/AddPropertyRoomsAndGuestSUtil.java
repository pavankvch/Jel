package com.jelsat.utils;

public class AddPropertyRoomsAndGuestSUtil {
    public static final int BATHROOMS = 16;
    public static final int BED_ROOMS = 12;
    public static final int DOUBLE_BEDS = 14;
    public static final int MAX_GUESTS = 17;
    public static final int SINGLE_BEDS = 15;
    public static final int TENTS = 13;
    private int type;

    public int getType() {
        return this.type;
    }

    public AddPropertyRoomsAndGuestSUtil(int i) {
        this.type = i;
    }
}
