package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.forgotpassword.ForgotPasswordPresenter;
import com.businesslogic.forgotpassword.IForgotPasswordView;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.validateotp.IValidateOTPView;
import com.businesslogic.validateotp.ValidateOTPPresenter;
import com.data.generateotp.GenerateOTPRequest;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.validateotp.ValidateOTPRequest;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.Utils;

public class VerifyOTPDetailsActivity extends BaseAppCompactActivity implements IForgotPasswordView, IValidateOTPView {
    private DialogUtil dialogUtil;
    private String emailOrPhoneNumber;
    private ForgotPasswordPresenter forgotPasswordPresenter = new ForgotPasswordPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362335)
    TextView mobileNumberTV;
    @BindView(2131362391)
    public EditText otpEditText;
    private ValidateOTPPresenter validateOTPPresenter = new ValidateOTPPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362816)
    public LinearLayout verifyOTPButton;

    public int getActivityLayout() {
        return R.layout.activity_verify_otp_details;
    }

    @OnClick({2131361892})
    public void clickOnBackArrow(View view) {
        finish();
    }

    @OnClick({2131362816})
    public void clickOnVerifyOTP(View view) {
        view = this.otpEditText.getText().toString().trim();
        if (TextUtils.isEmpty(view) || view.length() != 4) {
            showToast(getString(R.string.enter_valid_otp));
        } else {
            doValidateOTP();
        }
    }

    @OnClick({2131362508})
    public void clickOnResend() {
        doForgotPassword();
    }

    public void doValidateOTP() {
        hideKeyboard();
        if (isNetworkConnected()) {
            ValidateOTPRequest validateOTPRequest = new ValidateOTPRequest();
            if (Utils.isNumeric(this.emailOrPhoneNumber)) {
                validateOTPRequest.setPhoneNumber(this.emailOrPhoneNumber);
                validateOTPRequest.setEmail("");
            } else {
                validateOTPRequest.setPhoneNumber("");
                validateOTPRequest.setEmail(this.emailOrPhoneNumber);
            }
            validateOTPRequest.setOtp(this.otpEditText.getText().toString().trim());
            this.validateOTPPresenter.validateOTP(getString(R.string.verifying_otp), validateOTPRequest, true);
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    private void doForgotPassword() {
        if (isNetworkConnected()) {
            GenerateOTPRequest generateOTPRequest = new GenerateOTPRequest();
            if (Utils.isNumeric(this.emailOrPhoneNumber)) {
                generateOTPRequest.setPhoneNumber(this.emailOrPhoneNumber);
                generateOTPRequest.setEmail("");
            } else {
                generateOTPRequest.setEmail(this.emailOrPhoneNumber);
                generateOTPRequest.setPhoneNumber("");
            }
            this.forgotPasswordPresenter.doForgotPassword(getString(R.string.sending_otp), generateOTPRequest);
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.emailOrPhoneNumber = getIntent().getStringExtra(StringConstants.EMAIL_OR_MOBILE_NUMBER);
        bundle = this.mobileNumberTV;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.otp_sent_to_mobile_number_label));
        stringBuilder.append(" %s");
        bundle.setText(String.format(stringBuilder.toString(), new Object[]{this.emailOrPhoneNumber}));
        this.dialogUtil = new DialogUtil(this);
        if (this.otpEditText != null) {
            this.otpEditText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence.toString().trim().length() == 4) {
                        VerifyOTPDetailsActivity.this.verifyOTPButton.setFocusable(true);
                        VerifyOTPDetailsActivity.this.verifyOTPButton.setClickable(true);
                        VerifyOTPDetailsActivity.this.verifyOTPButton.setEnabled(true);
                        return;
                    }
                    VerifyOTPDetailsActivity.this.verifyOTPButton.setFocusable(false);
                    VerifyOTPDetailsActivity.this.verifyOTPButton.setClickable(false);
                    VerifyOTPDetailsActivity.this.verifyOTPButton.setEnabled(false);
                }
            });
            this.otpEditText.setOnEditorActionListener(new OnEditorActionListener() {
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i != 6) {
                        return null;
                    }
                    VerifyOTPDetailsActivity.this.otpEditText.clearFocus();
                    VerifyOTPDetailsActivity.this.doValidateOTP();
                    return true;
                }
            });
        }
    }

    public void validationOTPSuccessResponse() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.EMAIL_OR_MOBILE_NUMBER, this.emailOrPhoneNumber);
        goToActivity(CreatePasswordActivity.class, bundle);
    }

    public void onError(APIError aPIError, int i) {
        if (i == ErrorCodes.API_ERROR) {
            showToast(aPIError.getSeken_errors());
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

    public void onDetachedFromWindow() {
        if (this.validateOTPPresenter != null) {
            this.validateOTPPresenter.unSubscribeDisposables();
        }
        if (this.forgotPasswordPresenter != null) {
            this.forgotPasswordPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    public void forgotPasswordError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void responseSuccess() {
        showToast(getString(R.string.pass_resend_success));
    }
}
