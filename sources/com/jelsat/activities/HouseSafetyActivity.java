package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.media.ExifInterface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.amenitiesandhouserules.HouseSafety;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.propertydetail.FullInfo;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.adapters.HouseSafetyAdapter;
import com.jelsat.adapters.HouseSafetyAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.DataPlottingInAddpropertyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HouseSafetyActivity extends BaseAppCompactActivity implements IAddYourPropertyView, HouseSafetyAdapter$OnListItemClickListener {
    String PropertyId;
    private AddPropertyRequest addPropertyRequest;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361893)
    ImageView backArrowProperty;
    String checkClick;
    ArrayList<String> checkHouseSafetyForApi = new ArrayList();
    Boolean checkIntent;
    String editPropertyId;
    Bundle extraBundle;
    private FullInfo fullInfo;
    @BindView(2131362225)
    RecyclerView hoseSafetyRecyclerview;
    @BindView(2131362435)
    Button hosueSafetyNextButton;
    List<HouseSafety> houseSafeties;
    private HouseSafetyAdapter houseSafetyAdapter;
    ArrayList<String> housesafetyAdd = new ArrayList();
    private Map<String, HouseSafetyActivity> housesafetyHashMap = new HashMap();
    String mainStep;
    @BindView(2131362531)
    TextView saveAndExit;
    SeedResponse seedResponseHousesafety;
    private Steps steps;
    String subStep;

    public int getActivityLayout() {
        return R.layout.house_safety;
    }

    @OnClick({2131361893})
    public void backbutton() {
        finish();
    }

    @OnClick({2131362435})
    public void houseSafety() {
        this.checkClick = "nextbutton";
        this.addPropertyRequest = new AddPropertyRequest();
        if (this.checkIntent.booleanValue()) {
            if (this.mainStep.equalsIgnoreCase("finished")) {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
            } else {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
                if (Integer.parseInt(this.mainStep) < 2 || Integer.parseInt(this.subStep) < 2) {
                    this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
                    this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
                }
            }
            if (this.housesafetyAdd.equals(this.checkHouseSafetyForApi)) {
                Intent intent = new Intent(this, AboutYourProperty.class);
                intent.putExtras(this.extraBundle);
                intent.putExtra("checkIntent", true);
                intent.putExtra("editPropertyId", this.editPropertyId);
                intent.setFlags(33554432);
                startActivity(intent);
                return;
            }
            this.addPropertyRequest.setHouseSafety(this.housesafetyAdd);
            this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
            return;
        }
        this.addPropertyRequest.setPropertyid(this.PropertyId);
        this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
        this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
        this.addPropertyRequest.setHouseSafety(this.housesafetyAdd);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    @OnClick({2131362531})
    public void saveAndExit() {
        this.checkClick = "saveAndExit";
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent.booleanValue()) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) < 2 || Integer.parseInt(this.subStep) < 2) {
                this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
                this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_2D);
            }
        }
        this.addPropertyRequest.setHouseSafety(this.housesafetyAdd);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getIntent();
        this.extraBundle = bundle.getExtras();
        this.checkIntent = Boolean.valueOf(bundle.getExtras().getBoolean("checkIntent"));
        this.PropertyId = this.extraBundle.getString("PropertyId");
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            this.seedResponseHousesafety = PrefUtils.getInstance().getSeedResponse();
        }
        this.houseSafeties = this.seedResponseHousesafety.getHouseSafety();
        initHouseSaftyItems();
        if (this.checkIntent.booleanValue() != null) {
            this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
            this.steps = (Steps) this.extraBundle.getParcelable("steps");
            this.mainStep = this.steps.getStep();
            this.subStep = this.steps.getSubstep();
            this.editPropertyId = this.extraBundle.getString("editPropertyId");
            editSelectedType(this.houseSafeties);
        }
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void editSelectedType(List<HouseSafety> list) {
        for (HouseSafety houseSafety : list) {
            for (int i = 0; i < this.fullInfo.getHouseSafety().size(); i++) {
                if (houseSafety.getId().equalsIgnoreCase(((HouseSafety) this.fullInfo.getHouseSafety().get(i)).getId())) {
                    houseSafety.setChecked(true);
                    ArrayList arrayList = this.housesafetyAdd;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(houseSafety.getId());
                    arrayList.add(stringBuilder.toString());
                    arrayList = this.checkHouseSafetyForApi;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(houseSafety.getId());
                    arrayList.add(stringBuilder.toString());
                }
            }
        }
        this.houseSafetyAdapter.notifyDataSetChanged();
    }

    private void initHouseSaftyItems() {
        LayoutManager houseSafetyActivity$1 = new HouseSafetyActivity$1(this, this);
        this.houseSafetyAdapter = new HouseSafetyAdapter(this, null, this, false);
        this.hoseSafetyRecyclerview.setLayoutManager(houseSafetyActivity$1);
        this.hoseSafetyRecyclerview.setAdapter(this.houseSafetyAdapter);
        this.houseSafetyAdapter.setData(this.houseSafeties);
    }

    public void clickOnListItem(HouseSafety houseSafety, int i, boolean z) {
        ArrayList arrayList;
        StringBuilder stringBuilder;
        if (z) {
            arrayList = this.housesafetyAdd;
            stringBuilder = new StringBuilder();
            stringBuilder.append(houseSafety.getId());
            arrayList.add(stringBuilder.toString());
        } else {
            arrayList = this.housesafetyAdd;
            stringBuilder = new StringBuilder();
            stringBuilder.append(houseSafety.getId());
            if (arrayList.contains(stringBuilder.toString())) {
                arrayList = this.housesafetyAdd;
                stringBuilder = new StringBuilder();
                stringBuilder.append(houseSafety.getId());
                arrayList.remove(stringBuilder.toString());
            }
        }
        this.houseSafetyAdapter.setItemStatusChanged(i, z);
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        if (this.checkClick.equals("nextbutton")) {
            EventBus.getDefault().postSticky(new DataPlottingInAddpropertyEvent(addPropertyResponse));
            addPropertyResponse = new Intent(this, AboutYourProperty.class);
            addPropertyResponse.putExtras(this.extraBundle);
            if (this.checkIntent.booleanValue()) {
                addPropertyResponse.putExtra("checkIntent", true);
                addPropertyResponse.putExtra("editPropertyId", this.editPropertyId);
            } else {
                addPropertyResponse.putExtra("checkIntent", false);
            }
            addPropertyResponse.setFlags(33554432);
            startActivity(addPropertyResponse);
            return;
        }
        if (this.checkClick.equals("saveAndExit") != null) {
            addPropertyResponse = new Intent(this, DashBoardActivity.class);
            addPropertyResponse.setFlags(67108864);
            addPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
            startActivity(addPropertyResponse);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDataPloting(DataPlottingInAddpropertyEvent dataPlottingInAddpropertyEvent) {
        if (dataPlottingInAddpropertyEvent != null && dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getHouseSafety().size() > 0) {
            for (HouseSafety houseSafety : this.houseSafeties) {
                for (int i = 0; i < dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getHouseSafety().size(); i++) {
                    if (houseSafety.getId().equalsIgnoreCase(((HouseSafety) dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getHouseSafety().get(i)).getId())) {
                        houseSafety.setChecked(true);
                    }
                }
            }
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
}
