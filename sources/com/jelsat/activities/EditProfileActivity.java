package com.jelsat.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.media.ExifInterface;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.updateprofile.IUpdateProfileView;
import com.businesslogic.updateprofile.UpdateProfilePresenter;
import com.data.retrofit.RetrofitClient;
import com.data.signin.UserModel;
import com.data.updateprofile.UpdateProfileRequest;
import com.data.updateprofile.UpdateProfileResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.DOBSelectedEvent;
import com.jelsat.firebase.MyFirebaseMessagingService.EmailStatus;
import com.jelsat.fragments.DatePickerFragment;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EditProfileActivity extends BaseImageActivity implements IUpdateProfileView {
    @BindView(2131362134)
    EditText aboutmeEdittext;
    @BindView(2131362135)
    EditText addressEditText;
    private Calendar calendar = Calendar.getInstance();
    @BindView(2131362103)
    LinearLayout dobUpdateLayout;
    @BindView(2131362721)
    TextInputLayout dobValueTextInputLayout;
    @BindView(2131362720)
    EditText dobValueTextView;
    @BindView(2131362725)
    EditText emailEditText;
    @BindView(2131362724)
    TextView emailVerifyStatusTextView;
    @BindView(2131361989)
    RadioButton femaleCheck;
    @BindView(2131362142)
    EditText languagesEditText;
    @BindView(2131361992)
    RadioButton maleCheckBox;
    @BindView(2131362740)
    TextView mobileNumberValueTextView;
    @BindView(2131362739)
    TextView mobileNumberVerifyStatus;
    private File nationalIdImageFile;
    @BindView(2131362240)
    ImageView nationalIdIv;
    @BindView(2131362347)
    LinearLayout nationalidUpdateLayout;
    @BindView(2131362145)
    EditText nationalityEditText;
    private PrefUtils prefUtils;
    private UpdateProfilePresenter presenter = new UpdateProfilePresenter(this, RetrofitClient.getAPIServiceForMultiPart());
    private File profileImageFile;
    @BindView(2131362248)
    ImageView profileIv;
    @BindView(2131362750)
    EditText profileNameTextView;
    @BindView(2131362718)
    TextView tvDobUpdate;
    @BindView(2131362719)
    TextView tvDobVerifyStatus;
    @BindView(2131362745)
    LinearLayout tvNationalidUpdate;
    @BindView(2131362746)
    TextView tvNationalidVerifyStatus;
    @BindView(2131361945)
    FancyButton updateProfileBtn;
    @BindView(2131362803)
    LinearLayout uploadNationalidLayout;

    public int getActivityLayout() {
        return R.layout.activity_editprofile;
    }

    @OnClick({2131362718})
    public void dobUpdate() {
        showDatePickerDialog();
    }

    @OnClick({2131362745})
    public void nationaliIdUpadate() {
        if (isDeviceSupportCamera()) {
            selectImage(true, 1);
        }
    }

    @OnClick({2131362248})
    public void clickOnProfileImage() {
        if (isDeviceSupportCamera()) {
            selectImage(true, 0);
        }
    }

    @OnClick({2131362724})
    public void clickOnEmailVerify() {
        if (this.prefUtils.getUser() != null && this.prefUtils.getUser().getEmailVerified().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) && fieldValidation()) {
            this.presenter.verifyEmail(getString(R.string.sending_verfication_link));
        }
    }

    @OnClick({2131362240})
    public void clickOnNationalIdImage() {
        if (isDeviceSupportCamera()) {
            selectImage(true, 1);
        }
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131362142})
    public void openLanguagesPicker() {
        Intent intent = new Intent(this, LanguagesPickerActivity.class);
        intent.putExtra(StringConstants.LANGUAGES, this.languagesEditText.getText().toString());
        startActivityForResult(intent, 102);
    }

    @OnClick({2131362720})
    public void getCalender() {
        showDatePickerDialog();
    }

    @OnClick({2131362145})
    public void clickOnCountry() {
        Intent intent = new Intent(this, CountryPickerActivity.class);
        intent.putExtra(StringConstants.SHOW_COUNTRY_CODE, false);
        startActivityForResult(intent, 101);
    }

    @OnClick({2131361945})
    public void updateProfile() {
        if (fieldValidation()) {
            UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
            updateProfileRequest.setFieldFullName(this.profileNameTextView.getText().toString().trim());
            updateProfileRequest.setMail(this.emailEditText.getText().toString().trim());
            updateProfileRequest.setAbout(this.aboutmeEdittext.getText().toString().trim());
            updateProfileRequest.setDob(Utils.convertDateToString("yyyy-MM-dd", this.calendar.getTime()));
            if (this.maleCheckBox.isChecked() || this.femaleCheck.isChecked()) {
                updateProfileRequest.setGender(this.maleCheckBox.isChecked() ? "male" : "female");
            }
            updateProfileRequest.setNationality(this.nationalityEditText.getText().toString().trim());
            updateProfileRequest.setSpokenLanguages(this.languagesEditText.getText().toString().trim());
            updateProfileRequest.setUserAddress(this.addressEditText.getText().toString().trim());
            this.presenter.updateProfileDataWithimage(getString(R.string.please_wait), this.profileImageFile, this.nationalIdImageFile, updateProfileRequest);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.prefUtils = PrefUtils.getInstance();
        if (!(getIntent().getExtras() == null || getIntent().getBooleanExtra(StringConstants.FROM_FCM, false) == null)) {
            bundle = this.prefUtils.getUser();
            bundle.setEmailVerified(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            this.prefUtils.saveUser(bundle);
        }
        this.emailEditText.setTag(this.emailEditText.getKeyListener());
        this.aboutmeEdittext.setImeOptions(6);
        this.aboutmeEdittext.setRawInputType(1);
        initUserData(this.prefUtils);
        this.profileNameTextView.addTextChangedListener(new EditProfileActivity$1(this));
        this.aboutmeEdittext.setOnTouchListener(new EditProfileActivity$2(this));
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    protected void onResume() {
        super.onResume();
        Log.e("lifecycle", "onResume");
    }

    public void onPause() {
        super.onPause();
        Log.e("lifecycle", "onPause");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 101) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                this.nationalityEditText.setText(extras.getString(StringConstants.COUNTRY_NAME));
                this.nationalityEditText.setHintTextColor(applyColor(R.color.app_background));
            }
        }
        if (i2 == 102) {
            i2 = intent.getExtras();
            if (i2 != 0) {
                this.languagesEditText.setText(i2.getString(StringConstants.LANGUAGES));
                this.languagesEditText.setHintTextColor(applyColor(R.color.app_background));
            }
        }
    }

    private void initUserData(PrefUtils prefUtils) {
        if (prefUtils.getUser() != null) {
            this.profileNameTextView.setText(prefUtils.getUser().getFullName());
            this.mobileNumberValueTextView.setText(prefUtils.getUser().getPhoneNumber());
            if (prefUtils.getUser().getFieldDobVerified() != null) {
                setDOBVerifyStatus(Integer.parseInt(prefUtils.getUser().getFieldDobVerified()));
                Date convertStringToDate = Utils.convertStringToDate("yyyy-MM-dd", prefUtils.getUser().getDateOfBirth());
                if (convertStringToDate != null) {
                    this.calendar.setTime(convertStringToDate);
                    this.dobValueTextView.setText(Utils.convertDateToString("dd MMMM yyyy", convertStringToDate));
                }
            } else {
                setDOBVerifyStatus(0);
            }
            if (prefUtils.getUser().getGender().equalsIgnoreCase("male")) {
                this.maleCheckBox.setChecked(true);
                this.femaleCheck.setChecked(false);
            } else if (prefUtils.getUser().getGender().equalsIgnoreCase("female")) {
                this.maleCheckBox.setChecked(false);
                this.femaleCheck.setChecked(true);
            } else {
                this.maleCheckBox.setChecked(false);
                this.femaleCheck.setChecked(false);
            }
            if (prefUtils.getUser().getMail() != null) {
                this.emailEditText.setText(prefUtils.getUser().getMail());
            }
            if (PrefUtils.getInstance().isSocialSignin()) {
                this.emailEditText.setKeyListener(null);
            } else {
                this.emailEditText.setKeyListener((KeyListener) this.emailEditText.getTag());
            }
            if (prefUtils.getUser().getNationality() != null) {
                this.nationalityEditText.setText(prefUtils.getUser().getNationality());
            }
            if (prefUtils.getUser().getUserAddress() != null) {
                this.addressEditText.setText(prefUtils.getUser().getUserAddress());
            }
            if (prefUtils.getUser().getSpokenLanguages() != null) {
                this.languagesEditText.setText(prefUtils.getUser().getSpokenLanguages());
            }
            if (prefUtils.getUser().getBio() != null) {
                this.aboutmeEdittext.setText(prefUtils.getUser().getBio());
            }
            if (prefUtils.getUser().getEmailVerified() != null) {
                setEmailVerifyStatus(Integer.parseInt(prefUtils.getUser().getEmailVerified()));
            } else {
                setEmailVerifyStatus(0);
            }
            if (prefUtils.getUser().getPhoneNumberVerified() != null) {
                setMobileNumberStatus(Integer.parseInt(prefUtils.getUser().getPhoneNumberVerified()));
            } else {
                setMobileNumberStatus(0);
            }
            GlideApp.with(getApplicationContext()).load(prefUtils.getUser().getGuestImage()).placeholder(R.drawable.ic_profile_image_1).error(R.drawable.ic_profile_image_1).into(this.profileIv);
            if (this.nationalIdIv == null) {
                GlideApp.with(getApplicationContext()).load(prefUtils.getUser().getNationalId()).placeholder(R.drawable.ic_camera).error(R.drawable.ic_camera).into(this.nationalIdIv);
            } else if (TextUtils.isEmpty(prefUtils.getUser().getNationalId())) {
                this.uploadNationalidLayout.setVisibility(0);
                this.nationalidUpdateLayout.setVisibility(8);
                setnationalIdVerifyStatus(0);
            } else {
                this.uploadNationalidLayout.setVisibility(8);
                this.nationalidUpdateLayout.setVisibility(0);
                setnationalIdVerifyStatus(Integer.parseInt(prefUtils.getUser().getFieldNationalIdVerified()));
            }
        }
    }

    public void showDatePickerDialog() {
        DialogFragment datePickerFragment = new DatePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("calendar", this.calendar);
        datePickerFragment.setArguments(bundle);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDOBSelected(DOBSelectedEvent dOBSelectedEvent) {
        if (dOBSelectedEvent != null) {
            this.calendar = dOBSelectedEvent.getCalendar();
            this.dobValueTextView.setText(Utils.convertDateToString("dd MMMM yyyy", this.calendar.getTime()));
            this.dobValueTextView.setHintTextColor(applyColor(R.color.app_background));
        }
    }

    private void setnationalIdVerifyStatus(int i) {
        switch (i) {
            case 0:
                this.tvNationalidVerifyStatus.setText(getString(R.string.editprofile_not_verified));
                this.tvNationalidVerifyStatus.setTextColor(getResources().getColor(R.color.resend_color));
                return;
            case 1:
                this.tvNationalidVerifyStatus.setText(getString(R.string.edit_profile_verified));
                this.tvNationalidVerifyStatus.setTextColor(getResources().getColor(R.color.property_instant_color));
                return;
            case 2:
                this.tvNationalidVerifyStatus.setText(getString(R.string.rejected));
                this.tvNationalidVerifyStatus.setTextColor(getResources().getColor(R.color.resend_color));
                break;
            default:
                break;
        }
    }

    private void setDOBVerifyStatus(int i) {
        switch (i) {
            case 0:
                this.tvDobVerifyStatus.setText(getString(R.string.editprofile_not_verified));
                this.tvDobVerifyStatus.setTextColor(getResources().getColor(R.color.resend_color));
                return;
            case 1:
                this.tvDobVerifyStatus.setText(getString(R.string.edit_profile_verified));
                this.tvDobVerifyStatus.setTextColor(getResources().getColor(R.color.property_instant_color));
                break;
            default:
                break;
        }
    }

    private void setEmailVerifyStatus(int i) {
        switch (i) {
            case 0:
                this.emailVerifyStatusTextView.setText(getString(R.string.not_verified));
                this.emailVerifyStatusTextView.setTextColor(getResources().getColor(R.color.resend_color));
                return;
            case 1:
                this.emailVerifyStatusTextView.setText(getString(R.string.edit_profile_verified));
                this.emailVerifyStatusTextView.setTextColor(getResources().getColor(R.color.property_instant_color));
                break;
            default:
                break;
        }
    }

    private void setMobileNumberStatus(int i) {
        switch (i) {
            case 0:
                this.mobileNumberVerifyStatus.setText(getString(R.string.not_verified));
                this.mobileNumberVerifyStatus.setTextColor(getResources().getColor(R.color.resend_color));
                return;
            case 1:
                this.mobileNumberVerifyStatus.setText(getString(R.string.verified));
                this.mobileNumberVerifyStatus.setTextColor(getResources().getColor(R.color.property_instant_color));
                break;
            default:
                break;
        }
    }

    public void setProfileImage(Bitmap bitmap, File file, int i) {
        if (bitmap != null) {
            switch (i) {
                case 0:
                    this.profileIv.setImageBitmap(bitmap);
                    this.profileImageFile = file;
                    return;
                case 1:
                    this.nationalIdIv.setImageBitmap(bitmap);
                    this.nationalIdImageFile = file;
                    if (this.nationalIdIv != null) {
                        this.uploadNationalidLayout.setVisibility(null);
                        this.nationalidUpdateLayout.setVisibility(8);
                        break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onSuccess(UpdateProfileResponse updateProfileResponse) {
        showToast(getString(R.string.profile_updated_successfully));
        this.prefUtils.saveUser(updateProfileResponse.getUser());
        finish();
    }

    public void onSuccess() {
        showToast(getString(R.string.verification_link_sent));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void setEmailStatus(EmailStatus emailStatus) {
        if (emailStatus != null) {
            UserModel user = this.prefUtils.getUser();
            if (emailStatus.getStatusCode() == 1001) {
                user.setEmailVerified(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                this.prefUtils.saveUser(user);
                setEmailVerifyStatus(Integer.parseInt(this.prefUtils.getUser().getEmailVerified()));
            } else if (emailStatus.getStatusCode() == 1011) {
                user.setNationalId(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                this.prefUtils.saveUser(user);
                setnationalIdVerifyStatus(Integer.parseInt(this.prefUtils.getUser().getNationalId()));
            } else if (emailStatus.getStatusCode() == 1012) {
                user.setNationalId(ExifInterface.GPS_MEASUREMENT_2D);
                this.prefUtils.saveUser(user);
                setnationalIdVerifyStatus(Integer.parseInt(this.prefUtils.getUser().getNationalId()));
            }
            EventBus.getDefault().removeStickyEvent(emailStatus);
        }
    }

    private boolean fieldValidation() {
        this.emailEditText.setError(null);
        Object trim = this.emailEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            this.emailEditText.setError(getString(R.string.home_field_not_empty_label));
            return false;
        } else if (Utils.validateEmail(trim)) {
            return true;
        } else {
            this.emailEditText.setError(getString(R.string.home_email_validation_msg));
            return false;
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
