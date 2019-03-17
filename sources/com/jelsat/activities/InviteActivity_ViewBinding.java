package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class InviteActivity_ViewBinding implements Unbinder {
    private InviteActivity target;
    private View view2131361892;
    private View view2131362263;
    private View view2131362264;
    private View view2131362265;
    private View view2131362266;
    private View view2131362299;
    private View view2131362711;

    @UiThread
    public InviteActivity_ViewBinding(InviteActivity inviteActivity) {
        this(inviteActivity, inviteActivity.getWindow().getDecorView());
    }

    @UiThread
    public InviteActivity_ViewBinding(final InviteActivity inviteActivity, View view) {
        this.target = inviteActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.tv_code, "field 'codeTextView' and method 'clickToCopy'");
        inviteActivity.codeTextView = (FancyButton) Utils.castView(findRequiredView, R.id.tv_code, "field 'codeTextView'", FancyButton.class);
        this.view2131362711 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteActivity.clickToCopy();
            }
        });
        inviteActivity.referalBalanaceContentLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.referal_balance_content_layout, "field 'referalBalanaceContentLayout'", LinearLayout.class);
        inviteActivity.withoutRefferalAmountDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.without_refferal_amount_desc, "field 'withoutRefferalAmountDesc'", TextView.class);
        inviteActivity.getRefferalAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.get_refferal_amount, "field 'getRefferalAmount'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteActivity.clickOnBack();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.link_how_it_works, "method 'clickOnLink'");
        this.view2131362299 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteActivity.clickOnLink();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.iv_share, "method 'clickOnShareProperty'");
        this.view2131362263 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteActivity.clickOnShareProperty();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.iv_share_whatsapp, "method 'clickOnwhatsappIV'");
        this.view2131362266 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteActivity.clickOnwhatsappIV();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.iv_share_fb, "method 'clickOnFacebookIV'");
        this.view2131362264 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteActivity.clickOnFacebookIV();
            }
        });
        view = Utils.findRequiredView(view, R.id.iv_share_gmail, "method 'clickOnGmailIV'");
        this.view2131362265 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                inviteActivity.clickOnGmailIV();
            }
        });
    }

    @CallSuper
    public void unbind() {
        InviteActivity inviteActivity = this.target;
        if (inviteActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        inviteActivity.codeTextView = null;
        inviteActivity.referalBalanaceContentLayout = null;
        inviteActivity.withoutRefferalAmountDesc = null;
        inviteActivity.getRefferalAmount = null;
        this.view2131362711.setOnClickListener(null);
        this.view2131362711 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362299.setOnClickListener(null);
        this.view2131362299 = null;
        this.view2131362263.setOnClickListener(null);
        this.view2131362263 = null;
        this.view2131362266.setOnClickListener(null);
        this.view2131362266 = null;
        this.view2131362264.setOnClickListener(null);
        this.view2131362264 = null;
        this.view2131362265.setOnClickListener(null);
        this.view2131362265 = null;
    }
}
