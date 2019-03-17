package com.businesslogic.guestprofilefeedback;

import com.data.profilefeedback.SubmitGuestFeedbackRequest;
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

public class ProfileFeedbackPresenter {
    private APIService apiService;
    private Disposable profileFeedbackDisposable;
    private IProfileFeedbackView profileFeedbackView;

    public ProfileFeedbackPresenter(IProfileFeedbackView iProfileFeedbackView, APIService aPIService) {
        this.profileFeedbackView = iProfileFeedbackView;
        this.apiService = aPIService;
    }

    public void submitFeedback(String str, SubmitGuestFeedbackRequest submitGuestFeedbackRequest) {
        this.profileFeedbackView.showProgressDialog(str);
        this.profileFeedbackDisposable = this.apiService.submitProfileFeedback(submitGuestFeedbackRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                ProfileFeedbackPresenter.this.profileFeedbackView.dismissProgress();
                ProfileFeedbackPresenter.this.profileFeedbackView.onFeedbackSubmitSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                ProfileFeedbackPresenter.this.profileFeedbackView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    ProfileFeedbackPresenter.this.profileFeedbackView.onError(th2);
                }
                th2 = null;
                ProfileFeedbackPresenter.this.profileFeedbackView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.profileFeedbackDisposable != null && !this.profileFeedbackDisposable.isDisposed()) {
            this.profileFeedbackDisposable.dispose();
        }
    }
}
