package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HelpSupportActivity_ViewBinding implements Unbinder {
    private HelpSupportActivity target;
    private View view2131361892;
    private View view2131361940;
    private View view2131362011;

    @UiThread
    public HelpSupportActivity_ViewBinding(HelpSupportActivity helpSupportActivity) {
        this(helpSupportActivity, helpSupportActivity.getWindow().getDecorView());
    }

    @UiThread
    public HelpSupportActivity_ViewBinding(final HelpSupportActivity helpSupportActivity, View view) {
        this.target = helpSupportActivity;
        helpSupportActivity.headingTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTv'", TextView.class);
        helpSupportActivity.messageEt = (EditText) Utils.findRequiredViewAsType(view, R.id.et_message, "field 'messageEt'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.click_support_mail, "field 'clickSupportMail' and method 'supportMail'");
        helpSupportActivity.clickSupportMail = (TextView) Utils.castView(findRequiredView, R.id.click_support_mail, "field 'clickSupportMail'", TextView.class);
        this.view2131362011 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                helpSupportActivity.supportMail();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'goBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                helpSupportActivity.goBack();
            }
        });
        view = Utils.findRequiredView(view, R.id.btn_send, "method 'submitmessage'");
        this.view2131361940 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                helpSupportActivity.submitmessage();
            }
        });
    }

    @CallSuper
    public void unbind() {
        HelpSupportActivity helpSupportActivity = this.target;
        if (helpSupportActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        helpSupportActivity.headingTv = null;
        helpSupportActivity.messageEt = null;
        helpSupportActivity.clickSupportMail = null;
        this.view2131362011.setOnClickListener(null);
        this.view2131362011 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131361940.setOnClickListener(null);
        this.view2131361940 = null;
    }
}
