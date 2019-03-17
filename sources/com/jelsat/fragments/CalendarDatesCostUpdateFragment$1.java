package com.jelsat.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import com.data.payments.HostProperty;
import com.jelsat.R;

class CalendarDatesCostUpdateFragment$1 implements OnItemSelectedListener {
    final /* synthetic */ CalendarDatesCostUpdateFragment this$0;

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    CalendarDatesCostUpdateFragment$1(CalendarDatesCostUpdateFragment calendarDatesCostUpdateFragment) {
        this.this$0 = calendarDatesCostUpdateFragment;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        this.this$0.norecordsTv.setVisibility(0);
        this.this$0.daysLayout.setVisibility(8);
        this.this$0.calendarPickerView.setVisibility(8);
        this.this$0.dateRangeIv.setVisibility(8);
        if (this.this$0.spinner_nav.getSelectedItem().toString().trim().equals(this.this$0.getString(R.string.calender_cost_select_property)) != null) {
            CalendarDatesCostUpdateFragment.access$002(this.this$0, 0);
            this.this$0.showToast(this.this$0.getString(R.string.calender_cost_please_select_prop));
            return;
        }
        CalendarDatesCostUpdateFragment.access$002(this.this$0, Integer.parseInt(((HostProperty) adapterView.getItemAtPosition(i)).getPropertyId()));
        this.this$0.loadCalender(CalendarDatesCostUpdateFragment.access$000(this.this$0));
    }
}
