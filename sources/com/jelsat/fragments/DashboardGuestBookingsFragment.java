package com.jelsat.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.bookings.BookingsPresenter;
import com.businesslogic.bookings.BookingsView;
import com.businesslogic.location.ILocationView;
import com.businesslogic.location.LocationPresenter;
import com.data.bookings.BookingsResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.google.android.gms.maps.model.LatLng;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.utils.BookingsUtil;
import com.jelsat.widgets.FancyButton;
import com.jelsat.widgets.NonSwipeableViewPager;

public class DashboardGuestBookingsFragment extends BaseFragment implements BookingsView, ILocationView {
    private BookingsPresenter bookingsPresenter = new BookingsPresenter(this, RetrofitClient.getAPIService());
    private LocationPresenter chooseLocationPresenter;
    private GuestBookingsFragment currentBookingsFragment;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private GuestBookingsFragment previousBookingsFragment;
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362641)
    TabLayout tabs;
    @BindView(2131362677)
    LinearLayout toolbar;
    private GuestBookingsFragment upcomingBookingsFragment;
    @BindView(2131362832)
    NonSwipeableViewPager viewpager;

    public int getFragmentLayoutId() {
        return R.layout.toolbar_tabs_viewpager;
    }

    public void setAddress(Address address, String str) {
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getBookingsData(false);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.toolbar.setVisibility(8);
        initSwipeToRefresh();
        setupViewPager(this.viewpager);
        this.tabs.setupWithViewPager(this.viewpager);
        this.chooseLocationPresenter = new LocationPresenter(this, true, false);
        getBookingsData(true);
    }

    private void getBookingsData(boolean z) {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.viewpager.setVisibility(0);
            if (this.bookingsPresenter != null) {
                this.bookingsPresenter.getBookingsData(getString(R.string.please_wait), z);
                return;
            }
        }
        this.viewpager.setVisibility(8);
        showSwipeToRefresh(false);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(true));
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                DashboardGuestBookingsFragment.this.getBookingsData(false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void onStart() {
        super.onStart();
        this.chooseLocationPresenter.onStart();
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    private void setupViewPager(NonSwipeableViewPager nonSwipeableViewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        Fragment guestBookingsFragment = new GuestBookingsFragment();
        this.upcomingBookingsFragment = guestBookingsFragment;
        viewPagerAdapter.addFragment(guestBookingsFragment, getActivity().getResources().getString(R.string.guest_bookings_upcoming));
        guestBookingsFragment = new GuestBookingsFragment();
        this.currentBookingsFragment = guestBookingsFragment;
        viewPagerAdapter.addFragment(guestBookingsFragment, getActivity().getResources().getString(R.string.guest_bookings_current));
        guestBookingsFragment = new GuestBookingsFragment();
        this.previousBookingsFragment = guestBookingsFragment;
        viewPagerAdapter.addFragment(guestBookingsFragment, getActivity().getResources().getString(R.string.guest_bookings_previous));
        nonSwipeableViewPager.setAdapter(viewPagerAdapter);
        nonSwipeableViewPager.setOffscreenPageLimit(3);
    }

    public void onSuccess(BookingsResponse bookingsResponse) {
        if (bookingsResponse != null) {
            if (this.upcomingBookingsFragment != null) {
                this.upcomingBookingsFragment.setData(bookingsResponse.getUpcomingBookings(), new BookingsUtil(0));
                this.upcomingBookingsFragment.setLocationPresenter(this.chooseLocationPresenter);
            }
            if (this.currentBookingsFragment != null) {
                this.currentBookingsFragment.setData(bookingsResponse.getCurrentBookings(), new BookingsUtil(1));
                this.currentBookingsFragment.setLocationPresenter(this.chooseLocationPresenter);
            }
            if (this.previousBookingsFragment != null) {
                this.previousBookingsFragment.setData(bookingsResponse.getPreviousBookings(), new BookingsUtil(2));
                this.previousBookingsFragment.setLocationPresenter(this.chooseLocationPresenter);
            }
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            aPIError = getString(R.string.general_api_error_msg);
        } else {
            aPIError = aPIError.getSeken_errors();
        }
        this.viewpager.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_close_red);
        this.noResultTV.setText(aPIError);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.chooseLocationPresenter.requestPermissionResult(i, strArr, iArr);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.chooseLocationPresenter.onActivityResult(i, i2, intent);
    }

    public void onStop() {
        dismissProgress();
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        if (this.chooseLocationPresenter != null) {
            this.chooseLocationPresenter.onStop();
        }
        super.onStop();
    }

    public void onDetach() {
        if (this.bookingsPresenter != null) {
            this.bookingsPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void setCurrentLocation(LatLng latLng) {
        switch (this.viewpager.getCurrentItem()) {
            case 0:
                if (this.upcomingBookingsFragment != null) {
                    this.upcomingBookingsFragment.setLocation(latLng);
                    return;
                }
                break;
            case 1:
                if (this.currentBookingsFragment != null) {
                    this.currentBookingsFragment.setLocation(latLng);
                    return;
                }
                break;
            case 2:
                if (this.previousBookingsFragment != null) {
                    this.previousBookingsFragment.setLocation(latLng);
                    break;
                }
                break;
            default:
                break;
        }
    }

    public Activity getActivityInstance() {
        return getActivity();
    }

    @NonNull
    public Context getSekenApplicationContext() {
        return getActivityInstance().getApplicationContext();
    }

    public void showLocationPermissionDeniedAlert(boolean z) {
        if (z) {
            showPermissionDeniedDialogIfCheckNeverAskAgain(getActivity(), getString(R.string.permission_denied_explanation));
        } else {
            showToast(getString(true));
        }
    }

    private void showPermissionDeniedDialogIfCheckNeverAskAgain(Context context, String str) {
        context = new Builder(context).setTitle(R.string.settings_label).setIcon(R.mipmap.ic_app_icon).setMessage(str).setPositiveButton(17039370, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface = new Intent();
                dialogInterface.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                dialogInterface.setData(Uri.fromParts("package", DashboardGuestBookingsFragment.this.getActivityInstance().getPackageName(), null));
                DashboardGuestBookingsFragment.this.startActivity(dialogInterface);
            }
        }).create();
        context.setCanceledOnTouchOutside(null);
        context.show();
    }
}
