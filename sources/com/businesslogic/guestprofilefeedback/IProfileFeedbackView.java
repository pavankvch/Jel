package com.businesslogic.guestprofilefeedback;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface IProfileFeedbackView extends IBaseView {
    void onError(APIError aPIError);

    void onFeedbackSubmitSuccess();

    void showSwipeToRefresh(boolean z);
}
