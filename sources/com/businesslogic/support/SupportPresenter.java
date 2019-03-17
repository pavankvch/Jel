package com.businesslogic.support;

import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.support.SupportRequest;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class SupportPresenter {
    private static final String TAG = "SupportPresenter";
    private APIService apiService;
    private Disposable supportDisposable;
    private ISupportView supportView;

    public SupportPresenter(ISupportView iSupportView, APIService aPIService) {
        this.supportView = iSupportView;
        this.apiService = aPIService;
    }

    public void support(String str, SupportRequest supportRequest) {
        this.supportView.showProgressDialog(str);
        this.supportDisposable = this.apiService.support(supportRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                SupportPresenter.this.supportView.dismissProgress();
                SupportPresenter.this.supportView.onSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                SupportPresenter.this.supportView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            SupportPresenter.this.supportView.onError(aPIError2);
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            SupportPresenter.this.supportView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        SupportPresenter.this.supportView.onError(aPIError);
                    }
                }
                SupportPresenter.this.supportView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.supportDisposable != null && !this.supportDisposable.isDisposed()) {
            this.supportDisposable.dispose();
        }
    }
}
