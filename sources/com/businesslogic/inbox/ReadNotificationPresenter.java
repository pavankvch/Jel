package com.businesslogic.inbox;

import com.data.inbox.ReadNotificationRequest;
import com.data.retrofit.APIService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ReadNotificationPresenter {
    private APIService apiService;
    private Disposable disposable;

    public ReadNotificationPresenter(APIService aPIService) {
        this.apiService = aPIService;
    }

    public void readNotification(ReadNotificationRequest readNotificationRequest) {
        this.disposable = this.apiService.readNotification(readNotificationRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
