package com.businesslogic.bankdetails;

import com.businesslogic.framework.IBaseView;
import com.data.bankdetails.BankDetailsResponse;
import com.data.utils.APIError;

public interface IBankDetailsView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(BankDetailsResponse bankDetailsResponse, boolean z);
}
