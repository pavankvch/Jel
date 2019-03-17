package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class InboxMessagesFragment_ViewBinding implements Unbinder {
    private InboxMessagesFragment target;

    @UiThread
    public InboxMessagesFragment_ViewBinding(InboxMessagesFragment inboxMessagesFragment, View view) {
        this.target = inboxMessagesFragment;
        inboxMessagesFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        inboxMessagesFragment.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        InboxMessagesFragment inboxMessagesFragment = this.target;
        if (inboxMessagesFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        inboxMessagesFragment.recyclerView = null;
        inboxMessagesFragment.noResultTextView = null;
    }
}
