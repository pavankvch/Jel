package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class AddPropertyStepsActivity_ViewBinding implements Unbinder {
    private AddPropertyStepsActivity target;
    private View view2131361892;
    private View view2131361904;
    private View view2131362089;
    private View view2131362432;
    private View view2131362790;

    @UiThread
    public AddPropertyStepsActivity_ViewBinding(AddPropertyStepsActivity addPropertyStepsActivity) {
        this(addPropertyStepsActivity, addPropertyStepsActivity.getWindow().getDecorView());
    }

    @UiThread
    public AddPropertyStepsActivity_ViewBinding(final AddPropertyStepsActivity addPropertyStepsActivity, View view) {
        this.target = addPropertyStepsActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.un_publish_property, "field 'unPublishProperty' and method 'clickOnPublishProperty'");
        addPropertyStepsActivity.unPublishProperty = (FancyButton) Utils.castView(findRequiredView, R.id.un_publish_property, "field 'unPublishProperty'", FancyButton.class);
        this.view2131362790 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyStepsActivity.clickOnPublishProperty();
            }
        });
        addPropertyStepsActivity.addYourPropertyTV = (TextView) Utils.findRequiredViewAsType(view, R.id.add_your_property_tv, "field 'addYourPropertyTV'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.basic_info_step_one_layout, "field 'basicInfoStepOneLayout' and method 'clickOnBasicInfoStepOneLayout'");
        addPropertyStepsActivity.basicInfoStepOneLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.basic_info_step_one_layout, "field 'basicInfoStepOneLayout'", LinearLayout.class);
        this.view2131361904 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyStepsActivity.clickOnBasicInfoStepOneLayout();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.description_property_step_two_layout, "field 'descriptionPropertyStepTwoLayout' and method 'clickOnDescriptionPropertyStepTwoLayout'");
        addPropertyStepsActivity.descriptionPropertyStepTwoLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.description_property_step_two_layout, "field 'descriptionPropertyStepTwoLayout'", LinearLayout.class);
        this.view2131362089 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyStepsActivity.clickOnDescriptionPropertyStepTwoLayout();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.price_setting_step_three_layout, "field 'priceSettingStepThreeLayout' and method 'clickOnPriceSettingStepThreeLayout'");
        addPropertyStepsActivity.priceSettingStepThreeLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.price_setting_step_three_layout, "field 'priceSettingStepThreeLayout'", LinearLayout.class);
        this.view2131362432 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyStepsActivity.clickOnPriceSettingStepThreeLayout();
            }
        });
        view = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPropertyStepsActivity.clickOnBack();
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddPropertyStepsActivity addPropertyStepsActivity = this.target;
        if (addPropertyStepsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addPropertyStepsActivity.unPublishProperty = null;
        addPropertyStepsActivity.addYourPropertyTV = null;
        addPropertyStepsActivity.basicInfoStepOneLayout = null;
        addPropertyStepsActivity.descriptionPropertyStepTwoLayout = null;
        addPropertyStepsActivity.priceSettingStepThreeLayout = null;
        this.view2131362790.setOnClickListener(null);
        this.view2131362790 = null;
        this.view2131361904.setOnClickListener(null);
        this.view2131361904 = null;
        this.view2131362089.setOnClickListener(null);
        this.view2131362089 = null;
        this.view2131362432.setOnClickListener(null);
        this.view2131362432 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
    }
}
