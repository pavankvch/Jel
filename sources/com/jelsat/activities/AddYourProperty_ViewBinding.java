package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class AddYourProperty_ViewBinding implements Unbinder {
    private AddYourProperty target;
    private View view2131361850;
    private View view2131361893;
    private View view2131362531;

    @UiThread
    public AddYourProperty_ViewBinding(AddYourProperty addYourProperty) {
        this(addYourProperty, addYourProperty.getWindow().getDecorView());
    }

    @UiThread
    public AddYourProperty_ViewBinding(final AddYourProperty addYourProperty, View view) {
        this.target = addYourProperty;
        addYourProperty.roomLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.roomLay, "field 'roomLay'", LinearLayout.class);
        addYourProperty.tentLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.tentLay, "field 'tentLay'", LinearLayout.class);
        addYourProperty.bedRoomLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bedRoomLay, "field 'bedRoomLay'", LinearLayout.class);
        addYourProperty.doublebedLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.doublebedLay, "field 'doublebedLay'", LinearLayout.class);
        addYourProperty.singlebedLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.singlebedLay, "field 'singlebedLay'", LinearLayout.class);
        addYourProperty.bathroomLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bathroomLay, "field 'bathroomLay'", LinearLayout.class);
        addYourProperty.guestLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.guestsLay, "field 'guestLayout'", LinearLayout.class);
        addYourProperty.noOfPersonsSpinner = (Spinner) Utils.findRequiredViewAsType(view, R.id.noOfPersonsSpinner, "field 'noOfPersonsSpinner'", Spinner.class);
        addYourProperty.noOfRooms = (Spinner) Utils.findRequiredViewAsType(view, R.id.noOfRooms, "field 'noOfRooms'", Spinner.class);
        addYourProperty.noOfTents = (Spinner) Utils.findRequiredViewAsType(view, R.id.noOfTents, "field 'noOfTents'", Spinner.class);
        addYourProperty.noOfBeds = (Spinner) Utils.findRequiredViewAsType(view, R.id.noOfBeds, "field 'noOfBeds'", Spinner.class);
        addYourProperty.noOfDoubleBeds = (Spinner) Utils.findRequiredViewAsType(view, R.id.noOfDoubleBeds, "field 'noOfDoubleBeds'", Spinner.class);
        addYourProperty.doubleBedsTextview = (TextView) Utils.findRequiredViewAsType(view, R.id.double_beds_textview, "field 'doubleBedsTextview'", TextView.class);
        addYourProperty.noOfSingleBeds = (Spinner) Utils.findRequiredViewAsType(view, R.id.noOfSingleBeds, "field 'noOfSingleBeds'", Spinner.class);
        addYourProperty.singleBedsTextview = (TextView) Utils.findRequiredViewAsType(view, R.id.single_beds_textview, "field 'singleBedsTextview'", TextView.class);
        addYourProperty.noOfBathrooms = (Spinner) Utils.findRequiredViewAsType(view, R.id.noOfBathrooms, "field 'noOfBathrooms'", Spinner.class);
        addYourProperty.noOfPersons = (EditText) Utils.findRequiredViewAsType(view, R.id.noOfPersons, "field 'noOfPersons'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.add_your_property, "field 'addYourProperty' and method 'addYourProperty'");
        addYourProperty.addYourProperty = (Button) Utils.castView(findRequiredView, R.id.add_your_property, "field 'addYourProperty'", Button.class);
        this.view2131361850 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourProperty.addYourProperty();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'backArrowProperty' and method 'backButton'");
        addYourProperty.backArrowProperty = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'backArrowProperty'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourProperty.backButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.saveAndExit, "field 'saveAndExit' and method 'saveAndExit'");
        addYourProperty.saveAndExit = (TextView) Utils.castView(view, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        this.view2131362531 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourProperty.saveAndExit();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddYourProperty addYourProperty = this.target;
        if (addYourProperty == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addYourProperty.roomLay = null;
        addYourProperty.tentLay = null;
        addYourProperty.bedRoomLay = null;
        addYourProperty.doublebedLay = null;
        addYourProperty.singlebedLay = null;
        addYourProperty.bathroomLay = null;
        addYourProperty.guestLayout = null;
        addYourProperty.noOfPersonsSpinner = null;
        addYourProperty.noOfRooms = null;
        addYourProperty.noOfTents = null;
        addYourProperty.noOfBeds = null;
        addYourProperty.noOfDoubleBeds = null;
        addYourProperty.doubleBedsTextview = null;
        addYourProperty.noOfSingleBeds = null;
        addYourProperty.singleBedsTextview = null;
        addYourProperty.noOfBathrooms = null;
        addYourProperty.noOfPersons = null;
        addYourProperty.addYourProperty = null;
        addYourProperty.backArrowProperty = null;
        addYourProperty.saveAndExit = null;
        this.view2131361850.setOnClickListener(null);
        this.view2131361850 = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
    }
}
