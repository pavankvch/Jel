package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.data.utils.PrefUtils;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.BadgeEvent;
import com.jelsat.events.ChangeBottomTabsEvent;
import com.jelsat.events.HomeBadgeEvent;
import com.jelsat.fragments.DashBoardHostInboxFragment;
import com.jelsat.fragments.DashBoardProfileFragment;
import com.jelsat.fragments.DashboardGuestBookingsFragment;
import com.jelsat.fragments.DashboardGuestInboxFragment;
import com.jelsat.fragments.DashboardHomeFragment;
import com.jelsat.fragments.DashboardHostBookingsFragment;
import com.jelsat.fragments.DashboardHostPaymentsFragment;
import com.jelsat.fragments.DashboardSavedFragment;
import com.jelsat.fragments.PropertyListingFragment;
import com.jelsat.utils.Utils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DashBoardActivity extends BaseAppCompactActivity {
    private String CURRENT_TAG = "tab1";
    private final String TAG_TAB1 = "tab1";
    private final String TAG_TAB2 = "tab2";
    private final String TAG_TAB3 = "tab3";
    private final String TAG_TAB4 = "tab4";
    private final String TAG_TAB5 = "tab5";
    @BindView(2131362061)
    BottomNavigationViewEx dashBoardBottomNavigation;
    private int[] dashboardIcons = new int[]{R.drawable.ic_home_selected, R.drawable.ic_saved_selection, R.drawable.ic_bookings_selected, R.drawable.ic_inbox_selected, R.drawable.ic_profile_selected};
    private boolean doubleBackToExitPressedOnce = false;
    private TextView guestBadgeTextView;
    private int guestInboxCount;
    private String[] guestTitles;
    private TextView hostBadgeTextView;
    private int[] hostIcons = new int[]{R.drawable.ic_bookings_selected, R.drawable.ic_inbox_selected, R.drawable.ic_listing_selected_v2, R.drawable.ic_payments_selected, R.drawable.ic_profile_selected};
    private int hostInboxCount;
    private String[] hostTitles;
    private int imageWidth;
    private boolean isHostSelected;
    private int navItemIndex = 0;

    public int getActivityLayout() {
        return R.layout.activity_dashboard;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.isHostSelected = PrefUtils.getInstance().isHostSelected();
        this.guestTitles = getResources().getStringArray(R.array.dashboard_tabs);
        this.hostTitles = getResources().getStringArray(R.array.host_tabs);
        if (this.isHostSelected) {
            this.dashBoardBottomNavigation.inflateMenu(R.menu.menu_navigation_host_with_viewpager);
        } else {
            this.dashBoardBottomNavigation.inflateMenu(R.menu.menu_navigation_with_view_pager);
        }
        disableAllAnimation(this.dashBoardBottomNavigation);
        setUpBottomNavigationView();
        boolean z = false;
        if (getIntent().getBooleanExtra(StringConstants.FROM_PAYMENT_SUCCESS, false)) {
            if (getIntent().getBooleanExtra(StringConstants.FROM_GUEST, false) != null) {
                this.navItemIndex = 2;
                this.CURRENT_TAG = "tab3";
            } else {
                this.navItemIndex = 0;
                this.CURRENT_TAG = "tab1";
            }
        } else if (getIntent().getBooleanExtra(StringConstants.FROM_SETTINGS_PAGE, false)) {
            this.navItemIndex = 4;
            this.CURRENT_TAG = "tab5";
            z = true;
        } else if (bundle == null) {
            this.navItemIndex = 0;
            this.CURRENT_TAG = "tab1";
        }
        loadFragment(z);
        bundle = this.dashBoardBottomNavigation.getBottomNavigationItemView(3);
        this.guestBadgeTextView = addBadge(bundle);
        bundle.addView(this.guestBadgeTextView);
        bundle = this.dashBoardBottomNavigation.getBottomNavigationItemView(1);
        this.hostBadgeTextView = addBadge(bundle);
        bundle.addView(this.hostBadgeTextView);
        this.guestBadgeTextView.setVisibility(8);
        this.hostBadgeTextView.setVisibility(8);
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void setUpBottomNavigationView() {
        this.dashBoardBottomNavigation.setOnNavigationItemSelectedListener(new DashBoardActivity$1(this));
        this.dashBoardBottomNavigation.setOnNavigationItemReselectedListener(new DashBoardActivity$2(this));
    }

    private void loadFragment(boolean z) {
        selectNavMenu();
        new Handler().post(new DashBoardActivity$3(this, z));
    }

    private void selectNavMenu() {
        this.dashBoardBottomNavigation.getMenu().getItem(this.navItemIndex).setChecked(true);
    }

    private Fragment getHomeFragment(boolean z) {
        switch (this.navItemIndex) {
            case 0:
                if (this.isHostSelected) {
                    return new DashboardHostBookingsFragment();
                }
                return new DashboardHomeFragment();
            case 1:
                if (this.isHostSelected) {
                    return new DashBoardHostInboxFragment();
                }
                return new DashboardSavedFragment();
            case 2:
                if (this.isHostSelected) {
                    return new PropertyListingFragment();
                }
                return new DashboardGuestBookingsFragment();
            case 3:
                if (this.isHostSelected) {
                    return new DashboardHostPaymentsFragment();
                }
                return new DashboardGuestInboxFragment();
            case 4:
                return DashBoardProfileFragment.newInstance(z);
            default:
                if (this.isHostSelected) {
                    return new DashboardHostBookingsFragment();
                }
                return new DashboardHomeFragment();
        }
    }

    private void disableAllAnimation(BottomNavigationViewEx bottomNavigationViewEx) {
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeTabs(ChangeBottomTabsEvent changeBottomTabsEvent) {
        if (changeBottomTabsEvent != null) {
            this.isHostSelected = changeBottomTabsEvent.isHostSelected();
            for (int i = 0; i < this.dashBoardBottomNavigation.getMenu().size(); i++) {
                MenuItem item;
                if (changeBottomTabsEvent.isHostSelected()) {
                    item = this.dashBoardBottomNavigation.getMenu().getItem(i);
                    item.setIcon(this.hostIcons[i]);
                    item.setTitle(this.hostTitles[i]);
                } else {
                    item = this.dashBoardBottomNavigation.getMenu().getItem(i);
                    item.setIcon(this.dashboardIcons[i]);
                    item.setTitle(this.guestTitles[i]);
                }
            }
            setUpBottomNavigationView();
            loadFragment(false);
            showBadge();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showBadgeCount(HomeBadgeEvent homeBadgeEvent) {
        if (homeBadgeEvent != null) {
            this.hostInboxCount = homeBadgeEvent.getHostInboxCount();
            this.guestInboxCount = homeBadgeEvent.getGuestInboxCount();
            showBadge();
        }
    }

    private void showBadge() {
        if (this.isHostSelected) {
            this.guestBadgeTextView.setVisibility(8);
            if (this.hostInboxCount > 0) {
                this.hostBadgeTextView.setVisibility(0);
                placeLayoutParamsToBadgeTextView(this.hostBadgeTextView);
                return;
            }
            this.hostBadgeTextView.setVisibility(8);
            return;
        }
        this.hostBadgeTextView.setVisibility(8);
        if (this.guestInboxCount > 0) {
            this.guestBadgeTextView.setVisibility(0);
            placeLayoutParamsToBadgeTextView(this.guestBadgeTextView);
            return;
        }
        this.guestBadgeTextView.setVisibility(8);
    }

    private void placeLayoutParamsToBadgeTextView(TextView textView) {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 49);
        layoutParams.setMargins((this.imageWidth / 2) - 10, 20, 0, 0);
        textView.setLayoutParams(layoutParams);
    }

    private TextView addBadge(@NonNull BottomNavigationItemView bottomNavigationItemView) {
        ImageView imageView = (ImageView) bottomNavigationItemView.getChildAt(0);
        imageView.getViewTreeObserver().addOnPreDrawListener(new DashBoardActivity$4(this, imageView));
        bottomNavigationItemView = new TextView(this);
        bottomNavigationItemView.setMaxWidth(Utils.getPixelsFromDPs(this, 6));
        bottomNavigationItemView.setMaxHeight(Utils.getPixelsFromDPs(this, 6));
        bottomNavigationItemView.setBackgroundResource(R.drawable.badge_dot);
        return bottomNavigationItemView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ShowNotificationBadgeCount(BadgeEvent badgeEvent) {
        if (badgeEvent != null) {
            if (this.isHostSelected) {
                this.hostInboxCount = badgeEvent.getBadgeCount();
                this.guestBadgeTextView.setVisibility(8);
                if (badgeEvent.getBadgeCount() > null) {
                    this.hostBadgeTextView.setVisibility(0);
                    placeLayoutParamsToBadgeTextView(this.hostBadgeTextView);
                    return;
                }
                this.hostBadgeTextView.setVisibility(8);
                return;
            }
            this.guestInboxCount = badgeEvent.getBadgeCount();
            this.hostBadgeTextView.setVisibility(8);
            if (badgeEvent.getBadgeCount() > null) {
                this.guestBadgeTextView.setVisibility(0);
                placeLayoutParamsToBadgeTextView(this.guestBadgeTextView);
                return;
            }
            this.guestBadgeTextView.setVisibility(8);
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onBackPressed() {
        if (this.navItemIndex != 0) {
            this.navItemIndex = 0;
            this.CURRENT_TAG = "tab1";
            loadFragment(false);
        } else if (this.doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            showToast(getString(R.string.please_click_back_label));
            new Handler().postDelayed(new DashBoardActivity$5(this), 4000);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra(StringConstants.FROM_CALENDAR, false)) {
            this.navItemIndex = 0;
            this.CURRENT_TAG = "tab1";
            loadFragment(false);
            return;
        }
        if (intent.getBooleanExtra(StringConstants.FROM_PUBLISH_PAGE, false) != null) {
            this.navItemIndex = 2;
            this.CURRENT_TAG = "tab3";
            loadFragment(false);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null && currentFragment.isVisible()) {
            if (currentFragment instanceof DashboardGuestBookingsFragment) {
                currentFragment.onRequestPermissionsResult(i, strArr, iArr);
            } else if (currentFragment instanceof DashboardHostBookingsFragment) {
                currentFragment.onRequestPermissionsResult(i, strArr, iArr);
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment != null && currentFragment.isVisible()) {
            if (currentFragment instanceof DashboardGuestBookingsFragment) {
                currentFragment.onActivityResult(i, i2, intent);
            } else if (currentFragment instanceof DashboardHostBookingsFragment) {
                currentFragment.onActivityResult(i, i2, intent);
            }
        }
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.main_container);
    }

    private boolean isUserLoggedIn() {
        return (PrefUtils.getInstance().getUserAccessToken() == null || PrefUtils.getInstance().getCookie() == null) ? false : true;
    }
}
