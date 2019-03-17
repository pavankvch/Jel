package com.jelsat.activities;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

class FiltersActivity$4 implements OnGlobalLayoutListener {
    final /* synthetic */ FiltersActivity this$0;

    FiltersActivity$4(FiltersActivity filtersActivity) {
        this.this$0 = filtersActivity;
    }

    public void onGlobalLayout() {
        this.this$0.filterScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        FiltersActivity.access$300(this.this$0);
    }
}
