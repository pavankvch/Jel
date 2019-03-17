package com.businesslogic.payments;

import android.util.Log;
import com.data.payments.HostProperty;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.HttpException;

public class HostPropertiesPresenter {
    private static final String TAG = "HostPropertiesPresenter";
    private APIService apiService;
    private Disposable listingsDisposable;
    private IHostPropertiesView propertListingView;

    public HostPropertiesPresenter(IHostPropertiesView iHostPropertiesView, APIService aPIService) {
        this.propertListingView = iHostPropertiesView;
        this.apiService = aPIService;
    }

    public void getHostProperties(String str, final boolean z) {
        if (z) {
            this.propertListingView.showProgressDialog(str);
        } else {
            this.propertListingView.showSwipeToRefresh(true);
        }
        this.listingsDisposable = this.apiService.getHostPublishedProperties().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<HostProperty>>() {
            public void accept(List<HostProperty> list) throws Exception {
                if (z) {
                    HostPropertiesPresenter.this.propertListingView.dismissProgress();
                } else {
                    HostPropertiesPresenter.this.propertListingView.showSwipeToRefresh(false);
                }
                HostPropertiesPresenter.this.propertListingView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    HostPropertiesPresenter.this.propertListingView.dismissProgress();
                } else {
                    HostPropertiesPresenter.this.propertListingView.showSwipeToRefresh(false);
                }
                APIError aPIError = null;
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(HostPropertiesPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            HostPropertiesPresenter.this.propertListingView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        HostPropertiesPresenter.this.propertListingView.onError(aPIError);
                    }
                }
                HostPropertiesPresenter.this.propertListingView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.listingsDisposable != null && !this.listingsDisposable.isDisposed()) {
            this.listingsDisposable.dispose();
        }
    }
}
