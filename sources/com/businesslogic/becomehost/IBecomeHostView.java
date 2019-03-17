package com.businesslogic.becomehost;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface IBecomeHostView extends IBaseView {
    void onError(APIError aPIError);

    void onHostSuccess();
}
