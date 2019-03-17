package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.jelsat.DateDialogDecorator;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.OnDaySelectedEvent;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.OnDateSelectedListener;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import com.squareup.timessquare.DefaultDayViewAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class DateFragment extends BaseFragment {
    @BindView(2131361957)
    CalendarPickerView calendarPickerView;
    private Date checkInDate;
    private Date checkOutDate;

    public int getFragmentLayoutId() {
        return R.layout.fragment_date;
    }

    public static DateFragment newInstance(Date date, Date date2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.CHECK_IN_DATE, date);
        bundle.putSerializable(StringConstants.CHECK_OUT_DATE, date2);
        date = new DateFragment();
        date.setArguments(bundle);
        return date;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getArguments() != null) {
            this.checkInDate = (Date) getArguments().getSerializable(StringConstants.CHECK_IN_DATE);
            this.checkOutDate = (Date) getArguments().getSerializable(StringConstants.CHECK_OUT_DATE);
        }
        viewGroup = Calendar.getInstance();
        for (int i = 0; i < 8; i++) {
            viewGroup.add(2, 1);
        }
        MonthDisplayHelper monthDisplayHelper = new MonthDisplayHelper(viewGroup.get(1), viewGroup.get(2));
        viewGroup = Calendar.getInstance();
        viewGroup.set(monthDisplayHelper.getYear(), monthDisplayHelper.getMonth(), monthDisplayHelper.getNumberOfDaysInMonth() + 1);
        Collection arrayList = new ArrayList();
        if (!(this.checkInDate == null || this.checkOutDate == null)) {
            arrayList.add(this.checkInDate);
            arrayList.add(this.checkOutDate);
        }
        this.calendarPickerView.setCustomDayView(new DefaultDayViewAdapter());
        DateDialogDecorator dateDialogDecorator = new DateDialogDecorator();
        this.calendarPickerView.setDecorators(Arrays.asList(new CalendarCellDecorator[]{dateDialogDecorator}));
        this.calendarPickerView.setCustomDayView(new DefaultDayViewAdapter());
        this.calendarPickerView.setOnDateSelectedListener(new OnDateSelectedListener() {
            public void onDateUnselected(Date date) {
            }

            public void onDateSelected(Date date) {
                EventBus.getDefault().post(new OnDaySelectedEvent(DateFragment.this.calendarPickerView.getSelectedDates()));
            }
        });
        this.calendarPickerView.init(new Date(), viewGroup.getTime()).inMode(SelectionMode.RANGE).withSelectedDates(arrayList);
        return layoutInflater;
    }

    public List<Date> getSelectedRange() {
        return this.calendarPickerView.getSelectedDates();
    }
}
