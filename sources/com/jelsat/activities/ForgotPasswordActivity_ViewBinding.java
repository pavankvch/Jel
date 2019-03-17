package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ForgotPasswordActivity_ViewBinding implements Unbinder {
    private ForgotPasswordActivity target;
    private View view2131361892;
    private View view2131362179;
    private View view2131362582;

    @UiThread
    public ForgotPasswordActivity_ViewBinding(ForgotPasswordActivity forgotPasswordActivity) {
        this(forgotPasswordActivity, forgotPasswordActivity.getWindow().getDecorView());
    }

    @UiThread
    public ForgotPasswordActivity_ViewBinding(final ForgotPasswordActivity forgotPasswordActivity, View view) {
        this.target = forgotPasswordActivity;
        forgotPasswordActivity.emailOrPhoneNumberEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.email_or_phonenumber_EditText, "field 'emailOrPhoneNumberEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.flag_imageView, "field 'mCountryFlagImageView' and method 'clickOnFlagImageView'");
        forgotPasswordActivity.mCountryFlagImageView = (ImageView) Utils.castView(findRequiredView, R.id.flag_imageView, "field 'mCountryFlagImageView'", ImageView.class);
        this.view2131362179 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordActivity.clickOnFlagImageView(view);
            }
        });
        forgotPasswordActivity.emailOrPhoneLineView = Utils.findRequiredView(view, R.id.emai_phone_line_view, "field 'emailOrPhoneLineView'");
        forgotPasswordActivity.emailMobileError = (TextView) Utils.findRequiredViewAsType(view, R.id.email_mobile_number_error, "field 'emailMobileError'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBackArrow'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordActivity.clickOnBackArrow(view);
            }
        });
        view = Utils.findRequiredView(view, R.id.send_otp_button, "method 'clickOnSendOTPButton'");
        this.view2131362582 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                forgotPasswordActivity.clickOnSendOTPButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ForgotPasswordActivity forgotPasswordActivity = this.target;
        if (forgotPasswordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        forgotPasswordActivity.emailOrPhoneNumberEditText = null;
        forgotPasswordActivity.mCountryFlagImageView = null;
        forgotPasswordActivity.emailOrPhoneLineView = null;
        forgotPasswordActivity.emailMobileError = null;
        this.view2131362179.setOnClickListener(null);
        this.view2131362179 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362582.setOnClickListener(null);
        this.view2131362582 = null;
    }
}
