package com.businesslogic.propertycostcalendar;

import com.businesslogic.framework.IBaseView;
import com.data.propertycostcalendar.PropertyCostCalendarResponse;
import com.data.utils.APIError;

public interface IPropertyCostCalenderView extends IBaseView {
    void onCalendarUpdated(PropertyCostCalendarResponse propertyCostCalendarResponse);

    void onError(APIError aPIError);

    void onSuccess(PropertyCostCalendarResponse propertyCostCalendarResponse);
}
