package com.businesslogic.inbox;

import com.businesslogic.framework.IBaseView;
import com.data.inbox.InboxMessageData;
import com.data.utils.APIError;
import java.util.List;

public interface IInboxMessagesView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(List<InboxMessageData> list);

    void showSwipeToRefresh(boolean z);
}
