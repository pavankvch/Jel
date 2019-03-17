package com.businesslogic.AddYourProperty;

import android.util.Log;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class AddYourPropertyPresenter {
    private static final String TAG = "AddYourPropertyPresenter";
    private Disposable addPropertyDisposable;
    private IAddYourPropertyView addYourPropertyView;
    private APIService apiService;

    public AddYourPropertyPresenter(IAddYourPropertyView iAddYourPropertyView, APIService aPIService) {
        this.addYourPropertyView = iAddYourPropertyView;
        this.apiService = aPIService;
    }

    public void getAddPropertyResult(String str, AddPropertyRequest addPropertyRequest) {
        this.addYourPropertyView.showProgressDialog(str);
        this.addPropertyDisposable = this.apiService.getAllPropertyDeatils(addPropertyRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<AddPropertyResponse>() {
            public void accept(AddPropertyResponse addPropertyResponse) throws Exception {
                AddYourPropertyPresenter.this.addYourPropertyView.dismissProgress();
                AddYourPropertyPresenter.this.addYourPropertyView.onSuccessResponse(addPropertyResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                AddYourPropertyPresenter.this.addYourPropertyView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(AddYourPropertyPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            AddYourPropertyPresenter.this.addYourPropertyView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        AddYourPropertyPresenter.this.addYourPropertyView.onError(aPIError);
                    }
                }
                AddYourPropertyPresenter.this.addYourPropertyView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.addPropertyDisposable != null && !this.addPropertyDisposable.isDisposed()) {
            this.addPropertyDisposable.dispose();
        }
    }
}
