package com.businesslogic.updateprofile;

import com.businesslogic.framework.IBaseView;
import com.data.updateprofile.UpdateProfileResponse;
import com.data.utils.APIError;

public interface IUpdateProfileView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess();

    void onSuccess(UpdateProfileResponse updateProfileResponse);
}
