package com.businesslogic.validateotp;

import com.businesslogic.framework.ErrorCodes;
import com.data.retrofit.APIService;
import com.data.utils.APIError;
import com.data.validateotp.ValidateOTPRequest;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ValidateOTPPresenter {
    private static final String TAG = "ValidateOTPPresenter";
    private APIService apiService;
    private Disposable validateOTPDisposable;
    private IValidateOTPView validateOTPView;

    public ValidateOTPPresenter(IValidateOTPView iValidateOTPView, APIService aPIService) {
        this.validateOTPView = iValidateOTPView;
        this.apiService = aPIService;
    }

    public void validateOTP(String str, ValidateOTPRequest validateOTPRequest, boolean z) {
        this.validateOTPView.showProgressDialog(str);
        if (z) {
            str = this.apiService.doForgotValidateOTP(validateOTPRequest);
        } else {
            str = this.apiService.validateOTP(validateOTPRequest);
        }
        if (str != null) {
            this.validateOTPDisposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
                public void accept(@NonNull ResponseBody responseBody) throws Exception {
                    ValidateOTPPresenter.this.validateOTPView.dismissProgress();
                    ValidateOTPPresenter.this.validateOTPView.validationOTPSuccessResponse();
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                    ValidateOTPPresenter.this.validateOTPView.dismissProgress();
                    th.printStackTrace();
                    if (th instanceof HttpException) {
                        ValidateOTPPresenter.this.validateOTPView.onError(ValidateOTPPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                    } else if (th instanceof SocketTimeoutException) {
                        ValidateOTPPresenter.this.validateOTPView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                    } else if ((th instanceof IOException) != null) {
                        ValidateOTPPresenter.this.validateOTPView.onError(null, 511);
                    } else {
                        ValidateOTPPresenter.this.validateOTPView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                    }
                }
            });
        }
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
        if (this.validateOTPDisposable != null && !this.validateOTPDisposable.isDisposed()) {
            this.validateOTPDisposable.dispose();
        }
    }
}
