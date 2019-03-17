package com.jelsat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.data.amenitiesandhouserules.CancelPolicy;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.adapters.CancelPolicyAdapter;
import com.jelsat.adapters.CancelPolicyAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.events.PropertyTypeSelectionEvent;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class CancelationPolices extends BaseAppCompactActivity implements OnListItemClickListener {
    String PropertyId;
    @BindView(2131361962)
    TextView cancelPolicy;
    private CancelPolicyAdapter cancelPolicyAdapter;
    List<CancelPolicy> cancelPolicyAdd;
    List<CancelPolicy> cancelPolicySeed;
    String cancelTypeId;
    String cancelTypeName;
    @BindView(2131361965)
    RecyclerView cancelTypeRecylerview;
    boolean checkSelectionCancelId;
    Context context;
    Bundle extraBundle;
    private int previousPosition = 0;
    @BindView(2131362510)
    TextView resetCancelPol;
    @BindView(2131362535)
    Button saveCancelPol;
    private PropertySeedPresenter seedPresenter;
    SeedResponse seedResponse;
    private int updatedPosition;

    public int getActivityLayout() {
        return R.layout.cancelationpolicies;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.context = this;
        this.saveCancelPol.setEnabled(false);
        this.extraBundle = getIntent().getExtras();
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            this.seedResponse = PrefUtils.getInstance().getSeedResponse();
        }
        this.checkSelectionCancelId = this.extraBundle.getBoolean("checkSelectionCancelId");
        this.cancelPolicyAdd = this.seedResponse.getCancelPolicy();
        this.PropertyId = this.extraBundle.getString("PropertyId");
        initCancellationItems();
        if (this.checkSelectionCancelId != null) {
            this.cancelTypeName = this.extraBundle.getString("CancelTypeName");
            plotCheckData(this.seedResponse.getCancelPolicy());
        }
    }

    private void plotCheckData(List<CancelPolicy> list) {
        this.cancelPolicyAdd = list;
        for (CancelPolicy cancelPolicy : list) {
            if (cancelPolicy.getName().equalsIgnoreCase(this.cancelTypeName)) {
                cancelPolicy.setChecked(true);
                this.cancelTypeId = cancelPolicy.getId();
                this.cancelTypeName = cancelPolicy.getName();
                this.saveCancelPol.setEnabled(true);
                this.saveCancelPol.setBackgroundResource(R.drawable.button_save_background);
            }
        }
        this.cancelPolicyAdapter.setData(list);
    }

    private void initCancellationItems() {
        this.cancelPolicyAdapter = new CancelPolicyAdapter(this.context, null, this, this);
        this.cancelTypeRecylerview.setLayoutManager(new LinearLayoutManager(this.context));
        this.cancelTypeRecylerview.setAdapter(this.cancelPolicyAdapter);
        this.cancelTypeRecylerview.setNestedScrollingEnabled(false);
        this.cancelPolicySeed = this.seedResponse.getCancelPolicy();
        this.cancelPolicyAdapter.setData(this.cancelPolicySeed);
    }

    @OnClick({2131362510})
    public void resetCancelPol() {
        this.cancelPolicyAdapter.setToResetChecked();
        this.saveCancelPol.setEnabled(false);
        this.saveCancelPol.setBackgroundColor(getResources().getColor(R.color.default_color));
    }

    @OnClick({2131362535})
    public void saveCancelPol() {
        Intent intent = new Intent();
        intent.putExtra("cancelTypeName", this.cancelTypeName);
        intent.putExtra("cancelTypeId", this.cancelTypeId);
        setResult(103, intent);
        finish();
    }

    @OnClick({2131361962})
    public void cancelPolicy() {
        Intent intent = new Intent();
        intent.putExtra("cancelTypeId", "");
        setResult(103, intent);
        finish();
    }

    public void clickOnListItem(CancelPolicy cancelPolicy, int i, boolean z) {
        this.updatedPosition = i;
        this.cancelPolicyAdapter.setItemStatusChanged(cancelPolicy, this.previousPosition, this.updatedPosition);
        this.previousPosition = i;
        EventBus.getDefault().post(new PropertyTypeSelectionEvent(cancelPolicy, i, z));
        this.cancelTypeName = cancelPolicy.getName();
        this.cancelTypeId = cancelPolicy.getId();
        this.saveCancelPol.setEnabled(1);
        this.saveCancelPol.setBackgroundResource(R.drawable.button_save_background);
    }
}
