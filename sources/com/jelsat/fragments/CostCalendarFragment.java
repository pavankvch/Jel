package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.propertycostcalendar.IPropertyCostCalenderView;
import com.businesslogic.propertycostcalendar.PropertyCostCalendarPresenter;
import com.data.propertycostcalendar.ModifiedPrice;
import com.data.propertycostcalendar.PropertyCostCalendarRequest;
import com.data.propertycostcalendar.PropertyCostCalendarResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.AvailableDates;
import com.jelsat.R;
import com.jelsat.SampleDayViewAdapter;
import com.jelsat.SampleDecorator;
import com.jelsat.SampleDecorator.UpdateCalendar;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import com.squareup.timessquare.DayViewAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CostCalendarFragment extends BaseFragment implements IPropertyCostCalenderView {
    private List<String> availableDatesList;
    @BindView(2131361950)
    CalendarPickerView calendarPickerView;
    private Date checkInDate;
    private Date checkOutDate;
    @BindView(2131362010)
    TextView clear;
    private int minimumNights;
    private int monthCount;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private PropertyCostCalendarPresenter propertyCostCalendarPresenter = new PropertyCostCalendarPresenter(this, RetrofitClient.getAPIService());
    private int propertyId;
    public PropertyCostCalendarResponse responsevalue = null;
    @BindView(2131362517)
    FancyButton retryButton;
    private SampleDecorator sampleDecorator;
    private List<Date> selectedDates;

    public int getFragmentLayoutId() {
        return R.layout.property_cost_calendar;
    }

    public void onCalendarUpdated(PropertyCostCalendarResponse propertyCostCalendarResponse) {
    }

    @OnClick({2131362010})
    public void clickOnCancel() {
        if (this.selectedDates != null) {
            this.selectedDates.clear();
        }
        if (this.sampleDecorator != null) {
            this.sampleDecorator.setNewAvailableDates(false);
            this.sampleDecorator.setAvailableDates(getAvailableDatesWithoutDuplicates(this.availableDatesList));
        }
        this.calendarPickerView.init(new Date(), getMaxDate()).inMode(SelectionMode.RANGE);
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getCalendarData();
    }

    public static CostCalendarFragment newInstance(int i, Date date, Date date2, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(StringConstants.PROPERTY_ID, i);
        bundle.putSerializable(StringConstants.CHECK_IN_DATE, date);
        bundle.putSerializable(StringConstants.CHECK_OUT_DATE, date2);
        bundle.putInt(StringConstants.MIN_NIGHTS, i2);
        i = new CostCalendarFragment();
        i.setArguments(bundle);
        return i;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getArguments() != null) {
            this.checkInDate = (Date) getArguments().getSerializable(StringConstants.CHECK_IN_DATE);
            this.checkOutDate = (Date) getArguments().getSerializable(StringConstants.CHECK_OUT_DATE);
            this.propertyId = getArguments().getInt(StringConstants.PROPERTY_ID);
            this.minimumNights = getArguments().getInt(StringConstants.MIN_NIGHTS);
        }
        this.availableDatesList = new ArrayList();
        this.selectedDates = new ArrayList();
        viewGroup = Calendar.getInstance();
        viewGroup.add(1, 1);
        this.calendarPickerView.init(new Date(), viewGroup.getTime());
        getCalendarData();
        return layoutInflater;
    }

    private void getCalendarData() {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.calendarPickerView.setVisibility(0);
            this.clear.setVisibility(0);
            if (this.propertyCostCalendarPresenter != null) {
                PropertyCostCalendarRequest propertyCostCalendarRequest = new PropertyCostCalendarRequest();
                propertyCostCalendarRequest.setProperty_id(this.propertyId);
                this.propertyCostCalendarPresenter.getPropertyCostCalendarDetails(getString(R.string.please_wait), propertyCostCalendarRequest);
                return;
            }
        }
        this.noResultLayout.setVisibility(0);
        this.calendarPickerView.setVisibility(8);
        this.clear.setVisibility(8);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.retryButton.setVisibility(0);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(R.string.internet_connection_label));
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void initCalender(PropertyCostCalendarResponse propertyCostCalendarResponse) {
        Collection arrayList = new ArrayList();
        if (!(this.checkInDate == null || this.checkOutDate == null)) {
            arrayList.add(this.checkInDate);
            arrayList.add(this.checkOutDate);
        }
        this.sampleDecorator = new SampleDecorator(getActivity(), true);
        DayViewAdapter sampleDayViewAdapter = new SampleDayViewAdapter();
        this.sampleDecorator.setBookedDates(propertyCostCalendarResponse.getBookedDates());
        this.sampleDecorator.setDefaultPrice(propertyCostCalendarResponse.getPrice());
        this.sampleDecorator.setModifiedDates(getModifiedDates(propertyCostCalendarResponse.getModifiedPrice()));
        this.sampleDecorator.setAvailableDates(getAvailableDatesWithoutDuplicates(propertyCostCalendarResponse.getAvailableDates()));
        this.sampleDecorator.setMinNights(this.minimumNights);
        this.calendarPickerView.setDecorators(Arrays.asList(new CalendarCellDecorator[]{this.sampleDecorator}));
        this.calendarPickerView.setCustomDayView(sampleDayViewAdapter);
        this.calendarPickerView.setCellClickInterceptor(new CostCalendarFragment$1(this));
        this.calendarPickerView.init(new Date(), getMaxDate()).inMode(SelectionMode.RANGE).withSelectedDates(arrayList);
    }

    public void onSuccess(PropertyCostCalendarResponse propertyCostCalendarResponse) {
        this.availableDatesList.clear();
        this.availableDatesList.addAll(propertyCostCalendarResponse.getAvailableDates());
        this.monthCount = propertyCostCalendarResponse.getMonths();
        this.responsevalue = propertyCostCalendarResponse;
        initCalender(propertyCostCalendarResponse);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            aPIError = getString(R.string.general_api_error_msg);
        } else {
            aPIError = aPIError.getSeken_errors();
        }
        this.noResultLayout.setVisibility(0);
        this.calendarPickerView.setVisibility(8);
        this.noResultImage.setImageResource(R.drawable.ic_close_red);
        this.clear.setVisibility(8);
        this.noResultTV.setText(aPIError);
        this.retryButton.setVisibility(0);
    }

    private Date getMaxDate() {
        Calendar instance = Calendar.getInstance();
        for (int i = 0; i < this.monthCount; i++) {
            instance.add(2, 1);
        }
        MonthDisplayHelper monthDisplayHelper = new MonthDisplayHelper(instance.get(1), instance.get(2));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(monthDisplayHelper.getNumberOfDaysInMonth());
        Log.d("xxx", stringBuilder.toString());
        instance = Calendar.getInstance();
        instance.set(monthDisplayHelper.getYear(), monthDisplayHelper.getMonth(), monthDisplayHelper.getNumberOfDaysInMonth() + 1);
        return instance.getTime();
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void loadCalender(int i) {
        PropertyCostCalendarRequest propertyCostCalendarRequest = new PropertyCostCalendarRequest();
        propertyCostCalendarRequest.setProperty_id(i);
        this.propertyCostCalendarPresenter.getPropertyCostCalendarDetails(getString(R.string.please_wait), propertyCostCalendarRequest);
    }

    public List<Date> getSelectedRange() {
        return this.calendarPickerView.getSelectedDates();
    }

    public PropertyCostCalendarResponse getSelectedRangeCost() {
        return this.responsevalue;
    }

    public List<String> getAvailableDates() {
        return this.availableDatesList;
    }

    private Map<String, AvailableDates> getAvailableDatesWithoutDuplicates(List<String> list) {
        list = new ArrayList(new LinkedHashSet(list));
        Collections.sort(list, new CostCalendarFragment$2(this));
        Map<String, AvailableDates> linkedHashMap = new LinkedHashMap();
        for (String str : list) {
            AvailableDates availableDates = new AvailableDates();
            availableDates.setAvailableDate(Utils.convertStringToDate("yyyy-MM-dd", str));
            availableDates.setSelectable(true);
            linkedHashMap.put(str, availableDates);
        }
        return linkedHashMap;
    }

    private Map<String, ModifiedPrice> getModifiedDates(List<ModifiedPrice> list) {
        Map<String, ModifiedPrice> hashMap = new HashMap();
        for (ModifiedPrice modifiedPrice : list) {
            hashMap.put(modifiedPrice.getDate(), modifiedPrice);
        }
        return hashMap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCalendarDates(UpdateCalendar updateCalendar) {
        if (updateCalendar != null) {
            this.calendarPickerView.setTitleTypeface(ResourcesCompat.getFont(requireActivity(), R.font.sf_ui_display_regular));
        }
    }

    public void onDetach() {
        if (this.propertyCostCalendarPresenter != null) {
            this.propertyCostCalendarPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
