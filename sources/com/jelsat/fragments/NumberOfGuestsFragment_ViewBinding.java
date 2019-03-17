package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public class NumberOfGuestsFragment_ViewBinding implements Unbinder {
    private NumberOfGuestsFragment target;
    private View view2131362363;
    private TextWatcher view2131362363TextWatcher;

    @UiThread
    public NumberOfGuestsFragment_ViewBinding(final NumberOfGuestsFragment numberOfGuestsFragment, View view) {
        this.target = numberOfGuestsFragment;
        numberOfGuestsFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.guests_recyclerview, "field 'recyclerView'", RecyclerView.class);
        numberOfGuestsFragment.guestcountlayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.guestcount, "field 'guestcountlayout'", LinearLayout.class);
        view = Utils.findRequiredView(view, R.id.noOfPersons, "field 'guestcount' and method 'onTextChanged'");
        numberOfGuestsFragment.guestcount = (EditText) Utils.castView(view, R.id.noOfPersons, "field 'guestcount'", EditText.class);
        this.view2131362363 = view;
        this.view2131362363TextWatcher = new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                numberOfGuestsFragment.onTextChanged(charSequence);
            }
        };
        ((TextView) view).addTextChangedListener(this.view2131362363TextWatcher);
    }

    @CallSuper
    public void unbind() {
        NumberOfGuestsFragment numberOfGuestsFragment = this.target;
        if (numberOfGuestsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        numberOfGuestsFragment.recyclerView = null;
        numberOfGuestsFragment.guestcountlayout = null;
        numberOfGuestsFragment.guestcount = null;
        ((TextView) this.view2131362363).removeTextChangedListener(this.view2131362363TextWatcher);
        this.view2131362363TextWatcher = null;
        this.view2131362363 = null;
    }
}
