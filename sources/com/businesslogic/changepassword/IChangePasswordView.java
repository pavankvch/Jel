package com.businesslogic.changepassword;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface IChangePasswordView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess();
}
