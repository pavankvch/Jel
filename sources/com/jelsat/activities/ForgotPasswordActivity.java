package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.forgotpassword.ForgotPasswordPresenter;
import com.businesslogic.forgotpassword.IForgotPasswordView;
import com.data.generateotp.GenerateOTPRequest;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.country.Country;
import com.jelsat.utils.AlertDialogUtil;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.utils.Utils;

public class ForgotPasswordActivity extends BaseAppCompactActivity implements IForgotPasswordView {
    private AlertDialogUtil alertDialog;
    private String countryCode;
    private int countryFlag;
    private DialogUtil dialogUtil;
    @BindView(2131362126)
    TextView emailMobileError;
    @BindView(2131362124)
    View emailOrPhoneLineView;
    private String emailOrPhoneNumber;
    @BindView(2131362127)
    public EditText emailOrPhoneNumberEditText;
    private ForgotPasswordPresenter forgotPasswordPresenter = new ForgotPasswordPresenter(this, RetrofitClient.getAPIService());
    private boolean isEmailMobileNumberErrorFieldEnabled;
    @BindView(2131362179)
    public ImageView mCountryFlagImageView;

    public int getActivityLayout() {
        return R.layout.activity_forgot_password;
    }

    @OnClick({2131361892})
    public void clickOnBackArrow(View view) {
        finish();
    }

    @OnClick({2131362179})
    public void clickOnFlagImageView(View view) {
        startActivityForResult(new Intent(this, CountryPickerActivity.class), 101);
    }

    @OnClick({2131362582})
    public void clickOnSendOTPButton() {
        hideKeyboard();
        if (fieldValidation()) {
            doForgotPassword();
        }
    }

