package com.businesslogic.inbox;

import android.util.Log;
import com.data.inbox.InboxNotificationSectionModel;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.HttpException;

public class InboxNotificationsPresenter {
    private static final String TAG = "InboxNotificationsPresenter";
    private APIService apiService;
    private IInboxNotificationsView inboxNotificationsView;
    private Disposable messagesDisposable;

    public InboxNotificationsPresenter(IInboxNotificationsView iInboxNotificationsView, APIService aPIService) {
        this.inboxNotificationsView = iInboxNotificationsView;
        this.apiService = aPIService;
    }

    public void getGuestNotificationsHistory(String str, final boolean z) {
        if (z) {
            this.inboxNotificationsView.showProgressDialog(str);
        } else {
            this.inboxNotificationsView.showSwipeToRefresh(true);
        }
        this.messagesDisposable = this.apiService.getGuestNotificationsHistory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<InboxNotificationSectionModel>>() {
            public void accept(List<InboxNotificationSectionModel> list) throws Exception {
                if (z) {
                    InboxNotificationsPresenter.this.inboxNotificationsView.dismissProgress();
                } else {
                    InboxNotificationsPresenter.this.inboxNotificationsView.showSwipeToRefresh(false);
                }
                InboxNotificationsPresenter.this.inboxNotificationsView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    InboxNotificationsPresenter.this.inboxNotificationsView.dismissProgress();
                } else {
                    InboxNotificationsPresenter.this.inboxNotificationsView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(InboxNotificationsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            InboxNotificationsPresenter.this.inboxNotificationsView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        InboxNotificationsPresenter.this.inboxNotificationsView.onError(aPIError);
                    }
                }
                InboxNotificationsPresenter.this.inboxNotificationsView.onError(aPIError);
            }
        });
    }

    public void getHostNotificationsHistory(String str, final boolean z) {
        if (z) {
            this.inboxNotificationsView.showProgressDialog(str);
        } else {
            this.inboxNotificationsView.showSwipeToRefresh(true);
        }
        this.messagesDisposable = this.apiService.getHostNotificationsHistory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<InboxNotificationSectionModel>>() {
            public void accept(List<InboxNotificationSectionModel> list) throws Exception {
                if (z) {
                    InboxNotificationsPresenter.this.inboxNotificationsView.dismissProgress();
                } else {
                    InboxNotificationsPresenter.this.inboxNotificationsView.showSwipeToRefresh(false);
                }
                InboxNotificationsPresenter.this.inboxNotificationsView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    InboxNotificationsPresenter.this.inboxNotificationsView.dismissProgress();
                } else {
                    InboxNotificationsPresenter.this.inboxNotificationsView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(InboxNotificationsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            InboxNotificationsPresenter.this.inboxNotificationsView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        InboxNotificationsPresenter.this.inboxNotificationsView.onError(aPIError);
                    }
                }
                InboxNotificationsPresenter.this.inboxNotificationsView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.messagesDisposable != null && !this.messagesDisposable.isDisposed()) {
            this.messagesDisposable.dispose();
        }
    }
}
