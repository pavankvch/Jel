package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.squareup.timessquare.CalendarPickerView;

public class DateFragment_ViewBinding implements Unbinder {
    private DateFragment target;

    @UiThread
    public DateFragment_ViewBinding(DateFragment dateFragment, View view) {
        this.target = dateFragment;
        dateFragment.calendarPickerView = (CalendarPickerView) Utils.findRequiredViewAsType(view, R.id.calenderPickerView, "field 'calendarPickerView'", CalendarPickerView.class);
    }

    @CallSuper
    public void unbind() {
        DateFragment dateFragment = this.target;
        if (dateFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dateFragment.calendarPickerView = null;
    }
}
