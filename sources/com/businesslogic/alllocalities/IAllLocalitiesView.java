package com.businesslogic.alllocalities;

import com.businesslogic.framework.IBaseView;
import com.data.alllocalities.AllLocalitiesResponse;
import com.data.utils.APIError;
import java.util.List;

public interface IAllLocalitiesView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(List<AllLocalitiesResponse> list);
}
