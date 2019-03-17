package com.businesslogic.inbox;

import android.util.Log;
import com.data.inbox.InboxMessageData;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.HttpException;

public class InboxMessagesPresenter {
    private static final String TAG = "InboxMessagesPresenter";
    private APIService apiService;
    private IInboxMessagesView inboxMessagesView;
    private Disposable messagesDisposable;

    public InboxMessagesPresenter(IInboxMessagesView iInboxMessagesView, APIService aPIService) {
        this.inboxMessagesView = iInboxMessagesView;
        this.apiService = aPIService;
    }

    public void conversationsHistory(final boolean z) {
        if (!z) {
            this.inboxMessagesView.showSwipeToRefresh(true);
        }
        this.messagesDisposable = this.apiService.getConversationsHistory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<InboxMessageData>>() {
            public void accept(List<InboxMessageData> list) throws Exception {
                if (z) {
                    InboxMessagesPresenter.this.inboxMessagesView.dismissProgress();
                } else {
                    InboxMessagesPresenter.this.inboxMessagesView.showSwipeToRefresh(false);
                }
                InboxMessagesPresenter.this.inboxMessagesView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    InboxMessagesPresenter.this.inboxMessagesView.dismissProgress();
                } else {
                    InboxMessagesPresenter.this.inboxMessagesView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(InboxMessagesPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            InboxMessagesPresenter.this.inboxMessagesView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        InboxMessagesPresenter.this.inboxMessagesView.onError(aPIError);
                    }
                }
                InboxMessagesPresenter.this.inboxMessagesView.onError(aPIError);
            }
        });
    }

    public void hostConversationsHistory(final boolean z) {
        if (!z) {
            this.inboxMessagesView.showSwipeToRefresh(true);
        }
        this.messagesDisposable = this.apiService.getHostConversationsHistory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<InboxMessageData>>() {
            public void accept(List<InboxMessageData> list) throws Exception {
                if (z) {
                    InboxMessagesPresenter.this.inboxMessagesView.dismissProgress();
                } else {
                    InboxMessagesPresenter.this.inboxMessagesView.showSwipeToRefresh(false);
                }
                InboxMessagesPresenter.this.inboxMessagesView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    InboxMessagesPresenter.this.inboxMessagesView.dismissProgress();
                } else {
                    InboxMessagesPresenter.this.inboxMessagesView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(InboxMessagesPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            InboxMessagesPresenter.this.inboxMessagesView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        InboxMessagesPresenter.this.inboxMessagesView.onError(aPIError);
                    }
                }
                InboxMessagesPresenter.this.inboxMessagesView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.messagesDisposable != null && !this.messagesDisposable.isDisposed()) {
            this.messagesDisposable.dispose();
        }
    }
}
