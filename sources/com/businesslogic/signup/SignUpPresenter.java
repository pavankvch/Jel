package com.businesslogic.signup;

import com.businesslogic.framework.ErrorCodes;
import com.data.retrofit.APIService;
import com.data.signup.SignUpRequest;
import com.data.signup.SignUpResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
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

public class SignUpPresenter {
    private APIService apiService;
    private ISignUpView iSignUpView;
    private PrefUtils prefUtils;
    private Disposable signUpDisposable;

    public SignUpPresenter(ISignUpView iSignUpView, APIService aPIService, PrefUtils prefUtils) {
        this.iSignUpView = iSignUpView;
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void userSignUp(String str, final SignUpRequest signUpRequest) {
        this.iSignUpView.showProgressDialog(str);
        this.signUpDisposable = this.apiService.placeUserSignUp(signUpRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SignUpResponse>() {
            public void accept(@NonNull SignUpResponse signUpResponse) throws Exception {
                SignUpPresenter.this.iSignUpView.dismissProgress();
                SignUpPresenter.this.iSignUpView.goToOtpScreen(signUpRequest, SignUpPresenter.this.prefUtils, signUpResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SignUpPresenter.this.iSignUpView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    SignUpPresenter.this.iSignUpView.onError(SignUpPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SignUpPresenter.this.iSignUpView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SignUpPresenter.this.iSignUpView.onError(null, 511);
                } else {
                    SignUpPresenter.this.iSignUpView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.signUpDisposable != null && !this.signUpDisposable.isDisposed()) {
            this.signUpDisposable.dispose();
        }
    }
}
