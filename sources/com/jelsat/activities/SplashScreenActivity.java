package com.jelsat.activities;

import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.logout.ILogoutView;
import com.businesslogic.logout.LogoutPresenter;
import com.businesslogic.propertyseed.IPropertySeedView;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.businesslogic.systemconnect.SystemConnectPresenter;
import com.businesslogic.usertoken.UserTokenPresenter;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.ApiCallStatus;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.firebase.DeleteTokenService;
import com.jelsat.widgets.FancyButton;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SplashScreenActivity extends BaseAppCompactActivity implements ILogoutView, IPropertySeedView {
    private LogoutPresenter logoutPresenter;
    @BindView(2131362437)
    ProgressBar progressBar;
    private PropertySeedPresenter propertySeedPresenter = new PropertySeedPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362517)
    FancyButton retryButton;
    private SystemConnectPresenter systemConnectPresenter;
    private UserTokenPresenter userTokenPresenter;

    public int getActivityLayout() {
        return R.layout.activity_splash_screen;
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getData();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.progressBar.getIndeterminateDrawable().setColorFilter(-1, Mode.MULTIPLY);
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    protected void onResume() {
        super.onResume();
        if (this.progressBar != null) {
            this.progressBar.setVisibility(0);
        }
        if (this.retryButton != null) {
            this.retryButton.setVisibility(8);
        }
        getData();
    }

    private void getData() {
        if (isNetworkConnected()) {
            if (this.progressBar != null) {
                this.progressBar.setVisibility(0);
            }
            if (this.retryButton != null) {
                this.retryButton.setVisibility(4);
            }
            this.propertySeedPresenter.getPropertySeed(getString(R.string.hang_on_a_moment), false);
            return;
        }
        if (this.progressBar != null) {
            this.progressBar.setVisibility(4);
        }
        if (this.retryButton != null) {
            this.retryButton.setVisibility(0);
        }
        showToast(getString(R.string.error_message_network));
    }

    public void onSuccess(SeedResponse seedResponse) {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(4);
        }
        if (this.retryButton != null) {
            this.retryButton.setVisibility(8);
        }
        if (seedResponse != null) {
            PrefUtils.getInstance().saveSeedResponse(seedResponse);
            if (!(seedResponse.getUser().getUid() == null || seedResponse.getUser().getUid().equals(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
                PrefUtils.getInstance().saveUser(seedResponse.getUser());
            }
            if (PrefUtils.getInstance().getUserAccessToken() != null && PrefUtils.getInstance().getCookie() != null) {
                goToActivity(DashBoardActivity.class);
                return;
            } else if (PrefUtils.getInstance().isSkipClicked() != null) {
                goToActivity(DashBoardActivity.class);
                return;
            } else {
                goToActivity(IntroActivity.class);
                return;
            }
        }
        showToast(getString(R.string.general_api_error_msg));
    }

    public void onError(APIError aPIError, int i) {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(4);
        }
        if (this.retryButton != null) {
            this.retryButton.setVisibility(0);
        }
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
            goToScreen(aPIError.getCode());
        }
    }

    private void goToScreen(int i) {
        switch (i) {
            case 801:
                PrefUtils.getInstance().clearSharedPreferences();
                startService(new Intent(this, DeleteTokenService.class));
                goToActivity(HomeActivity.class);
                return;
            case 802:
                goToActivity(DashBoardActivity.class);
                return;
            case 803:
                showLoading();
                PrefUtils.getInstance().clearSharedPreferences();
                this.userTokenPresenter = new UserTokenPresenter(RetrofitClient.getAPIService(), PrefUtils.getInstance());
                this.userTokenPresenter.getUserToken();
                break;
            default:
                break;
        }
    }

    protected void onStop() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(4);
        }
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.propertySeedPresenter != null) {
            this.propertySeedPresenter.unSubscribeDisposables();
        }
        if (this.userTokenPresenter != null) {
            this.userTokenPresenter.unSubscribeDisposables();
        }
        if (this.logoutPresenter != null) {
            this.logoutPresenter.unSubscribeDisposables();
        }
        if (this.systemConnectPresenter != null) {
            this.systemConnectPresenter.unSubScribeDisposable();
        }
        super.onDetachedFromWindow();
    }

    public void errorOccurred(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void logoutSuccess() {
        PrefUtils.getInstance().clearSharedPreferences();
        startService(new Intent(this, DeleteTokenService.class));
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void apiCallStatusEvent(ApiCallStatus apiCallStatus) {
        if (apiCallStatus == null || apiCallStatus.isSuccess() == null) {
            dismissProgress();
            return;
        }
        this.systemConnectPresenter = new SystemConnectPresenter(RetrofitClient.getAPIService(), PrefUtils.getInstance());
        this.systemConnectPresenter.getCookie();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void apiCall(Boolean bool) {
        dismissProgress();
        if (bool.booleanValue() != null) {
            this.logoutPresenter = new LogoutPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
            this.logoutPresenter.logoutFromApp(getString(R.string.please_wait));
        }
    }
}
