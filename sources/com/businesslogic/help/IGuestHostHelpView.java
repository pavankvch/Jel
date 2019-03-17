package com.businesslogic.help;

import com.businesslogic.framework.IBaseView;
import com.data.help.FaqCombinedResponse;

public interface IGuestHostHelpView extends IBaseView {
    void onError(String str, int i);

    void onSuccess(FaqCombinedResponse faqCombinedResponse);
}
