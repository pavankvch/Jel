package com.businesslogic.signin;

import com.businesslogic.framework.ErrorCodes;
import com.data.retrofit.APIService;
import com.data.signin.SaveFcmTokenRequest;
import com.data.signin.SignInRequest;
import com.data.signin.SignInResponse;
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

public class SignInPresenter {
    private APIService apiService;
    private Disposable fcmTokenDisposable;
    private PrefUtils prefUtils;
    private Disposable signInDisposable;
    private ISignInView signInView;

    public SignInPresenter(ISignInView iSignInView, APIService aPIService, PrefUtils prefUtils) {
        this.signInView = iSignInView;
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void userSignIn(String str, SignInRequest signInRequest) {
        this.signInView.showProgressDialog(str);
        this.signInDisposable = this.apiService.placeUserLogin(signInRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SignInResponse>() {
            public void accept(@NonNull SignInResponse signInResponse) throws Exception {
                SignInPresenter.this.signInView.dismissProgress();
                SignInPresenter.this.signInView.goToNextScreen(signInResponse, SignInPresenter.this.prefUtils);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SignInPresenter.this.signInView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    SignInPresenter.this.signInView.onError(SignInPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SignInPresenter.this.signInView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SignInPresenter.this.signInView.onError(null, 511);
                } else {
                    SignInPresenter.this.signInView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    public void sendFcmTokenToServer(String str) {
        this.signInView.showProgressDialog(str);
        str = new SaveFcmTokenRequest();
        str.setToken(this.prefUtils.getFcmToken());
        str.setType("android");
        this.fcmTokenDisposable = this.apiService.saveToken(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(@NonNull ResponseBody responseBody) throws Exception {
                SignInPresenter.this.signInView.dismissProgress();
                SignInPresenter.this.signInView.saveTokenSuccess(SignInPresenter.this.prefUtils);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SignInPresenter.this.signInView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    SignInPresenter.this.signInView.onError(SignInPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SignInPresenter.this.signInView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SignInPresenter.this.signInView.onError(null, 511);
                } else {
                    SignInPresenter.this.signInView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (!(this.signInDisposable == null || this.signInDisposable.isDisposed())) {
            this.signInDisposable.dispose();
        }
        if (this.fcmTokenDisposable != null && !this.fcmTokenDisposable.isDisposed()) {
            this.fcmTokenDisposable.dispose();
        }
    }
}
