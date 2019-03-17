package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;
import com.jelsat.widgets.PriceRangeSeekbar;
import com.jelsat.widgets.SegmentedGroup;

public class FiltersActivity_ViewBinding implements Unbinder {
    private FiltersActivity target;
    private View view2131361872;
    private View view2131361881;
    private View view2131361959;
    private View view2131362218;
    private View view2131362222;
    private View view2131362256;
    private View view2131362509;

    @UiThread
    public FiltersActivity_ViewBinding(FiltersActivity filtersActivity) {
        this(filtersActivity, filtersActivity.getWindow().getDecorView());
    }

    @UiThread
    public FiltersActivity_ViewBinding(final FiltersActivity filtersActivity, View view) {
        this.target = filtersActivity;
        filtersActivity.filterScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.filter_scrollView, "field 'filterScrollView'", NestedScrollView.class);
        filtersActivity.aminitiesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.aminities_recyclerview, "field 'aminitiesRecyclerView'", RecyclerView.class);
        filtersActivity.houseRulesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.house_rules_recyclerview, "field 'houseRulesRecyclerView'", RecyclerView.class);
        filtersActivity.cancellationPolicyRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.cancellation_policy_recyclerview, "field 'cancellationPolicyRecyclerView'", RecyclerView.class);
        filtersActivity.filterImageRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.filter_image_recyclerview, "field 'filterImageRecyclerView'", RecyclerView.class);
        filtersActivity.rangeSeekbar = (PriceRangeSeekbar) Utils.findRequiredViewAsType(view, R.id.rangeSeekbar, "field 'rangeSeekbar'", PriceRangeSeekbar.class);
        filtersActivity.priceTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.price_TV, "field 'priceTextView'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.aminities_more_TV, "field 'amenitiesMoreTV' and method 'clickOnAmenitiesMore'");
        filtersActivity.amenitiesMoreTV = (TextView) Utils.castView(findRequiredView, R.id.aminities_more_TV, "field 'amenitiesMoreTV'", TextView.class);
        this.view2131361872 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                filtersActivity.clickOnAmenitiesMore();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.house_rules_more_TV, "field 'houseRulesMoreTV' and method 'clickOnHouseRulesMoreTV'");
        filtersActivity.houseRulesMoreTV = (TextView) Utils.castView(findRequiredView, R.id.house_rules_more_TV, "field 'houseRulesMoreTV'", TextView.class);
        this.view2131362222 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                filtersActivity.clickOnHouseRulesMoreTV();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.instant_booking_type, "field 'instantBookingType' and method 'clickOnInstantBookingType'");
        filtersActivity.instantBookingType = (CheckBox) Utils.castView(findRequiredView, R.id.instant_booking_type, "field 'instantBookingType'", CheckBox.class);
        this.view2131362256 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                filtersActivity.clickOnInstantBookingType();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.hours_booking_type, "field 'hoursBookingType' and method 'clickOnHoursBookingType'");
        filtersActivity.hoursBookingType = (CheckBox) Utils.castView(findRequiredView, R.id.hours_booking_type, "field 'hoursBookingType'", CheckBox.class);
        this.view2131362218 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                filtersActivity.clickOnHoursBookingType();
            }
        });
        filtersActivity.roomsRadioGroup = (SegmentedGroup) Utils.findRequiredViewAsType(view, R.id.rooms_radio_group, "field 'roomsRadioGroup'", SegmentedGroup.class);
        filtersActivity.singleBedsRadioGroup = (SegmentedGroup) Utils.findRequiredViewAsType(view, R.id.single_beds_radio_group, "field 'singleBedsRadioGroup'", SegmentedGroup.class);
        filtersActivity.doubleBedsRadioGroup = (SegmentedGroup) Utils.findRequiredViewAsType(view, R.id.double_beds_radio_group, "field 'doubleBedsRadioGroup'", SegmentedGroup.class);
        findRequiredView = Utils.findRequiredView(view, R.id.apply_filter_button, "field 'applyFilterButton' and method 'clickOnApplyFilter'");
        filtersActivity.applyFilterButton = (FancyButton) Utils.castView(findRequiredView, R.id.apply_filter_button, "field 'applyFilterButton'", FancyButton.class);
        this.view2131361881 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                filtersActivity.clickOnApplyFilter();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.cancel_TV, "method 'clickOnCancel'");
        this.view2131361959 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                filtersActivity.clickOnCancel();
            }
        });
        view = Utils.findRequiredView(view, R.id.reset_button, "method 'clickOnReset'");
        this.view2131362509 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                filtersActivity.clickOnReset();
            }
        });
    }

    @CallSuper
    public void unbind() {
        FiltersActivity filtersActivity = this.target;
        if (filtersActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        filtersActivity.filterScrollView = null;
        filtersActivity.aminitiesRecyclerView = null;
        filtersActivity.houseRulesRecyclerView = null;
        filtersActivity.cancellationPolicyRecyclerView = null;
        filtersActivity.filterImageRecyclerView = null;
        filtersActivity.rangeSeekbar = null;
        filtersActivity.priceTextView = null;
        filtersActivity.amenitiesMoreTV = null;
        filtersActivity.houseRulesMoreTV = null;
        filtersActivity.instantBookingType = null;
        filtersActivity.hoursBookingType = null;
        filtersActivity.roomsRadioGroup = null;
        filtersActivity.singleBedsRadioGroup = null;
        filtersActivity.doubleBedsRadioGroup = null;
        filtersActivity.applyFilterButton = null;
        this.view2131361872.setOnClickListener(null);
        this.view2131361872 = null;
        this.view2131362222.setOnClickListener(null);
        this.view2131362222 = null;
        this.view2131362256.setOnClickListener(null);
        this.view2131362256 = null;
        this.view2131362218.setOnClickListener(null);
        this.view2131362218 = null;
        this.view2131361881.setOnClickListener(null);
        this.view2131361881 = null;
        this.view2131361959.setOnClickListener(null);
        this.view2131361959 = null;
        this.view2131362509.setOnClickListener(null);
        this.view2131362509 = null;
    }
}
