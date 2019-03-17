package com.jelsat.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.SeedAndLanguage.ISeedAndLanguageChangeView;
import com.businesslogic.SeedAndLanguage.SeedAndLanguageChangePresenter;
import com.businesslogic.becomehost.BecomeHostPresenter;
import com.businesslogic.becomehost.IBecomeHostView;
import com.businesslogic.framework.ErrorCodes;
import com.data.SeedAndLanguage.SeedAndLanguageCombinedResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.activities.EditProfileActivity;
import com.jelsat.activities.PublicProfileActivity;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.ChangeBottomTabsEvent;
import com.jelsat.events.FragmentLoadedEvent;
import com.jelsat.utils.GlideApp;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DashBoardProfileFragment extends BaseFragment implements ISeedAndLanguageChangeView, IBecomeHostView {
    @BindView(2131361934)
    RadioButton btnGuest;
    @BindView(2131361935)
    RadioButton btnHost;
    @BindView(2131362723)
    TextView emailNotVerifiedTV;
    private PrefUtils prefUtils;
    private BecomeHostPresenter presenter;
    @BindView(2131362248)
    ImageView profileImg;
    @BindView(2131362750)
    TextView profileNameTv;
    private SeedAndLanguageChangePresenter seedAndLanguageChangePresenter;

    public int getFragmentLayoutId() {
        return R.layout.fragment_dashboard_profile;
    }

    @OnClick({2131361935})
    public void clickOnHost() {
        if (Integer.parseInt(this.prefUtils.getUser().getHost()) == 0) {
            this.presenter.becomeHost(getString(R.string.please_wait));
            return;
        }
        PrefUtils.getInstance().saveHostSelection(true);
        loadFragment(new ProfileHostFragment());
        EventBus.getDefault().post(new ChangeBottomTabsEvent(true));
    }

    @OnClick({2131361934})
    public void clickOnGuest() {
        PrefUtils.getInstance().saveHostSelection(false);
        loadFragment(new ProfileGuestFragment());
        EventBus.getDefault().post(new ChangeBottomTabsEvent(false));
    }

    @OnClick({2131362763})
    public void gotoPublicProfile() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.UID, PrefUtils.getInstance().getUser().getUid());
        bundle.putInt(StringConstants.NEED_REVIEW, 1);
        goToActivity(PublicProfileActivity.class, bundle);
    }

    @OnClick({2131362722})
    public void gotoEditProfileScreen() {
        goToActivity(EditProfileActivity.class);
    }

    public static DashBoardProfileFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(StringConstants.FROM_SETTINGS_PAGE, z);
        z = new DashBoardProfileFragment();
        z.setArguments(bundle);
        return z;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.prefUtils = PrefUtils.getInstance();
        initUserData(this.prefUtils);
        this.btnHost.setTextColor(getResources().getColor(R.color.app_background));
        this.btnGuest.setTextColor(getResources().getColor(R.color.app_background));
        if (this.prefUtils.getUser() == null || this.prefUtils.getUser().getHost() == null || this.prefUtils.getUser().getHost().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES) == null) {
            this.presenter = new BecomeHostPresenter(this, RetrofitClient.getAPIService());
            this.btnHost.setText(getString(R.string.become_host));
        } else {
            this.btnHost.setText(getString(R.string.profile_host));
        }
        if (!(getArguments() == null || getArguments().getBoolean(StringConstants.FROM_SETTINGS_PAGE) == null)) {
            initSeedApiCall();
        }
        return layoutInflater;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.prefUtils.isHostSelected() != null) {
            loadFragment(new ProfileHostFragment());
            this.btnHost.setChecked(true);
            this.btnHost.setTypeface(Typeface.defaultFromStyle(1));
            return;
        }
        loadFragment(new ProfileGuestFragment());
        this.btnGuest.setChecked(true);
        this.btnGuest.setTypeface(Typeface.defaultFromStyle(1));
    }

    private void initSeedApiCall() {
        this.seedAndLanguageChangePresenter = new SeedAndLanguageChangePresenter(this, RetrofitClient.getAPIService());
        this.seedAndLanguageChangePresenter.changeDeviceLanguageAndDoSeedCall();
    }

    private void initUserData(PrefUtils prefUtils) {
        prefUtils = prefUtils.getUser();
        if (prefUtils != null) {
            if (prefUtils.getEmailVerified().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                this.emailNotVerifiedTV.setText(prefUtils.getMail());
            } else {
                this.emailNotVerifiedTV.setText(getString(R.string.profile_email_not_verified));
            }
            if (prefUtils.getFullName() != null) {
                this.profileNameTv.setText(prefUtils.getFullName().trim());
            }
            GlideApp.with(requireActivity()).load(prefUtils.getGuestImage()).placeholder(R.drawable.ic_profile_image_1).error(R.drawable.ic_profile_image_1).into(this.profileImg);
        }
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void loadFragment(Fragment fragment) {
        new Handler().post(new DashBoardProfileFragment$1(this, fragment));
    }

    public void onResume() {
        super.onResume();
        initUserData(this.prefUtils);
    }

    public void onHostSuccess() {
        this.btnHost.setText(getString(R.string.profile_host));
        loadFragment(new ProfileHostFragment());
        EventBus.getDefault().post(new ChangeBottomTabsEvent(true));
    }

    public void onError(APIError aPIError) {
        dismissProgress();
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isFragmentLoaded(FragmentLoadedEvent fragmentLoadedEvent) {
        if (fragmentLoadedEvent != null && this.prefUtils.getUser() != null && this.prefUtils.getUser().getHost() != null && this.prefUtils.getUser().getHost().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) != null) {
            new BecomeHostIntroDialogFragment().show(getFragmentManager(), "hostdialogue");
            fragmentLoadedEvent = this.prefUtils.getUser();
            fragmentLoadedEvent.setHost(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.prefUtils.saveUser(fragmentLoadedEvent);
            this.prefUtils.saveHostSelection(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isProfileUpdated(PrefUtils prefUtils) {
        if (prefUtils != null) {
            initUserData(prefUtils);
        }
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetach() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        if (this.seedAndLanguageChangePresenter != null) {
            this.seedAndLanguageChangePresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void onSuccess(SeedAndLanguageCombinedResponse seedAndLanguageCombinedResponse) {
        dismissProgress();
        if (seedAndLanguageCombinedResponse == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else if (seedAndLanguageCombinedResponse.getSeedResponse() != null) {
            PrefUtils.getInstance().saveSeedResponse(seedAndLanguageCombinedResponse.getSeedResponse());
            PrefUtils.getInstance().saveUser(seedAndLanguageCombinedResponse.getSeedResponse().getUser());
        }
    }

    public void onError(APIError aPIError, int i) {
        dismissProgress();
        if (i != ErrorCodes.API_ERROR) {
            if (i == ErrorCodes.INTERNAL_SERVER_ERROR) {
                showToast(getString(R.string.internal_server_error));
            } else if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                showToast(getString(R.string.socket_time_out_error));
            } else if (i == 511) {
                showToast(getString(R.string.network_error));
            }
        } else if (aPIError != null) {
            showToast(aPIError.getSeken_errors());
        }
    }
}
