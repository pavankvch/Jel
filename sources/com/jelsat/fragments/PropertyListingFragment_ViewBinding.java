package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class PropertyListingFragment_ViewBinding implements Unbinder {
    private PropertyListingFragment target;
    private View view2131362384;
    private View view2131362517;

    @UiThread
    public PropertyListingFragment_ViewBinding(final PropertyListingFragment propertyListingFragment, View view) {
        this.target = propertyListingFragment;
        propertyListingFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        propertyListingFragment.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        propertyListingFragment.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        propertyListingFragment.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        propertyListingFragment.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        propertyListingFragment.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyListingFragment.clickOnRetryButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.on_addpropert_button, "method 'addProperty'");
        this.view2131362384 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyListingFragment.addProperty();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PropertyListingFragment propertyListingFragment = this.target;
        if (propertyListingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyListingFragment.recyclerView = null;
        propertyListingFragment.swipeContainer = null;
        propertyListingFragment.noResultTV = null;
        propertyListingFragment.noResultLayout = null;
        propertyListingFragment.noResultImage = null;
        propertyListingFragment.retryButton = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131362384.setOnClickListener(null);
        this.view2131362384 = null;
    }
}
