package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class InboxNotificationsFragment_ViewBinding implements Unbinder {
    private InboxNotificationsFragment target;

    @UiThread
    public InboxNotificationsFragment_ViewBinding(InboxNotificationsFragment inboxNotificationsFragment, View view) {
        this.target = inboxNotificationsFragment;
        inboxNotificationsFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        inboxNotificationsFragment.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        InboxNotificationsFragment inboxNotificationsFragment = this.target;
        if (inboxNotificationsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        inboxNotificationsFragment.recyclerView = null;
        inboxNotificationsFragment.noResultTextView = null;
    }
}
