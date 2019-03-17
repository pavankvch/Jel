package com.businesslogic.propertyfavourite;

import com.data.propertyfavourite.PropertyFavouriteRequest;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PropertyFavouritePresenter {
    private APIService apiService;
    private Disposable propertyFavouriteDisposable;
    private IPropertyFavouriteView propertyFavouriteView;

    public PropertyFavouritePresenter(IPropertyFavouriteView iPropertyFavouriteView, APIService aPIService) {
        this.propertyFavouriteView = iPropertyFavouriteView;
        this.apiService = aPIService;
    }

    public void setPropertyFavouriteToUser(String str, PropertyFavouriteRequest propertyFavouriteRequest, final int i) {
        this.propertyFavouriteView.showProgressDialog(str);
        this.propertyFavouriteDisposable = this.apiService.setPropertyFavourite(propertyFavouriteRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyFavouriteResponse>() {
            public void accept(PropertyFavouriteResponse propertyFavouriteResponse) throws Exception {
                PropertyFavouritePresenter.this.propertyFavouriteView.dismissProgress();
                PropertyFavouritePresenter.this.propertyFavouriteView.onSuccess(propertyFavouriteResponse, i);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyFavouritePresenter.this.propertyFavouriteView.dismissProgress();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    PropertyFavouritePresenter.this.propertyFavouriteView.onError(th2);
                }
                th2 = null;
                PropertyFavouritePresenter.this.propertyFavouriteView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.propertyFavouriteDisposable != null && !this.propertyFavouriteDisposable.isDisposed()) {
            this.propertyFavouriteDisposable.dispose();
        }
    }
}
