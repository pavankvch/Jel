package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class CountryPickerActivity_ViewBinding implements Unbinder {
    private CountryPickerActivity target;
    private View view2131361959;

    @UiThread
    public CountryPickerActivity_ViewBinding(CountryPickerActivity countryPickerActivity) {
        this(countryPickerActivity, countryPickerActivity.getWindow().getDecorView());
    }

    @UiThread
    public CountryPickerActivity_ViewBinding(final CountryPickerActivity countryPickerActivity, View view) {
        this.target = countryPickerActivity;
        countryPickerActivity.countrySearchEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.country_search_edittext, "field 'countrySearchEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.cancel_TV, "field 'cancelTextView' and method 'clickOnCancel'");
        countryPickerActivity.cancelTextView = (TextView) Utils.castView(findRequiredView, R.id.cancel_TV, "field 'cancelTextView'", TextView.class);
        this.view2131361959 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                countryPickerActivity.clickOnCancel(view);
            }
        });
        countryPickerActivity.countryCodeRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.country_code_recyclerview, "field 'countryCodeRecyclerView'", RecyclerView.class);
        countryPickerActivity.noResultTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.textView_noresult, "field 'noResultTextView'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        CountryPickerActivity countryPickerActivity = this.target;
        if (countryPickerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        countryPickerActivity.countrySearchEditText = null;
        countryPickerActivity.cancelTextView = null;
        countryPickerActivity.countryCodeRecyclerView = null;
        countryPickerActivity.noResultTextView = null;
        this.view2131361959.setOnClickListener(null);
        this.view2131361959 = null;
    }
}
