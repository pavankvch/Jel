package com.businesslogic.wallet;

import com.businesslogic.framework.IBaseView;
import com.data.paymenthistory.PaymentHistoryResponse;
import com.data.utils.APIError;

public interface IPaymentHistoryView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(PaymentHistoryResponse paymentHistoryResponse);

    void showSwipeToRefresh(boolean z);
}
