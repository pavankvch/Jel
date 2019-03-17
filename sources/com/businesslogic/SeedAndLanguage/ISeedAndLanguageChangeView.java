package com.businesslogic.SeedAndLanguage;

import com.businesslogic.framework.IBaseView;
import com.data.SeedAndLanguage.SeedAndLanguageCombinedResponse;
import com.data.utils.APIError;

public interface ISeedAndLanguageChangeView extends IBaseView {
    void onError(APIError aPIError, int i);

    void onSuccess(SeedAndLanguageCombinedResponse seedAndLanguageCombinedResponse);
}
