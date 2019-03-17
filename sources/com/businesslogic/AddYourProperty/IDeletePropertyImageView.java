package com.businesslogic.AddYourProperty;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;

public interface IDeletePropertyImageView extends IBaseView {
    void deleteSuccess(int i);

    void errorOccurred(APIError aPIError);
}
