package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class DashboardSavedFragment_ViewBinding implements Unbinder {
    private DashboardSavedFragment target;
    private View view2131362517;

    @UiThread
    public DashboardSavedFragment_ViewBinding(final DashboardSavedFragment dashboardSavedFragment, View view) {
        this.target = dashboardSavedFragment;
        dashboardSavedFragment.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        dashboardSavedFragment.savedPropertiesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.saved_properties_recyclerview, "field 'savedPropertiesRecyclerView'", RecyclerView.class);
        dashboardSavedFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        dashboardSavedFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        dashboardSavedFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        view = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        dashboardSavedFragment.retryButton = (FancyButton) Utils.castView(view, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardSavedFragment.clickOnRetryButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DashboardSavedFragment dashboardSavedFragment = this.target;
        if (dashboardSavedFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashboardSavedFragment.swipeContainer = null;
        dashboardSavedFragment.savedPropertiesRecyclerView = null;
        dashboardSavedFragment.noResultTV = null;
        dashboardSavedFragment.noResultLayout = null;
        dashboardSavedFragment.noResultImage = null;
        dashboardSavedFragment.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
    }
}
