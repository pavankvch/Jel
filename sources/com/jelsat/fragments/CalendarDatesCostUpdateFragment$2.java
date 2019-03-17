package com.jelsat.fragments;

import com.jelsat.utils.Utils;
import com.squareup.timessquare.CalendarPickerView.CellClickInterceptor;
import java.util.Date;

class CalendarDatesCostUpdateFragment$2 implements CellClickInterceptor {
    final /* synthetic */ CalendarDatesCostUpdateFragment this$0;

    CalendarDatesCostUpdateFragment$2(CalendarDatesCostUpdateFragment calendarDatesCostUpdateFragment) {
        this.this$0 = calendarDatesCostUpdateFragment;
    }

    public boolean onCellClicked(Date date) {
        UpdatePriceDialogFragment.newInstance(Utils.getDateToString(date), CalendarDatesCostUpdateFragment.access$000(this.this$0)).show(this.this$0.getChildFragmentManager(), "updatecalendar");
        return null;
    }
}
