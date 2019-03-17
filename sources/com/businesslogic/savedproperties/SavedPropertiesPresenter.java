package com.businesslogic.savedproperties;

import com.businesslogic.framework.ErrorCodes;
import com.data.retrofit.APIService;
import com.data.searchproperty.SearchProperty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.HttpException;

public class SavedPropertiesPresenter {
    private APIService apiService;
    private Disposable disposable;
    private ISavedPropertiesView savedPropertiesView;

    public SavedPropertiesPresenter(ISavedPropertiesView iSavedPropertiesView, APIService aPIService) {
        this.savedPropertiesView = iSavedPropertiesView;
        this.apiService = aPIService;
    }

    public void getSavedProperties(String str, final boolean z) {
        if (z) {
            this.savedPropertiesView.showProgressDialog(str);
        } else {
            this.savedPropertiesView.showSwipeToRefresh(true);
        }
        this.disposable = this.apiService.getSavedPropertiesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<SearchProperty>>() {
            public void accept(List<SearchProperty> list) throws Exception {
                if (z) {
                    SavedPropertiesPresenter.this.savedPropertiesView.dismissProgress();
                } else {
                    SavedPropertiesPresenter.this.savedPropertiesView.showSwipeToRefresh(false);
                }
                SavedPropertiesPresenter.this.savedPropertiesView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    SavedPropertiesPresenter.this.savedPropertiesView.dismissProgress();
                } else {
                    SavedPropertiesPresenter.this.savedPropertiesView.showSwipeToRefresh(false);
                }
                if (th instanceof HttpException) {
                    SavedPropertiesPresenter.this.savedPropertiesView.onError(SavedPropertiesPresenter.this.getErrorMessage(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    SavedPropertiesPresenter.this.savedPropertiesView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    SavedPropertiesPresenter.this.savedPropertiesView.onError(null, 511);
                } else {
                    SavedPropertiesPresenter.this.savedPropertiesView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
                }
            }
        });
    }

    private String getErrorMessage(ResponseBody responseBody) {
        try {
            return new JSONObject(responseBody.string()).getString("seken_errors");
        } catch (ResponseBody responseBody2) {
            return responseBody2.getMessage();
        }
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
