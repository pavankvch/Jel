package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.alllocalities.AllLocalitiesPresenter;
import com.businesslogic.alllocalities.IAllLocalitiesView;
import com.data.alllocalities.AllLocalitiesResponse;
import com.data.alllocalities.CountryDetail;
import com.data.retrofit.RetrofitClient;
import com.data.searchtoplocalities.Locality;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.AllLocalitiesAdapter;
import com.jelsat.adapters.AllLocalitiesAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.events.SearchPropertyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public class AllLocalitiesActivity extends BaseAppCompactActivity implements IAllLocalitiesView, OnListItemClickListener {
    @BindView(2131361860)
    RecyclerView allLocalitiesRecyclerView;
    private AllLocalitiesAdapter localitiesAdapter;
    private AllLocalitiesPresenter localitiesPresenter;
    private Map<String, List<Locality>> localityMap = new HashMap();
    @BindView(2131362611)
    Spinner spinner_nav;

    public int getActivityLayout() {
        return R.layout.activity_all_localities;
    }

    @OnClick({2131361892})
    public void clickOnBackArrow() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initAllLocalitiesRecyclerView();
        initPresenter();
    }

    private void initAllLocalitiesRecyclerView() {
        this.allLocalitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.localitiesAdapter = new AllLocalitiesAdapter(this, null, this);
        this.allLocalitiesRecyclerView.setAdapter(this.localitiesAdapter);
    }

    private void initPresenter() {
        this.localitiesPresenter = new AllLocalitiesPresenter(this, RetrofitClient.getAPIService());
        this.localitiesPresenter.getAllLocalitiesFromCountry(getString(R.string.fetching_localities));
    }

    public void clickOnListItem(Locality locality, int i) {
        EventBus.getDefault().postSticky(new SearchPropertyEvent(locality, false));
        startActivity(new Intent(this, SearchPropertyActivity.class));
    }

    public void onSuccess(List<AllLocalitiesResponse> list) {
        if (!(this.localityMap == null || this.localityMap.isEmpty())) {
            this.localityMap.clear();
        }
        List arrayList = new ArrayList();
        for (AllLocalitiesResponse allLocalitiesResponse : list) {
            this.localityMap.put(allLocalitiesResponse.getShortName(), allLocalitiesResponse.getLocalities());
            arrayList.add(new CountryDetail(allLocalitiesResponse.getName(), allLocalitiesResponse.getShortName()));
        }
        addItemsToSpinner(arrayList);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onDetachedFromWindow() {
        if (this.localitiesPresenter != null) {
            this.localitiesPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    public void addItemsToSpinner(List<CountryDetail> list) {
        SpinnerAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_row, list);
        arrayAdapter.setDropDownViewResource(17367049);
        this.spinner_nav.setAdapter(arrayAdapter);
        this.spinner_nav.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                AllLocalitiesActivity.this.localitiesAdapter.setData(AllLocalitiesActivity.this.filterLocalitiesBasedOnCountryShortName(AllLocalitiesActivity.this.localityMap, ((CountryDetail) adapterView.getItemAtPosition(i)).getCountryShortName()));
            }
        });
    }

    private List<Locality> filterLocalitiesBasedOnCountryShortName(Map<String, List<Locality>> map, String str) {
        return map.containsKey(str) ? (List) map.get(str) : null;
    }
}
