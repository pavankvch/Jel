package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.addproperty.AddPropertyRequest;
import com.data.amenitiesandhouserules.Limits;
import com.data.propertydetail.FullInfo;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.utils.BottomSheetData;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.AddPropertyRoomsAndGuestEvent;
import com.jelsat.fragments.BottomSheetFragment;
import com.jelsat.utils.AddPropertyRoomsAndGuestSUtil;
import com.jelsat.utils.DialogUtil;
import com.jelsat.widgets.FancyButton;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AddPropertyRoomsAndGuestsActivity extends BaseAppCompactActivity {
    private AddPropertyRequest addPropertyRequest;
    @BindView(2131361907)
    TextInputLayout bathRoomsTextInputLayout;
    @BindView(2131361906)
    TextInputEditText bathroomsEditText;
    @BindView(2131361909)
    TextInputEditText bedRoomsEditText;
    @BindView(2131361910)
    TextInputLayout bedRoomsTextInputLayout;
    OnClickListener clickListener = new AddPropertyRoomsAndGuestsActivity$1(this);
    private DialogUtil dialogUtil;
    @BindView(2131362106)
    TextInputEditText doubleBedsEditText;
    @BindView(2131362108)
    TextInputLayout doubleBedsTextInputLayout;
    private boolean isFromEdit;
    private Limits limits;
    @BindView(2131362326)
    TextInputEditText maxGuestsEditText;
    @BindView(2131362327)
    TextInputLayout maxGuestsTextInputLayout;
    @BindView(2131362357)
    FancyButton nextButton;
    @BindView(2131362594)
    TextInputEditText singleBedsEditText;
    @BindView(2131362596)
    TextInputLayout singleBedsTextInputLayout;
    @BindView(2131362647)
    TextInputEditText tentsEditText;
    @BindView(2131362648)
    TextInputLayout tentsTextInputLayout;

    public int getActivityLayout() {
        return R.layout.activity_add_property_rooms_and_guests;
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131362531})
    public void clickOnSaveAndExit() {
        Log.w("qwa", "save and exit");
    }

    @OnClick({2131361909})
    public void clickOnBedRoomsEditText() {
        if (this.limits != null) {
            AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil = new AddPropertyRoomsAndGuestSUtil(12);
            BottomSheetFragment newInstance = BottomSheetFragment.newInstance(getData(this.limits.getMaxBeds(), getString(R.string.bed_room_label)), getString(R.string.no_of_beds));
            newInstance.setData(addPropertyRoomsAndGuestSUtil);
            newInstance.show(getSupportFragmentManager(), "bed rooms");
        }
    }

    @OnClick({2131362647})
    public void clickOnTentsEditText() {
        if (this.limits != null) {
            AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil = new AddPropertyRoomsAndGuestSUtil(13);
            BottomSheetFragment newInstance = BottomSheetFragment.newInstance(getData(this.limits.getMaxTents(), getString(R.string.tents_label)), getString(R.string.no_of_tents));
            newInstance.setData(addPropertyRoomsAndGuestSUtil);
            newInstance.show(getSupportFragmentManager(), "tents");
        }
    }

    @OnClick({2131362106})
    public void clickOnDoubleBedsEditText() {
        if (this.limits != null) {
            AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil = new AddPropertyRoomsAndGuestSUtil(14);
            BottomSheetFragment newInstance = BottomSheetFragment.newInstance(getData(this.limits.getMaxDoublebeds(), getString(R.string.double_bed_label)), getString(R.string.no_of_doublebeds));
            newInstance.setData(addPropertyRoomsAndGuestSUtil);
            newInstance.show(getSupportFragmentManager(), "double beds");
        }
    }

    @OnClick({2131362594})
    public void clickOnSingleBedsEditText() {
        if (this.limits != null) {
            AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil = new AddPropertyRoomsAndGuestSUtil(15);
            BottomSheetFragment newInstance = BottomSheetFragment.newInstance(getData(this.limits.getMaxSinglebeds(), getString(R.string.single_bed_label)), getString(R.string.no_of_singlebeds));
            newInstance.setData(addPropertyRoomsAndGuestSUtil);
            newInstance.show(getSupportFragmentManager(), "single beds");
        }
    }

    @OnClick({2131361906})
    public void clickOnBathRoomsEditText() {
        if (this.limits != null) {
            AddPropertyRoomsAndGuestSUtil addPropertyRoomsAndGuestSUtil = new AddPropertyRoomsAndGuestSUtil(16);
            BottomSheetFragment newInstance = BottomSheetFragment.newInstance(getBathRoomsData(this.limits.getMaxBathrooms(), getString(R.string.bathrooms)), String.format("%s \n <small>%s</small>", new Object[]{getString(R.string.no_of_bathrooms), getString(R.string.bath_rooms_text)}));
            newInstance.setData(addPropertyRoomsAndGuestSUtil);
            newInstance.show(getSupportFragmentManager(), "bathrooms");
        }
    }

    @OnClick({2131362357})
    public void clickOnNextButton() {
        if (this.nextButton != null && this.nextButton.isEnabled() && this.nextButton.isClickable()) {
            if (isNetworkConnected()) {
                new Bundle().putBoolean(StringConstants.IS_FROM_EDIT_PROPERTY, this.isFromEdit);
                goToActivity(AddPropertyRoomsAndGuestsActivity.class);
                return;
            }
            this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
        }
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.dialogUtil = new DialogUtil(this);
        if (!(getIntent() == null || getIntent().hasExtra(StringConstants.IS_FROM_EDIT_PROPERTY) == null)) {
            this.isFromEdit = getIntent().getBooleanExtra(StringConstants.IS_FROM_EDIT_PROPERTY, false);
        }
        bundle = PrefUtils.getInstance().getSeedResponse();
        if (bundle != null) {
            this.limits = bundle.getLimits();
        }
        this.addPropertyRequest = new AddPropertyRequest();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getAdPropertyRequest(AddPropertyRequest addPropertyRequest) {
        if (addPropertyRequest != null) {
            this.addPropertyRequest = addPropertyRequest;
            this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
            EventBus.getDefault().removeStickyEvent(addPropertyRequest);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getAddPropertyData(PropertyDetailResponse propertyDetailResponse) {
        if (propertyDetailResponse != null) {
            FullInfo fullInfo = propertyDetailResponse.getFullInfo();
            Log.w("qwa", "Rooms and Guests");
            this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
            this.addPropertyRequest.setPropertyid(propertyDetailResponse.getPropertyInfo().getPropertyId());
            selectFieldsBasedOnPropertyType(fullInfo.getPropertyTypeId(), fullInfo);
            EventBus.getDefault().removeStickyEvent(propertyDetailResponse);
        }
    }

    private void selectFieldsBasedOnPropertyType(String str, FullInfo fullInfo) {
        if (fullInfo != null) {
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != 1660) {
                if (hashCode == 48819) {
                    if (str.equals("168") != null) {
                        obj = null;
                    }
                }
            } else if (str.equals("40") != null) {
                obj = 1;
            }
            switch (obj) {
                case null:
                    this.bedRoomsTextInputLayout.setVisibility(8);
                    this.maxGuestsEditText.setInputType(2);
                    this.maxGuestsEditText.setFocusable(false);
                    this.maxGuestsEditText.setCursorVisible(true);
                    this.maxGuestsEditText.setFocusableInTouchMode(true);
                    this.maxGuestsEditText.setOnClickListener(null);
                    if (TextUtils.isEmpty(fullInfo.getNoOfTents()) == null && fullInfo.getNoOfTents().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                        this.tentsEditText.setText(String.format("%s %s", new Object[]{fullInfo.getNoOfTents(), getString(R.string.tents_label)}));
                        this.addPropertyRequest.setNoOfTents(fullInfo.getNoOfTents());
                    }
                    if (TextUtils.isEmpty(fullInfo.getNoOfDoublebeds()) == null && fullInfo.getNoOfDoublebeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                        this.doubleBedsEditText.setText(String.format("%s %s", new Object[]{fullInfo.getNoOfDoublebeds(), getString(R.string.double_bed_label)}));
                        this.addPropertyRequest.setNoOfDoublebeds(fullInfo.getNoOfDoublebeds());
                    }
                    if (TextUtils.isEmpty(fullInfo.getNoOfSinglebeds()) == null && fullInfo.getNoOfSinglebeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                        this.singleBedsEditText.setText(String.format("%s %s", new Object[]{fullInfo.getNoOfSinglebeds(), getString(R.string.single_bed_label)}));
                        this.addPropertyRequest.setNoOfSinglebeds(fullInfo.getNoOfSinglebeds());
                    }
                    if (TextUtils.isEmpty(fullInfo.getMaxGuests()) == null && fullInfo.getMaxGuests().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                        this.maxGuestsEditText.setText(fullInfo.getMaxGuests());
                        this.addPropertyRequest.setMaxGuests(fullInfo.getMaxGuests());
                        break;
                    }
                case 1:
                    this.bedRoomsTextInputLayout.setVisibility(8);
                    this.doubleBedsTextInputLayout.setVisibility(8);
                    this.singleBedsTextInputLayout.setVisibility(8);
                    this.tentsTextInputLayout.setVisibility(8);
                    this.maxGuestsEditText.setInputType(2);
                    this.maxGuestsEditText.setFocusable(false);
                    this.maxGuestsEditText.setCursorVisible(true);
                    this.maxGuestsEditText.setFocusableInTouchMode(true);
                    this.maxGuestsEditText.setOnClickListener(null);
                    if (TextUtils.isEmpty(fullInfo.getMaxGuests()) == null && fullInfo.getMaxGuests().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                        this.maxGuestsEditText.setText(fullInfo.getMaxGuests());
                        this.addPropertyRequest.setMaxGuests(fullInfo.getMaxGuests());
                        break;
                    }
                default:
                    this.tentsTextInputLayout.setVisibility(8);
                    this.maxGuestsEditText.setInputType(0);
                    this.maxGuestsEditText.setFocusable(false);
                    this.maxGuestsEditText.setCursorVisible(false);
                    this.maxGuestsEditText.setOnClickListener(this.clickListener);
                    if (TextUtils.isEmpty(fullInfo.getNoOfBeds()) == null && fullInfo.getNoOfBeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                        this.bedRoomsEditText.setText(String.format("%s %s", new Object[]{fullInfo.getNoOfBeds(), getString(R.string.bed_room_label)}));
                        this.addPropertyRequest.setNoOfBeds(fullInfo.getNoOfBeds());
                    }
                    if (TextUtils.isEmpty(fullInfo.getMaxGuests()) == null && fullInfo.getMaxGuests().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                        str = fullInfo.getMaxGuests();
                        if (this.limits != null && this.limits.getMaxGuests() == Integer.parseInt(fullInfo.getMaxGuests())) {
                            str = String.format("%s+", new Object[]{fullInfo.getMaxGuests()});
                        }
                        this.maxGuestsEditText.setText(String.format("%s %s", new Object[]{str, getString(R.string.guests)}));
                        this.addPropertyRequest.setMaxGuests(fullInfo.getMaxGuests());
                        break;
                    }
            }
            if (TextUtils.isEmpty(fullInfo.getNoOfBathrooms()) == null && fullInfo.getNoOfBathrooms().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) == null) {
                this.bathroomsEditText.setText(String.format("%s %s", new Object[]{fullInfo.getNoOfBathrooms(), getString(R.string.bathrooms)}));
                this.addPropertyRequest.setNoOfBathroom(fullInfo.getNoOfBathrooms());
            }
        }
    }

    private List<BottomSheetData> getData(int i, String str) {
        List<BottomSheetData> arrayList = new ArrayList();
        for (int i2 = 1; i2 <= i; i2++) {
            BottomSheetData bottomSheetData = new BottomSheetData();
            bottomSheetData.setNumber((float) i2);
            bottomSheetData.setText(String.format("%s %s", new Object[]{Integer.valueOf(i2), str}));
            arrayList.add(bottomSheetData);
        }
        return arrayList;
    }

    private List<BottomSheetData> getBathRoomsData(int i, String str) {
        List<BottomSheetData> arrayList = new ArrayList();
        for (float f = 1.0f; f <= ((float) i); f = (float) (((double) f) + 0.5d)) {
            BottomSheetData bottomSheetData = new BottomSheetData();
            bottomSheetData.setNumber(f);
            bottomSheetData.setText(String.format("%s %s", new Object[]{Float.valueOf(f), str}));
            arrayList.add(bottomSheetData);
        }
        return arrayList;
    }

    private List<BottomSheetData> getGuestsData(int i, String str) {
        List<BottomSheetData> arrayList = new ArrayList();
        for (int i2 = 1; i2 <= i; i2++) {
            BottomSheetData bottomSheetData = new BottomSheetData();
            bottomSheetData.setNumber((float) i2);
            if (i2 == i) {
                bottomSheetData.setText(String.format("%s+ %s", new Object[]{Integer.valueOf(i2), str}));
            } else {
                bottomSheetData.setText(String.format("%s %s", new Object[]{Integer.valueOf(i2), str}));
            }
            arrayList.add(bottomSheetData);
        }
        return arrayList;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getBottomSheetData(AddPropertyRoomsAndGuestEvent addPropertyRoomsAndGuestEvent) {
        if (addPropertyRoomsAndGuestEvent != null) {
            BottomSheetData bottomSheetData = addPropertyRoomsAndGuestEvent.getBottomSheetData();
            switch (addPropertyRoomsAndGuestEvent.getUtil().getType()) {
                case 12:
                    this.bedRoomsEditText.setText(bottomSheetData.getText());
                    this.addPropertyRequest.setNoOfBeds(String.format("%s", new Object[]{Float.valueOf(bottomSheetData.getNumber())}));
                    break;
                case 13:
                    this.tentsEditText.setText(bottomSheetData.getText());
                    this.addPropertyRequest.setNoOfTents(String.format("%s", new Object[]{Float.valueOf(bottomSheetData.getNumber())}));
                    break;
                case 14:
                    this.doubleBedsEditText.setText(bottomSheetData.getText());
                    this.addPropertyRequest.setNoOfDoublebeds(String.format("%s", new Object[]{Float.valueOf(bottomSheetData.getNumber())}));
                    break;
                case 15:
                    this.singleBedsEditText.setText(bottomSheetData.getText());
                    this.addPropertyRequest.setNoOfSinglebeds(String.format("%s", new Object[]{Float.valueOf(bottomSheetData.getNumber())}));
                    break;
                case 16:
                    this.bathroomsEditText.setText(bottomSheetData.getText());
                    this.addPropertyRequest.setNoOfBathroom(String.format("%s", new Object[]{Float.valueOf(bottomSheetData.getNumber())}));
                    break;
                case 17:
                    this.maxGuestsEditText.setText(bottomSheetData.getText());
                    this.addPropertyRequest.setMaxGuests(String.format("%s", new Object[]{Float.valueOf(bottomSheetData.getNumber())}));
                    break;
                default:
                    break;
            }
            checkNextButtonEnabled(this.addPropertyRequest.getPropertyid());
        }
    }

    private void checkNextButtonEnabled(String str) {
        int hashCode = str.hashCode();
        if (hashCode != 1660) {
            if (hashCode == 48819) {
                if (str.equals("168") != null) {
                    str = null;
                    switch (str) {
                        case null:
                            if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfTents()) != null && TextUtils.isEmpty(this.addPropertyRequest.getNoOfDoublebeds()) == null && TextUtils.isEmpty(this.addPropertyRequest.getNoOfSinglebeds()) == null && TextUtils.isEmpty(this.addPropertyRequest.getNoOfBathroom()) == null && TextUtils.isEmpty(this.addPropertyRequest.getMaxGuests()) == null) {
                                this.nextButton.setEnabled(true);
                                this.nextButton.setClickable(true);
                                this.nextButton.setFocusable(true);
                                return;
                            }
                            this.nextButton.setEnabled(false);
                            this.nextButton.setClickable(false);
                            this.nextButton.setFocusable(false);
                            return;
                        case 1:
                            if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfBathroom()) == null || TextUtils.isEmpty(this.addPropertyRequest.getMaxGuests()) != null) {
                                this.nextButton.setEnabled(false);
                                this.nextButton.setClickable(false);
                                this.nextButton.setFocusable(false);
                                return;
                            }
                            this.nextButton.setEnabled(true);
                            this.nextButton.setClickable(true);
                            this.nextButton.setFocusable(true);
                            return;
                        default:
                            if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfBeds()) != null && TextUtils.isEmpty(this.addPropertyRequest.getNoOfDoublebeds()) == null && TextUtils.isEmpty(this.addPropertyRequest.getNoOfSinglebeds()) == null && TextUtils.isEmpty(this.addPropertyRequest.getNoOfBathroom()) == null && TextUtils.isEmpty(this.addPropertyRequest.getMaxGuests()) == null) {
                                this.nextButton.setEnabled(true);
                                this.nextButton.setClickable(true);
                                this.nextButton.setFocusable(true);
                                return;
                            }
                            this.nextButton.setEnabled(false);
                            this.nextButton.setClickable(false);
                            this.nextButton.setFocusable(false);
                            return;
                    }
                }
            }
        } else if (str.equals("40") != null) {
            str = true;
            switch (str) {
                case null:
                    if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfTents()) != null) {
                        break;
                    }
                    this.nextButton.setEnabled(false);
                    this.nextButton.setClickable(false);
                    this.nextButton.setFocusable(false);
                    return;
                case 1:
                    if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfBathroom()) == null) {
                        break;
                    }
                    this.nextButton.setEnabled(false);
                    this.nextButton.setClickable(false);
                    this.nextButton.setFocusable(false);
                    return;
                default:
                    if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfBeds()) != null) {
                        break;
                    }
                    this.nextButton.setEnabled(false);
                    this.nextButton.setClickable(false);
                    this.nextButton.setFocusable(false);
                    return;
            }
        }
        str = -1;
        switch (str) {
            case null:
                if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfTents()) != null) {
                    break;
                }
                this.nextButton.setEnabled(false);
                this.nextButton.setClickable(false);
                this.nextButton.setFocusable(false);
                return;
            case 1:
                if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfBathroom()) == null) {
                    break;
                }
                this.nextButton.setEnabled(false);
                this.nextButton.setClickable(false);
                this.nextButton.setFocusable(false);
                return;
            default:
                if (TextUtils.isEmpty(this.addPropertyRequest.getNoOfBeds()) != null) {
                    break;
                }
                this.nextButton.setEnabled(false);
                this.nextButton.setClickable(false);
                this.nextButton.setFocusable(false);
                return;
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
