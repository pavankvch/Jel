package com.businesslogic.PublishProperty;

import android.util.Log;
import com.data.publishproperty.PublishPropertyRequest;
import com.data.publishproperty.PublishPropertyResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PublishPropertyPresenter {
    private static final String TAG = "PublishPropertyPresenter";
    private APIService apiService;
    private Disposable publishPropertyDisposable;
    private IPublishProperty publishPropertyView;

    public PublishPropertyPresenter(IPublishProperty iPublishProperty, APIService aPIService) {
        this.publishPropertyView = iPublishProperty;
        this.apiService = aPIService;
    }

    public void getPublishPropertyResult(String str, PublishPropertyRequest publishPropertyRequest) {
        this.publishPropertyView.showProgressDialog(str);
        this.publishPropertyDisposable = this.apiService.getPublishPropertyStaus(publishPropertyRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PublishPropertyResponse>() {
            public void accept(PublishPropertyResponse publishPropertyResponse) throws Exception {
                PublishPropertyPresenter.this.publishPropertyView.dismissProgress();
                PublishPropertyPresenter.this.publishPropertyView.onSuccessResponse(publishPropertyResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                PublishPropertyPresenter.this.publishPropertyView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(PublishPropertyPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            PublishPropertyPresenter.this.publishPropertyView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        PublishPropertyPresenter.this.publishPropertyView.onError(aPIError);
                    }
                }
                PublishPropertyPresenter.this.publishPropertyView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.publishPropertyDisposable != null && !this.publishPropertyDisposable.isDisposed()) {
            this.publishPropertyDisposable.dispose();
        }
    }
}
