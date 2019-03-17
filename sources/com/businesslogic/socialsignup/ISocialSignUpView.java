package com.businesslogic.socialsignup;

import com.businesslogic.framework.IBaseView;
import com.data.socialsignup.SocialSignUpResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;

public interface ISocialSignUpView extends IBaseView {
    void onError(APIError aPIError, int i);

    void socialSignUpSuccess(SocialSignUpResponse socialSignUpResponse, PrefUtils prefUtils);
}
