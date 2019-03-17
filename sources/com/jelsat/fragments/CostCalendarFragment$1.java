package com.jelsat.fragments;

import com.data.utils.AvailableDates;
import com.jelsat.R;
import com.jelsat.SampleDecorator;
import com.squareup.timessquare.CalendarPickerView.CellClickInterceptor;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

class CostCalendarFragment$1 implements CellClickInterceptor {
    final /* synthetic */ CostCalendarFragment this$0;

    CostCalendarFragment$1(CostCalendarFragment costCalendarFragment) {
        this.this$0 = costCalendarFragment;
    }

    public boolean onCellClicked(Date date) {
        if (!CostCalendarFragment.access$000(this.this$0).contains(date)) {
            CostCalendarFragment.access$000(this.this$0).add(date);
        }
        if (CostCalendarFragment.access$100(this.this$0).isNewAvailableDates()) {
            Map newAvailableMap = CostCalendarFragment.access$100(this.this$0).getNewAvailableMap();
            date = SampleDecorator.convertDateToString("yyyy-MM-dd", date);
            if (newAvailableMap.containsKey(date) && ((AvailableDates) newAvailableMap.get(date)).isSelectable() == null) {
                this.this$0.showToast(String.format(Locale.getDefault(), "%s %d", new Object[]{this.this$0.getString(R.string.minimum_nights_label), Integer.valueOf(CostCalendarFragment.access$200(this.this$0))}));
                CostCalendarFragment.access$000(this.this$0).clear();
                this.this$0.calendarPickerView.init(new Date(), CostCalendarFragment.access$300(this.this$0)).inMode(SelectionMode.RANGE);
            }
        } else if (CostCalendarFragment.access$100(this.this$0).isNextDatesAvailableBasedOnMinimumNights(date)) {
            CostCalendarFragment.access$100(this.this$0).setNewAvailableDates(true);
            CostCalendarFragment.access$100(this.this$0).setNewAvailableDatesToDecorator(date);
        } else {
            this.this$0.showToast(String.format(Locale.getDefault(), "%s %d", new Object[]{this.this$0.getString(R.string.minimum_nights_label), Integer.valueOf(CostCalendarFragment.access$200(this.this$0))}));
            CostCalendarFragment.access$000(this.this$0).clear();
            this.this$0.calendarPickerView.init(new Date(), CostCalendarFragment.access$300(this.this$0)).inMode(SelectionMode.RANGE);
        }
        if (CostCalendarFragment.access$000(this.this$0).size() == 2) {
            CostCalendarFragment.access$000(this.this$0).clear();
        }
        return false;
    }
}
