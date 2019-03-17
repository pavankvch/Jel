package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ChangePasswordActivity_ViewBinding implements Unbinder {
    private ChangePasswordActivity target;
    private View view2131361892;
    private View view2131361944;
    private View view2131362674;
    private View view2131362675;

    @UiThread
    public ChangePasswordActivity_ViewBinding(ChangePasswordActivity changePasswordActivity) {
        this(changePasswordActivity, changePasswordActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChangePasswordActivity_ViewBinding(final ChangePasswordActivity changePasswordActivity, View view) {
        this.target = changePasswordActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.toggle_text_old_pwd, "field 'tvOldPassword' and method 'clickOnToggleText1'");
        changePasswordActivity.tvOldPassword = (TextView) Utils.castView(findRequiredView, R.id.toggle_text_old_pwd, "field 'tvOldPassword'", TextView.class);
        this.view2131362675 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordActivity.clickOnToggleText1(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.toggle_text_new_pwd, "field 'tvNewPassword' and method 'clickOnToggleText2'");
        changePasswordActivity.tvNewPassword = (TextView) Utils.castView(findRequiredView, R.id.toggle_text_new_pwd, "field 'tvNewPassword'", TextView.class);
        this.view2131362674 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordActivity.clickOnToggleText2(view);
            }
        });
        changePasswordActivity.etOldPassword = (EditText) Utils.findRequiredViewAsType(view, R.id.old_password_editText, "field 'etOldPassword'", EditText.class);
        changePasswordActivity.etNewPassword = (EditText) Utils.findRequiredViewAsType(view, R.id.new_password_editText, "field 'etNewPassword'", EditText.class);
        changePasswordActivity.oldPasswordTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.old_password_text_input_layout, "field 'oldPasswordTextInputLayout'", TextInputLayout.class);
        changePasswordActivity.newPasswordTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.new_password_text_input_layout, "field 'newPasswordTextInputLayout'", TextInputLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.btn_update, "method 'updatePassword'");
        this.view2131361944 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordActivity.updatePassword();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                changePasswordActivity.clickOnBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ChangePasswordActivity changePasswordActivity = this.target;
        if (changePasswordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        changePasswordActivity.tvOldPassword = null;
        changePasswordActivity.tvNewPassword = null;
        changePasswordActivity.etOldPassword = null;
        changePasswordActivity.etNewPassword = null;
        changePasswordActivity.oldPasswordTextInputLayout = null;
        changePasswordActivity.newPasswordTextInputLayout = null;
        this.view2131362675.setOnClickListener(null);
        this.view2131362675 = null;
        this.view2131362674.setOnClickListener(null);
        this.view2131362674 = null;
        this.view2131361944.setOnClickListener(null);
        this.view2131361944 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
