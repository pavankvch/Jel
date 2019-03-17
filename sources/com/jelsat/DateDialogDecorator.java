package com.jelsat;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import java.util.Date;

public class DateDialogDecorator implements CalendarCellDecorator {
    public void decorate(CalendarCellView calendarCellView, Date date) {
        if (calendarCellView.isSelectable() == null) {
            calendarCellView.setSelectable(false);
            calendarCellView.setClickable(false);
            return;
        }
        calendarCellView.setSelectable(true);
    }
}
