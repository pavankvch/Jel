package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.createpassword.CreatePasswordPresenter;
import com.businesslogic.createpassword.ICreatePasswordView;
import com.data.cretepassword.CreatePasswordRequest;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomEditText;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.utils.Utils;
import org.bouncycastle.crypto.tls.CipherSuite;

public class CreatePasswordActivity extends BaseAppCompactActivity implements ICreatePasswordView {
    private CreatePasswordPresenter createPasswordPresenter = new CreatePasswordPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362044)
    public TextInputLayout createPasswordWrapper;
    private DialogUtil dialogUtil;
    private String emailOrPhoneNumber;
    @BindView(2131362400)
    public CustomEditText passwordEditText;
    @BindView(2131362673)
    public TextView toggleText;

    public int getActivityLayout() {
        return R.layout.activity_create_password;
    }

    @OnClick({2131361892})
    public void clickOnBackArrow(View view) {
        finish();
    }

    @OnClick({2131362673})
    public void clickOnToggleText(View view) {
        if (this.toggleText.getText().toString().equalsIgnoreCase(getString(R.string.show_label)) != null) {
            this.toggleText.setText(getString(R.string.hide));
            this.passwordEditText.setInputType(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA);
        } else {
            this.toggleText.setText(getString(R.string.show_label));
            this.passwordEditText.setInputType(129);
        }
        this.passwordEditText.setSelection(this.passwordEditText.getText().toString().length());
    }

    @OnClick({2131362533})
    public void clickOnSave() {
        hideKeyboard();
        if (fieldValidation()) {
            doUpdatePassword();
        }
    }

    private void doUpdatePassword() {
        if (!NetworkUtil.isConnectedToNetwork(this)) {
            if (!NetworkUtil.isNetworkConnectedThroughWifi(this)) {
                this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
                return;
            }
        }
        CreatePasswordRequest createPasswordRequest = new CreatePasswordRequest();
        if (Utils.isNumeric(this.emailOrPhoneNumber)) {
            createPasswordRequest.setPhoneNumber(this.emailOrPhoneNumber);
            createPasswordRequest.setEmail("");
        } else {
            createPasswordRequest.setPhoneNumber("");
            createPasswordRequest.setEmail(this.emailOrPhoneNumber);
        }
        createPasswordRequest.setPassword(this.passwordEditText.getText().toString());
        this.createPasswordPresenter.doUpdatePassword(getString(R.string.updating_password), createPasswordRequest);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.createPasswordWrapper.setHintEnabled(false);
        this.emailOrPhoneNumber = getIntent().getStringExtra(StringConstants.EMAIL_OR_MOBILE_NUMBER);
        this.dialogUtil = new DialogUtil(this);
    }

    private boolean fieldValidation() {
        this.createPasswordWrapper.setErrorEnabled(false);
        String trim = this.passwordEditText.getText().toString().trim();
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388629;
        layoutParams.setMargins(0, 0, 0, 0);
        this.toggleText.setLayoutParams(layoutParams);
        if (TextUtils.isEmpty(trim)) {
            this.createPasswordWrapper.setError(getString(R.string.field_empty_msg));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 12));
            this.toggleText.setLayoutParams(layoutParams);
            return false;
        }
        if (trim.length() >= 8) {
            if (trim.length() <= 15) {
                return true;
            }
        }
        this.createPasswordWrapper.setError(getString(R.string.password_validation_msg));
        layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(this, 14));
        this.toggleText.setLayoutParams(layoutParams);
        return false;
    }

    public void errorResponse(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void successResponse() {
        showToast(getString(R.string.passwordCreate));
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(StringConstants.CAME_FROM_FORGOT_PASSWORD, true);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    public void onDetachedFromWindow() {
        if (this.createPasswordPresenter != null) {
            this.createPasswordPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
