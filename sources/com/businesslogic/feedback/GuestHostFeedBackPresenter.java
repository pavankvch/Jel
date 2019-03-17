package com.businesslogic.feedback;

import com.businesslogic.framework.ErrorCodes;
import com.data.feedback.FeedBackCombinedResponse;
import com.data.feedback.FeedbackCategoryResponse;
import com.data.retrofit.APIService;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.HttpException;

public class GuestHostFeedBackPresenter {
    private APIService apiService;
    private IGuestHostFeedBackView feedBackView;
    private Disposable feedbackDisposable;

    public GuestHostFeedBackPresenter(IGuestHostFeedBackView iGuestHostFeedBackView, APIService aPIService) {
        this.feedBackView = iGuestHostFeedBackView;
        this.apiService = aPIService;
    }

    public void getGuestHostFeedBackCategories(final boolean z) {
        if (z) {
            this.feedBackView.showLoading();
        }
        this.feedbackDisposable = Single.zip(this.apiService.feedbackGuest().subscribeOn(Schedulers.io()), this.apiService.feedbackHost().subscribeOn(Schedulers.io()), new BiFunction<List<FeedbackCategoryResponse>, List<FeedbackCategoryResponse>, FeedBackCombinedResponse>() {
            public FeedBackCombinedResponse apply(List<FeedbackCategoryResponse> list, List<FeedbackCategoryResponse> list2) throws Exception {
                return new FeedBackCombinedResponse(list, list2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<FeedBackCombinedResponse>() {
            public void accept(FeedBackCombinedResponse feedBackCombinedResponse) throws Exception {
                if (z) {
                    GuestHostFeedBackPresenter.this.feedBackView.dismissProgress();
                }
                GuestHostFeedBackPresenter.this.feedBackView.onSuccess(feedBackCombinedResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (z) {
                    GuestHostFeedBackPresenter.this.feedBackView.dismissProgress();
                }
                th.printStackTrace();
                if (th instanceof HttpException) {
                    GuestHostFeedBackPresenter.this.feedBackView.onError(GuestHostFeedBackPresenter.this.getErrorMessage(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    GuestHostFeedBackPresenter.this.feedBackView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    GuestHostFeedBackPresenter.this.feedBackView.onError(null, 511);
                } else {
                    GuestHostFeedBackPresenter.this.feedBackView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.feedbackDisposable != null && !this.feedbackDisposable.isDisposed()) {
            this.feedbackDisposable.dispose();
        }
    }
}
