package com.businesslogic.wallet;

import android.util.Log;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.wallet.DeleteSavedCardRequest;
import com.data.wallet.WalletResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class WalletPresenter {
    private static final String TAG = "WalletPresenter";
    private APIService apiService;
    private Disposable propertyDetailDisposable;
    private IWalletView walletView;

    public WalletPresenter(IWalletView iWalletView, APIService aPIService) {
        this.walletView = iWalletView;
        this.apiService = aPIService;
    }

    public void getWalletData(String str, final boolean z) {
        if (z) {
            this.walletView.showProgressDialog(str);
        } else {
            this.walletView.showSwipeToRefresh(true);
        }
        this.propertyDetailDisposable = this.apiService.getWalletData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<WalletResponse>() {
            public void accept(WalletResponse walletResponse) throws Exception {
                if (z) {
                    WalletPresenter.this.walletView.dismissProgress();
                } else {
                    WalletPresenter.this.walletView.showSwipeToRefresh(false);
                }
                WalletPresenter.this.walletView.onSuccess(walletResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    WalletPresenter.this.walletView.dismissProgress();
                } else {
                    WalletPresenter.this.walletView.showSwipeToRefresh(false);
                }
                APIError aPIError = null;
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(WalletPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            WalletPresenter.this.walletView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        WalletPresenter.this.walletView.onError(aPIError);
                    }
                }
                WalletPresenter.this.walletView.onError(aPIError);
            }
        });
    }

    public void refundAmount() {
        this.walletView.showProgressDialog("Please Wait...");
        this.propertyDetailDisposable = this.apiService.guestRefund().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                WalletPresenter.this.walletView.dismissProgress();
                WalletPresenter.this.walletView.onRefundSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                WalletPresenter.this.walletView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    WalletPresenter.this.walletView.onError(th2);
                }
                th2 = null;
                WalletPresenter.this.walletView.onError(th2);
            }
        });
    }

    public void deleteCard(DeleteSavedCardRequest deleteSavedCardRequest, String str) {
        this.walletView.showProgressDialog(str);
        this.propertyDetailDisposable = this.apiService.deleteSavedCard(deleteSavedCardRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                WalletPresenter.this.walletView.dismissProgress();
                WalletPresenter.this.walletView.onCardDeleted();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                WalletPresenter.this.walletView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    WalletPresenter.this.walletView.onError(th2);
                }
                th2 = null;
                WalletPresenter.this.walletView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.propertyDetailDisposable != null && !this.propertyDetailDisposable.isDisposed()) {
            this.propertyDetailDisposable.dispose();
        }
    }
}
