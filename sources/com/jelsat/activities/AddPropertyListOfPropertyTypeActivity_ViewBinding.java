package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class AddPropertyListOfPropertyTypeActivity_ViewBinding implements Unbinder {
    private AddPropertyListOfPropertyTypeActivity target;
    private View view2131361892;
    private View view2131362357;

    @UiThread
    public AddPropertyListOfPropertyTypeActivity_ViewBinding(AddPropertyListOfPropertyTypeActivity addPropertyListOfPropertyTypeActivity) {
        this(addPropertyListOfPropertyTypeActivity, addPropertyListOfPropertyTypeActivity.getWindow().getDecorView());
    }

    @UiThread
    public AddPropertyListOfPropertyTypeActivity_ViewBinding(final AddPropertyListOfPropertyTypeActivity addPropertyListOfPropertyTypeActivity, View view) {
        this.target = addPropertyListOfPropertyTypeActivity;
        addPropertyListOfPropertyTypeActivity.propertyTypeRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'propertyTypeRecyclerView'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.next_button, "field 'nextButton' and method 'clickOnNextButton'");
        addPropertyListOfPropertyTypeActivity.nextButton = (FancyButton) Utils.castView(findRequiredView, R.id.next_button, "field 'nextButton'", FancyButton.class);
        this.view2131362357 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyListOfPropertyTypeActivity.clickOnNextButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyListOfPropertyTypeActivity.clickOnBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddPropertyListOfPropertyTypeActivity addPropertyListOfPropertyTypeActivity = this.target;
        if (addPropertyListOfPropertyTypeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addPropertyListOfPropertyTypeActivity.propertyTypeRecyclerView = null;
        addPropertyListOfPropertyTypeActivity.nextButton = null;
        this.view2131362357.setOnClickListener(null);
        this.view2131362357 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
