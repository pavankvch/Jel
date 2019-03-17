package com.businesslogic.messages;

import com.data.inbox.MessageHistoryRequest;
import com.data.inbox.SendMessageRequest;
import com.data.inbox.SendMessageRequestHost;
import com.data.messagehistory.MessagesData;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class MessagesPresenter {
    private APIService apiService;
    private Disposable messagesDisposable;
    private IMessagesView messagesView;

    public MessagesPresenter(IMessagesView iMessagesView, APIService aPIService) {
        this.messagesView = iMessagesView;
        this.apiService = aPIService;
    }

    public void sendMessage(SendMessageRequest sendMessageRequest) {
        this.messagesView.showLoading();
        this.messagesDisposable = this.apiService.sendMessage(sendMessageRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                MessagesPresenter.this.messagesView.dismissProgress();
                MessagesPresenter.this.messagesView.onSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                MessagesPresenter.this.messagesView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    MessagesPresenter.this.messagesView.onError(th2);
                }
                th2 = null;
                MessagesPresenter.this.messagesView.onError(th2);
            }
        });
    }

    public void createMessage(SendMessageRequest sendMessageRequest) {
        this.messagesView.showLoading();
        this.messagesDisposable = this.apiService.createMessage(sendMessageRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MessagesData>() {
            public void accept(MessagesData messagesData) throws Exception {
                MessagesPresenter.this.messagesView.dismissProgress();
                MessagesPresenter.this.messagesView.onMessageSentSuccess(messagesData);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                MessagesPresenter.this.messagesView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    MessagesPresenter.this.messagesView.onError(th2);
                }
                th2 = null;
                MessagesPresenter.this.messagesView.onError(th2);
            }
        });
    }

    public void createHostMessage(SendMessageRequestHost sendMessageRequestHost) {
        this.messagesView.showLoading();
        this.messagesDisposable = this.apiService.createHostMessage(sendMessageRequestHost).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                MessagesPresenter.this.messagesView.dismissProgress();
                MessagesPresenter.this.messagesView.onSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                MessagesPresenter.this.messagesView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    MessagesPresenter.this.messagesView.onError(th2);
                }
                th2 = null;
                MessagesPresenter.this.messagesView.onError(th2);
            }
        });
    }

    public void guestMessageHistory(MessageHistoryRequest messageHistoryRequest, final boolean z) {
        if (z) {
            this.messagesView.showLoading();
        } else {
            this.messagesView.showSwipeToRefresh(true);
        }
        this.messagesDisposable = this.apiService.getMessageHistory(messageHistoryRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MessagesData>() {
            public void accept(MessagesData messagesData) throws Exception {
                if (z) {
                    MessagesPresenter.this.messagesView.dismissProgress();
                } else {
                    MessagesPresenter.this.messagesView.showSwipeToRefresh(false);
                }
                MessagesPresenter.this.messagesView.onGetMessagesHistory(messagesData);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    MessagesPresenter.this.messagesView.dismissProgress();
                } else {
                    MessagesPresenter.this.messagesView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    MessagesPresenter.this.messagesView.onError(th2);
                }
                th2 = null;
                MessagesPresenter.this.messagesView.onError(th2);
            }
        });
    }

    public void hostMessageHistory(MessageHistoryRequest messageHistoryRequest, final boolean z) {
        if (z) {
            this.messagesView.showLoading();
        } else {
            this.messagesView.showSwipeToRefresh(true);
        }
        this.messagesDisposable = this.apiService.getHostMessageHistory(messageHistoryRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MessagesData>() {
            public void accept(MessagesData messagesData) throws Exception {
                if (z) {
                    MessagesPresenter.this.messagesView.dismissProgress();
                } else {
                    MessagesPresenter.this.messagesView.showSwipeToRefresh(false);
                }
                MessagesPresenter.this.messagesView.onGetMessagesHistory(messagesData);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    MessagesPresenter.this.messagesView.dismissProgress();
                } else {
                    MessagesPresenter.this.messagesView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    MessagesPresenter.this.messagesView.onError(th2);
                }
                th2 = null;
                MessagesPresenter.this.messagesView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.messagesDisposable != null && !this.messagesDisposable.isDisposed()) {
            this.messagesDisposable.dispose();
        }
    }
}
