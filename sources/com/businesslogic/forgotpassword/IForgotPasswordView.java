package com.businesslogic.forgotpassword;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface IForgotPasswordView extends IBaseView {
    void forgotPasswordError(APIError aPIError);

    void responseSuccess();
}
