package com.businesslogic.validateotp;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface IValidateOTPView extends IBaseView {
    void onError(APIError aPIError, int i);

    void validationOTPSuccessResponse();
}
