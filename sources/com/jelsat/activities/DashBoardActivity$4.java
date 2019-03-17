package com.jelsat.activities;

import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;

class DashBoardActivity$4 implements OnPreDrawListener {
    final /* synthetic */ DashBoardActivity this$0;
    final /* synthetic */ ImageView val$imageView;

    DashBoardActivity$4(DashBoardActivity dashBoardActivity, ImageView imageView) {
        this.this$0 = dashBoardActivity;
        this.val$imageView = imageView;
    }

    public boolean onPreDraw() {
        this.val$imageView.getViewTreeObserver().removeOnPreDrawListener(this);
        DashBoardActivity.access$602(this.this$0, this.val$imageView.getMeasuredWidth());
        return true;
    }
}
