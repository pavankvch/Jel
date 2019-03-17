package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.media.ExifInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.amenitiesandhouserules.Amenity;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.propertydetail.FullInfo;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.adapters.AmenitiesAdapter;
import com.jelsat.adapters.AmenitiesAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmenitiesActivity extends BaseAppCompactActivity implements IAddYourPropertyView, AmenitiesAdapter$OnListItemClickListener {
    private static String TAG = "AmenitiesActivity";
    String PropertyId;
    private AddPropertyRequest addPropertyRequest;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    List<Amenity> amenities;
    private AmenitiesAdapter amenitiesAdapter;
    private Map<String, Amenity> amenityHashMap = new HashMap();
    @BindView(2131361870)
    RecyclerView amenityRecyclerview;
    @BindView(2131362435)
    Button aminetyNextButton;
    ArrayList<String> aminitiesAdd = new ArrayList();
    @BindView(2131361893)
    ImageView backArrowProperty;
    ArrayList<String> checkAmentiesForApi = new ArrayList();
    String checkClick;
    Boolean checkIntent;
    String editPropertyId;
    Bundle extraBundle;
    private FullInfo fullInfo;
    String mainStep;
    private PropertySeedPresenter propertySeedPresenter;
    @BindView(2131362531)
    TextView saveAndExit;
    private SearchProperty searchProperty;
    SeedResponse seedResponseInAmenities;
    private Steps steps;
    String subStep;

    public int getActivityLayout() {
        return R.layout.amenities_layout;
    }

    @OnClick({2131361893})
    public void backbutton() {
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.aminetyNextButton.setEnabled(false);
        bundle = getIntent();
        this.extraBundle = bundle.getExtras();
        this.checkIntent = Boolean.valueOf(bundle.getExtras().getBoolean("checkIntent"));
        StringBuilder stringBuilder;
        if (this.checkIntent.booleanValue() != null) {
            this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
            this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
            this.steps = (Steps) this.extraBundle.getParcelable("steps");
            this.mainStep = this.steps.getStep();
            this.subStep = this.steps.getSubstep();
            this.editPropertyId = this.searchProperty.getPropertyId();
            if (PrefUtils.getInstance().getSeedResponse() != null) {
                this.seedResponseInAmenities = PrefUtils.getInstance().getSeedResponse();
            }
            this.amenities = this.seedResponseInAmenities.getAmenities();
            editSelectedType(this.amenities);
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.amenities.get(1));
            Log.d("InAmenitiesActivity", stringBuilder.toString());
        } else {
            this.PropertyId = this.extraBundle.getString("PropertyId");
            this.seedResponseInAmenities = (SeedResponse) this.extraBundle.getParcelable("seedResponse1");
            this.amenities = this.seedResponseInAmenities.getAmenities();
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.amenities.get(1));
            Log.d("InAmenitiesActivity", stringBuilder.toString());
        }
        initAmenitiesItems();
    }

    private void editSelectedType(List<Amenity> list) {
        for (Amenity amenity : list) {
            for (int i = 0; i < this.fullInfo.getAmenities().size(); i++) {
                if (amenity.getName().equalsIgnoreCase(((Amenity) this.fullInfo.getAmenities().get(i)).getName())) {
                    amenity.setChecked(true);
                    ArrayList arrayList = this.aminitiesAdd;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(amenity.getId());
                    arrayList.add(stringBuilder.toString());
                    arrayList = this.checkAmentiesForApi;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(amenity.getId());
                    arrayList.add(stringBuilder.toString());
                    this.aminetyNextButton.setEnabled(true);
                    this.saveAndExit.setVisibility(0);
                    this.aminetyNextButton.setBackgroundResource(R.drawable.button_selected_background);
                }
            }
        }
    }

    private void initAmenitiesItems() {
        LayoutManager anonymousClass1 = new LinearLayoutManager(this) {
            public boolean canScrollVertically() {
                return true;
            }
        };
        this.amenitiesAdapter = new AmenitiesAdapter(this, null, this, false, false);
        this.amenityRecyclerview.setLayoutManager(anonymousClass1);
        this.amenityRecyclerview.setAdapter(this.amenitiesAdapter);
        this.amenitiesAdapter.setData(this.amenities);
    }

    @OnClick({2131362531})
    public void saveAndExit() {
        this.checkClick = "saveAndExit";
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent.booleanValue()) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) <= 0 || Integer.parseInt(this.subStep) < 3) {
                this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
            }
        }
        this.addPropertyRequest.setAmenities(this.aminitiesAdd);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    @OnClick({2131362435})
    public void ametiesNextbutton() {
        this.checkClick = "nextbutton";
        this.addPropertyRequest = new AddPropertyRequest();
        if (this.checkIntent.booleanValue()) {
            if (this.mainStep.equalsIgnoreCase("finished")) {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
            } else {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
                if (Integer.parseInt(this.mainStep) <= 0 || Integer.parseInt(this.subStep) < 3) {
                    this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
                }
            }
            if (this.aminitiesAdd.equals(this.checkAmentiesForApi)) {
                Intent intent = new Intent(this, AddYourPropertySteps.class);
                intent.setFlags(67108864);
                intent.putExtras(this.extraBundle);
                intent.putExtra("FromActivity", "fromStep1");
                intent.putExtra("checkIntent", this.checkIntent);
                startActivity(intent);
                return;
            }
            this.addPropertyRequest.setAmenities(this.aminitiesAdd);
            this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
            return;
        }
        this.addPropertyRequest.setPropertyid(this.PropertyId);
        this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
        this.addPropertyRequest.setAmenities(this.aminitiesAdd);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    public void clickOnListItem(Amenity amenity, int i, boolean z) {
        ArrayList arrayList;
        StringBuilder stringBuilder;
        if (z) {
            arrayList = this.aminitiesAdd;
            stringBuilder = new StringBuilder();
            stringBuilder.append(amenity.getId());
            arrayList.add(stringBuilder.toString());
            this.aminetyNextButton.setEnabled(true);
            this.saveAndExit.setVisibility(0);
            this.aminetyNextButton.setBackgroundResource(R.drawable.button_selected_background);
        } else {
            arrayList = this.aminitiesAdd;
            stringBuilder = new StringBuilder();
            stringBuilder.append(amenity.getId());
            if (arrayList.contains(stringBuilder.toString())) {
                arrayList = this.aminitiesAdd;
                stringBuilder = new StringBuilder();
                stringBuilder.append(amenity.getId());
                arrayList.remove(stringBuilder.toString());
                if (this.aminitiesAdd.size() == null) {
                    this.aminetyNextButton.setEnabled(false);
                    this.saveAndExit.setVisibility(8);
                    this.aminetyNextButton.setBackgroundResource(R.drawable.button_backgound);
                }
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.aminitiesAdd.size());
        Log.d("UpdatedAminities", stringBuilder2.toString());
        this.amenitiesAdapter.setItemStatusChanged(i, z);
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        if (this.checkClick.equals("nextbutton") != null) {
            addPropertyResponse = new Intent(this, AddYourPropertySteps.class);
            addPropertyResponse.setFlags(67108864);
            addPropertyResponse.putExtras(this.extraBundle);
            addPropertyResponse.putExtra("FromActivity", "fromStep1");
            addPropertyResponse.putExtra("checkIntent", this.checkIntent);
            startActivity(addPropertyResponse);
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
}