    private void doForgotPassword() {
        if (!NetworkUtil.isConnectedToNetwork(this)) {
            if (!NetworkUtil.isNetworkConnectedThroughWifi(this)) {
                this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
                return;
            }
        }
        this.emailOrPhoneNumber = this.emailOrPhoneNumberEditText.getText().toString();
        GenerateOTPRequest generateOTPRequest = new GenerateOTPRequest();
        if (Utils.isNumeric(this.emailOrPhoneNumber)) {
            String substring = this.countryCode.substring(1);
            if (this.emailOrPhoneNumber.substring(0, 1).equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.emailOrPhoneNumber = this.emailOrPhoneNumber.substring(1);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(substring);
            stringBuilder.append(this.emailOrPhoneNumber);
            this.emailOrPhoneNumber = stringBuilder.toString();
            generateOTPRequest.setPhoneNumber(this.emailOrPhoneNumber);
            generateOTPRequest.setEmail("");
        } else {
            generateOTPRequest.setEmail(this.emailOrPhoneNumber);
            generateOTPRequest.setPhoneNumber("");
        }
        this.forgotPasswordPresenter.doForgotPassword(getString(R.string.sending_otp), generateOTPRequest);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.dialogUtil = new DialogUtil(this);
        this.alertDialog = new AlertDialogUtil(this);
        bundle = Country.getCountry(this);
        if (bundle != null) {
            this.countryFlag = bundle.getFlag();
            this.countryCode = bundle.getDialCode();
        }
        this.emailOrPhoneNumberEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().trim().length() == 0) {
                    i = Country.getCountry(ForgotPasswordActivity.this);
                    if (i != 0) {
                        ForgotPasswordActivity.this.countryFlag = i.getFlag();
                        ForgotPasswordActivity.this.countryCode = i.getDialCode();
                    }
                }
                if (Utils.isNumeric(charSequence.toString()) != null) {
                    ForgotPasswordActivity.this.mCountryFlagImageView.setClickable(1);
                    ForgotPasswordActivity.this.mCountryFlagImageView.setImageResource(ForgotPasswordActivity.this.countryFlag);
                } else {
                    ForgotPasswordActivity.this.mCountryFlagImageView.setClickable(0);
                    ForgotPasswordActivity.this.mCountryFlagImageView.setImageResource(R.drawable.ic_email_phonenumber);
                }
                ForgotPasswordActivity.this.fieldValidation();
            }
        });
        this.emailOrPhoneNumberEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (ForgotPasswordActivity.this.emailOrPhoneLineView != null) {
                    if (z) {
                        if (ForgotPasswordActivity.this.isEmailMobileNumberErrorFieldEnabled) {
                            ForgotPasswordActivity.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(ForgotPasswordActivity.this, R.color.edit_text_error_text_color));
                        } else {
                            ForgotPasswordActivity.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(ForgotPasswordActivity.this, R.color.editext_line_color));
                        }
                    } else if (ForgotPasswordActivity.this.isEmailMobileNumberErrorFieldEnabled) {
                        ForgotPasswordActivity.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(ForgotPasswordActivity.this, R.color.edit_text_error_background_line_color));
                    } else {
                        ForgotPasswordActivity.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(ForgotPasswordActivity.this, R.color.editext_line_color));
                    }
                }
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 101) {
            i = intent.getExtras();
            if (i != 0) {
                this.countryCode = i.getString(StringConstants.COUNTRY_DIALCODE);
                this.countryFlag = i.getInt(StringConstants.COUNTRY_FLAG);
                this.mCountryFlagImageView.setImageResource(i.getInt(StringConstants.COUNTRY_FLAG));
            }
        }
    }

    private boolean fieldValidation() {
        String substring = this.countryCode.substring(1);
        this.isEmailMobileNumberErrorFieldEnabled = false;
        this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(this, R.color.editext_line_color));
        this.emailMobileError.setVisibility(4);
        String trim = this.emailOrPhoneNumberEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.isEmailMobileNumberErrorFieldEnabled = true;
            this.emailMobileError.setText(getString(R.string.field_empty_msg));
            this.emailMobileError.setAlpha(1.0f);
            this.emailMobileError.setVisibility(0);
            this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(this, R.color.edit_text_error_background_line_color));
            return false;
        }
        if (Utils.isNumeric(trim)) {
            if (this.countryCode != null && this.countryCode.length() == 0 && trim.length() == 0) {
                this.isEmailMobileNumberErrorFieldEnabled = true;
                this.emailMobileError.setText(getString(R.string.country_code_phone_validation_label));
                this.emailMobileError.setAlpha(1.0f);
                this.emailMobileError.setVisibility(0);
                this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(this, R.color.edit_text_error_background_line_color));
                return false;
            } else if (!Utils.isValidPhoneNumber(getApplicationContext(), substring, trim, false)) {
                this.isEmailMobileNumberErrorFieldEnabled = true;
                this.emailMobileError.setText(getString(R.string.home_not_valid_mobile_number_label));
                this.emailMobileError.setAlpha(1.0f);
                this.emailMobileError.setVisibility(0);
                this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(this, R.color.edit_text_error_background_line_color));
                return false;
            }
        } else if (!Utils.validateEmail(trim)) {
            this.isEmailMobileNumberErrorFieldEnabled = true;
            this.emailMobileError.setText(getString(R.string.home_email_validation_msg));
            this.emailMobileError.setAlpha(1.0f);
            this.emailMobileError.setVisibility(0);
            this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(this, R.color.edit_text_error_background_line_color));
            return false;
        }
        return true;
    }

    public void onDetachedFromWindow() {
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
        if (Utils.isNumeric(this.emailOrPhoneNumberEditText.getText().toString())) {
            verifyOTPScreen();
        } else {
            this.alertDialog.showAlertDialog(getString(R.string.pass_resend_success), getString(R.string.ok), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ForgotPasswordActivity.this.alertDialog.dismissDialog();
                    ForgotPasswordActivity.this.verifyOTPScreen();
                }
            });
        }
    }

    public void verifyOTPScreen() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.EMAIL_OR_MOBILE_NUMBER, this.emailOrPhoneNumber);
        goToActivity(VerifyOTPDetailsActivity.class, bundle);
    }
}
