package com.jelsat.activities;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

class PropertyDetailActivity$6 extends GridLayoutManager {
    final /* synthetic */ PropertyDetailActivity this$0;

    public boolean canScrollHorizontally() {
        return false;
    }

    public boolean canScrollVertically() {
        return false;
    }

    public boolean isSmoothScrollbarEnabled() {
        return false;
    }

    PropertyDetailActivity$6(PropertyDetailActivity propertyDetailActivity, Context context, int i) {
        this.this$0 = propertyDetailActivity;
        super(context, i);
    }
}
