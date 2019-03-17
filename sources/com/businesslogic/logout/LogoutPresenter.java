package com.businesslogic.logout;

import android.util.Log;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.signin.SaveFcmTokenRequest;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class LogoutPresenter {
    private static final String TAG = "LogoutPresenter";
    private APIService apiService;
    private Disposable logoutDisposable;
    private ILogoutView logoutView;
    private PrefUtils prefUtils;

    public LogoutPresenter(ILogoutView iLogoutView, APIService aPIService, PrefUtils prefUtils) {
        this.logoutView = iLogoutView;
        this.apiService = aPIService;
        this.prefUtils = prefUtils;
    }

    public void logoutFromApp(String str) {
        str = new SaveFcmTokenRequest();
        str.setToken(this.prefUtils.getFcmToken());
        this.logoutDisposable = this.apiService.logout(str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(@NonNull ResponseBody responseBody) throws Exception {
                LogoutPresenter.this.logoutView.logoutSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                Exception e;
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(LogoutPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            LogoutPresenter.this.logoutView.errorOccurred(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        LogoutPresenter.this.logoutView.errorOccurred(aPIError);
                    }
                }
                LogoutPresenter.this.logoutView.errorOccurred(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.logoutDisposable != null && !this.logoutDisposable.isDisposed()) {
            this.logoutDisposable.dispose();
        }
    }
}
