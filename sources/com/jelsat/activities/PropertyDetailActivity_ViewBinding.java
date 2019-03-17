package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.customclasses.ExpandableTextView;
import com.jelsat.customclasses.NonScrollExpandableListView;
import com.jelsat.widgets.FancyButton;

public class PropertyDetailActivity_ViewBinding implements Unbinder {
    private PropertyDetailActivity target;
    private View view2131361913;
    private View view2131362073;
    private View view2131362341;
    private View view2131362385;
    private View view2131362465;
    private View view2131362480;
    private View view2131362517;
    private View view2131362578;
    private View view2131362581;
    private View view2131362707;
    private View view2131362713;
    private View view2131362767;

    @UiThread
    public PropertyDetailActivity_ViewBinding(PropertyDetailActivity propertyDetailActivity) {
        this(propertyDetailActivity, propertyDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public PropertyDetailActivity_ViewBinding(final PropertyDetailActivity propertyDetailActivity, View view) {
        this.target = propertyDetailActivity;
        propertyDetailActivity.propertyDetailScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.property_detail_scrollView, "field 'propertyDetailScrollView'", NestedScrollView.class);
        propertyDetailActivity.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, R.id.toolbar, "field 'toolbar'", Toolbar.class);
        propertyDetailActivity.toolbar_property_price = (TextView) Utils.findRequiredViewAsType(view, R.id.toolbar_property_price, "field 'toolbar_property_price'", TextView.class);
        propertyDetailActivity.toolbar_property_name = (TextView) Utils.findRequiredViewAsType(view, R.id.toolbar_property_name, "field 'toolbar_property_name'", TextView.class);
        propertyDetailActivity.expandableListView = (NonScrollExpandableListView) Utils.findRequiredViewAsType(view, R.id.expandableListView, "field 'expandableListView'", NonScrollExpandableListView.class);
        propertyDetailActivity.propertyImageRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_image_recyclerView, "field 'propertyImageRecyclerView'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.property_host_image, "field 'propertyHostImage' and method 'clickOnHostProfileImage'");
        propertyDetailActivity.propertyHostImage = (ImageView) Utils.castView(findRequiredView, R.id.property_host_image, "field 'propertyHostImage'", ImageView.class);
        this.view2131362465 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnHostProfileImage();
            }
        });
        propertyDetailActivity.propertyNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_name_TV, "field 'propertyNameTV'", TextView.class);
        propertyDetailActivity.tv_reviewscount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reviewscount, "field 'tv_reviewscount'", TextView.class);
        propertyDetailActivity.propertyAddressTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_address_TV, "field 'propertyAddressTV'", TextView.class);
        propertyDetailActivity.propertyRatingBar = (AppCompatRatingBar) Utils.findRequiredViewAsType(view, R.id.property_rating_bar, "field 'propertyRatingBar'", AppCompatRatingBar.class);
        propertyDetailActivity.appRatingLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.app_rating_layout, "field 'appRatingLayout'", LinearLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.property_reviews, "field 'propertyReviews' and method 'clickOnRatingReviewsLayout'");
        propertyDetailActivity.propertyReviews = (TextView) Utils.castView(findRequiredView, R.id.property_reviews, "field 'propertyReviews'", TextView.class);
        this.view2131362480 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnRatingReviewsLayout();
            }
        });
        propertyDetailActivity.propertyHostName = (TextView) Utils.findRequiredViewAsType(view, R.id.property_host_name_TV, "field 'propertyHostName'", TextView.class);
        propertyDetailActivity.propertyType = (TextView) Utils.findRequiredViewAsType(view, R.id.property_type_TV, "field 'propertyType'", TextView.class);
        propertyDetailActivity.propertyAreaTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_area_TV, "field 'propertyAreaTV'", TextView.class);
        propertyDetailActivity.propertyConstraint = (TextView) Utils.findRequiredViewAsType(view, R.id.property_constraint_TV, "field 'propertyConstraint'", TextView.class);
        propertyDetailActivity.propertyBookingType = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.property_booking_type, "field 'propertyBookingType'", CustomTextView.class);
        propertyDetailActivity.propertyDetailRoomsGuestsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_detail_rooms_guests_recyclerView, "field 'propertyDetailRoomsGuestsRecyclerView'", RecyclerView.class);
        propertyDetailActivity.priceTV = (TextView) Utils.findRequiredViewAsType(view, R.id.price_TV, "field 'priceTV'", TextView.class);
        propertyDetailActivity.noOfPropertyImagesCount = (TextView) Utils.findRequiredViewAsType(view, R.id.no_of_images_count_TV, "field 'noOfPropertyImagesCount'", TextView.class);
        propertyDetailActivity.propertyDescription = (ExpandableTextView) Utils.findRequiredViewAsType(view, R.id.property_description_TV, "field 'propertyDescription'", ExpandableTextView.class);
        propertyDetailActivity.amenityGridRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.amenity_grid_recycler_view, "field 'amenityGridRecyclerView'", RecyclerView.class);
        propertyDetailActivity.amenitySection = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.amenity_section, "field 'amenitySection'", LinearLayout.class);
        propertyDetailActivity.propertyCheckInDateTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'propertyCheckInDateTV'", TextView.class);
        propertyDetailActivity.propertyCheckOutDateTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'propertyCheckOutDateTV'", TextView.class);
        propertyDetailActivity.propertyDurationTV = (FancyButton) Utils.findRequiredViewAsType(view, R.id.property_duration_TV, "field 'propertyDurationTV'", FancyButton.class);
        findRequiredView = Utils.findRequiredView(view, R.id.selected_guest_count, "field 'selectedGuestCountTV' and method 'clickOnNoOfGuest'");
        propertyDetailActivity.selectedGuestCountTV = (TextView) Utils.castView(findRequiredView, R.id.selected_guest_count, "field 'selectedGuestCountTV'", TextView.class);
        this.view2131362578 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnNoOfGuest();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.date_layout, "field 'dateLayout' and method 'clickOnDateLayout'");
        propertyDetailActivity.dateLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.date_layout, "field 'dateLayout'", LinearLayout.class);
        this.view2131362073 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnDateLayout();
            }
        });
        propertyDetailActivity.reviewsLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.reviews_layout, "field 'reviewsLayout'", LinearLayout.class);
        propertyDetailActivity.reviewRating = (RatingBar) Utils.findRequiredViewAsType(view, R.id.review_rating_bar, "field 'reviewRating'", RatingBar.class);
        propertyDetailActivity.reviewerImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.reviewer_image, "field 'reviewerImage'", ImageView.class);
        propertyDetailActivity.hostName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hostname, "field 'hostName'", TextView.class);
        propertyDetailActivity.reviwedDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reviewdate, "field 'reviwedDate'", TextView.class);
        propertyDetailActivity.reviewText = (ExpandableTextView) Utils.findRequiredViewAsType(view, R.id.tv_review, "field 'reviewText'", ExpandableTextView.class);
        propertyDetailActivity.policyTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_ploicy, "field 'policyTextView'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.tv_booking_policies, "field 'bookingPolicies' and method 'bookingPolicies'");
        propertyDetailActivity.bookingPolicies = (TextView) Utils.castView(findRequiredView, R.id.tv_booking_policies, "field 'bookingPolicies'", TextView.class);
        this.view2131362707 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.bookingPolicies();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.more_reviews_TV, "field 'moreReviewsTV' and method 'clickOnMoreReviews'");
        propertyDetailActivity.moreReviewsTV = (TextView) Utils.castView(findRequiredView, R.id.more_reviews_TV, "field 'moreReviewsTV'", TextView.class);
        this.view2131362341 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnMoreReviews();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.book_now_button, "field 'bookNowButton' and method 'clickOnBookNowButton'");
        propertyDetailActivity.bookNowButton = (FancyButton) Utils.castView(findRequiredView, R.id.book_now_button, "field 'bookNowButton'", FancyButton.class);
        this.view2131361913 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnBookNowButton();
            }
        });
        propertyDetailActivity.bookingLayout = (CardView) Utils.findRequiredViewAsType(view, R.id.booking_layout, "field 'bookingLayout'", CardView.class);
        propertyDetailActivity.youCantBookYourProperty = (TextView) Utils.findRequiredViewAsType(view, R.id.you_cant_book_your_property, "field 'youCantBookYourProperty'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.send_message, "field 'sendMessage' and method 'clickOnSendMessage'");
        propertyDetailActivity.sendMessage = (CustomTextView) Utils.castView(findRequiredView, R.id.send_message, "field 'sendMessage'", CustomTextView.class);
        this.view2131362581 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnSendMessage();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_report, "field 'reportPropertyTV' and method 'submitReport'");
        propertyDetailActivity.reportPropertyTV = (CustomTextView) Utils.castView(findRequiredView, R.id.tv_report, "field 'reportPropertyTV'", CustomTextView.class);
        this.view2131362767 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.submitReport();
            }
        });
        propertyDetailActivity.datesCompleteLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.date_complete_layout, "field 'datesCompleteLayout'", LinearLayout.class);
        propertyDetailActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        propertyDetailActivity.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        propertyDetailActivity.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        propertyDetailActivity.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        propertyDetailActivity.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnRetryButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_cost_calendar, "method 'clickOnCostCalendar'");
        this.view2131362713 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnCostCalendar();
            }
        });
        view = Utils.findRequiredView(view, R.id.on_map_button, "method 'clickOnMapButton'");
        this.view2131362385 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyDetailActivity.clickOnMapButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PropertyDetailActivity propertyDetailActivity = this.target;
        if (propertyDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyDetailActivity.propertyDetailScrollView = null;
        propertyDetailActivity.toolbar = null;
        propertyDetailActivity.toolbar_property_price = null;
        propertyDetailActivity.toolbar_property_name = null;
        propertyDetailActivity.expandableListView = null;
        propertyDetailActivity.propertyImageRecyclerView = null;
        propertyDetailActivity.propertyHostImage = null;
        propertyDetailActivity.propertyNameTV = null;
        propertyDetailActivity.tv_reviewscount = null;
        propertyDetailActivity.propertyAddressTV = null;
        propertyDetailActivity.propertyRatingBar = null;
        propertyDetailActivity.appRatingLayout = null;
        propertyDetailActivity.propertyReviews = null;
        propertyDetailActivity.propertyHostName = null;
        propertyDetailActivity.propertyType = null;
        propertyDetailActivity.propertyAreaTV = null;
        propertyDetailActivity.propertyConstraint = null;
        propertyDetailActivity.propertyBookingType = null;
        propertyDetailActivity.propertyDetailRoomsGuestsRecyclerView = null;
        propertyDetailActivity.priceTV = null;
        propertyDetailActivity.noOfPropertyImagesCount = null;
        propertyDetailActivity.propertyDescription = null;
        propertyDetailActivity.amenityGridRecyclerView = null;
        propertyDetailActivity.amenitySection = null;
        propertyDetailActivity.propertyCheckInDateTV = null;
        propertyDetailActivity.propertyCheckOutDateTV = null;
        propertyDetailActivity.propertyDurationTV = null;
        propertyDetailActivity.selectedGuestCountTV = null;
        propertyDetailActivity.dateLayout = null;
        propertyDetailActivity.reviewsLayout = null;
        propertyDetailActivity.reviewRating = null;
        propertyDetailActivity.reviewerImage = null;
        propertyDetailActivity.hostName = null;
        propertyDetailActivity.reviwedDate = null;
        propertyDetailActivity.reviewText = null;
        propertyDetailActivity.policyTextView = null;
        propertyDetailActivity.bookingPolicies = null;
        propertyDetailActivity.moreReviewsTV = null;
        propertyDetailActivity.bookNowButton = null;
        propertyDetailActivity.bookingLayout = null;
        propertyDetailActivity.youCantBookYourProperty = null;
        propertyDetailActivity.sendMessage = null;
        propertyDetailActivity.reportPropertyTV = null;
        propertyDetailActivity.datesCompleteLayout = null;
        propertyDetailActivity.swipeContainer = null;
        propertyDetailActivity.noResultTV = null;
        propertyDetailActivity.noResultLayout = null;
        propertyDetailActivity.noResultImage = null;
        propertyDetailActivity.retryButton = null;
        this.view2131362465.setOnClickListener(null);
        this.view2131362465 = null;
        this.view2131362480.setOnClickListener(null);
        this.view2131362480 = null;
        this.view2131362578.setOnClickListener(null);
        this.view2131362578 = null;
        this.view2131362073.setOnClickListener(null);
        this.view2131362073 = null;
        this.view2131362707.setOnClickListener(null);
        this.view2131362707 = null;
        this.view2131362341.setOnClickListener(null);
        this.view2131362341 = null;
        this.view2131361913.setOnClickListener(null);
        this.view2131361913 = null;
        this.view2131362581.setOnClickListener(null);
        this.view2131362581 = null;
        this.view2131362767.setOnClickListener(null);
        this.view2131362767 = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131362713.setOnClickListener(null);
        this.view2131362713 = null;
        this.view2131362385.setOnClickListener(null);
        this.view2131362385 = null;
    }
}
