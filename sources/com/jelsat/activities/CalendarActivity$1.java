package com.jelsat.activities;

import android.support.v4.app.FragmentTransaction;
import com.jelsat.R;

class CalendarActivity$1 implements Runnable {
    final /* synthetic */ CalendarActivity this$0;

    CalendarActivity$1(CalendarActivity calendarActivity) {
        this.this$0 = calendarActivity;
    }

    public void run() {
        CalendarActivity.access$002(this.this$0, CalendarActivity.access$100(this.this$0));
        FragmentTransaction beginTransaction = this.this$0.getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.calendar_date_container, CalendarActivity.access$000(this.this$0), CalendarActivity.access$200(this.this$0));
        beginTransaction.commitAllowingStateLoss();
    }
}
