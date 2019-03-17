package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomEditText;

public class CreatePasswordActivity_ViewBinding implements Unbinder {
    private CreatePasswordActivity target;
    private View view2131361892;
    private View view2131362533;
    private View view2131362673;

    @UiThread
    public CreatePasswordActivity_ViewBinding(CreatePasswordActivity createPasswordActivity) {
        this(createPasswordActivity, createPasswordActivity.getWindow().getDecorView());
    }

    @UiThread
    public CreatePasswordActivity_ViewBinding(final CreatePasswordActivity createPasswordActivity, View view) {
        this.target = createPasswordActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.toggle_text, "field 'toggleText' and method 'clickOnToggleText'");
        createPasswordActivity.toggleText = (TextView) Utils.castView(findRequiredView, R.id.toggle_text, "field 'toggleText'", TextView.class);
        this.view2131362673 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createPasswordActivity.clickOnToggleText(view);
            }
        });
        createPasswordActivity.passwordEditText = (CustomEditText) Utils.findRequiredViewAsType(view, R.id.password_editText, "field 'passwordEditText'", CustomEditText.class);
        createPasswordActivity.createPasswordWrapper = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.create_password_Wrapper, "field 'createPasswordWrapper'", TextInputLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBackArrow'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createPasswordActivity.clickOnBackArrow(view);
            }
        });
        view = Utils.findRequiredView(view, R.id.save_button, "method 'clickOnSave'");
        this.view2131362533 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                createPasswordActivity.clickOnSave();
            }
        });
    }

    @CallSuper
    public void unbind() {
        CreatePasswordActivity createPasswordActivity = this.target;
        if (createPasswordActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        createPasswordActivity.toggleText = null;
        createPasswordActivity.passwordEditText = null;
        createPasswordActivity.createPasswordWrapper = null;
        this.view2131362673.setOnClickListener(null);
        this.view2131362673 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362533.setOnClickListener(null);
        this.view2131362533 = null;
    }
}
