package com.businesslogic.wallet;

import android.util.Log;
import com.data.paymenthistory.PaymentHistoryResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PaymentHistoryPresenter {
    private static final String TAG = "PaymentHistoryPresenter";
    private APIService apiService;
    private IPaymentHistoryView paymentHistoryView;
    private Disposable propertyDetailDisposable;

    public PaymentHistoryPresenter(IPaymentHistoryView iPaymentHistoryView, APIService aPIService) {
        this.paymentHistoryView = iPaymentHistoryView;
        this.apiService = aPIService;
    }

    public void getPaymentHistory(String str, final boolean z) {
        if (z) {
            this.paymentHistoryView.showProgressDialog(str);
        } else {
            this.paymentHistoryView.showSwipeToRefresh(true);
        }
        this.propertyDetailDisposable = this.apiService.getPaymentHistory().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PaymentHistoryResponse>() {
            public void accept(PaymentHistoryResponse paymentHistoryResponse) throws Exception {
                PaymentHistoryPresenter.this.paymentHistoryView.dismissProgress();
                if (z) {
                    PaymentHistoryPresenter.this.paymentHistoryView.dismissProgress();
                } else {
                    PaymentHistoryPresenter.this.paymentHistoryView.showSwipeToRefresh(false);
                }
                PaymentHistoryPresenter.this.paymentHistoryView.onSuccess(paymentHistoryResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    PaymentHistoryPresenter.this.paymentHistoryView.dismissProgress();
                } else {
                    PaymentHistoryPresenter.this.paymentHistoryView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(PaymentHistoryPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            PaymentHistoryPresenter.this.paymentHistoryView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        PaymentHistoryPresenter.this.paymentHistoryView.onError(aPIError);
                    }
                }
                PaymentHistoryPresenter.this.paymentHistoryView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.propertyDetailDisposable != null && !this.propertyDetailDisposable.isDisposed()) {
            this.propertyDetailDisposable.dispose();
        }
    }
}
