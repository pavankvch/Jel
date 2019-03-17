package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.feedback.GuestHostFeedBackPresenter;
import com.businesslogic.feedback.IGuestHostFeedBackView;
import com.businesslogic.framework.ErrorCodes;
import com.data.feedback.FeedBackCombinedResponse;
import com.data.retrofit.RetrofitClient;
import com.jelsat.R;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.fragments.SubmitFeedbackFragment;
import com.jelsat.widgets.FancyButton;

public class FeedbackActivity extends BaseAppCompactActivity implements IGuestHostFeedBackView {
    private SubmitFeedbackFragment guestFragment;
    @BindView(2131362733)
    TextView headingTv;
    private SubmitFeedbackFragment hostFragment;
    private boolean isHost;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private GuestHostFeedBackPresenter presenter = new GuestHostFeedBackPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362641)
    TabLayout tabs;
    @BindView(2131362832)
    ViewPager viewpager;

    public int getActivityLayout() {
        return R.layout.toolbar_tabs_viewpager;
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getData(false);
    }

    @OnClick({2131361892})
    public void gotoBack() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.isHost = getIntent().getBooleanExtra(StringConstants.HOST_PROFILE, false);
        this.headingTv.setText(getResources().getString(R.string.profile_feedback_heading));
        this.swipeContainer.setEnabled(false);
        setupViewPager(this.viewpager);
        this.tabs.setupWithViewPager(this.viewpager);
        getData(true);
    }

    private void getData(boolean z) {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.viewpager.setVisibility(0);
            if (this.presenter != null) {
                this.presenter.getGuestHostFeedBackCategories(z);
                return;
            }
        }
        this.viewpager.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(true));
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        Fragment submitFeedbackFragment = new SubmitFeedbackFragment();
        this.guestFragment = submitFeedbackFragment;
        viewPagerAdapter.addFragment(submitFeedbackFragment, getResources().getString(R.string.profile_feedback_guest));
        submitFeedbackFragment = new SubmitFeedbackFragment();
        this.hostFragment = submitFeedbackFragment;
        viewPagerAdapter.addFragment(submitFeedbackFragment, getResources().getString(R.string.profile_feedback_host));
        viewPager.setAdapter(viewPagerAdapter);
        if (this.isHost) {
            viewPager.setCurrentItem(1);
        } else {
            viewPager.setCurrentItem(0);
        }
    }

    public void onSuccess(FeedBackCombinedResponse feedBackCombinedResponse) {
        if (this.guestFragment != null) {
            this.guestFragment.setData(feedBackCombinedResponse.getGuestFeedback());
        }
        if (this.hostFragment != null) {
            this.hostFragment.setData(feedBackCombinedResponse.getHostFeedback());
        }
    }

    public void onError(String str, int i) {
        if (i != ErrorCodes.API_ERROR) {
            if (i == ErrorCodes.INTERNAL_SERVER_ERROR) {
                str = getString(R.string.internal_server_error);
            } else if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                str = getString(R.string.socket_time_out_error);
            } else if (i != 511) {
                str = getString(R.string.general_api_error_msg);
            } else {
                str = getString(R.string.network_error);
            }
        }
        this.viewpager.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_close_red);
        this.noResultTV.setText(str);
    }

    protected void onStop() {
        dismissProgress();
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
