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

public class HomeActivity_ViewBinding implements Unbinder {
    private HomeActivity target;
    private View view2131362011;
    private View view2131362158;
    private View view2131362195;
    private View view2131362272;
    private View view2131362599;
    private View view2131362814;

    @UiThread
    public HomeActivity_ViewBinding(HomeActivity homeActivity) {
        this(homeActivity, homeActivity.getWindow().getDecorView());
    }

    @UiThread
    public HomeActivity_ViewBinding(final HomeActivity homeActivity, View view) {
        this.target = homeActivity;
        homeActivity.socialButtonsLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.social_buttons_layout, "field 'socialButtonsLayout'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.user_signin_tv, "field 'userSignInTv' and method 'clickOnUserSignIn'");
        homeActivity.userSignInTv = (TextView) Utils.castView(findRequiredView, R.id.user_signin_tv, "field 'userSignInTv'", TextView.class);
        this.view2131362814 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeActivity.clickOnUserSignIn();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.facebook_button, "field 'facebookButton' and method 'clickOnFacebookButton'");
        homeActivity.facebookButton = (TextView) Utils.castView(findRequiredView, R.id.facebook_button, "field 'facebookButton'", TextView.class);
        this.view2131362158 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeActivity.clickOnFacebookButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.google_button, "field 'googleButton' and method 'clickOnGoogleSignIn'");
        homeActivity.googleButton = (TextView) Utils.castView(findRequiredView, R.id.google_button, "field 'googleButton'", TextView.class);
        this.view2131362195 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeActivity.clickOnGoogleSignIn(view);
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.skip_tv, "field 'skipTv' and method 'clickOnSkip'");
        homeActivity.skipTv = (TextView) Utils.castView(findRequiredView, R.id.skip_tv, "field 'skipTv'", TextView.class);
        this.view2131362599 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeActivity.clickOnSkip();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.language_view, "field 'languageTv' and method 'changeLanguage'");
        homeActivity.languageTv = (TextView) Utils.castView(findRequiredView, R.id.language_view, "field 'languageTv'", TextView.class);
        this.view2131362272 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeActivity.changeLanguage();
            }
        });
        view = Utils.findRequiredView(view, R.id.click_support_mail, "method 'clickOnSupportMail'");
        this.view2131362011 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                homeActivity.clickOnSupportMail();
            }
        });
    }

    @CallSuper
    public void unbind() {
        HomeActivity homeActivity = this.target;
        if (homeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeActivity.socialButtonsLayout = null;
        homeActivity.userSignInTv = null;
        homeActivity.facebookButton = null;
        homeActivity.googleButton = null;
        homeActivity.skipTv = null;
        homeActivity.languageTv = null;
        this.view2131362814.setOnClickListener(null);
        this.view2131362814 = null;
        this.view2131362158.setOnClickListener(null);
        this.view2131362158 = null;
        this.view2131362195.setOnClickListener(null);
        this.view2131362195 = null;
        this.view2131362599.setOnClickListener(null);
        this.view2131362599 = null;
        this.view2131362272.setOnClickListener(null);
        this.view2131362272 = null;
        this.view2131362011.setOnClickListener(null);
        this.view2131362011 = null;
    }
}
