package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ExpandableListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HelpFragment_ViewBinding implements Unbinder {
    private HelpFragment target;

    @UiThread
    public HelpFragment_ViewBinding(HelpFragment helpFragment, View view) {
        this.target = helpFragment;
        helpFragment.faqElv = (ExpandableListView) Utils.findRequiredViewAsType(view, R.id.elv_faq, "field 'faqElv'", ExpandableListView.class);
    }

    @CallSuper
    public void unbind() {
        HelpFragment helpFragment = this.target;
        if (helpFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        helpFragment.faqElv = null;
    }
}
