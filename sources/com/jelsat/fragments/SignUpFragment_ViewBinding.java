package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomEditText;

public class SignUpFragment_ViewBinding implements Unbinder {
    private SignUpFragment target;
    private View view2131362179;
    private View view2131362434;
    private View view2131362592;
    private View view2131362649;
    private View view2131362673;

    @UiThread
    public SignUpFragment_ViewBinding(final SignUpFragment signUpFragment, View view) {
        this.target = signUpFragment;
        signUpFragment.firstNameEditText = (CustomEditText) Utils.findRequiredViewAsType(view, R.id.firstname_edittext, "field 'firstNameEditText'", CustomEditText.class);
        signUpFragment.emailEditText = (CustomEditText) Utils.findRequiredViewAsType(view, R.id.email_edittext, "field 'emailEditText'", CustomEditText.class);
        signUpFragment.mobileEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.mobile_edittext, "field 'mobileEditText'", EditText.class);
        signUpFragment.mobileNumberError = (TextView) Utils.findRequiredViewAsType(view, R.id.mobile_number_error, "field 'mobileNumberError'", TextView.class);
        signUpFragment.passwordLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.password_layout, "field 'passwordLayout'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.flag_imageView, "field 'mCountryFlagImageView' and method 'clickOnFlagImageView'");
        signUpFragment.mCountryFlagImageView = (ImageView) Utils.castView(findRequiredView, R.id.flag_imageView, "field 'mCountryFlagImageView'", ImageView.class);
        this.view2131362179 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signUpFragment.clickOnFlagImageView();
            }
        });
        signUpFragment.passwordEditText = (CustomEditText) Utils.findRequiredViewAsType(view, R.id.password_editText, "field 'passwordEditText'", CustomEditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.toggle_text, "field 'toggleText' and method 'clickOnToggleText'");
        signUpFragment.toggleText = (TextView) Utils.castView(findRequiredView, R.id.toggle_text, "field 'toggleText'", TextView.class);
        this.view2131362673 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signUpFragment.clickOnToggleText();
            }
        });
        signUpFragment.referralCodeEditText = (CustomEditText) Utils.findRequiredViewAsType(view, R.id.referral_code, "field 'referralCodeEditText'", CustomEditText.class);
        signUpFragment.firstnameTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.firstname_text_input_layout, "field 'firstnameTextInputLayout'", TextInputLayout.class);
        signUpFragment.emailTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.email_text_input_layout, "field 'emailTextInputLayout'", TextInputLayout.class);
        signUpFragment.passwordTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.password_text_input_layout, "field 'passwordTextInputLayout'", TextInputLayout.class);
        signUpFragment.referralTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.referral_text_input_layout, "field 'referralTextInputLayout'", TextInputLayout.class);
        signUpFragment.mobileLineView = Utils.findRequiredView(view, R.id.mobile_line, "field 'mobileLineView'");
        findRequiredView = Utils.findRequiredView(view, R.id.terms_text, "method 'termsText'");
        this.view2131362649 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signUpFragment.termsText();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.privacy_view, "method 'privacyPolicy'");
        this.view2131362434 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signUpFragment.privacyPolicy();
            }
        });
        view = Utils.findRequiredView(view, R.id.signUp_button, "method 'clickOnSignUpButton'");
        this.view2131362592 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signUpFragment.clickOnSignUpButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SignUpFragment signUpFragment = this.target;
        if (signUpFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        signUpFragment.firstNameEditText = null;
        signUpFragment.emailEditText = null;
        signUpFragment.mobileEditText = null;
        signUpFragment.mobileNumberError = null;
        signUpFragment.passwordLayout = null;
        signUpFragment.mCountryFlagImageView = null;
        signUpFragment.passwordEditText = null;
        signUpFragment.toggleText = null;
        signUpFragment.referralCodeEditText = null;
        signUpFragment.firstnameTextInputLayout = null;
        signUpFragment.emailTextInputLayout = null;
        signUpFragment.passwordTextInputLayout = null;
        signUpFragment.referralTextInputLayout = null;
        signUpFragment.mobileLineView = null;
        this.view2131362179.setOnClickListener(null);
        this.view2131362179 = null;
        this.view2131362673.setOnClickListener(null);
        this.view2131362673 = null;
        this.view2131362649.setOnClickListener(null);
        this.view2131362649 = null;
        this.view2131362434.setOnClickListener(null);
        this.view2131362434 = null;
        this.view2131362592.setOnClickListener(null);
        this.view2131362592 = null;
    }
}
