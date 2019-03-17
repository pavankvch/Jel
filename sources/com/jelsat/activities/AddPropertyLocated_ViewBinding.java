package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class AddPropertyLocated_ViewBinding implements Unbinder {
    private AddPropertyLocated target;
    private View view2131361852;
    private View view2131361893;
    private View view2131362322;

    @UiThread
    public AddPropertyLocated_ViewBinding(AddPropertyLocated addPropertyLocated) {
        this(addPropertyLocated, addPropertyLocated.getWindow().getDecorView());
    }

    @UiThread
    public AddPropertyLocated_ViewBinding(final AddPropertyLocated addPropertyLocated, View view) {
        this.target = addPropertyLocated;
        View findRequiredView = Utils.findRequiredView(view, R.id.addressLine1, "field 'addressLine1' and method 'clickOnAddress'");
        addPropertyLocated.addressLine1 = (TextView) Utils.castView(findRequiredView, R.id.addressLine1, "field 'addressLine1'", TextView.class);
        this.view2131361852 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyLocated.clickOnAddress();
            }
        });
        addPropertyLocated.landMark = (TextView) Utils.findRequiredViewAsType(view, R.id.land_mark, "field 'landMark'", TextView.class);
        addPropertyLocated.flat_No = (TextView) Utils.findRequiredViewAsType(view, R.id.flat_no, "field 'flat_No'", TextView.class);
        addPropertyLocated.add_your_property = (Button) Utils.findRequiredViewAsType(view, R.id.add_your_property, "field 'add_your_property'", Button.class);
        addPropertyLocated.saveAndExit = (TextView) Utils.findRequiredViewAsType(view, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        addPropertyLocated.mapImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.map_image, "field 'mapImage'", ImageView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.map_image_layout, "field 'mapImageLayout' and method 'clickOnMapLayout'");
        addPropertyLocated.mapImageLayout = (FrameLayout) Utils.castView(findRequiredView, R.id.map_image_layout, "field 'mapImageLayout'", FrameLayout.class);
        this.view2131362322 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyLocated.clickOnMapLayout();
            }
        });
        addPropertyLocated.mapLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.map_layout, "field 'mapLayout'", FrameLayout.class);
        view = Utils.findRequiredView(view, R.id.back_arrow_property, "method 'backButton'");
        this.view2131361893 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyLocated.backButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddPropertyLocated addPropertyLocated = this.target;
        if (addPropertyLocated == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addPropertyLocated.addressLine1 = null;
        addPropertyLocated.landMark = null;
        addPropertyLocated.flat_No = null;
        addPropertyLocated.add_your_property = null;
        addPropertyLocated.saveAndExit = null;
        addPropertyLocated.mapImage = null;
        addPropertyLocated.mapImageLayout = null;
        addPropertyLocated.mapLayout = null;
        this.view2131361852.setOnClickListener(null);
        this.view2131361852 = null;
        this.view2131362322.setOnClickListener(null);
        this.view2131362322 = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
    }
}
