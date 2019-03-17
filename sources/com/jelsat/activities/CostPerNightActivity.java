package com.jelsat.activities;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.media.ExifInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.businesslogic.propertypricing.IPropertyPricing;
import com.businesslogic.propertypricing.PropertyPricingPresenter;
import com.data.propertydetail.FullInfo;
import com.data.propertypricing.Price;
import com.data.propertypricing.PropertyPricingRequest;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CostPerNightActivity extends BaseAppCompactActivity implements OnClickListener, IPropertyPricing {
    @BindView(2131361893)
    ImageView backArrowProperty;
    final Calendar c = Calendar.getInstance();
    String checkClick;
    @BindView(2131361987)
    EditText checkInTimeEdt;
    boolean checkIntent;
    @BindView(2131361988)
    EditText checkOutTimeEdt;
    @BindView(2131361994)
    CheckBox checkboxFri;
    @BindView(2131361995)
    CheckBox checkboxMon;
    @BindView(2131361996)
    CheckBox checkboxSat;
    @BindView(2131361997)
    CheckBox checkboxSun;
    @BindView(2131361998)
    CheckBox checkboxThur;
    @BindView(2131361999)
    CheckBox checkboxTue;
    @BindView(2131362000)
    CheckBox checkboxWed;
    @BindView(2131362037)
    Button costpnNextButton;
    private SimpleDateFormat dateFormatter;
    @BindView(2131362079)
    TextView daysCount;
    @BindView(2131362113)
    EditText editFri;
    @BindView(2131362114)
    EditText editMon;
    String editPropertyId;
    @BindView(2131362115)
    EditText editSat;
    @BindView(2131362116)
    EditText editSun;
    @BindView(2131362117)
    EditText editThur;
    @BindView(2131362119)
    EditText editTue;
    @BindView(2131362120)
    EditText editWed;
    Bundle extraBundle;
    Calendar[] fromDate;
    @BindView(2131362190)
    EditText fromDateEdtTx;
    private DatePickerDialog fromDatePickerDialog;
    private TimePickerDialog fromTimePickerDialog;
    private FullInfo fullInfo;
    int hour = this.c.get(11);
    private int minCal = 0;
    int minute = this.c.get(12);
    Calendar newCalendar;
    private Price prices;
    private PropertyPricingPresenter propertyPricingPresenter = new PropertyPricingPresenter(this, RetrofitClient.getAPIService());
    private PropertyPricingRequest propertyPricingRequest;
    String property_id;
    @BindView(2131362531)
    TextView saveAndExit;
    private SearchProperty searchProperty;
    Calendar[] toDate;
    @BindView(2131362671)
    EditText toDateEdtTx;
    private DatePickerDialog toDatePickerDialog;
    private TimePickerDialog toTimePickerDialog;
    private final TextWatcher watcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!(i == 0 || i3 == 0 || (((CostPerNightActivity.this.editSun.getText().toString().trim().length() == 0 || Float.valueOf(CostPerNightActivity.this.editSun.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editMon.getText().toString().trim().length() == 0 || Float.valueOf(CostPerNightActivity.this.editMon.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editTue.getText().toString().trim().length() == 0 || Float.valueOf(CostPerNightActivity.this.editTue.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editWed.getText().toString().trim().length() == 0 || Float.valueOf(CostPerNightActivity.this.editWed.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editThur.getText().toString().trim().length() == 0 || Float.valueOf(CostPerNightActivity.this.editThur.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editFri.getText().toString().trim().length() == 0 || Float.valueOf(CostPerNightActivity.this.editFri.getText().toString()).floatValue() < 1065353216) && (CostPerNightActivity.this.editSat.getText().toString().trim().length() == 0 || Float.valueOf(CostPerNightActivity.this.editSat.getText().toString()).floatValue() < 1065353216))))))) || CostPerNightActivity.this.fromDateEdtTx.getText().toString().trim().length() == 0 || CostPerNightActivity.this.toDateEdtTx.getText().toString().trim().length() == 0 || CostPerNightActivity.this.checkInTimeEdt.getText().toString().trim().length() == 0))) {
                if (CostPerNightActivity.this.checkOutTimeEdt.getText().toString().trim().length() != 0) {
                    CostPerNightActivity.this.saveAndExit.setVisibility(0);
                    CostPerNightActivity.this.costpnNextButton.setEnabled(1);
                    CostPerNightActivity.this.costpnNextButton.setBackgroundResource(R.drawable.button_selected_background);
                    return;
                }
            }
            CostPerNightActivity.this.costpnNextButton.setEnabled(false);
            CostPerNightActivity.this.costpnNextButton.setBackgroundResource(R.drawable.button_backgound);
            CostPerNightActivity.this.saveAndExit.setVisibility(8);
        }

        public void afterTextChanged(Editable editable) {
            if (!(((CostPerNightActivity.this.editSun.getText().toString().trim().length() == null || Float.valueOf(CostPerNightActivity.this.editSun.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editMon.getText().toString().trim().length() == null || Float.valueOf(CostPerNightActivity.this.editMon.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editTue.getText().toString().trim().length() == null || Float.valueOf(CostPerNightActivity.this.editTue.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editWed.getText().toString().trim().length() == null || Float.valueOf(CostPerNightActivity.this.editWed.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editThur.getText().toString().trim().length() == null || Float.valueOf(CostPerNightActivity.this.editThur.getText().toString()).floatValue() < 1065353216) && ((CostPerNightActivity.this.editFri.getText().toString().trim().length() == null || Float.valueOf(CostPerNightActivity.this.editFri.getText().toString()).floatValue() < 1065353216) && (CostPerNightActivity.this.editSat.getText().toString().trim().length() == null || Float.valueOf(CostPerNightActivity.this.editSat.getText().toString()).floatValue() < 1065353216))))))) || CostPerNightActivity.this.fromDateEdtTx.getText().toString().trim().length() == null || CostPerNightActivity.this.toDateEdtTx.getText().toString().trim().length() == null || CostPerNightActivity.this.checkInTimeEdt.getText().toString().trim().length() == null)) {
                if (CostPerNightActivity.this.checkOutTimeEdt.getText().toString().trim().length() != null) {
                    CostPerNightActivity.this.saveAndExit.setVisibility(0);
                    CostPerNightActivity.this.costpnNextButton.setEnabled(true);
                    CostPerNightActivity.this.costpnNextButton.setBackgroundResource(R.drawable.button_selected_background);
                    return;
                }
            }
            CostPerNightActivity.this.costpnNextButton.setEnabled(false);
            CostPerNightActivity.this.costpnNextButton.setBackgroundResource(R.drawable.button_backgound);
            CostPerNightActivity.this.saveAndExit.setVisibility(8);
        }
    };

    public int getActivityLayout() {
        return R.layout.costpernight_layout;
    }

    @OnClick({2131361893})
    public void backbutton() {
        finish();
    }

    @OnCheckedChanged({2131361997, 2131361995, 2131361999, 2131362000, 2131361998, 2131361994, 2131361996})
    public void onRadioCheckChanged(CheckBox checkBox) {
        boolean isChecked = checkBox.isChecked();
        switch (checkBox.getId()) {
            case R.id.checkboxFri:
                if (isChecked) {
                    this.editFri.setEnabled(true);
                    this.editFri.setInputType(2);
                    return;
                }
                this.editFri.setEnabled(false);
                this.editFri.setError(null);
                this.editFri.setText("");
                return;
            case R.id.checkboxMon:
                if (isChecked) {
                    this.editMon.setEnabled(true);
                    this.editMon.setInputType(2);
                    return;
                }
                this.editMon.setEnabled(false);
                this.editMon.setError(null);
                this.editMon.setText("");
                return;
            case R.id.checkboxSat:
                if (!isChecked) {
                    this.editSat.setEnabled(false);
                    this.editSat.setError(null);
                    this.editSat.setText("");
                    break;
                }
                this.editSat.setEnabled(true);
                this.editSat.setInputType(2);
                return;
            case R.id.checkboxSun:
                if (isChecked) {
                    this.editSun.setEnabled(true);
                    this.editSun.setInputType(2);
                    return;
                }
                this.editSun.setEnabled(false);
                this.editSun.setError(null);
                this.editSun.setText("");
                return;
            case R.id.checkboxThur:
                if (isChecked) {
                    this.editThur.setEnabled(true);
                    this.editThur.setInputType(2);
                    return;
                }
                this.editThur.setEnabled(false);
                this.editThur.setError(null);
                this.editThur.setText("");
                return;
            case R.id.checkboxTue:
                if (isChecked) {
                    this.editTue.setEnabled(true);
                    this.editTue.setInputType(2);
                    return;
                }
                this.editTue.setEnabled(false);
                this.editTue.setError(null);
                this.editTue.setText("");
                return;
            case R.id.checkboxWed:
                if (isChecked) {
                    this.editWed.setEnabled(true);
                    this.editWed.setInputType(2);
                    return;
                }
                this.editWed.setEnabled(false);
                this.editWed.setError(null);
                this.editWed.setText("");
                return;
            default:
                break;
        }
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.checkInTimeEdt.setInputType(0);
        this.checkOutTimeEdt.setInputType(0);
        this.fromDateEdtTx.setInputType(0);
        this.toDateEdtTx.setInputType(0);
        this.toDateEdtTx.setEnabled(false);
        this.checkOutTimeEdt.setEnabled(false);
        this.dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        bundle = getIntent();
        this.extraBundle = bundle.getExtras();
        this.property_id = bundle.getExtras().getString(StringConstants.PROPERTY_ID);
        this.checkIntent = bundle.getExtras().getBoolean("checkIntent");
        if (this.extraBundle != null) {
            plotData();
        }
        if (this.checkIntent != null) {
            plotData();
        }
        setDateTimeField();
        setTimePickerField();
        this.editSun.setImeOptions(6);
        this.editSun.setRawInputType(1);
        this.editMon.setImeOptions(6);
        this.editMon.setRawInputType(1);
        this.editTue.setImeOptions(6);
        this.editTue.setRawInputType(1);
        this.editWed.setImeOptions(6);
        this.editWed.setRawInputType(1);
        this.editThur.setImeOptions(6);
        this.editThur.setRawInputType(1);
        this.editFri.setImeOptions(6);
        this.editFri.setRawInputType(1);
        this.editSat.setImeOptions(6);
        this.editSat.setRawInputType(1);
        this.editSun.addTextChangedListener(this.watcher);
        this.editMon.addTextChangedListener(this.watcher);
        this.editTue.addTextChangedListener(this.watcher);
        this.editWed.addTextChangedListener(this.watcher);
        this.editThur.addTextChangedListener(this.watcher);
        this.editFri.addTextChangedListener(this.watcher);
        this.editSat.addTextChangedListener(this.watcher);
        this.checkInTimeEdt.addTextChangedListener(this.watcher);
        this.checkOutTimeEdt.addTextChangedListener(this.watcher);
        this.fromDateEdtTx.addTextChangedListener(this.watcher);
        this.toDateEdtTx.addTextChangedListener(this.watcher);
    }

    private void plotData() {
        this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
        this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
        this.propertyPricingRequest = (PropertyPricingRequest) this.extraBundle.getParcelable("pricing");
        this.editPropertyId = this.searchProperty.getPropertyId();
        if (this.propertyPricingRequest != null) {
            this.checkInTimeEdt.setText(this.propertyPricingRequest.getCheckInTime());
            this.checkOutTimeEdt.setText(this.propertyPricingRequest.getCheckOutTime());
            this.fromDateEdtTx.setText(convertYearformatToDayformat(this.propertyPricingRequest.getStartDate()));
            this.toDateEdtTx.setText(convertYearformatToDayformat(this.propertyPricingRequest.getEndDate()));
            if (!this.propertyPricingRequest.getPrice().get0().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.editSun.setText(this.propertyPricingRequest.getPrice().get0());
                this.checkboxSun.setChecked(true);
            }
            if (!this.propertyPricingRequest.getPrice().get1().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.editMon.setText(this.propertyPricingRequest.getPrice().get1());
                this.checkboxMon.setChecked(true);
            }
            if (!this.propertyPricingRequest.getPrice().get2().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.editTue.setText(this.propertyPricingRequest.getPrice().get2());
                this.checkboxTue.setChecked(true);
            }
            if (!this.propertyPricingRequest.getPrice().get3().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.editWed.setText(this.propertyPricingRequest.getPrice().get3());
                this.checkboxWed.setChecked(true);
            }
            if (!this.propertyPricingRequest.getPrice().get4().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.editThur.setText(this.propertyPricingRequest.getPrice().get4());
                this.checkboxThur.setChecked(true);
            }
            if (!this.propertyPricingRequest.getPrice().get5().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.editFri.setText(this.propertyPricingRequest.getPrice().get5());
                this.checkboxFri.setChecked(true);
            }
            if (!this.propertyPricingRequest.getPrice().get6().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.editSat.setText(this.propertyPricingRequest.getPrice().get6());
                this.checkboxSat.setChecked(true);
            }
            this.saveAndExit.setVisibility(0);
            this.costpnNextButton.setEnabled(true);
            this.costpnNextButton.setBackgroundResource(R.drawable.button_selected_background);
        }
    }

    private String convertYearformatToDayformat(String str) {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new SimpleDateFormat("dd-MM-yyyy").format(simpleDateFormat.parse(str));
        } catch (String str2) {
            str2.printStackTrace();
            return null;
        }
    }

    private void setTimePickerField() {
        this.checkInTimeEdt.setOnClickListener(this);
        this.checkOutTimeEdt.setOnClickListener(this);
        this.fromTimePickerDialog = new TimePickerDialog(this, new OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                CostPerNightActivity.this.getHoursCalucaltion((i * 60) + i2);
                CostPerNightActivity.this.checkInTimeEdt.setText(CostPerNightActivity.this.getTime(i, i2));
                CostPerNightActivity.this.checkOutTimeEdt.setText("");
                CostPerNightActivity.this.checkOutTimeEdt.setEnabled(1);
            }
        }, this.hour, this.minute, false);
        this.fromTimePickerDialog.setButton(-1, getString(R.string.ok), this.fromTimePickerDialog);
        this.fromTimePickerDialog.setButton(-2, getString(R.string.cancellation_policy_txt), this.fromTimePickerDialog);
        this.toTimePickerDialog = new TimePickerDialog(this, new OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                if ((i * 60) + i2 < CostPerNightActivity.this.minCal) {
                    CostPerNightActivity.this.checkOutTimeEdt.setText(CostPerNightActivity.this.getTime(i, i2));
                    return;
                }
                CostPerNightActivity.this.checkOutTimeEdt.setText("");
                CostPerNightActivity.this.showToast(CostPerNightActivity.this.getString(R.string.check_in_time_check_out_cond));
            }
        }, this.hour, this.minute, false);
        this.toTimePickerDialog.setButton(-1, getString(R.string.ok), this.toTimePickerDialog);
        this.toTimePickerDialog.setButton(-2, getString(R.string.cancellation_policy_txt), this.toTimePickerDialog);
    }

    private String getTime(int i, int i2) {
        return new SimpleDateFormat("h:mm a").format(new Time(i, i2, 0));
    }

    private void getHoursCalucaltion(int i) {
        this.minCal = i;
    }

    private void setDateTimeField() {
        this.fromDateEdtTx.setOnClickListener(this);
        this.toDateEdtTx.setOnClickListener(this);
        this.newCalendar = Calendar.getInstance();
        this.fromDate = new Calendar[1];
        this.toDate = new Calendar[1];
        Locale.setDefault(Locale.US);
        this.fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                CostPerNightActivity.this.fromDate[0] = Calendar.getInstance();
                CostPerNightActivity.this.fromDate[0].set(i, i2, i3);
                CostPerNightActivity.this.fromDateEdtTx.setText(CostPerNightActivity.this.dateFormatter.format(CostPerNightActivity.this.fromDate[0].getTime()));
                CostPerNightActivity.this.toDateEdtTx.setEnabled(1);
                CostPerNightActivity.this.toDateEdtTx.setText("");
            }
        }, this.newCalendar.get(1), this.newCalendar.get(2), this.newCalendar.get(5));
        this.newCalendar.add(2, 12);
        this.fromDatePickerDialog.getDatePicker().setMaxDate(this.newCalendar.getTimeInMillis());
        this.fromDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        this.fromDatePickerDialog.setButton(-1, getString(R.string.ok), this.fromDatePickerDialog);
        this.fromDatePickerDialog.setButton(-2, getString(R.string.cancellation_policy_txt), this.fromDatePickerDialog);
    }

    @OnClick({2131362531})
    public void saveAndExit() {
        this.checkClick = "saveAndExit";
        sendDataToServer();
    }

    @OnClick({2131362037})
    public void costpnNextButton() {
        this.checkClick = "nextbutton";
        sendDataToServer();
    }

    private void sendDataToServer() {
        String format;
        String format2;
        ParseException e;
        boolean isChecked;
        int i;
        int i2;
        int i3;
        this.prices = new Price();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            format = simpleDateFormat2.format(simpleDateFormat.parse(this.fromDateEdtTx.getText().toString()));
            try {
                format2 = simpleDateFormat2.format(simpleDateFormat.parse(this.toDateEdtTx.getText().toString()));
            } catch (ParseException e2) {
                e = e2;
                e.printStackTrace();
                format2 = null;
                this.propertyPricingRequest = new PropertyPricingRequest();
                if (TextUtils.isEmpty(this.editSat.getText().toString())) {
                    this.prices.set6(this.editSat.getText().toString());
                } else {
                    this.prices.set6(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                if (TextUtils.isEmpty(this.editFri.getText().toString())) {
                    this.prices.set5(this.editFri.getText().toString());
                } else {
                    this.prices.set5(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                if (TextUtils.isEmpty(this.editThur.getText().toString())) {
                    this.prices.set4(this.editThur.getText().toString());
                } else {
                    this.prices.set4(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                if (TextUtils.isEmpty(this.editWed.getText().toString())) {
                    this.prices.set3(this.editWed.getText().toString());
                } else {
                    this.prices.set3(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                if (TextUtils.isEmpty(this.editTue.getText().toString())) {
                    this.prices.set2(this.editTue.getText().toString());
                } else {
                    this.prices.set2(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                if (TextUtils.isEmpty(this.editMon.getText().toString())) {
                    this.prices.set1(this.editMon.getText().toString());
                } else {
                    this.prices.set1(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                if (TextUtils.isEmpty(this.editSun.getText().toString())) {
                    this.prices.set0(this.editSun.getText().toString());
                } else {
                    this.prices.set0(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                this.propertyPricingRequest.setCheckInTime(this.checkInTimeEdt.getText().toString());
                this.propertyPricingRequest.setCheckOutTime(this.checkOutTimeEdt.getText().toString());
                this.propertyPricingRequest.setEndDate(format2);
                this.propertyPricingRequest.setPrice(this.prices);
                this.propertyPricingRequest.setPropertyId(Integer.parseInt(this.property_id));
                this.propertyPricingRequest.setStartDate(format);
                this.propertyPricingRequest.setStep(ExifInterface.GPS_MEASUREMENT_3D);
                this.propertyPricingRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                isChecked = this.checkboxSun.isChecked();
                i = 0;
                if (!TextUtils.isEmpty(this.editSun.getText().toString())) {
                }
                if ((isChecked ^ i2) != 0) {
                    isChecked = this.checkboxMon.isChecked();
                    if (!TextUtils.isEmpty(this.editMon.getText().toString())) {
                    }
                    if ((isChecked ^ i3) != 0) {
                        isChecked = this.checkboxTue.isChecked();
                        if (!TextUtils.isEmpty(this.editTue.getText().toString())) {
                        }
                        if ((isChecked ^ i3) != 0) {
                            isChecked = this.checkboxWed.isChecked();
                            if (!TextUtils.isEmpty(this.editWed.getText().toString())) {
                            }
                            if ((isChecked ^ i3) != 0) {
                                isChecked = this.checkboxThur.isChecked();
                                if (!TextUtils.isEmpty(this.editThur.getText().toString())) {
                                }
                                if ((isChecked ^ i3) != 0) {
                                    isChecked = this.checkboxFri.isChecked();
                                    if (!TextUtils.isEmpty(this.editFri.getText().toString())) {
                                    }
                                    if ((isChecked ^ i3) != 0) {
                                        isChecked = this.checkboxSat.isChecked();
                                        i = 1;
                                        if ((isChecked ^ i) != 0) {
                                            this.propertyPricingPresenter.getPropertyPricingData(getString(R.string.please_wait), this.propertyPricingRequest);
                                        } else {
                                            this.editSat.setError(getString(R.string.home_field_not_empty_label));
                                        }
                                    }
                                    this.editFri.setError(getString(R.string.home_field_not_empty_label));
                                    return;
                                }
                                this.editThur.setError(getString(R.string.home_field_not_empty_label));
                                return;
                            }
                            this.editWed.setError(getString(R.string.home_field_not_empty_label));
                            return;
                        }
                        this.editTue.setError(getString(R.string.home_field_not_empty_label));
                        return;
                    }
                    this.editMon.setError(getString(R.string.home_field_not_empty_label));
                    return;
                }
                this.editSun.setError(getString(R.string.home_field_not_empty_label));
                return;
            }
        } catch (ParseException e3) {
            e = e3;
            format = null;
            e.printStackTrace();
            format2 = null;
            this.propertyPricingRequest = new PropertyPricingRequest();
            if (TextUtils.isEmpty(this.editSat.getText().toString())) {
                this.prices.set6(this.editSat.getText().toString());
            } else {
                this.prices.set6(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            if (TextUtils.isEmpty(this.editFri.getText().toString())) {
                this.prices.set5(this.editFri.getText().toString());
            } else {
                this.prices.set5(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            if (TextUtils.isEmpty(this.editThur.getText().toString())) {
                this.prices.set4(this.editThur.getText().toString());
            } else {
                this.prices.set4(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            if (TextUtils.isEmpty(this.editWed.getText().toString())) {
                this.prices.set3(this.editWed.getText().toString());
            } else {
                this.prices.set3(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            if (TextUtils.isEmpty(this.editTue.getText().toString())) {
                this.prices.set2(this.editTue.getText().toString());
            } else {
                this.prices.set2(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            if (TextUtils.isEmpty(this.editMon.getText().toString())) {
                this.prices.set1(this.editMon.getText().toString());
            } else {
                this.prices.set1(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            if (TextUtils.isEmpty(this.editSun.getText().toString())) {
                this.prices.set0(this.editSun.getText().toString());
            } else {
                this.prices.set0(AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            this.propertyPricingRequest.setCheckInTime(this.checkInTimeEdt.getText().toString());
            this.propertyPricingRequest.setCheckOutTime(this.checkOutTimeEdt.getText().toString());
            this.propertyPricingRequest.setEndDate(format2);
            this.propertyPricingRequest.setPrice(this.prices);
            this.propertyPricingRequest.setPropertyId(Integer.parseInt(this.property_id));
            this.propertyPricingRequest.setStartDate(format);
            this.propertyPricingRequest.setStep(ExifInterface.GPS_MEASUREMENT_3D);
            this.propertyPricingRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            isChecked = this.checkboxSun.isChecked();
            i = 0;
            if (TextUtils.isEmpty(this.editSun.getText().toString())) {
            }
            if ((isChecked ^ i2) != 0) {
                this.editSun.setError(getString(R.string.home_field_not_empty_label));
                return;
            }
            isChecked = this.checkboxMon.isChecked();
            if (TextUtils.isEmpty(this.editMon.getText().toString())) {
            }
            if ((isChecked ^ i3) != 0) {
                this.editMon.setError(getString(R.string.home_field_not_empty_label));
                return;
            }
            isChecked = this.checkboxTue.isChecked();
            if (TextUtils.isEmpty(this.editTue.getText().toString())) {
            }
            if ((isChecked ^ i3) != 0) {
                this.editTue.setError(getString(R.string.home_field_not_empty_label));
                return;
            }
            isChecked = this.checkboxWed.isChecked();
            if (TextUtils.isEmpty(this.editWed.getText().toString())) {
            }
            if ((isChecked ^ i3) != 0) {
                this.editWed.setError(getString(R.string.home_field_not_empty_label));
                return;
            }
            isChecked = this.checkboxThur.isChecked();
            if (TextUtils.isEmpty(this.editThur.getText().toString())) {
            }
            if ((isChecked ^ i3) != 0) {
                this.editThur.setError(getString(R.string.home_field_not_empty_label));
                return;
            }
            isChecked = this.checkboxFri.isChecked();
            if (TextUtils.isEmpty(this.editFri.getText().toString())) {
            }
            if ((isChecked ^ i3) != 0) {
                this.editFri.setError(getString(R.string.home_field_not_empty_label));
                return;
            }
            isChecked = this.checkboxSat.isChecked();
            i = 1;
            if ((isChecked ^ i) != 0) {
                this.editSat.setError(getString(R.string.home_field_not_empty_label));
            } else {
                this.propertyPricingPresenter.getPropertyPricingData(getString(R.string.please_wait), this.propertyPricingRequest);
            }
        }
        this.propertyPricingRequest = new PropertyPricingRequest();
        if (TextUtils.isEmpty(this.editSat.getText().toString())) {
            this.prices.set6(this.editSat.getText().toString());
        } else {
            this.prices.set6(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (TextUtils.isEmpty(this.editFri.getText().toString())) {
            this.prices.set5(this.editFri.getText().toString());
        } else {
            this.prices.set5(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (TextUtils.isEmpty(this.editThur.getText().toString())) {
            this.prices.set4(this.editThur.getText().toString());
        } else {
            this.prices.set4(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (TextUtils.isEmpty(this.editWed.getText().toString())) {
            this.prices.set3(this.editWed.getText().toString());
        } else {
            this.prices.set3(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (TextUtils.isEmpty(this.editTue.getText().toString())) {
            this.prices.set2(this.editTue.getText().toString());
        } else {
            this.prices.set2(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (TextUtils.isEmpty(this.editMon.getText().toString())) {
            this.prices.set1(this.editMon.getText().toString());
        } else {
            this.prices.set1(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        if (TextUtils.isEmpty(this.editSun.getText().toString())) {
            this.prices.set0(this.editSun.getText().toString());
        } else {
            this.prices.set0(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.propertyPricingRequest.setCheckInTime(this.checkInTimeEdt.getText().toString());
        this.propertyPricingRequest.setCheckOutTime(this.checkOutTimeEdt.getText().toString());
        this.propertyPricingRequest.setEndDate(format2);
        this.propertyPricingRequest.setPrice(this.prices);
        this.propertyPricingRequest.setPropertyId(Integer.parseInt(this.property_id));
        this.propertyPricingRequest.setStartDate(format);
        this.propertyPricingRequest.setStep(ExifInterface.GPS_MEASUREMENT_3D);
        this.propertyPricingRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        isChecked = this.checkboxSun.isChecked();
        i = 0;
        i2 = (TextUtils.isEmpty(this.editSun.getText().toString()) || Integer.parseInt(this.editSun.getText().toString()) <= 0) ? 0 : 1;
        if ((isChecked ^ i2) != 0) {
            this.editSun.setError(getString(R.string.home_field_not_empty_label));
            return;
        }
        isChecked = this.checkboxMon.isChecked();
        i3 = (TextUtils.isEmpty(this.editMon.getText().toString()) || Integer.parseInt(this.editMon.getText().toString()) <= 0) ? 0 : 1;
        if ((isChecked ^ i3) != 0) {
            this.editMon.setError(getString(R.string.home_field_not_empty_label));
            return;
        }
        isChecked = this.checkboxTue.isChecked();
        i3 = (TextUtils.isEmpty(this.editTue.getText().toString()) || Integer.parseInt(this.editTue.getText().toString()) <= 0) ? 0 : 1;
        if ((isChecked ^ i3) != 0) {
            this.editTue.setError(getString(R.string.home_field_not_empty_label));
            return;
        }
        isChecked = this.checkboxWed.isChecked();
        i3 = (TextUtils.isEmpty(this.editWed.getText().toString()) || Integer.parseInt(this.editWed.getText().toString()) <= 0) ? 0 : 1;
        if ((isChecked ^ i3) != 0) {
            this.editWed.setError(getString(R.string.home_field_not_empty_label));
            return;
        }
        isChecked = this.checkboxThur.isChecked();
        i3 = (TextUtils.isEmpty(this.editThur.getText().toString()) || Integer.parseInt(this.editThur.getText().toString()) <= 0) ? 0 : 1;
        if ((isChecked ^ i3) != 0) {
            this.editThur.setError(getString(R.string.home_field_not_empty_label));
            return;
        }
        isChecked = this.checkboxFri.isChecked();
        i3 = (TextUtils.isEmpty(this.editFri.getText().toString()) || Integer.parseInt(this.editFri.getText().toString()) <= 0) ? 0 : 1;
        if ((isChecked ^ i3) != 0) {
            this.editFri.setError(getString(R.string.home_field_not_empty_label));
            return;
        }
        isChecked = this.checkboxSat.isChecked();
        if (!TextUtils.isEmpty(this.editSat.getText().toString()) && Integer.parseInt(this.editSat.getText().toString()) > 0) {
            i = 1;
        }
        if ((isChecked ^ i) != 0) {
            this.editSat.setError(getString(R.string.home_field_not_empty_label));
        } else {
            this.propertyPricingPresenter.getPropertyPricingData(getString(R.string.please_wait), this.propertyPricingRequest);
        }
    }

    public void onSuccess() {
        Intent intent;
        if (this.checkClick.equals("nextbutton")) {
            intent = new Intent(this, MinimumNightsToBook.class);
            if (this.checkIntent) {
                intent.putExtra("checkIntent", true);
                intent.putExtra("editPropertyId", this.editPropertyId);
            } else {
                intent.putExtra("checkIntent", false);
            }
            intent.putExtras(this.extraBundle);
            intent.putExtra("costPerNight", "");
            intent.setFlags(33554432);
            startActivity(intent);
            return;
        }
        if (this.checkClick.equals("saveAndExit")) {
            intent = new Intent(this, DashBoardActivity.class);
            intent.addFlags(67108864);
            intent.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
            startActivity(intent);
        }
    }

    public void onError(APIError aPIError) {
        dismissProgress();
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onClick(View view) {
        if (view == this.fromDateEdtTx) {
            this.fromDatePickerDialog.show();
        } else if (view == this.toDateEdtTx) {
            view = Calendar.getInstance();
            this.toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {
                public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                    CostPerNightActivity.this.toDate[0] = Calendar.getInstance();
                    CostPerNightActivity.this.toDate[0].set(i, i2, i3);
                    CostPerNightActivity.this.toDatePickerDialog.getDatePicker().setMinDate(CostPerNightActivity.this.fromDate[0].getTimeInMillis() - 1000);
                    if ((CostPerNightActivity.this.toDate[0].getTimeInMillis() - CostPerNightActivity.this.fromDate[0].getTimeInMillis()) / 86400000 > 0) {
                        CostPerNightActivity.this.toDateEdtTx.setText(CostPerNightActivity.this.dateFormatter.format(CostPerNightActivity.this.toDate[0].getTime()));
                        CostPerNightActivity.this.daysCount.setVisibility(0);
                        long timeInMillis = (CostPerNightActivity.this.toDate[0].getTimeInMillis() - CostPerNightActivity.this.fromDate[0].getTimeInMillis()) / 86400000;
                        datePicker = CostPerNightActivity.this.daysCount;
                        i = new StringBuilder("(");
                        i.append(String.valueOf(timeInMillis));
                        i.append(" ");
                        i.append(CostPerNightActivity.this.getString(R.string.pricing_days_selected));
                        i.append(")");
                        datePicker.setText(i.toString());
                        return;
                    }
                    CostPerNightActivity.this.showToast(CostPerNightActivity.this.getString(R.string.plz_select_todate));
                }
            }, view.get(1), view.get(2), view.get(5));
            view.add(2, 12);
            this.toDatePickerDialog.getDatePicker().setMaxDate(view.getTimeInMillis());
            if (this.fromDate != null) {
                this.toDatePickerDialog.getDatePicker().setMinDate(this.fromDate[0].getTimeInMillis() - 1000);
            } else {
                this.toDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            }
            this.toDatePickerDialog.setButton(-1, getString(R.string.ok), this.toDatePickerDialog);
            this.toDatePickerDialog.setButton(-2, getString(R.string.cancellation_policy_txt), this.toDatePickerDialog);
            this.toDatePickerDialog.show();
        } else if (view == this.checkInTimeEdt) {
            this.fromTimePickerDialog.show();
        } else {
            if (view == this.checkOutTimeEdt) {
                this.toTimePickerDialog.show();
            }
        }
    }
}
