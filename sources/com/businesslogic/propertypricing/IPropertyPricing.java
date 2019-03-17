package com.businesslogic.propertypricing;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface IPropertyPricing extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess();
}
