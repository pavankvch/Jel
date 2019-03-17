package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.squareup.timessquare.CalendarPickerView;

public class CalendarDatesAvailabilityFragment_ViewBinding implements Unbinder {
    private CalendarDatesAvailabilityFragment target;
    private View view2131362262;
    private View view2131362729;
    private View view2131362741;
    private View view2131362771;
    private View view2131362775;
    private View view2131362778;
    private View view2131362785;
    private View view2131362788;

    @UiThread
    public CalendarDatesAvailabilityFragment_ViewBinding(final CalendarDatesAvailabilityFragment calendarDatesAvailabilityFragment, View view) {
        this.target = calendarDatesAvailabilityFragment;
        calendarDatesAvailabilityFragment.spinner_nav = (Spinner) Utils.findRequiredViewAsType(view, R.id.spinner_nav, "field 'spinner_nav'", Spinner.class);
        calendarDatesAvailabilityFragment.propertyName = (TextView) Utils.findRequiredViewAsType(view, R.id.propertyName, "field 'propertyName'", TextView.class);
        calendarDatesAvailabilityFragment.descriptionTv1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_description_1, "field 'descriptionTv1'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_date_range, "field 'dateRangeIv' and method 'clickOnDateRangeImage'");
        calendarDatesAvailabilityFragment.dateRangeIv = (ImageView) Utils.castView(findRequiredView, R.id.iv_date_range, "field 'dateRangeIv'", ImageView.class);
        this.view2131362262 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnDateRangeImage();
            }
        });
        calendarDatesAvailabilityFragment.calendarPickerView = (CalendarPickerView) Utils.findRequiredViewAsType(view, R.id.calendarView, "field 'calendarPickerView'", CalendarPickerView.class);
        calendarDatesAvailabilityFragment.daysLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_days, "field 'daysLayout'", LinearLayout.class);
        calendarDatesAvailabilityFragment.norecordsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.norecordsTv, "field 'norecordsTv'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.tv_sun, "method 'clickOnSunday'");
        this.view2131362775 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnSunday();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_mon, "method 'clickOnMonday'");
        this.view2131362741 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnMonday();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_tue, "method 'clickOnTuesday'");
        this.view2131362785 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnTuesday();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_wed, "method 'clickOnWednesday'");
        this.view2131362788 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnWednesday();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_thu, "method 'clickOnThursday'");
        this.view2131362778 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnThursday();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_fri, "method 'clickOnFriday'");
        this.view2131362729 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnFriday();
            }
        });
        view = Utils.findRequiredView(view, R.id.tv_sat, "method 'clickOnSaturday'");
        this.view2131362771 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesAvailabilityFragment.clickOnSaturday();
            }
        });
    }

    @CallSuper
    public void unbind() {
        CalendarDatesAvailabilityFragment calendarDatesAvailabilityFragment = this.target;
        if (calendarDatesAvailabilityFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        calendarDatesAvailabilityFragment.spinner_nav = null;
        calendarDatesAvailabilityFragment.propertyName = null;
        calendarDatesAvailabilityFragment.descriptionTv1 = null;
        calendarDatesAvailabilityFragment.dateRangeIv = null;
        calendarDatesAvailabilityFragment.calendarPickerView = null;
        calendarDatesAvailabilityFragment.daysLayout = null;
        calendarDatesAvailabilityFragment.norecordsTv = null;
        this.view2131362262.setOnClickListener(null);
        this.view2131362262 = null;
        this.view2131362775.setOnClickListener(null);
        this.view2131362775 = null;
        this.view2131362741.setOnClickListener(null);
        this.view2131362741 = null;
        this.view2131362785.setOnClickListener(null);
        this.view2131362785 = null;
        this.view2131362788.setOnClickListener(null);
        this.view2131362788 = null;
        this.view2131362778.setOnClickListener(null);
        this.view2131362778 = null;
        this.view2131362729.setOnClickListener(null);
        this.view2131362729 = null;
        this.view2131362771.setOnClickListener(null);
        this.view2131362771 = null;
    }
}
