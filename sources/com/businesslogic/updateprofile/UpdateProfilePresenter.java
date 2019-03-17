package com.businesslogic.updateprofile;

import com.data.retrofit.APIService;
import com.data.retrofit.RetrofitClient;
import com.data.updateprofile.UpdateProfileRequest;
import com.data.updateprofile.UpdateProfileResponse;
import com.data.utils.APIError;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class UpdateProfilePresenter {
    private APIService apiService;
    private Disposable updateProfileDisposable;
    private IUpdateProfileView updateProfileView;

    public UpdateProfilePresenter(IUpdateProfileView iUpdateProfileView, APIService aPIService) {
        this.updateProfileView = iUpdateProfileView;
        this.apiService = aPIService;
    }

    public void updateProfileDataWithimage(String str, File file, File file2, UpdateProfileRequest updateProfileRequest) {
        this.updateProfileView.showProgressDialog(str);
        str = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(updateProfileRequest));
        updateProfileRequest = null;
        if (file2 != null) {
            file2 = Part.createFormData("national_id_image", file2.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file2));
        } else {
            file2 = null;
        }
        if (file != null) {
            updateProfileRequest = Part.createFormData("profile_image", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
        }
        this.updateProfileDisposable = this.apiService.updateProfileWithImage(updateProfileRequest, file2, str).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<UpdateProfileResponse>() {
            public void accept(UpdateProfileResponse updateProfileResponse) throws Exception {
                UpdateProfilePresenter.this.updateProfileView.dismissProgress();
                UpdateProfilePresenter.this.updateProfileView.onSuccess(updateProfileResponse);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                UpdateProfilePresenter.this.updateProfileView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    UpdateProfilePresenter.this.updateProfileView.onError(th2);
                }
                th2 = null;
                UpdateProfilePresenter.this.updateProfileView.onError(th2);
            }
        });
    }

    public void verifyEmail(String str) {
        this.updateProfileView.showProgressDialog(str);
        this.updateProfileDisposable = this.apiService.verifyEmail().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ResponseBody>() {
            public void accept(ResponseBody responseBody) throws Exception {
                UpdateProfilePresenter.this.updateProfileView.dismissProgress();
                UpdateProfilePresenter.this.updateProfileView.onSuccess();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                UpdateProfilePresenter.this.updateProfileView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    try {
                        th = (APIError) RetrofitClient.getErrorConverter().convert(((HttpException) th).response().errorBody());
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                    UpdateProfilePresenter.this.updateProfileView.onError(th2);
                }
                th2 = null;
                UpdateProfilePresenter.this.updateProfileView.onError(th2);
            }
        });
    }

    public void unSubscribeDisposables() {
        if (this.updateProfileDisposable != null && !this.updateProfileDisposable.isDisposed()) {
            this.updateProfileDisposable.dispose();
        }
    }
}
