package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class AmenitiesActivity_ViewBinding implements Unbinder {
    private AmenitiesActivity target;
    private View view2131361893;
    private View view2131362435;
    private View view2131362531;

    @UiThread
    public AmenitiesActivity_ViewBinding(AmenitiesActivity amenitiesActivity) {
        this(amenitiesActivity, amenitiesActivity.getWindow().getDecorView());
    }

    @UiThread
    public AmenitiesActivity_ViewBinding(final AmenitiesActivity amenitiesActivity, View view) {
        this.target = amenitiesActivity;
        amenitiesActivity.amenityRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.amenity_recyclerView, "field 'amenityRecyclerview'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.products_list_next, "field 'aminetyNextButton' and method 'ametiesNextbutton'");
        amenitiesActivity.aminetyNextButton = (Button) Utils.castView(findRequiredView, R.id.products_list_next, "field 'aminetyNextButton'", Button.class);
        this.view2131362435 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                amenitiesActivity.ametiesNextbutton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'backArrowProperty' and method 'backbutton'");
        amenitiesActivity.backArrowProperty = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'backArrowProperty'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                amenitiesActivity.backbutton();
            }
        });
        view = Utils.findRequiredView(view, R.id.saveAndExit, "field 'saveAndExit' and method 'saveAndExit'");
        amenitiesActivity.saveAndExit = (TextView) Utils.castView(view, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        this.view2131362531 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                amenitiesActivity.saveAndExit();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AmenitiesActivity amenitiesActivity = this.target;
        if (amenitiesActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        amenitiesActivity.amenityRecyclerview = null;
        amenitiesActivity.aminetyNextButton = null;
        amenitiesActivity.backArrowProperty = null;
        amenitiesActivity.saveAndExit = null;
        this.view2131362435.setOnClickListener(null);
        this.view2131362435 = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
    }
}
