package com.jelsat.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.media.ExifInterface;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.propertydetail.FullInfo;
import com.data.propertypricing.PropertyPricingRequest;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.DataPlottingInAddpropertyEvent;
import java.util.Calendar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MinimumNightsToBook extends BaseAppCompactActivity implements OnClickListener, IAddYourPropertyView {
    String PropertyId;
    private AddPropertyRequest addPropertyRequest;
    private AddPropertyResponse addPropertyResponse1;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361889)
    TextView availabilityPricing;
    @BindView(2131361891)
    ImageView backButton;
    @BindView(2131361914)
    Spinner bookingType;
    String bookingTypeString;
    @BindView(2131361956)
    LinearLayout calenderLayout;
    String cancelId;
    String cancelName;
    @BindView(2131361966)
    EditText cancelationPolicesEdt;
    String checkClick;
    @BindView(2131361987)
    EditText checkInTimeEdt;
    Boolean checkIntent = Boolean.valueOf(false);
    @BindView(2131361988)
    EditText checkOutTimeEdt;
    String editPropertyId;
    @BindView(2131362118)
    LinearLayout editTimeLayout;
    Bundle extraBundle;
    private TimePickerDialog fromTimePickerDialog;
    private FullInfo fullInfo;
    int itemAtPosition;
    String mainStep;
    @BindView(2131362331)
    Spinner minNights;
    int nightsCount;
    private PropertyPricingRequest propertyPricingRequest;
    @BindView(2131362519)
    Button reviewProperty;
    @BindView(2131362531)
    TextView saveAndExit;
    private SearchProperty searchProperty;
    SeedResponse seedResponse;
    String selectedNights;
    private Steps steps;
    String subStep;
    private TimePickerDialog toTimePickerDialog;
    private final TextWatcher watcher = new MinimumNightsToBook$5(this);

    public int getActivityLayout() {
        return R.layout.minimumnights_layout;
    }

    @OnClick({2131361891})
    public void backbutton() {
        finish();
    }

    @OnClick({2131361889})
    public void availabilityPricing() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.PROPERTY_ID, this.editPropertyId);
        bundle.putBoolean(StringConstants.IS_EDIT_PROPERTY, true);
        goToActivity(CalendarActivity.class, bundle);
    }

    @OnClick({2131361966})
    public void cancelationpolicy() {
        Intent intent = new Intent(this, CancelationPolices.class);
        if (!this.cancelationPolicesEdt.getText().toString().equalsIgnoreCase("")) {
            intent.putExtra("checkSelectionCancelId", true);
            intent.putExtra("CancelTypeName", this.cancelationPolicesEdt.getText().toString());
        }
        intent.putExtras(this.extraBundle);
        startActivityForResult(intent, 103);
    }

    @OnClick({2131362519})
    public void reviewYourProperty() {
        this.checkClick = "nextbutton";
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent.booleanValue()) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_3D);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            this.addPropertyRequest.setCheckInTime(this.checkInTimeEdt.getText().toString());
            this.addPropertyRequest.setCheckOutTime(this.checkOutTimeEdt.getText().toString());
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) < 3 || Integer.parseInt(this.subStep) < 2) {
                this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_3D);
                this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
            }
        }
        this.addPropertyRequest.setMinNights(this.selectedNights);
        this.addPropertyRequest.setBookingType(this.bookingTypeString);
        this.addPropertyRequest.setCancellationPolicy(this.cancelId);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    @OnClick({2131362531})
    public void saveAndExit() {
        this.checkClick = "saveAndExit";
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent.booleanValue()) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_3D);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            this.addPropertyRequest.setCheckInTime(this.checkInTimeEdt.getText().toString());
            this.addPropertyRequest.setCheckOutTime(this.checkOutTimeEdt.getText().toString());
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) < 3 || Integer.parseInt(this.subStep) < 2) {
                this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_3D);
                this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
            }
        }
        this.addPropertyRequest.setMinNights(this.selectedNights);
        this.addPropertyRequest.setBookingType(this.bookingTypeString);
        this.addPropertyRequest.setCancellationPolicy(this.cancelId);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onCreate(@android.support.annotation.Nullable android.os.Bundle r6) {
        /*
        r5 = this;
        super.onCreate(r6);
        r6 = r5.checkInTimeEdt;
        r0 = 0;
        r6.setInputType(r0);
        r6 = r5.checkOutTimeEdt;
        r6.setInputType(r0);
        r6 = r5.getIntent();
        r1 = r6.getExtras();
        r5.extraBundle = r1;
        r1 = r5.calenderLayout;
        r2 = 8;
        r1.setVisibility(r2);
        r6 = r6.getExtras();
        r1 = "checkIntent";
        r6 = r6.getBoolean(r1);
        r6 = java.lang.Boolean.valueOf(r6);
        r5.checkIntent = r6;
        r6 = com.data.utils.PrefUtils.getInstance();
        r6 = r6.getSeedResponse();
        if (r6 == 0) goto L_0x0043;
    L_0x0039:
        r6 = com.data.utils.PrefUtils.getInstance();
        r6 = r6.getSeedResponse();
        r5.seedResponse = r6;
    L_0x0043:
        r6 = r5.seedResponse;
        r6 = r6.getLimits();
        r1 = r5.extraBundle;
        r2 = "property_id";
        r1 = r1.getString(r2);
        r5.PropertyId = r1;
        r6 = r6.getMinNights();
        r5.nightsCount = r6;
        r6 = r5.cancelationPolicesEdt;
        r1 = r5.watcher;
        r6.addTextChangedListener(r1);
        r6 = "Limits Values";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r5.nightsCount;
        r1.append(r2);
        r1 = r1.toString();
        android.util.Log.d(r6, r1);
        r6 = new java.util.ArrayList;
        r6.<init>();
        r1 = 1;
        r2 = 1;
    L_0x007a:
        r3 = r5.nightsCount;
        if (r2 > r3) goto L_0x009f;
    L_0x007e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r4 = " ";
        r3.append(r4);
        r4 = 2131820677; // 0x7f110085 float:1.9274076E38 double:1.0532593596E-314;
        r4 = r5.getString(r4);
        r3.append(r4);
        r3 = r3.toString();
        r6.add(r3);
        r2 = r2 + 1;
        goto L_0x007a;
    L_0x009f:
        r2 = new android.widget.ArrayAdapter;
        r3 = 2131558476; // 0x7f0d004c float:1.8742269E38 double:1.053129815E-314;
        r2.<init>(r5, r3, r6);
        r6 = 2131558474; // 0x7f0d004a float:1.8742265E38 double:1.053129814E-314;
        r2.setDropDownViewResource(r6);
        r6 = r5.minNights;
        r6.setAdapter(r2);
        r6 = r5.minNights;
        r2 = new com.jelsat.activities.MinimumNightsToBook$1;
        r2.<init>(r5);
        r6.setOnItemSelectedListener(r2);
        r6 = 3;
        r2 = new java.lang.String[r6];
        r3 = 2131821348; // 0x7f110324 float:1.9275437E38 double:1.053259691E-314;
        r3 = r5.getString(r3);
        r2[r0] = r3;
        r0 = r5.seedResponse;
        r0 = r0.getBookingDesc();
        r0 = r0.getInstant();
        r0 = android.text.Html.fromHtml(r0);
        r0 = r0.toString();
        r2[r1] = r0;
        r0 = 2;
        r1 = r5.seedResponse;
        r1 = r1.getBookingDesc();
        r1 = r1.get24hours();
        r1 = android.text.Html.fromHtml(r1);
        r1 = r1.toString();
        r2[r0] = r1;
        r6 = new int[r6];
        r6 = {2131689473, 2131231280, 2131231191};
        r0 = new com.jelsat.adapters.spinneradapter;
        r0.<init>(r5, r6, r2);
        r6 = r5.bookingType;
        r6.setAdapter(r0);
        r6 = r5.bookingType;
        r0 = new com.jelsat.activities.MinimumNightsToBook$2;
        r0.<init>(r5);
        r6.setOnItemSelectedListener(r0);
        r6 = r5.extraBundle;
        if (r6 == 0) goto L_0x0111;
    L_0x010e:
        r5.plotData();
    L_0x0111:
        r6 = r5.checkIntent;
        r6 = r6.booleanValue();
        if (r6 == 0) goto L_0x011c;
    L_0x0119:
        r5.plotData();
    L_0x011c:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.activities.MinimumNightsToBook.onCreate(android.os.Bundle):void");
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void plotData() {
        this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
        this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
        this.propertyPricingRequest = (PropertyPricingRequest) this.extraBundle.getParcelable("pricing");
        this.steps = (Steps) this.extraBundle.getParcelable("steps");
        this.mainStep = this.steps.getStep();
        this.subStep = this.steps.getSubstep();
        this.editPropertyId = this.searchProperty.getPropertyId();
        String minDays = this.fullInfo.getMinDays();
        String type = this.searchProperty.getType();
        if (this.mainStep.equalsIgnoreCase("finished")) {
            this.editTimeLayout.setVisibility(0);
            setTimePickerField();
            if (this.propertyPricingRequest != null) {
                this.checkInTimeEdt.setText(this.propertyPricingRequest.getCheckInTime());
                this.checkOutTimeEdt.setText(this.propertyPricingRequest.getCheckOutTime());
            }
        } else {
            this.editTimeLayout.setVisibility(8);
        }
        if (minDays != null && type != null && !minDays.equalsIgnoreCase("")) {
            this.minNights.setSelection(Integer.parseInt(minDays) - 1);
            this.selectedNights = minDays;
            int parseInt = Integer.parseInt(type);
            if (parseInt == 0) {
                this.bookingType.setSelection(2);
            } else if (parseInt == 1) {
                this.bookingType.setSelection(1);
            } else {
                this.bookingType.setSelection(0);
            }
            this.bookingTypeString = type;
            this.cancelationPolicesEdt.setText(this.fullInfo.getCancellationPolicy());
        }
    }

    private void setTimePickerField() {
        this.checkInTimeEdt.setOnClickListener(this);
        this.checkOutTimeEdt.setOnClickListener(this);
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int i2 = i;
        int i3 = instance.get(12);
        this.fromTimePickerDialog = new TimePickerDialog(this, new MinimumNightsToBook$3(this), i2, i3, false);
        this.fromTimePickerDialog.setButton(-1, getString(R.string.ok), this.fromTimePickerDialog);
        this.fromTimePickerDialog.setButton(-2, getString(R.string.cancellation_policy_txt), this.fromTimePickerDialog);
        this.toTimePickerDialog = new TimePickerDialog(this, new MinimumNightsToBook$4(this), i2, i3, false);
        this.toTimePickerDialog.setButton(-1, getString(R.string.ok), this.toTimePickerDialog);
        this.toTimePickerDialog.setButton(-2, getString(R.string.cancellation_policy_txt), this.toTimePickerDialog);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 103 && intent != null) {
            this.cancelName = intent.getStringExtra("cancelTypeName");
            this.cancelId = intent.getStringExtra("cancelTypeId");
            this.cancelationPolicesEdt.setText(this.cancelName);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDataPloting(DataPlottingInAddpropertyEvent dataPlottingInAddpropertyEvent) {
        if (dataPlottingInAddpropertyEvent != null) {
            String minDays = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getMinDays();
            String type = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getBasicInfo().getType();
            if (minDays != null && type != null && !minDays.equalsIgnoreCase("")) {
                this.minNights.setSelection(Integer.parseInt(minDays) - 1);
                this.selectedNights = minDays;
                int parseInt = Integer.parseInt(type);
                if (parseInt == 0) {
                    this.bookingType.setSelection(2);
                } else if (parseInt == 1) {
                    this.bookingType.setSelection(1);
                } else {
                    this.bookingType.setSelection(0);
                }
                this.bookingTypeString = type;
                this.cancelationPolicesEdt.setText(dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getCancellationPolicy());
            }
        }
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        if (this.checkClick.equals("nextbutton")) {
            this.addPropertyResponse1 = addPropertyResponse;
            EventBus.getDefault().postSticky(new DataPlottingInAddpropertyEvent(this.addPropertyResponse1));
            this.fullInfo = this.addPropertyResponse1.getFullInfo();
            addPropertyResponse = this.addPropertyResponse1.getBasicInfo();
            Intent intent = new Intent(this, PublishPropertyActivity.class);
            Bundle bundle = new Bundle();
            if (this.checkIntent.booleanValue()) {
                intent.putExtra("checkIntent", true);
                bundle.putString("PropertyId", this.editPropertyId);
            } else {
                bundle.putString("PropertyId", this.PropertyId);
                intent.putExtra("checkIntent", false);
            }
            bundle.putParcelable("fullInfo", this.fullInfo);
            bundle.putParcelable("basicInfo", addPropertyResponse);
            intent.putExtras(bundle);
            intent.setFlags(33554432);
            startActivity(intent);
            return;
        }
        if (this.checkClick.equals("saveAndExit") != null) {
            addPropertyResponse = new Intent(this, DashBoardActivity.class);
            addPropertyResponse.addFlags(67108864);
            addPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
            startActivity(addPropertyResponse);
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

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onClick(View view) {
        if (view == this.checkInTimeEdt) {
            this.fromTimePickerDialog.show();
            return;
        }
        if (view == this.checkOutTimeEdt) {
            this.toTimePickerDialog.show();
        }
    }
}
