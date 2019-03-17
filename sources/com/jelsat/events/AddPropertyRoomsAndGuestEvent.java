package com.jelsat.events;

import com.data.utils.BottomSheetData;
import com.jelsat.utils.AddPropertyRoomsAndGuestSUtil;

public class AddPropertyRoomsAndGuestEvent {
    private BottomSheetData bottomSheetData;
    private AddPropertyRoomsAndGuestSUtil util;

    public AddPropertyRoomsAndGuestEvent(BottomSheetData bottomSheetData, AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil) {
        this.bottomSheetData = bottomSheetData;
        this.util = addPropertyRoomsAndGuestSUtil;
    }

    public BottomSheetData getBottomSheetData() {
        return this.bottomSheetData;
    }

    public AddPropertyRoomsAndGuestSUtil getUtil() {
        return this.util;
    }
}
