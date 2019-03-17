package com.businesslogic.searchproperty;

import android.util.Log;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchPropertyRequest;
import com.data.searchproperty.SearchPropertyResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class SearchPropertyPresenter {
    private static final String TAG = "SearchPropertyPresenter";
    private APIService apiService;
    private Disposable searchPropertyDisposable;
    private ISearchPropertyView searchPropertyView;

    public SearchPropertyPresenter(ISearchPropertyView iSearchPropertyView, APIService aPIService) {
        this.searchPropertyView = iSearchPropertyView;
        this.apiService = aPIService;
    }

    public void getSearchPropertyResults(String str, SearchPropertyRequest searchPropertyRequest) {
        Log.e(TAG, "getSearchPropertyResults() method has called ");
        this.searchPropertyView.showProgressDialog(str);
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            Log.e(TAG, "openFetchSearchResults() method call");
            str = this.apiService.openFetchSearchResults(searchPropertyRequest);
        } else {
            Log.e(TAG, "fetchSearchResults() method call");
            str = this.apiService.fetchSearchResults(searchPropertyRequest);
        }
        this.searchPropertyDisposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SearchPropertyResponse>() {
            public void accept(SearchPropertyResponse searchPropertyResponse) throws Exception {
                SearchPropertyPresenter.this.searchPropertyView.dismissProgress();
                SearchPropertyPresenter.this.searchPropertyView.onSuccessResponse(searchPropertyResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                SearchPropertyPresenter.this.searchPropertyView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(SearchPropertyPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            SearchPropertyPresenter.this.searchPropertyView.onError(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        SearchPropertyPresenter.this.searchPropertyView.onError(aPIError);
                    }
                }
                SearchPropertyPresenter.this.searchPropertyView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.searchPropertyDisposable != null && !this.searchPropertyDisposable.isDisposed()) {
            this.searchPropertyDisposable.dispose();
        }
    }
}
