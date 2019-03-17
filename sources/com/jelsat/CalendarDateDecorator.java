package com.jelsat;

import android.support.v4.internal.view.SupportMenu;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.data.propertycostcalendar.ModifiedPrice;
import com.jelsat.utils.Utils;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarDateDecorator implements CalendarCellDecorator {
    private int activeDateColor;
    private List<String> availableDates;
    private List<String> bookedDates;
    private float defaultPrice;
    private int disabledDateColor;
    private boolean isCostCalender;
    private List<ModifiedPrice> modifiedDates;
    private int priceColor;
    private String priceType;

    public void decorate(CalendarCellView calendarCellView, Date date) {
        String dateToString = Utils.getDateToString(date);
        TextView textView = (TextView) ((LinearLayout) calendarCellView.getChildAt(0)).getChildAt(1);
        textView.setText("");
        calendarCellView.setSelectable(false);
        calendarCellView.getDayOfMonthTextView().setTextColor(this.disabledDateColor);
        if (date.before(new Date()) != null) {
            calendarCellView.getDayOfMonthTextView().setTextColor(this.disabledDateColor);
            calendarCellView.setSelectable(false);
            calendarCellView.setClickable(false);
        } else if (this.isCostCalender != null) {
            if (this.availableDates == null) {
                calendarCellView.getDayOfMonthTextView().setTextColor(this.disabledDateColor);
                calendarCellView.setSelectable(false);
                calendarCellView.setClickable(false);
            } else if (this.availableDates.contains(dateToString) != null) {
                textView.setTextColor(this.priceColor);
                if (isModifiedDay(dateToString) != -1.0f) {
                    if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                        textView.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(date), this.priceType}));
                    } else {
                        textView.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.priceType, Float.valueOf(date)}));
                    }
                } else if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                    textView.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(this.defaultPrice), this.priceType}));
                } else {
                    textView.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.priceType, Float.valueOf(this.defaultPrice)}));
                }
                calendarCellView.getDayOfMonthTextView().setTextColor(this.activeDateColor);
                calendarCellView.setSelectable(true);
                calendarCellView.setClickable(true);
            } else {
                calendarCellView.getDayOfMonthTextView().setTextColor(this.disabledDateColor);
                calendarCellView.setSelectable(false);
                calendarCellView.setClickable(false);
            }
        } else if (this.availableDates == null || this.availableDates.contains(dateToString) == null) {
            if (!(this.bookedDates == null || this.bookedDates.contains(dateToString) == null)) {
                textView.setText("-");
                textView.setTypeface(null, 1);
                textView.setTextColor(SupportMenu.CATEGORY_MASK);
                calendarCellView.getDayOfMonthTextView().setTextColor(this.disabledDateColor);
                calendarCellView.setSelectable(false);
                calendarCellView.setClickable(false);
            }
        } else {
            calendarCellView.getDayOfMonthTextView().setTextColor(this.activeDateColor);
            calendarCellView.setSelectable(true);
            calendarCellView.setClickable(true);
        }
    }

    public void setDefaultPrice(float f) {
        this.defaultPrice = f;
    }

    public void setAvailableDates(List<String> list) {
        this.availableDates = list;
    }

    public void isCostCalender(boolean z) {
        this.isCostCalender = z;
    }

    public void setBookedDates(List<String> list) {
        this.bookedDates = list;
    }

    public void setDisabledDatesColor(int i) {
        this.disabledDateColor = i;
    }

    public void setPriceColor(int i) {
        this.priceColor = i;
    }

    public void setPriceType(String str) {
        this.priceType = str;
    }

    public void setModifiedDates(List<ModifiedPrice> list) {
        this.modifiedDates = list;
    }

    private float isModifiedDay(String str) {
        for (int i = 0; i < this.modifiedDates.size(); i++) {
            if (((ModifiedPrice) this.modifiedDates.get(i)).getDate().equalsIgnoreCase(str)) {
                return ((ModifiedPrice) this.modifiedDates.get(i)).getPrice();
            }
        }
        return -1.0f;
    }

    public void setActiveDatesColor(int i) {
        this.activeDateColor = i;
    }
}
