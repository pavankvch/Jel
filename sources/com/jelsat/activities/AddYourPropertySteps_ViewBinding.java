package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class AddYourPropertySteps_ViewBinding implements Unbinder {
    private AddYourPropertySteps target;
    private View view2131361893;
    private View view2131362629;
    private View view2131362630;
    private View view2131362631;
    private View view2131362789;

    @UiThread
    public AddYourPropertySteps_ViewBinding(AddYourPropertySteps addYourPropertySteps) {
        this(addYourPropertySteps, addYourPropertySteps.getWindow().getDecorView());
    }

    @UiThread
    public AddYourPropertySteps_ViewBinding(final AddYourPropertySteps addYourPropertySteps, View view) {
        this.target = addYourPropertySteps;
        View findRequiredView = Utils.findRequiredView(view, R.id.step_one, "field 'step_one' and method 'stepOne'");
        addYourPropertySteps.step_one = (LinearLayout) Utils.castView(findRequiredView, R.id.step_one, "field 'step_one'", LinearLayout.class);
        this.view2131362629 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourPropertySteps.stepOne();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.step_two, "field 'step_two' and method 'stepTwo'");
        addYourPropertySteps.step_two = (LinearLayout) Utils.castView(findRequiredView, R.id.step_two, "field 'step_two'", LinearLayout.class);
        this.view2131362631 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourPropertySteps.stepTwo();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.step_three, "field 'step_three' and method 'stepThree'");
        addYourPropertySteps.step_three = (LinearLayout) Utils.castView(findRequiredView, R.id.step_three, "field 'step_three'", LinearLayout.class);
        this.view2131362630 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourPropertySteps.stepThree();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'back_arrow_property' and method 'backArrowPressed'");
        addYourPropertySteps.back_arrow_property = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'back_arrow_property'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourPropertySteps.backArrowPressed();
            }
        });
        addYourPropertySteps.StepOneTxV = (TextView) Utils.findRequiredViewAsType(view, R.id.StepOneTxV, "field 'StepOneTxV'", TextView.class);
        addYourPropertySteps.StepTwoTxV = (TextView) Utils.findRequiredViewAsType(view, R.id.StepTwoTxV, "field 'StepTwoTxV'", TextView.class);
        addYourPropertySteps.StepThreeTxV = (TextView) Utils.findRequiredViewAsType(view, R.id.StepThreeTxV, "field 'StepThreeTxV'", TextView.class);
        addYourPropertySteps.stepOneDisc = (TextView) Utils.findRequiredViewAsType(view, R.id.stepOneDisc, "field 'stepOneDisc'", TextView.class);
        addYourPropertySteps.stepTwoDisc = (TextView) Utils.findRequiredViewAsType(view, R.id.stepTwoDisc, "field 'stepTwoDisc'", TextView.class);
        addYourPropertySteps.stepThreeDisc = (TextView) Utils.findRequiredViewAsType(view, R.id.stepThreeDisc, "field 'stepThreeDisc'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.unPublishButton, "field 'unPublishButton' and method 'unPublishProperty'");
        addYourPropertySteps.unPublishButton = (FancyButton) Utils.castView(findRequiredView, R.id.unPublishButton, "field 'unPublishButton'", FancyButton.class);
        this.view2131362789 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addYourPropertySteps.unPublishProperty();
            }
        });
        addYourPropertySteps.stepTwoImageview = (ImageView) Utils.findRequiredViewAsType(view, R.id.stepTwoImageview, "field 'stepTwoImageview'", ImageView.class);
        addYourPropertySteps.stepThreeImageview = (ImageView) Utils.findRequiredViewAsType(view, R.id.stepThreeImageview, "field 'stepThreeImageview'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        AddYourPropertySteps addYourPropertySteps = this.target;
        if (addYourPropertySteps == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        addYourPropertySteps.step_one = null;
        addYourPropertySteps.step_two = null;
        addYourPropertySteps.step_three = null;
        addYourPropertySteps.back_arrow_property = null;
        addYourPropertySteps.StepOneTxV = null;
        addYourPropertySteps.StepTwoTxV = null;
        addYourPropertySteps.StepThreeTxV = null;
        addYourPropertySteps.stepOneDisc = null;
        addYourPropertySteps.stepTwoDisc = null;
        addYourPropertySteps.stepThreeDisc = null;
        addYourPropertySteps.unPublishButton = null;
        addYourPropertySteps.stepTwoImageview = null;
        addYourPropertySteps.stepThreeImageview = null;
        this.view2131362629.setOnClickListener(null);
        this.view2131362629 = null;
        this.view2131362631.setOnClickListener(null);
        this.view2131362631 = null;
        this.view2131362630.setOnClickListener(null);
        this.view2131362630 = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362789.setOnClickListener(null);
        this.view2131362789 = null;
    }
}
