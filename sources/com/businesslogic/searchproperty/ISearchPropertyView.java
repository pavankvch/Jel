package com.businesslogic.searchproperty;

import com.businesslogic.framework.IBaseView;
import com.data.searchproperty.SearchPropertyResponse;
import com.data.utils.APIError;

public interface ISearchPropertyView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccessResponse(SearchPropertyResponse searchPropertyResponse);
}
