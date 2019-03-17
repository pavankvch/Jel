package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.NonSwipeableViewPager;

public class DashboardHostBookingsFragment_ViewBinding implements Unbinder {
    private DashboardHostBookingsFragment target;

    @UiThread
    public DashboardHostBookingsFragment_ViewBinding(DashboardHostBookingsFragment dashboardHostBookingsFragment, View view) {
        this.target = dashboardHostBookingsFragment;
        dashboardHostBookingsFragment.toolbar = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.toolbar, "field 'toolbar'", LinearLayout.class);
        dashboardHostBookingsFragment.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        dashboardHostBookingsFragment.viewpager = (NonSwipeableViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", NonSwipeableViewPager.class);
        dashboardHostBookingsFragment.tabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'tabs'", TabLayout.class);
    }

    @CallSuper
    public void unbind() {
        DashboardHostBookingsFragment dashboardHostBookingsFragment = this.target;
        if (dashboardHostBookingsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashboardHostBookingsFragment.toolbar = null;
        dashboardHostBookingsFragment.swipeContainer = null;
        dashboardHostBookingsFragment.viewpager = null;
        dashboardHostBookingsFragment.tabs = null;
    }
}
