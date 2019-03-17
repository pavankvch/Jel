package com.businesslogic.sdktoken;

import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.sdktoken.SDKTokenRequest;
import com.data.sdktoken.SDKTokenResponse;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class SDKTokenPresenter {
    private APIService apiService;
    private ISDKTokenView isdkTokenView;
    private Disposable sdkTokenDisposable;

    public SDKTokenPresenter(ISDKTokenView iSDKTokenView, APIService aPIService) {
        this.isdkTokenView = iSDKTokenView;
        this.apiService = aPIService;
    }

    public void getSdkToken(String str, SDKTokenRequest sDKTokenRequest) {
        this.isdkTokenView.showProgressDialog(str);
        this.sdkTokenDisposable = this.apiService.getPayfortSdkToken(sDKTokenRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SDKTokenResponse>() {
            public void accept(SDKTokenResponse sDKTokenResponse) throws Exception {
                SDKTokenPresenter.this.isdkTokenView.dismissProgress();
                SDKTokenPresenter.this.isdkTokenView.onSuccess(sDKTokenResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                SDKTokenPresenter.this.isdkTokenView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            SDKTokenPresenter.this.isdkTokenView.onError(aPIError2);
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            SDKTokenPresenter.this.isdkTokenView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        SDKTokenPresenter.this.isdkTokenView.onError(aPIError);
                    }
                }
                SDKTokenPresenter.this.isdkTokenView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.sdkTokenDisposable != null && !this.sdkTokenDisposable.isDisposed()) {
            this.sdkTokenDisposable.dispose();
        }
    }
}
