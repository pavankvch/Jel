package com.businesslogic.propertycostcalendar;

import com.data.propertycostcalendar.PropertyCostCalendarRequest;
import com.data.propertycostcalendar.PropertyCostCalendarResponse;
import com.data.propertycostcalendar.UpdateCalendarRequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class PropertyCostCalendarPresenter {
    private APIService apiService;
    private Disposable disposable;
    private IPropertyCostCalenderView propertyCostCalenderView;

    public PropertyCostCalendarPresenter(IPropertyCostCalenderView iPropertyCostCalenderView, APIService aPIService) {
        this.propertyCostCalenderView = iPropertyCostCalenderView;
        this.apiService = aPIService;
    }

    public void getPropertyCostCalendarDetails(String str, PropertyCostCalendarRequest propertyCostCalendarRequest) {
        this.propertyCostCalenderView.showProgressDialog(str);
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            str = this.apiService.getOpenPropertyCostCalendarDetails(propertyCostCalendarRequest);
        } else {
            str = this.apiService.getPropertyCostCalendarDetails(propertyCostCalendarRequest);
        }
        this.disposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyCostCalendarResponse>() {
            public void accept(PropertyCostCalendarResponse propertyCostCalendarResponse) throws Exception {
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.dismissProgress();
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.onSuccess(propertyCostCalendarResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    PropertyCostCalendarPresenter.this.propertyCostCalenderView.onError(th2);
                }
                th2 = null;
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.onError(th2);
            }
        });
    }

    public void updateCalendar(String str, UpdateCalendarRequest updateCalendarRequest) {
        this.propertyCostCalenderView.showProgressDialog(str);
        this.disposable = this.apiService.updatePropertyCalendar(updateCalendarRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyCostCalendarResponse>() {
            public void accept(PropertyCostCalendarResponse propertyCostCalendarResponse) throws Exception {
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.dismissProgress();
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.onCalendarUpdated(propertyCostCalendarResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.dismissProgress();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                } else {
                    th2.printStackTrace();
                    th2 = null;
                }
                PropertyCostCalendarPresenter.this.propertyCostCalenderView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
