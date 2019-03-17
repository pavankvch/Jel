package com.businesslogic.socialsignup;

import com.businesslogic.framework.ErrorCodes;
import com.data.retrofit.APIService;
import com.data.socialsignup.SocialSignUpRequest;
import com.data.socialsignup.SocialSignUpResponse;
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

public class SocialSignUpPresenter {
    private static final String TAG = "SocialSignUpPresenter";
    private APIService apiService;
    private PrefUtils prefUtils;
    private Disposable socialSignUpDisposable;
    private ISocialSignUpView socialSignUpView;

    public SocialSignUpPresenter(ISocialSignUpView iSocialSignUpView, APIService aPIService, PrefUtils prefUtils) {
        this.socialSignUpView = iSocialSignUpView;
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void doSocialSignUp(String str, SocialSignUpRequest socialSignUpRequest) {
        this.socialSignUpView.showProgressDialog(str);
        this.socialSignUpDisposable = this.apiService.doSocialSignUp(socialSignUpRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SocialSignUpResponse>() {
            public void accept(@NonNull SocialSignUpResponse socialSignUpResponse) throws Exception {
                SocialSignUpPresenter.this.socialSignUpView.dismissProgress();
                SocialSignUpPresenter.this.socialSignUpView.socialSignUpSuccess(socialSignUpResponse, SocialSignUpPresenter.this.prefUtils);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SocialSignUpPresenter.this.socialSignUpView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    SocialSignUpPresenter.this.socialSignUpView.onError(SocialSignUpPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SocialSignUpPresenter.this.socialSignUpView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SocialSignUpPresenter.this.socialSignUpView.onError(null, 511);
                } else {
                    SocialSignUpPresenter.this.socialSignUpView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.socialSignUpDisposable != null && !this.socialSignUpDisposable.isDisposed()) {
            this.socialSignUpDisposable.dispose();
        }
    }
}
