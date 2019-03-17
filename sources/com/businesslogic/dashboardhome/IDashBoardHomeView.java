package com.businesslogic.dashboardhome;

import com.businesslogic.framework.IBaseView;
import com.data.dashboardhome.PropertyItem;
import com.data.searchtoplocalities.Locality;
import java.util.List;

public interface IDashBoardHomeView extends IBaseView {
    void onError(String str, int i);

    void setLabelsList(List<String> list);

    void setLocalities(List<Locality> list);

    void setSectionProperties(List<PropertyItem> list, boolean z);

    void showInboxCount(int i, int i2);

    void showSwipeToRefresh(boolean z);
}
