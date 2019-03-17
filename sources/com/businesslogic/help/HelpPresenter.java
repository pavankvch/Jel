package com.businesslogic.help;

import com.data.help.Answer;
import com.data.help.GetAnswerRequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import retrofit2.HttpException;

public class HelpPresenter {
    private APIService apiService;
    private Disposable helpDisposable;
    private IHelpView helpView;

    public HelpPresenter(IHelpView iHelpView, APIService aPIService) {
        this.helpView = iHelpView;
        this.apiService = aPIService;
    }

    public void getGuestAnswer(String str, GetAnswerRequest getAnswerRequest) {
        this.helpView.showProgressDialog(str);
        this.helpDisposable = this.apiService.faqAnswer(getAnswerRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Answer>>() {
            public void accept(List<Answer> list) throws Exception {
                HelpPresenter.this.helpView.dismissProgress();
                HelpPresenter.this.helpView.onAnsweSuccess(list);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                HelpPresenter.this.helpView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    HelpPresenter.this.helpView.onError(th2);
                }
                th2 = null;
                HelpPresenter.this.helpView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.helpDisposable != null && !this.helpDisposable.isDisposed()) {
            this.helpDisposable.dispose();
        }
    }
}
