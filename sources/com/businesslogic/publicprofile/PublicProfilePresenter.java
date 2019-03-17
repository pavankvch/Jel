package com.businesslogic.publicprofile;

import com.data.profile.GetGuestProfileRequest;
import com.data.publicprofile.PublicProfileResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PublicProfilePresenter {
    private APIService apiService;
    private Disposable profiDetailDisposable;
    private IPublicProfileView publicProfileView;

    public PublicProfilePresenter(IPublicProfileView iPublicProfileView, APIService aPIService) {
        this.publicProfileView = iPublicProfileView;
        this.apiService = aPIService;
    }

    public void getGuestProfileData(String str, GetGuestProfileRequest getGuestProfileRequest, final boolean z) {
        if (z) {
            this.publicProfileView.showProgressDialog(str);
        } else {
            this.publicProfileView.showSwipeToRefresh(true);
        }
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            str = this.apiService.getOpenGuestPublicProfile(getGuestProfileRequest);
        } else {
            str = this.apiService.getGuestPublicProfile(getGuestProfileRequest);
        }
        this.profiDetailDisposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PublicProfileResponse>() {
            public void accept(PublicProfileResponse publicProfileResponse) throws Exception {
                if (z) {
                    PublicProfilePresenter.this.publicProfileView.dismissProgress();
                } else {
                    PublicProfilePresenter.this.publicProfileView.showSwipeToRefresh(false);
                }
                PublicProfilePresenter.this.publicProfileView.onSuccess(publicProfileResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    PublicProfilePresenter.this.publicProfileView.dismissProgress();
                } else {
                    PublicProfilePresenter.this.publicProfileView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    PublicProfilePresenter.this.publicProfileView.onError(th2);
                }
                th2 = null;
                PublicProfilePresenter.this.publicProfileView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.profiDetailDisposable != null && !this.profiDetailDisposable.isDisposed()) {
            this.profiDetailDisposable.dispose();
        }
    }
}
