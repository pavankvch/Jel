package com.businesslogic.propertypayment;

import com.businesslogic.framework.IBaseView;
import com.data.propertypayment.PropertyPaymentResponse;
import com.data.utils.APIError;

public interface IPropertyPaymentView extends IBaseView {
    void onError(APIError aPIError, int i);

    void onSuccess(PropertyPaymentResponse propertyPaymentResponse);
}
