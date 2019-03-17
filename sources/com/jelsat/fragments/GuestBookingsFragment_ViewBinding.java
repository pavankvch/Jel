package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class GuestBookingsFragment_ViewBinding implements Unbinder {
    private GuestBookingsFragment target;

    @UiThread
    public GuestBookingsFragment_ViewBinding(GuestBookingsFragment guestBookingsFragment, View view) {
        this.target = guestBookingsFragment;
        guestBookingsFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        guestBookingsFragment.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        GuestBookingsFragment guestBookingsFragment = this.target;
        if (guestBookingsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        guestBookingsFragment.recyclerView = null;
        guestBookingsFragment.noResultTextView = null;
    }
}
