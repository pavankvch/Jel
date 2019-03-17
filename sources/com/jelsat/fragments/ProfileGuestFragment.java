package com.jelsat.fragments;

import android.os.Bundle;
import butterknife.OnClick;
import com.jelsat.R;
import com.jelsat.activities.FeedbackActivity;
import com.jelsat.activities.HelpActivity;
import com.jelsat.activities.InviteActivity;
import com.jelsat.activities.SettingsActivity;
import com.jelsat.activities.WalletActivity;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;

public class ProfileGuestFragment extends BaseFragment {
    public int getFragmentLayoutId() {
        return R.layout.fragment_profile_guest;
    }

    @OnClick({2131362259})
    public void gotoInvite() {
        goToActivity(InviteActivity.class);
    }

    @OnClick({2131362836})
    public void gotoWallet() {
        goToActivity(WalletActivity.class);
    }

    @OnClick({2131362208})
    public void gotoGuestHelp() {
        new Bundle().putBoolean(StringConstants.HOST_PROFILE, false);
        goToActivity(HelpActivity.class);
    }

    @OnClick({2131362586})
    public void gotoGuestSettings() {
        goToActivity(SettingsActivity.class);
    }

    @OnClick({2131362167})
    public void gotoFeedback() {
        new Bundle().putBoolean(StringConstants.HOST_PROFILE, false);
        goToActivity(FeedbackActivity.class);
    }
}
