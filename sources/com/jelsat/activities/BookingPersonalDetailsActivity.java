package com.jelsat.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.updateprofile.IUpdateProfileView;
import com.businesslogic.updateprofile.UpdateProfilePresenter;
import com.data.retrofit.RetrofitClient;
import com.data.updateprofile.UpdateProfileRequest;
import com.data.updateprofile.UpdateProfileResponse;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.events.BookPersonalDetailsEvent;
import com.jelsat.events.DOBSelectedEvent;
import com.jelsat.events.PropertyBookEvent;
import com.jelsat.fragments.DatePickerFragment;
import com.jelsat.utils.GlideApp;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BookingPersonalDetailsActivity extends BaseImageActivity implements IUpdateProfileView {
    private Calendar calendar = Calendar.getInstance();
    private Date checkInDate;
    private Date checkoutDate;
    @BindView(2131362074)
    LinearLayout dateOfBirthLayout;
    @BindView(2131362075)
    View dateOfBirthView;
    private String guestCount = AppEventsConstants.EVENT_PARAM_VALUE_YES;
    private File imageFile;
    @BindView(2131362345)
    ImageView nationalIdImageView;
    @BindView(2131362346)
    LinearLayout nationalIdLayout;
    private String propertyId;
    private UpdateProfilePresenter updateProfilePresenter = new UpdateProfilePresenter(this, RetrofitClient.getAPIServiceForMultiPart());
    @BindView(2131362806)
    TextView uploadNationalIdTv;
    @BindView(2131362808)
    TextView userDateOfBirth;
    @BindView(2131362810)
    TextView userEmail;
    @BindView(2131362812)
    TextView userMobileNumber;
    @BindView(2131362813)
    TextView userName;

    public int getActivityLayout() {
        return R.layout.activity_booking_personal_details;
    }

    public void onSuccess() {
    }

    @OnClick({2131362074})
    public void clickOnDateOfBirthLayout() {
        Log.d("zzz", "dateofbirth");
        showDatePickerDialog();
    }

    @OnClick({2131362346})
    public void clickOnNationalIdLayout() {
        Log.d("zzz", "nationalId");
        if (isDeviceSupportCamera()) {
            selectImage(false, 0);
        }
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131361959})
    public void clickOnCancel() {
        Intent intent = new Intent(this, PropertyDetailActivity.class);
        intent.setFlags(67108864);
        startActivity(intent);
    }

    @OnClick({2131362357})
    public void clickOnNextButton() {
        if (!this.nationalIdLayout.isClickable()) {
            if (!this.dateOfBirthLayout.isClickable()) {
                PropertyBookEvent propertyBookEvent = new PropertyBookEvent(this.propertyId);
                propertyBookEvent.setCheckInDate(this.checkInDate);
                propertyBookEvent.setCheckOutDate(this.checkoutDate);
                propertyBookEvent.setSelectedGuestCount(this.guestCount);
                EventBus.getDefault().postSticky(propertyBookEvent);
                startActivity(new Intent(this, BookingPaymentActivity.class));
            }
        }
        if (validateFields() && this.imageFile != null) {
            UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
            updateProfileRequest.setDob(convertDateToString("yyyy-MM-dd", this.calendar.getTime()));
            this.updateProfilePresenter.updateProfileDataWithimage(getString(R.string.please_wait), null, this.imageFile, updateProfileRequest);
        }
    }

    private boolean validateFields() {
        if (this.userDateOfBirth.getText().toString().equalsIgnoreCase("DD-MM-YYYY")) {
            showToast(getString(R.string.please_choose_your_date_birth));
            return false;
        } else if (this.uploadNationalIdTv.getVisibility() != 0) {
            return true;
        } else {
            showToast(getString(R.string.upload_your_national_id));
            return false;
        }
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 21) {
            bundle = getWindow();
            bundle.addFlags(Integer.MIN_VALUE);
            bundle.setStatusBarColor(applyColor(R.color.dialog_save));
        }
        if (PrefUtils.getInstance().getUser() != null) {
            bundle = PrefUtils.getInstance().getUser();
            this.userName.setText(bundle.getFullName());
            this.userEmail.setText(bundle.getMail());
            this.userMobileNumber.setText(bundle.getPhoneNumber());
            if (TextUtils.isEmpty(bundle.getDateOfBirth())) {
                this.dateOfBirthLayout.setVisibility(0);
                this.dateOfBirthView.setVisibility(0);
                this.dateOfBirthLayout.setClickable(true);
            } else {
                TextView textView = this.userDateOfBirth;
                StringBuilder stringBuilder = new StringBuilder("<font color='");
                stringBuilder.append(getResources().getColor(R.color.normal_text_color));
                stringBuilder.append("'>");
                stringBuilder.append(bundle.getDateOfBirth());
                stringBuilder.append("</font>");
                textView.setText(Html.fromHtml(stringBuilder.toString()));
                this.dateOfBirthLayout.setClickable(false);
                this.dateOfBirthLayout.setVisibility(8);
                this.dateOfBirthView.setVisibility(8);
                this.calendar.setTime(convertStringToDate(bundle.getDateOfBirth()));
            }
            Log.d("zzz", bundle.getDateOfBirth());
            if (TextUtils.isEmpty(bundle.getNationalId())) {
                this.nationalIdLayout.setClickable(true);
                this.uploadNationalIdTv.setVisibility(0);
                this.nationalIdImageView.setVisibility(8);
                this.nationalIdLayout.setVisibility(0);
            } else {
                this.uploadNationalIdTv.setVisibility(4);
                this.nationalIdImageView.setVisibility(0);
                initNationalIdImage(bundle.getNationalId());
                this.nationalIdLayout.setClickable(false);
                this.nationalIdLayout.setVisibility(8);
            }
        }
    }

    private void initNationalIdImage(String str) {
        GlideApp.with(this).load(str).placeholder(R.drawable.default_logo).error(R.drawable.default_logo).into(this.nationalIdImageView);
    }

    public void showDatePickerDialog() {
        DialogFragment datePickerFragment = new DatePickerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("calendar", this.calendar);
        datePickerFragment.setArguments(bundle);
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDOBSelected(DOBSelectedEvent dOBSelectedEvent) {
        if (dOBSelectedEvent != null) {
            this.calendar = dOBSelectedEvent.getCalendar();
            this.userDateOfBirth.setText(convertDateToString("dd-MM-yyyy", this.calendar.getTime()));
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getSelectedDates(BookPersonalDetailsEvent bookPersonalDetailsEvent) {
        if (bookPersonalDetailsEvent != null) {
            this.propertyId = bookPersonalDetailsEvent.getPropertyId();
            this.checkInDate = bookPersonalDetailsEvent.getCheckInDate();
            this.checkoutDate = bookPersonalDetailsEvent.getCheckOutDate();
            this.guestCount = bookPersonalDetailsEvent.getSelectedGuestCount();
            EventBus.getDefault().removeStickyEvent(bookPersonalDetailsEvent);
        }
    }

    private String convertDateToString(String str, Date date) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    private Date convertStringToDate(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str);
        } catch (String str2) {
            str2.printStackTrace();
            return null;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void setProfileImage(Bitmap bitmap, File file, int i) {
        if (bitmap != null) {
            this.nationalIdImageView.setImageBitmap(bitmap);
            this.uploadNationalIdTv.setVisibility(4);
            this.nationalIdImageView.setVisibility(0);
            this.nationalIdLayout.setClickable(1);
        }
        this.imageFile = file;
    }

    public void onSuccess(UpdateProfileResponse updateProfileResponse) {
        dismissProgress();
        updateUserDataInPreferences(updateProfileResponse);
        updateProfileResponse = new PropertyBookEvent(this.propertyId);
        updateProfileResponse.setCheckInDate(this.checkInDate);
        updateProfileResponse.setCheckOutDate(this.checkoutDate);
        updateProfileResponse.setSelectedGuestCount(this.guestCount);
        EventBus.getDefault().postSticky(updateProfileResponse);
        startActivity(new Intent(this, BookingPaymentActivity.class));
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    private void updateUserDataInPreferences(UpdateProfileResponse updateProfileResponse) {
        PrefUtils.getInstance().saveUser(updateProfileResponse.getUser());
    }
}
