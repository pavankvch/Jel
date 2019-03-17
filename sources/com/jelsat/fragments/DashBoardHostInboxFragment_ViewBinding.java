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

public class DashBoardHostInboxFragment_ViewBinding implements Unbinder {
    private DashBoardHostInboxFragment target;
    private View view2131362517;

    @UiThread
    public DashBoardHostInboxFragment_ViewBinding(final DashBoardHostInboxFragment dashBoardHostInboxFragment, View view) {
        this.target = dashBoardHostInboxFragment;
        dashBoardHostInboxFragment.viewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", ViewPager.class);
        dashBoardHostInboxFragment.inboxTabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'inboxTabs'", TabLayout.class);
        dashBoardHostInboxFragment.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        dashBoardHostInboxFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        dashBoardHostInboxFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        dashBoardHostInboxFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        view = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        dashBoardHostInboxFragment.retryButton = (FancyButton) Utils.castView(view, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashBoardHostInboxFragment.clickOnRetryButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DashBoardHostInboxFragment dashBoardHostInboxFragment = this.target;
        if (dashBoardHostInboxFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashBoardHostInboxFragment.viewpager = null;
        dashBoardHostInboxFragment.inboxTabs = null;
        dashBoardHostInboxFragment.swipeContainer = null;
        dashBoardHostInboxFragment.noResultTV = null;
        dashBoardHostInboxFragment.noResultLayout = null;
        dashBoardHostInboxFragment.noResultImage = null;
        dashBoardHostInboxFragment.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
    }
}
