package com.businesslogic.propertylisting;

import com.data.propertylisting.PropertyListingResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PropertyListingPresenter {
    private APIService apiService;
    private Disposable listingsDisposable;
    private PropertListingView propertListingView;

    public PropertyListingPresenter(PropertListingView propertListingView, APIService aPIService) {
        this.propertListingView = propertListingView;
        this.apiService = aPIService;
    }

    public void getPropertListings(String str, final boolean z) {
        if (z) {
            this.propertListingView.showProgressDialog(str);
        } else {
            this.propertListingView.showSwipeToRefresh(true);
        }
        this.listingsDisposable = this.apiService.getPropertyListingDetails().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyListingResponse>() {
            public void accept(PropertyListingResponse propertyListingResponse) throws Exception {
                if (z) {
                    PropertyListingPresenter.this.propertListingView.dismissProgress();
                } else {
                    PropertyListingPresenter.this.propertListingView.showSwipeToRefresh(false);
                }
                PropertyListingPresenter.this.propertListingView.onSuccess(propertyListingResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    PropertyListingPresenter.this.propertListingView.dismissProgress();
                } else {
                    PropertyListingPresenter.this.propertListingView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    PropertyListingPresenter.this.propertListingView.onError(th2);
                }
                th2 = null;
                PropertyListingPresenter.this.propertListingView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.listingsDisposable != null && !this.listingsDisposable.isDisposed()) {
            this.listingsDisposable.dispose();
        }
    }
}
