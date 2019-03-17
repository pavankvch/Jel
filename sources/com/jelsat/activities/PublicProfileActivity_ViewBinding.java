package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.ExpandableTextView;
import com.jelsat.widgets.FancyButton;

public class PublicProfileActivity_ViewBinding implements Unbinder {
    private PublicProfileActivity target;
    private View view2131361892;
    private View view2131362517;
    private View view2131362773;

    @UiThread
    public PublicProfileActivity_ViewBinding(PublicProfileActivity publicProfileActivity) {
        this(publicProfileActivity, publicProfileActivity.getWindow().getDecorView());
    }

    @UiThread
    public PublicProfileActivity_ViewBinding(final PublicProfileActivity publicProfileActivity, View view) {
        this.target = publicProfileActivity;
        publicProfileActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        publicProfileActivity.profileImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_profile, "field 'profileImage'", ImageView.class);
        publicProfileActivity.profileName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_profilename, "field 'profileName'", TextView.class);
        publicProfileActivity.memberSince = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_membersince, "field 'memberSince'", TextView.class);
        publicProfileActivity.about = (ExpandableTextView) Utils.findRequiredViewAsType(view, R.id.tv_about, "field 'about'", ExpandableTextView.class);
        publicProfileActivity.languages = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_languages, "field 'languages'", TextView.class);
        publicProfileActivity.reviewsCount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reviewscount, "field 'reviewsCount'", TextView.class);
        publicProfileActivity.verifiedInfo = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_verified_info_value, "field 'verifiedInfo'", TextView.class);
        publicProfileActivity.reviewsLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.reviews_layout, "field 'reviewsLayout'", LinearLayout.class);
        publicProfileActivity.reviewRating = (RatingBar) Utils.findRequiredViewAsType(view, R.id.review_rating_bar, "field 'reviewRating'", RatingBar.class);
        publicProfileActivity.reviewerImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.reviewer_image, "field 'reviewerImage'", ImageView.class);
        publicProfileActivity.hostName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hostname, "field 'hostName'", TextView.class);
        publicProfileActivity.reviwedDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reviewdate, "field 'reviwedDate'", TextView.class);
        publicProfileActivity.reviewText = (ExpandableTextView) Utils.findRequiredViewAsType(view, R.id.tv_review, "field 'reviewText'", ExpandableTextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_showmore, "field 'showMoreReviews' and method 'showMoreReviews'");
        publicProfileActivity.showMoreReviews = (FancyButton) Utils.castView(findRequiredView, R.id.tv_showmore, "field 'showMoreReviews'", FancyButton.class);
        this.view2131362773 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publicProfileActivity.showMoreReviews();
            }
        });
        publicProfileActivity.aboutLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.about_layout, "field 'aboutLayout'", LinearLayout.class);
        publicProfileActivity.spokenLanguagesLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.spoken_Languages_layout, "field 'spokenLanguagesLayout'", LinearLayout.class);
        publicProfileActivity.reviewsLinearLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.reviews_linearLayout, "field 'reviewsLinearLayout'", LinearLayout.class);
        publicProfileActivity.nestedScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.nestedScrollView, "field 'nestedScrollView'", NestedScrollView.class);
        publicProfileActivity.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        publicProfileActivity.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        publicProfileActivity.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "method 'clickOnRetryButton'");
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publicProfileActivity.clickOnRetryButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'goBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publicProfileActivity.goBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PublicProfileActivity publicProfileActivity = this.target;
        if (publicProfileActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        publicProfileActivity.swipeContainer = null;
        publicProfileActivity.profileImage = null;
        publicProfileActivity.profileName = null;
        publicProfileActivity.memberSince = null;
        publicProfileActivity.about = null;
        publicProfileActivity.languages = null;
        publicProfileActivity.reviewsCount = null;
        publicProfileActivity.verifiedInfo = null;
        publicProfileActivity.reviewsLayout = null;
        publicProfileActivity.reviewRating = null;
        publicProfileActivity.reviewerImage = null;
        publicProfileActivity.hostName = null;
        publicProfileActivity.reviwedDate = null;
        publicProfileActivity.reviewText = null;
        publicProfileActivity.showMoreReviews = null;
        publicProfileActivity.aboutLayout = null;
        publicProfileActivity.spokenLanguagesLayout = null;
        publicProfileActivity.reviewsLinearLayout = null;
        publicProfileActivity.nestedScrollView = null;
        publicProfileActivity.noResultTV = null;
        publicProfileActivity.noResultLayout = null;
        publicProfileActivity.noResultImage = null;
        this.view2131362773.setOnClickListener(null);
        this.view2131362773 = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
