package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.businesslogic.hostbookings.HostBookingsPresenter;
import com.businesslogic.hostbookings.IHostBookingsView;
import com.data.hostbookings.HostBookingsResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.events.HomeBadgeEvent;
import com.jelsat.utils.BookingsUtil;
import com.jelsat.widgets.NonSwipeableViewPager;
import org.greenrobot.eventbus.EventBus;

public class DashboardHostBookingsFragment extends BaseFragment implements IHostBookingsView {
    private HostBookingsPresenter bookingsPresenter = new HostBookingsPresenter(this, RetrofitClient.getAPIService());
    private HostBookingsFragment currentBookingsFragment;
    private HostBookingsFragment previousBookingsFragment;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362641)
    TabLayout tabs;
    @BindView(2131362677)
    LinearLayout toolbar;
    private HostBookingsFragment upcomingBookingsFragment;
    @BindView(2131362832)
    NonSwipeableViewPager viewpager;

    public int getFragmentLayoutId() {
        return R.layout.toolbar_tabs_viewpager;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.toolbar.setVisibility(8);
        initSwipeToRefresh();
        setupViewPager(this.viewpager);
        this.tabs.setupWithViewPager(this.viewpager);
        if (isNetworkConnected() != null) {
            this.bookingsPresenter.getBookings(getString(R.string.please_wait), true);
        } else {
            showToast(getString(R.string.error_message_network));
        }
    }

    private void setupViewPager(NonSwipeableViewPager nonSwipeableViewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        Fragment hostBookingsFragment = new HostBookingsFragment();
        this.upcomingBookingsFragment = hostBookingsFragment;
        viewPagerAdapter.addFragment(hostBookingsFragment, getString(R.string.host_bookings_upcoming));
        hostBookingsFragment = new HostBookingsFragment();
        this.currentBookingsFragment = hostBookingsFragment;
        viewPagerAdapter.addFragment(hostBookingsFragment, getString(R.string.host_bookings_current));
        hostBookingsFragment = new HostBookingsFragment();
        this.previousBookingsFragment = hostBookingsFragment;
        viewPagerAdapter.addFragment(hostBookingsFragment, getString(R.string.host_bookings_previous));
        nonSwipeableViewPager.setAdapter(viewPagerAdapter);
        nonSwipeableViewPager.setOffscreenPageLimit(3);
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                if (DashboardHostBookingsFragment.this.isNetworkConnected()) {
                    DashboardHostBookingsFragment.this.bookingsPresenter.getBookings(DashboardHostBookingsFragment.this.getString(R.string.please_wait), false);
                    return;
                }
                DashboardHostBookingsFragment.this.swipeContainer.setRefreshing(false);
                DashboardHostBookingsFragment.this.showToast(DashboardHostBookingsFragment.this.getString(R.string.error_message_network));
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    public void onSuccess(HostBookingsResponse hostBookingsResponse) {
        this.upcomingBookingsFragment.setData(hostBookingsResponse.getUpcomingBookings(), new BookingsUtil(0));
        this.currentBookingsFragment.setData(hostBookingsResponse.getCurrentBookings(), new BookingsUtil(1));
        this.previousBookingsFragment.setData(hostBookingsResponse.getPreviousBookings(), new BookingsUtil(2));
        EventBus.getDefault().post(new HomeBadgeEvent(hostBookingsResponse.getGuestInboxCount(), hostBookingsResponse.getHostInboxCount()));
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onStop() {
        dismissProgress();
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onStop();
    }

    public void onDetach() {
        if (this.bookingsPresenter != null) {
            this.bookingsPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
