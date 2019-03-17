package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
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
import com.jelsat.compoundviews.PropertyLibraryView;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.widgets.FancyButton;

public class DashboardHomeFragment_ViewBinding implements Unbinder {
    private DashboardHomeFragment target;
    private View view2131362309;
    private View view2131362517;
    private View view2131362556;

    @UiThread
    public DashboardHomeFragment_ViewBinding(final DashboardHomeFragment dashboardHomeFragment, View view) {
        this.target = dashboardHomeFragment;
        dashboardHomeFragment.localitiesSectionTv = (TextView) Utils.findRequiredViewAsType(view, R.id.localities_section_tv, "field 'localitiesSectionTv'", TextView.class);
        dashboardHomeFragment.topCitiesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.top_cities_recyclerView, "field 'topCitiesRecyclerView'", RecyclerView.class);
        dashboardHomeFragment.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        dashboardHomeFragment.propertyLibrary = (PropertyLibraryView) Utils.findRequiredViewAsType(view, R.id.property_library, "field 'propertyLibrary'", PropertyLibraryView.class);
        dashboardHomeFragment.nestedScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.nestedScrollView, "field 'nestedScrollView'", NestedScrollView.class);
        dashboardHomeFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        dashboardHomeFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        dashboardHomeFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        dashboardHomeFragment.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardHomeFragment.clickOnRetryButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.search_TextView, "field 'searchTextview' and method 'clickOnSearch'");
        dashboardHomeFragment.searchTextview = (CustomTextView) Utils.castView(findRequiredView, R.id.search_TextView, "field 'searchTextview'", CustomTextView.class);
        this.view2131362556 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardHomeFragment.clickOnSearch();
            }
        });
        view = Utils.findRequiredView(view, R.id.localities_viewall_label, "method 'clickOnLocalitiesViewAll'");
        this.view2131362309 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardHomeFragment.clickOnLocalitiesViewAll();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DashboardHomeFragment dashboardHomeFragment = this.target;
        if (dashboardHomeFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashboardHomeFragment.localitiesSectionTv = null;
        dashboardHomeFragment.topCitiesRecyclerView = null;
        dashboardHomeFragment.swipeContainer = null;
        dashboardHomeFragment.propertyLibrary = null;
        dashboardHomeFragment.nestedScrollView = null;
        dashboardHomeFragment.noResultTV = null;
        dashboardHomeFragment.noResultLayout = null;
        dashboardHomeFragment.noResultImage = null;
        dashboardHomeFragment.retryButton = null;
        dashboardHomeFragment.searchTextview = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131362556.setOnClickListener(null);
        this.view2131362556 = null;
        this.view2131362309.setOnClickListener(null);
        this.view2131362309 = null;
    }
}
