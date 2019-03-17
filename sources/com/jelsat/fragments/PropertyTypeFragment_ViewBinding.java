package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class PropertyTypeFragment_ViewBinding implements Unbinder {
    private PropertyTypeFragment target;

    @UiThread
    public PropertyTypeFragment_ViewBinding(PropertyTypeFragment propertyTypeFragment, View view) {
        this.target = propertyTypeFragment;
        propertyTypeFragment.propertyTypeRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_type_recyclerView, "field 'propertyTypeRecyclerView'", RecyclerView.class);
        propertyTypeFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PropertyTypeFragment propertyTypeFragment = this.target;
        if (propertyTypeFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyTypeFragment.propertyTypeRecyclerView = null;
        propertyTypeFragment.noResultTV = null;
    }
}
