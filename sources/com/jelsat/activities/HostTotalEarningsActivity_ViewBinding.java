package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HostTotalEarningsActivity_ViewBinding implements Unbinder {
    private HostTotalEarningsActivity target;
    private View view2131362710;

    @UiThread
    public HostTotalEarningsActivity_ViewBinding(HostTotalEarningsActivity hostTotalEarningsActivity) {
        this(hostTotalEarningsActivity, hostTotalEarningsActivity.getWindow().getDecorView());
    }

    @UiThread
    public HostTotalEarningsActivity_ViewBinding(final HostTotalEarningsActivity hostTotalEarningsActivity, View view) {
        this.target = hostTotalEarningsActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_close, "field 'closeTv' and method 'clickOnClose'");
        hostTotalEarningsActivity.closeTv = (TextView) Utils.castView(findRequiredView, R.id.tv_close, "field 'closeTv'", TextView.class);
        this.view2131362710 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostTotalEarningsActivity.clickOnClose();
            }
        });
        hostTotalEarningsActivity.norecordsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.norecordsTv, "field 'norecordsTv'", TextView.class);
        hostTotalEarningsActivity.backArrowIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.back_arrow_IV, "field 'backArrowIV'", ImageView.class);
        hostTotalEarningsActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        hostTotalEarningsActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        hostTotalEarningsActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        hostTotalEarningsActivity.layoutTotalCount = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_total_count, "field 'layoutTotalCount'", RelativeLayout.class);
        hostTotalEarningsActivity.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'titleTextView'", TextView.class);
        hostTotalEarningsActivity.countTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_count, "field 'countTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        HostTotalEarningsActivity hostTotalEarningsActivity = this.target;
        if (hostTotalEarningsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hostTotalEarningsActivity.closeTv = null;
        hostTotalEarningsActivity.norecordsTv = null;
        hostTotalEarningsActivity.backArrowIV = null;
        hostTotalEarningsActivity.headingTv = null;
        hostTotalEarningsActivity.recyclerView = null;
        hostTotalEarningsActivity.swipeContainer = null;
        hostTotalEarningsActivity.layoutTotalCount = null;
        hostTotalEarningsActivity.titleTextView = null;
        hostTotalEarningsActivity.countTextView = null;
        this.view2131362710.setOnClickListener(null);
        this.view2131362710 = null;
    }
}
