package com.businesslogic.sdktoken;

import com.businesslogic.framework.IBaseView;
import com.data.sdktoken.SDKTokenResponse;
import com.data.utils.APIError;

public interface ISDKTokenView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(SDKTokenResponse sDKTokenResponse);
}
