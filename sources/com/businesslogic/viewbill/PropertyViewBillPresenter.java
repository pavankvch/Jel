package com.businesslogic.viewbill;

import android.util.Log;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.viewbill.PropertyViewBillRequest;
import com.data.viewbill.PropertyViewBillResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PropertyViewBillPresenter {
    private static final String TAG = "PropertyViewBillPresenter";
    private APIService apiService;
    private IPropertyViewBillView propertyViewBillView;
    private Disposable viewBillDisposable;

    public PropertyViewBillPresenter(IPropertyViewBillView iPropertyViewBillView, APIService aPIService) {
        this.propertyViewBillView = iPropertyViewBillView;
        this.apiService = aPIService;
    }

    public void getPropertyBill(String str, PropertyViewBillRequest propertyViewBillRequest) {
        this.propertyViewBillView.showProgressDialog(str);
        this.viewBillDisposable = this.apiService.getPropertyBill(propertyViewBillRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyViewBillResponse>() {
            public void accept(PropertyViewBillResponse propertyViewBillResponse) throws Exception {
                PropertyViewBillPresenter.this.propertyViewBillView.dismissProgress();
                PropertyViewBillPresenter.this.propertyViewBillView.onSuccess(propertyViewBillResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyViewBillPresenter.this.propertyViewBillView.dismissProgress();
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        Log.e(PropertyViewBillPresenter.TAG, aPIError.getSeken_errors());
                        PropertyViewBillPresenter.this.propertyViewBillView.onError(aPIError);
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.viewBillDisposable != null && !this.viewBillDisposable.isDisposed()) {
            this.viewBillDisposable.dispose();
        }
    }
}
