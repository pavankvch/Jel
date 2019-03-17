package com.businesslogic.messages;

import com.businesslogic.framework.IBaseView;
import com.data.messagehistory.MessagesData;
import com.data.utils.APIError;

public interface IMessagesView extends IBaseView {
    void onError(APIError aPIError);

    void onGetMessagesHistory(MessagesData messagesData);

    void onMessageSentSuccess(MessagesData messagesData);

    void onSuccess();

    void showSwipeToRefresh(boolean z);
}
