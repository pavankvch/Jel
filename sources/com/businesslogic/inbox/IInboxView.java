package com.businesslogic.inbox;

import com.businesslogic.framework.IBaseView;
import com.data.inbox.InboxMergeResponse;
import com.data.utils.APIError;

public interface IInboxView extends IBaseView {
    void onError(APIError aPIError, int i);

    void onSuccess(InboxMergeResponse inboxMergeResponse);

    void showSwipeToRefresh(boolean z);
}
