package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.payments.totalbookings.HostTotalBookingsPresenter;
import com.businesslogic.payments.totalbookings.IHostTotalBookingsView;
import com.data.payments.totalbookings.HostBookingSectionModel;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.hostpaymentstotalbookingsadapter.BookingsSectionModel;
import com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalBookingsAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import java.util.ArrayList;
import java.util.List;

public class HostTotalBookingsActivity extends BaseAppCompactActivity implements IHostTotalBookingsView {
    private HostTotalBookingsAdapter adapterRecycler;
    @BindView(2131361892)
    ImageView backArrowIV;
    @BindView(2131362710)
    TextView closeTv;
    @BindView(2131362714)
    TextView countTextView;
    @BindView(2131362733)
    TextView headingTv;
    private HostTotalBookingsPresenter hostTotalBookingsPresenter = new HostTotalBookingsPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362292)
    RelativeLayout layoutTotalCount;
    @BindView(2131362373)
    TextView norecordsTv;
    @BindView(2131362498)
    RecyclerView recyclerView;
    private List<BookingsSectionModel> sectionModelArrayList;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362779)
    TextView titleTextView;

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
        this.norecordsTv.setVisibility(8);
        this.sectionModelArrayList = new ArrayList();
        this.backArrowIV.setVisibility(8);
        this.closeTv.setVisibility(0);
        this.headingTv.setText(getString(R.string.total_bookings_heading));
        this.layoutTotalCount.setVisibility(0);
        this.titleTextView.setText(getString(R.string.total_bookings_heading));
        this.countTextView.setText(getIntent().getStringExtra(StringConstants.TOTAL_COUNT));
        setUpRecyclerView();
        this.hostTotalBookingsPresenter.getHostTotalBookings(getString(R.string.please_wait), true);
    }

    private void setUpRecyclerView() {
        this.recyclerView.setHasFixedSize(true);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.scrollToPositionWithOffset(0, 0);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.adapterRecycler = new HostTotalBookingsAdapter(this, this.sectionModelArrayList);
        this.recyclerView.setAdapter(this.adapterRecycler);
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                HostTotalBookingsActivity.this.hostTotalBookingsPresenter.getHostTotalBookings(HostTotalBookingsActivity.this.getString(R.string.please_wait), false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onSuccess(List<HostBookingSectionModel> list) {
        this.sectionModelArrayList.clear();
        int i = 0;
        if (list == null) {
            this.norecordsTv.setVisibility(0);
        } else if (list.size() > 0) {
            while (i < list.size()) {
                this.sectionModelArrayList.add(new BookingsSectionModel(((HostBookingSectionModel) list.get(i)).getMonth(), ((HostBookingSectionModel) list.get(i)).getData()));
                i++;
            }
            this.adapterRecycler.notifyDataChanged(this.sectionModelArrayList);
        } else {
            this.norecordsTv.setVisibility(0);
        }
    }

    protected void onStop() {
        if (this.hostTotalBookingsPresenter != null) {
            this.hostTotalBookingsPresenter.unSubscribeDisposables();
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
