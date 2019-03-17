package com.businesslogic.bankdetails;

import android.util.Log;
import com.data.bankdetails.AddBankDetailsRequest;
import com.data.bankdetails.BankDetailsResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class BankDetailsPresenter {
    private static final String TAG = "BankDetailsPresenter";
    private IBankDetailsView addbankDetailsView;
    private APIService apiService;
    private Disposable bankDetailsDisposable;

    public BankDetailsPresenter(IBankDetailsView iBankDetailsView, APIService aPIService) {
        this.addbankDetailsView = iBankDetailsView;
        this.apiService = aPIService;
    }

    public void addBankDetails(String str, AddBankDetailsRequest addBankDetailsRequest) {
        this.addbankDetailsView.showProgressDialog(str);
        this.bankDetailsDisposable = this.apiService.addBankDetails(addBankDetailsRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BankDetailsResponse>() {
            public void accept(BankDetailsResponse bankDetailsResponse) throws Exception {
                BankDetailsPresenter.this.addbankDetailsView.dismissProgress();
                BankDetailsPresenter.this.addbankDetailsView.onSuccess(bankDetailsResponse, true);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                BankDetailsPresenter.this.addbankDetailsView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(BankDetailsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            BankDetailsPresenter.this.addbankDetailsView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        BankDetailsPresenter.this.addbankDetailsView.onError(aPIError);
                    }
                }
                BankDetailsPresenter.this.addbankDetailsView.onError(aPIError);
            }
        });
    }

    public void getBankDetails(String str) {
        this.addbankDetailsView.showProgressDialog(str);
        this.bankDetailsDisposable = this.apiService.getBankDetails().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BankDetailsResponse>() {
            public void accept(BankDetailsResponse bankDetailsResponse) throws Exception {
                BankDetailsPresenter.this.addbankDetailsView.dismissProgress();
                BankDetailsPresenter.this.addbankDetailsView.onSuccess(bankDetailsResponse, false);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                BankDetailsPresenter.this.addbankDetailsView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(BankDetailsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            BankDetailsPresenter.this.addbankDetailsView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        BankDetailsPresenter.this.addbankDetailsView.onError(aPIError);
                    }
                }
                BankDetailsPresenter.this.addbankDetailsView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.bankDetailsDisposable != null && !this.bankDetailsDisposable.isDisposed()) {
            this.bankDetailsDisposable.dispose();
        }
    }
}
