package com.jelsat.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.propertyseed.IPropertySeedView;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.businesslogic.socialsignin.ISocialSignInView;
import com.businesslogic.socialsignin.SocialSignInPresenter;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.retrofit.RetrofitClient;
import com.data.signin.SignInResponse;
import com.data.signup.SignUpRequest;
import com.data.socialsignin.SocialSignInRequest;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.country.Country;
import com.jelsat.events.FragmentLoadedEvent;
import com.jelsat.events.SocialDataEvent;
import com.jelsat.fragments.SignInFragment;
import com.jelsat.fragments.SignUpFragment;
import com.jelsat.helper.LocaleManager;
import com.jelsat.sociallogin.SmartLogin;
import com.jelsat.sociallogin.SmartLoginCallbacks;
import com.jelsat.sociallogin.SmartLoginConfig;
import com.jelsat.sociallogin.SmartLoginException;
import com.jelsat.sociallogin.SmartLoginFactory;
import com.jelsat.sociallogin.user.SmartUser;
import com.jelsat.utils.DialogUtil;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeActivity extends BaseAppCompactActivity implements IPropertySeedView, ISocialSignInView, SmartLoginCallbacks {
    private final String FACEBOOK = "Facebook";
    private final String GOOGLE = "Google";
    private SmartLoginConfig config;
    private DialogUtil dialogUtil;
    private boolean doubleBackToExitPressedOnce = false;
    @BindView(2131362158)
    TextView facebookButton;
    @BindView(2131362195)
    TextView googleButton;
    private boolean isEnglish;
    private boolean isSignUp = false;
    @BindView(2131362272)
    TextView languageTv;
    private boolean localeHasChanged = false;
    private int loginType;
    private PropertySeedPresenter propertySeedPresenter = new PropertySeedPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362599)
    TextView skipTv;
    private SmartLogin smartLogin;
    private SmartUser smartUser;
    @BindView(2131362605)
    LinearLayout socialButtonsLayout;
    private SocialSignInPresenter socialSignInPresenter = new SocialSignInPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    @BindView(2131362814)
    TextView userSignInTv;

    public int getActivityLayout() {
        return R.layout.activity_home;
    }

    @OnClick({2131362158})
    public void clickOnFacebookButton() {
        this.smartLogin = SmartLoginFactory.build(1);
        this.smartLogin.login(this.config);
    }

    @OnClick({2131362195})
    public void clickOnGoogleSignIn(View view) {
        this.smartLogin = SmartLoginFactory.build(null);
        this.smartLogin.login(this.config);
    }

    @OnClick({2131362814})
    public void clickOnUserSignIn() {
        this.smartUser = null;
        this.socialButtonsLayout.setVisibility(0);
        if (isVisibleFragmentSignUp()) {
            this.isSignUp = false;
            this.userSignInTv.setText(getString(R.string.sign_up_label));
            loadFragment(new SignInFragment());
            return;
        }
        this.isSignUp = true;
        this.userSignInTv.setText(getString(R.string.intro_sign_in_label));
        loadFragment(new SignUpFragment());
    }

    @OnClick({2131362599})
    public void clickOnSkip() {
        PrefUtils.getInstance().setClickSkip(true);
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        finish();
    }

    @OnClick({2131362011})
    public void clickOnSupportMail() {
        shareToGMail(new String[]{getString(R.string.support_email_link)}, "login/sign up support", "Please provide details");
    }

    public void shareToGMail(String[] strArr, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        intent.putExtra("android.intent.extra.SUBJECT", str);
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str2);
        str = null;
        for (ResolveInfo resolveInfo : getPackageManager().queryIntentActivities(intent, null)) {
            if (resolveInfo.activityInfo.packageName.endsWith(".gm") || resolveInfo.activityInfo.name.toLowerCase().contains("gmail")) {
                str = resolveInfo;
            }
        }
        if (str != null) {
            intent.setClassName(str.activityInfo.packageName, str.activityInfo.name);
        }
        startActivity(intent);
    }

    @OnClick({2131362272})
    public void changeLanguage() {
        this.localeHasChanged = true;
        this.isEnglish = true ^ this.isEnglish;
        if (this.isEnglish) {
            switchLanguage("en", LocaleManager.DEFAULT_COUNTRY);
        } else {
            switchLanguage("ar", "SA");
        }
    }

    public void switchLanguage(String str, String str2) {
        LocaleManager.setNewLocale(this, str, str2);
        RetrofitClient.changeApiBaseUrlBasedOnLanguage();
        Log.d("changedLanguage", LocaleManager.getLanguage(this));
        refresh();
    }

    public void refresh() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(65536);
        finish();
        overridePendingTransition(0, 0);
        Bundle bundle = new Bundle();
        if (this.isSignUp) {
            bundle.putBoolean(StringConstants.IS_JOIN_NOW, true);
        } else {
            bundle.putBoolean(StringConstants.IS_JOIN_NOW, false);
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("en")) {
            this.isEnglish = true;
            this.languageTv.setText(getString(R.string.change_language_dialog_option2));
        } else {
            this.isEnglish = false;
            this.languageTv.setText(getString(R.string.change_language_dialog_option1));
        }
        this.facebookButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_facebook, 0, 0, 0);
        this.googleButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_google, 0, 0, 0);
        this.dialogUtil = new DialogUtil(this);
        if (bundle != null) {
            this.isSignUp = bundle.getBoolean("is_signup_fragment");
            if (this.isSignUp != null) {
                this.userSignInTv.setText(getString(R.string.intro_sign_in_label));
                loadFragment(new SignUpFragment());
            } else {
                this.userSignInTv.setText(getString(R.string.sign_up_label));
                loadFragment(new SignInFragment());
            }
        } else if (getIntent().getBooleanExtra(StringConstants.IS_JOIN_NOW, false) != null) {
            this.isSignUp = true;
            this.userSignInTv.setText(getString(R.string.intro_sign_in_label));
            loadFragment(new SignUpFragment());
        } else {
            this.isSignUp = false;
            this.userSignInTv.setText(getString(R.string.sign_up_label));
            loadFragment(new SignInFragment());
        }
        this.config = new SmartLoginConfig(this, this);
        this.config.setFacebookAppId(getString(R.string.facebook_app_id));
        this.config.setFacebookPermissions(null);
        this.config.setGoogleSignInClient(getGoogleSignInOptions());
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void loadFragment(Fragment fragment) {
        new Handler().post(new HomeActivity$1(this, fragment));
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.smartLogin != null) {
            this.smartLogin.onActivityResult(i, i2, intent, this.config);
        }
    }

    public void onLoginSuccess(SmartUser smartUser, int i) {
        this.smartUser = smartUser;
        this.loginType = i;
        boolean logout = this.smartLogin.logout(this);
        smartUser = smartUser.getUserId();
        i = i == 1 ? "Facebook" : "Google";
        if (logout) {
            doSocialSignIn(smartUser, i);
        }
    }

    private void doSocialSignIn(String str, String str2) {
        if (isNetworkConnected()) {
            SocialSignInRequest socialSignInRequest = new SocialSignInRequest();
            socialSignInRequest.setIdentifier(str);
            socialSignInRequest.setProvider(str2);
            this.socialSignInPresenter.doSocialSignIn(socialSignInRequest, getString(R.string.please_wait));
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    public void onLoginFailure(SmartLoginException smartLoginException) {
        showToast(smartLoginException.getMessage());
        Log.e("aaa", smartLoginException.getMessage());
    }

    public void socialSignInSuccess(SignInResponse signInResponse, PrefUtils prefUtils) {
        if (signInResponse.getUser().getOtpValidated().equalsIgnoreCase("No")) {
            prefUtils = signInResponse.getUser().getPhoneNumber().substring(signInResponse.getUser().getCountryCode().length());
            StringBuilder stringBuilder = new StringBuilder("+");
            stringBuilder.append(signInResponse.getUser().getCountryCode());
            Country countryByDialCode = Country.getCountryByDialCode(stringBuilder.toString());
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setFieldPhoneNumber(prefUtils);
            signUpRequest.setUserId(signInResponse.getUser().getUid());
            signUpRequest.setMail(signInResponse.getUser().getMail());
            signUpRequest.setIdentifier(this.smartUser.getUserId());
            signUpRequest.setProvider(this.loginType == 1 ? "Facebook" : "Google");
            prefUtils = new StringBuilder("+");
            prefUtils.append(signInResponse.getUser().getCountryCode());
            signUpRequest.setCountryCode(prefUtils.toString());
            if (countryByDialCode != null) {
                signUpRequest.setCountryFlag(countryByDialCode.getFlag());
            }
            signUpRequest.setFromSocial(true);
            EventBus.getDefault().postSticky(signUpRequest);
            goToActivity(OTPActivity.class);
            return;
        }
        prefUtils.saveSocialSignin(true);
        if (signInResponse.getToken() != null) {
            prefUtils.saveUserToken(signInResponse.getToken());
        }
        if (!(signInResponse.getSessionId() == null || signInResponse.getSessionName() == null)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(signInResponse.getSessionName());
            stringBuilder.append("=");
            stringBuilder.append(signInResponse.getSessionId());
            prefUtils.saveCookie(stringBuilder.toString());
        }
        if (prefUtils.getUserAccessToken() != null) {
            this.propertySeedPresenter.getPropertySeed(getString(R.string.hang_on_a_moment), true);
        }
    }

    public void saveFCMTokenSuccess() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    public void onSuccess(SeedResponse seedResponse) {
        if (seedResponse != null) {
            PrefUtils.getInstance().saveSeedResponse(seedResponse);
            PrefUtils.getInstance().saveUser(seedResponse.getUser());
            this.socialSignInPresenter.sendFcmTokenToServer(getString(R.string.please_wait));
            return;
        }
        showToast(getString(R.string.general_api_error_msg));
    }

    public void onError(APIError aPIError, int i) {
        if (i == ErrorCodes.API_ERROR) {
            if (aPIError.getCode() != 804) {
                showToast(aPIError.getSeken_errors());
            }
            goToScreen(aPIError.getCode());
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

    private void goToScreen(int i) {
        if (i != 802) {
            if (i == 804) {
                this.socialButtonsLayout.setVisibility(8);
                this.userSignInTv.setText(getString(R.string.intro_sign_in_label));
                this.isSignUp = true;
                if (isVisibleFragmentSignUp() != 0) {
                    EventBus.getDefault().post(new SocialDataEvent(this.smartUser, this.loginType));
                    return;
                }
                loadFragment(new SignUpFragment());
            }
            return;
        }
        i = new Intent(this, DashBoardActivity.class);
        i.setFlags(268468224);
        startActivity(i);
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isFragmentLoaded(FragmentLoadedEvent fragmentLoadedEvent) {
        if (fragmentLoadedEvent != null) {
            EventBus.getDefault().post(new SocialDataEvent(this.smartUser, this.loginType));
        }
    }

    private boolean isVisibleFragmentSignUp() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.user_login_fragment_container);
        return findFragmentById != null && findFragmentById.isVisible() && (findFragmentById instanceof SignUpFragment);
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.socialSignInPresenter != null) {
            this.socialSignInPresenter.unSubscribeDisposables();
        }
        if (this.propertySeedPresenter != null) {
            this.propertySeedPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("is_signup_fragment", this.isSignUp);
    }

    public void onBackPressed() {
        if (!getIntent().getBooleanExtra(StringConstants.CAME_FROM_FORGOT_PASSWORD, false)) {
            super.onBackPressed();
        } else if (this.doubleBackToExitPressedOnce) {
            super.onBackPressed();
        } else {
            this.doubleBackToExitPressedOnce = true;
            showToast(getString(R.string.please_click_back_label));
            new Handler().postDelayed(new HomeActivity$2(this), 2000);
        }
    }

    private GoogleSignInClient getGoogleSignInOptions() {
        return GoogleSignIn.getClient((Activity) this, new Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestId().requestEmail().requestProfile().build());
    }
}
