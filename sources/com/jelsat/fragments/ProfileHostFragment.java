package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.OnClick;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.activities.AddYourPropertySteps;
import com.jelsat.activities.CalendarActivity;
import com.jelsat.activities.FeedbackActivity;
import com.jelsat.activities.HelpActivity;
import com.jelsat.activities.HostbankDetailsActivity;
import com.jelsat.activities.InviteActivity;
import com.jelsat.activities.SettingsActivity;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.FragmentLoadedEvent;
import org.greenrobot.eventbus.EventBus;

public class ProfileHostFragment extends BaseFragment {
    public int getFragmentLayoutId() {
        return R.layout.fragment_profile_host;
    }

    @OnClick({2131362821})
    public void clickOnAddProperty() {
        Intent intent = new Intent(getActivity(), AddYourPropertySteps.class);
        intent.putExtra("FromActivity", "fromProfileHostFragment");
        intent.putExtra("checkIntent", false);
        startActivity(intent);
    }

    @OnClick({2131362824})
    public void clickOnCalendar() {
        goToActivity(CalendarActivity.class);
    }

    @OnClick({2131362822})
    public void gotoBankDetails() {
        goToActivity(HostbankDetailsActivity.class);
    }

    @OnClick({2131362829})
    public void gotoHostSettings() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(StringConstants.HOST_PROFILE, true);
        goToActivity(SettingsActivity.class, bundle);
    }

    @OnClick({2131362825})
    public void gotoHostFeedback() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(StringConstants.HOST_PROFILE, true);
        goToActivity(FeedbackActivity.class, bundle);
    }

    @OnClick({2131362209})
    public void gotoHostHelp() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(StringConstants.HOST_PROFILE, true);
        goToActivity(HelpActivity.class, bundle);
    }

    @OnClick({2131362216})
    public void gotoHostInvite() {
        goToActivity(InviteActivity.class);
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (PrefUtils.getInstance().isNeedShowIntroDialog() != null) {
            EventBus.getDefault().post(new FragmentLoadedEvent(true));
        }
    }
}
