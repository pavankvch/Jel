package com.businesslogic.AddYourProperty;

import com.businesslogic.framework.ErrorCodes;
import com.data.addproperty.FormData;
import com.data.addproperty.ImageUploadResponse;
import com.data.retrofit.APIService;
import com.data.utils.APIError;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.gson.Gson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.MediaType;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class AddPropertyImagePresenter {
    private Disposable addPropertyImageDisposable;
    private IAddPropertyImageView addPropertyImageView;
    private APIService apiService;

    public AddPropertyImagePresenter(IAddPropertyImageView iAddPropertyImageView, APIService aPIService) {
        this.addPropertyImageView = iAddPropertyImageView;
        this.apiService = aPIService;
    }

    public void addImageToServer(File file, FormData formData, final String str, final int i) {
        this.addPropertyImageView.showProgressDialog("");
        formData = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(formData));
        if (file != null) {
            file = Part.createFormData(MessengerShareContentUtility.MEDIA_IMAGE, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        } else {
            file = null;
        }
        this.addPropertyImageDisposable = this.apiService.uploadPropertyimage(file, formData).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ImageUploadResponse>() {
            public void accept(ImageUploadResponse imageUploadResponse) throws Exception {
                AddPropertyImagePresenter.this.addPropertyImageView.dismissProgress();
                if (str.equalsIgnoreCase("Featured")) {
                    AddPropertyImagePresenter.this.addPropertyImageView.onFeatureimageUploaded(imageUploadResponse);
                } else {
                    AddPropertyImagePresenter.this.addPropertyImageView.onOtherImageUploaded(imageUploadResponse, i);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                AddPropertyImagePresenter.this.addPropertyImageView.dismissProgress();
                th.printStackTrace();
                if (th instanceof HttpException) {
                    AddPropertyImagePresenter.this.addPropertyImageView.onError(AddPropertyImagePresenter.this.getApiError(((HttpException) th).response().errorBody()), ErrorCodes.API_ERROR, i);
                } else if (th instanceof SocketTimeoutException) {
                    AddPropertyImagePresenter.this.addPropertyImageView.onError(null, ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE, i);
                } else if ((th instanceof IOException) != null) {
                    AddPropertyImagePresenter.this.addPropertyImageView.onError(null, 511, i);
                } else {
                    AddPropertyImagePresenter.this.addPropertyImageView.onError(null, ErrorCodes.INTERNAL_SERVER_ERROR, i);
                }
            }
        });
    }

    private APIError getApiError(ResponseBody responseBody) {
        try {
            return (APIError) new Gson().fromJson(responseBody.string(), APIError.class);
        } catch (ResponseBody responseBody2) {
            responseBody2.printStackTrace();
            APIError aPIError = new APIError();
            aPIError.setSeken_errors(responseBody2.getMessage());
            return aPIError;
        }
    }

    public void unSubscribeDisposables() {
        if (this.addPropertyImageDisposable != null && !this.addPropertyImageDisposable.isDisposed()) {
            this.addPropertyImageDisposable.dispose();
        }
    }
}
