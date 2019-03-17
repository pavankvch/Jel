package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HostBookingsFragment_ViewBinding implements Unbinder {
    private HostBookingsFragment target;

    @UiThread
    public HostBookingsFragment_ViewBinding(HostBookingsFragment hostBookingsFragment, View view) {
        this.target = hostBookingsFragment;
        hostBookingsFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        hostBookingsFragment.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        HostBookingsFragment hostBookingsFragment = this.target;
        if (hostBookingsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        hostBookingsFragment.recyclerView = null;
        hostBookingsFragment.noResultTextView = null;
    }
}
