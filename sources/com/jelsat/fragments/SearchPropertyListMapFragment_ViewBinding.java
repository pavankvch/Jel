package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class SearchPropertyListMapFragment_ViewBinding implements Unbinder {
    private SearchPropertyListMapFragment target;

    @UiThread
    public SearchPropertyListMapFragment_ViewBinding(SearchPropertyListMapFragment searchPropertyListMapFragment, View view) {
        this.target = searchPropertyListMapFragment;
        searchPropertyListMapFragment.mapPropertyRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.map_property_recyclerView, "field 'mapPropertyRecyclerView'", RecyclerView.class);
        searchPropertyListMapFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SearchPropertyListMapFragment searchPropertyListMapFragment = this.target;
        if (searchPropertyListMapFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchPropertyListMapFragment.mapPropertyRecyclerView = null;
        searchPropertyListMapFragment.noResultTV = null;
    }
}
