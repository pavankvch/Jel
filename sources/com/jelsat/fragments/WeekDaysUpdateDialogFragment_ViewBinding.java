package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomEditText;

public class WeekDaysUpdateDialogFragment_ViewBinding implements Unbinder {
    private WeekDaysUpdateDialogFragment target;
    private View view2131362246;
    private View view2131362730;
    private View view2131362780;
    private View view2131362800;

    @UiThread
    public WeekDaysUpdateDialogFragment_ViewBinding(final WeekDaysUpdateDialogFragment weekDaysUpdateDialogFragment, View view) {
        this.target = weekDaysUpdateDialogFragment;
        weekDaysUpdateDialogFragment.availabilityRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radioStatus, "field 'availabilityRadioGroup'", RadioGroup.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_fromDate, "field 'fromDateTv' and method 'clickOnFromDate'");
        weekDaysUpdateDialogFragment.fromDateTv = (CustomEditText) Utils.castView(findRequiredView, R.id.tv_fromDate, "field 'fromDateTv'", CustomEditText.class);
        this.view2131362730 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                weekDaysUpdateDialogFragment.clickOnFromDate();
            }
        });
        weekDaysUpdateDialogFragment.priceEt = (CustomEditText) Utils.findRequiredViewAsType(view, R.id.et_price, "field 'priceEt'", CustomEditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.tv_toDate, "field 'toDateTv' and method 'clickOnToDate'");
        weekDaysUpdateDialogFragment.toDateTv = (CustomEditText) Utils.castView(findRequiredView, R.id.tv_toDate, "field 'toDateTv'", CustomEditText.class);
        this.view2131362780 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                weekDaysUpdateDialogFragment.clickOnToDate();
            }
        });
        weekDaysUpdateDialogFragment.fromDateTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.from_date_text_input_layout, "field 'fromDateTextInputLayout'", TextInputLayout.class);
        weekDaysUpdateDialogFragment.toDateTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.to_date_text_input_layout, "field 'toDateTextInputLayout'", TextInputLayout.class);
        weekDaysUpdateDialogFragment.priceTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.price_text_input_layout, "field 'priceTextInputLayout'", TextInputLayout.class);
        weekDaysUpdateDialogFragment.parentView = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.parentView, "field 'parentView'", RelativeLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.update_button, "method 'clickOnUpdate'");
        this.view2131362800 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                weekDaysUpdateDialogFragment.clickOnUpdate();
            }
        });
        view = Utils.findRequiredView(view, R.id.img_close, "method 'clickOnClose'");
        this.view2131362246 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                weekDaysUpdateDialogFragment.clickOnClose();
            }
        });
    }

    @CallSuper
    public void unbind() {
        WeekDaysUpdateDialogFragment weekDaysUpdateDialogFragment = this.target;
        if (weekDaysUpdateDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        weekDaysUpdateDialogFragment.availabilityRadioGroup = null;
        weekDaysUpdateDialogFragment.fromDateTv = null;
        weekDaysUpdateDialogFragment.priceEt = null;
        weekDaysUpdateDialogFragment.toDateTv = null;
        weekDaysUpdateDialogFragment.fromDateTextInputLayout = null;
        weekDaysUpdateDialogFragment.toDateTextInputLayout = null;
        weekDaysUpdateDialogFragment.priceTextInputLayout = null;
        weekDaysUpdateDialogFragment.parentView = null;
        this.view2131362730.setOnClickListener(null);
        this.view2131362730 = null;
        this.view2131362780.setOnClickListener(null);
        this.view2131362780 = null;
        this.view2131362800.setOnClickListener(null);
        this.view2131362800 = null;
        this.view2131362246.setOnClickListener(null);
        this.view2131362246 = null;
    }
}
