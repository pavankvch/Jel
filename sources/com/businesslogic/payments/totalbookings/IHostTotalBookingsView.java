package com.businesslogic.payments.totalbookings;

import com.businesslogic.framework.IBaseView;
import com.data.payments.totalbookings.HostBookingSectionModel;
import com.data.utils.APIError;
import java.util.List;

public interface IHostTotalBookingsView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(List<HostBookingSectionModel> list);

    void showSwipeToRefresh(boolean z);
}
