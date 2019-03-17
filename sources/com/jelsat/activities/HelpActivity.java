package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.help.GuestHostHelpPresenter;
import com.businesslogic.help.IGuestHostHelpView;
import com.data.help.FaqCombinedResponse;
import com.data.retrofit.RetrofitClient;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.fragments.HelpFragment;

public class HelpActivity extends BaseAppCompactActivity implements IGuestHostHelpView {
    private HelpFragment guestFragment;
    @BindView(2131362733)
    TextView headingTv;
    @BindView(2131362641)
    TabLayout helpTabs;
    private HelpFragment hostFragment;
    private boolean isHost;
    private GuestHostHelpPresenter presenter;
    @BindView(2131362636)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(2131362832)
    ViewPager viewpager;

    public int getActivityLayout() {
        return R.layout.activity_help;
    }

    @OnClick({2131361892})
    public void gotoBack() {
        finish();
    }

    @OnClick({2131362247})
    public void gotoSupport() {
        new Bundle().putBoolean(StringConstants.USER_TYPE, isVisibleFragmentHost());
        goToActivity(HelpSupportActivity.class);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.swipeRefreshLayout.setEnabled(false);
        this.isHost = getIntent().getBooleanExtra(StringConstants.HOST_PROFILE, false);
        this.headingTv.setText(getResources().getString(R.string.profile_help_heading));
        setupViewPager(this.viewpager);
        this.helpTabs.setupWithViewPager(this.viewpager);
        initPresenter();
    }

    private void initPresenter() {
        if (isNetworkConnected()) {
            this.presenter = new GuestHostHelpPresenter(this, RetrofitClient.getAPIService());
            this.presenter.getGuestHostHelpCategories();
            return;
        }
        showToast(getString(R.string.error_message_network));
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment helpFragment = new HelpFragment();
        this.guestFragment = helpFragment;
        viewPagerAdapter.addFragment(helpFragment, getResources().getString(R.string.profile_help_guest));
        helpFragment = new HelpFragment();
        this.hostFragment = helpFragment;
        viewPagerAdapter.addFragment(helpFragment, getResources().getString(R.string.profile_help_host));
        viewPager.setAdapter(viewPagerAdapter);
        if (this.isHost) {
            viewPager.setCurrentItem(1);
        } else {
            viewPager.setCurrentItem(0);
        }
    }

    public void onSuccess(FaqCombinedResponse faqCombinedResponse) {
        if (this.guestFragment != null) {
            this.guestFragment.setData(faqCombinedResponse.getGuestHelp());
        }
        if (this.hostFragment != null) {
            this.hostFragment.setData(faqCombinedResponse.getHostHelp());
        }
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

    private boolean isVisibleFragmentHost() {
        return this.viewpager.getCurrentItem() == 1;
    }

    public void onDetachedFromWindow() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
