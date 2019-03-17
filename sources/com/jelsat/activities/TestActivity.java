package com.jelsat.activities;

import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.util.MonthDisplayHelper;
import android.widget.EditText;
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
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DayViewAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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

public class TestActivity extends BaseAppCompactActivity implements IPropertyCostCalenderView {
    private List<String> availableDatesList;
    @BindView(2131361950)
    CalendarPickerView calendarPickerView;
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
    @BindView(2131362517)
    FancyButton retryButton;
    private SampleDecorator sampleDecorator;

    public int getActivityLayout() {
        return R.layout.activity_test;
    }

    public void onCalendarUpdated(PropertyCostCalendarResponse propertyCostCalendarResponse) {
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getCalendarData();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.availableDatesList = new ArrayList();
        bundle = Calendar.getInstance();
        bundle.add(1, 1);
        this.clear.setVisibility(8);
        this.calendarPickerView.init(new Date(), bundle.getTime());
        this.minimumNights = getIntent().getIntExtra(StringConstants.MIN_NIGHTS, 1);
        getCalendarData();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void getCalendarData() {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.calendarPickerView.setVisibility(0);
            if (this.propertyCostCalendarPresenter != null) {
                PropertyCostCalendarRequest propertyCostCalendarRequest = new PropertyCostCalendarRequest();
                propertyCostCalendarRequest.setProperty_id(Integer.parseInt(getIntent().getStringExtra(StringConstants.PROPERTY_ID)));
                this.propertyCostCalendarPresenter.getPropertyCostCalendarDetails(getString(R.string.please_wait), propertyCostCalendarRequest);
                return;
            }
        }
        this.noResultLayout.setVisibility(0);
        this.calendarPickerView.setVisibility(8);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.retryButton.setVisibility(0);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(R.string.internet_connection_label));
    }

    public void onSuccess(PropertyCostCalendarResponse propertyCostCalendarResponse) {
        this.availableDatesList.clear();
        this.availableDatesList.addAll(propertyCostCalendarResponse.getAvailableDates());
        this.monthCount = propertyCostCalendarResponse.getMonths();
        this.sampleDecorator = new SampleDecorator(this, true);
        DayViewAdapter sampleDayViewAdapter = new SampleDayViewAdapter();
        this.sampleDecorator.setBookedDates(propertyCostCalendarResponse.getBookedDates());
        this.sampleDecorator.setDefaultPrice(propertyCostCalendarResponse.getPrice());
        this.sampleDecorator.setModifiedDates(getModifiedDates(propertyCostCalendarResponse.getModifiedPrice()));
        this.sampleDecorator.setAvailableDates(getAvailableDatesWithoutDuplicates(propertyCostCalendarResponse.getAvailableDates()));
        this.sampleDecorator.setMinNights(this.minimumNights);
        this.calendarPickerView.setDecorators(Arrays.asList(new CalendarCellDecorator[]{this.sampleDecorator}));
        this.calendarPickerView.setCustomDayView(sampleDayViewAdapter);
        this.calendarPickerView.init(new Date(), getMaxDate()).displayOnly();
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

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            aPIError = getString(R.string.general_api_error_msg);
        } else {
            aPIError = aPIError.getSeken_errors();
        }
        this.noResultLayout.setVisibility(0);
        this.calendarPickerView.setVisibility(8);
        this.noResultImage.setImageResource(R.drawable.ic_close_red);
        this.noResultTV.setText(aPIError);
        this.retryButton.setVisibility(0);
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.propertyCostCalendarPresenter != null) {
            this.propertyCostCalendarPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    private static boolean sameDate(Calendar calendar, Calendar calendar2) {
        if (calendar.get(2) == calendar2.get(2) && calendar.get(1) == calendar2.get(1) && calendar.get(5) == calendar2.get(5)) {
            return true;
        }
        return null;
    }

    private void showCalendarInDialog(String str, int i, Date date) {
        i = getLayoutInflater().inflate(i, null, false);
        str = new Builder(this).setTitle(str).setView(i).setPositiveButton("Update", new TestActivity$2(this, (EditText) i.findViewById(R.id.updated_price), date)).setNegativeButton("Cancel", new TestActivity$1(this)).create();
        str.setCanceledOnTouchOutside(false);
        str.show();
    }

    private Map<String, AvailableDates> getAvailableDatesWithoutDuplicates(List<String> list) {
        list = new ArrayList(new LinkedHashSet(list));
        Collections.sort(list, new TestActivity$3(this));
        Map<String, AvailableDates> linkedHashMap = new LinkedHashMap();
        for (String str : list) {
            AvailableDates availableDates = new AvailableDates();
            availableDates.setAvailableDate(Utils.convertStringToDate("yyyy-MM-dd", str));
            availableDates.setSelectable(true);
            linkedHashMap.put(str, availableDates);
        }
        Log.e("avail", linkedHashMap.toString());
        return linkedHashMap;
    }

    private Map<String, ModifiedPrice> getModifiedDates(List<ModifiedPrice> list) {
        Map<String, ModifiedPrice> hashMap = new HashMap();
        for (ModifiedPrice modifiedPrice : list) {
            hashMap.put(modifiedPrice.getDate(), modifiedPrice);
        }
        Log.e("modify", hashMap.toString());
        return hashMap;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCalendarDates(UpdateCalendar updateCalendar) {
        if (updateCalendar != null) {
            this.calendarPickerView.setTitleTypeface(ResourcesCompat.getFont(this, R.font.sf_ui_display_regular));
        }
    }
}
