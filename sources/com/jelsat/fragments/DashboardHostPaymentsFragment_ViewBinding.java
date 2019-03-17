package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.NonSwipeableViewPager;

public class DashboardHostPaymentsFragment_ViewBinding implements Unbinder {
    private DashboardHostPaymentsFragment target;
    private View view2131362281;
    private View view2131362282;
    private View view2131362290;
    private View view2131362830;

    @UiThread
    public DashboardHostPaymentsFragment_ViewBinding(final DashboardHostPaymentsFragment dashboardHostPaymentsFragment, View view) {
        this.target = dashboardHostPaymentsFragment;
        dashboardHostPaymentsFragment.viewpager = (NonSwipeableViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", NonSwipeableViewPager.class);
        dashboardHostPaymentsFragment.paymentCategoriesTabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs_payment_categories, "field 'paymentCategoriesTabs'", TabLayout.class);
        dashboardHostPaymentsFragment.currencyText = (TextView) Utils.findRequiredViewAsType(view, R.id.currency_text, "field 'currencyText'", TextView.class);
        dashboardHostPaymentsFragment.totalBalanceTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_balance, "field 'totalBalanceTv'", TextView.class);
        dashboardHostPaymentsFragment.propertiesCountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_properties_count, "field 'propertiesCountTv'", TextView.class);
        dashboardHostPaymentsFragment.totalBookingsCountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_bookings_count, "field 'totalBookingsCountTv'", TextView.class);
        dashboardHostPaymentsFragment.totalCancellsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_cancells, "field 'totalCancellsTv'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.view_totalbookings, "method 'clickOnTotalBookings'");
        this.view2131362830 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardHostPaymentsFragment.clickOnTotalBookings();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.layout_properties, "method 'clickOnProperties'");
        this.view2131362290 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardHostPaymentsFragment.clickOnProperties();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.layout_cancelled, "method 'clickOnCancelled'");
        this.view2131362282 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardHostPaymentsFragment.clickOnCancelled();
            }
        });
        view = Utils.findRequiredView(view, R.id.layout_amount, "method 'clickOnAmount'");
        this.view2131362281 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashboardHostPaymentsFragment.clickOnAmount();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DashboardHostPaymentsFragment dashboardHostPaymentsFragment = this.target;
        if (dashboardHostPaymentsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashboardHostPaymentsFragment.viewpager = null;
        dashboardHostPaymentsFragment.paymentCategoriesTabs = null;
        dashboardHostPaymentsFragment.currencyText = null;
        dashboardHostPaymentsFragment.totalBalanceTv = null;
        dashboardHostPaymentsFragment.propertiesCountTv = null;
        dashboardHostPaymentsFragment.totalBookingsCountTv = null;
        dashboardHostPaymentsFragment.totalCancellsTv = null;
        this.view2131362830.setOnClickListener(null);
        this.view2131362830 = null;
        this.view2131362290.setOnClickListener(null);
        this.view2131362290 = null;
        this.view2131362282.setOnClickListener(null);
        this.view2131362282 = null;
        this.view2131362281.setOnClickListener(null);
        this.view2131362281 = null;
    }
}
