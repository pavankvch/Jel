package com.businesslogic.feedback;

import com.data.feedback.SubmitFeedbackRequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class SubmitFeedbackPresenter {
    private APIService apiService;
    private ISubmitFeedbackView feedbackView;
    private Disposable submitFeedbackDisposable;

    public SubmitFeedbackPresenter(ISubmitFeedbackView iSubmitFeedbackView, APIService aPIService) {
        this.feedbackView = iSubmitFeedbackView;
        this.apiService = aPIService;
    }

    public void submitFeedback(String str, SubmitFeedbackRequest submitFeedbackRequest) {
        this.feedbackView.showProgressDialog(str);
        this.submitFeedbackDisposable = this.apiService.submitFeedbackGuest(submitFeedbackRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                SubmitFeedbackPresenter.this.feedbackView.dismissProgress();
                SubmitFeedbackPresenter.this.feedbackView.onFeedbackSubmitSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SubmitFeedbackPresenter.this.feedbackView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    SubmitFeedbackPresenter.this.feedbackView.onError(th2);
                }
                th2 = null;
                SubmitFeedbackPresenter.this.feedbackView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.submitFeedbackDisposable != null && !this.submitFeedbackDisposable.isDisposed()) {
            this.submitFeedbackDisposable.dispose();
        }
    }
}
