package com.businesslogic.dashboardhome;

import com.businesslogic.framework.ErrorCodes;
import com.data.dashboardhome.DashBoardHomeResponse;
import com.data.retrofit.APIService;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.HttpException;

public class DashBoardHomePresenter {
    private APIService apiService;
    private Disposable dashBoardHomeDisposable;
    private IDashBoardHomeView dashBoardHomeView;

    public DashBoardHomePresenter(IDashBoardHomeView iDashBoardHomeView, APIService aPIService) {
        this.dashBoardHomeView = iDashBoardHomeView;
        this.apiService = aPIService;
    }

    public void getDashBoardData(String str, final boolean z) {
        if (z) {
            this.dashBoardHomeView.showProgressDialog(str);
        } else {
            this.dashBoardHomeView.showSwipeToRefresh(true);
        }
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            str = this.apiService.getOpenDashBoardData();
        } else {
            str = this.apiService.getDashBoardData(new JSONObject());
        }
        this.dashBoardHomeDisposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DashBoardHomeResponse>() {
            public void accept(DashBoardHomeResponse dashBoardHomeResponse) throws Exception {
                if (z) {
                    DashBoardHomePresenter.this.dashBoardHomeView.dismissProgress();
                } else {
                    DashBoardHomePresenter.this.dashBoardHomeView.showSwipeToRefresh(false);
                }
                DashBoardHomePresenter.this.dashBoardHomeView.setLocalities(dashBoardHomeResponse.getLocalitiesList());
                DashBoardHomePresenter.this.dashBoardHomeView.setSectionProperties(dashBoardHomeResponse.getPropertyList(), z);
                DashBoardHomePresenter.this.dashBoardHomeView.setLabelsList(dashBoardHomeResponse.getLabelsList());
                DashBoardHomePresenter.this.dashBoardHomeView.showInboxCount(dashBoardHomeResponse.getGuestInboxCount(), dashBoardHomeResponse.getHostInboxCount());
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                if (z) {
                    DashBoardHomePresenter.this.dashBoardHomeView.dismissProgress();
                } else {
                    DashBoardHomePresenter.this.dashBoardHomeView.showSwipeToRefresh(false);
                }
                if (th instanceof HttpException) {
                    DashBoardHomePresenter.this.dashBoardHomeView.onError(DashBoardHomePresenter.this.getErrorMessage(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    DashBoardHomePresenter.this.dashBoardHomeView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    DashBoardHomePresenter.this.dashBoardHomeView.onError(null, 511);
                } else {
                    DashBoardHomePresenter.this.dashBoardHomeView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.dashBoardHomeDisposable != null && !this.dashBoardHomeDisposable.isDisposed()) {
            this.dashBoardHomeDisposable.dispose();
        }
    }
}
