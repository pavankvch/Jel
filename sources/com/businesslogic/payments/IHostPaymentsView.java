package com.businesslogic.payments;

import com.businesslogic.framework.IBaseView;
import com.data.payments.HostPaymentsResponse;
import com.data.utils.APIError;

public interface IHostPaymentsView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(HostPaymentsResponse hostPaymentsResponse);
}
