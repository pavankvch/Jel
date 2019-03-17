package com.jelsat;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.data.propertycostcalendar.ModifiedPrice;
import com.data.utils.AvailableDates;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarCellView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.greenrobot.eventbus.EventBus;

public class SampleDecorator implements CalendarCellDecorator {
    private Map<String, AvailableDates> availableDateMap;
    private List<String> bookedDates;
    private Context context;
    private float defaultPrice;
    private boolean isFromPropertyDetail;
    private boolean isNewAvailableDates;
    private int minimumNights;
    private Map<String, ModifiedPrice> modifiedPriceMap;
    private Map<String, AvailableDates> newAvailableMap = new LinkedHashMap();

    public class UpdateCalendar {
        private Date date;

        public UpdateCalendar(Date date) {
            this.date = date;
        }

        public Date getDate() {
            return this.date;
        }
    }

    public SampleDecorator(Context context, boolean z) {
        this.context = context;
        this.isFromPropertyDetail = z;
    }

    public void decorate(CalendarCellView calendarCellView, Date date) {
        TextView textView = (TextView) ((LinearLayout) calendarCellView.getChildAt(0)).getChildAt(1);
        if (calendarCellView.isSelectable()) {
            if (isDateSelectable(date)) {
                calendarCellView.setSelectable(true);
                calendarCellView.setClickable(true);
                float isModifiedDay = isModifiedDay(date);
                calendarCellView.getDayOfMonthTextView().setPaintFlags(0);
                if (this.isFromPropertyDetail) {
                    if (isNewAvailableDates()) {
                        if (isNewAvailableDateSelectable(date) != null) {
                            calendarCellView.getDayOfMonthTextView().setTextColor(applyColor(calendarCellView.getContext(), R.color.cost_calendar_available_date_color));
                            textView.setTextColor(applyColor(textView.getContext(), R.color.cost_calendar_price_color));
                            if (isModifiedDay != -1.0f) {
                                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                                    textView.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(isModifiedDay), this.context.getString(R.string.cost_calendar_price_type)}));
                                    return;
                                }
                                textView.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.context.getString(R.string.cost_calendar_price_type), Float.valueOf(isModifiedDay)}));
                                return;
                            } else if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                                textView.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(this.defaultPrice), this.context.getString(R.string.cost_calendar_price_type)}));
                                return;
                            } else {
                                textView.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.context.getString(R.string.cost_calendar_price_type), Float.valueOf(this.defaultPrice)}));
                                return;
                            }
                        }
                        calendarCellView.getDayOfMonthTextView().setTextColor(applyColor(calendarCellView.getContext(), R.color.cost_calendar_partial_date_color));
                        textView.setText("");
                        return;
                    } else if (isNextDatesAvailableBasedOnMinimumNights(date) != null) {
                        calendarCellView.getDayOfMonthTextView().setTextColor(applyColor(calendarCellView.getContext(), R.color.cost_calendar_available_date_color));
                        textView.setTextColor(applyColor(textView.getContext(), R.color.cost_calendar_price_color));
                        if (isModifiedDay != -1.0f) {
                            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                                textView.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(isModifiedDay), this.context.getString(R.string.cost_calendar_price_type)}));
                                return;
                            }
                            textView.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.context.getString(R.string.cost_calendar_price_type), Float.valueOf(isModifiedDay)}));
                            return;
                        } else if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                            textView.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(this.defaultPrice), this.context.getString(R.string.cost_calendar_price_type)}));
                            return;
                        } else {
                            textView.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.context.getString(R.string.cost_calendar_price_type), Float.valueOf(this.defaultPrice)}));
                            return;
                        }
                    } else {
                        calendarCellView.getDayOfMonthTextView().setTextColor(applyColor(calendarCellView.getContext(), R.color.cost_calendar_partial_date_color));
                        textView.setText("");
                    }
                }
            }
        }
        calendarCellView.getDayOfMonthTextView().setTextColor(applyColor(calendarCellView.getContext(), R.color.cost_calendar_disable_date_color));
        textView.setText("");
        if (calendarCellView.isCurrentMonth() != null) {
            calendarCellView.getDayOfMonthTextView().setPaintFlags(17);
            calendarCellView.setSelectable(false);
            calendarCellView.setClickable(false);
        }
    }

    private float isModifiedDay(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        for (Entry entry : this.modifiedPriceMap.entrySet()) {
            Date convertStringToDate = convertStringToDate(((ModifiedPrice) entry.getValue()).getDate());
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(convertStringToDate);
            if (sameDate(instance2, instance)) {
                return ((ModifiedPrice) entry.getValue()).getPrice();
            }
        }
        return -1.0f;
    }

    public void setBookedDates(List<String> list) {
        this.bookedDates = list;
    }

    public void setDefaultPrice(float f) {
        this.defaultPrice = f;
    }

    public void setModifiedDates(Map<String, ModifiedPrice> map) {
        this.modifiedPriceMap = map;
    }

    private boolean isDateValid(Date date) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date);
        return instance2.compareTo(instance) >= null ? true : null;
    }

    private static boolean sameDate(Calendar calendar, Calendar calendar2) {
        if (calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1) && calendar.get(5) == calendar2.get(5)) {
            return true;
        }
        return null;
    }

    private boolean isDateSelectable(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (isNewAvailableDates() != null) {
            date = this.newAvailableMap;
        } else {
            date = this.availableDateMap;
        }
        for (Entry entry : r4.entrySet()) {
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(((AvailableDates) entry.getValue()).getAvailableDate());
            if (sameDate(instance, instance2)) {
                return true;
            }
        }
        return null;
    }

    public void setAvailableDates(Map<String, AvailableDates> map) {
        this.availableDateMap = map;
    }

    public void updateDate(Date date, int i) {
        ModifiedPrice modifiedPrice = new ModifiedPrice();
        modifiedPrice.setDate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date));
        modifiedPrice.setPrice((float) i);
    }

    private int applyColor(Context context, @ColorRes int i) {
        return ContextCompat.getColor(context, i);
    }

    private Date convertStringToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
        } catch (String str2) {
            str2.printStackTrace();
            return null;
        }
    }

    public static String convertDateToString(String str, Date date) {
        return new SimpleDateFormat(str, Locale.US).format(date);
    }

    public boolean isNextDatesAvailableBasedOnMinimumNights(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i = 0;
        for (int i2 = 0; i2 < this.minimumNights - 1; i2++) {
            instance.add(5, 1);
            if (this.availableDateMap.containsKey(convertDateToString("yyyy-MM-dd", instance.getTime()))) {
                i++;
            }
        }
        if (i == this.minimumNights - 1) {
            return true;
        }
        return null;
    }

    public void setMinNights(int i) {
        this.minimumNights = i;
    }

    public boolean isNewAvailableDates() {
        return this.isNewAvailableDates;
    }

    public void setNewAvailableDates(boolean z) {
        this.isNewAvailableDates = z;
    }

    public void setNewAvailableDatesToDecorator(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(5, 1);
        int i = 1;
        while (this.availableDateMap.containsKey(convertDateToString("yyyy-MM-dd", instance.getTime()))) {
            instance.add(5, 1);
            i++;
        }
        if (!(this.newAvailableMap == null || this.newAvailableMap.isEmpty())) {
            this.newAvailableMap.clear();
        }
        instance.setTime(date);
        for (int i2 = 0; i2 <= i; i2++) {
            AvailableDates availableDates = new AvailableDates();
            availableDates.setAvailableDate(instance.getTime());
            if (i2 != 0) {
                if (i2 != i) {
                    if (i2 < this.minimumNights) {
                        availableDates.setSelectable(false);
                    } else {
                        availableDates.setSelectable(true);
                    }
                    this.newAvailableMap.put(convertDateToString("yyyy-MM-dd", instance.getTime()), availableDates);
                    instance.add(5, 1);
                }
            }
            availableDates.setSelectable(true);
            this.newAvailableMap.put(convertDateToString("yyyy-MM-dd", instance.getTime()), availableDates);
            instance.add(5, 1);
        }
        EventBus.getDefault().post(new UpdateCalendar(date));
    }

    private boolean isNewAvailableDateSelectable(Date date) {
        return ((AvailableDates) this.newAvailableMap.get(convertDateToString("yyyy-MM-dd", date))).isSelectable();
    }

    public Map<String, AvailableDates> getNewAvailableMap() {
        return this.newAvailableMap;
    }
}
