package com.businesslogic.hostbookings;

import com.data.hostbookings.HostBookingsResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class HostBookingsPresenter {
    private static final String TAG = "HostBookingsPresenter";
    private APIService apiService;
    private Disposable bookingsDisposable;
    private IHostBookingsView hostBookingsView;

    public HostBookingsPresenter(IHostBookingsView iHostBookingsView, APIService aPIService) {
        this.hostBookingsView = iHostBookingsView;
        this.apiService = aPIService;
    }

    public void getBookings(String str, final boolean z) {
        if (z) {
            this.hostBookingsView.showProgressDialog(str);
        } else {
            this.hostBookingsView.showSwipeToRefresh(true);
        }
        this.bookingsDisposable = this.apiService.getHostBookings().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HostBookingsResponse>() {
            public void accept(HostBookingsResponse hostBookingsResponse) throws Exception {
                if (z) {
                    HostBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                HostBookingsPresenter.this.hostBookingsView.onSuccess(hostBookingsResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    HostBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    HostBookingsPresenter.this.hostBookingsView.onError(th2);
                }
                th2 = null;
                HostBookingsPresenter.this.hostBookingsView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.bookingsDisposable != null && !this.bookingsDisposable.isDisposed()) {
            this.bookingsDisposable.dispose();
        }
    }
}
