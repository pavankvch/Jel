package com.jelsat.activities;

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

public class ReviewsActivity_ViewBinding implements Unbinder {
    private ReviewsActivity target;
    private View view2131361892;
    private View view2131362517;

    @UiThread
    public ReviewsActivity_ViewBinding(ReviewsActivity reviewsActivity) {
        this(reviewsActivity, reviewsActivity.getWindow().getDecorView());
    }

    @UiThread
    public ReviewsActivity_ViewBinding(final ReviewsActivity reviewsActivity, View view) {
        this.target = reviewsActivity;
        reviewsActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        reviewsActivity.tv_heading = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'tv_heading'", TextView.class);
        reviewsActivity.recyclerview_reviews = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerview_reviews, "field 'recyclerview_reviews'", RecyclerView.class);
        reviewsActivity.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        reviewsActivity.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        reviewsActivity.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        reviewsActivity.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                reviewsActivity.clickOnRetryButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'goBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                reviewsActivity.goBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ReviewsActivity reviewsActivity = this.target;
        if (reviewsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        reviewsActivity.swipeContainer = null;
        reviewsActivity.tv_heading = null;
        reviewsActivity.recyclerview_reviews = null;
        reviewsActivity.noResultTV = null;
        reviewsActivity.noResultLayout = null;
        reviewsActivity.noResultImage = null;
        reviewsActivity.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
