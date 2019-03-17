package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.customclasses.CustomEditText;

public class AboutYourProperty_ViewBinding implements Unbinder {
    private AboutYourProperty target;
    private View view2131361893;
    private View view2131362357;
    private View view2131362470;
    private View view2131362531;

    @UiThread
    public AboutYourProperty_ViewBinding(AboutYourProperty aboutYourProperty) {
        this(aboutYourProperty, aboutYourProperty.getWindow().getDecorView());
    }

    @UiThread
    public AboutYourProperty_ViewBinding(final AboutYourProperty aboutYourProperty, View view) {
        this.target = aboutYourProperty;
        View findRequiredView = Utils.findRequiredView(view, R.id.saveAndExit, "field 'saveAndExit' and method 'saveAndExit'");
        aboutYourProperty.saveAndExit = (TextView) Utils.castView(findRequiredView, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        this.view2131362531 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutYourProperty.saveAndExit();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.next_button, "field 'nextButton' and method 'aboutYourPropertyNext'");
        aboutYourProperty.nextButton = (Button) Utils.castView(findRequiredView, R.id.next_button, "field 'nextButton'", Button.class);
        this.view2131362357 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutYourProperty.aboutYourPropertyNext();
            }
        });
        aboutYourProperty.propertyName = (EditText) Utils.findRequiredViewAsType(view, R.id.property_name, "field 'propertyName'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.property_images_ed, "field 'propertyImage' and method 'propertimage'");
        aboutYourProperty.propertyImage = (CustomEditText) Utils.castView(findRequiredView, R.id.property_images_ed, "field 'propertyImage'", CustomEditText.class);
        this.view2131362470 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutYourProperty.propertimage();
            }
        });
        aboutYourProperty.aboutProperty = (EditText) Utils.findRequiredViewAsType(view, R.id.about_property_ed, "field 'aboutProperty'", EditText.class);
        aboutYourProperty.areaOfPropertyEd = (EditText) Utils.findRequiredViewAsType(view, R.id.areaOfPropertyEd, "field 'areaOfPropertyEd'", EditText.class);
        view = Utils.findRequiredView(view, R.id.back_arrow_property, "method 'backbutton'");
        this.view2131361893 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutYourProperty.backbutton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AboutYourProperty aboutYourProperty = this.target;
        if (aboutYourProperty == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        aboutYourProperty.saveAndExit = null;
        aboutYourProperty.nextButton = null;
        aboutYourProperty.propertyName = null;
        aboutYourProperty.propertyImage = null;
        aboutYourProperty.aboutProperty = null;
        aboutYourProperty.areaOfPropertyEd = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
        this.view2131362357.setOnClickListener(null);
        this.view2131362357 = null;
        this.view2131362470.setOnClickListener(null);
        this.view2131362470 = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
    }
}
