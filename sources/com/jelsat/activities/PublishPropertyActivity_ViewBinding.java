package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.customclasses.ExpandableTextView;
import com.jelsat.customclasses.NonScrollExpandableListView;
import com.jelsat.widgets.FancyButton;

public class PublishPropertyActivity_ViewBinding implements Unbinder {
    private PublishPropertyActivity target;
    private View view2131361913;
    private View view2131362385;
    private View view2131362465;
    private View view2131362707;

    @UiThread
    public PublishPropertyActivity_ViewBinding(PublishPropertyActivity publishPropertyActivity) {
        this(publishPropertyActivity, publishPropertyActivity.getWindow().getDecorView());
    }

    @UiThread
    public PublishPropertyActivity_ViewBinding(final PublishPropertyActivity publishPropertyActivity, View view) {
        this.target = publishPropertyActivity;
        publishPropertyActivity.propertyDetailScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.property_detail_scrollView, "field 'propertyDetailScrollView'", NestedScrollView.class);
        publishPropertyActivity.propertyImageRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_image_recyclerView, "field 'propertyImageRecyclerView'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.property_host_image, "field 'propertyHostImage' and method 'clickOnHostProfileImage'");
        publishPropertyActivity.propertyHostImage = (ImageView) Utils.castView(findRequiredView, R.id.property_host_image, "field 'propertyHostImage'", ImageView.class);
        this.view2131362465 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publishPropertyActivity.clickOnHostProfileImage();
            }
        });
        publishPropertyActivity.propertyNameTV = (AppCompatTextView) Utils.findRequiredViewAsType(view, R.id.property_name_TV, "field 'propertyNameTV'", AppCompatTextView.class);
        publishPropertyActivity.propertyAddressTV = (AppCompatTextView) Utils.findRequiredViewAsType(view, R.id.property_address_TV, "field 'propertyAddressTV'", AppCompatTextView.class);
        publishPropertyActivity.propertyHostName = (TextView) Utils.findRequiredViewAsType(view, R.id.property_host_name_TV, "field 'propertyHostName'", TextView.class);
        publishPropertyActivity.propertyType = (TextView) Utils.findRequiredViewAsType(view, R.id.property_type_TV, "field 'propertyType'", TextView.class);
        publishPropertyActivity.propertyArea = (TextView) Utils.findRequiredViewAsType(view, R.id.property_area_TV, "field 'propertyArea'", TextView.class);
        publishPropertyActivity.propertyConstraint = (TextView) Utils.findRequiredViewAsType(view, R.id.property_constraint_TV, "field 'propertyConstraint'", TextView.class);
        publishPropertyActivity.propertyBookingType = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.property_booking_type, "field 'propertyBookingType'", CustomTextView.class);
        publishPropertyActivity.propertyDetailRoomsGuestsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_detail_rooms_guests_recyclerView, "field 'propertyDetailRoomsGuestsRecyclerView'", RecyclerView.class);
        publishPropertyActivity.priceTV = (TextView) Utils.findRequiredViewAsType(view, R.id.price_TV, "field 'priceTV'", TextView.class);
        publishPropertyActivity.landmark = (TextView) Utils.findRequiredViewAsType(view, R.id.landmark, "field 'landmark'", TextView.class);
        publishPropertyActivity.flatNo = (TextView) Utils.findRequiredViewAsType(view, R.id.flat_no, "field 'flatNo'", TextView.class);
        publishPropertyActivity.propertyDescription = (ExpandableTextView) Utils.findRequiredViewAsType(view, R.id.property_description_TV, "field 'propertyDescription'", ExpandableTextView.class);
        publishPropertyActivity.amenitySection = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.amenity_section, "field 'amenitySection'", LinearLayout.class);
        publishPropertyActivity.expandableListView = (NonScrollExpandableListView) Utils.findRequiredViewAsType(view, R.id.expandableListView, "field 'expandableListView'", NonScrollExpandableListView.class);
        publishPropertyActivity.amenityGridRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.amenity_grid_recycler_view, "field 'amenityGridRecyclerView'", RecyclerView.class);
        publishPropertyActivity.noOfPropertyImagesCount = (TextView) Utils.findRequiredViewAsType(view, R.id.no_of_images_count_TV, "field 'noOfPropertyImagesCount'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.book_now_button, "field 'book_now_button' and method 'publishButton'");
        publishPropertyActivity.book_now_button = (FancyButton) Utils.castView(findRequiredView, R.id.book_now_button, "field 'book_now_button'", FancyButton.class);
        this.view2131361913 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publishPropertyActivity.publishButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_booking_policies, "method 'bookingPolicies'");
        this.view2131362707 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publishPropertyActivity.bookingPolicies();
            }
        });
        view = Utils.findRequiredView(view, R.id.on_map_button, "method 'clickOnMapButton'");
        this.view2131362385 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                publishPropertyActivity.clickOnMapButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PublishPropertyActivity publishPropertyActivity = this.target;
        if (publishPropertyActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        publishPropertyActivity.propertyDetailScrollView = null;
        publishPropertyActivity.propertyImageRecyclerView = null;
        publishPropertyActivity.propertyHostImage = null;
        publishPropertyActivity.propertyNameTV = null;
        publishPropertyActivity.propertyAddressTV = null;
        publishPropertyActivity.propertyHostName = null;
        publishPropertyActivity.propertyType = null;
        publishPropertyActivity.propertyArea = null;
        publishPropertyActivity.propertyConstraint = null;
        publishPropertyActivity.propertyBookingType = null;
        publishPropertyActivity.propertyDetailRoomsGuestsRecyclerView = null;
        publishPropertyActivity.priceTV = null;
        publishPropertyActivity.landmark = null;
        publishPropertyActivity.flatNo = null;
        publishPropertyActivity.propertyDescription = null;
        publishPropertyActivity.amenitySection = null;
        publishPropertyActivity.expandableListView = null;
        publishPropertyActivity.amenityGridRecyclerView = null;
        publishPropertyActivity.noOfPropertyImagesCount = null;
        publishPropertyActivity.book_now_button = null;
        this.view2131362465.setOnClickListener(null);
        this.view2131362465 = null;
        this.view2131361913.setOnClickListener(null);
        this.view2131361913 = null;
        this.view2131362707.setOnClickListener(null);
        this.view2131362707 = null;
        this.view2131362385.setOnClickListener(null);
        this.view2131362385 = null;
    }
}
