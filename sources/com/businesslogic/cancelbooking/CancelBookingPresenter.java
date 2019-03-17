package com.businesslogic.cancelbooking;

import com.businesslogic.framework.ErrorCodes;
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
import org.json.JSONObject;
import retrofit2.HttpException;

public class CancelBookingPresenter {
    private APIService apiService;
    private Disposable cancelBookingDisposable;
    private ICancelBookingView cancelBookingView;

    public CancelBookingPresenter(ICancelBookingView iCancelBookingView, APIService aPIService) {
        this.cancelBookingView = iCancelBookingView;
        this.apiService = aPIService;
    }

    public void cancelBooking(String str, final boolean z) {
        this.cancelBookingView.showProgressDialog(str);
        this.cancelBookingDisposable = this.apiService.abortBooking(new JSONObject()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                CancelBookingPresenter.this.cancelBookingView.dismissProgress();
                CancelBookingPresenter.this.cancelBookingView.onSuccess(z);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                CancelBookingPresenter.this.cancelBookingView.dismissProgress();
                if (th instanceof HttpException) {
                    CancelBookingPresenter.this.cancelBookingView.onCancelBookingError(CancelBookingPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    CancelBookingPresenter.this.cancelBookingView.onCancelBookingError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    CancelBookingPresenter.this.cancelBookingView.onCancelBookingError(null, 511);
                } else {
                    CancelBookingPresenter.this.cancelBookingView.onCancelBookingError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.cancelBookingDisposable != null && !this.cancelBookingDisposable.isDisposed()) {
            this.cancelBookingDisposable.dispose();
        }
    }
}
