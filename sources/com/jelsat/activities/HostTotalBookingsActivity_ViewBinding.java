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

public class HostTotalBookingsActivity_ViewBinding implements Unbinder {
    private HostTotalBookingsActivity target;
    private View view2131362710;

    @UiThread
    public HostTotalBookingsActivity_ViewBinding(HostTotalBookingsActivity hostTotalBookingsActivity) {
        this(hostTotalBookingsActivity, hostTotalBookingsActivity.getWindow().getDecorView());
    }

    @UiThread
    public HostTotalBookingsActivity_ViewBinding(final HostTotalBookingsActivity hostTotalBookingsActivity, View view) {
        this.target = hostTotalBookingsActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_close, "field 'closeTv' and method 'clickOnClose'");
        hostTotalBookingsActivity.closeTv = (TextView) Utils.castView(findRequiredView, R.id.tv_close, "field 'closeTv'", TextView.class);
        this.view2131362710 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostTotalBookingsActivity.clickOnClose();
            }
        });
        hostTotalBookingsActivity.norecordsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.norecordsTv, "field 'norecordsTv'", TextView.class);
        hostTotalBookingsActivity.backArrowIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.back_arrow_IV, "field 'backArrowIV'", ImageView.class);
        hostTotalBookingsActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        hostTotalBookingsActivity.layoutTotalCount = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_total_count, "field 'layoutTotalCount'", RelativeLayout.class);
        hostTotalBookingsActivity.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'titleTextView'", TextView.class);
        hostTotalBookingsActivity.countTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_count, "field 'countTextView'", TextView.class);
        hostTotalBookingsActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        hostTotalBookingsActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        HostTotalBookingsActivity hostTotalBookingsActivity = this.target;
        if (hostTotalBookingsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hostTotalBookingsActivity.closeTv = null;
        hostTotalBookingsActivity.norecordsTv = null;
        hostTotalBookingsActivity.backArrowIV = null;
        hostTotalBookingsActivity.headingTv = null;
        hostTotalBookingsActivity.layoutTotalCount = null;
        hostTotalBookingsActivity.titleTextView = null;
        hostTotalBookingsActivity.countTextView = null;
        hostTotalBookingsActivity.recyclerView = null;
        hostTotalBookingsActivity.swipeContainer = null;
        this.view2131362710.setOnClickListener(null);
        this.view2131362710 = null;
    }
}
