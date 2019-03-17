package com.businesslogic.propertylisting;

import android.util.Log;
import com.data.propertylisting.DeletePropertyRequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class PropertyDeletingPresenter {
    private static final String TAG = "PropertyDeletingPresenter";
    private APIService apiService;
    private DeletePropertyView deletePropertyView;
    private Disposable listingsDisposable;

    public PropertyDeletingPresenter(DeletePropertyView deletePropertyView, APIService aPIService) {
        this.deletePropertyView = deletePropertyView;
        this.apiService = aPIService;
    }

    public void getPropertyDeleted(String str, DeletePropertyRequest deletePropertyRequest, final int i, final int i2) {
        this.deletePropertyView.showProgressDialog(str);
        this.listingsDisposable = this.apiService.deleteProperty(deletePropertyRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                PropertyDeletingPresenter.this.deletePropertyView.dismissProgress();
                PropertyDeletingPresenter.this.deletePropertyView.onSuccess(i, i2);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                PropertyDeletingPresenter.this.deletePropertyView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(PropertyDeletingPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            PropertyDeletingPresenter.this.deletePropertyView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        PropertyDeletingPresenter.this.deletePropertyView.onError(aPIError);
                    }
                }
                PropertyDeletingPresenter.this.deletePropertyView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.listingsDisposable != null && !this.listingsDisposable.isDisposed()) {
            this.listingsDisposable.dispose();
        }
    }
}
