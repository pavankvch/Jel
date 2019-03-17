package com.businesslogic.propertypricing;

import android.util.Log;
import com.data.propertypricing.PropertyPricingRequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class PropertyPricingPresenter {
    private static final String TAG = "PropertyPricingPresenter";
    private APIService apiService;
    private Disposable disposable;
    private IPropertyPricing iPropertyPricing;

    public PropertyPricingPresenter(IPropertyPricing iPropertyPricing, APIService aPIService) {
        this.iPropertyPricing = iPropertyPricing;
        this.apiService = aPIService;
    }

    public void getPropertyPricingData(String str, PropertyPricingRequest propertyPricingRequest) {
        this.iPropertyPricing.showProgressDialog(str);
        this.disposable = this.apiService.getPropertyPricing(propertyPricingRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                PropertyPricingPresenter.this.iPropertyPricing.dismissProgress();
                PropertyPricingPresenter.this.iPropertyPricing.onSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                PropertyPricingPresenter.this.iPropertyPricing.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(PropertyPricingPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            PropertyPricingPresenter.this.iPropertyPricing.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        PropertyPricingPresenter.this.iPropertyPricing.onError(aPIError);
                    }
                }
                PropertyPricingPresenter.this.iPropertyPricing.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
