package com.jelsat.fragments;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.data.propertycostcalendar.UpdateCalendarRequest;
import com.jelsat.R;
import com.jelsat.utils.Utils;
import com.squareup.timessquare.CalendarPickerView.CellClickInterceptor;
import java.util.Date;
import java.util.List;

class CalendarDatesAvailabilityFragment$2 implements CellClickInterceptor {
    final /* synthetic */ CalendarDatesAvailabilityFragment this$0;
    final /* synthetic */ List val$availableDates;

    CalendarDatesAvailabilityFragment$2(CalendarDatesAvailabilityFragment calendarDatesAvailabilityFragment, List list) {
        this.this$0 = calendarDatesAvailabilityFragment;
        this.val$availableDates = list;
    }

    public boolean onCellClicked(Date date) {
        String string;
        boolean z;
        date = Utils.getDateToString(date);
        if (this.val$availableDates.contains(date)) {
            string = this.this$0.getString(R.string.disable_label);
            z = true;
        } else {
            string = this.this$0.getString(R.string.enable_label);
            z = false;
        }
        new Builder(this.this$0.getActivity()).setMessage(String.format("%s %s (%s) %s", new Object[]{this.this$0.getResources().getString(R.string.calender_dates_do_you_really_want), string, Utils.bookingsDateFormat(date), this.this$0.getResources().getString(R.string.calender_date_this_date)})).setIcon(17301543).setPositiveButton(17039379, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                i = new UpdateCalendarRequest();
                i.setEndDate(date);
                i.setPropertyId(CalendarDatesAvailabilityFragment.access$000(CalendarDatesAvailabilityFragment$2.this.this$0));
                i.setStartDate(date);
                i.setWeeklyDay("");
                if (z) {
                    i.setStatus(2);
                } else {
                    i.setStatus(1);
                }
                i.setType("availability");
                CalendarDatesAvailabilityFragment.access$100(CalendarDatesAvailabilityFragment$2.this.this$0).updateCalendar(CalendarDatesAvailabilityFragment$2.this.this$0.getString(R.string.please_wait), i);
                dialogInterface.dismiss();
            }
        }).setNegativeButton(17039369, null).show();
        return false;
    }
}
