package com.businesslogic.propertyseed;

import com.businesslogic.framework.ErrorCodes;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.retrofit.APIService;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class PropertySeedPresenter {
    private APIService apiService;
    private Disposable disposable;
    private IPropertySeedView propertySeedView;

    public PropertySeedPresenter(IPropertySeedView iPropertySeedView, APIService aPIService) {
        this.propertySeedView = iPropertySeedView;
        this.apiService = aPIService;
    }

    public void getPropertySeed(String str, boolean z) {
        if (z) {
            this.propertySeedView.showProgressDialog(str);
        }
        str = PrefUtils.getInstance();
        if (!str.getUserAccessToken() || str.getCookie() == null) {
            str = this.apiService.getOpenPropertySeed();
        } else {
            str = this.apiService.getPropertySeed();
        }
        this.disposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SeedResponse>() {
            public void accept(SeedResponse seedResponse) throws Exception {
                PropertySeedPresenter.this.propertySeedView.dismissProgress();
                PropertySeedPresenter.this.propertySeedView.onSuccess(seedResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertySeedPresenter.this.propertySeedView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    PropertySeedPresenter.this.propertySeedView.onError(PropertySeedPresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    PropertySeedPresenter.this.propertySeedView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    PropertySeedPresenter.this.propertySeedView.onError(null, 511);
                } else {
                    PropertySeedPresenter.this.propertySeedView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
