package com.businesslogic.propertybook;

import com.businesslogic.framework.ErrorCodes;
import com.data.propertybook.PropertyBookResponse;
import com.data.retrofit.APIService;
import com.data.utils.APIError;
import com.data.viewbill.PropertyViewBillRequest;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class PropertyBookPresenter {
    private APIService apiService;
    private Disposable propertyBookDisposable;
    private IPropertyBookView propertyBookView;

    public PropertyBookPresenter(IPropertyBookView iPropertyBookView, APIService aPIService) {
        this.propertyBookView = iPropertyBookView;
        this.apiService = aPIService;
    }

    public void bookProperty(String str, PropertyViewBillRequest propertyViewBillRequest) {
        this.propertyBookView.showProgressDialog(str);
        this.propertyBookDisposable = this.apiService.getPropertyBookResponse(propertyViewBillRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyBookResponse>() {
            public void accept(PropertyBookResponse propertyBookResponse) throws Exception {
                PropertyBookPresenter.this.propertyBookView.dismissProgress();
                PropertyBookPresenter.this.propertyBookView.onSuccess(propertyBookResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyBookPresenter.this.propertyBookView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    PropertyBookPresenter.this.propertyBookView.onPropertyBookError(PropertyBookPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    PropertyBookPresenter.this.propertyBookView.onPropertyBookError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    PropertyBookPresenter.this.propertyBookView.onPropertyBookError(null, 511);
                } else {
                    PropertyBookPresenter.this.propertyBookView.onPropertyBookError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.propertyBookDisposable != null && !this.propertyBookDisposable.isDisposed()) {
            this.propertyBookDisposable.dispose();
        }
    }
}
