package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class CostPerNightActivity_ViewBinding implements Unbinder {
    private CostPerNightActivity target;
    private View view2131361893;
    private View view2131361994;
    private View view2131361995;
    private View view2131361996;
    private View view2131361997;
    private View view2131361998;
    private View view2131361999;
    private View view2131362000;
    private View view2131362037;
    private View view2131362531;

    @UiThread
    public CostPerNightActivity_ViewBinding(CostPerNightActivity costPerNightActivity) {
        this(costPerNightActivity, costPerNightActivity.getWindow().getDecorView());
    }

    @UiThread
    public CostPerNightActivity_ViewBinding(final CostPerNightActivity costPerNightActivity, View view) {
        this.target = costPerNightActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_property, "field 'backArrowProperty' and method 'backbutton'");
        costPerNightActivity.backArrowProperty = (ImageView) Utils.castView(findRequiredView, R.id.back_arrow_property, "field 'backArrowProperty'", ImageView.class);
        this.view2131361893 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                costPerNightActivity.backbutton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.saveAndExit, "field 'saveAndExit' and method 'saveAndExit'");
        costPerNightActivity.saveAndExit = (TextView) Utils.castView(findRequiredView, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        this.view2131362531 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                costPerNightActivity.saveAndExit();
            }
        });
        costPerNightActivity.checkInTimeEdt = (EditText) Utils.findRequiredViewAsType(view, R.id.checkInTimeEdt, "field 'checkInTimeEdt'", EditText.class);
        costPerNightActivity.checkOutTimeEdt = (EditText) Utils.findRequiredViewAsType(view, R.id.checkOutTimeEdt, "field 'checkOutTimeEdt'", EditText.class);
        costPerNightActivity.fromDateEdtTx = (EditText) Utils.findRequiredViewAsType(view, R.id.fromDateEdtTx, "field 'fromDateEdtTx'", EditText.class);
        costPerNightActivity.toDateEdtTx = (EditText) Utils.findRequiredViewAsType(view, R.id.toDateEdtTx, "field 'toDateEdtTx'", EditText.class);
        findRequiredView = Utils.findRequiredView(view, R.id.costpnNextButton, "field 'costpnNextButton' and method 'costpnNextButton'");
        costPerNightActivity.costpnNextButton = (Button) Utils.castView(findRequiredView, R.id.costpnNextButton, "field 'costpnNextButton'", Button.class);
        this.view2131362037 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                costPerNightActivity.costpnNextButton();
            }
        });
        costPerNightActivity.daysCount = (TextView) Utils.findRequiredViewAsType(view, R.id.daysCount, "field 'daysCount'", TextView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.checkboxMon, "field 'checkboxMon' and method 'onRadioCheckChanged'");
        costPerNightActivity.checkboxMon = (CheckBox) Utils.castView(findRequiredView, R.id.checkboxMon, "field 'checkboxMon'", CheckBox.class);
        this.view2131361995 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                costPerNightActivity.onRadioCheckChanged((CheckBox) Utils.castParam(compoundButton, "onCheckedChanged", 0, "onRadioCheckChanged", 0, CheckBox.class));
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.checkboxTue, "field 'checkboxTue' and method 'onRadioCheckChanged'");
        costPerNightActivity.checkboxTue = (CheckBox) Utils.castView(findRequiredView, R.id.checkboxTue, "field 'checkboxTue'", CheckBox.class);
        this.view2131361999 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                costPerNightActivity.onRadioCheckChanged((CheckBox) Utils.castParam(compoundButton, "onCheckedChanged", 0, "onRadioCheckChanged", 0, CheckBox.class));
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.checkboxWed, "field 'checkboxWed' and method 'onRadioCheckChanged'");
        costPerNightActivity.checkboxWed = (CheckBox) Utils.castView(findRequiredView, R.id.checkboxWed, "field 'checkboxWed'", CheckBox.class);
        this.view2131362000 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                costPerNightActivity.onRadioCheckChanged((CheckBox) Utils.castParam(compoundButton, "onCheckedChanged", 0, "onRadioCheckChanged", 0, CheckBox.class));
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.checkboxThur, "field 'checkboxThur' and method 'onRadioCheckChanged'");
        costPerNightActivity.checkboxThur = (CheckBox) Utils.castView(findRequiredView, R.id.checkboxThur, "field 'checkboxThur'", CheckBox.class);
        this.view2131361998 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                costPerNightActivity.onRadioCheckChanged((CheckBox) Utils.castParam(compoundButton, "onCheckedChanged", 0, "onRadioCheckChanged", 0, CheckBox.class));
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.checkboxFri, "field 'checkboxFri' and method 'onRadioCheckChanged'");
        costPerNightActivity.checkboxFri = (CheckBox) Utils.castView(findRequiredView, R.id.checkboxFri, "field 'checkboxFri'", CheckBox.class);
        this.view2131361994 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                costPerNightActivity.onRadioCheckChanged((CheckBox) Utils.castParam(compoundButton, "onCheckedChanged", 0, "onRadioCheckChanged", 0, CheckBox.class));
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.checkboxSat, "field 'checkboxSat' and method 'onRadioCheckChanged'");
        costPerNightActivity.checkboxSat = (CheckBox) Utils.castView(findRequiredView, R.id.checkboxSat, "field 'checkboxSat'", CheckBox.class);
        this.view2131361996 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                costPerNightActivity.onRadioCheckChanged((CheckBox) Utils.castParam(compoundButton, "onCheckedChanged", 0, "onRadioCheckChanged", 0, CheckBox.class));
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.checkboxSun, "field 'checkboxSun' and method 'onRadioCheckChanged'");
        costPerNightActivity.checkboxSun = (CheckBox) Utils.castView(findRequiredView, R.id.checkboxSun, "field 'checkboxSun'", CheckBox.class);
        this.view2131361997 = findRequiredView;
        ((CompoundButton) findRequiredView).setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                costPerNightActivity.onRadioCheckChanged((CheckBox) Utils.castParam(compoundButton, "onCheckedChanged", 0, "onRadioCheckChanged", 0, CheckBox.class));
            }
        });
        costPerNightActivity.editMon = (EditText) Utils.findRequiredViewAsType(view, R.id.editMon, "field 'editMon'", EditText.class);
        costPerNightActivity.editTue = (EditText) Utils.findRequiredViewAsType(view, R.id.editTue, "field 'editTue'", EditText.class);
        costPerNightActivity.editWed = (EditText) Utils.findRequiredViewAsType(view, R.id.editWed, "field 'editWed'", EditText.class);
        costPerNightActivity.editThur = (EditText) Utils.findRequiredViewAsType(view, R.id.editThur, "field 'editThur'", EditText.class);
        costPerNightActivity.editFri = (EditText) Utils.findRequiredViewAsType(view, R.id.editFri, "field 'editFri'", EditText.class);
        costPerNightActivity.editSat = (EditText) Utils.findRequiredViewAsType(view, R.id.editSat, "field 'editSat'", EditText.class);
        costPerNightActivity.editSun = (EditText) Utils.findRequiredViewAsType(view, R.id.editSun, "field 'editSun'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        CostPerNightActivity costPerNightActivity = this.target;
        if (costPerNightActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        costPerNightActivity.backArrowProperty = null;
        costPerNightActivity.saveAndExit = null;
        costPerNightActivity.checkInTimeEdt = null;
        costPerNightActivity.checkOutTimeEdt = null;
        costPerNightActivity.fromDateEdtTx = null;
        costPerNightActivity.toDateEdtTx = null;
        costPerNightActivity.costpnNextButton = null;
        costPerNightActivity.daysCount = null;
        costPerNightActivity.checkboxMon = null;
        costPerNightActivity.checkboxTue = null;
        costPerNightActivity.checkboxWed = null;
        costPerNightActivity.checkboxThur = null;
        costPerNightActivity.checkboxFri = null;
        costPerNightActivity.checkboxSat = null;
        costPerNightActivity.checkboxSun = null;
        costPerNightActivity.editMon = null;
        costPerNightActivity.editTue = null;
        costPerNightActivity.editWed = null;
        costPerNightActivity.editThur = null;
        costPerNightActivity.editFri = null;
        costPerNightActivity.editSat = null;
        costPerNightActivity.editSun = null;
        this.view2131361893.setOnClickListener(null);
        this.view2131361893 = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
        this.view2131362037.setOnClickListener(null);
        this.view2131362037 = null;
        ((CompoundButton) this.view2131361995).setOnCheckedChangeListener(null);
        this.view2131361995 = null;
        ((CompoundButton) this.view2131361999).setOnCheckedChangeListener(null);
        this.view2131361999 = null;
        ((CompoundButton) this.view2131362000).setOnCheckedChangeListener(null);
        this.view2131362000 = null;
        ((CompoundButton) this.view2131361998).setOnCheckedChangeListener(null);
        this.view2131361998 = null;
        ((CompoundButton) this.view2131361994).setOnCheckedChangeListener(null);
        this.view2131361994 = null;
        ((CompoundButton) this.view2131361996).setOnCheckedChangeListener(null);
        this.view2131361996 = null;
        ((CompoundButton) this.view2131361997).setOnCheckedChangeListener(null);
        this.view2131361997 = null;
    }
}
