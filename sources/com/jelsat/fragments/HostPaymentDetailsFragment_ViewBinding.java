package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HostPaymentDetailsFragment_ViewBinding implements Unbinder {
    private HostPaymentDetailsFragment target;

    @UiThread
    public HostPaymentDetailsFragment_ViewBinding(HostPaymentDetailsFragment hostPaymentDetailsFragment, View view) {
        this.target = hostPaymentDetailsFragment;
        hostPaymentDetailsFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        hostPaymentDetailsFragment.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        HostPaymentDetailsFragment hostPaymentDetailsFragment = this.target;
        if (hostPaymentDetailsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hostPaymentDetailsFragment.recyclerView = null;
        hostPaymentDetailsFragment.noResultTextView = null;
    }
}
