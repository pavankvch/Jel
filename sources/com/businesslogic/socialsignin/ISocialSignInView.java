package com.businesslogic.socialsignin;

import com.businesslogic.framework.IBaseView;
import com.data.signin.SignInResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;

public interface ISocialSignInView extends IBaseView {
    void onError(APIError aPIError, int i);

    void saveFCMTokenSuccess();

    void socialSignInSuccess(SignInResponse signInResponse, PrefUtils prefUtils);
}
