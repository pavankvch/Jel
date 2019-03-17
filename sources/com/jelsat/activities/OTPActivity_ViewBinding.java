package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class OTPActivity_ViewBinding implements Unbinder {
    private OTPActivity target;
    private View view2131361959;
    private View view2131362121;
    private View view2131362179;
    private View view2131362508;
    private View view2131362816;

    @UiThread
    public OTPActivity_ViewBinding(OTPActivity oTPActivity) {
        this(oTPActivity, oTPActivity.getWindow().getDecorView());
    }

    @UiThread
    public OTPActivity_ViewBinding(final OTPActivity oTPActivity, View view) {
        this.target = oTPActivity;
        oTPActivity.mobileNumberEdiText = (EditText) Utils.findRequiredViewAsType(view, R.id.mobile_number_edit_text, "field 'mobileNumberEdiText'", EditText.class);
        oTPActivity.otpEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.otp_editText, "field 'otpEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.verify_otp_button, "field 'verifyOTPButton' and method 'clickOnVerifyButton'");
        oTPActivity.verifyOTPButton = (Button) Utils.castView(findRequiredView, R.id.verify_otp_button, "field 'verifyOTPButton'", Button.class);
        this.view2131362816 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oTPActivity.clickOnVerifyButton(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.flag_imageView, "field 'mCountryFlagImageView' and method 'clickOnFlag'");
        oTPActivity.mCountryFlagImageView = (ImageView) Utils.castView(findRequiredView, R.id.flag_imageView, "field 'mCountryFlagImageView'", ImageView.class);
        this.view2131362179 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oTPActivity.clickOnFlag();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.edit_imageView, "field 'editImageView' and method 'clickOnEditImage'");
        oTPActivity.editImageView = (ImageView) Utils.castView(findRequiredView, R.id.edit_imageView, "field 'editImageView'", ImageView.class);
        this.view2131362121 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oTPActivity.clickOnEditImage(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.cancel_TV, "method 'clickOnCancel'");
        this.view2131361959 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oTPActivity.clickOnCancel(view);
            }
        });
        view = Utils.findRequiredView(view, R.id.resend_TV, "method 'clickOnResend'");
        this.view2131362508 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                oTPActivity.clickOnResend();
            }
        });
    }

    @CallSuper
    public void unbind() {
        OTPActivity oTPActivity = this.target;
        if (oTPActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        oTPActivity.mobileNumberEdiText = null;
        oTPActivity.otpEditText = null;
        oTPActivity.verifyOTPButton = null;
        oTPActivity.mCountryFlagImageView = null;
        oTPActivity.editImageView = null;
        this.view2131362816.setOnClickListener(null);
        this.view2131362816 = null;
        this.view2131362179.setOnClickListener(null);
        this.view2131362179 = null;
        this.view2131362121.setOnClickListener(null);
        this.view2131362121 = null;
        this.view2131361959.setOnClickListener(null);
        this.view2131361959 = null;
        this.view2131362508.setOnClickListener(null);
        this.view2131362508 = null;
    }
}
