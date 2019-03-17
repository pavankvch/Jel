package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import com.data.payments.totalbookings.HostBookingData;
import com.data.payments.totalbookings.HostBookingSectionModel;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.hostpaymentstotalbookingsadapter.BookingsSectionModel;
import com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalEarningsAdapter;
import com.jelsat.adapters.hostpaymentstotalbookingsadapter.HostTotalEarningsAdapter.ListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.fragments.ViewBillDialogHostFragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HostTotalEarningsActivity extends BaseAppCompactActivity implements IHostTotalBookingsView, ListItemClickListener {
    private HostTotalEarningsAdapter adapterRecycler;
    @BindView(2131361892)
    ImageView backArrowIV;
    private HostTotalBookingsPresenter bookingsPresenter = new HostTotalBookingsPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362710)
    TextView closeTv;
    @BindView(2131362714)
    TextView countTextView;
    @BindView(2131362733)
    TextView headingTv;
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
        this.layoutTotalCount.setVisibility(0);
        this.titleTextView.setText(getString(R.string.host_payments_total_earnings));
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
            this.countTextView.setText(String.format("%s %s ", new Object[]{getIntent().getStringExtra(StringConstants.TOTAL_COUNT), getString(R.string.host_payments_price_type)}));
        } else {
            bundle = this.countTextView;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.host_payments_price_type));
            stringBuilder.append(" %s ");
            bundle.setText(String.format(stringBuilder.toString(), new Object[]{getIntent().getStringExtra(StringConstants.TOTAL_COUNT)}));
        }
        this.sectionModelArrayList = new ArrayList();
        this.backArrowIV.setVisibility(8);
        this.closeTv.setVisibility(0);
        this.headingTv.setText(getResources().getString(R.string.host_payments_total_earnings));
        this.norecordsTv.setVisibility(8);
        setUpRecyclerView();
        this.bookingsPresenter.getHostTotalEarnings(getString(R.string.please_wait), true);
    }

    private void setUpRecyclerView() {
        this.recyclerView.setHasFixedSize(true);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.scrollToPositionWithOffset(0, 0);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.adapterRecycler = new HostTotalEarningsAdapter(this, this.sectionModelArrayList, this);
        this.recyclerView.setAdapter(this.adapterRecycler);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onSuccess(List<HostBookingSectionModel> list) {
        int i = 0;
        if (list == null) {
            this.norecordsTv.setVisibility(0);
        } else if (list.size() > 0) {
            this.sectionModelArrayList.clear();
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
        if (this.bookingsPresenter != null) {
            this.bookingsPresenter.unSubscribeDisposables();
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onDetachedFromWindow();
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                HostTotalEarningsActivity.this.bookingsPresenter.getHostTotalEarnings(HostTotalEarningsActivity.this.getString(R.string.please_wait), false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    public void clickOnViewBill(HostBookingData hostBookingData) {
        DialogFragment viewBillDialogHostFragment = new ViewBillDialogHostFragment();
        ((ViewBillDialogHostFragment) viewBillDialogHostFragment).setBookingPropertyData(hostBookingData);
        viewBillDialogHostFragment.show(getSupportFragmentManager(), "viewbill");
    }
}
