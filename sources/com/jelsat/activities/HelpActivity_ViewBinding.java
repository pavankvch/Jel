package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HelpActivity_ViewBinding implements Unbinder {
    private HelpActivity target;
    private View view2131361892;
    private View view2131362247;

    @UiThread
    public HelpActivity_ViewBinding(HelpActivity helpActivity) {
        this(helpActivity, helpActivity.getWindow().getDecorView());
    }

    @UiThread
    public HelpActivity_ViewBinding(final HelpActivity helpActivity, View view) {
        this.target = helpActivity;
        helpActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        helpActivity.viewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", ViewPager.class);
        helpActivity.helpTabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'helpTabs'", TabLayout.class);
        helpActivity.swipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'gotoBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                helpActivity.gotoBack();
            }
        });
        view = Utils.findRequiredView(view, R.id.img_contact, "method 'gotoSupport'");
        this.view2131362247 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                helpActivity.gotoSupport();
            }
        });
    }

    @CallSuper
    public void unbind() {
        HelpActivity helpActivity = this.target;
        if (helpActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        helpActivity.headingTv = null;
        helpActivity.viewpager = null;
        helpActivity.helpTabs = null;
        helpActivity.swipeRefreshLayout = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362247.setOnClickListener(null);
        this.view2131362247 = null;
    }
}
