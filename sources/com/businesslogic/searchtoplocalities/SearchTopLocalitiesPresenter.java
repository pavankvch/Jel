package com.businesslogic.searchtoplocalities;

import android.util.Log;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.searchtoplocalities.SearchTopLocality;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class SearchTopLocalitiesPresenter {
    private static final String TAG = "SearchTopLocalitiesPresenter";
    private APIService apiService;
    private Disposable searchTopLocalitiesDisposable;
    private ISearchTopLocalitiesView searchTopLocalitiesView;

    public SearchTopLocalitiesPresenter(ISearchTopLocalitiesView iSearchTopLocalitiesView, APIService aPIService) {
        this.searchTopLocalitiesView = iSearchTopLocalitiesView;
        this.apiService = aPIService;
    }

    public void fetchTopLocalities(String str) {
        this.searchTopLocalitiesView.showProgressDialog(str);
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            str = this.apiService.getOpenTopLocalitiesData();
        } else {
            str = this.apiService.getTopLocalitiesData();
        }
        this.searchTopLocalitiesDisposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SearchTopLocality>() {
            public void accept(SearchTopLocality searchTopLocality) throws Exception {
                SearchTopLocalitiesPresenter.this.searchTopLocalitiesView.dismissProgress();
                SearchTopLocalitiesPresenter.this.searchTopLocalitiesView.onSuccessResponse(searchTopLocality);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Exception e;
                SearchTopLocalitiesPresenter.this.searchTopLocalitiesView.dismissProgress();
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(SearchTopLocalitiesPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Exception e2) {
                            e = e2;
                            aPIError = aPIError2;
                            e.printStackTrace();
                            SearchTopLocalitiesPresenter.this.searchTopLocalitiesView.onErrorResponse(aPIError);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        SearchTopLocalitiesPresenter.this.searchTopLocalitiesView.onErrorResponse(aPIError);
                    }
                }
                SearchTopLocalitiesPresenter.this.searchTopLocalitiesView.onErrorResponse(aPIError);
            }
        });
    }

    public void unSubscribeDisposable() {
        if (this.searchTopLocalitiesDisposable != null && !this.searchTopLocalitiesDisposable.isDisposed()) {
            this.searchTopLocalitiesDisposable.dispose();
        }
    }
}
