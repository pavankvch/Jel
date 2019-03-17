package com.businesslogic.socialsignin;

import com.businesslogic.framework.ErrorCodes;
import com.data.retrofit.APIService;
import com.data.signin.SaveFcmTokenRequest;
import com.data.signin.SignInResponse;
import com.data.socialsignin.SocialSignInRequest;
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

public class SocialSignInPresenter {
    private APIService apiService;
    private Disposable fcmTokenDisposable;
    private PrefUtils prefUtils;
    private Disposable socialSignInDisposable;
    private ISocialSignInView socialSignInView;

    public SocialSignInPresenter(ISocialSignInView iSocialSignInView, APIService aPIService, PrefUtils prefUtils) {
        this.socialSignInView = iSocialSignInView;
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void doSocialSignIn(SocialSignInRequest socialSignInRequest, String str) {
        this.socialSignInView.showProgressDialog(str);
        this.socialSignInDisposable = this.apiService.doSocialLogin(socialSignInRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SignInResponse>() {
            public void accept(@NonNull SignInResponse signInResponse) throws Exception {
                SocialSignInPresenter.this.socialSignInView.dismissProgress();
                SocialSignInPresenter.this.socialSignInView.socialSignInSuccess(signInResponse, SocialSignInPresenter.this.prefUtils);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SocialSignInPresenter.this.socialSignInView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    SocialSignInPresenter.this.socialSignInView.onError(SocialSignInPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SocialSignInPresenter.this.socialSignInView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SocialSignInPresenter.this.socialSignInView.onError(null, 511);
                } else {
                    SocialSignInPresenter.this.socialSignInView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    public void sendFcmTokenToServer(String str) {
        this.socialSignInView.showProgressDialog(str);
        str = new SaveFcmTokenRequest();
        str.setToken(this.prefUtils.getFcmToken());
        str.setType("android");
        this.fcmTokenDisposable = this.apiService.saveToken(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(@NonNull ResponseBody responseBody) throws Exception {
                SocialSignInPresenter.this.socialSignInView.dismissProgress();
                SocialSignInPresenter.this.prefUtils.setDeviceTokenUpdated(true);
                SocialSignInPresenter.this.socialSignInView.saveFCMTokenSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SocialSignInPresenter.this.socialSignInView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    SocialSignInPresenter.this.socialSignInView.onError(SocialSignInPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SocialSignInPresenter.this.socialSignInView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SocialSignInPresenter.this.socialSignInView.onError(null, 511);
                } else {
                    SocialSignInPresenter.this.socialSignInView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (!(this.socialSignInDisposable == null || this.socialSignInDisposable.isDisposed())) {
            this.socialSignInDisposable.dispose();
        }
        if (this.fcmTokenDisposable != null && !this.fcmTokenDisposable.isDisposed()) {
            this.fcmTokenDisposable.dispose();
        }
    }
}
