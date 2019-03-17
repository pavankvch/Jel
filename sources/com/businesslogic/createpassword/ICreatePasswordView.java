package com.businesslogic.createpassword;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface ICreatePasswordView extends IBaseView {
    void errorResponse(APIError aPIError);

    void successResponse();
}
