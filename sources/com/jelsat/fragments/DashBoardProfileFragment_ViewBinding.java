package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class DashBoardProfileFragment_ViewBinding implements Unbinder {
    private DashBoardProfileFragment target;
    private View view2131361934;
    private View view2131361935;
    private View view2131362722;
    private View view2131362763;

    @UiThread
    public DashBoardProfileFragment_ViewBinding(final DashBoardProfileFragment dashBoardProfileFragment, View view) {
        this.target = dashBoardProfileFragment;
        dashBoardProfileFragment.profileImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_profile, "field 'profileImg'", ImageView.class);
        dashBoardProfileFragment.emailNotVerifiedTV = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_email_not_verified, "field 'emailNotVerifiedTV'", TextView.class);
        dashBoardProfileFragment.profileNameTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_profile_name, "field 'profileNameTv'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_host, "field 'btnHost' and method 'clickOnHost'");
        dashBoardProfileFragment.btnHost = (RadioButton) Utils.castView(findRequiredView, R.id.btn_host, "field 'btnHost'", RadioButton.class);
        this.view2131361935 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashBoardProfileFragment.clickOnHost();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.btn_guest, "field 'btnGuest' and method 'clickOnGuest'");
        dashBoardProfileFragment.btnGuest = (RadioButton) Utils.castView(findRequiredView, R.id.btn_guest, "field 'btnGuest'", RadioButton.class);
        this.view2131361934 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashBoardProfileFragment.clickOnGuest();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tv_publicProfile, "method 'gotoPublicProfile'");
        this.view2131362763 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashBoardProfileFragment.gotoPublicProfile();
            }
        });
        view = Utils.findRequiredView(view, R.id.tv_editprofile, "method 'gotoEditProfileScreen'");
        this.view2131362722 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                dashBoardProfileFragment.gotoEditProfileScreen();
            }
        });
    }

    @CallSuper
    public void unbind() {
        DashBoardProfileFragment dashBoardProfileFragment = this.target;
        if (dashBoardProfileFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dashBoardProfileFragment.profileImg = null;
        dashBoardProfileFragment.emailNotVerifiedTV = null;
        dashBoardProfileFragment.profileNameTv = null;
        dashBoardProfileFragment.btnHost = null;
        dashBoardProfileFragment.btnGuest = null;
        this.view2131361935.setOnClickListener(null);
        this.view2131361935 = null;
        this.view2131361934.setOnClickListener(null);
        this.view2131361934 = null;
        this.view2131362763.setOnClickListener(null);
        this.view2131362763 = null;
        this.view2131362722.setOnClickListener(null);
        this.view2131362722 = null;
    }
}
