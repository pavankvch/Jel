package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class SearchPropertyListFragment_ViewBinding implements Unbinder {
    private SearchPropertyListFragment target;

    @UiThread
    public SearchPropertyListFragment_ViewBinding(SearchPropertyListFragment searchPropertyListFragment, View view) {
        this.target = searchPropertyListFragment;
        searchPropertyListFragment.propertyRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_recyclerview, "field 'propertyRecyclerView'", RecyclerView.class);
        searchPropertyListFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        searchPropertyListFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        searchPropertyListFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        SearchPropertyListFragment searchPropertyListFragment = this.target;
        if (searchPropertyListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        searchPropertyListFragment.propertyRecyclerView = null;
        searchPropertyListFragment.noResultLayout = null;
        searchPropertyListFragment.noResultImage = null;
        searchPropertyListFragment.noResultTV = null;
    }
}
