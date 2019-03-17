package com.jelsat.activities;

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

public class FeedbackActivity_ViewBinding implements Unbinder {
    private FeedbackActivity target;
    private View view2131361892;
    private View view2131362517;

    @UiThread
    public FeedbackActivity_ViewBinding(FeedbackActivity feedbackActivity) {
        this(feedbackActivity, feedbackActivity.getWindow().getDecorView());
    }

    @UiThread
    public FeedbackActivity_ViewBinding(final FeedbackActivity feedbackActivity, View view) {
        this.target = feedbackActivity;
        feedbackActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        feedbackActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        feedbackActivity.viewpager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewpager'", ViewPager.class);
        feedbackActivity.tabs = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs, "field 'tabs'", TabLayout.class);
        feedbackActivity.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        feedbackActivity.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        feedbackActivity.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        feedbackActivity.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                feedbackActivity.clickOnRetryButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'gotoBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                feedbackActivity.gotoBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        FeedbackActivity feedbackActivity = this.target;
        if (feedbackActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        feedbackActivity.headingTv = null;
        feedbackActivity.swipeContainer = null;
        feedbackActivity.viewpager = null;
        feedbackActivity.tabs = null;
        feedbackActivity.noResultTV = null;
        feedbackActivity.noResultLayout = null;
        feedbackActivity.noResultImage = null;
        feedbackActivity.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
