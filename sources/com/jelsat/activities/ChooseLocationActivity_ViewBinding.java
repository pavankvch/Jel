package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ChooseLocationActivity_ViewBinding implements Unbinder {
    private ChooseLocationActivity target;
    private View view2131361892;
    private View view2131362349;

    @UiThread
    public ChooseLocationActivity_ViewBinding(ChooseLocationActivity chooseLocationActivity) {
        this(chooseLocationActivity, chooseLocationActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChooseLocationActivity_ViewBinding(final ChooseLocationActivity chooseLocationActivity, View view) {
        this.target = chooseLocationActivity;
        chooseLocationActivity.searchTopLocalityRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.search_top_locality_recyclerView, "field 'searchTopLocalityRecyclerView'", RecyclerView.class);
        chooseLocationActivity.mAutocompleteView = (AutoCompleteTextView) Utils.findRequiredViewAsType(view, R.id.autocomplete_places, "field 'mAutocompleteView'", AutoCompleteTextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.near_by_textView, "method 'clickOnNearByTextView'");
        this.view2131362349 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                chooseLocationActivity.clickOnNearByTextView();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBackArrow'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                chooseLocationActivity.clickOnBackArrow();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ChooseLocationActivity chooseLocationActivity = this.target;
        if (chooseLocationActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        chooseLocationActivity.searchTopLocalityRecyclerView = null;
        chooseLocationActivity.mAutocompleteView = null;
        this.view2131362349.setOnClickListener(null);
        this.view2131362349 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
