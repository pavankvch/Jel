package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class PaymentHistoryFragment_ViewBinding implements Unbinder {
    private PaymentHistoryFragment target;

    @UiThread
    public PaymentHistoryFragment_ViewBinding(PaymentHistoryFragment paymentHistoryFragment, View view) {
        this.target = paymentHistoryFragment;
        paymentHistoryFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        paymentHistoryFragment.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PaymentHistoryFragment paymentHistoryFragment = this.target;
        if (paymentHistoryFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        paymentHistoryFragment.recyclerView = null;
        paymentHistoryFragment.noResultTextView = null;
    }
}
