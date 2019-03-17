package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class BottomSheetFragment_ViewBinding implements Unbinder {
    private BottomSheetFragment target;

    @UiThread
    public BottomSheetFragment_ViewBinding(BottomSheetFragment bottomSheetFragment, View view) {
        this.target = bottomSheetFragment;
        bottomSheetFragment.textView = (TextView) Utils.findRequiredViewAsType(view, R.id.textView, "field 'textView'", TextView.class);
        bottomSheetFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        BottomSheetFragment bottomSheetFragment = this.target;
        if (bottomSheetFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bottomSheetFragment.textView = null;
        bottomSheetFragment.recyclerView = null;
    }
}
