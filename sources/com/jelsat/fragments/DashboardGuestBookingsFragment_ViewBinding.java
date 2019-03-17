package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;
import com.jelsat.widgets.NonSwipeableViewPager;

public class DashboardGuestBookingsFragment_ViewBinding implements Unbinder {
    private DashboardGuestBookingsFragment target;
    private View view2131362517;

    @UiThread
    public DashboardGuestBookingsFragment_ViewBinding(final DashboardGuestBookingsFragment dashboardGuestBookingsFragment, View view) {
        this.target = dashboardGuestBookingsFragment;
        dashboardGuestBookingsFragment.toolbar = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.toolbar, "field 'toolbar'", LinearLayout.class);
        dashboardGuestBookingsFragment.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        dashboardGuestBookingsFragment.viewpager = (NonSwipeableViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", NonSwipeableViewPager.class);
        dashboardGuestBookingsFragment.tabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'tabs'", TabLayout.class);
        dashboardGuestBookingsFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        dashboardGuestBookingsFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        dashboardGuestBookingsFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        view = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        dashboardGuestBookingsFragment.retryButton = (FancyButton) Utils.castView(view, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardGuestBookingsFragment.clickOnRetryButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DashboardGuestBookingsFragment dashboardGuestBookingsFragment = this.target;
        if (dashboardGuestBookingsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashboardGuestBookingsFragment.toolbar = null;
        dashboardGuestBookingsFragment.swipeContainer = null;
        dashboardGuestBookingsFragment.viewpager = null;
        dashboardGuestBookingsFragment.tabs = null;
        dashboardGuestBookingsFragment.noResultTV = null;
        dashboardGuestBookingsFragment.noResultLayout = null;
        dashboardGuestBookingsFragment.noResultImage = null;
        dashboardGuestBookingsFragment.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
    }
}
