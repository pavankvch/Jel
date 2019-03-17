package com.businesslogic.SeedAndLanguage;

import com.businesslogic.framework.ErrorCodes;
import com.data.SeedAndLanguage.ChangeLanguageResponse;
import com.data.SeedAndLanguage.SeedAndLanguageCombinedResponse;
import com.data.SeedAndLanguage.SendDeviceTokenRequest;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.retrofit.APIService;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.google.gson.Gson;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class SeedAndLanguageChangePresenter {
    private APIService apiService;
    private Disposable disposable;
    private ISeedAndLanguageChangeView seedAndLanguageChangeView;

    public SeedAndLanguageChangePresenter(ISeedAndLanguageChangeView iSeedAndLanguageChangeView, APIService aPIService) {
        this.apiService = aPIService;
        this.seedAndLanguageChangeView = iSeedAndLanguageChangeView;
    }

    public void changeDeviceLanguageAndDoSeedCall() {
        this.seedAndLanguageChangeView.showLoading();
        SendDeviceTokenRequest sendDeviceTokenRequest = new SendDeviceTokenRequest();
        sendDeviceTokenRequest.setDeviceToken(PrefUtils.getInstance().getFcmToken());
        this.disposable = Single.zip(this.apiService.changeLanguage(sendDeviceTokenRequest).subscribeOn(Schedulers.io()), this.apiService.getPropertySeed().subscribeOn(Schedulers.io()), new BiFunction<ChangeLanguageResponse, SeedResponse, SeedAndLanguageCombinedResponse>() {
            public SeedAndLanguageCombinedResponse apply(ChangeLanguageResponse changeLanguageResponse, SeedResponse seedResponse) throws Exception {
                return new SeedAndLanguageCombinedResponse(changeLanguageResponse, seedResponse);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SeedAndLanguageCombinedResponse>() {
            public void accept(SeedAndLanguageCombinedResponse seedAndLanguageCombinedResponse) throws Exception {
                SeedAndLanguageChangePresenter.this.seedAndLanguageChangeView.dismissProgress();
                SeedAndLanguageChangePresenter.this.seedAndLanguageChangeView.onSuccess(seedAndLanguageCombinedResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                SeedAndLanguageChangePresenter.this.seedAndLanguageChangeView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    SeedAndLanguageChangePresenter.this.seedAndLanguageChangeView.onError(SeedAndLanguageChangePresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SeedAndLanguageChangePresenter.this.seedAndLanguageChangeView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SeedAndLanguageChangePresenter.this.seedAndLanguageChangeView.onError(null, 511);
                } else {
                    SeedAndLanguageChangePresenter.this.seedAndLanguageChangeView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    private APIError getApiError(ResponseBody responseBody) {
        try {
            return (APIError) new Gson().fromJson(responseBody.string(), APIError.class);
        } catch (ResponseBody responseBody2) {
            responseBody2.printStackTrace();
            APIError aPIError = new APIError();
            aPIError.setSeken_errors(responseBody2.getMessage());
            return aPIError;
        }
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
