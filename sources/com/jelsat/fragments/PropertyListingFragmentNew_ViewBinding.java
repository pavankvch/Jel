package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class PropertyListingFragmentNew_ViewBinding implements Unbinder {
    private PropertyListingFragmentNew target;
    private View view2131362384;

    @UiThread
    public PropertyListingFragmentNew_ViewBinding(final PropertyListingFragmentNew propertyListingFragmentNew, View view) {
        this.target = propertyListingFragmentNew;
        propertyListingFragmentNew.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        propertyListingFragmentNew.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        propertyListingFragmentNew.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTextView'", TextView.class);
        view = Utils.findRequiredView(view, R.id.on_addpropert_button, "method 'addProperty'");
        this.view2131362384 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyListingFragmentNew.addProperty();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PropertyListingFragmentNew propertyListingFragmentNew = this.target;
        if (propertyListingFragmentNew == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyListingFragmentNew.recyclerView = null;
        propertyListingFragmentNew.swipeContainer = null;
        propertyListingFragmentNew.noResultTextView = null;
        this.view2131362384.setOnClickListener(null);
        this.view2131362384 = null;
    }
}
