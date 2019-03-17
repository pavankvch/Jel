package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.signup.ISignUpView;
import com.businesslogic.signup.SignUpPresenter;
import com.businesslogic.socialsignup.ISocialSignUpView;
import com.businesslogic.socialsignup.SocialSignUpPresenter;
import com.data.retrofit.RetrofitClient;
import com.data.signup.SignUpRequest;
import com.data.signup.SignUpResponse;
import com.data.socialsignup.SocialSignUpRequest;
import com.data.socialsignup.SocialSignUpResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.activities.CountryPickerActivity;
import com.jelsat.activities.OTPActivity;
import com.jelsat.activities.SplashScreenActivity;
import com.jelsat.activities.WebViewActivity;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.country.Country;
import com.jelsat.customclasses.CustomEditText;
import com.jelsat.events.FragmentLoadedEvent;
import com.jelsat.events.SocialDataEvent;
import com.jelsat.sociallogin.user.SmartUser;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.Utils;
import java.util.Locale;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SignUpFragment extends BaseFragment implements ISignUpView, ISocialSignUpView {
    private static final String TAG = "SignUpFragment";
    private String countryCode;
    private int countryFlag;
    private DialogUtil dialogUtil;
    @BindView(2131362125)
    public CustomEditText emailEditText;
    @BindView(2131362128)
    public TextInputLayout emailTextInputLayout;
    @BindView(2131362175)
    public CustomEditText firstNameEditText;
    @BindView(2131362176)
    public TextInputLayout firstnameTextInputLayout;
    private boolean isMobileNumberErrorFieldEnabled;
    private int loginType;
    @BindView(2131362179)
    public ImageView mCountryFlagImageView;
    @BindView(2131362333)
    public EditText mobileEditText;
    @BindView(2131362334)
    public View mobileLineView;
    @BindView(2131362337)
    TextView mobileNumberError;
    @BindView(2131362400)
    public CustomEditText passwordEditText;
    @BindView(2131362401)
    public FrameLayout passwordLayout;
    @BindView(2131362402)
    public TextInputLayout passwordTextInputLayout;
    @BindView(2131362501)
    public CustomEditText referralCodeEditText;
    @BindView(2131362502)
    public TextInputLayout referralTextInputLayout;
    private SignUpPresenter signUpPresenter = new SignUpPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    private SmartUser smartUser;
    private SocialSignUpPresenter socialSignUpPresenter = new SocialSignUpPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    @BindView(2131362673)
    public TextView toggleText;

    public int getFragmentLayoutId() {
        return R.layout.fragment_signup;
    }

    @OnClick({2131362649})
    public void termsText() {
        String str = Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/content/terms-conditions" : "https://jelsat.com/en/content/terms-conditions";
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.TERMS_URL, str);
        goToActivity(WebViewActivity.class, bundle);
    }

    @OnClick({2131362434})
    public void privacyPolicy() {
        String str = Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/content/privacy-policy" : "https://jelsat.com/en/content/privacy-policy";
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.TERMS_URL, str);
        goToActivity(WebViewActivity.class, bundle);
    }

    @OnClick({2131362179})
    public void clickOnFlagImageView() {
        startActivityForResult(new Intent(requireActivity(), CountryPickerActivity.class), 101);
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

    @OnClick({2131362592})
    public void clickOnSignUpButton() {
        Utils.hideKeyboard(requireActivity());
        if (!fieldValidation()) {
            return;
        }
        if (isNetworkConnected()) {
            String trim = this.firstNameEditText.getText().toString().trim();
            String trim2 = this.emailEditText.getText().toString().trim();
            String trim3 = this.mobileEditText.getText().toString().trim();
            String trim4 = this.passwordEditText.getText().toString().trim();
            String trim5 = this.referralCodeEditText.getText().toString().trim();
            String substring = this.countryCode.substring(1);
            if (trim3.substring(0, 1).equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                trim3 = trim3.substring(1);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(substring);
            stringBuilder.append(trim3);
            trim3 = stringBuilder.toString();
            String str = TAG;
            StringBuilder stringBuilder2 = new StringBuilder("countrycode------>");
            stringBuilder2.append(substring);
            Log.d(str, stringBuilder2.toString());
            str = TAG;
            stringBuilder2 = new StringBuilder("emailorPhone------>");
            stringBuilder2.append(trim3);
            Log.d(str, stringBuilder2.toString());
            if (this.emailEditText.isEnabled()) {
                SignUpRequest signUpRequest = new SignUpRequest();
                signUpRequest.setFieldFullName(trim);
                signUpRequest.setMail(trim2);
                signUpRequest.setFieldPhoneNumber(trim3);
                signUpRequest.setPass(trim4);
                signUpRequest.setFieldReferralCode(trim5);
                signUpRequest.setFieldDeviceType("android");
                signUpRequest.setCountryCode(substring);
                this.signUpPresenter.userSignUp(getString(R.string.please_wait), signUpRequest);
                return;
            }
            SocialSignUpRequest socialSignUpRequest = new SocialSignUpRequest();
            socialSignUpRequest.setIdentifier(this.smartUser.getUserId());
            socialSignUpRequest.setProvider(this.loginType == 1 ? "Facebook" : "Google");
            socialSignUpRequest.setMail(trim2);
            socialSignUpRequest.setFullName(trim);
            socialSignUpRequest.setPhoneNumber(trim3);
            socialSignUpRequest.setDeviceType("android");
            socialSignUpRequest.setReferralCode(trim5);
            socialSignUpRequest.setProfileURL(this.smartUser.getPhotoUrl());
            socialSignUpRequest.setCountryCode(substring);
            this.socialSignUpPresenter.doSocialSignUp(getString(R.string.please_wait), socialSignUpRequest);
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.firstnameTextInputLayout.setHintEnabled(false);
        this.emailTextInputLayout.setHintEnabled(false);
        this.passwordTextInputLayout.setHintEnabled(false);
        this.referralTextInputLayout.setHintEnabled(false);
        this.dialogUtil = new DialogUtil(requireActivity());
        viewGroup = Country.getCountry(requireActivity());
        if (viewGroup != null) {
            this.countryFlag = viewGroup.getFlag();
            this.countryCode = viewGroup.getDialCode();
            this.mCountryFlagImageView.setImageResource(this.countryFlag);
        }
        this.mobileEditText.setOnFocusChangeListener(new SignUpFragment$1(this));
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new FragmentLoadedEvent(true));
    }

    private boolean fieldValidation() {
        boolean z;
        String substring = this.countryCode.substring(1);
        this.isMobileNumberErrorFieldEnabled = false;
        this.mobileLineView.setBackgroundColor(Utils.applyColor(requireActivity(), R.color.editext_line_color));
        this.firstnameTextInputLayout.setErrorEnabled(false);
        this.emailTextInputLayout.setErrorEnabled(false);
        this.passwordTextInputLayout.setErrorEnabled(false);
        this.mobileEditText.setError(null);
        this.mobileNumberError.setVisibility(4);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388629;
        layoutParams.setMargins(0, 0, 0, 0);
        this.toggleText.setLayoutParams(layoutParams);
        String trim = this.firstNameEditText.getText().toString().trim();
        Object trim2 = this.emailEditText.getText().toString().trim();
        String trim3 = this.mobileEditText.getText().toString().trim();
        String trim4 = this.passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.firstnameTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
        } else {
            if (trim.length() >= 3) {
                if (trim.length() <= 50) {
                    if ("!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".contains(trim.substring(0, 1))) {
                        this.firstnameTextInputLayout.setError(getString(R.string.first_letter_should_be_char));
                    } else {
                        z = true;
                        if (TextUtils.isEmpty(trim2)) {
                            if (!Utils.validateEmail(trim2)) {
                                this.emailTextInputLayout.setError(getString(R.string.home_email_validation_msg));
                            }
                            if (this.countryCode == null && this.countryCode.length() == 0 && trim3.length() == 0) {
                                this.isMobileNumberErrorFieldEnabled = true;
                                this.mobileNumberError.setText(getString(R.string.country_code_phone_validation_label));
                                this.mobileNumberError.setAlpha(1.0f);
                                this.mobileNumberError.setVisibility(0);
                                this.mobileLineView.setBackgroundColor(Utils.applyColor(requireActivity(), R.color.edit_text_error_background_line_color));
                            } else {
                                if (!Utils.isValidPhoneNumber(requireActivity().getApplicationContext(), substring, trim3, true)) {
                                    this.isMobileNumberErrorFieldEnabled = true;
                                    this.mobileNumberError.setText(getString(R.string.home_not_valid_mobile_number_label));
                                    this.mobileNumberError.setAlpha(1.0f);
                                    this.mobileNumberError.setVisibility(0);
                                    this.mobileLineView.setBackgroundColor(Utils.applyColor(requireActivity(), R.color.edit_text_error_background_line_color));
                                }
                                if (this.emailEditText.isEnabled()) {
                                    if (TextUtils.isEmpty(trim4)) {
                                        this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                                        layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 12));
                                        this.toggleText.setLayoutParams(layoutParams);
                                        return false;
                                    } else if (trim4.length() < 8 || trim4.length() > 15) {
                                        this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
                                        layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 14));
                                        this.toggleText.setLayoutParams(layoutParams);
                                        return false;
                                    }
                                }
                                return z;
                            }
                            z = false;
                            if (this.emailEditText.isEnabled()) {
                                if (TextUtils.isEmpty(trim4)) {
                                    this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
                                    layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 14));
                                    this.toggleText.setLayoutParams(layoutParams);
                                    return false;
                                }
                                this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                                layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 12));
                                this.toggleText.setLayoutParams(layoutParams);
                                return false;
                            }
                            return z;
                        }
                        this.emailTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                        z = false;
                        if (this.countryCode == null) {
                        }
                        if (Utils.isValidPhoneNumber(requireActivity().getApplicationContext(), substring, trim3, true)) {
                            this.isMobileNumberErrorFieldEnabled = true;
                            this.mobileNumberError.setText(getString(R.string.home_not_valid_mobile_number_label));
                            this.mobileNumberError.setAlpha(1.0f);
                            this.mobileNumberError.setVisibility(0);
                            this.mobileLineView.setBackgroundColor(Utils.applyColor(requireActivity(), R.color.edit_text_error_background_line_color));
                            z = false;
                        }
                        if (this.emailEditText.isEnabled()) {
                            if (TextUtils.isEmpty(trim4)) {
                                this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                                layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 12));
                                this.toggleText.setLayoutParams(layoutParams);
                                return false;
                            }
                            this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
                            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 14));
                            this.toggleText.setLayoutParams(layoutParams);
                            return false;
                        }
                        return z;
                    }
                }
            }
            this.firstnameTextInputLayout.setError(getString(R.string.home_user_name_validation_msg));
        }
        z = false;
        if (TextUtils.isEmpty(trim2)) {
            this.emailTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
        } else {
            if (Utils.validateEmail(trim2)) {
                this.emailTextInputLayout.setError(getString(R.string.home_email_validation_msg));
            }
            if (this.countryCode == null) {
            }
            if (Utils.isValidPhoneNumber(requireActivity().getApplicationContext(), substring, trim3, true)) {
                this.isMobileNumberErrorFieldEnabled = true;
                this.mobileNumberError.setText(getString(R.string.home_not_valid_mobile_number_label));
                this.mobileNumberError.setAlpha(1.0f);
                this.mobileNumberError.setVisibility(0);
                this.mobileLineView.setBackgroundColor(Utils.applyColor(requireActivity(), R.color.edit_text_error_background_line_color));
                z = false;
            }
            if (this.emailEditText.isEnabled()) {
                if (TextUtils.isEmpty(trim4)) {
                    this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
                    layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 14));
                    this.toggleText.setLayoutParams(layoutParams);
                    return false;
                }
                this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 12));
                this.toggleText.setLayoutParams(layoutParams);
                return false;
            }
            return z;
        }
        z = false;
        if (this.countryCode == null) {
        }
        if (Utils.isValidPhoneNumber(requireActivity().getApplicationContext(), substring, trim3, true)) {
            this.isMobileNumberErrorFieldEnabled = true;
            this.mobileNumberError.setText(getString(R.string.home_not_valid_mobile_number_label));
            this.mobileNumberError.setAlpha(1.0f);
            this.mobileNumberError.setVisibility(0);
            this.mobileLineView.setBackgroundColor(Utils.applyColor(requireActivity(), R.color.edit_text_error_background_line_color));
            z = false;
        }
        if (this.emailEditText.isEnabled()) {
            if (TextUtils.isEmpty(trim4)) {
                this.passwordTextInputLayout.setError(getString(R.string.home_field_not_empty_label));
                layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 12));
                this.toggleText.setLayoutParams(layoutParams);
                return false;
            }
            this.passwordTextInputLayout.setError(getString(R.string.home_password_valid_msg));
            layoutParams.setMargins(0, 0, 0, Utils.getPixelsFromDPs(requireActivity(), 14));
            this.toggleText.setLayoutParams(layoutParams);
            return false;
        }
        return z;
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

    public void goToOtpScreen(SignUpRequest signUpRequest, PrefUtils prefUtils, SignUpResponse signUpResponse) {
        signUpRequest.setUserId(signUpResponse.getUid());
        signUpRequest.setMail(this.emailEditText.getText().toString().trim());
        prefUtils = this.mobileEditText.getText().toString().trim();
        if (prefUtils.substring(0, 1).equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            prefUtils = prefUtils.substring(1);
        }
        signUpRequest.setFieldPhoneNumber(prefUtils);
        signUpRequest.setCountryCode(this.countryCode);
        signUpRequest.setCountryFlag(this.countryFlag);
        signUpRequest.setFromSocial(false);
        EventBus.getDefault().postSticky(signUpRequest);
        goToActivity(OTPActivity.class);
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
            i = new Intent(requireActivity(), SplashScreenActivity.class);
            i.setFlags(268468224);
            startActivity(i);
            requireActivity().finish();
        }
    }

    public void onStop() {
        super.onStop();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    public void onDetach() {
        if (this.signUpPresenter != null) {
            this.signUpPresenter.unSubscribeDisposables();
        }
        if (this.socialSignUpPresenter != null) {
            this.socialSignUpPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void socialSignUpSuccess(SocialSignUpResponse socialSignUpResponse, PrefUtils prefUtils) {
        prefUtils = new SignUpRequest();
        prefUtils.setUserId(socialSignUpResponse.getUid());
        prefUtils.setMail(socialSignUpResponse.getMail());
        prefUtils.setFieldPhoneNumber(this.mobileEditText.getText().toString().trim());
        prefUtils.setProvider(socialSignUpResponse.getProvider());
        prefUtils.setIdentifier(socialSignUpResponse.getIdentifier());
        prefUtils.setFromSocial(true);
        prefUtils.setCountryCode(this.countryCode);
        prefUtils.setCountryFlag(this.countryFlag);
        EventBus.getDefault().postSticky(prefUtils);
        goToActivity(OTPActivity.class);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSocialDataEvent(SocialDataEvent socialDataEvent) {
        if (socialDataEvent != null && socialDataEvent.getSmartUser() != null) {
            this.smartUser = socialDataEvent.getSmartUser();
            this.loginType = socialDataEvent.getLoginType();
            this.emailEditText.setText(this.smartUser.getEmail());
            this.emailEditText.setEnabled(false);
            this.firstNameEditText.setText(this.smartUser.getUsername());
            this.passwordLayout.setVisibility(8);
        }
    }
}
