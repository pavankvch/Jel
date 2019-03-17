package com.businesslogic.changepassword;

import com.data.changepassword.ChangePasswordRequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ChangePasswordPresenter {
    private APIService apiService;
    private IChangePasswordView changePwdView;
    private Disposable changepasswordDisposable;

    public ChangePasswordPresenter(IChangePasswordView iChangePasswordView, APIService aPIService) {
        this.changePwdView = iChangePasswordView;
        this.apiService = aPIService;
    }

    public void changePassword(String str, ChangePasswordRequest changePasswordRequest) {
        this.changePwdView.showProgressDialog(str);
        this.changepasswordDisposable = this.apiService.changePassword(changePasswordRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                ChangePasswordPresenter.this.changePwdView.dismissProgress();
                ChangePasswordPresenter.this.changePwdView.onSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                ChangePasswordPresenter.this.changePwdView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    ChangePasswordPresenter.this.changePwdView.onError(th2);
                }
                th2 = null;
                ChangePasswordPresenter.this.changePwdView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.changepasswordDisposable != null && !this.changepasswordDisposable.isDisposed()) {
            this.changepasswordDisposable.dispose();
        }
    }
}
