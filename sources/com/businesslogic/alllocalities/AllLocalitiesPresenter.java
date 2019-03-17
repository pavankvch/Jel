package com.businesslogic.alllocalities;

import android.util.Log;
import com.data.alllocalities.AllLocalitiesResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.json.JSONObject;
import retrofit2.HttpException;

public class AllLocalitiesPresenter {
    private static final String TAG = "AllLocalitiesPresenter";
    private APIService apiService;
    private Disposable localitiesDisposable;
    private IAllLocalitiesView localitiesView;

    public AllLocalitiesPresenter(IAllLocalitiesView iAllLocalitiesView, APIService aPIService) {
        this.localitiesView = iAllLocalitiesView;
        this.apiService = aPIService;
    }

    public void getAllLocalitiesFromCountry(String str) {
        this.localitiesView.showProgressDialog(str);
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            str = this.apiService.getOpenAllLocalities();
        } else {
            str = this.apiService.getAllLocalities(new JSONObject());
        }
        this.localitiesDisposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<AllLocalitiesResponse>>() {
            public void accept(List<AllLocalitiesResponse> list) throws Exception {
                AllLocalitiesPresenter.this.localitiesView.dismissProgress();
                AllLocalitiesPresenter.this.localitiesView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                AllLocalitiesPresenter.this.localitiesView.dismissProgress();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(AllLocalitiesPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            AllLocalitiesPresenter.this.localitiesView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        AllLocalitiesPresenter.this.localitiesView.onError(aPIError);
                    }
                }
                AllLocalitiesPresenter.this.localitiesView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.localitiesDisposable != null && !this.localitiesDisposable.isDisposed()) {
            this.localitiesDisposable.dispose();
        }
    }
}
