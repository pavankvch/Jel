package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ProfileHostFragment_ViewBinding implements Unbinder {
    private ProfileHostFragment target;
    private View view2131362209;
    private View view2131362216;
    private View view2131362821;
    private View view2131362822;
    private View view2131362824;
    private View view2131362825;
    private View view2131362829;

    @UiThread
    public ProfileHostFragment_ViewBinding(final ProfileHostFragment profileHostFragment, View view) {
        this.target = profileHostFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.view_addproperty, "method 'clickOnAddProperty'");
        this.view2131362821 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileHostFragment.clickOnAddProperty();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.view_calender, "method 'clickOnCalendar'");
        this.view2131362824 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileHostFragment.clickOnCalendar();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.view_bankdetails, "method 'gotoBankDetails'");
        this.view2131362822 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileHostFragment.gotoBankDetails();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.view_settings, "method 'gotoHostSettings'");
        this.view2131362829 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileHostFragment.gotoHostSettings();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.view_feedback, "method 'gotoHostFeedback'");
        this.view2131362825 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileHostFragment.gotoHostFeedback();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.help_view_host, "method 'gotoHostHelp'");
        this.view2131362209 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileHostFragment.gotoHostHelp();
            }
        });
        view = Utils.findRequiredView(view, R.id.host_invite_view, "method 'gotoHostInvite'");
        this.view2131362216 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileHostFragment.gotoHostInvite();
            }
        });
    }

    @CallSuper
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131362821.setOnClickListener(null);
        this.view2131362821 = null;
        this.view2131362824.setOnClickListener(null);
        this.view2131362824 = null;
        this.view2131362822.setOnClickListener(null);
        this.view2131362822 = null;
        this.view2131362829.setOnClickListener(null);
        this.view2131362829 = null;
        this.view2131362825.setOnClickListener(null);
        this.view2131362825 = null;
        this.view2131362209.setOnClickListener(null);
        this.view2131362209 = null;
        this.view2131362216.setOnClickListener(null);
        this.view2131362216 = null;
    }
}
