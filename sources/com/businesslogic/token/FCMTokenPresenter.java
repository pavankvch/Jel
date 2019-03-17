package com.businesslogic.token;

import com.data.retrofit.APIService;
import com.data.signin.SaveFcmTokenRequest;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class FCMTokenPresenter {
    private APIService apiService;
    private Disposable disposable;
    private PrefUtils prefUtils;

    public FCMTokenPresenter(APIService aPIService, PrefUtils prefUtils) {
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void sendFCMTokenToServer() {
        SaveFcmTokenRequest saveFcmTokenRequest = new SaveFcmTokenRequest();
        saveFcmTokenRequest.setToken(this.prefUtils.getFcmToken());
        saveFcmTokenRequest.setType("android");
        this.disposable = this.apiService.sendFCMTokenToServer(saveFcmTokenRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                FCMTokenPresenter.this.prefUtils.setDeviceTokenUpdated(true);
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
