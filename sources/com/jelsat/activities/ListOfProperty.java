package com.jelsat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.amenitiesandhouserules.PropertyType;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.propertydetail.FullInfo;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.adapters.AddPropertyTypeAdapter;
import com.jelsat.adapters.AddPropertyTypeAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.events.DataPlottingInAddpropertyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ListOfProperty extends BaseAppCompactActivity implements IAddYourPropertyView, AddPropertyTypeAdapter$OnListItemClickListener {
    private static String TAG = "ListOfProperty";
    private AddPropertyRequest addPropertyRequest;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361893)
    public ImageView back_arrow_property;
    boolean checkIntent;
    Context context = this;
    Bundle dataParseStep1;
    String editPropertyId;
    private FullInfo fullInfo;
    String mainStep;
    private int previousPosition = 0;
    @BindView(2131362435)
    public Button products_list_next;
    String propertyId;
    private AddPropertyTypeAdapter propertyTypeAdapter;
    private Map<String, PropertyType> propertyTypeHashMap = new HashMap();
    String propertyTypeId;
    List<PropertyType> propertyTypeList1 = null;
    String propertyTypeName;
    @BindView(2131362485)
    RecyclerView propertyTypeRecyclerView;
    private SearchProperty searchProperty;
    private SeedResponse seedResponse1;
    private Steps steps;
    String subStep;
    private int updatedPosition;

    public int getActivityLayout() {
        return R.layout.list_of_properties;
    }

    @OnClick({2131361893})
    public void backArrow() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        bundle = getIntent();
        this.checkIntent = bundle.getExtras().getBoolean("checkIntent");
        this.dataParseStep1 = bundle.getExtras();
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            this.seedResponse1 = PrefUtils.getInstance().getSeedResponse();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.seedResponse1.getPropertyTypes());
        Log.d("PropertyType", stringBuilder.toString());
        this.propertyTypeAdapter = new AddPropertyTypeAdapter(this.context, null, this);
        this.propertyTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        this.propertyTypeRecyclerView.setAdapter(this.propertyTypeAdapter);
        if (this.checkIntent != null) {
            this.searchProperty = (SearchProperty) this.dataParseStep1.getParcelable("searchProperty");
            this.fullInfo = (FullInfo) this.dataParseStep1.getParcelable("fullInfo");
            this.steps = (Steps) this.dataParseStep1.getParcelable("steps");
            this.mainStep = this.steps.getStep();
            this.subStep = this.steps.getSubstep();
            this.editPropertyId = this.searchProperty.getPropertyId();
            if (this.fullInfo.getPropertyType() != null) {
                if (this.fullInfo.getPropertyType().equalsIgnoreCase("") == null && this.seedResponse1 != null) {
                    editSelectedType(this.seedResponse1.getPropertyTypes());
                }
            } else if (this.seedResponse1 != null) {
                applySelectionTypes(this.seedResponse1.getPropertyTypes());
            }
        } else if (this.seedResponse1 != null) {
            applySelectionTypes(this.seedResponse1.getPropertyTypes());
        }
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @OnClick({2131362435})
    public void productListNext() {
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent) {
            if (!TextUtils.isEmpty(this.propertyId)) {
                this.addPropertyRequest.setPropertyid(this.propertyId);
            }
            this.addPropertyRequest.setStep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.addPropertyRequest.setPropertytype(this.propertyTypeId);
            this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
        } else if (this.propertyTypeName.equalsIgnoreCase(this.fullInfo.getPropertyType())) {
            Intent intent = new Intent(this, AddYourProperty.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("searchProperty", this.searchProperty);
            bundle.putParcelable("fullInfo", this.fullInfo);
            bundle.putParcelable("steps", this.steps);
            bundle.putString("PropertyId", this.editPropertyId);
            bundle.putString("propertyTypeId", this.propertyTypeId);
            intent.putExtra("changedData", true);
            intent.putExtra("checkIntent", true);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            this.addPropertyRequest.setPropertytype(this.propertyTypeId);
            this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
        }
    }

    private void editSelectedType(List<PropertyType> list) {
        this.propertyTypeList1 = list;
        for (PropertyType propertyType : list) {
            if (propertyType.getName().equalsIgnoreCase(this.fullInfo.getPropertyType())) {
                propertyType.setChecked(true);
                this.propertyTypeId = propertyType.getId();
                this.propertyTypeName = propertyType.getName();
                this.products_list_next.setEnabled(true);
                this.products_list_next.setBackgroundResource(R.drawable.button_selected_background);
            }
        }
        this.propertyTypeAdapter.setData(list);
    }

    private void applySelectionTypes(List<PropertyType> list) {
        for (PropertyType propertyType : list) {
            if (this.propertyTypeHashMap.containsKey(propertyType.getId())) {
                propertyType.setChecked(true);
            }
        }
        this.propertyTypeAdapter.setData(list);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDataPloting(DataPlottingInAddpropertyEvent dataPlottingInAddpropertyEvent) {
        if (dataPlottingInAddpropertyEvent != null && !TextUtils.isEmpty(dataPlottingInAddpropertyEvent.getAddPropertyResponse().getBasicInfo().getPropertyId())) {
            for (PropertyType propertyType : this.seedResponse1.getPropertyTypes()) {
                propertyType.setChecked(false);
                if (propertyType.getName().equalsIgnoreCase(dataPlottingInAddpropertyEvent.getAddPropertyResponse().getFullInfo().getPropertyType())) {
                    propertyType.setChecked(true);
                    this.propertyTypeId = propertyType.getId();
                    this.propertyTypeName = propertyType.getName();
                    this.propertyId = dataPlottingInAddpropertyEvent.getAddPropertyResponse().getBasicInfo().getPropertyId();
                    this.products_list_next.setEnabled(true);
                    this.products_list_next.setBackgroundResource(R.drawable.button_selected_background);
                    EventBus.getDefault().removeStickyEvent(propertyType);
                }
            }
            this.propertyTypeAdapter.setData(this.seedResponse1.getPropertyTypes());
        }
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        String propertyId = addPropertyResponse.getBasicInfo().getPropertyId();
        EventBus.getDefault().postSticky(new DataPlottingInAddpropertyEvent(addPropertyResponse));
        if (this.checkIntent) {
            boolean equalsIgnoreCase = this.propertyTypeName.equalsIgnoreCase(this.fullInfo.getPropertyType());
            this.fullInfo.setPropertyTypeId(addPropertyResponse.getFullInfo().getPropertyTypeId());
            this.fullInfo.setPropertyType(addPropertyResponse.getFullInfo().getPropertyType());
            addPropertyResponse = new Intent(this, AddYourProperty.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("searchProperty", this.searchProperty);
            bundle.putParcelable("fullInfo", this.fullInfo);
            bundle.putParcelable("steps", this.steps);
            bundle.putString("PropertyId", propertyId);
            bundle.putString("propertyTypeId", this.propertyTypeId);
            addPropertyResponse.putExtra("changedData", equalsIgnoreCase);
            addPropertyResponse.putExtra("checkIntent", true);
            addPropertyResponse.putExtras(bundle);
            startActivity(addPropertyResponse);
            return;
        }
        addPropertyResponse = new Intent(this, AddYourProperty.class);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("seedResponse1", this.seedResponse1);
        bundle2.putString("PropertyId", propertyId);
        bundle2.putString("propertyTypeId", this.propertyTypeId);
        addPropertyResponse.putExtra("checkIntent", false);
        addPropertyResponse.putExtras(bundle2);
        startActivity(addPropertyResponse);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void clickOnListItem(PropertyType propertyType, int i, boolean z) {
        if (this.mainStep == null || TextUtils.isEmpty(this.mainStep) || (!this.mainStep.equalsIgnoreCase("finished") && TextUtils.isEmpty(this.editPropertyId))) {
            clickRadioButtons(propertyType, i, z);
        }
    }

    private void clickRadioButtons(PropertyType propertyType, int i, boolean z) {
        if (this.checkIntent && this.propertyTypeList1) {
            for (PropertyType propertyType2 : this.propertyTypeList1) {
                if (propertyType2.getName().equalsIgnoreCase(this.fullInfo.getPropertyType())) {
                    propertyType2.setChecked(false);
                }
            }
            this.propertyTypeAdapter.notifyDataSetChanged();
        }
        this.updatedPosition = i;
        this.propertyTypeAdapter.setItemStatusChanged(propertyType, this.previousPosition, this.updatedPosition);
        this.previousPosition = i;
        this.propertyTypeId = propertyType.getId();
        this.propertyTypeName = propertyType.getName();
        this.products_list_next.setEnabled(1);
        this.products_list_next.setBackgroundResource(R.drawable.button_selected_background);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
