package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.media.ExifInterface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.propertycostcalendar.IPropertyCostCalenderView;
import com.businesslogic.propertycostcalendar.PropertyCostCalendarPresenter;
import com.data.payments.HostProperty;
import com.data.propertycostcalendar.PropertyCostCalendarRequest;
import com.data.propertycostcalendar.PropertyCostCalendarResponse;
import com.data.propertycostcalendar.UpdateCalendarRequest;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.CalendarDateDecorator;
import com.jelsat.R;
import com.jelsat.SampleDayViewAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.FragmentLoadedEvent;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.CalendarPickerView.SelectionMode;
import com.squareup.timessquare.DayViewAdapter;
import com.squareup.timessquare.DefaultDayViewAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CalendarDatesAvailabilityFragment extends BaseFragment implements IPropertyCostCalenderView {
    @BindView(2131361950)
    CalendarPickerView calendarPickerView;
    @BindView(2131362262)
    ImageView dateRangeIv;
    @BindView(2131362283)
    LinearLayout daysLayout;
    @BindView(2131362717)
    TextView descriptionTv1;
    private boolean isEditProperty;
    int monthsLimit;
    @BindView(2131362373)
    TextView norecordsTv;
    private PropertyCostCalendarPresenter propertyCostCalendarPresenter = new PropertyCostCalendarPresenter(this, RetrofitClient.getAPIService());
    private int propertyId;
    @BindView(2131362445)
    TextView propertyName;
    @BindView(2131362611)
    Spinner spinner_nav;

    public int getFragmentLayoutId() {
        return R.layout.fragment_custom_calendar;
    }

    @OnClick({2131362262})
    public void clickOnDateRangeImage() {
        updateWeek("");
    }

    @OnClick({2131362775})
    public void clickOnSunday() {
        updateWeek(AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    @OnClick({2131362741})
    public void clickOnMonday() {
        updateWeek(AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    @OnClick({2131362785})
    public void clickOnTuesday() {
        updateWeek(ExifInterface.GPS_MEASUREMENT_2D);
    }

    @OnClick({2131362788})
    public void clickOnWednesday() {
        updateWeek(ExifInterface.GPS_MEASUREMENT_3D);
    }

    @OnClick({2131362778})
    public void clickOnThursday() {
        updateWeek("4");
    }

    @OnClick({2131362729})
    public void clickOnFriday() {
        updateWeek("5");
    }

    @OnClick({2131362771})
    public void clickOnSaturday() {
        updateWeek("6");
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.descriptionTv1.setText(String.format("%s", new Object[]{getString(R.string.calender_description)}));
        viewGroup = Calendar.getInstance();
        viewGroup.add(1, 1);
        this.calendarPickerView.init(new Date(), viewGroup.getTime());
        this.norecordsTv.setVisibility(0);
        this.daysLayout.setVisibility(8);
        this.calendarPickerView.setVisibility(8);
        this.dateRangeIv.setVisibility(8);
        return layoutInflater;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        CharSequence charSequence = null;
        if (getActivity().getIntent() != null) {
            if (getActivity().getIntent().getStringExtra(StringConstants.PROPERTY_ID) != null) {
                this.propertyId = Integer.parseInt(getActivity().getIntent().getStringExtra(StringConstants.PROPERTY_ID));
            }
            if (getActivity().getIntent().getStringExtra(StringConstants.PROPERTY_NAME) != null) {
                charSequence = getActivity().getIntent().getStringExtra(StringConstants.PROPERTY_NAME);
            }
            if (getActivity().getIntent().getBooleanExtra(StringConstants.IS_EDIT_PROPERTY, false) != null) {
                this.isEditProperty = getActivity().getIntent().getBooleanExtra(StringConstants.IS_EDIT_PROPERTY, false);
            }
        }
        if (this.propertyId != null && this.isEditProperty != null && TextUtils.isEmpty(charSequence) == null) {
            this.spinner_nav.setVisibility(8);
            this.propertyName.setVisibility(0);
            this.propertyName.setText(charSequence);
            loadCalender(this.propertyId);
        }
    }

    public void setPropertiesList(List<HostProperty> list) {
        List arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            arrayList.addAll(list);
        }
        addItemsToSpinner(arrayList);
    }

    private void updateWeek(String str) {
        WeekDaysUpdateDialogFragment.newInstance(this.propertyId, str, this.monthsLimit, false).show(getChildFragmentManager(), "updatecalendar");
    }

    public void addItemsToSpinner(List<HostProperty> list) {
        SpinnerAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367048, list);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spinner_nav.setAdapter(arrayAdapter);
        this.spinner_nav.setOnItemSelectedListener(new CalendarDatesAvailabilityFragment$1(this));
    }

    public void onSuccess(PropertyCostCalendarResponse propertyCostCalendarResponse) {
        int i = 0;
        if (!(this.norecordsTv == null || this.daysLayout == null || this.calendarPickerView == null || this.dateRangeIv == null)) {
            this.norecordsTv.setVisibility(8);
            this.daysLayout.setVisibility(0);
            this.calendarPickerView.setVisibility(0);
            this.dateRangeIv.setVisibility(0);
        }
        Calendar instance = Calendar.getInstance();
        this.monthsLimit = propertyCostCalendarResponse.getMonths();
        if (propertyCostCalendarResponse.getMonths() > 0) {
            while (i < propertyCostCalendarResponse.getMonths() - 1) {
                instance.add(2, 1);
                i++;
            }
        }
        MonthDisplayHelper monthDisplayHelper = new MonthDisplayHelper(instance.get(1), instance.get(2));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(monthDisplayHelper.getNumberOfDaysInMonth());
        Log.d("xxx", stringBuilder.toString());
        instance = Calendar.getInstance();
        instance.set(monthDisplayHelper.getYear(), monthDisplayHelper.getMonth(), monthDisplayHelper.getNumberOfDaysInMonth() + 1);
        initCalender(instance, propertyCostCalendarResponse.getAvailableDates(), propertyCostCalendarResponse.getBookedDates());
    }

    public void onCalendarUpdated(PropertyCostCalendarResponse propertyCostCalendarResponse) {
        showToast(getString(R.string.calender_cost_updated));
        loadCalender(this.propertyId);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getWeekDaysValues(UpdateCalendarRequest updateCalendarRequest) {
        if (updateCalendarRequest != null) {
            this.propertyCostCalendarPresenter.updateCalendar(getString(R.string.please_wait), updateCalendarRequest);
        }
    }

    private void initCalender(Calendar calendar, List<String> list, List<String> list2) {
        if (getActivity() != null) {
            DayViewAdapter sampleDayViewAdapter = new SampleDayViewAdapter();
            CalendarDateDecorator calendarDateDecorator = new CalendarDateDecorator();
            calendarDateDecorator.setPriceColor(applyColor(R.color.cost_calendar_price_color));
            calendarDateDecorator.setDisabledDatesColor(applyColor(R.color.normal_text_color_30pr_opacity));
            calendarDateDecorator.setAvailableDates(list);
            calendarDateDecorator.setBookedDates(list2);
            calendarDateDecorator.setDisabledDatesColor(applyColor(R.color.normal_text_color_30pr_opacity));
            calendarDateDecorator.setActiveDatesColor(applyColor(R.color.cost_calendar_date_color));
            calendarDateDecorator.isCostCalender(false);
            this.calendarPickerView.setCustomDayView(new DefaultDayViewAdapter());
            this.calendarPickerView.setDecorators(Arrays.asList(new CalendarCellDecorator[]{calendarDateDecorator}));
            this.calendarPickerView.setCustomDayView(sampleDayViewAdapter);
            this.calendarPickerView.setCellClickInterceptor(new CalendarDatesAvailabilityFragment$2(this, list));
            this.calendarPickerView.init(new Date(), calendar.getTime()).inMode(SelectionMode.SINGLE);
        }
    }

    public List<Date> getSelectedDates() {
        return this.calendarPickerView.getSelectedDates();
    }

    public void loadCalender(int i) {
        PropertyCostCalendarRequest propertyCostCalendarRequest = new PropertyCostCalendarRequest();
        propertyCostCalendarRequest.setProperty_id(i);
        this.propertyCostCalendarPresenter.getPropertyCostCalendarDetails(getString(R.string.please_wait), propertyCostCalendarRequest);
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new FragmentLoadedEvent(true));
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetach() {
        EventBus.getDefault().post(this.propertyCostCalendarPresenter);
        super.onDetach();
    }

    public int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(requireActivity(), i);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}
