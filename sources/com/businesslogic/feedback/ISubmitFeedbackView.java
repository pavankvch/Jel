package com.businesslogic.feedback;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface ISubmitFeedbackView extends IBaseView {
    void onError(APIError aPIError);

    void onFeedbackSubmitSuccess();
}
