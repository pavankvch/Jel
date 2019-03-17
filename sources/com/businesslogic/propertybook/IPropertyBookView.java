package com.businesslogic.propertybook;

import com.businesslogic.framework.IBaseView;
import com.data.propertybook.PropertyBookResponse;
import com.data.utils.APIError;

public interface IPropertyBookView extends IBaseView {
    void onPropertyBookError(APIError aPIError, int i);

    void onSuccess(PropertyBookResponse propertyBookResponse);
}
