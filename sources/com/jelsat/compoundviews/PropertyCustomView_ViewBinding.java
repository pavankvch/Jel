package com.jelsat.compoundviews;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class PropertyCustomView_ViewBinding implements Unbinder {
    private PropertyCustomView target;

    @UiThread
    public PropertyCustomView_ViewBinding(PropertyCustomView propertyCustomView) {
        this(propertyCustomView, propertyCustomView);
    }

    @UiThread
    public PropertyCustomView_ViewBinding(PropertyCustomView propertyCustomView, View view) {
        this.target = propertyCustomView;
        propertyCustomView.propertyImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.property_IV, "field 'propertyImageView'", ImageView.class);
        propertyCustomView.favoriteImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.favorite_IV, "field 'favoriteImageView'", ImageView.class);
        propertyCustomView.rateTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.rate_TV, "field 'rateTextView'", TextView.class);
        propertyCustomView.propertyNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.property_name_TV, "field 'propertyNameTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        PropertyCustomView propertyCustomView = this.target;
        if (propertyCustomView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        propertyCustomView.propertyImageView = null;
        propertyCustomView.favoriteImageView = null;
        propertyCustomView.rateTextView = null;
        propertyCustomView.propertyNameTextView = null;
    }
}
