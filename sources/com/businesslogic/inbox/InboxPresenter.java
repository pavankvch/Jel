package com.businesslogic.inbox;

import com.businesslogic.framework.ErrorCodes;
import com.data.inbox.InboxMergeResponse;
import com.data.retrofit.APIService;
import com.data.utils.APIError;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class InboxPresenter {
    private APIService apiService;
    private Disposable disposable;
    private IInboxView iInboxView;

    public InboxPresenter(IInboxView iInboxView, APIService aPIService) {
        this.iInboxView = iInboxView;
        this.apiService = aPIService;
    }

    public void getGuestInboxData(String str, final boolean z) {
        if (z) {
            this.iInboxView.showProgressDialog(str);
        } else {
            this.iInboxView.showSwipeToRefresh(true);
        }
        this.disposable = this.apiService.getGuestInboxResponse().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<InboxMergeResponse>() {
            public void accept(InboxMergeResponse inboxMergeResponse) throws Exception {
                if (z) {
                    InboxPresenter.this.iInboxView.dismissProgress();
                } else {
                    InboxPresenter.this.iInboxView.showSwipeToRefresh(false);
                }
                InboxPresenter.this.iInboxView.onSuccess(inboxMergeResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    InboxPresenter.this.iInboxView.dismissProgress();
                } else {
                    InboxPresenter.this.iInboxView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    InboxPresenter.this.iInboxView.onError(InboxPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    InboxPresenter.this.iInboxView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    InboxPresenter.this.iInboxView.onError(null, 511);
                } else {
                    InboxPresenter.this.iInboxView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    public void getHostInboxData(String str, final boolean z) {
        if (z) {
            this.iInboxView.showProgressDialog(str);
        } else {
            this.iInboxView.showSwipeToRefresh(true);
        }
        this.disposable = this.apiService.getHostInboxResponse().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<InboxMergeResponse>() {
            public void accept(InboxMergeResponse inboxMergeResponse) throws Exception {
                if (z) {
                    InboxPresenter.this.iInboxView.dismissProgress();
                } else {
                    InboxPresenter.this.iInboxView.showSwipeToRefresh(false);
                }
                InboxPresenter.this.iInboxView.onSuccess(inboxMergeResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    InboxPresenter.this.iInboxView.dismissProgress();
                } else {
                    InboxPresenter.this.iInboxView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    InboxPresenter.this.iInboxView.onError(InboxPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    InboxPresenter.this.iInboxView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    InboxPresenter.this.iInboxView.onError(null, 511);
                } else {
                    InboxPresenter.this.iInboxView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    private APIError getApiError(ResponseBody responseBody) {
        try {
            return (APIError) new Gson().fromJson(responseBody.string(), APIError.class);
        } catch (ResponseBody responseBody2) {
            responseBody2.printStackTrace();
            APIError aPIError = new APIError();
            aPIError.setSeken_errors(responseBody2.getMessage());
            return aPIError;
        }
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
