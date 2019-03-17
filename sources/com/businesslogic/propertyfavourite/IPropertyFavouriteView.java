package com.businesslogic.propertyfavourite;

import com.businesslogic.framework.IBaseView;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.utils.APIError;

public interface IPropertyFavouriteView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(PropertyFavouriteResponse propertyFavouriteResponse, int i);
}
