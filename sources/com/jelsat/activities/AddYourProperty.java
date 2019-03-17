package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.media.ExifInterface;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.amenitiesandhouserules.Limits;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.propertydetail.FullInfo;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.DataPlottingInAddpropertyEvent;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AddYourProperty extends BaseAppCompactActivity implements IAddYourPropertyView {
    String PropertyId;
    private AddPropertyRequest addPropertyRequest;
    @BindView(2131361850)
    Button addYourProperty;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361893)
    ImageView backArrowProperty;
    @BindView(2131361905)
    LinearLayout bathroomLay;
    int bathroomPosition = 0;
    int bathrooms;
    @BindView(2131361908)
    LinearLayout bedRoomLay;
    int bedroomPosition = 0;
    int beds;
    boolean changedData;
    String checkClick;
    boolean checkIntent;
    int doubleBed;
    int doubleBedPosition = 0;
    @BindView(2131362109)
    TextView doubleBedsTextview;
    @BindView(2131362110)
    LinearLayout doublebedLay;
    String editPropertyId;
    Bundle extraBundle;
    private FullInfo fullInfo;
    @BindView(2131362204)
    LinearLayout guestLayout;
    int kitchens;
    String mainStep;
    int maxGuests;
    int maxGuestsPosition = 0;
    @BindView(2131362358)
    Spinner noOfBathrooms;
    @BindView(2131362359)
    Spinner noOfBeds;
    @BindView(2131362360)
    Spinner noOfDoubleBeds;
    @BindView(2131362363)
    EditText noOfPersons;
    @BindView(2131362364)
    Spinner noOfPersonsSpinner;
    @BindView(2131362365)
    Spinner noOfRooms;
    @BindView(2131362366)
    Spinner noOfSingleBeds;
    @BindView(2131362367)
    Spinner noOfTents;
    String propertyTypeId;
    @BindView(2131362527)
    LinearLayout roomLay;
    int rooms;
    @BindView(2131362531)
    TextView saveAndExit;
    private SearchProperty searchProperty;
    SeedResponse seedResponse;
    String selectedBathroom;
    String selectedBed;
    String selectedDoublebed;
    String selectedPersons;
    String selectedRoom;
    String selectedSinglebed;
    String selectedTent;
    int singleBed;
    int singleBedposition = 0;
    @BindView(2131362597)
    TextView singleBedsTextview;
    @BindView(2131362598)
    LinearLayout singlebedLay;
    private Steps steps;
    String subStep;
    @BindView(2131362646)
    LinearLayout tentLay;
    int tents;
    int tentsPosition = 0;
    private final TextWatcher watcher = new AddYourProperty$8(this);

    public int getActivityLayout() {
        return R.layout.activity_add_your_property;
    }

    @OnClick({2131361893})
    public void backButton() {
        finish();
    }

    @OnClick({2131361850})
    public void addYourProperty() {
        this.checkClick = "nextbutton";
        this.addPropertyRequest = new AddPropertyRequest();
        if (this.selectedRoom != null) {
            this.addPropertyRequest.setNoOfRooms(this.selectedRoom);
        } else {
            this.selectedRoom = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfRooms(this.selectedRoom);
        }
        if (this.selectedTent != null) {
            this.addPropertyRequest.setNoOfTents(this.selectedTent);
        } else {
            this.selectedTent = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfTents(this.selectedTent);
        }
        if (this.selectedDoublebed != null) {
            this.selectedDoublebed = this.selectedDoublebed.split("\\s+")[0];
            if (this.selectedDoublebed.endsWith("+")) {
                this.selectedDoublebed = this.selectedDoublebed.replace("+", "");
            }
            this.addPropertyRequest.setNoOfDoublebeds(this.selectedDoublebed);
        } else {
            this.selectedDoublebed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfDoublebeds(this.selectedDoublebed);
        }
        if (this.selectedSinglebed != null) {
            this.selectedSinglebed = this.selectedSinglebed.split("\\s+")[0];
            if (this.selectedSinglebed.endsWith("+")) {
                this.selectedSinglebed = this.selectedSinglebed.replace("+", "");
            }
            this.addPropertyRequest.setNoOfSinglebeds(this.selectedSinglebed);
        } else {
            this.selectedSinglebed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfSinglebeds(this.selectedSinglebed);
        }
        if (this.selectedBed != null) {
            this.addPropertyRequest.setNoOfBeds(this.selectedBed);
        } else {
            this.selectedBed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfBeds(this.selectedBed);
        }
        if (this.selectedBathroom != null) {
            this.addPropertyRequest.setNoOfBathroom(this.selectedBathroom);
        } else {
            this.selectedBathroom = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfBathroom(this.selectedBathroom);
        }
        if (!this.propertyTypeId.equalsIgnoreCase("40")) {
            if (!this.propertyTypeId.equalsIgnoreCase("168")) {
                if (TextUtils.isEmpty(this.selectedPersons)) {
                    this.selectedPersons = AppEventsConstants.EVENT_PARAM_VALUE_YES;
                    this.addPropertyRequest.setMaxGuests(this.selectedPersons);
                } else {
                    this.addPropertyRequest.setMaxGuests(this.selectedPersons);
                }
                if (this.checkIntent) {
                    this.addPropertyRequest.setPropertyid(this.PropertyId);
                    this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
                    this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
                    return;
                }
                if (this.mainStep.equalsIgnoreCase("finished")) {
                    this.addPropertyRequest.setPropertyid(this.editPropertyId);
                    if (Integer.parseInt(this.mainStep) <= 0 || Integer.parseInt(this.subStep) < 2) {
                        this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                        this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
                    }
                } else {
                    this.addPropertyRequest.setPropertyid(this.editPropertyId);
                }
                if (this.fullInfo.getNoOfRooms().equalsIgnoreCase(this.selectedRoom) && this.fullInfo.getNoOfTents().equalsIgnoreCase(this.selectedTent) && this.fullInfo.getNoOfDoublebeds().equalsIgnoreCase(this.selectedDoublebed) && this.fullInfo.getNoOfSinglebeds().equalsIgnoreCase(this.selectedSinglebed) && this.fullInfo.getNoOfBeds().equalsIgnoreCase(this.selectedBed) && this.fullInfo.getNoOfBathrooms().equalsIgnoreCase(this.selectedBathroom)) {
                    if (!this.fullInfo.getMaxGuests().equalsIgnoreCase(this.selectedPersons)) {
                        Intent intent = new Intent(this, AmenitiesActivity.class);
                        this.extraBundle.putParcelable("searchProperty", this.searchProperty);
                        this.extraBundle.putParcelable("fullInfo", this.fullInfo);
                        this.extraBundle.putParcelable("steps", this.steps);
                        intent.putExtras(this.extraBundle);
                        intent.putExtra("checkIntent", true);
                        intent.setFlags(33554432);
                        startActivity(intent);
                        return;
                    }
                }
                this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
            }
        }
        this.selectedPersons = this.noOfPersons.getText().toString();
        if (!TextUtils.isEmpty(this.selectedPersons)) {
            this.addPropertyRequest.setMaxGuests(this.selectedPersons);
        }
        if (this.checkIntent) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
            this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
            return;
        }
        if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
        }
        if (!this.fullInfo.getMaxGuests().equalsIgnoreCase(this.selectedPersons)) {
            Intent intent2 = new Intent(this, AmenitiesActivity.class);
            this.extraBundle.putParcelable("searchProperty", this.searchProperty);
            this.extraBundle.putParcelable("fullInfo", this.fullInfo);
            this.extraBundle.putParcelable("steps", this.steps);
            intent2.putExtras(this.extraBundle);
            intent2.putExtra("checkIntent", true);
            intent2.setFlags(33554432);
            startActivity(intent2);
            return;
        }
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    @OnClick({2131362531})
    public void saveAndExit() {
        this.checkClick = "saveAndExit";
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) <= 0 || Integer.parseInt(this.subStep) < 2) {
                this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
            }
        }
        if (this.selectedRoom != null) {
            this.addPropertyRequest.setNoOfRooms(this.selectedRoom);
        } else {
            this.selectedRoom = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfRooms(this.selectedRoom);
        }
        if (this.selectedTent != null) {
            this.addPropertyRequest.setNoOfTents(this.selectedTent);
        } else {
            this.selectedTent = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfTents(this.selectedTent);
        }
        if (this.selectedDoublebed != null) {
            this.selectedDoublebed = this.selectedDoublebed.split("\\s+")[0];
            if (this.selectedDoublebed.endsWith("+")) {
                this.selectedDoublebed = this.selectedDoublebed.replace("+", "");
            }
            this.addPropertyRequest.setNoOfDoublebeds(this.selectedDoublebed);
        } else {
            this.selectedDoublebed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfDoublebeds(this.selectedDoublebed);
        }
        if (this.selectedSinglebed != null) {
            this.selectedSinglebed = this.selectedSinglebed.split("\\s+")[0];
            if (this.selectedSinglebed.endsWith("+")) {
                this.selectedSinglebed = this.selectedSinglebed.replace("+", "");
            }
            this.addPropertyRequest.setNoOfSinglebeds(this.selectedSinglebed);
        } else {
            this.selectedSinglebed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfSinglebeds(this.selectedSinglebed);
        }
        if (this.selectedBed != null) {
            this.addPropertyRequest.setNoOfBeds(this.selectedBed);
        } else {
            this.selectedBed = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfBeds(this.selectedBed);
        }
        if (this.selectedBathroom != null) {
            this.addPropertyRequest.setNoOfBathroom(this.selectedBathroom);
        } else {
            this.selectedBathroom = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.addPropertyRequest.setNoOfBathroom(this.selectedBathroom);
        }
        if (!this.propertyTypeId.equalsIgnoreCase("40")) {
            if (!this.propertyTypeId.equalsIgnoreCase("168")) {
                if (this.selectedPersons != null) {
                    this.addPropertyRequest.setMaxGuests(this.selectedPersons);
                } else {
                    this.selectedPersons = AppEventsConstants.EVENT_PARAM_VALUE_YES;
                    this.addPropertyRequest.setMaxGuests(this.selectedPersons);
                }
                this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
            }
        }
        this.selectedPersons = this.noOfPersons.getText().toString();
        if (!TextUtils.isEmpty(this.selectedPersons)) {
            this.addPropertyRequest.setMaxGuests(this.selectedPersons);
        }
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getIntent();
        this.checkIntent = bundle.getExtras().getBoolean("checkIntent");
        this.extraBundle = bundle.getExtras();
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            this.seedResponse = PrefUtils.getInstance().getSeedResponse();
        }
        Limits limits = this.seedResponse.getLimits();
        this.PropertyId = this.extraBundle.getString("PropertyId");
        this.propertyTypeId = this.extraBundle.getString("propertyTypeId");
        this.noOfPersons.addTextChangedListener(this.watcher);
        if (this.propertyTypeId.equalsIgnoreCase("40")) {
            this.tentLay.setVisibility(8);
            this.bedRoomLay.setVisibility(8);
            this.doublebedLay.setVisibility(8);
            this.singlebedLay.setVisibility(8);
            this.guestLayout.setVisibility(8);
            this.noOfPersons.setVisibility(0);
        } else if (this.propertyTypeId.equalsIgnoreCase("168")) {
            this.doubleBedsTextview.setText(getText(R.string.no_of_doublebeds_optional));
            this.singleBedsTextview.setText(getText(R.string.no_of_singlebeds_optional));
            this.tentLay.setVisibility(0);
            this.bedRoomLay.setVisibility(8);
            this.doublebedLay.setVisibility(0);
            this.singlebedLay.setVisibility(0);
            this.guestLayout.setVisibility(8);
            this.noOfPersons.setVisibility(0);
        } else {
            this.tentLay.setVisibility(8);
            this.bedRoomLay.setVisibility(0);
            this.doublebedLay.setVisibility(0);
            this.singlebedLay.setVisibility(0);
            this.guestLayout.setVisibility(0);
        }
        this.rooms = limits.getMaxRooms();
        this.beds = limits.getMaxBeds();
        this.bathrooms = limits.getMaxBathrooms();
        this.kitchens = limits.getMaxKitchens();
        this.tents = limits.getMaxTents();
        this.singleBed = limits.getMaxSinglebeds();
        this.doubleBed = limits.getMaxDoublebeds();
        this.maxGuests = limits.getMaxGuests();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.beds);
        stringBuilder.append(this.rooms);
        stringBuilder.append(this.bathrooms);
        stringBuilder.append(this.PropertyId);
        Log.d("Limits Values", stringBuilder.toString());
        List arrayList = new ArrayList();
        int i = 0;
        while (i <= this.rooms - 1) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(i);
            stringBuilder2.append(" ");
            stringBuilder2.append(getString(R.string.living_rooms_label));
            arrayList.add(stringBuilder2.toString());
            i++;
            if (i == this.rooms) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(this.rooms);
                stringBuilder2.append("+ ");
                stringBuilder2.append(getString(R.string.living_rooms_label));
                arrayList.add(stringBuilder2.toString());
            }
        }
        List arrayList2 = new ArrayList();
        arrayList2.add(0, getString(R.string.select_no_of_tents));
        int i2 = 1;
        while (i2 <= this.tents - 1) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(i2);
            stringBuilder3.append(" ");
            stringBuilder3.append(getString(R.string.tents_label));
            arrayList2.add(stringBuilder3.toString());
            i2++;
            if (i2 == this.tents) {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(this.tents);
                stringBuilder3.append("+ ");
                stringBuilder3.append(getString(R.string.tents_label));
                arrayList2.add(stringBuilder3.toString());
            }
        }
        List arrayList3 = new ArrayList();
        arrayList3.add(0, getString(R.string.select_no_of_bedrooms));
        int i3 = 1;
        while (i3 <= this.beds - 1) {
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append(i3);
            stringBuilder4.append(" ");
            stringBuilder4.append(getString(R.string.bed_room_label));
            arrayList3.add(stringBuilder4.toString());
            i3++;
            if (i3 == this.beds) {
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append(this.beds);
                stringBuilder4.append("+ ");
                stringBuilder4.append(getString(R.string.bed_room_label));
                arrayList3.add(stringBuilder4.toString());
            }
        }
        List arrayList4 = new ArrayList();
        arrayList4.add(0, getString(R.string.select_no_of_doublebeds));
        int i4 = 1;
        while (i4 <= this.doubleBed - 1) {
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append(i4);
            stringBuilder5.append(" ");
            stringBuilder5.append(getString(R.string.double_bed_label));
            arrayList4.add(stringBuilder5.toString());
            i4++;
            if (i4 == this.doubleBed) {
                stringBuilder5 = new StringBuilder();
                stringBuilder5.append(this.doubleBed);
                stringBuilder5.append("+ ");
                stringBuilder5.append(getString(R.string.double_bed_label));
                arrayList4.add(stringBuilder5.toString());
            }
        }
        List arrayList5 = new ArrayList();
        arrayList5.add(0, getString(R.string.select_no_of_singlebeds));
        int i5 = 1;
        while (i5 <= this.singleBed - 1) {
            StringBuilder stringBuilder6 = new StringBuilder();
            stringBuilder6.append(i5);
            stringBuilder6.append(" ");
            stringBuilder6.append(getString(R.string.single_bed_label));
            arrayList5.add(stringBuilder6.toString());
            i5++;
            if (i5 == this.singleBed) {
                stringBuilder6 = new StringBuilder();
                stringBuilder6.append(this.singleBed);
                stringBuilder6.append("+ ");
                stringBuilder6.append(getString(R.string.single_bed_label));
                arrayList5.add(stringBuilder6.toString());
            }
        }
        List arrayList6 = new ArrayList();
        arrayList6.add(0, getString(R.string.select_no_of_bathrooms));
        double[] dArr = new double[(this.bathrooms * 2)];
        for (int i6 = 1; i6 <= (this.bathrooms * 2) - 1; i6++) {
            dArr[i6] = (((double) i6) * 0.5d) + 0.5d;
            StringBuilder stringBuilder7 = new StringBuilder();
            stringBuilder7.append(dArr[i6]);
            stringBuilder7.append(" ");
            stringBuilder7.append(getString(R.string.bathrooms));
            arrayList6.add(stringBuilder7.toString());
        }
        List arrayList7 = new ArrayList();
        arrayList7.add(0, getString(R.string.select_no_of_guests));
        int i7 = 1;
        while (i7 <= this.maxGuests - 1) {
            StringBuilder stringBuilder8 = new StringBuilder();
            stringBuilder8.append(i7);
            stringBuilder8.append(" ");
            stringBuilder8.append(getString(R.string.publish_guests));
            arrayList7.add(stringBuilder8.toString());
            i7++;
            if (i7 == this.maxGuests) {
                stringBuilder8 = new StringBuilder();
                stringBuilder8.append(this.maxGuests);
                stringBuilder8.append("+ ");
                stringBuilder8.append(getString(R.string.guests));
                arrayList7.add(stringBuilder8.toString());
            }
        }
        SpinnerAdapter arrayAdapter = new ArrayAdapter(this, R.layout.add_your_property_spinner_text, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.add_property_simple_spinner_layout);
        this.noOfRooms.setAdapter(arrayAdapter);
        this.noOfRooms.setOnItemSelectedListener(new AddYourProperty$1(this));
        arrayAdapter = new ArrayAdapter(this, R.layout.add_your_property_spinner_text, arrayList2);
        arrayAdapter.setDropDownViewResource(R.layout.add_property_simple_spinner_layout);
        this.noOfTents.setAdapter(arrayAdapter);
        this.noOfTents.setOnItemSelectedListener(new AddYourProperty$2(this));
        SpinnerAdapter arrayAdapter2 = new ArrayAdapter(this, R.layout.add_your_property_spinner_text, arrayList4);
        arrayAdapter2.setDropDownViewResource(R.layout.add_property_simple_spinner_layout);
        this.noOfDoubleBeds.setAdapter(arrayAdapter2);
        this.noOfDoubleBeds.setOnItemSelectedListener(new AddYourProperty$3(this));
        arrayAdapter2 = new ArrayAdapter(this, R.layout.add_your_property_spinner_text, arrayList5);
        arrayAdapter2.setDropDownViewResource(R.layout.add_property_simple_spinner_layout);
        this.noOfSingleBeds.setAdapter(arrayAdapter2);
        this.noOfSingleBeds.setOnItemSelectedListener(new AddYourProperty$4(this));
        arrayAdapter2 = new ArrayAdapter(this, R.layout.add_your_property_spinner_text, arrayList3);
        arrayAdapter2.setDropDownViewResource(R.layout.add_property_simple_spinner_layout);
        this.noOfBeds.setAdapter(arrayAdapter2);
        this.noOfBeds.setOnItemSelectedListener(new AddYourProperty$5(this));
        arrayAdapter2 = new ArrayAdapter(this, R.layout.add_your_property_spinner_text, arrayList6);
        arrayAdapter2.setDropDownViewResource(R.layout.add_property_simple_spinner_layout);
        this.noOfBathrooms.setAdapter(arrayAdapter2);
        this.noOfBathrooms.setOnItemSelectedListener(new AddYourProperty$6(this));
        arrayAdapter2 = new ArrayAdapter(this, R.layout.add_your_property_spinner_text, arrayList7);
        arrayAdapter2.setDropDownViewResource(R.layout.add_property_simple_spinner_layout);
        this.noOfPersonsSpinner.setAdapter(arrayAdapter2);
        this.noOfPersonsSpinner.setOnItemSelectedListener(new AddYourProperty$7(this));
        if (this.checkIntent) {
            String noOfDoublebeds;
            String noOfSinglebeds;
            String noOfTents;
            String noOfBathrooms;
            CharSequence maxGuests;
            this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
            this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
            this.steps = (Steps) this.extraBundle.getParcelable("steps");
            this.changedData = bundle.getExtras().getBoolean("changedData");
            this.mainStep = this.steps.getStep();
            this.subStep = this.steps.getSubstep();
            this.editPropertyId = this.searchProperty.getPropertyId();
            String str = null;
            if (this.changedData != null) {
                str = this.fullInfo.getNoOfRooms();
                bundle = this.fullInfo.getNoOfBeds();
                noOfDoublebeds = this.fullInfo.getNoOfDoublebeds();
                noOfSinglebeds = this.fullInfo.getNoOfSinglebeds();
                noOfTents = this.fullInfo.getNoOfTents();
                noOfBathrooms = this.fullInfo.getNoOfBathrooms();
                maxGuests = this.fullInfo.getMaxGuests();
                visibleButton();
            } else {
                invisibleButton();
                bundle = null;
                noOfDoublebeds = bundle;
                noOfSinglebeds = noOfDoublebeds;
                noOfTents = noOfSinglebeds;
                noOfBathrooms = noOfTents;
                maxGuests = noOfBathrooms;
            }
            if (this.fullInfo.getMaxGuests() == null || this.fullInfo.getMaxGuests().equalsIgnoreCase("") || this.fullInfo.getNoOfBathrooms().equalsIgnoreCase("")) {
                invisibleButton();
            } else {
                if (!TextUtils.isEmpty(str)) {
                    this.noOfRooms.setSelection(Integer.parseInt(str));
                    this.selectedRoom = str;
                }
                if (!TextUtils.isEmpty(noOfTents)) {
                    this.noOfTents.setSelection(Integer.parseInt(noOfTents));
                    this.selectedTent = noOfTents;
                }
                if (!TextUtils.isEmpty(noOfSinglebeds)) {
                    this.noOfSingleBeds.setSelection(Integer.parseInt(noOfSinglebeds));
                    this.selectedSinglebed = noOfSinglebeds;
                }
                if (!TextUtils.isEmpty(noOfDoublebeds)) {
                    this.noOfDoubleBeds.setSelection(Integer.parseInt(noOfDoublebeds));
                    this.selectedDoublebed = noOfDoublebeds;
                }
                if (!TextUtils.isEmpty(bundle)) {
                    this.noOfBeds.setSelection(Integer.parseInt(bundle));
                    this.selectedBed = bundle;
                }
                this.noOfBathrooms.setSelection(((int) ((Float.valueOf(noOfBathrooms).floatValue() * 1073741824) - 1073741824)) + 1);
                this.selectedBathroom = noOfBathrooms;
                if (this.propertyTypeId.equalsIgnoreCase("40") == null) {
                    if (this.propertyTypeId.equalsIgnoreCase("168") == null) {
                        if (Integer.parseInt(maxGuests) < 17) {
                            this.noOfPersonsSpinner.setSelection(Integer.parseInt(maxGuests));
                            this.selectedPersons = maxGuests;
                        } else {
                            this.noOfPersonsSpinner.setSelection(1);
                            this.selectedPersons = AppEventsConstants.EVENT_PARAM_VALUE_YES;
                        }
                        visibleButton();
                    }
                }
                this.noOfPersons.setText(maxGuests);
                visibleButton();
            }
        }
    }

    private void invisibleButton() {
        this.saveAndExit.setVisibility(8);
        this.addYourProperty.setEnabled(false);
        this.addYourProperty.setBackgroundResource(R.drawable.button_backgound);
    }

    private void visibleButton() {
        this.saveAndExit.setVisibility(0);
        this.addYourProperty.setEnabled(true);
        this.addYourProperty.setBackgroundResource(R.drawable.button_selected_background);
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDataPloting(DataPlottingInAddpropertyEvent dataPlottingInAddpropertyEvent) {
        if (dataPlottingInAddpropertyEvent != null && !TextUtils.isEmpty(dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getPropertyTypeId())) {
            if (this.propertyTypeId.equalsIgnoreCase(dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getPropertyTypeId())) {
                String noOfRooms = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getNoOfRooms();
                String noOfBeds = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getNoOfBeds();
                String noOfDoublebeds = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getNoOfDoublebeds();
                String noOfSinglebeds = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getNoOfSinglebeds();
                String noOfTents = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getNoOfTents();
                String noOfBathrooms = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getNoOfBathrooms();
                CharSequence maxGuests = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getMaxGuests();
                if (maxGuests == null || maxGuests.equalsIgnoreCase("") || noOfBathrooms.equalsIgnoreCase("") || noOfTents.equalsIgnoreCase("")) {
                    invisibleButton();
                } else {
                    this.noOfRooms.setSelection(Integer.parseInt(noOfRooms));
                    this.selectedRoom = noOfRooms;
                    this.noOfTents.setSelection(Integer.parseInt(noOfTents));
                    this.selectedTent = noOfTents;
                    this.noOfSingleBeds.setSelection(Integer.parseInt(noOfSinglebeds));
                    this.selectedSinglebed = noOfSinglebeds;
                    this.noOfDoubleBeds.setSelection(Integer.parseInt(noOfDoublebeds));
                    this.selectedDoublebed = noOfDoublebeds;
                    this.noOfBeds.setSelection(Integer.parseInt(noOfBeds));
                    this.selectedBed = noOfBeds;
                    this.noOfBathrooms.setSelection(((int) ((Float.valueOf(noOfBathrooms).floatValue() * 2.0f) - 2.0f)) + 1);
                    this.selectedBathroom = noOfBathrooms;
                    if (!this.propertyTypeId.equalsIgnoreCase("40")) {
                        if (!this.propertyTypeId.equalsIgnoreCase("168")) {
                            if (Integer.parseInt(maxGuests) < 17) {
                                this.noOfPersonsSpinner.setSelection(Integer.parseInt(maxGuests));
                                this.selectedPersons = maxGuests;
                            } else {
                                this.noOfPersonsSpinner.setSelection(1);
                                this.selectedPersons = AppEventsConstants.EVENT_PARAM_VALUE_YES;
                            }
                            visibleButton();
                        }
                    }
                    this.noOfPersons.setText(maxGuests);
                    visibleButton();
                }
            }
            EventBus.getDefault().removeStickyEvent(dataPlottingInAddpropertyEvent);
        }
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        EventBus.getDefault().postSticky(new DataPlottingInAddpropertyEvent(addPropertyResponse));
        if (this.checkIntent != null) {
            this.fullInfo.setNoOfRooms(this.selectedRoom);
            this.fullInfo.setNoOfBeds(this.selectedBed);
            this.fullInfo.setNoOfSinglebeds(this.selectedSinglebed);
            this.fullInfo.setNoOfDoublebeds(this.selectedDoublebed);
            this.fullInfo.setNoOfBathrooms(this.selectedBathroom);
            this.fullInfo.setNoOfTents(this.selectedTent);
            this.fullInfo.setMaxGuests(this.selectedPersons);
            if (this.checkClick.equals("nextbutton") != null) {
                addPropertyResponse = new Intent(this, AmenitiesActivity.class);
                this.extraBundle.putParcelable("searchProperty", this.searchProperty);
                this.extraBundle.putParcelable("fullInfo", this.fullInfo);
                this.extraBundle.putParcelable("steps", this.steps);
                addPropertyResponse.putExtras(this.extraBundle);
                addPropertyResponse.putExtra("checkIntent", true);
                addPropertyResponse.setFlags(33554432);
                startActivity(addPropertyResponse);
            } else if (this.checkClick.equals("saveAndExit") != null) {
                addPropertyResponse = new Intent(this, DashBoardActivity.class);
                addPropertyResponse.addFlags(67108864);
                addPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
                startActivity(addPropertyResponse);
            }
        } else if (this.checkClick.equals("nextbutton") != null) {
            addPropertyResponse = new Intent(this, AmenitiesActivity.class);
            addPropertyResponse.putExtras(this.extraBundle);
            addPropertyResponse.putExtra("checkIntent", false);
            addPropertyResponse.setFlags(33554432);
            startActivity(addPropertyResponse);
        } else if (this.checkClick.equals("saveAndExit") != null) {
            addPropertyResponse = new Intent(this, DashBoardActivity.class);
            addPropertyResponse.addFlags(67108864);
            addPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
            startActivity(addPropertyResponse);
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
