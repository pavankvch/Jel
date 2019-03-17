package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.payments.HostPropertiesPresenter;
import com.businesslogic.payments.IHostPropertiesView;
import com.businesslogic.propertycostcalendar.PropertyCostCalendarPresenter;
import com.data.payments.HostProperty;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.FragmentLoadedEvent;
import com.jelsat.fragments.CalendarDatesAvailabilityFragment;
import com.jelsat.fragments.CalendarDatesCostUpdateFragment;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CalendarActivity extends BaseAppCompactActivity implements OnTabSelectedListener, IHostPropertiesView {
    private String CURRENT_TAG = "calendar_tab1";
    private final String TAG_TAB1 = "calendar_tab1";
    private final String TAG_TAB2 = "calendar_tab2";
    private Fragment fragment;
    private HostPropertiesPresenter hostPropertiesPresenter = new HostPropertiesPresenter(this, RetrofitClient.getAPIService());
    private List<HostProperty> hostPropertyList;
    @BindView(2131362641)
    TabLayout inboxTabs;
    private boolean isEditProperty;
    @BindView(2131362340)
    ImageView moreTV;
    private PropertyCostCalendarPresenter propertyCostCalendarPresenter;
    private String propertyName;
    @BindView(2131362799)
    TextView upcomingBookingTV;

    public int getActivityLayout() {
        return R.layout.activity_calendar;
    }

    public void onTabReselected(Tab tab) {
    }

    public void showSwipeToRefresh(boolean z) {
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131362799})
    public void clickOnUpComing() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(67108864);
        intent.putExtra(StringConstants.FROM_CALENDAR, true);
        startActivity(intent);
        finish();
    }

    @OnClick({2131362509})
    public void clickOnResetButton() {
        showToast(getString(R.string.reset_label));
    }

    @OnClick({2131362800})
    public void clickOnUpdate() {
        showToast(getString(R.string.calender_cost_updated));
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        bundle = getIntent().getStringExtra(StringConstants.PROPERTY_ID);
        this.isEditProperty = getIntent().getBooleanExtra(StringConstants.IS_EDIT_PROPERTY, false);
        this.propertyName = getIntent().getStringExtra(StringConstants.PROPERTY_NAME);
        this.inboxTabs.addOnTabSelectedListener(this);
        if (bundle == null) {
            getPublishedPropertiesList();
        } else {
            loadFragment();
        }
        setTabIcons();
    }

    private void setTabIcons() {
        this.inboxTabs.addTab(this.inboxTabs.newTab().setCustomView(getAvaliableCustomView()));
        this.inboxTabs.addTab(this.inboxTabs.newTab().setCustomView(getCostCalenderCustomView()));
    }

    private View getAvaliableCustomView() {
        View inflate = getLayoutInflater().inflate(R.layout.custom_inbox_tab, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.fragment_icon);
        imageView.setImageResource(R.drawable.ic_calendar_availability);
        imageView.setLayoutParams(new LayoutParams(100, 100));
        TextView textView = (TextView) inflate.findViewById(R.id.fragment_title);
        ((TextView) inflate.findViewById(R.id.fragment_count)).setVisibility(8);
        textView.setText(getString(R.string.calendar_availabilty));
        textView.setTextColor(applyColor(R.color.white));
        return inflate;
    }

    private View getCostCalenderCustomView() {
        View inflate = getLayoutInflater().inflate(R.layout.custom_inbox_tab, null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.fragment_icon);
        imageView.setImageResource(R.drawable.ic_cost_calendar_manager);
        TextView textView = (TextView) inflate.findViewById(R.id.fragment_title);
        imageView.setLayoutParams(new LayoutParams(100, 100));
        ((TextView) inflate.findViewById(R.id.fragment_count)).setVisibility(8);
        textView.setText(getString(R.string.calendar_cost_calendar));
        return inflate;
    }

    private void loadFragment() {
        new Handler().post(new CalendarActivity$1(this));
    }

    private Fragment getHomeFragment() {
        switch (this.inboxTabs.getSelectedTabPosition()) {
            case 0:
                return new CalendarDatesAvailabilityFragment();
            case 1:
                return new CalendarDatesCostUpdateFragment();
            default:
                return new CalendarDatesAvailabilityFragment();
        }
    }

    private void getPublishedPropertiesList() {
        if (this.hostPropertiesPresenter != null) {
            this.hostPropertiesPresenter.getHostProperties(getString(R.string.please_wait), true);
        }
    }

    public void onSuccess(List<HostProperty> list) {
        loadFragment();
        this.hostPropertyList = new ArrayList();
        this.hostPropertyList.addAll(list);
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.calendar_date_container);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isFragmentLoaded(FragmentLoadedEvent fragmentLoadedEvent) {
        if (fragmentLoadedEvent != null && this.isEditProperty == null) {
            fragmentLoadedEvent = getCurrentFragment();
            if (fragmentLoadedEvent != null) {
                if (fragmentLoadedEvent instanceof CalendarDatesAvailabilityFragment) {
                    ((CalendarDatesAvailabilityFragment) fragmentLoadedEvent).setPropertiesList(this.hostPropertyList);
                } else if (fragmentLoadedEvent instanceof CalendarDatesCostUpdateFragment) {
                    ((CalendarDatesCostUpdateFragment) fragmentLoadedEvent).setPropertiesList(this.hostPropertyList);
                }
            }
        }
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getpropertyCostCalendarPresenter(PropertyCostCalendarPresenter propertyCostCalendarPresenter) {
        this.propertyCostCalendarPresenter = propertyCostCalendarPresenter;
    }

    public void onDetachedFromWindow() {
        if (this.hostPropertiesPresenter != null) {
            this.hostPropertiesPresenter.unSubscribeDisposables();
        }
        if (this.propertyCostCalendarPresenter != null) {
            this.propertyCostCalendarPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    public void relaunch() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.addFlags(32768);
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        startActivity(intent);
        Runtime.getRuntime().exit(0);
        finish();
    }

    public void onTabSelected(Tab tab) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tab.getPosition());
        Log.d("tabPosition", stringBuilder.toString());
        switch (tab.getPosition()) {
            case 0:
                this.fragment = new CalendarDatesAvailabilityFragment();
                loadFragment();
                break;
            case 1:
                this.fragment = new CalendarDatesCostUpdateFragment();
                loadFragment();
                break;
            default:
                this.fragment = new CalendarDatesAvailabilityFragment();
                loadFragment();
                break;
        }
        tab = tab.getCustomView();
        if (tab != null) {
            ((TextView) tab.findViewById(R.id.fragment_title)).setTextColor(applyColor(R.color.white));
        }
    }

    public void onTabUnselected(Tab tab) {
        tab = tab.getCustomView();
        if (tab != null) {
            ((TextView) tab.findViewById(R.id.fragment_title)).setTextColor(applyColor(R.color.transparent_image_color_40_percent));
        }
    }
}
