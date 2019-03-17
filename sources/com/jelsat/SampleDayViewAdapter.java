package com.jelsat;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.squareup.timessquare.CalendarCellView;
import com.squareup.timessquare.DayViewAdapter;

public class SampleDayViewAdapter implements DayViewAdapter {
    public void makeCellView(CalendarCellView calendarCellView) {
        View inflate = LayoutInflater.from(calendarCellView.getContext()).inflate(R.layout.custom_day_view, null);
        calendarCellView.addView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.day_TV);
        textView.setDuplicateParentStateEnabled(true);
        textView.setTextColor(ContextCompat.getColor(inflate.getContext(), R.color.colorPrimary));
        calendarCellView.setDayOfMonthTextView(textView);
        ((TextView) inflate.findViewById(R.id.cost_TV)).setDuplicateParentStateEnabled(true);
    }
}
