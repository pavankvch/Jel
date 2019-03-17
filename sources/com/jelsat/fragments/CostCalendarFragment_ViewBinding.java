package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;
import com.squareup.timessquare.CalendarPickerView;

public class CostCalendarFragment_ViewBinding implements Unbinder {
    private CostCalendarFragment target;
    private View view2131362010;
    private View view2131362517;

    @UiThread
    public CostCalendarFragment_ViewBinding(final CostCalendarFragment costCalendarFragment, View view) {
        this.target = costCalendarFragment;
        costCalendarFragment.calendarPickerView = (CalendarPickerView) Utils.findRequiredViewAsType(view, R.id.calendarView, "field 'calendarPickerView'", CalendarPickerView.class);
        costCalendarFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        costCalendarFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        costCalendarFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        costCalendarFragment.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                costCalendarFragment.clickOnRetryButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.clear_TV, "field 'clear' and method 'clickOnCancel'");
        costCalendarFragment.clear = (TextView) Utils.castView(view, R.id.clear_TV, "field 'clear'", TextView.class);
        this.view2131362010 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                costCalendarFragment.clickOnCancel();
            }
        });
    }

    @CallSuper
    public void unbind() {
        CostCalendarFragment costCalendarFragment = this.target;
        if (costCalendarFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        costCalendarFragment.calendarPickerView = null;
        costCalendarFragment.noResultTV = null;
        costCalendarFragment.noResultLayout = null;
        costCalendarFragment.noResultImage = null;
        costCalendarFragment.retryButton = null;
        costCalendarFragment.clear = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131362010.setOnClickListener(null);
        this.view2131362010 = null;
    }
}
