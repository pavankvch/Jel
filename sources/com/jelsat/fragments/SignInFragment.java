package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.propertyseed.IPropertySeedView;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.businesslogic.signin.ISignInView;
import com.businesslogic.signin.SignInPresenter;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.retrofit.RetrofitClient;
import com.data.signin.SignInRequest;
import com.data.signin.SignInResponse;
import com.data.signup.SignUpRequest;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.activities.CountryPickerActivity;
import com.jelsat.activities.DashBoardActivity;
import com.jelsat.activities.ForgotPasswordActivity;
import com.jelsat.activities.OTPActivity;
import com.jelsat.activities.SplashScreenActivity;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.country.Country;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.Utils;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.greenrobot.eventbus.EventBus;

public class SignInFragment extends BaseFragment implements IPropertySeedView, ISignInView {
    private static String TAG = SignUpFragment.class.getSimpleName();
    private String countryCode;
    private int countryFlag;
    private DialogUtil dialogUtil;
    @BindView(2131362126)
    TextView emailMobileError;
    @BindView(2131362124)
    View emailOrPhoneLineView;
    @BindView(2131362127)
    public EditText emailOrPhoneNumberEditText;
    private boolean isEmailMobileNumberErrorFieldEnabled;
    @BindView(2131362179)
    public ImageView mCountryFlagImageView;
    private String password;
    @BindView(2131362400)
    public EditText passwordEditText;
    @BindView(2131362402)
    public TextInputLayout passwordTextInputLayout;
    private PropertySeedPresenter propertySeedPresenter = new PropertySeedPresenter(this, RetrofitClient.getAPIService());
    private SignInPresenter signInPresenter = new SignInPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    @BindView(2131362673)
    public TextView toggleText;

    public int getFragmentLayoutId() {
        return R.layout.fragment_signin;
    }

    @OnClick({2131362179})
    public void clickOnFlagImageView() {
        startActivityForResult(new Intent(getActivity(), CountryPickerActivity.class), 101);
    }

    @OnClick({2131362673})
    public void clickOnToggleText() {
        if (this.toggleText.getText().toString().equalsIgnoreCase(getString(R.string.home_show_label))) {
            this.toggleText.setText(getString(R.string.home_hide_label));
            this.passwordEditText.setInputType(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA);
        } else {
            this.toggleText.setText(getString(R.string.home_show_label));
            this.passwordEditText.setInputType(129);
        }
        this.passwordEditText.setSelection(this.passwordEditText.getText().toString().length());
    }

    @OnClick({2131362593})
    public void clickOnSignInButton() {
        doSignIn();
    }

