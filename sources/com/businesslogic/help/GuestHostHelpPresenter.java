package com.businesslogic.help;

import com.businesslogic.framework.ErrorCodes;
import com.data.help.Faq;
import com.data.help.FaqCombinedResponse;
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

public class GuestHostHelpPresenter {
    private APIService apiService;
    private Disposable helpDisposable;
    private IGuestHostHelpView helpView;

    public GuestHostHelpPresenter(IGuestHostHelpView iGuestHostHelpView, APIService aPIService) {
        this.helpView = iGuestHostHelpView;
        this.apiService = aPIService;
    }

    public void getGuestHostHelpCategories() {
        this.helpView.showLoading();
        this.helpDisposable = Single.zip(this.apiService.faqGuest().subscribeOn(Schedulers.io()), this.apiService.faqHost().subscribeOn(Schedulers.io()), new BiFunction<List<Faq>, List<Faq>, FaqCombinedResponse>() {
            public FaqCombinedResponse apply(List<Faq> list, List<Faq> list2) throws Exception {
                return new FaqCombinedResponse(list, list2);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<FaqCombinedResponse>() {
            public void accept(FaqCombinedResponse faqCombinedResponse) throws Exception {
                GuestHostHelpPresenter.this.helpView.dismissProgress();
                GuestHostHelpPresenter.this.helpView.onSuccess(faqCombinedResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                GuestHostHelpPresenter.this.helpView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    GuestHostHelpPresenter.this.helpView.onError(GuestHostHelpPresenter.this.getErrorMessage(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR);
                } else if (th instanceof SocketTimeoutException) {
                    GuestHostHelpPresenter.this.helpView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE);
                } else if ((th instanceof IOException) != null) {
                    GuestHostHelpPresenter.this.helpView.onError(null, 511);
                } else {
                    GuestHostHelpPresenter.this.helpView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR);
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
        if (this.helpDisposable != null && !this.helpDisposable.isDisposed()) {
            this.helpDisposable.dispose();
        }
    }
}
