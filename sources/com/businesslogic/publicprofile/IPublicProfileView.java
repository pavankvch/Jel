package com.businesslogic.publicprofile;

import com.businesslogic.framework.IBaseView;
import com.data.publicprofile.PublicProfileResponse;
import com.data.utils.APIError;

public interface IPublicProfileView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(PublicProfileResponse publicProfileResponse);

    void showSwipeToRefresh(boolean z);
}
