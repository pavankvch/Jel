package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.payments.HostPropertiesPresenter;
import com.businesslogic.payments.IHostPropertiesView;
import com.data.payments.HostProperty;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.HostPropertiesAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import java.util.ArrayList;
import java.util.List;

public class HostPropertiesActivity extends BaseAppCompactActivity implements IHostPropertiesView {
    @BindView(2131361892)
    ImageView backArrowIV;
    @BindView(2131362710)
    TextView closeTv;
    @BindView(2131362733)
    TextView headingTv;
    private HostPropertiesAdapter hostPropertiesAdapter;
    private HostPropertiesPresenter hostPropertiesPresenter = new HostPropertiesPresenter(this, RetrofitClient.getAPIService());
    private List<HostProperty> hostPropertiesResponse;
    @BindView(2131362373)
    TextView norecordsTv;
    @BindView(2131362498)
    RecyclerView recyclerView;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;

    public int getActivityLayout() {
        return R.layout.host_properties;
    }

    @OnClick({2131362710})
    public void clickOnClose() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initSwipeToRefresh();
        this.hostPropertiesResponse = new ArrayList();
        this.backArrowIV.setVisibility(8);
        this.closeTv.setVisibility(0);
        this.headingTv.setText(getResources().getString(R.string.total_properties_heading));
        this.norecordsTv.setVisibility(8);
        initView();
    }

    private void initView() {
        this.hostPropertiesAdapter = new HostPropertiesAdapter(this.hostPropertiesResponse, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.hostPropertiesAdapter);
        this.hostPropertiesPresenter.getHostProperties(getString(R.string.please_wait), true);
    }

    public void onSuccess(List<HostProperty> list) {
        int i = 0;
        if (list == null) {
            this.norecordsTv.setVisibility(0);
        } else if (list.size() > 0) {
            this.hostPropertiesResponse.clear();
            while (i < list.size()) {
                this.hostPropertiesResponse.add(list.get(i));
                i++;
            }
            this.hostPropertiesAdapter.notifyDataSetChanged();
        } else {
            this.norecordsTv.setVisibility(0);
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                HostPropertiesActivity.this.hostPropertiesPresenter.getHostProperties(HostPropertiesActivity.this.getString(R.string.please_wait), false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    protected void onStop() {
        if (this.hostPropertiesPresenter != null) {
            this.hostPropertiesPresenter.unSubscribeDisposables();
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onDetachedFromWindow();
    }
}
