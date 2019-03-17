package com.businesslogic.propertypayment;

import com.businesslogic.framework.ErrorCodes;
import com.data.propertypayment.PropertyPaymentRequest;
import com.data.propertypayment.PropertyPaymentResponse;
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

public class PropertyPaymentPresenter {
    private APIService apiService;
    private Disposable propertyPaymentDisposable;
    private IPropertyPaymentView propertyPaymentView;

    public PropertyPaymentPresenter(IPropertyPaymentView iPropertyPaymentView, APIService aPIService) {
        this.propertyPaymentView = iPropertyPaymentView;
        this.apiService = aPIService;
    }

    public void doPropertyPayment(String str, PropertyPaymentRequest propertyPaymentRequest) {
        this.propertyPaymentView.showProgressDialog(str);
        this.propertyPaymentDisposable = this.apiService.doPropertyPayment(propertyPaymentRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyPaymentResponse>() {
            public void accept(PropertyPaymentResponse propertyPaymentResponse) throws Exception {
                PropertyPaymentPresenter.this.propertyPaymentView.dismissProgress();
                PropertyPaymentPresenter.this.propertyPaymentView.onSuccess(propertyPaymentResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyPaymentPresenter.this.propertyPaymentView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    PropertyPaymentPresenter.this.propertyPaymentView.onError(PropertyPaymentPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    PropertyPaymentPresenter.this.propertyPaymentView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    PropertyPaymentPresenter.this.propertyPaymentView.onError(null, 511);
                } else {
                    PropertyPaymentPresenter.this.propertyPaymentView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.propertyPaymentDisposable != null && !this.propertyPaymentDisposable.isDisposed()) {
            this.propertyPaymentDisposable.dispose();
        }
    }
}
