package com.businesslogic.propertyfeedback;

import com.businesslogic.framework.IBaseView;
import com.data.propertyreviews.PropertyReviewsResponse;
import com.data.utils.APIError;

public interface IFeedbackView extends IBaseView {
    void onError(APIError aPIError);

    void onFeedbackSubmitSuccess(boolean z);

    void onReviewsGet(PropertyReviewsResponse propertyReviewsResponse);

    void showSwipeToRefresh(boolean z);
}
