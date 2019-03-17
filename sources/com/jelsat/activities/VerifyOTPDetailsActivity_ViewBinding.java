package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class VerifyOTPDetailsActivity_ViewBinding implements Unbinder {
    private VerifyOTPDetailsActivity target;
    private View view2131361892;
    private View view2131362508;
    private View view2131362816;

    @UiThread
    public VerifyOTPDetailsActivity_ViewBinding(VerifyOTPDetailsActivity verifyOTPDetailsActivity) {
        this(verifyOTPDetailsActivity, verifyOTPDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public VerifyOTPDetailsActivity_ViewBinding(final VerifyOTPDetailsActivity verifyOTPDetailsActivity, View view) {
        this.target = verifyOTPDetailsActivity;
        verifyOTPDetailsActivity.mobileNumberTV = (TextView) Utils.findRequiredViewAsType(view, R.id.mobile_number_TV, "field 'mobileNumberTV'", TextView.class);
        verifyOTPDetailsActivity.otpEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.otp_editText, "field 'otpEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.verify_otp_button, "field 'verifyOTPButton' and method 'clickOnVerifyOTP'");
        verifyOTPDetailsActivity.verifyOTPButton = (LinearLayout) Utils.castView(findRequiredView, R.id.verify_otp_button, "field 'verifyOTPButton'", LinearLayout.class);
        this.view2131362816 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                verifyOTPDetailsActivity.clickOnVerifyOTP(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBackArrow'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                verifyOTPDetailsActivity.clickOnBackArrow(view);
            }
        });
        view = Utils.findRequiredView(view, R.id.resend_TV, "method 'clickOnResend'");
        this.view2131362508 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                verifyOTPDetailsActivity.clickOnResend();
            }
        });
    }

    @CallSuper
    public void unbind() {
        VerifyOTPDetailsActivity verifyOTPDetailsActivity = this.target;
        if (verifyOTPDetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        verifyOTPDetailsActivity.mobileNumberTV = null;
        verifyOTPDetailsActivity.otpEditText = null;
        verifyOTPDetailsActivity.verifyOTPButton = null;
        this.view2131362816.setOnClickListener(null);
        this.view2131362816 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362508.setOnClickListener(null);
        this.view2131362508 = null;
    }
}
