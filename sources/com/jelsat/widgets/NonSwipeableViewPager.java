package com.jelsat.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NonSwipeableViewPager extends ViewPager {
    private boolean enabled;

    public NonSwipeableViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return (!this.enabled || super.onInterceptTouchEvent(motionEvent) == null) ? null : true;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return (!this.enabled || super.onTouchEvent(motionEvent) == null) ? null : true;
    }

    public void setPagingEnabled(boolean z) {
        this.enabled = z;
    }
}
