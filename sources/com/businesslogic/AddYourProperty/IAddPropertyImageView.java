package com.businesslogic.AddYourProperty;

import com.businesslogic.framework.IBaseView;
import com.data.addproperty.ImageUploadResponse;
import com.data.utils.APIError;

public interface IAddPropertyImageView extends IBaseView {
    void onError(APIError aPIError, int i, int i2);

    void onFeatureimageUploaded(ImageUploadResponse imageUploadResponse);

    void onOtherImageUploaded(ImageUploadResponse imageUploadResponse, int i);
}
