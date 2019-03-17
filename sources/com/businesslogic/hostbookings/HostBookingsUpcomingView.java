package com.businesslogic.hostbookings;

import com.businesslogic.framework.IBaseView;
import com.data.bookings.BookingCancelResponse;
import com.data.utils.APIError;

public interface HostBookingsUpcomingView extends IBaseView {
    void onAcceptSuccess(BookingCancelResponse bookingCancelResponse, int i);

    void onError(APIError aPIError);

    void onRejectSuccess(BookingCancelResponse bookingCancelResponse, int i);
}
