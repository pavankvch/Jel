package com.businesslogic.signup;

import com.businesslogic.framework.IBaseView;
import com.data.signup.SignUpRequest;
import com.data.signup.SignUpResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;

public interface ISignUpView extends IBaseView {
    void goToOtpScreen(SignUpRequest signUpRequest, PrefUtils prefUtils, SignUpResponse signUpResponse);

    void onError(APIError aPIError, int i);
}
