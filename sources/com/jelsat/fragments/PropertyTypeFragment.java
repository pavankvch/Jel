package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.data.amenitiesandhouserules.PropertyType;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.adapters.PropertyTypeAdapter;
import com.jelsat.adapters.PropertyTypeAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.PropertyTypeSelectionEvent;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public class PropertyTypeFragment extends BaseFragment implements OnListItemClickListener {
    @BindView(2131362371)
    TextView noResultTV;
    private PropertyTypeAdapter propertyTypeAdapter;
    private Map<String, PropertyType> propertyTypeHashMap;
    @BindView(2131362485)
    RecyclerView propertyTypeRecyclerView;

    public int getFragmentLayoutId() {
        return R.layout.fragment_property_type;
    }

    public static PropertyTypeFragment newInstance(Map<String, PropertyType> map) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstants.PROPERTY_MAP, (Serializable) map);
        map = new PropertyTypeFragment();
        map.setArguments(bundle);
        return map;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getArguments() != null) {
            this.propertyTypeHashMap = (Map) getArguments().getSerializable(StringConstants.PROPERTY_MAP);
        }
        viewGroup = null;
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            viewGroup = PrefUtils.getInstance().getSeedResponse().getPropertyTypes();
            applySelectionTypes(viewGroup);
        }
        this.propertyTypeAdapter = new PropertyTypeAdapter(getActivity(), viewGroup, this);
        this.propertyTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.propertyTypeRecyclerView.setAdapter(this.propertyTypeAdapter);
        showNoResultVisibility(viewGroup);
        return layoutInflater;
    }

    public void clickOnListItem(PropertyType propertyType, int i, boolean z) {
        this.propertyTypeAdapter.setItemStatusChanged(propertyType, i, z);
        EventBus.getDefault().post(new PropertyTypeSelectionEvent(propertyType, i, z));
    }

    private void applySelectionTypes(List<PropertyType> list) {
        for (PropertyType propertyType : list) {
            if (this.propertyTypeHashMap != null && this.propertyTypeHashMap.containsKey(propertyType.getId())) {
                propertyType.setChecked(true);
            }
        }
    }

    private void showNoResultVisibility(List<PropertyType> list) {
        this.noResultTV.setText("No Property types Found");
        if (list == null || list.size() <= null) {
            this.noResultTV.setVisibility(0);
        } else {
            this.noResultTV.setVisibility(8);
        }
    }
}
