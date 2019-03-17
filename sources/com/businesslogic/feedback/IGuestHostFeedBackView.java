package com.businesslogic.feedback;

import com.businesslogic.framework.IBaseView;
import com.data.feedback.FeedBackCombinedResponse;

public interface IGuestHostFeedBackView extends IBaseView {
    void onError(String str, int i);

    void onSuccess(FeedBackCombinedResponse feedBackCombinedResponse);
}
