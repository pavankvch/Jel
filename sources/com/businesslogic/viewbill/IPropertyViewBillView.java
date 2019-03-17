package com.businesslogic.viewbill;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;
import com.data.viewbill.PropertyViewBillResponse;

public interface IPropertyViewBillView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(PropertyViewBillResponse propertyViewBillResponse);
}
