package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;

public class SearchPropertyActivity_ViewBinding implements Unbinder {
    private SearchPropertyActivity target;
    private View view2131361892;
    private View view2131361920;
    private View view2131361922;
    private View view2131361923;
    private View view2131361925;
    private View view2131362073;
    private View view2131362201;
    private View view2131362310;
    private View view2131362471;

    @UiThread
    public SearchPropertyActivity_ViewBinding(SearchPropertyActivity searchPropertyActivity) {
        this(searchPropertyActivity, searchPropertyActivity.getWindow().getDecorView());
    }

    @UiThread
    public SearchPropertyActivity_ViewBinding(final SearchPropertyActivity searchPropertyActivity, View view) {
        this.target = searchPropertyActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.location_text, "field 'locationText' and method 'clickOnALLCities'");
        searchPropertyActivity.locationText = (TextView) Utils.castView(findRequiredView, R.id.location_text, "field 'locationText'", TextView.class);
        this.view2131362310 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnALLCities();
            }
        });
        searchPropertyActivity.checkInOutDate = (TextView) Utils.findRequiredViewAsType(view, R.id.check_in_out_date, "field 'checkInOutDate'", TextView.class);
        searchPropertyActivity.guestsTV = (TextView) Utils.findRequiredViewAsType(view, R.id.guests_TV, "field 'guestsTV'", TextView.class);
        searchPropertyActivity.propertyTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_TV, "field 'propertyTV'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.bottom_saved, "field 'bottomSaved' and method 'clickOnSaved'");
        searchPropertyActivity.bottomSaved = (CustomTextView) Utils.castView(findRequiredView, R.id.bottom_saved, "field 'bottomSaved'", CustomTextView.class);
        this.view2131361923 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnSaved();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.bottom_sort, "field 'bottomSort' and method 'clickOnSort'");
        searchPropertyActivity.bottomSort = (CustomTextView) Utils.castView(findRequiredView, R.id.bottom_sort, "field 'bottomSort'", CustomTextView.class);
        this.view2131361925 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnSort();
            }
        });
        searchPropertyActivity.toolbar_date = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.date, "field 'toolbar_date'", CustomTextView.class);
        searchPropertyActivity.toolbar_property = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.property, "field 'toolbar_property'", CustomTextView.class);
        searchPropertyActivity.toolbar_guest = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.guest, "field 'toolbar_guest'", CustomTextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.bottom_filter, "field 'bottomFilter' and method 'clickOnFilter'");
        searchPropertyActivity.bottomFilter = (LinearLayout) Utils.castView(findRequiredView, R.id.bottom_filter, "field 'bottomFilter'", LinearLayout.class);
        this.view2131361920 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnFilter();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.bottom_map, "field 'bottomMap' and method 'clickOnMap'");
        searchPropertyActivity.bottomMap = (CustomTextView) Utils.castView(findRequiredView, R.id.bottom_map, "field 'bottomMap'", CustomTextView.class);
        this.view2131361922 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnMap();
            }
        });
        searchPropertyActivity.badgeCount = (TextView) Utils.findRequiredViewAsType(view, R.id.fragment_count, "field 'badgeCount'", TextView.class);
        searchPropertyActivity.searchHead_main = Utils.findRequiredView(view, R.id.search_head_body, "field 'searchHead_main'");
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnBack(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.date_layout, "method 'clickOnDate'");
        this.view2131362073 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnDate();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.guest_layout, "method 'clickOnGuests'");
        this.view2131362201 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnGuests();
            }
        });
        view = Utils.findRequiredView(view, R.id.property_layout, "method 'clickOnProperty'");
        this.view2131362471 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                searchPropertyActivity.clickOnProperty();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SearchPropertyActivity searchPropertyActivity = this.target;
        if (searchPropertyActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchPropertyActivity.locationText = null;
        searchPropertyActivity.checkInOutDate = null;
        searchPropertyActivity.guestsTV = null;
        searchPropertyActivity.propertyTV = null;
        searchPropertyActivity.bottomSaved = null;
        searchPropertyActivity.bottomSort = null;
        searchPropertyActivity.toolbar_date = null;
        searchPropertyActivity.toolbar_property = null;
        searchPropertyActivity.toolbar_guest = null;
        searchPropertyActivity.bottomFilter = null;
        searchPropertyActivity.bottomMap = null;
        searchPropertyActivity.badgeCount = null;
        searchPropertyActivity.searchHead_main = null;
        this.view2131362310.setOnClickListener(null);
        this.view2131362310 = null;
        this.view2131361923.setOnClickListener(null);
        this.view2131361923 = null;
        this.view2131361925.setOnClickListener(null);
        this.view2131361925 = null;
        this.view2131361920.setOnClickListener(null);
        this.view2131361920 = null;
        this.view2131361922.setOnClickListener(null);
        this.view2131361922 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362073.setOnClickListener(null);
        this.view2131362073 = null;
        this.view2131362201.setOnClickListener(null);
        this.view2131362201 = null;
        this.view2131362471.setOnClickListener(null);
        this.view2131362471 = null;
    }
}
