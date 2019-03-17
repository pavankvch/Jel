package com.businesslogic.cancelbooking;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface ICancelBookingView extends IBaseView {
    void onCancelBookingError(APIError aPIError, int i);

    void onSuccess(boolean z);
}
