package com.businesslogic.bookings;

import android.util.Log;
import com.data.bookings.BookingsResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class BookingsPresenter {
    private static final String TAG = "BookingsPresenter";
    private APIService apiService;
    private Disposable bookingsDisposable;
    private BookingsView guestBookingsView;

    public BookingsPresenter(BookingsView bookingsView, APIService aPIService) {
        this.guestBookingsView = bookingsView;
        this.apiService = aPIService;
    }

    public void getBookingsData(String str, final boolean z) {
        if (z) {
            this.guestBookingsView.showProgressDialog(str);
        } else {
            this.guestBookingsView.showSwipeToRefresh(true);
        }
        this.bookingsDisposable = this.apiService.getGuestBookings().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BookingsResponse>() {
            public void accept(BookingsResponse bookingsResponse) throws Exception {
                if (z) {
                    BookingsPresenter.this.guestBookingsView.dismissProgress();
                } else {
                    BookingsPresenter.this.guestBookingsView.showSwipeToRefresh(false);
                }
                BookingsPresenter.this.guestBookingsView.onSuccess(bookingsResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                BookingsPresenter.this.guestBookingsView.dismissProgress();
                if (z) {
                    BookingsPresenter.this.guestBookingsView.dismissProgress();
                } else {
                    BookingsPresenter.this.guestBookingsView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                APIError aPIError = null;
                if (th instanceof HttpException) {
                    try {
                        APIError aPIError2 = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                        try {
                            Log.e(BookingsPresenter.TAG, aPIError2.getSeken_errors());
                            aPIError = aPIError2;
                        } catch (Throwable e) {
                            Throwable th2 = e;
                            aPIError = aPIError2;
                            th = th2;
                            th.printStackTrace();
                            BookingsPresenter.this.guestBookingsView.onError(aPIError);
                        }
                    } catch (Exception e2) {
                        th = e2;
                        th.printStackTrace();
                        BookingsPresenter.this.guestBookingsView.onError(aPIError);
                    }
                }
                BookingsPresenter.this.guestBookingsView.onError(aPIError);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.bookingsDisposable != null && !this.bookingsDisposable.isDisposed()) {
            this.bookingsDisposable.dispose();
        }
    }
}
