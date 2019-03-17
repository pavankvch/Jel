package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class HouseRulesActivity_ViewBinding implements Unbinder {
    private HouseRulesActivity target;
    private View view2131361848;
    private View view2131361893;
    private View view2131362085;
    private View view2131362087;
    private View view2131362435;
    private View view2131362531;

    @UiThread
    public HouseRulesActivity_ViewBinding(HouseRulesActivity houseRulesActivity) {
        this(houseRulesActivity, houseRulesActivity.getWindow().getDecorView());
    }

    @UiThread
    public HouseRulesActivity_ViewBinding(final HouseRulesActivity houseRulesActivity, View view) {
        this.target = houseRulesActivity;
        houseRulesActivity.houseRulesRecyclerview = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.houserules_recyclerView, "field 'houseRulesRecyclerview'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.add_house_rule, "field 'addHouseRule' and method 'addHouseRule'");
        houseRulesActivity.addHouseRule = (TextView) Utils.castView(findRequiredView, R.id.add_house_rule, "field 'addHouseRule'", TextView.class);
        this.view2131361848 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseRulesActivity.addHouseRule();
            }
        });
        houseRulesActivity.addHouseRuleET = (EditText) Utils.findRequiredViewAsType(view, R.id.AddedRuleEditText, "field 'addHouseRuleET'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'backArrowProperty' and method 'backbutton'");
        houseRulesActivity.backArrowProperty = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'backArrowProperty'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseRulesActivity.backbutton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.saveAndExit, "field 'saveAndExit' and method 'saveAndExit'");
        houseRulesActivity.saveAndExit = (TextView) Utils.castView(findRequiredView, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        this.view2131362531 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseRulesActivity.saveAndExit();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.products_list_next, "field 'hosueRulesNextButton' and method 'houseRuleNextButton'");
        houseRulesActivity.hosueRulesNextButton = (Button) Utils.castView(findRequiredView, R.id.products_list_next, "field 'hosueRulesNextButton'", Button.class);
        this.view2131362435 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseRulesActivity.houseRuleNextButton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.delete_rule, "field 'delete_rule' and method 'deleteRule'");
        houseRulesActivity.delete_rule = (ImageView) Utils.castView(findRequiredView, R.id.delete_rule, "field 'delete_rule'", ImageView.class);
        this.view2131362087 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseRulesActivity.deleteRule();
            }
        });
        houseRulesActivity.yourHouseruleLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.yourHouseruleLayout, "field 'yourHouseruleLayout'", LinearLayout.class);
        houseRulesActivity.yourHouserule = (TextView) Utils.findRequiredViewAsType(view, R.id.yourHouserule, "field 'yourHouserule'", TextView.class);
        view = Utils.findRequiredView(view, R.id.deleteCustomRule, "method 'deleteCustomRule'");
        this.view2131362085 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                houseRulesActivity.deleteCustomRule();
            }
        });
    }

    @CallSuper
    public void unbind() {
        HouseRulesActivity houseRulesActivity = this.target;
        if (houseRulesActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        houseRulesActivity.houseRulesRecyclerview = null;
        houseRulesActivity.addHouseRule = null;
        houseRulesActivity.addHouseRuleET = null;
        houseRulesActivity.backArrowProperty = null;
        houseRulesActivity.saveAndExit = null;
        houseRulesActivity.hosueRulesNextButton = null;
        houseRulesActivity.delete_rule = null;
        houseRulesActivity.yourHouseruleLayout = null;
        houseRulesActivity.yourHouserule = null;
        this.view2131361848.setOnClickListener(null);
        this.view2131361848 = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
        this.view2131362435.setOnClickListener(null);
        this.view2131362435 = null;
        this.view2131362087.setOnClickListener(null);
        this.view2131362087 = null;
        this.view2131362085.setOnClickListener(null);
        this.view2131362085 = null;
    }
}
