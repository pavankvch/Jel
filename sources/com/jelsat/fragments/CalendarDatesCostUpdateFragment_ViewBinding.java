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

public class CalendarDatesCostUpdateFragment_ViewBinding implements Unbinder {
    private CalendarDatesCostUpdateFragment target;
    private View view2131362262;
    private View view2131362729;
    private View view2131362741;
    private View view2131362771;
    private View view2131362775;
    private View view2131362778;
    private View view2131362785;
    private View view2131362788;

    @UiThread
    public CalendarDatesCostUpdateFragment_ViewBinding(final CalendarDatesCostUpdateFragment calendarDatesCostUpdateFragment, View view) {
        this.target = calendarDatesCostUpdateFragment;
        calendarDatesCostUpdateFragment.spinner_nav = (Spinner) Utils.findRequiredViewAsType(view, R.id.spinner_nav, "field 'spinner_nav'", Spinner.class);
        calendarDatesCostUpdateFragment.propertyName = (TextView) Utils.findRequiredViewAsType(view, R.id.propertyName, "field 'propertyName'", TextView.class);
        calendarDatesCostUpdateFragment.calendarPickerView = (CalendarPickerView) Utils.findRequiredViewAsType(view, R.id.calendarView, "field 'calendarPickerView'", CalendarPickerView.class);
        calendarDatesCostUpdateFragment.descriptionTv1 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_description_1, "field 'descriptionTv1'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.iv_date_range, "field 'dateRangeIv' and method 'clickOnDateRangeImage'");
        calendarDatesCostUpdateFragment.dateRangeIv = (ImageView) Utils.castView(findRequiredView, R.id.iv_date_range, "field 'dateRangeIv'", ImageView.class);
        this.view2131362262 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnDateRangeImage();
            }
        });
        calendarDatesCostUpdateFragment.daysLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_days, "field 'daysLayout'", LinearLayout.class);
        calendarDatesCostUpdateFragment.norecordsTv = (TextView) Utils.findRequiredViewAsType(view, R.id.norecordsTv, "field 'norecordsTv'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.tv_sun, "method 'clickOnSunDay'");
        this.view2131362775 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnSunDay();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_mon, "method 'clickOnMonDay'");
        this.view2131362741 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnMonDay();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_tue, "method 'clickOnTuesDay'");
        this.view2131362785 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnTuesDay();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_wed, "method 'clickOnWednesDay'");
        this.view2131362788 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnWednesDay();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_thu, "method 'clickOnThursDay'");
        this.view2131362778 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnThursDay();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_fri, "method 'clickOnFriDay'");
        this.view2131362729 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnFriDay();
            }
        });
        view = Utils.findRequiredView(view, R.id.tv_sat, "method 'clickOnSatDay'");
        this.view2131362771 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                calendarDatesCostUpdateFragment.clickOnSatDay();
            }
        });
    }

    @CallSuper
    public void unbind() {
        CalendarDatesCostUpdateFragment calendarDatesCostUpdateFragment = this.target;
        if (calendarDatesCostUpdateFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        calendarDatesCostUpdateFragment.spinner_nav = null;
        calendarDatesCostUpdateFragment.propertyName = null;
        calendarDatesCostUpdateFragment.calendarPickerView = null;
        calendarDatesCostUpdateFragment.descriptionTv1 = null;
        calendarDatesCostUpdateFragment.dateRangeIv = null;
        calendarDatesCostUpdateFragment.daysLayout = null;
        calendarDatesCostUpdateFragment.norecordsTv = null;
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
