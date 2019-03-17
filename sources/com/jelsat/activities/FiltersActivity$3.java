package com.jelsat.activities;

import android.graphics.Rect;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;

class FiltersActivity$3 implements OnScrollChangeListener {
    final /* synthetic */ FiltersActivity this$0;
    final /* synthetic */ Rect val$scrollBounds;

    FiltersActivity$3(FiltersActivity filtersActivity, Rect rect) {
        this.this$0 = filtersActivity;
        this.val$scrollBounds = rect;
    }

    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
        FiltersActivity.access$200(this.this$0, nestedScrollView, this.val$scrollBounds);
    }
}
