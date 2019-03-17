package com.businesslogic.payments;

import android.util.Log;
import com.data.payments.HostPaymentsResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class HostPaymentsPresenter {
    private static final String TAG = "HostPaymentsPresenter";
    private APIService apiService;
    private IHostPaymentsView hostPaymentsView;
    private Disposable listingsDisposable;

    public HostPaymentsPresenter(IHostPaymentsView iHostPaymentsView, APIService aPIService) {
        this.hostPaymentsView = iHostPaymentsView;
        this.apiService = aPIService;
    }

    public void getHostPayments(String str) {
        this.hostPaymentsView.showProgressDialog(str);
        this.listingsDisposable = this.apiService.getHostPayments().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HostPaymentsResponse>() {
            public void accept(HostPaymentsResponse hostPaymentsResponse) throws Exception {
                HostPaymentsPresenter.this.hostPaymentsView.dismissProgress();
                HostPaymentsPresenter.this.hostPaymentsView.onSuccess(hostPaymentsResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                HostPaymentsPresenter.this.hostPaymentsView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(HostPaymentsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            HostPaymentsPresenter.this.hostPaymentsView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        HostPaymentsPresenter.this.hostPaymentsView.onError(aPIError);
                    }
                }
                HostPaymentsPresenter.this.hostPaymentsView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.listingsDisposable != null && !this.listingsDisposable.isDisposed()) {
            this.listingsDisposable.dispose();
        }
    }
}
