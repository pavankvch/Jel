package com.jelsat.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.logout.ILogoutView;
import com.businesslogic.logout.LogoutPresenter;
import com.businesslogic.propertyseed.IPropertySeedView;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.login.LoginManager;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.compoundviews.ProfileCompoundView;
import com.jelsat.constants.StringConstants;
import com.jelsat.firebase.DeleteTokenService;
import com.jelsat.fragments.BecomeHostIntroDialogFragment;
import com.jelsat.helper.LocaleManager;
import com.jelsat.utils.DialogUtil;
import java.util.Locale;

public class SettingsActivity extends BaseAppCompactActivity implements ILogoutView, IPropertySeedView {
    @BindView(2131361984)
    public ProfileCompoundView changePasswordView;
    private DialogUtil dialogUtil;
    @BindView(2131362217)
    public ProfileCompoundView hostGuideView;
    @BindView(2131362272)
    public ProfileCompoundView languageView;
    private LogoutPresenter logoutPresenter = new LogoutPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    private PropertySeedPresenter propertySeedPresenter;
    @BindView(2131362733)
    TextView toolbarTitle;
    @BindView(2131362817)
    TextView versionNumber;

    public int getActivityLayout() {
        return R.layout.activity_settings;
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131361984})
    public void clickOnChangePassword() {
        goToActivity(ChangePasswordActivity.class);
    }

    @OnClick({2131361814})
    public void termsOfUse() {
        loadWebView(StringConstants.TERMS_URL, Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/content/terms-conditions" : "https://jelsat.com/en/content/terms-conditions");
    }

    @OnClick({2131362434})
    public void privacyPolicy() {
        loadWebView(StringConstants.TERMS_URL, Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/content/privacy-policy" : "https://jelsat.com/en/content/privacy-policy");
    }

    @OnClick({2131361823})
    public void aboutUs() {
        loadWebView(StringConstants.TERMS_URL, Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/content/about-us" : "https://jelsat.com/en/content/about-us");
    }

    @OnClick({2131362217})
    public void clickOnHostGuide() {
        new BecomeHostIntroDialogFragment().show(getSupportFragmentManager(), "hostdialogue");
    }

    @OnClick({2131362272})
    public void changeLanguage() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_for_capturing);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_arabic);
        ((TextView) dialog.findViewById(R.id.tv_english)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SettingsActivity.this.isNetworkConnected() != null) {
                    SettingsActivity.this.switchLanguage("en", LocaleManager.DEFAULT_COUNTRY);
                    dialog.dismiss();
                    return;
                }
                SettingsActivity.this.showToast(SettingsActivity.this.getString(R.string.error_message_network));
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SettingsActivity.this.isNetworkConnected() != null) {
                    SettingsActivity.this.switchLanguage("ar", "SA");
                    dialog.dismiss();
                    return;
                }
                SettingsActivity.this.showToast(SettingsActivity.this.getString(R.string.error_message_network));
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(-1, -2);
    }

    @OnClick({2131362311})
    public void clickOnlogout() {
        new Builder(this).setMessage(getString(R.string.are_you_sure_to_logout)).setIcon(17301543).setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                SettingsActivity.this.doLogout();
            }
        }).setNegativeButton(getString(R.string.cancellation_policy_txt), null).show();
    }

    private void doLogout() {
        if (isNetworkConnected()) {
            showProgressDialog(getString(R.string.please_wait));
            this.logoutPresenter.logoutFromApp(getString(R.string.please_wait));
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.toolbarTitle.setText(getString(R.string.settings_label));
        this.toolbarTitle.setAllCaps(false);
        this.dialogUtil = new DialogUtil(this);
        bundle = this.versionNumber;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.app_version));
        stringBuilder.append(" 1.2.3");
        bundle.setText(stringBuilder.toString());
        this.hostGuideView.setVisibility(getIntent().getBooleanExtra(StringConstants.HOST_PROFILE, false) ? 0 : 8);
        if (PrefUtils.getInstance().isSocialSignin() != null) {
            this.changePasswordView.setVisibility(8);
        } else {
            this.changePasswordView.setVisibility(0);
        }
        if (LocaleManager.getLanguage(this).equalsIgnoreCase("en") != null) {
            this.languageView.setlanguageText(getString(R.string.settings_language_english));
        } else {
            this.languageView.setlanguageText(getResources().getString(R.string.settings_language_arabic));
        }
    }

    public void errorOccurred(APIError aPIError) {
        dismissProgress();
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void logoutSuccess() {
        LoginManager.getInstance().logOut();
        PrefUtils.getInstance().clearSharedPreferences();
        startService(new Intent(this, DeleteTokenService.class));
        Log.e("sda", Locale.getDefault().getLanguage());
        this.propertySeedPresenter = new PropertySeedPresenter(this, RetrofitClient.changeApiBaseUrlBasedOnLanguage());
        this.propertySeedPresenter.getPropertySeed(getString(R.string.hang_on_a_moment), false);
    }

    public void onDetachedFromWindow() {
        if (this.logoutPresenter != null) {
            this.logoutPresenter.unSubscribeDisposables();
        }
        if (this.propertySeedPresenter != null) {
            this.propertySeedPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    private void loadWebView(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(str, str2);
        goToActivity(WebViewActivity.class, bundle);
    }

    public void switchLanguage(String str, String str2) {
        LocaleManager.setNewLocale(this, str, str2);
        RetrofitClient.changeApiBaseUrlBasedOnLanguage();
        relaunch();
    }

    public void relaunch() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.putExtra(StringConstants.FROM_SETTINGS_PAGE, true);
        intent.setFlags(268468224);
        startActivity(intent);
        Log.e("sda", Locale.getDefault().getLanguage());
        finish();
    }

    public void onSuccess(SeedResponse seedResponse) {
        dismissProgress();
        PrefUtils.getInstance().saveSeedResponse(seedResponse);
        seedResponse = new Intent(this, HomeActivity.class);
        seedResponse.setFlags(268468224);
        startActivity(seedResponse);
        finish();
    }

    public void onError(APIError aPIError, int i) {
        dismissProgress();
        finish();
        System.exit(null);
    }
}
