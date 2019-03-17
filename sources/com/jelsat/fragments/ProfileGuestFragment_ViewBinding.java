package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ProfileGuestFragment_ViewBinding implements Unbinder {
    private ProfileGuestFragment target;
    private View view2131362167;
    private View view2131362208;
    private View view2131362259;
    private View view2131362586;
    private View view2131362836;

    @UiThread
    public ProfileGuestFragment_ViewBinding(final ProfileGuestFragment profileGuestFragment, View view) {
        this.target = profileGuestFragment;
        View findRequiredView = Utils.findRequiredView(view, R.id.invite_view, "method 'gotoInvite'");
        this.view2131362259 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileGuestFragment.gotoInvite();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.wallet_view, "method 'gotoWallet'");
        this.view2131362836 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileGuestFragment.gotoWallet();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.help_view_guest, "method 'gotoGuestHelp'");
        this.view2131362208 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileGuestFragment.gotoGuestHelp();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.settings_view, "method 'gotoGuestSettings'");
        this.view2131362586 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileGuestFragment.gotoGuestSettings();
            }
        });
        view = Utils.findRequiredView(view, R.id.feedback_view, "method 'gotoFeedback'");
        this.view2131362167 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                profileGuestFragment.gotoFeedback();
            }
        });
    }

    @CallSuper
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131362259.setOnClickListener(null);
        this.view2131362259 = null;
        this.view2131362836.setOnClickListener(null);
        this.view2131362836 = null;
        this.view2131362208.setOnClickListener(null);
        this.view2131362208 = null;
        this.view2131362586.setOnClickListener(null);
        this.view2131362586 = null;
        this.view2131362167.setOnClickListener(null);
        this.view2131362167 = null;
    }
}
