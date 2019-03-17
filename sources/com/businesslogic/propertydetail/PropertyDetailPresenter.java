package com.businesslogic.propertydetail;

import android.util.Log;
import com.businesslogic.framework.ErrorCodes;
import com.data.bookings.BookingCancelRequest;
import com.data.bookings.BookingCancelResponse;
import com.data.propertydetail.PropertyDetailRequest;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.retrofit.APIService;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class PropertyDetailPresenter {
    private APIService apiService;
    private Disposable propertyDetailDisposable;
    private IPropertyDetailView propertyDetailView;

    public PropertyDetailPresenter(IPropertyDetailView iPropertyDetailView, APIService aPIService) {
        this.propertyDetailView = iPropertyDetailView;
        this.apiService = aPIService;
    }

    public void getPropertyDetailData(String str, PropertyDetailRequest propertyDetailRequest, final boolean z) {
        if (z) {
            this.propertyDetailView.showProgressDialog(str);
        } else {
            this.propertyDetailView.showSwipeToRefresh(true);
        }
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            str = this.apiService.getOpenPropertyDetail(propertyDetailRequest);
        } else {
            Log.e("PropertyDetailPresenter", "PropertyDetailRequest class has called");
            str = this.apiService.getPropertyDetail(propertyDetailRequest);
        }
        this.propertyDetailDisposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyDetailResponse>() {
            public void accept(PropertyDetailResponse propertyDetailResponse) throws Exception {
                if (z) {
                    PropertyDetailPresenter.this.propertyDetailView.dismissProgress();
                } else {
                    PropertyDetailPresenter.this.propertyDetailView.showSwipeToRefresh(false);
                }
                PropertyDetailPresenter.this.propertyDetailView.onSuccess(propertyDetailResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    PropertyDetailPresenter.this.propertyDetailView.dismissProgress();
                } else {
                    PropertyDetailPresenter.this.propertyDetailView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(PropertyDetailPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, 511);
                } else {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    public void cancelBookingByGuest(String str, BookingCancelRequest bookingCancelRequest) {
        this.propertyDetailView.showProgressDialog(str);
        this.propertyDetailDisposable = this.apiService.cancelBookingByGuest(bookingCancelRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BookingCancelResponse>() {
            public void accept(BookingCancelResponse bookingCancelResponse) throws Exception {
                PropertyDetailPresenter.this.propertyDetailView.dismissProgress();
                PropertyDetailPresenter.this.propertyDetailView.onSuccess(bookingCancelResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyDetailPresenter.this.propertyDetailView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(PropertyDetailPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, 511);
                } else {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    public void cancelBookingByHost(String str, BookingCancelRequest bookingCancelRequest) {
        this.propertyDetailView.showProgressDialog(str);
        this.propertyDetailDisposable = this.apiService.cancelBookingByHost(bookingCancelRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BookingCancelResponse>() {
            public void accept(BookingCancelResponse bookingCancelResponse) throws Exception {
                PropertyDetailPresenter.this.propertyDetailView.dismissProgress();
                PropertyDetailPresenter.this.propertyDetailView.onSuccess(bookingCancelResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyDetailPresenter.this.propertyDetailView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(PropertyDetailPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, 511);
                } else {
                    PropertyDetailPresenter.this.propertyDetailView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.propertyDetailDisposable != null && !this.propertyDetailDisposable.isDisposed()) {
            this.propertyDetailDisposable.dispose();
        }
    }
}
