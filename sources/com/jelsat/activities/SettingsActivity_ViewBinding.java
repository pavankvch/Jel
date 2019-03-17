package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.compoundviews.ProfileCompoundView;

public class SettingsActivity_ViewBinding implements Unbinder {
    private SettingsActivity target;
    private View view2131361814;
    private View view2131361823;
    private View view2131361892;
    private View view2131361984;
    private View view2131362217;
    private View view2131362272;
    private View view2131362311;
    private View view2131362434;

    @UiThread
    public SettingsActivity_ViewBinding(SettingsActivity settingsActivity) {
        this(settingsActivity, settingsActivity.getWindow().getDecorView());
    }

    @UiThread
    public SettingsActivity_ViewBinding(final SettingsActivity settingsActivity, View view) {
        this.target = settingsActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.language_view, "field 'languageView' and method 'changeLanguage'");
        settingsActivity.languageView = (ProfileCompoundView) Utils.castView(findRequiredView, R.id.language_view, "field 'languageView'", ProfileCompoundView.class);
        this.view2131362272 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.changeLanguage();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.hostguide_view, "field 'hostGuideView' and method 'clickOnHostGuide'");
        settingsActivity.hostGuideView = (ProfileCompoundView) Utils.castView(findRequiredView, R.id.hostguide_view, "field 'hostGuideView'", ProfileCompoundView.class);
        this.view2131362217 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.clickOnHostGuide();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.changePassword_view, "field 'changePasswordView' and method 'clickOnChangePassword'");
        settingsActivity.changePasswordView = (ProfileCompoundView) Utils.castView(findRequiredView, R.id.changePassword_view, "field 'changePasswordView'", ProfileCompoundView.class);
        this.view2131361984 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.clickOnChangePassword();
            }
        });
        settingsActivity.toolbarTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'toolbarTitle'", TextView.class);
        settingsActivity.versionNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.versionnumber, "field 'versionNumber'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.clickOnBack();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.Terms_view, "method 'termsOfUse'");
        this.view2131361814 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.termsOfUse();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.privacy_view, "method 'privacyPolicy'");
        this.view2131362434 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.privacyPolicy();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.about_view, "method 'aboutUs'");
        this.view2131361823 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.aboutUs();
            }
        });
        view = Utils.findRequiredView(view, R.id.logout_button, "method 'clickOnlogout'");
        this.view2131362311 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingsActivity.clickOnlogout();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SettingsActivity settingsActivity = this.target;
        if (settingsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        settingsActivity.languageView = null;
        settingsActivity.hostGuideView = null;
        settingsActivity.changePasswordView = null;
        settingsActivity.toolbarTitle = null;
        settingsActivity.versionNumber = null;
        this.view2131362272.setOnClickListener(null);
        this.view2131362272 = null;
        this.view2131362217.setOnClickListener(null);
        this.view2131362217 = null;
        this.view2131361984.setOnClickListener(null);
        this.view2131361984 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131361814.setOnClickListener(null);
        this.view2131361814 = null;
        this.view2131362434.setOnClickListener(null);
        this.view2131362434 = null;
        this.view2131361823.setOnClickListener(null);
        this.view2131361823 = null;
        this.view2131362311.setOnClickListener(null);
        this.view2131362311 = null;
    }
}
