package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class CancelBookingDialogFragment_ViewBinding implements Unbinder {
    private CancelBookingDialogFragment target;
    private View view2131361933;
    private View view2131361939;

    @UiThread
    public CancelBookingDialogFragment_ViewBinding(final CancelBookingDialogFragment cancelBookingDialogFragment, View view) {
        this.target = cancelBookingDialogFragment;
        cancelBookingDialogFragment.reasonEt = (EditText) Utils.findRequiredViewAsType(view, R.id.et_reason, "field 'reasonEt'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_confirm, "field 'btnConfirm' and method 'clickOnSubmit'");
        cancelBookingDialogFragment.btnConfirm = (FancyButton) Utils.castView(findRequiredView, R.id.btn_confirm, "field 'btnConfirm'", FancyButton.class);
        this.view2131361933 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cancelBookingDialogFragment.clickOnSubmit();
            }
        });
        cancelBookingDialogFragment.cancelationPolicyDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.cancelation_policy_desc, "field 'cancelationPolicyDesc'", TextView.class);
        view = Utils.findRequiredView(view, R.id.btn_reject, "method 'clickOnReject'");
        this.view2131361939 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cancelBookingDialogFragment.clickOnReject();
            }
        });
    }

    @CallSuper
    public void unbind() {
        CancelBookingDialogFragment cancelBookingDialogFragment = this.target;
        if (cancelBookingDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cancelBookingDialogFragment.reasonEt = null;
        cancelBookingDialogFragment.btnConfirm = null;
        cancelBookingDialogFragment.cancelationPolicyDesc = null;
        this.view2131361933.setOnClickListener(null);
        this.view2131361933 = null;
        this.view2131361939.setOnClickListener(null);
        this.view2131361939 = null;
    }
}
