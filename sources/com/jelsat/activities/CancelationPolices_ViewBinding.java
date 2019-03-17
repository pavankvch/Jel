package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class CancelationPolices_ViewBinding implements Unbinder {
    private CancelationPolices target;
    private View view2131361962;
    private View view2131362510;
    private View view2131362535;

    @UiThread
    public CancelationPolices_ViewBinding(CancelationPolices cancelationPolices) {
        this(cancelationPolices, cancelationPolices.getWindow().getDecorView());
    }

    @UiThread
    public CancelationPolices_ViewBinding(final CancelationPolices cancelationPolices, View view) {
        this.target = cancelationPolices;
        cancelationPolices.cancelTypeRecylerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.cancel_type_recyclerView, "field 'cancelTypeRecylerview'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.cancel_cancelPol, "field 'cancelPolicy' and method 'cancelPolicy'");
        cancelationPolices.cancelPolicy = (TextView) Utils.castView(findRequiredView, R.id.cancel_cancelPol, "field 'cancelPolicy'", TextView.class);
        this.view2131361962 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cancelationPolices.cancelPolicy();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.reset_cancelPol, "field 'resetCancelPol' and method 'resetCancelPol'");
        cancelationPolices.resetCancelPol = (TextView) Utils.castView(findRequiredView, R.id.reset_cancelPol, "field 'resetCancelPol'", TextView.class);
        this.view2131362510 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cancelationPolices.resetCancelPol();
            }
        });
        view = Utils.findRequiredView(view, R.id.save_cancelPol, "field 'saveCancelPol' and method 'saveCancelPol'");
        cancelationPolices.saveCancelPol = (Button) Utils.castView(view, R.id.save_cancelPol, "field 'saveCancelPol'", Button.class);
        this.view2131362535 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                cancelationPolices.saveCancelPol();
            }
        });
    }

    @CallSuper
    public void unbind() {
        CancelationPolices cancelationPolices = this.target;
        if (cancelationPolices == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cancelationPolices.cancelTypeRecylerview = null;
        cancelationPolices.cancelPolicy = null;
        cancelationPolices.resetCancelPol = null;
        cancelationPolices.saveCancelPol = null;
        this.view2131361962.setOnClickListener(null);
        this.view2131361962 = null;
        this.view2131362510.setOnClickListener(null);
        this.view2131362510 = null;
        this.view2131362535.setOnClickListener(null);
        this.view2131362535 = null;
    }
}
