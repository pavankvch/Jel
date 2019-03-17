package com.businesslogic.becomehost;

import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class BecomeHostPresenter {
    private APIService apiService;
    private IBecomeHostView profileView;
    private Disposable propertyDetailDisposable;

    public BecomeHostPresenter(IBecomeHostView iBecomeHostView, APIService aPIService) {
        this.profileView = iBecomeHostView;
        this.apiService = aPIService;
    }

    public void becomeHost(String str) {
        this.profileView.showProgressDialog(str);
        this.propertyDetailDisposable = this.apiService.becomeHost().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                BecomeHostPresenter.this.profileView.dismissProgress();
                BecomeHostPresenter.this.profileView.onHostSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                BecomeHostPresenter.this.profileView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    BecomeHostPresenter.this.profileView.onError(th2);
                }
                th2 = null;
                BecomeHostPresenter.this.profileView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.propertyDetailDisposable != null && !this.propertyDetailDisposable.isDisposed()) {
            this.propertyDetailDisposable.dispose();
        }
    }
}
