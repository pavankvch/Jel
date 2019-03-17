package com.businesslogic.propertylisting;

import com.businesslogic.framework.IBaseView;
import com.data.propertylisting.PropertyListingResponse;
import com.data.utils.APIError;

public interface PropertListingView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(PropertyListingResponse propertyListingResponse);

    void showSwipeToRefresh(boolean z);
}