    @OnClick({2131362184})
    public void clickOnForgotPassword() {
        goToActivity(ForgotPasswordActivity.class);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.passwordTextInputLayout.setHintEnabled(null);
        viewGroup = Country.getCountry(getActivity());
        if (viewGroup != null) {
            this.countryFlag = viewGroup.getFlag();
            this.countryCode = viewGroup.getDialCode();
        }
        this.dialogUtil = new DialogUtil(getActivity());
        this.emailOrPhoneNumberEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().trim().length() == 0) {
                    i = Country.getCountry(SignInFragment.this.getActivity());
                    if (i != 0) {
                        SignInFragment.this.countryFlag = i.getFlag();
                        SignInFragment.this.countryCode = i.getDialCode();
                    }
                }
                if (Utils.isNumeric(charSequence.toString()) != null) {
                    SignInFragment.this.mCountryFlagImageView.setClickable(1);
                    SignInFragment.this.mCountryFlagImageView.setImageResource(SignInFragment.this.countryFlag);
                    return;
                }
                SignInFragment.this.mCountryFlagImageView.setClickable(0);
                SignInFragment.this.mCountryFlagImageView.setImageResource(R.drawable.ic_email_phonenumber);
            }
        });
        this.emailOrPhoneNumberEditText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 5) {
                    return null;
                }
                SignInFragment.this.emailOrPhoneNumberEditText.clearFocus();
                SignInFragment.this.passwordEditText.requestFocus();
                return true;
            }
        });
        this.passwordEditText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return null;
                }
                SignInFragment.this.passwordEditText.clearFocus();
                SignInFragment.this.doSignIn();
                return true;
            }
        });
        this.emailOrPhoneNumberEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (SignInFragment.this.emailOrPhoneLineView != null) {
                    if (z) {
                        if (SignInFragment.this.isEmailMobileNumberErrorFieldEnabled) {
                            SignInFragment.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(SignInFragment.this.getActivity(), R.color.edit_text_error_text_color));
                        } else {
                            SignInFragment.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(SignInFragment.this.getActivity(), R.color.editext_line_color));
                        }
                    } else if (SignInFragment.this.isEmailMobileNumberErrorFieldEnabled) {
                        SignInFragment.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(SignInFragment.this.getActivity(), R.color.edit_text_error_background_line_color));
                    } else {
                        SignInFragment.this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(SignInFragment.this.getActivity(), R.color.editext_line_color));
                    }
                }
            }
        });
        return layoutInflater;
    }

    private void doSignIn() {
        Utils.hideKeyboard(getActivity());
        if (!fieldValidation()) {
            return;
        }
        if (isNetworkConnected()) {
            StringBuilder stringBuilder;
            String str = "";
            String obj = this.emailOrPhoneNumberEditText.getText().toString();
            if (Utils.isNumeric(obj)) {
                str = this.countryCode.substring(1);
                if (obj.substring(0, 1).equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                    obj = obj.substring(1);
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(obj);
                obj = stringBuilder.toString();
            }
            String str2 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder("countrycode------>");
            stringBuilder2.append(str);
            Log.d(str2, stringBuilder2.toString());
            str = TAG;
            stringBuilder = new StringBuilder("emailorPhone------>");
            stringBuilder.append(obj);
            Log.d(str, stringBuilder.toString());
            this.password = this.passwordEditText.getText().toString().trim();
            SignInRequest signInRequest = new SignInRequest();
            signInRequest.setUsername(obj);
            signInRequest.setPassword(this.password);
            this.signInPresenter.userSignIn(getString(R.string.please_wait), signInRequest);
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    private boolean fieldValidation() {
        boolean z = true;
        String substring = this.countryCode.substring(1);
        this.isEmailMobileNumberErrorFieldEnabled = false;
        this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(getActivity(), R.color.editext_line_color));
        this.passwordTextInputLayout.setErrorEnabled(false);
        this.emailOrPhoneNumberEditText.setError(null);
        this.emailMobileError.setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388629;
        layoutParams.setMargins(0, 0, 0, 0);
        this.toggleText.setLayoutParams(layoutParams);
        String trim = this.emailOrPhoneNumberEditText.getText().toString().trim();
        String trim2 = this.passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.isEmailMobileNumberErrorFieldEnabled = true;
            this.isEmailMobileNumberErrorFieldEnabled = true;
            this.emailMobileError.setText(getString(R.string.home_field_not_empty_label));
            this.emailMobileError.setAlpha(1.0f);
            this.emailMobileError.setVisibility(0);
            this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(getActivity(), R.color.edit_text_error_background_line_color));
        } else if (!Utils.isNumeric(trim)) {
            if (!Utils.validateEmail(trim)) {
                this.isEmailMobileNumberErrorFieldEnabled = true;
                this.emailMobileError.setText(getString(R.string.home_email_validation_msg));
                this.emailMobileError.setAlpha(1.0f);
                this.emailMobileError.setVisibility(0);
                this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(getActivity(), R.color.edit_text_error_background_line_color));
            }
            if (TextUtils.isEmpty(trim2)) {
                if (trim2.length() >= 8) {
                    if (trim2.length() > 15) {
                        return z;
                    }
                }
                this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
                layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(getActivity(), 14));
                this.toggleText.setLayoutParams(layoutParams);
                return false;
            }
            this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(getActivity(), 12));
            this.toggleText.setLayoutParams(layoutParams);
            return false;
        } else if (this.countryCode != null && this.countryCode.length() == 0 && trim.length() == 0) {
            this.isEmailMobileNumberErrorFieldEnabled = true;
            this.emailMobileError.setText(getString(R.string.country_code_phone_validation_label));
            this.emailMobileError.setAlpha(1.0f);
            this.emailMobileError.setVisibility(0);
            this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(getActivity(), R.color.edit_text_error_background_line_color));
        } else {
            if (!Utils.isValidPhoneNumber(requireActivity().getApplicationContext(), substring, trim, true)) {
                this.isEmailMobileNumberErrorFieldEnabled = true;
                this.emailMobileError.setText(getString(R.string.home_not_valid_mobile_number_label));
                this.emailMobileError.setAlpha(1.0f);
                this.emailMobileError.setVisibility(0);
                this.emailOrPhoneLineView.setBackgroundColor(Utils.applyColor(getActivity(), R.color.edit_text_error_background_line_color));
            }
            if (TextUtils.isEmpty(trim2)) {
                this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(getActivity(), 12));
                this.toggleText.setLayoutParams(layoutParams);
                return false;
            }
            if (trim2.length() >= 8) {
                if (trim2.length() > 15) {
                    return z;
                }
            }
            this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(getActivity(), 14));
            this.toggleText.setLayoutParams(layoutParams);
            return false;
        }
        z = false;
        if (TextUtils.isEmpty(trim2)) {
            this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(getActivity(), 12));
            this.toggleText.setLayoutParams(layoutParams);
            return false;
        }
        if (trim2.length() >= 8) {
            if (trim2.length() > 15) {
                return z;
            }
        }
        this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
        layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(getActivity(), 14));
        this.toggleText.setLayoutParams(layoutParams);
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        dismissProgress();
        if (i2 == 101) {
            i = intent.getExtras();
            if (i != 0) {
                this.countryCode = i.getString(StringConstants.COUNTRY_DIALCODE);
                this.countryFlag = i.getInt(StringConstants.COUNTRY_FLAG);
                this.mCountryFlagImageView.setImageResource(i.getInt(StringConstants.COUNTRY_FLAG));
                i = TAG;
                i2 = new StringBuilder("countrycode------>");
                i2.append(this.countryCode);
                Log.d(i, i2.toString());
            }
        }
    }

    public void goToNextScreen(SignInResponse signInResponse, PrefUtils prefUtils) {
        if (signInResponse.getUser().getOtpValidated().equalsIgnoreCase("No")) {
            prefUtils = signInResponse.getUser().getPhoneNumber().substring(signInResponse.getUser().getCountryCode().length());
            StringBuilder stringBuilder = new StringBuilder("+");
            stringBuilder.append(signInResponse.getUser().getCountryCode());
            Country countryByDialCode = Country.getCountryByDialCode(stringBuilder.toString());
            SignUpRequest signUpRequest = new SignUpRequest();
            signUpRequest.setUserId(signInResponse.getUser().getUid());
            signUpRequest.setFieldPhoneNumber(prefUtils);
            signUpRequest.setMail(signInResponse.getUser().getMail());
            signUpRequest.setPass(this.password);
            prefUtils = new StringBuilder("+");
            prefUtils.append(signInResponse.getUser().getCountryCode());
            signUpRequest.setCountryCode(prefUtils.toString());
            if (countryByDialCode != null) {
                signUpRequest.setCountryFlag(countryByDialCode.getFlag());
            }
            EventBus.getDefault().postSticky(signUpRequest);
            goToActivity(OTPActivity.class);
            return;
        }
        if (signInResponse.getToken() != null) {
            prefUtils.saveUserToken(signInResponse.getToken());
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

    public void saveTokenSuccess(PrefUtils prefUtils) {
        prefUtils.setDeviceTokenUpdated(true);
        prefUtils = new Intent(getActivity(), DashBoardActivity.class);
        prefUtils.setFlags(268468224);
        startActivity(prefUtils);
        getActivity().finish();
    }

    public void onSuccess(SeedResponse seedResponse) {
        if (seedResponse != null) {
            PrefUtils.getInstance().saveSeedResponse(seedResponse);
            PrefUtils.getInstance().saveUser(seedResponse.getUser());
            this.signInPresenter.sendFcmTokenToServer(getString(R.string.please_wait));
            return;
        }
        showToast(getString(R.string.general_api_error_msg));
    }

    public void onError(APIError aPIError, int i) {
        if (i == ErrorCodes.API_ERROR) {
            showToast(aPIError.getSeken_errors());
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
        if (i == 803) {
            i = new Intent(getActivity(), SplashScreenActivity.class);
            i.setFlags(268468224);
            startActivity(i);
            getActivity().finish();
        }
    }

    public void onDetach() {
        if (this.propertySeedPresenter != null) {
            this.propertySeedPresenter.unSubscribeDisposables();
        }
        if (this.signInPresenter != null) {
            this.signInPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
