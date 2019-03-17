package com.businesslogic.help;

import com.businesslogic.framework.IBaseView;
import com.data.help.Answer;
import com.data.utils.APIError;
import java.util.List;

public interface IHelpView extends IBaseView {
    void onAnsweSuccess(List<Answer> list);

    void onError(APIError aPIError);
}
