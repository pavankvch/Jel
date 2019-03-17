package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.dashboardhome.DashBoardHomePresenter;
import com.businesslogic.dashboardhome.IDashBoardHomeView;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.token.FCMTokenPresenter;
import com.data.dashboardhome.PropertyItem;
import com.data.retrofit.RetrofitClient;
import com.data.searchtoplocalities.Locality;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.activities.AllLocalitiesActivity;
import com.jelsat.activities.ChooseLocationActivity;
import com.jelsat.activities.SearchPropertyActivity;
import com.jelsat.adapters.TopCitiesRecyclerViewAdapter;
import com.jelsat.adapters.TopCitiesRecyclerViewAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.compoundviews.PropertyLibraryView;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.events.HomeBadgeEvent;
import com.jelsat.events.SearchPropertyEvent;
import com.jelsat.widgets.FancyButton;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class DashboardHomeFragment extends BaseFragment implements IDashBoardHomeView, OnListItemClickListener {
    private DashBoardHomePresenter dashBoardHomePresenter = new DashBoardHomePresenter(this, RetrofitClient.getAPIService());
    private FCMTokenPresenter fcmTokenPresenter;
    @BindView(2131362308)
    TextView localitiesSectionTv;
    @BindView(2131362350)
    NestedScrollView nestedScrollView;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    @BindView(2131362472)
    PropertyLibraryView propertyLibrary;
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362556)
    CustomTextView searchTextview;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362684)
    RecyclerView topCitiesRecyclerView;
    private TopCitiesRecyclerViewAdapter topCitiesRecyclerViewAdapter;

    public int getFragmentLayoutId() {
        return R.layout.fragment_dashboard_home;
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getAllPropertiesAndLocalities();
    }

    @OnClick({2131362556})
    public void clickOnSearch() {
        goToActivity(ChooseLocationActivity.class);
    }

    @OnClick({2131362309})
    public void clickOnLocalitiesViewAll() {
        goToActivity(AllLocalitiesActivity.class);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        initSwipeToRefresh();
        initCitiesRecyclerView();
        initNetworkLayout();
        return layoutInflater;
    }

    private void initNetworkLayout() {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.nestedScrollView.setVisibility(0);
            this.dashBoardHomePresenter.getDashBoardData(getString(R.string.please_wait), true);
            if (!(PrefUtils.getInstance().getUserAccessToken() == null || PrefUtils.getInstance().getCookie() == null || PrefUtils.getInstance().isDeviceTokenUpdated())) {
                this.fcmTokenPresenter = new FCMTokenPresenter(RetrofitClient.getAPIService(), PrefUtils.getInstance());
                this.fcmTokenPresenter.sendFCMTokenToServer();
                return;
            }
        }
        this.nestedScrollView.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                DashboardHomeFragment.this.getAllPropertiesAndLocalities();
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void getAllPropertiesAndLocalities() {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.nestedScrollView.setVisibility(0);
            if (this.dashBoardHomePresenter != null) {
                this.dashBoardHomePresenter.getDashBoardData(getString(R.string.please_wait), false);
                return;
            }
        }
        showSwipeToRefresh(false);
        this.nestedScrollView.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(R.string.internet_connection_label));
    }

    private void initCitiesRecyclerView() {
        this.topCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false) {
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.topCitiesRecyclerView.setNestedScrollingEnabled(false);
        this.topCitiesRecyclerViewAdapter = new TopCitiesRecyclerViewAdapter(getActivity(), null, this);
        this.topCitiesRecyclerView.setAdapter(this.topCitiesRecyclerViewAdapter);
    }

    public void clickOnItem(Locality locality, int i) {
        EventBus.getDefault().postSticky(new SearchPropertyEvent(locality, false));
        startActivity(new Intent(getActivity(), SearchPropertyActivity.class));
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    public void showInboxCount(int i, int i2) {
        EventBus.getDefault().post(new HomeBadgeEvent(i, i2));
    }

    public void setLocalities(List<Locality> list) {
        this.topCitiesRecyclerViewAdapter.setData(list);
        showNoResultVisibility(list);
    }

    public void setSectionProperties(List<PropertyItem> list, boolean z) {
        for (int i = 0; i < list.size(); i++) {
            if (z) {
                this.propertyLibrary.addStreamsSection(((PropertyItem) list.get(i)).getItems(), ((PropertyItem) list.get(i)).getSectionName(), ((PropertyItem) list.get(i)).getPropertyInstant());
            } else {
                this.propertyLibrary.updateStreams(((PropertyItem) list.get(i)).getItems(), ((PropertyItem) list.get(i)).getSectionName());
            }
        }
    }

    private void showNoResultVisibility(List<Locality> list) {
        this.noResultImage.setImageResource(R.drawable.ic_noresults_found);
        this.noResultTV.setText(getText(R.string.no_resu_found));
        if (list == null || list.size() <= null) {
            this.noResultLayout.setVisibility(0);
            this.retryButton.setVisibility(8);
            this.nestedScrollView.setVisibility(8);
            return;
        }
        this.noResultLayout.setVisibility(8);
        this.nestedScrollView.setVisibility(0);
    }

    public void setLabelsList(List<String> list) {
        this.localitiesSectionTv.setText((CharSequence) list.get(0));
    }

    public void onError(String str, int i) {
        if (i == ErrorCodes.API_ERROR) {
            showToast(str);
        } else if (i != ErrorCodes.INTERNAL_SERVER_ERROR) {
            if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                showToast(getString(R.string.socket_time_out_error));
            } else if (i == 511) {
                showToast(getString(R.string.network_error));
            }
        } else {
            showToast(getString(R.string.internal_server_error));
        }
    }

    public void onStop() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onStop();
    }

    public void onDetach() {
        if (this.dashBoardHomePresenter != null) {
            this.dashBoardHomePresenter.unSubscribeDisposables();
        }
        if (this.fcmTokenPresenter != null) {
            this.fcmTokenPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
