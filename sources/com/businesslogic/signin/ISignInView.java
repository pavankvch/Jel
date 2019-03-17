package com.businesslogic.signin;

import com.businesslogic.framework.IBaseView;
import com.data.signin.SignInResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;

public interface ISignInView extends IBaseView {
    void goToNextScreen(SignInResponse signInResponse, PrefUtils prefUtils);

    void onError(APIError aPIError, int i);

    void saveTokenSuccess(PrefUtils prefUtils);
}
