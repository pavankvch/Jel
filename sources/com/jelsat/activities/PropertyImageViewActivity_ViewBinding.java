package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class PropertyImageViewActivity_ViewBinding implements Unbinder {
    private PropertyImageViewActivity target;
    private View view2131362017;

    @UiThread
    public PropertyImageViewActivity_ViewBinding(PropertyImageViewActivity propertyImageViewActivity) {
        this(propertyImageViewActivity, propertyImageViewActivity.getWindow().getDecorView());
    }

    @UiThread
    public PropertyImageViewActivity_ViewBinding(final PropertyImageViewActivity propertyImageViewActivity, View view) {
        this.target = propertyImageViewActivity;
        propertyImageViewActivity.noOfPropertyImagesCount = (TextView) Utils.findRequiredViewAsType(view, R.id.no_of_images_count_TV, "field 'noOfPropertyImagesCount'", TextView.class);
        propertyImageViewActivity.propertyImageName = (TextView) Utils.findRequiredViewAsType(view, R.id.image_name, "field 'propertyImageName'", TextView.class);
        propertyImageViewActivity.propertyImageRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_image_recyclerView, "field 'propertyImageRecyclerView'", RecyclerView.class);
        view = Utils.findRequiredView(view, R.id.close_image, "method 'clickOnClose'");
        this.view2131362017 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                propertyImageViewActivity.clickOnClose();
            }
        });
    }

    @CallSuper
    public void unbind() {
        PropertyImageViewActivity propertyImageViewActivity = this.target;
        if (propertyImageViewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyImageViewActivity.noOfPropertyImagesCount = null;
        propertyImageViewActivity.propertyImageName = null;
        propertyImageViewActivity.propertyImageRecyclerView = null;
        this.view2131362017.setOnClickListener(null);
        this.view2131362017 = null;
    }
}
