package com.businesslogic.applycoupon;

import android.util.Log;
import com.data.applycoupon.ApplyCouponRequest;
import com.data.applycoupon.ApplyCouponResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class ApplyCouponPresenter {
    private final String TAG = ApplyCouponPresenter.class.getSimpleName();
    private APIService apiService;
    private Disposable applyCouponDisposable;
    private IApplyCouponView applyCouponView;

    public ApplyCouponPresenter(IApplyCouponView iApplyCouponView, APIService aPIService) {
        this.applyCouponView = iApplyCouponView;
        this.apiService = aPIService;
    }

    public void applyCoupon(final ApplyCouponRequest applyCouponRequest, String str) {
        this.applyCouponView.showProgressDialog(str);
        this.applyCouponDisposable = this.apiService.applyCoupon(applyCouponRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ApplyCouponResponse>() {
            public void accept(ApplyCouponResponse applyCouponResponse) throws Exception {
                ApplyCouponPresenter.this.applyCouponView.dismissProgress();
                ApplyCouponPresenter.this.applyCouponView.onSuccess(applyCouponResponse, applyCouponRequest);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                ApplyCouponPresenter.this.applyCouponView.dismissProgress();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(ApplyCouponPresenter.this.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            ApplyCouponPresenter.this.applyCouponView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        ApplyCouponPresenter.this.applyCouponView.onError(aPIError);
                    }
                }
                ApplyCouponPresenter.this.applyCouponView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.applyCouponDisposable != null && !this.applyCouponDisposable.isDisposed()) {
            this.applyCouponDisposable.dispose();
        }
    }
}
