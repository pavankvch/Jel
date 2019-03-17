package com.businesslogic.searchtoplocalities;

import com.businesslogic.framework.IBaseView;
import com.data.searchtoplocalities.SearchTopLocality;
import com.data.utils.APIError;

public interface ISearchTopLocalitiesView extends IBaseView {
    void onErrorResponse(APIError aPIError);

    void onSuccessResponse(SearchTopLocality searchTopLocality);
}
