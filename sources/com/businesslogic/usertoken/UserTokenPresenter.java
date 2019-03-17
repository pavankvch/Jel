package com.businesslogic.usertoken;

import com.data.retrofit.APIService;
import com.data.sdktoken.SDKTokenResponse;
import com.data.utils.ApiCallStatus;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.greenrobot.eventbus.EventBus;

public class UserTokenPresenter {
    private APIService apiService;
    private Disposable disposable;
    private PrefUtils prefUtils;

    public UserTokenPresenter(APIService aPIService, PrefUtils prefUtils) {
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void getUserToken() {
        this.disposable = this.apiService.getUserToken().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SDKTokenResponse>() {
            public void accept(SDKTokenResponse sDKTokenResponse) throws Exception {
                UserTokenPresenter.this.prefUtils.saveUserToken(sDKTokenResponse.getToken());
                EventBus.getDefault().post(new ApiCallStatus(true));
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                EventBus.getDefault().post(new ApiCallStatus(false));
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
