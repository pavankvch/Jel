package com.jelsat.payfort;

import android.app.Activity;
import com.businesslogic.framework.IBaseView;
import com.data.payfort.PayFortData;

public interface IPaymentRequestCallBackView extends IBaseView {
    Activity getActivityInstance();

    void onPaymentRequestResponse(int i, PayFortData payFortData);
}
