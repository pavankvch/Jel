package com.businesslogic.createpassword;

import com.data.cretepassword.CreatePasswordRequest;
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

public class CreatePasswordPresenter {
    private static final String TAG = "CreatePasswordPresenter";
    private APIService apiService;
    private Disposable createPasswordDisposable;
    private ICreatePasswordView createPasswordView;

    public CreatePasswordPresenter(ICreatePasswordView iCreatePasswordView, APIService aPIService) {
        this.createPasswordView = iCreatePasswordView;
        this.apiService = aPIService;
    }

    public void doUpdatePassword(String str, CreatePasswordRequest createPasswordRequest) {
        this.createPasswordView.showProgressDialog(str);
        this.createPasswordDisposable = this.apiService.doUpdatePassword(createPasswordRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(@NonNull ResponseBody responseBody) throws Exception {
                CreatePasswordPresenter.this.createPasswordView.dismissProgress();
                CreatePasswordPresenter.this.createPasswordView.successResponse();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                CreatePasswordPresenter.this.createPasswordView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    CreatePasswordPresenter.this.createPasswordView.errorResponse(th2);
                }
                th2 = null;
                CreatePasswordPresenter.this.createPasswordView.errorResponse(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.createPasswordDisposable != null && !this.createPasswordDisposable.isDisposed()) {
            this.createPasswordDisposable.dispose();
        }
    }
}
