package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class EditProfileActivity_ViewBinding implements Unbinder {
    private EditProfileActivity target;
    private View view2131361892;
    private View view2131361945;
    private View view2131362142;
    private View view2131362145;
    private View view2131362240;
    private View view2131362248;
    private View view2131362718;
    private View view2131362720;
    private View view2131362724;
    private View view2131362745;

    @UiThread
    public EditProfileActivity_ViewBinding(EditProfileActivity editProfileActivity) {
        this(editProfileActivity, editProfileActivity.getWindow().getDecorView());
    }

    @UiThread
    public EditProfileActivity_ViewBinding(final EditProfileActivity editProfileActivity, View view) {
        this.target = editProfileActivity;
        editProfileActivity.emailEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_emailvalue, "field 'emailEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_email_verify_status, "field 'emailVerifyStatusTextView' and method 'clickOnEmailVerify'");
        editProfileActivity.emailVerifyStatusTextView = (TextView) Utils.castView(findRequiredView, R.id.tv_email_verify_status, "field 'emailVerifyStatusTextView'", TextView.class);
        this.view2131362724 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.clickOnEmailVerify();
            }
        });
        editProfileActivity.mobileNumberVerifyStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_mobilenumber_verify_status, "field 'mobileNumberVerifyStatus'", TextView.class);
        editProfileActivity.mobileNumberValueTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_mobilenumbervalue, "field 'mobileNumberValueTextView'", TextView.class);
        editProfileActivity.dobValueTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.tv_dobvalue_textview, "field 'dobValueTextInputLayout'", TextInputLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.tv_dobvalue, "field 'dobValueTextView' and method 'getCalender'");
        editProfileActivity.dobValueTextView = (EditText) Utils.castView(findRequiredView, R.id.tv_dobvalue, "field 'dobValueTextView'", EditText.class);
        this.view2131362720 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.getCalender();
            }
        });
        editProfileActivity.maleCheckBox = (RadioButton) Utils.findRequiredViewAsType(view, R.id.check_male, "field 'maleCheckBox'", RadioButton.class);
        editProfileActivity.femaleCheck = (RadioButton) Utils.findRequiredViewAsType(view, R.id.check_female, "field 'femaleCheck'", RadioButton.class);
        findRequiredView = Utils.findRequiredView(view, R.id.et_nationality, "field 'nationalityEditText' and method 'clickOnCountry'");
        editProfileActivity.nationalityEditText = (EditText) Utils.castView(findRequiredView, R.id.et_nationality, "field 'nationalityEditText'", EditText.class);
        this.view2131362145 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.clickOnCountry();
            }
        });
        editProfileActivity.addressEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.et_address, "field 'addressEditText'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.et_languages, "field 'languagesEditText' and method 'openLanguagesPicker'");
        editProfileActivity.languagesEditText = (EditText) Utils.castView(findRequiredView, R.id.et_languages, "field 'languagesEditText'", EditText.class);
        this.view2131362142 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.openLanguagesPicker();
            }
        });
        editProfileActivity.aboutmeEdittext = (EditText) Utils.findRequiredViewAsType(view, R.id.et_aboutme, "field 'aboutmeEdittext'", EditText.class);
        editProfileActivity.profileNameTextView = (EditText) Utils.findRequiredViewAsType(view, R.id.tv_profile_name, "field 'profileNameTextView'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.img_profile, "field 'profileIv' and method 'clickOnProfileImage'");
        editProfileActivity.profileIv = (ImageView) Utils.castView(findRequiredView, R.id.img_profile, "field 'profileIv'", ImageView.class);
        this.view2131362248 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.clickOnProfileImage();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.image_nationalid, "field 'nationalIdIv' and method 'clickOnNationalIdImage'");
        editProfileActivity.nationalIdIv = (ImageView) Utils.castView(findRequiredView, R.id.image_nationalid, "field 'nationalIdIv'", ImageView.class);
        this.view2131362240 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.clickOnNationalIdImage();
            }
        });
        editProfileActivity.uploadNationalidLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.uploadNationalIdLayout, "field 'uploadNationalidLayout'", LinearLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.btn_update_profile, "field 'updateProfileBtn' and method 'updateProfile'");
        editProfileActivity.updateProfileBtn = (FancyButton) Utils.castView(findRequiredView, R.id.btn_update_profile, "field 'updateProfileBtn'", FancyButton.class);
        this.view2131361945 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.updateProfile();
            }
        });
        editProfileActivity.dobUpdateLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dob_update_layout, "field 'dobUpdateLayout'", LinearLayout.class);
        editProfileActivity.tvDobVerifyStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dob_verify_status, "field 'tvDobVerifyStatus'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.tv_dob_update, "field 'tvDobUpdate' and method 'dobUpdate'");
        editProfileActivity.tvDobUpdate = (TextView) Utils.castView(findRequiredView, R.id.tv_dob_update, "field 'tvDobUpdate'", TextView.class);
        this.view2131362718 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.dobUpdate();
            }
        });
        editProfileActivity.nationalidUpdateLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.nationalid_update_layout, "field 'nationalidUpdateLayout'", LinearLayout.class);
        editProfileActivity.tvNationalidVerifyStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_nationalid_verify_status, "field 'tvNationalidVerifyStatus'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.tv_nationalid_update, "field 'tvNationalidUpdate' and method 'nationaliIdUpadate'");
        editProfileActivity.tvNationalidUpdate = (LinearLayout) Utils.castView(findRequiredView, R.id.tv_nationalid_update, "field 'tvNationalidUpdate'", LinearLayout.class);
        this.view2131362745 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.nationaliIdUpadate();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                editProfileActivity.clickOnBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        EditProfileActivity editProfileActivity = this.target;
        if (editProfileActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        editProfileActivity.emailEditText = null;
        editProfileActivity.emailVerifyStatusTextView = null;
        editProfileActivity.mobileNumberVerifyStatus = null;
        editProfileActivity.mobileNumberValueTextView = null;
        editProfileActivity.dobValueTextInputLayout = null;
        editProfileActivity.dobValueTextView = null;
        editProfileActivity.maleCheckBox = null;
        editProfileActivity.femaleCheck = null;
        editProfileActivity.nationalityEditText = null;
        editProfileActivity.addressEditText = null;
        editProfileActivity.languagesEditText = null;
        editProfileActivity.aboutmeEdittext = null;
        editProfileActivity.profileNameTextView = null;
        editProfileActivity.profileIv = null;
        editProfileActivity.nationalIdIv = null;
        editProfileActivity.uploadNationalidLayout = null;
        editProfileActivity.updateProfileBtn = null;
        editProfileActivity.dobUpdateLayout = null;
        editProfileActivity.tvDobVerifyStatus = null;
        editProfileActivity.tvDobUpdate = null;
        editProfileActivity.nationalidUpdateLayout = null;
        editProfileActivity.tvNationalidVerifyStatus = null;
        editProfileActivity.tvNationalidUpdate = null;
        this.view2131362724.setOnClickListener(null);
        this.view2131362724 = null;
        this.view2131362720.setOnClickListener(null);
        this.view2131362720 = null;
        this.view2131362145.setOnClickListener(null);
        this.view2131362145 = null;
        this.view2131362142.setOnClickListener(null);
        this.view2131362142 = null;
        this.view2131362248.setOnClickListener(null);
        this.view2131362248 = null;
        this.view2131362240.setOnClickListener(null);
        this.view2131362240 = null;
        this.view2131361945.setOnClickListener(null);
        this.view2131361945 = null;
        this.view2131362718.setOnClickListener(null);
        this.view2131362718 = null;
        this.view2131362745.setOnClickListener(null);
        this.view2131362745 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
