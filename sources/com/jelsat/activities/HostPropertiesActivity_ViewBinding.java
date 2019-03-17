package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HostPropertiesActivity_ViewBinding implements Unbinder {
    private HostPropertiesActivity target;
    private View view2131362710;

    @UiThread
    public HostPropertiesActivity_ViewBinding(HostPropertiesActivity hostPropertiesActivity) {
        this(hostPropertiesActivity, hostPropertiesActivity.getWindow().getDecorView());
    }

    @UiThread
    public HostPropertiesActivity_ViewBinding(final HostPropertiesActivity hostPropertiesActivity, View view) {
        this.target = hostPropertiesActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_close, "field 'closeTv' and method 'clickOnClose'");
        hostPropertiesActivity.closeTv = (TextView) Utils.castView(findRequiredView, R.id.tv_close, "field 'closeTv'", TextView.class);
        this.view2131362710 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                hostPropertiesActivity.clickOnClose();
            }
        });
        hostPropertiesActivity.norecordsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.norecordsTv, "field 'norecordsTv'", TextView.class);
        hostPropertiesActivity.backArrowIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.back_arrow_IV, "field 'backArrowIV'", ImageView.class);
        hostPropertiesActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        hostPropertiesActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        hostPropertiesActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
    }

    @CallSuper
    public void unbind() {
        HostPropertiesActivity hostPropertiesActivity = this.target;
        if (hostPropertiesActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hostPropertiesActivity.closeTv = null;
        hostPropertiesActivity.norecordsTv = null;
        hostPropertiesActivity.backArrowIV = null;
        hostPropertiesActivity.headingTv = null;
        hostPropertiesActivity.recyclerView = null;
        hostPropertiesActivity.swipeContainer = null;
        this.view2131362710.setOnClickListener(null);
        this.view2131362710 = null;
    }
}
