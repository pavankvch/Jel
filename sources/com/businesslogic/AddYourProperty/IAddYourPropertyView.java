package com.businesslogic.AddYourProperty;

import com.businesslogic.framework.IBaseView;
import com.data.addproperty.AddPropertyResponse;
import com.data.utils.APIError;

public interface IAddYourPropertyView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccessResponse(AddPropertyResponse addPropertyResponse);
}
