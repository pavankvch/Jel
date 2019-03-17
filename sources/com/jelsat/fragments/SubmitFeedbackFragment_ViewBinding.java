package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ExpandableListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class SubmitFeedbackFragment_ViewBinding implements Unbinder {
    private SubmitFeedbackFragment target;

    @UiThread
    public SubmitFeedbackFragment_ViewBinding(SubmitFeedbackFragment submitFeedbackFragment, View view) {
        this.target = submitFeedbackFragment;
        submitFeedbackFragment.expListView = (ExpandableListView) Utils.findRequiredViewAsType(view, R.id.lv_feedback_options, "field 'expListView'", ExpandableListView.class);
    }

    @CallSuper
    public void unbind() {
        SubmitFeedbackFragment submitFeedbackFragment = this.target;
        if (submitFeedbackFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        submitFeedbackFragment.expListView = null;
    }
}
