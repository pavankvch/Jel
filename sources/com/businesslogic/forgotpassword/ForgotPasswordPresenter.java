package com.businesslogic.forgotpassword;

import com.data.generateotp.GenerateOTPRequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ForgotPasswordPresenter {
    private static final String TAG = "ForgotPasswordPresenter";
    private APIService apiService;
    private Disposable forgotPasswordDisposable;
    private IForgotPasswordView forgotPasswordView;

    public ForgotPasswordPresenter(IForgotPasswordView iForgotPasswordView, APIService aPIService) {
        this.forgotPasswordView = iForgotPasswordView;
        this.apiService = aPIService;
    }

    public void doForgotPassword(String str, GenerateOTPRequest generateOTPRequest) {
        this.forgotPasswordView.showProgressDialog(str);
        this.forgotPasswordDisposable = this.apiService.doForgotPassword(generateOTPRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(@NonNull ResponseBody responseBody) throws Exception {
                ForgotPasswordPresenter.this.forgotPasswordView.dismissProgress();
                ForgotPasswordPresenter.this.forgotPasswordView.responseSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ForgotPasswordPresenter.this.forgotPasswordView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    ForgotPasswordPresenter.this.forgotPasswordView.forgotPasswordError(th2);
                }
                th2 = null;
                ForgotPasswordPresenter.this.forgotPasswordView.forgotPasswordError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.forgotPasswordDisposable != null && !this.forgotPasswordDisposable.isDisposed()) {
            this.forgotPasswordDisposable.dispose();
        }
    }
}
