package com.businesslogic.bookings;

import com.businesslogic.framework.IBaseView;
import com.data.bookings.BookingsResponse;
import com.data.utils.APIError;

public interface BookingsView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(BookingsResponse bookingsResponse);

    void showSwipeToRefresh(boolean z);
}
