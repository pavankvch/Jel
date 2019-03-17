package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.addproperty.AddPropertyRequest;
import com.data.amenitiesandhouserules.PropertyType;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.adapters.ListOfPropertyTypeAdapter;
import com.jelsat.adapters.ListOfPropertyTypeAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.DialogUtil;
import com.jelsat.widgets.FancyButton;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class AddPropertyListOfPropertyTypeActivity extends BaseAppCompactActivity implements OnListItemClickListener {
    private DialogUtil dialogUtil;
    private boolean isFromEdit;
    @BindView(2131362357)
    FancyButton nextButton;
    private int previousPosition = 0;
    private PropertyType propertyType;
    private ListOfPropertyTypeAdapter propertyTypeAdapter;
    @BindView(2131362498)
    RecyclerView propertyTypeRecyclerView;

    public int getActivityLayout() {
        return R.layout.activity_add_property_list_of_property_type;
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131362357})
    public void clickOnNextButton() {
        if (this.propertyType != null && this.propertyType.getChecked()) {
            if (isNetworkConnected()) {
                if (!this.isFromEdit) {
                    AddPropertyRequest addPropertyRequest = new AddPropertyRequest();
                    addPropertyRequest.setPropertytype(this.propertyType.getId());
                    EventBus.getDefault().postSticky(addPropertyRequest);
                }
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
            initRecyclerView(bundle.getPropertyTypes());
        }
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void initRecyclerView(List<PropertyType> list) {
        this.propertyTypeRecyclerView.setLayoutManager(new LinearLayoutManager(this, 1, false) {
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        this.propertyTypeRecyclerView.setNestedScrollingEnabled(false);
        this.propertyTypeAdapter = new ListOfPropertyTypeAdapter(list, this);
        this.propertyTypeRecyclerView.setHasFixedSize(true);
        this.propertyTypeRecyclerView.setAdapter(this.propertyTypeAdapter);
    }

    public void clickOnListItem(PropertyType propertyType, int i) {
        this.propertyTypeAdapter.setItemStatusChanged(propertyType, this.previousPosition, i);
        this.previousPosition = i;
        this.propertyType = propertyType;
        this.nextButton.setEnabled(true);
        this.nextButton.setClickable(true);
        this.nextButton.setFocusable(true);
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
