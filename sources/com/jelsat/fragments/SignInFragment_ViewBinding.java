package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class SignInFragment_ViewBinding implements Unbinder {
    private SignInFragment target;
    private View view2131362179;
    private View view2131362184;
    private View view2131362593;
    private View view2131362673;

    @UiThread
    public SignInFragment_ViewBinding(final SignInFragment signInFragment, View view) {
        this.target = signInFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.toggle_text, "field 'toggleText' and method 'clickOnToggleText'");
        signInFragment.toggleText = (TextView) Utils.castView(findRequiredView, R.id.toggle_text, "field 'toggleText'", TextView.class);
        this.view2131362673 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signInFragment.clickOnToggleText();
            }
        });
        signInFragment.passwordTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.password_text_input_layout, "field 'passwordTextInputLayout'", TextInputLayout.class);
        signInFragment.passwordEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.password_editText, "field 'passwordEditText'", EditText.class);
        signInFragment.emailOrPhoneNumberEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.email_or_phonenumber_EditText, "field 'emailOrPhoneNumberEditText'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.flag_imageView, "field 'mCountryFlagImageView' and method 'clickOnFlagImageView'");
        signInFragment.mCountryFlagImageView = (ImageView) Utils.castView(findRequiredView, R.id.flag_imageView, "field 'mCountryFlagImageView'", ImageView.class);
        this.view2131362179 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signInFragment.clickOnFlagImageView();
            }
        });
        signInFragment.emailOrPhoneLineView = Utils.findRequiredView(view, R.id.emai_phone_line_view, "field 'emailOrPhoneLineView'");
        signInFragment.emailMobileError = (TextView) Utils.findRequiredViewAsType(view, R.id.email_mobile_number_error, "field 'emailMobileError'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.sign_in_button, "method 'clickOnSignInButton'");
        this.view2131362593 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signInFragment.clickOnSignInButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.forgot_password, "method 'clickOnForgotPassword'");
        this.view2131362184 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                signInFragment.clickOnForgotPassword();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SignInFragment signInFragment = this.target;
        if (signInFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        signInFragment.toggleText = null;
        signInFragment.passwordTextInputLayout = null;
        signInFragment.passwordEditText = null;
        signInFragment.emailOrPhoneNumberEditText = null;
        signInFragment.mCountryFlagImageView = null;
        signInFragment.emailOrPhoneLineView = null;
        signInFragment.emailMobileError = null;
        this.view2131362673.setOnClickListener(null);
        this.view2131362673 = null;
        this.view2131362179.setOnClickListener(null);
        this.view2131362179 = null;
        this.view2131362593.setOnClickListener(null);
        this.view2131362593 = null;
        this.view2131362184.setOnClickListener(null);
        this.view2131362184 = null;
    }
}
