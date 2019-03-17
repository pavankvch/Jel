package com.businesslogic.PublishProperty;

import com.businesslogic.framework.IBaseView;
import com.data.publishproperty.PublishPropertyResponse;
import com.data.utils.APIError;

public interface IPublishProperty extends IBaseView {
    void onError(APIError aPIError);

    void onSuccessResponse(PublishPropertyResponse publishPropertyResponse);
}
