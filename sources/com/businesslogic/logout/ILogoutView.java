package com.businesslogic.logout;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface ILogoutView extends IBaseView {
    void errorOccurred(APIError aPIError);

    void logoutSuccess();
}
