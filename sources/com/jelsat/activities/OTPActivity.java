package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.generateotp.GenerateOTPPresenter;
import com.businesslogic.generateotp.IGenerateOTPView;
import com.businesslogic.propertyseed.IPropertySeedView;
import com.businesslogic.propertyseed.PropertySeedPresenter;
import com.businesslogic.signin.ISignInView;
import com.businesslogic.signin.SignInPresenter;
import com.businesslogic.socialsignin.ISocialSignInView;
import com.businesslogic.socialsignin.SocialSignInPresenter;
import com.businesslogic.validateotp.IValidateOTPView;
import com.businesslogic.validateotp.ValidateOTPPresenter;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.generateotp.GenerateOTPRequest;
import com.data.generateotp.UpdateMobileNumberRequest;
import com.data.retrofit.RetrofitClient;
import com.data.signin.SignInRequest;
import com.data.signin.SignInResponse;
import com.data.signup.SignUpRequest;
import com.data.socialsignin.SocialSignInRequest;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.data.validateotp.ValidateOTPRequest;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.utils.Utils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OTPActivity extends BaseAppCompactActivity implements IGenerateOTPView, IPropertySeedView, ISignInView, ISocialSignInView, IValidateOTPView {
    private String countryCode;
    private int countryFlag;
    private DialogUtil dialogUtil;
    @BindView(2131362121)
    public ImageView editImageView;
    private GenerateOTPPresenter generateOTPPresenter = new GenerateOTPPresenter(this, RetrofitClient.getAPIService());
    private boolean isShowingEditIcon = true;
    private KeyListener keyListener;
    @BindView(2131362179)
    public ImageView mCountryFlagImageView;
    @BindView(2131362336)
    public EditText mobileNumberEdiText;
    private String newNumber;
    private String oldNumber;
    @BindView(2131362391)
    public EditText otpEditText;
    private PropertySeedPresenter propertySeedPresenter = new PropertySeedPresenter(this, RetrofitClient.getAPIService());
    private SignInPresenter signInPresenter = new SignInPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    private SignUpRequest signUpRequest;
    private SocialSignInPresenter socialSignInPresenter = new SocialSignInPresenter(this, RetrofitClient.getAPIService(), PrefUtils.getInstance());
    private ValidateOTPPresenter validateOTPPresenter = new ValidateOTPPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362816)
    public Button verifyOTPButton;

    public int getActivityLayout() {
        return R.layout.activity_otp;
    }

    @OnClick({2131362179})
    public void clickOnFlag() {
        if (!this.isShowingEditIcon) {
            startActivityForResult(new Intent(this, CountryPickerActivity.class), 101);
        }
    }

    @OnClick({2131362121})
    public void clickOnEditImage(View view) {
        if (this.isShowingEditIcon != null) {
            this.isShowingEditIcon = false;
            this.editImageView.setImageResource(R.drawable.ic_done);
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(this.mobileNumberEdiText, 1);
            }
            this.mobileNumberEdiText.setKeyListener(this.keyListener);
            this.mobileNumberEdiText.requestFocus();
            this.mobileNumberEdiText.setSelection(this.mobileNumberEdiText.getText().length());
            return;
        }
        hideKeyboard();
        if (validateMobileNumber() != null) {
            view = this.mobileNumberEdiText.getText().toString().trim();
            if (view.substring(0, 1).equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                view = view.substring(1);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.countryCode.substring(1));
            stringBuilder.append(view);
            if (this.oldNumber.equalsIgnoreCase(stringBuilder.toString()) != null) {
                this.dialogUtil.showOkDialog(getString(R.string.please_provide_another_number));
            } else {
                doUpdateMobileNumber();
            }
        }
    }

    private void doUpdateMobileNumber() {
        if (isNetworkConnected()) {
            String trim = this.mobileNumberEdiText.getText().toString().trim();
            if (trim.substring(0, 1).equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                trim = trim.substring(1);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.countryCode.substring(1));
            stringBuilder.append(trim);
            this.newNumber = stringBuilder.toString();
            UpdateMobileNumberRequest updateMobileNumberRequest = new UpdateMobileNumberRequest();
            updateMobileNumberRequest.setUid(this.signUpRequest.getUserId());
            updateMobileNumberRequest.setOldNumber(this.oldNumber);
            updateMobileNumberRequest.setPhoneNumber(this.newNumber);
            updateMobileNumberRequest.setFieldCountryCode(this.countryCode.substring(1));
            this.generateOTPPresenter.updateMobileNumber(getString(R.string.updating_mobile_number), updateMobileNumberRequest);
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    @OnClick({2131361959})
    public void clickOnCancel(View view) {
        finish();
    }

    @OnClick({2131362816})
    public void clickOnVerifyButton(View view) {
        doVerifyOtp(this.oldNumber);
    }

    private void doVerifyOtp(String str) {
        hideKeyboard();
        String trim = this.otpEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim) || trim.length() != 4) {
            showToast(getString(R.string.enter_valid_otp));
        } else if (isNetworkConnected()) {
            ValidateOTPRequest validateOTPRequest = new ValidateOTPRequest();
            validateOTPRequest.setPhoneNumber(str);
            validateOTPRequest.setEmail(this.signUpRequest.getMail());
            validateOTPRequest.setOtp(trim);
            this.validateOTPPresenter.validateOTP(getString(R.string.verifying_otp), validateOTPRequest, false);
        } else {
            this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
        }
    }

    @OnClick({2131362508})
    public void clickOnResend() {
        doGenerateOTP(this.oldNumber);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.dialogUtil = new DialogUtil(this);
        this.keyListener = this.mobileNumberEdiText.getKeyListener();
        this.mobileNumberEdiText.setKeyListener(null);
        this.otpEditText.addTextChangedListener(new OTPActivity$1(this));
        this.otpEditText.setOnEditorActionListener(new OTPActivity$2(this));
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
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
                i2 = new StringBuilder("countrycode------>");
                i2.append(this.countryCode);
                Log.d("aaa", i2.toString());
            }
        }
    }

    private void doGenerateOTP(String str) {
        if (isNetworkConnected()) {
            GenerateOTPRequest generateOTPRequest = new GenerateOTPRequest();
            generateOTPRequest.setPhoneNumber(str);
            generateOTPRequest.setEmail(this.signUpRequest.getMail());
            this.generateOTPPresenter.generateOTP(getString(R.string.sending_otp), generateOTPRequest);
            return;
        }
        this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
    }

    public void generateOTPSuccessResponse() {
        showToast(getString(R.string.otp_sent_to_mobile));
    }

    public void updateMobileNumberSuccess(UpdateMobileNumberRequest updateMobileNumberRequest) {
        this.oldNumber = updateMobileNumberRequest.getPhoneNumber();
        this.editImageView.setImageResource(R.drawable.ic_edit);
        this.isShowingEditIcon = true;
        this.editImageView.setOnKeyListener(null);
        this.signUpRequest.setFieldPhoneNumber(this.oldNumber);
        showToast(getString(R.string.otp_sent_to_mobile));
    }

    public void validationOTPSuccessResponse() {
        if (PrefUtils.getInstance().getUserAccessToken() == null) {
            if (!NetworkUtil.isConnectedToNetwork(this)) {
                if (!NetworkUtil.isNetworkConnectedThroughWifi(this)) {
                    this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
                }
            }
            if (this.signUpRequest.isFromSocial()) {
                doSocialSignIn();
            } else {
                doSignIn();
            }
        }
    }

    public void goToNextScreen(SignInResponse signInResponse, PrefUtils prefUtils) {
        if (signInResponse.getToken() != null) {
            prefUtils.saveUserToken(signInResponse.getToken());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(signInResponse.getSessionName());
            stringBuilder.append("=");
            stringBuilder.append(signInResponse.getSessionId());
            prefUtils.saveCookie(stringBuilder.toString());
            if (prefUtils.getUserAccessToken() != null) {
                this.propertySeedPresenter.getPropertySeed(getString(R.string.hang_on_a_moment), true);
            }
        }
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
        if (i == 803) {
            i = new Intent(this, SplashScreenActivity.class);
            i.setFlags(268468224);
            startActivity(i);
            finish();
        }
    }

    public void socialSignInSuccess(SignInResponse signInResponse, PrefUtils prefUtils) {
        if (signInResponse.getToken() != null) {
            prefUtils.saveUserToken(signInResponse.getToken());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(signInResponse.getSessionName());
            stringBuilder.append("=");
            stringBuilder.append(signInResponse.getSessionId());
            prefUtils.saveCookie(stringBuilder.toString());
            prefUtils.saveSocialSignin(true);
            if (prefUtils.getUserAccessToken() != null) {
                this.propertySeedPresenter.getPropertySeed(getString(R.string.hang_on_a_moment), true);
                return;
            }
        }
        showToast(getString(R.string.general_api_error_msg));
    }

    public void saveFCMTokenSuccess() {
        goToDashBoardScreen();
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

    public void saveTokenSuccess(PrefUtils prefUtils) {
        prefUtils.setDeviceTokenUpdated(true);
        goToDashBoardScreen();
    }

    public void generateOTPerrorOccurred(APIError aPIError) {
        this.newNumber = this.oldNumber;
        this.editImageView.setImageResource(R.drawable.ic_edit);
        this.isShowingEditIcon = true;
        this.editImageView.setOnKeyListener(null);
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    private void doSignIn() {
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setUsername(this.signUpRequest.getFieldPhoneNumber());
        signInRequest.setPassword(this.signUpRequest.getPass());
        this.signInPresenter.userSignIn(getString(R.string.please_wait), signInRequest);
    }

    private void doSocialSignIn() {
        SocialSignInRequest socialSignInRequest = new SocialSignInRequest();
        socialSignInRequest.setIdentifier(this.signUpRequest.getIdentifier());
        socialSignInRequest.setProvider(this.signUpRequest.getProvider());
        this.socialSignInPresenter.doSocialSignIn(socialSignInRequest, getString(R.string.home_checking_user_existed));
    }

    private void goToDashBoardScreen() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.generateOTPPresenter != null) {
            this.generateOTPPresenter.unSubscribeDisposables();
        }
        if (this.validateOTPPresenter != null) {
            this.validateOTPPresenter.unSubscribeDisposables();
        }
        if (this.signInPresenter != null) {
            this.signInPresenter.unSubscribeDisposables();
        }
        if (this.socialSignInPresenter != null) {
            this.socialSignInPresenter.unSubscribeDisposables();
        }
        if (this.propertySeedPresenter != null) {
            this.propertySeedPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getSignUpData(SignUpRequest signUpRequest) {
        if (signUpRequest != null) {
            this.signUpRequest = signUpRequest;
            this.mobileNumberEdiText.setText(signUpRequest.getFieldPhoneNumber());
            this.countryCode = signUpRequest.getCountryCode();
            this.countryFlag = signUpRequest.getCountryFlag();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(signUpRequest.getCountryCode().substring(1));
            stringBuilder.append(signUpRequest.getFieldPhoneNumber());
            String stringBuilder2 = stringBuilder.toString();
            this.oldNumber = stringBuilder2;
            this.newNumber = stringBuilder2;
            if (this.countryFlag != 0) {
                this.mCountryFlagImageView.setImageResource(this.countryFlag);
            } else {
                this.mCountryFlagImageView.setImageResource(R.drawable.ic_phone_number);
            }
            EventBus.getDefault().removeStickyEvent(signUpRequest);
            doGenerateOTP(this.oldNumber);
            signUpRequest.setFieldPhoneNumber(this.oldNumber);
        }
    }

    public boolean validateMobileNumber() {
        String substring = this.countryCode.substring(1);
        String trim = this.mobileNumberEdiText.getText().toString().trim();
        if (this.countryCode != null && this.countryCode.length() == 0 && trim.length() == 0) {
            showToast(getString(R.string.country_code_phone_validation_label));
            return false;
        } else if (Utils.isValidPhoneNumber(getApplicationContext(), substring, trim, true)) {
            return true;
        } else {
            return false;
        }
    }
}
