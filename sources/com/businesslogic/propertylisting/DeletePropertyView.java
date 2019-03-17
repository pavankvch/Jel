package com.businesslogic.propertylisting;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface DeletePropertyView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(int i, int i2);
}
