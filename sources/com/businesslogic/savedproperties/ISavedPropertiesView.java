package com.businesslogic.savedproperties;

import com.businesslogic.framework.IBaseView;
import com.data.searchproperty.SearchProperty;
import java.util.List;

public interface ISavedPropertiesView extends IBaseView {
    void onError(String str, int i);

    void onSuccess(List<SearchProperty> list);

    void showSwipeToRefresh(boolean z);
}
