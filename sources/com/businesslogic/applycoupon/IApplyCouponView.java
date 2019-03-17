package com.businesslogic.applycoupon;

import com.businesslogic.framework.IBaseView;
import com.data.applycoupon.ApplyCouponRequest;
import com.data.applycoupon.ApplyCouponResponse;
import com.data.utils.APIError;

public interface IApplyCouponView extends IBaseView {
    void onError(APIError aPIError);

    void onSuccess(ApplyCouponResponse applyCouponResponse, ApplyCouponRequest applyCouponRequest);
}
