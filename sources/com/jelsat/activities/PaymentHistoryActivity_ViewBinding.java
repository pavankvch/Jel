package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class PaymentHistoryActivity_ViewBinding implements Unbinder {
    private PaymentHistoryActivity target;
    private View view2131362710;

    @UiThread
    public PaymentHistoryActivity_ViewBinding(PaymentHistoryActivity paymentHistoryActivity) {
        this(paymentHistoryActivity, paymentHistoryActivity.getWindow().getDecorView());
    }

    @UiThread
    public PaymentHistoryActivity_ViewBinding(final PaymentHistoryActivity paymentHistoryActivity, View view) {
        this.target = paymentHistoryActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_close, "field 'closeTextView' and method 'clickOnBack'");
        paymentHistoryActivity.closeTextView = (TextView) Utils.castView(findRequiredView, R.id.tv_close, "field 'closeTextView'", TextView.class);
        this.view2131362710 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                paymentHistoryActivity.clickOnBack();
            }
        });
        paymentHistoryActivity.backArrowIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.back_arrow_IV, "field 'backArrowIV'", ImageView.class);
        paymentHistoryActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        paymentHistoryActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        paymentHistoryActivity.viewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", ViewPager.class);
        paymentHistoryActivity.tabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'tabs'", TabLayout.class);
    }

    @CallSuper
    public void unbind() {
        PaymentHistoryActivity paymentHistoryActivity = this.target;
        if (paymentHistoryActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        paymentHistoryActivity.closeTextView = null;
        paymentHistoryActivity.backArrowIV = null;
        paymentHistoryActivity.headingTv = null;
        paymentHistoryActivity.swipeContainer = null;
        paymentHistoryActivity.viewpager = null;
        paymentHistoryActivity.tabs = null;
        this.view2131362710.setOnClickListener(null);
        this.view2131362710 = null;
    }
}
