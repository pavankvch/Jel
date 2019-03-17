package com.jelsat.fragments;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.propertycostcalendar.UpdateCalendarRequest;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseDialogFragment;
import com.jelsat.customclasses.CustomEditText;
import java.util.Calendar;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

public class WeekDaysUpdateDialogFragment extends BaseDialogFragment {
    private static final String IS_COST_CALENDAR = "is_cost_calendar";
    private static final String MONTH_LIMIT = "month_limit";
    private static final String PROPERTY_ID = "property_id";
    private static final String WEEK_DAY = "week_day";
    @BindView(2131362490)
    RadioGroup availabilityRadioGroup;
    Calendar[] fromDate;
    @BindView(2131362191)
    TextInputLayout fromDateTextInputLayout;
    @BindView(2131362730)
    CustomEditText fromDateTv;
    private boolean isCostCalendar;
    private int monthsLimit;
    @BindView(2131362398)
    RelativeLayout parentView;
    @BindView(2131362146)
    CustomEditText priceEt;
    @BindView(2131362433)
    TextInputLayout priceTextInputLayout;
    private int propertyId;
    @BindView(2131362672)
    TextInputLayout toDateTextInputLayout;
    @BindView(2131362780)
    CustomEditText toDateTv;
    private String weekday;

    public int getDialogFragmentLayoutId() {
        return R.layout.update_week;
    }

    @OnClick({2131362730})
    public void clickOnFromDate() {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        this.fromDate = new Calendar[1];
        Locale.setDefault(Locale.US);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                WeekDaysUpdateDialogFragment.this.fromDate[0] = Calendar.getInstance();
                WeekDaysUpdateDialogFragment.this.fromDate[0].set(i, i2, i3);
                WeekDaysUpdateDialogFragment.this.fromDateTv.setText(String.format("%s-%s-%s", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2 + 1), Integer.valueOf(i)}));
                WeekDaysUpdateDialogFragment.this.toDateTv.setEnabled(true);
                WeekDaysUpdateDialogFragment.this.toDateTv.setText("");
            }
        }, instance.get(1), instance.get(2), instance.get(5));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        instance2.add(2, this.monthsLimit - 1);
        datePickerDialog.getDatePicker().setMaxDate(instance2.getTimeInMillis());
        datePickerDialog.show();
    }

    @OnClick({2131362780})
    public void clickOnToDate() {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        Locale.setDefault(Locale.US);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                WeekDaysUpdateDialogFragment.this.toDateTv.setText(String.format("%s-%s-%s", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2 + 1), Integer.valueOf(i)}));
            }
        }, instance.get(1), instance.get(2), instance.get(5));
        if (this.fromDate != null) {
            datePickerDialog.getDatePicker().setMinDate(this.fromDate[0].getTimeInMillis() - 1000);
        } else {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        instance2.add(2, this.monthsLimit - 1);
        datePickerDialog.getDatePicker().setMaxDate(instance2.getTimeInMillis());
        datePickerDialog.show();
    }

    @OnClick({2131362800})
    public void clickOnUpdate() {
        if (!fieldValidation()) {
            return;
        }
        if (this.isCostCalendar || this.availabilityRadioGroup.getCheckedRadioButtonId() != -1) {
            UpdateCalendarRequest updateCalendarRequest = new UpdateCalendarRequest();
            updateCalendarRequest.setPropertyId(this.propertyId);
            updateCalendarRequest.setStartDate(this.fromDateTv.getText().toString().trim());
            updateCalendarRequest.setEndDate(this.toDateTv.getText().toString());
            updateCalendarRequest.setWeeklyDay(this.weekday);
            if (this.isCostCalendar) {
                updateCalendarRequest.setPrice(Float.parseFloat(this.priceEt.getText().toString().trim()));
                updateCalendarRequest.setType("pricing");
            } else {
                updateCalendarRequest.setStatus(getSelectedStatus());
                updateCalendarRequest.setType("availability");
            }
            EventBus.getDefault().post(updateCalendarRequest);
            dismiss();
            return;
        }
        showToast(getString(R.string.select_at_least_one_option));
    }

    @OnClick({2131362246})
    public void clickOnClose() {
        dismiss();
    }

    public static WeekDaysUpdateDialogFragment newInstance(int i, String str, int i2, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("property_id", i);
        bundle.putString(WEEK_DAY, str);
        bundle.putInt(MONTH_LIMIT, i2);
        bundle.putBoolean(IS_COST_CALENDAR, z);
        i = new WeekDaysUpdateDialogFragment();
        i.setArguments(bundle);
        return i;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        return bundle;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.fromDateTextInputLayout.setHintEnabled(false);
        this.toDateTextInputLayout.setHintEnabled(false);
        this.priceTextInputLayout.setHintEnabled(false);
        this.toDateTv.setEnabled(false);
        this.availabilityRadioGroup.clearCheck();
        if (getArguments() != null) {
            this.propertyId = getArguments().getInt("property_id", 0);
            this.isCostCalendar = getArguments().getBoolean(IS_COST_CALENDAR, false);
            this.weekday = getArguments().getString(WEEK_DAY, "");
            this.monthsLimit = getArguments().getInt(MONTH_LIMIT, 0);
        }
        return layoutInflater;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.isCostCalendar != null) {
            this.priceTextInputLayout.setVisibility(0);
            this.priceEt.setVisibility(0);
            this.availabilityRadioGroup.setVisibility(8);
            return;
        }
        this.availabilityRadioGroup.setVisibility(0);
        this.priceEt.setVisibility(8);
        this.priceTextInputLayout.setVisibility(8);
    }

    private int getSelectedStatus() {
        int indexOfChild = this.availabilityRadioGroup.indexOfChild(this.availabilityRadioGroup.findViewById(this.availabilityRadioGroup.getCheckedRadioButtonId()));
        Log.d("ccc", String.valueOf(indexOfChild));
        return indexOfChild == 0 ? 1 : 2;
    }

    private boolean fieldValidation() {
        boolean z;
        this.fromDateTextInputLayout.setErrorEnabled(false);
        this.toDateTextInputLayout.setErrorEnabled(false);
        this.priceTextInputLayout.setErrorEnabled(false);
        CharSequence trim = this.fromDateTv.getText().toString().trim();
        CharSequence trim2 = this.toDateTv.getText().toString().trim();
        String trim3 = this.priceEt.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.fromDateTextInputLayout.setError(getString(R.string.select_from_date_label));
            z = false;
        } else {
            z = true;
        }
        if (TextUtils.isEmpty(trim2)) {
            this.toDateTextInputLayout.setError(getString(R.string.select_to_date_label));
            z = false;
        }
        if (this.isCostCalendar) {
            if (!TextUtils.isEmpty(trim3)) {
                if (!trim3.equalsIgnoreCase(".")) {
                    if (Float.parseFloat(trim3) < 1.0f) {
                        this.priceTextInputLayout.setError(getString(R.string.atleast_price_label));
                        return false;
                    } else if (Float.parseFloat(trim3) > 9999.0f) {
                        this.priceTextInputLayout.setError(getString(R.string.price_must_lessthan_tenthousand));
                        return false;
                    }
                }
            }
            this.priceTextInputLayout.setError(getString(R.string.enter_price_label));
            return false;
        }
        return z;
    }
}
