package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class AmenitiesDialogFragment_ViewBinding implements Unbinder {
    private AmenitiesDialogFragment target;
    private View view2131362016;

    @UiThread
    public AmenitiesDialogFragment_ViewBinding(final AmenitiesDialogFragment amenitiesDialogFragment, View view) {
        this.target = amenitiesDialogFragment;
        amenitiesDialogFragment.amenityRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.amenities_recyclerView, "field 'amenityRecyclerView'", RecyclerView.class);
        view = Utils.findRequiredView(view, R.id.close_TV, "method 'clickOnClose'");
        this.view2131362016 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                amenitiesDialogFragment.clickOnClose();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AmenitiesDialogFragment amenitiesDialogFragment = this.target;
        if (amenitiesDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        amenitiesDialogFragment.amenityRecyclerView = null;
        this.view2131362016.setOnClickListener(null);
        this.view2131362016 = null;
    }
}
