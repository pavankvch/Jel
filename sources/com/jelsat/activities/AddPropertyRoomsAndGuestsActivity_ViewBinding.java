package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class AddPropertyRoomsAndGuestsActivity_ViewBinding implements Unbinder {
    private AddPropertyRoomsAndGuestsActivity target;
    private View view2131361892;
    private View view2131361906;
    private View view2131361909;
    private View view2131362106;
    private View view2131362357;
    private View view2131362531;
    private View view2131362594;
    private View view2131362647;

    @UiThread
    public AddPropertyRoomsAndGuestsActivity_ViewBinding(AddPropertyRoomsAndGuestsActivity addPropertyRoomsAndGuestsActivity) {
        this(addPropertyRoomsAndGuestsActivity, addPropertyRoomsAndGuestsActivity.getWindow().getDecorView());
    }

    @UiThread
    public AddPropertyRoomsAndGuestsActivity_ViewBinding(final AddPropertyRoomsAndGuestsActivity addPropertyRoomsAndGuestsActivity, View view) {
        this.target = addPropertyRoomsAndGuestsActivity;
        addPropertyRoomsAndGuestsActivity.bedRoomsTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.bed_rooms_textInput_layout, "field 'bedRoomsTextInputLayout'", TextInputLayout.class);
        addPropertyRoomsAndGuestsActivity.tentsTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.tents_textInput_layout, "field 'tentsTextInputLayout'", TextInputLayout.class);
        addPropertyRoomsAndGuestsActivity.doubleBedsTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.double_beds_textInput_layout, "field 'doubleBedsTextInputLayout'", TextInputLayout.class);
        addPropertyRoomsAndGuestsActivity.singleBedsTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.single_beds_textInput_layout, "field 'singleBedsTextInputLayout'", TextInputLayout.class);
        addPropertyRoomsAndGuestsActivity.bathRoomsTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.bathrooms_textInput_layout, "field 'bathRoomsTextInputLayout'", TextInputLayout.class);
        addPropertyRoomsAndGuestsActivity.maxGuestsTextInputLayout = (TextInputLayout) Utils.findRequiredViewAsType(view, R.id.max_guests_textInput_layout, "field 'maxGuestsTextInputLayout'", TextInputLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.bed_rooms_editText, "field 'bedRoomsEditText' and method 'clickOnBedRoomsEditText'");
        addPropertyRoomsAndGuestsActivity.bedRoomsEditText = (TextInputEditText) Utils.castView(findRequiredView, R.id.bed_rooms_editText, "field 'bedRoomsEditText'", TextInputEditText.class);
        this.view2131361909 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnBedRoomsEditText();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.tents_editText, "field 'tentsEditText' and method 'clickOnTentsEditText'");
        addPropertyRoomsAndGuestsActivity.tentsEditText = (TextInputEditText) Utils.castView(findRequiredView, R.id.tents_editText, "field 'tentsEditText'", TextInputEditText.class);
        this.view2131362647 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnTentsEditText();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.double_beds_editText, "field 'doubleBedsEditText' and method 'clickOnDoubleBedsEditText'");
        addPropertyRoomsAndGuestsActivity.doubleBedsEditText = (TextInputEditText) Utils.castView(findRequiredView, R.id.double_beds_editText, "field 'doubleBedsEditText'", TextInputEditText.class);
        this.view2131362106 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnDoubleBedsEditText();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.single_beds_editText, "field 'singleBedsEditText' and method 'clickOnSingleBedsEditText'");
        addPropertyRoomsAndGuestsActivity.singleBedsEditText = (TextInputEditText) Utils.castView(findRequiredView, R.id.single_beds_editText, "field 'singleBedsEditText'", TextInputEditText.class);
        this.view2131362594 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnSingleBedsEditText();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.bathrooms_editText, "field 'bathroomsEditText' and method 'clickOnBathRoomsEditText'");
        addPropertyRoomsAndGuestsActivity.bathroomsEditText = (TextInputEditText) Utils.castView(findRequiredView, R.id.bathrooms_editText, "field 'bathroomsEditText'", TextInputEditText.class);
        this.view2131361906 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnBathRoomsEditText();
            }
        });
        addPropertyRoomsAndGuestsActivity.maxGuestsEditText = (TextInputEditText) Utils.findRequiredViewAsType(view, R.id.max_guests_editText, "field 'maxGuestsEditText'", TextInputEditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.next_button, "field 'nextButton' and method 'clickOnNextButton'");
        addPropertyRoomsAndGuestsActivity.nextButton = (FancyButton) Utils.castView(findRequiredView, R.id.next_button, "field 'nextButton'", FancyButton.class);
        this.view2131362357 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnNextButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnBack();
            }
        });
        view = Utils.findRequiredView(view, R.id.saveAndExit, "method 'clickOnSaveAndExit'");
        this.view2131362531 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyRoomsAndGuestsActivity.clickOnSaveAndExit();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddPropertyRoomsAndGuestsActivity addPropertyRoomsAndGuestsActivity = this.target;
        if (addPropertyRoomsAndGuestsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addPropertyRoomsAndGuestsActivity.bedRoomsTextInputLayout = null;
        addPropertyRoomsAndGuestsActivity.tentsTextInputLayout = null;
        addPropertyRoomsAndGuestsActivity.doubleBedsTextInputLayout = null;
        addPropertyRoomsAndGuestsActivity.singleBedsTextInputLayout = null;
        addPropertyRoomsAndGuestsActivity.bathRoomsTextInputLayout = null;
        addPropertyRoomsAndGuestsActivity.maxGuestsTextInputLayout = null;
        addPropertyRoomsAndGuestsActivity.bedRoomsEditText = null;
        addPropertyRoomsAndGuestsActivity.tentsEditText = null;
        addPropertyRoomsAndGuestsActivity.doubleBedsEditText = null;
        addPropertyRoomsAndGuestsActivity.singleBedsEditText = null;
        addPropertyRoomsAndGuestsActivity.bathroomsEditText = null;
        addPropertyRoomsAndGuestsActivity.maxGuestsEditText = null;
        addPropertyRoomsAndGuestsActivity.nextButton = null;
        this.view2131361909.setOnClickListener(null);
        this.view2131361909 = null;
        this.view2131362647.setOnClickListener(null);
        this.view2131362647 = null;
        this.view2131362106.setOnClickListener(null);
        this.view2131362106 = null;
        this.view2131362594.setOnClickListener(null);
        this.view2131362594 = null;
        this.view2131361906.setOnClickListener(null);
        this.view2131361906 = null;
        this.view2131362357.setOnClickListener(null);
        this.view2131362357 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
    }
}
