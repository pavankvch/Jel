package com.businesslogic.hostbookings;

import com.data.bookings.BookingCancelRequest;
import com.data.bookings.BookingCancelResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class HostUpcomingBookingsPresenter {
    private APIService apiService;
    private Disposable bookingsDisposable;
    private HostBookingsUpcomingView hostBookingsView;

    public HostUpcomingBookingsPresenter(HostBookingsUpcomingView hostBookingsUpcomingView, APIService aPIService) {
        this.hostBookingsView = hostBookingsUpcomingView;
        this.apiService = aPIService;
    }

    public void confirmBooking(String str, BookingCancelRequest bookingCancelRequest, final int i) {
        this.hostBookingsView.showProgressDialog(str);
        this.bookingsDisposable = this.apiService.confirmBookingByHost(bookingCancelRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BookingCancelResponse>() {
            public void accept(BookingCancelResponse bookingCancelResponse) throws Exception {
                HostUpcomingBookingsPresenter.this.hostBookingsView.dismissProgress();
                HostUpcomingBookingsPresenter.this.hostBookingsView.onAcceptSuccess(bookingCancelResponse, i);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                HostUpcomingBookingsPresenter.this.hostBookingsView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    HostUpcomingBookingsPresenter.this.hostBookingsView.onError(th2);
                }
                th2 = null;
                HostUpcomingBookingsPresenter.this.hostBookingsView.onError(th2);
            }
        });
    }

    public void rejectBooking(String str, BookingCancelRequest bookingCancelRequest, final int i) {
        this.hostBookingsView.showProgressDialog(str);
        this.bookingsDisposable = this.apiService.rejectBookingByHost(bookingCancelRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BookingCancelResponse>() {
            public void accept(BookingCancelResponse bookingCancelResponse) throws Exception {
                HostUpcomingBookingsPresenter.this.hostBookingsView.dismissProgress();
                HostUpcomingBookingsPresenter.this.hostBookingsView.onRejectSuccess(bookingCancelResponse, i);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                HostUpcomingBookingsPresenter.this.hostBookingsView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    HostUpcomingBookingsPresenter.this.hostBookingsView.onError(th2);
                }
                th2 = null;
                HostUpcomingBookingsPresenter.this.hostBookingsView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.bookingsDisposable != null && !this.bookingsDisposable.isDisposed()) {
            this.bookingsDisposable.dispose();
        }
    }
}
