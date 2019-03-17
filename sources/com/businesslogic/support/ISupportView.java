package com.businesslogic.support;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface ISupportView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess();
}
