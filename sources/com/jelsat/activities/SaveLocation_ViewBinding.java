package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class SaveLocation_ViewBinding implements Unbinder {
    private SaveLocation target;
    private View view2131361850;
    private View view2131361890;
    private View view2131362008;
    private View view2131362343;
    private View view2131362532;
    private View view2131362566;

    @UiThread
    public SaveLocation_ViewBinding(SaveLocation saveLocation) {
        this(saveLocation, saveLocation.getWindow().getDecorView());
    }

    @UiThread
    public SaveLocation_ViewBinding(final SaveLocation saveLocation, View view) {
        this.target = saveLocation;
        saveLocation.mapFrameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.map_fragment_layout, "field 'mapFrameLayout'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.save_address, "field 'save_address' and method 'saveAddress'");
        saveLocation.save_address = (EditText) Utils.castView(findRequiredView, R.id.save_address, "field 'save_address'", EditText.class);
        this.view2131362532 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                saveLocation.saveAddress();
            }
        });
        saveLocation.save_house = (EditText) Utils.findRequiredViewAsType(view, R.id.save_house, "field 'save_house'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.city_name, "field 'city_name' and method 'cityName'");
        saveLocation.city_name = (EditText) Utils.castView(findRequiredView, R.id.city_name, "field 'city_name'", EditText.class);
        this.view2131362008 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                saveLocation.cityName();
            }
        });
        saveLocation.save_landmark = (EditText) Utils.findRequiredViewAsType(view, R.id.save_lankmark, "field 'save_landmark'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.add_your_property, "field 'add_your_property' and method 'addYourProperty'");
        saveLocation.add_your_property = (Button) Utils.castView(findRequiredView, R.id.add_your_property, "field 'add_your_property'", Button.class);
        this.view2131361850 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                saveLocation.addYourProperty();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.myLocationFab, "method 'clickOnMyLocationFab'");
        this.view2131362343 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                saveLocation.clickOnMyLocationFab();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.search_icon, "method 'searchIcon'");
        this.view2131362566 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                saveLocation.searchIcon();
            }
        });
        view = Utils.findRequiredView(view, R.id.back, "method 'back'");
        this.view2131361890 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                saveLocation.back();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SaveLocation saveLocation = this.target;
        if (saveLocation == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        saveLocation.mapFrameLayout = null;
        saveLocation.save_address = null;
        saveLocation.save_house = null;
        saveLocation.city_name = null;
        saveLocation.save_landmark = null;
        saveLocation.add_your_property = null;
        this.view2131362532.setOnClickListener(null);
        this.view2131362532 = null;
        this.view2131362008.setOnClickListener(null);
        this.view2131362008 = null;
        this.view2131361850.setOnClickListener(null);
        this.view2131361850 = null;
        this.view2131362343.setOnClickListener(null);
        this.view2131362343 = null;
        this.view2131362566.setOnClickListener(null);
        this.view2131362566 = null;
        this.view2131361890.setOnClickListener(null);
        this.view2131361890 = null;
    }
}
