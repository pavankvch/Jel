package com.businesslogic.propertyfeedback;

import com.data.profilefeedback.SubmitGuestFeedbackRequest;
import com.data.propertyfeedback.SubmitPropertyFeedbackRequest;
import com.data.propertyreviews.PropertyReviewsResponse;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class FeedbackPresenter {
    private APIService apiService;
    private Disposable disposable;
    private IFeedbackView feedbackView;

    public FeedbackPresenter(IFeedbackView iFeedbackView, APIService aPIService) {
        this.feedbackView = iFeedbackView;
        this.apiService = aPIService;
    }

    public void submitFeedback(String str, SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest) {
        this.feedbackView.showProgressDialog(str);
        this.disposable = this.apiService.submitPropertyFeedback(submitPropertyFeedbackRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                FeedbackPresenter.this.feedbackView.dismissProgress();
                FeedbackPresenter.this.feedbackView.onFeedbackSubmitSuccess(true);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FeedbackPresenter.this.feedbackView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    FeedbackPresenter.this.feedbackView.onError(th2);
                }
                th2 = null;
                FeedbackPresenter.this.feedbackView.onError(th2);
            }
        });
    }

    public void getFeedbacks(String str, SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest, final boolean z) {
        if (z) {
            this.feedbackView.showProgressDialog(str);
        } else {
            this.feedbackView.showSwipeToRefresh(true);
        }
        str = PrefUtils.getInstance();
        if (str.getUserAccessToken() == null || str.getCookie() == null) {
            str = this.apiService.getOpenPropertyReviews(submitPropertyFeedbackRequest);
        } else {
            str = this.apiService.getPropertyReviews(submitPropertyFeedbackRequest);
        }
        this.disposable = str.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyReviewsResponse>() {
            public void accept(PropertyReviewsResponse propertyReviewsResponse) throws Exception {
                if (z) {
                    FeedbackPresenter.this.feedbackView.dismissProgress();
                } else {
                    FeedbackPresenter.this.feedbackView.showSwipeToRefresh(false);
                }
                FeedbackPresenter.this.feedbackView.onReviewsGet(propertyReviewsResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                if (z) {
                    FeedbackPresenter.this.feedbackView.dismissProgress();
                } else {
                    FeedbackPresenter.this.feedbackView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    FeedbackPresenter.this.feedbackView.onError(th2);
                }
                th2 = null;
                FeedbackPresenter.this.feedbackView.onError(th2);
            }
        });
    }

    public void submitReport(String str, SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest) {
        this.feedbackView.showProgressDialog(str);
        this.disposable = this.apiService.reportProperty(submitPropertyFeedbackRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                FeedbackPresenter.this.feedbackView.dismissProgress();
                FeedbackPresenter.this.feedbackView.onFeedbackSubmitSuccess(false);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FeedbackPresenter.this.feedbackView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    FeedbackPresenter.this.feedbackView.onError(th2);
                }
                th2 = null;
                FeedbackPresenter.this.feedbackView.onError(th2);
            }
        });
    }

    public void getGuestFeedbackReviews(String str, SubmitGuestFeedbackRequest submitGuestFeedbackRequest, final boolean z) {
        if (z) {
            this.feedbackView.showProgressDialog(str);
        } else {
            this.feedbackView.showSwipeToRefresh(true);
        }
        this.disposable = this.apiService.getGuestReviews(submitGuestFeedbackRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<PropertyReviewsResponse>() {
            public void accept(PropertyReviewsResponse propertyReviewsResponse) throws Exception {
                if (z) {
                    FeedbackPresenter.this.feedbackView.dismissProgress();
                } else {
                    FeedbackPresenter.this.feedbackView.showSwipeToRefresh(false);
                }
                FeedbackPresenter.this.feedbackView.onReviewsGet(propertyReviewsResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                if (z) {
                    FeedbackPresenter.this.feedbackView.dismissProgress();
                } else {
                    FeedbackPresenter.this.feedbackView.showSwipeToRefresh(false);
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    FeedbackPresenter.this.feedbackView.onError(th2);
                }
                th2 = null;
                FeedbackPresenter.this.feedbackView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.disposable != null && !this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
