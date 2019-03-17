package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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

public class DashboardGuestInboxFragment_ViewBinding implements Unbinder {
    private DashboardGuestInboxFragment target;
    private View view2131362517;

    @UiThread
    public DashboardGuestInboxFragment_ViewBinding(final DashboardGuestInboxFragment dashboardGuestInboxFragment, View view) {
        this.target = dashboardGuestInboxFragment;
        dashboardGuestInboxFragment.viewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", ViewPager.class);
        dashboardGuestInboxFragment.inboxTabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'inboxTabs'", TabLayout.class);
        dashboardGuestInboxFragment.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        dashboardGuestInboxFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        dashboardGuestInboxFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        dashboardGuestInboxFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        view = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        dashboardGuestInboxFragment.retryButton = (FancyButton) Utils.castView(view, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardGuestInboxFragment.clickOnRetryButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DashboardGuestInboxFragment dashboardGuestInboxFragment = this.target;
        if (dashboardGuestInboxFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashboardGuestInboxFragment.viewpager = null;
        dashboardGuestInboxFragment.inboxTabs = null;
        dashboardGuestInboxFragment.swipeContainer = null;
        dashboardGuestInboxFragment.noResultTV = null;
        dashboardGuestInboxFragment.noResultLayout = null;
        dashboardGuestInboxFragment.noResultImage = null;
        dashboardGuestInboxFragment.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
    }
}
