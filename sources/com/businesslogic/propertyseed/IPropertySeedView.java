package com.businesslogic.propertyseed;

import com.businesslogic.framework.IBaseView;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.utils.APIError;

public interface IPropertySeedView extends IBaseView {
    void onError(APIError aPIError, int i);

    void onSuccess(SeedResponse seedResponse);
}
