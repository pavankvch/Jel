package com.jelsat.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.media.ExifInterface;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.amenitiesandhouserules.Houserule;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.propertydetail.FullInfo;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.adapters.HouseRulesAdapter;
import com.jelsat.adapters.HouseRulesAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseRulesActivity extends BaseAppCompactActivity implements IAddYourPropertyView, HouseRulesAdapter$OnListItemClickListener {
    String PropertyId;
    @BindView(2131361848)
    TextView addHouseRule;
    @BindView(2131361793)
    EditText addHouseRuleET;
    private AddPropertyRequest addPropertyRequest;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361893)
    ImageView backArrowProperty;
    String checkClick;
    ArrayList<String> checkHouseRuleForApi = new ArrayList();
    Boolean checkIntent;
    @BindView(2131362087)
    ImageView delete_rule;
    String editPropertyId;
    Bundle extraBundle;
    private FullInfo fullInfo;
    @BindView(2131362435)
    Button hosueRulesNextButton;
    private HouseRulesAdapter houseRulesAdapter;
    ArrayList<String> houseRulesAdd = new ArrayList();
    @BindView(2131362224)
    RecyclerView houseRulesRecyclerview;
    private Map<String, Houserule> houseruleHashMap = new HashMap();
    List<Houserule> houserules;
    Context mContext;
    String mainStep;
    @BindView(2131362531)
    TextView saveAndExit;
    private SearchProperty searchProperty;
    SeedResponse seedResponseHouseRule;
    private Steps steps;
    String subStep;
    @BindView(2131362846)
    TextView yourHouserule;
    @BindView(2131362847)
    LinearLayout yourHouseruleLayout;

    public int getActivityLayout() {
        return R.layout.house_rules;
    }

    @OnClick({2131361893})
    public void backbutton() {
        finish();
    }

    @OnClick({2131361848})
    public void addHouseRule() {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.add_your_rule, null);
        Builder builder = new Builder(this.mContext);
        builder.setView(inflate);
        final EditText editText = (EditText) inflate.findViewById(R.id.et_message);
        builder.setCancelable(false).setPositiveButton(getString(R.string.button_add), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(editText.getText().toString().trim()) != null) {
                    Toast.makeText(HouseRulesActivity.this, HouseRulesActivity.this.mContext.getString(R.string.enter_your_rule), 1).show();
                    return;
                }
                HouseRulesActivity.this.addHouseRule.setVisibility(8);
                HouseRulesActivity.this.yourHouseruleLayout.setVisibility(0);
                HouseRulesActivity.this.yourHouserule.setText(editText.getText());
            }
        }).setNegativeButton(getString(R.string.cancel_label), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                HouseRulesActivity.this.addHouseRule.setVisibility(0);
                HouseRulesActivity.this.yourHouseruleLayout.setVisibility(8);
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    @OnClick({2131362087})
    public void deleteRule() {
        this.yourHouserule.setText("");
        this.addHouseRule.setVisibility(0);
        this.yourHouseruleLayout.setVisibility(8);
    }

    @OnClick({2131362085})
    public void deleteCustomRule() {
        if (this.addHouseRuleET.getText().toString().length() != 0) {
            this.addHouseRuleET.setText("");
        }
        this.addHouseRule.setVisibility(0);
        this.yourHouserule.setVisibility(8);
    }

    @OnClick({2131362531})
    public void saveAndExit() {
        this.checkClick = "saveAndExit";
        String trim = this.yourHouserule.getText().toString().trim();
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent.booleanValue()) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
            this.addPropertyRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) < 2 || Integer.parseInt(this.subStep) <= 0) {
                this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
                this.addPropertyRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            }
        }
        this.addPropertyRequest.setHouseRules(this.houseRulesAdd);
        this.addPropertyRequest.setCustomHouseRule(trim);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    @OnClick({2131362435})
    public void houseRuleNextButton() {
        this.checkClick = "nextbutton";
        String trim = this.yourHouserule.getText().toString().trim();
        this.addPropertyRequest = new AddPropertyRequest();
        if (this.checkIntent.booleanValue()) {
            if (this.mainStep.equalsIgnoreCase("finished")) {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
            } else {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
                if (Integer.parseInt(this.mainStep) < 2 || Integer.parseInt(this.subStep) <= 0) {
                    this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
                    this.addPropertyRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                }
            }
            if (this.houseRulesAdd.equals(this.checkHouseRuleForApi) && trim.equalsIgnoreCase(this.fullInfo.getCustomHouseRule())) {
                Intent intent = new Intent(this, HouseSafetyActivity.class);
                intent.putExtras(this.extraBundle);
                intent.putExtra("checkIntent", true);
                intent.putExtra("editPropertyId", this.editPropertyId);
                intent.setFlags(33554432);
                startActivity(intent);
                return;
            }
            this.addPropertyRequest.setHouseRules(this.houseRulesAdd);
            this.addPropertyRequest.setCustomHouseRule(trim);
            this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
            return;
        }
        this.addPropertyRequest.setPropertyid(this.PropertyId);
        this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
        this.addPropertyRequest.setSubstep(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.addPropertyRequest.setHouseRules(this.houseRulesAdd);
        this.addPropertyRequest.setCustomHouseRule(trim);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.addHouseRuleET.setImeOptions(6);
        this.addHouseRuleET.setRawInputType(1);
        bundle = getIntent();
        this.extraBundle = bundle.getExtras();
        this.checkIntent = Boolean.valueOf(bundle.getExtras().getBoolean("checkIntent"));
        this.yourHouseruleLayout.setVisibility(8);
        this.PropertyId = this.extraBundle.getString("PropertyId");
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            this.seedResponseHouseRule = PrefUtils.getInstance().getSeedResponse();
        }
        this.houserules = this.seedResponseHouseRule.getHouserules();
        initHouseRulesItems();
        if (this.checkIntent.booleanValue() != null) {
            this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
            this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
            this.steps = (Steps) this.extraBundle.getParcelable("steps");
            this.mainStep = this.steps.getStep();
            this.subStep = this.steps.getSubstep();
            this.editPropertyId = this.searchProperty.getPropertyId();
            editSelectedType(this.houserules);
            if (this.fullInfo.getCustomHouseRule().equalsIgnoreCase("") == null) {
                this.addHouseRule.setVisibility(8);
                this.yourHouseruleLayout.setVisibility(0);
                this.yourHouserule.setText(this.fullInfo.getCustomHouseRule());
            }
        }
    }

    private void editSelectedType(List<Houserule> list) {
        for (Houserule houserule : list) {
            for (int i = 0; i < this.fullInfo.getHouseRules().size(); i++) {
                if (houserule.getId().equalsIgnoreCase(((Houserule) this.fullInfo.getHouseRules().get(i)).getId())) {
                    houserule.setChecked(true);
                    ArrayList arrayList = this.houseRulesAdd;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(houserule.getId());
                    arrayList.add(stringBuilder.toString());
                    arrayList = this.checkHouseRuleForApi;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(houserule.getId());
                    arrayList.add(stringBuilder.toString());
                }
            }
        }
        this.houseRulesAdapter.notifyDataSetChanged();
    }

    private void initHouseRulesItems() {
        LayoutManager anonymousClass3 = new LinearLayoutManager(this) {
            public boolean canScrollVertically() {
                return true;
            }
        };
        this.houseRulesAdapter = new HouseRulesAdapter(this, null, this, false, false);
        this.houseRulesRecyclerview.setLayoutManager(anonymousClass3);
        this.houseRulesRecyclerview.setAdapter(this.houseRulesAdapter);
        showMoreButtonsForHouseRules(this.houserules);
    }

    private void showMoreButtonsForHouseRules(List<Houserule> list) {
        if (list != null) {
            for (Houserule houserule : list) {
                if (this.houseruleHashMap.containsKey(houserule.getId())) {
                    houserule.setChecked(true);
                }
            }
            this.houseRulesAdapter.setData(list);
        }
    }

    public void clickOnListItem(Houserule houserule, int i, boolean z) {
        ArrayList arrayList;
        StringBuilder stringBuilder;
        if (z) {
            arrayList = this.houseRulesAdd;
            stringBuilder = new StringBuilder();
            stringBuilder.append(houserule.getId());
            arrayList.add(stringBuilder.toString());
        } else {
            arrayList = this.houseRulesAdd;
            stringBuilder = new StringBuilder();
            stringBuilder.append(houserule.getId());
            if (arrayList.contains(stringBuilder.toString())) {
                arrayList = this.houseRulesAdd;
                stringBuilder = new StringBuilder();
                stringBuilder.append(houserule.getId());
                arrayList.remove(stringBuilder.toString());
            }
        }
        this.houseRulesAdapter.setItemStatusChanged(i, z);
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        if (this.checkClick.equals("nextbutton") != null) {
            addPropertyResponse = new Intent(this, HouseSafetyActivity.class);
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

    public void onError(APIError aPIError) {
        dismissProgress();
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }
}
