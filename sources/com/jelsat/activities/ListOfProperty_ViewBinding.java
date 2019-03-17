package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ListOfProperty_ViewBinding implements Unbinder {
    private ListOfProperty target;
    private View view2131361893;
    private View view2131362435;

    @UiThread
    public ListOfProperty_ViewBinding(ListOfProperty listOfProperty) {
        this(listOfProperty, listOfProperty.getWindow().getDecorView());
    }

    @UiThread
    public ListOfProperty_ViewBinding(final ListOfProperty listOfProperty, View view) {
        this.target = listOfProperty;
        View findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'back_arrow_property' and method 'backArrow'");
        listOfProperty.back_arrow_property = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'back_arrow_property'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                listOfProperty.backArrow();
            }
        });
        listOfProperty.propertyTypeRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.property_type_recyclerView, "field 'propertyTypeRecyclerView'", RecyclerView.class);
        view = Utils.findRequiredView(view, R.id.products_list_next, "field 'products_list_next' and method 'productListNext'");
        listOfProperty.products_list_next = (Button) Utils.castView(view, R.id.products_list_next, "field 'products_list_next'", Button.class);
        this.view2131362435 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                listOfProperty.productListNext();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ListOfProperty listOfProperty = this.target;
        if (listOfProperty == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        listOfProperty.back_arrow_property = null;
        listOfProperty.propertyTypeRecyclerView = null;
        listOfProperty.products_list_next = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362435.setOnClickListener(null);
        this.view2131362435 = null;
    }
}
