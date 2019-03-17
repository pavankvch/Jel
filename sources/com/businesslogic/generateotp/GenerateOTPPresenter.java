package com.businesslogic.generateotp;

import android.util.Log;
import com.data.generateotp.GenerateOTPRequest;
import com.data.generateotp.UpdateMobileNumberRequest;
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

public class GenerateOTPPresenter {
    private static final String TAG = "GenerateOTPPresenter";
    private APIService apiService;
    private Disposable generateOTPDisposable;
    private IGenerateOTPView generateOTPView;
    private Disposable updateMobileNumberDisposable;

    public GenerateOTPPresenter(IGenerateOTPView iGenerateOTPView, APIService aPIService) {
        this.generateOTPView = iGenerateOTPView;
        this.apiService = aPIService;
    }

    public void generateOTP(String str, GenerateOTPRequest generateOTPRequest) {
        this.generateOTPView.showProgressDialog(str);
        this.generateOTPDisposable = this.apiService.generateOTP(generateOTPRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(@NonNull ResponseBody responseBody) throws Exception {
                GenerateOTPPresenter.this.generateOTPView.dismissProgress();
                GenerateOTPPresenter.this.generateOTPView.generateOTPSuccessResponse();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                Exception e;
                GenerateOTPPresenter.this.generateOTPView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(GenerateOTPPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            GenerateOTPPresenter.this.generateOTPView.generateOTPerrorOccurred(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        GenerateOTPPresenter.this.generateOTPView.generateOTPerrorOccurred(aPIError);
                    }
                }
                GenerateOTPPresenter.this.generateOTPView.generateOTPerrorOccurred(aPIError);
            }
        });
    }

    public void updateMobileNumber(String str, final UpdateMobileNumberRequest updateMobileNumberRequest) {
        this.generateOTPView.showProgressDialog(str);
        this.updateMobileNumberDisposable = this.apiService.updateMobileNumber(updateMobileNumberRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                GenerateOTPPresenter.this.generateOTPView.dismissProgress();
                GenerateOTPPresenter.this.generateOTPView.updateMobileNumberSuccess(updateMobileNumberRequest);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                GenerateOTPPresenter.this.generateOTPView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(GenerateOTPPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            GenerateOTPPresenter.this.generateOTPView.generateOTPerrorOccurred(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        GenerateOTPPresenter.this.generateOTPView.generateOTPerrorOccurred(aPIError);
                    }
                }
                GenerateOTPPresenter.this.generateOTPView.generateOTPerrorOccurred(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (!(this.generateOTPDisposable == null || this.generateOTPDisposable.isDisposed())) {
            this.generateOTPDisposable.dispose();
        }
        if (this.updateMobileNumberDisposable != null && !this.updateMobileNumberDisposable.isDisposed()) {
            this.updateMobileNumberDisposable.dispose();
        }
    }
}
