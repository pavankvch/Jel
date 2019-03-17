package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class SortDialogFragment_ViewBinding implements Unbinder {
    private SortDialogFragment target;
    private View view2131362104;

    @UiThread
    public SortDialogFragment_ViewBinding(final SortDialogFragment sortDialogFragment, View view) {
        this.target = sortDialogFragment;
        sortDialogFragment.sortRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.sort_radio_group, "field 'sortRadioGroup'", RadioGroup.class);
        sortDialogFragment.sortRatingRadioButton = (RadioButton) Utils.findRequiredViewAsType(view, R.id.sort_rating, "field 'sortRatingRadioButton'", RadioButton.class);
        sortDialogFragment.highLowRadioButton = (AppCompatRadioButton) Utils.findRequiredViewAsType(view, R.id.high_low_price_radioButton, "field 'highLowRadioButton'", AppCompatRadioButton.class);
        sortDialogFragment.lowHighRadioButton = (AppCompatRadioButton) Utils.findRequiredViewAsType(view, R.id.low_high_price_radioButton, "field 'lowHighRadioButton'", AppCompatRadioButton.class);
        view = Utils.findRequiredView(view, R.id.done_TV, "method 'clickOnDone'");
        this.view2131362104 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sortDialogFragment.clickOnDone();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SortDialogFragment sortDialogFragment = this.target;
        if (sortDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sortDialogFragment.sortRadioGroup = null;
        sortDialogFragment.sortRatingRadioButton = null;
        sortDialogFragment.highLowRadioButton = null;
        sortDialogFragment.lowHighRadioButton = null;
        this.view2131362104.setOnClickListener(null);
        this.view2131362104 = null;
    }
}
