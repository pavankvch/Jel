package com.businesslogic.AddYourProperty;

import com.data.addproperty.DeleteImagerequest;
import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class DeletePropertyImagePresenter {
    private APIService apiService;
    private Disposable deleteImageDisposable;
    private IDeletePropertyImageView iDeletePropertyImageView;

    public DeletePropertyImagePresenter(IDeletePropertyImageView iDeletePropertyImageView, APIService aPIService) {
        this.iDeletePropertyImageView = iDeletePropertyImageView;
        this.apiService = aPIService;
    }

    public void deletePropertyImage(String str, DeleteImagerequest deleteImagerequest, final int i) {
        this.iDeletePropertyImageView.showProgressDialog(str);
        this.deleteImageDisposable = this.apiService.deletePropertyImage(deleteImagerequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                DeletePropertyImagePresenter.this.iDeletePropertyImageView.dismissProgress();
                DeletePropertyImagePresenter.this.iDeletePropertyImageView.deleteSuccess(i);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                DeletePropertyImagePresenter.this.iDeletePropertyImageView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    DeletePropertyImagePresenter.this.iDeletePropertyImageView.errorOccurred(th2);
                }
                th2 = null;
                DeletePropertyImagePresenter.this.iDeletePropertyImageView.errorOccurred(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.deleteImageDisposable != null && !this.deleteImageDisposable.isDisposed()) {
            this.deleteImageDisposable.dispose();
        }
    }
}
