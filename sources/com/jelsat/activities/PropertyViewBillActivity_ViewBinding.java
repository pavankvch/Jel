package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.customclasses.NonScrollExpandableListView;
import com.jelsat.widgets.FancyButton;

public class PropertyViewBillActivity_ViewBinding implements Unbinder {
    private PropertyViewBillActivity target;
    private View view2131361892;
    private View view2131361959;
    private View view2131362357;
    private View view2131362823;

    @UiThread
    public PropertyViewBillActivity_ViewBinding(PropertyViewBillActivity propertyViewBillActivity) {
        this(propertyViewBillActivity, propertyViewBillActivity.getWindow().getDecorView());
    }

    @UiThread
    public PropertyViewBillActivity_ViewBinding(final PropertyViewBillActivity propertyViewBillActivity, View view) {
        this.target = propertyViewBillActivity;
        propertyViewBillActivity.expandableListView = (NonScrollExpandableListView) Utils.findRequiredViewAsType(view, R.id.expandableListView, "field 'expandableListView'", NonScrollExpandableListView.class);
        propertyViewBillActivity.propertyBookingUserName = (TextView) Utils.findRequiredViewAsType(view, R.id.property_booking_user_name, "field 'propertyBookingUserName'", TextView.class);
        propertyViewBillActivity.propertyImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.property_image, "field 'propertyImage'", ImageView.class);
        propertyViewBillActivity.favourite = (ImageView) Utils.findRequiredViewAsType(view, R.id.favourite, "field 'favourite'", ImageView.class);
        propertyViewBillActivity.priceTV = (TextView) Utils.findRequiredViewAsType(view, R.id.price_TV, "field 'priceTV'", TextView.class);
        propertyViewBillActivity.distanceTV = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.distance_TV, "field 'distanceTV'", CustomTextView.class);
        propertyViewBillActivity.propertyNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_name_TV, "field 'propertyNameTV'", TextView.class);
        propertyViewBillActivity.propertyAddressTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_address_TV, "field 'propertyAddressTV'", TextView.class);
        propertyViewBillActivity.propertyCheckInDateTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_in_date, "field 'propertyCheckInDateTV'", TextView.class);
        propertyViewBillActivity.propertyCheckOutDateTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_check_out_date, "field 'propertyCheckOutDateTV'", TextView.class);
        propertyViewBillActivity.propertyDurationTV = (FancyButton) Utils.findRequiredViewAsType(view, R.id.property_duration_TV, "field 'propertyDurationTV'", FancyButton.class);
        propertyViewBillActivity.selectedGuestCountTV = (TextView) Utils.findRequiredViewAsType(view, R.id.selected_guest_count, "field 'selectedGuestCountTV'", TextView.class);
        propertyViewBillActivity.totalBillCardView = (CardView) Utils.findRequiredViewAsType(view, R.id.total_bill_cardView, "field 'totalBillCardView'", CardView.class);
        propertyViewBillActivity.payableAmountLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.payable_amount_layout, "field 'payableAmountLayout'", LinearLayout.class);
        propertyViewBillActivity.payableAmountTV = (TextView) Utils.findRequiredViewAsType(view, R.id.payable_amount_TV, "field 'payableAmountTV'", TextView.class);
        propertyViewBillActivity.paymentBreakDownLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.payment_breakdown_layout, "field 'paymentBreakDownLayout'", LinearLayout.class);
        propertyViewBillActivity.pricePerNightTv = (TextView) Utils.findRequiredViewAsType(view, R.id.price_per_night_TV, "field 'pricePerNightTv'", TextView.class);
        propertyViewBillActivity.pricePerNightAmountTV = (TextView) Utils.findRequiredViewAsType(view, R.id.price_per_night_amount_TV, "field 'pricePerNightAmountTV'", TextView.class);
        propertyViewBillActivity.addOnsLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.addOns_layout, "field 'addOnsLayout'", LinearLayout.class);
        propertyViewBillActivity.totalAmountTV = (TextView) Utils.findRequiredViewAsType(view, R.id.total_amount_TV, "field 'totalAmountTV'", TextView.class);
        propertyViewBillActivity.checkinCheckoutLay = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.checkin_checkout_lay, "field 'checkinCheckoutLay'", CustomTextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.next_button, "field 'nextButton' and method 'clickOnNext'");
        propertyViewBillActivity.nextButton = (FancyButton) Utils.castView(findRequiredView, R.id.next_button, "field 'nextButton'", FancyButton.class);
        this.view2131362357 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyViewBillActivity.clickOnNext();
            }
        });
        propertyViewBillActivity.startWeekDay = (TextView) Utils.findRequiredViewAsType(view, R.id.start_week_day, "field 'startWeekDay'", TextView.class);
        propertyViewBillActivity.endWeekDay = (TextView) Utils.findRequiredViewAsType(view, R.id.end_week_day, "field 'endWeekDay'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyViewBillActivity.clickOnBack();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.cancel_TV, "method 'clickOnCancel'");
        this.view2131361959 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyViewBillActivity.clickOnCancel();
            }
        });
        view = Utils.findRequiredView(view, R.id.view_bill_button, "method 'clickOnViewBillButton'");
        this.view2131362823 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyViewBillActivity.clickOnViewBillButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PropertyViewBillActivity propertyViewBillActivity = this.target;
        if (propertyViewBillActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyViewBillActivity.expandableListView = null;
        propertyViewBillActivity.propertyBookingUserName = null;
        propertyViewBillActivity.propertyImage = null;
        propertyViewBillActivity.favourite = null;
        propertyViewBillActivity.priceTV = null;
        propertyViewBillActivity.distanceTV = null;
        propertyViewBillActivity.propertyNameTV = null;
        propertyViewBillActivity.propertyAddressTV = null;
        propertyViewBillActivity.propertyCheckInDateTV = null;
        propertyViewBillActivity.propertyCheckOutDateTV = null;
        propertyViewBillActivity.propertyDurationTV = null;
        propertyViewBillActivity.selectedGuestCountTV = null;
        propertyViewBillActivity.totalBillCardView = null;
        propertyViewBillActivity.payableAmountLayout = null;
        propertyViewBillActivity.payableAmountTV = null;
        propertyViewBillActivity.paymentBreakDownLayout = null;
        propertyViewBillActivity.pricePerNightTv = null;
        propertyViewBillActivity.pricePerNightAmountTV = null;
        propertyViewBillActivity.addOnsLayout = null;
        propertyViewBillActivity.totalAmountTV = null;
        propertyViewBillActivity.checkinCheckoutLay = null;
        propertyViewBillActivity.nextButton = null;
        propertyViewBillActivity.startWeekDay = null;
        propertyViewBillActivity.endWeekDay = null;
        this.view2131362357.setOnClickListener(null);
        this.view2131362357 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131361959.setOnClickListener(null);
        this.view2131361959 = null;
        this.view2131362823.setOnClickListener(null);
        this.view2131362823 = null;
    }
}
