package com.jelsat.compoundviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.data.searchproperty.SearchProperty;
import java.util.List;

public class PropertyLibraryView extends LinearLayout {
    private Context context;

    public PropertyLibraryView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        setOrientation(1);
    }

    public void addStreamsSection(List<SearchProperty> list, String str, int i) {
        if (list != null) {
            View propertySectionView = new PropertySectionView(this.context, list, str, i);
            propertySectionView.setTag(String.valueOf(str));
            addView(propertySectionView);
        }
    }

    public void updateStreams(List<SearchProperty> list, String str) {
        str = findViewWithTag(String.valueOf(str));
        if (str != null && list != null) {
            ((PropertySectionView) str).setStreamsData(list);
        }
    }
}
