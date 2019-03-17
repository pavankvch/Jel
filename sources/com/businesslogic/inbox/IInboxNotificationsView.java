package com.businesslogic.inbox;

import com.businesslogic.framework.IBaseView;
import com.data.inbox.InboxNotificationSectionModel;
import com.data.utils.APIError;
import java.util.List;

public interface IInboxNotificationsView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(List<InboxNotificationSectionModel> list);

    void showSwipeToRefresh(boolean z);
}
