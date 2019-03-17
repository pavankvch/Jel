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

public class HostCancelledPropertiesActivity_ViewBinding implements Unbinder {
    private HostCancelledPropertiesActivity target;
    private View view2131362710;

    @UiThread
    public HostCancelledPropertiesActivity_ViewBinding(HostCancelledPropertiesActivity hostCancelledPropertiesActivity) {
        this(hostCancelledPropertiesActivity, hostCancelledPropertiesActivity.getWindow().getDecorView());
    }

    @UiThread
    public HostCancelledPropertiesActivity_ViewBinding(final HostCancelledPropertiesActivity hostCancelledPropertiesActivity, View view) {
        this.target = hostCancelledPropertiesActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_close, "field 'closeTextView' and method 'clickOnClose'");
        hostCancelledPropertiesActivity.closeTextView = (TextView) Utils.castView(findRequiredView, R.id.tv_close, "field 'closeTextView'", TextView.class);
        this.view2131362710 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostCancelledPropertiesActivity.clickOnClose();
            }
        });
        hostCancelledPropertiesActivity.norecordsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.norecordsTv, "field 'norecordsTv'", TextView.class);
        hostCancelledPropertiesActivity.backArrowIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.back_arrow_IV, "field 'backArrowIV'", ImageView.class);
        hostCancelledPropertiesActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        hostCancelledPropertiesActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        hostCancelledPropertiesActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        hostCancelledPropertiesActivity.layoutTotalCount = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_total_count, "field 'layoutTotalCount'", RelativeLayout.class);
        hostCancelledPropertiesActivity.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'titleTextView'", TextView.class);
        hostCancelledPropertiesActivity.countTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_count, "field 'countTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        HostCancelledPropertiesActivity hostCancelledPropertiesActivity = this.target;
        if (hostCancelledPropertiesActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hostCancelledPropertiesActivity.closeTextView = null;
        hostCancelledPropertiesActivity.norecordsTv = null;
        hostCancelledPropertiesActivity.backArrowIV = null;
        hostCancelledPropertiesActivity.headingTv = null;
        hostCancelledPropertiesActivity.recyclerView = null;
        hostCancelledPropertiesActivity.swipeContainer = null;
        hostCancelledPropertiesActivity.layoutTotalCount = null;
        hostCancelledPropertiesActivity.titleTextView = null;
        hostCancelledPropertiesActivity.countTextView = null;
        this.view2131362710.setOnClickListener(null);
        this.view2131362710 = null;
    }
}
