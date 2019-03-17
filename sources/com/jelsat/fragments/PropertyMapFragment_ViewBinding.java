package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class PropertyMapFragment_ViewBinding implements Unbinder {
    private PropertyMapFragment target;
    private View view2131361892;

    @UiThread
    public PropertyMapFragment_ViewBinding(final PropertyMapFragment propertyMapFragment, View view) {
        this.target = propertyMapFragment;
        propertyMapFragment.addressApperenceTextview = (TextView) Utils.findRequiredViewAsType(view, R.id.address_apperence_textview, "field 'addressApperenceTextview'", TextView.class);
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBackArrow'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyMapFragment.clickOnBackArrow();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PropertyMapFragment propertyMapFragment = this.target;
        if (propertyMapFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyMapFragment.addressApperenceTextview = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
