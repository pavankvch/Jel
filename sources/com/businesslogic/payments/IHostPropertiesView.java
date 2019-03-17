package com.businesslogic.payments;

import com.businesslogic.framework.IBaseView;
import com.data.payments.HostProperty;
import com.data.utils.APIError;
import java.util.List;

public interface IHostPropertiesView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(List<HostProperty> list);

    void showSwipeToRefresh(boolean z);
}
