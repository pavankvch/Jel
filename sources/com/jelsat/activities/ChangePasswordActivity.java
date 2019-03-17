package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.changepassword.ChangePasswordPresenter;
import com.businesslogic.changepassword.IChangePasswordView;
import com.businesslogic.logout.ILogoutView;
import com.businesslogic.logout.LogoutPresenter;
import com.businesslogic.propertyseed.IPropertySeedView;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.changepassword.ChangePasswordRequest;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.login.LoginManager;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.firebase.DeleteTokenService;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.utils.Utils;
import org.bouncycastle.crypto.tls.CipherSuite;

public class ChangePasswordActivity extends BaseAppCompactActivity implements IChangePasswordView, ILogoutView, IPropertySeedView {
    ChangePasswordPresenter changePasswordPresenter = new ChangePasswordPresenter(this, RetrofitClient.getAPIService());
    private DialogUtil dialogUtil;
    @BindView(2131362353)
    public EditText etNewPassword;
    @BindView(2131362380)
    public EditText etOldPassword;
    private LogoutPresenter logoutPresenter = new LogoutPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    @BindView(2131362354)
    public TextInputLayout newPasswordTextInputLayout;
    @BindView(2131362381)
    public TextInputLayout oldPasswordTextInputLayout;
    private PropertySeedPresenter propertySeedPresenter = new PropertySeedPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362674)
    public TextView tvNewPassword;
    @BindView(2131362675)
    public TextView tvOldPassword;

    public int getActivityLayout() {
        return R.layout.activity_update_password;
    }

    @OnClick({2131362675})
    public void clickOnToggleText1(View view) {
        if (this.tvOldPassword.getText().toString().equalsIgnoreCase(getString(R.string.show_label)) != null) {
            this.tvOldPassword.setText(getString(R.string.hide));
            this.etOldPassword.setInputType(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA);
        } else {
            this.tvOldPassword.setText(getString(R.string.show_label));
            this.etOldPassword.setInputType(129);
        }
        this.etOldPassword.setSelection(this.etOldPassword.getText().toString().length());
    }

    @OnClick({2131362674})
    public void clickOnToggleText2(View view) {
        if (this.tvNewPassword.getText().toString().equalsIgnoreCase(getString(R.string.show_label)) != null) {
            this.tvNewPassword.setText(getString(R.string.hide));
            this.etNewPassword.setInputType(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA);
        } else {
            this.tvNewPassword.setText(getString(R.string.show_label));
            this.etNewPassword.setInputType(129);
        }
        this.etNewPassword.setSelection(this.etNewPassword.getText().toString().length());
    }

    @OnClick({2131361944})
    public void updatePassword() {
        changePassword();
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.oldPasswordTextInputLayout.setHintEnabled(false);
        this.newPasswordTextInputLayout.setHintEnabled(false);
        this.dialogUtil = new DialogUtil(this);
        this.etOldPassword.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 5) {
                    return null;
                }
                ChangePasswordActivity.this.etOldPassword.clearFocus();
                ChangePasswordActivity.this.etNewPassword.requestFocus();
                return true;
            }
        });
        this.etNewPassword.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return null;
                }
                ChangePasswordActivity.this.etNewPassword.clearFocus();
                ChangePasswordActivity.this.changePassword();
                return true;
            }
        });
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onSuccess() {
        if (isNetworkConnected()) {
            showProgressDialog(getString(R.string.please_wait));
            this.logoutPresenter.logoutFromApp(getString(R.string.please_wait));
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    private void changePassword() {
        if (fieldValidation()) {
            if (!NetworkUtil.isConnectedToNetwork(this)) {
                if (!NetworkUtil.isNetworkConnectedThroughWifi(this)) {
                    this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
                    return;
                }
            }
            String trim = this.etOldPassword.getText().toString().trim();
            String trim2 = this.etNewPassword.getText().toString().trim();
            ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
            changePasswordRequest.setCurentPassword(trim);
            changePasswordRequest.setNewPassword(trim2);
            this.changePasswordPresenter.changePassword(getString(R.string.please_wait), changePasswordRequest);
        }
    }

    private boolean fieldValidation() {
        boolean z;
        this.oldPasswordTextInputLayout.setErrorEnabled(false);
        this.newPasswordTextInputLayout.setErrorEnabled(false);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388629;
        layoutParams.setMargins(0, 0, 0, 0);
        this.tvOldPassword.setLayoutParams(layoutParams);
        hideKeyboard();
        String trim = this.etOldPassword.getText().toString().trim();
        String trim2 = this.etNewPassword.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.oldPasswordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 12));
            this.tvOldPassword.setLayoutParams(layoutParams);
        } else {
            if (trim.length() >= 8) {
                if (trim.length() <= 15) {
                    z = true;
                    if (TextUtils.isEmpty(trim2)) {
                        if (trim2.length() < 8 || trim2.length() > 15) {
                            this.newPasswordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
                            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 14));
                            this.tvNewPassword.setLayoutParams(layoutParams);
                        }
                        if (trim.equals(trim2)) {
                            return z;
                        }
                        showToast(getString(R.string.change_password_both_validation_label));
                        return false;
                    }
                    this.newPasswordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                    layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 12));
                    this.tvNewPassword.setLayoutParams(layoutParams);
                    z = false;
                    if (trim.equals(trim2)) {
                        return z;
                    }
                    showToast(getString(R.string.change_password_both_validation_label));
                    return false;
                }
            }
            this.oldPasswordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 14));
            this.tvOldPassword.setLayoutParams(layoutParams);
        }
        z = false;
        if (TextUtils.isEmpty(trim2)) {
            this.newPasswordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 14));
            this.tvNewPassword.setLayoutParams(layoutParams);
        } else {
            this.newPasswordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 12));
            this.tvNewPassword.setLayoutParams(layoutParams);
        }
        z = false;
        if (trim.equals(trim2)) {
            return z;
        }
        showToast(getString(R.string.change_password_both_validation_label));
        return false;
    }

    public void onDetachedFromWindow() {
        if (this.logoutPresenter != null) {
            this.logoutPresenter.unSubscribeDisposables();
        }
        if (this.propertySeedPresenter != null) {
            this.propertySeedPresenter.unSubscribeDisposables();
        }
        if (this.changePasswordPresenter != null) {
            this.changePasswordPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
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
        this.propertySeedPresenter.getPropertySeed(getString(R.string.hang_on_a_moment), false);
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
