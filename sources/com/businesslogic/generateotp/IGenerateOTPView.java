package com.businesslogic.generateotp;

import com.businesslogic.framework.IBaseView;
import com.data.generateotp.UpdateMobileNumberRequest;
import com.data.utils.APIError;

public interface IGenerateOTPView extends IBaseView {
    void generateOTPSuccessResponse();

    void generateOTPerrorOccurred(APIError aPIError);

    void updateMobileNumberSuccess(UpdateMobileNumberRequest updateMobileNumberRequest);
}
