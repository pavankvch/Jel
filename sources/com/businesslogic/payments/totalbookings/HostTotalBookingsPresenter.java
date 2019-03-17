package com.businesslogic.payments.totalbookings;

import android.util.Log;
import com.data.payments.totalbookings.HostBookingSectionModel;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.HttpException;

public class HostTotalBookingsPresenter {
    private static final String TAG = "HostTotalBookingsPresenter";
    private APIService apiService;
    private Disposable bookingsDisposable;
    private IHostTotalBookingsView hostBookingsView;

    public HostTotalBookingsPresenter(IHostTotalBookingsView iHostTotalBookingsView, APIService aPIService) {
        this.hostBookingsView = iHostTotalBookingsView;
        this.apiService = aPIService;
    }

    public void getHostTotalBookings(String str, final boolean z) {
        if (z) {
            this.hostBookingsView.showProgressDialog(str);
        } else {
            this.hostBookingsView.showSwipeToRefresh(true);
        }
        this.bookingsDisposable = this.apiService.getHostTotalBookings().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<HostBookingSectionModel>>() {
            public void accept(List<HostBookingSectionModel> list) throws Exception {
                if (z) {
                    HostTotalBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostTotalBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                HostTotalBookingsPresenter.this.hostBookingsView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    HostTotalBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostTotalBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(HostTotalBookingsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
                    }
                }
                HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
            }
        });
    }

    public void getHostTotalEarnings(String str, final boolean z) {
        if (z) {
            this.hostBookingsView.showProgressDialog(str);
        } else {
            this.hostBookingsView.showSwipeToRefresh(true);
        }
        this.bookingsDisposable = this.apiService.getHostTotalEarnings().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<HostBookingSectionModel>>() {
            public void accept(List<HostBookingSectionModel> list) throws Exception {
                if (z) {
                    HostTotalBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostTotalBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                HostTotalBookingsPresenter.this.hostBookingsView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    HostTotalBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostTotalBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(HostTotalBookingsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
                    }
                }
                HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
            }
        });
    }

    public void getHostTotalCanceledBookings(String str, final boolean z) {
        if (z) {
            this.hostBookingsView.showProgressDialog(str);
        } else {
            this.hostBookingsView.showSwipeToRefresh(true);
        }
        this.bookingsDisposable = this.apiService.getHostCancelledProperties().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<HostBookingSectionModel>>() {
            public void accept(List<HostBookingSectionModel> list) throws Exception {
                if (z) {
                    HostTotalBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostTotalBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                HostTotalBookingsPresenter.this.hostBookingsView.onSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    HostTotalBookingsPresenter.this.hostBookingsView.dismissProgress();
                } else {
                    HostTotalBookingsPresenter.this.hostBookingsView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(HostTotalBookingsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
                    }
                }
                HostTotalBookingsPresenter.this.hostBookingsView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.bookingsDisposable != null && !this.bookingsDisposable.isDisposed()) {
            this.bookingsDisposable.dispose();
        }
    }
}
