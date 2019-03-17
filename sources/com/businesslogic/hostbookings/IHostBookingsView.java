package com.businesslogic.hostbookings;

import com.businesslogic.framework.IBaseView;
import com.data.hostbookings.HostBookingsResponse;
import com.data.utils.APIError;

public interface IHostBookingsView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(HostBookingsResponse hostBookingsResponse);

    void showSwipeToRefresh(boolean z);
}
