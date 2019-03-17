package com.businesslogic.propertydetail;

import com.businesslogic.framework.IBaseView;
import com.data.bookings.BookingCancelResponse;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.utils.APIError;

public interface IPropertyDetailView extends IBaseView {
    void onError(APIError aPIError, int i);

    void onSuccess(BookingCancelResponse bookingCancelResponse);

    void onSuccess(PropertyDetailResponse propertyDetailResponse);

    void showSwipeToRefresh(boolean z);
}
