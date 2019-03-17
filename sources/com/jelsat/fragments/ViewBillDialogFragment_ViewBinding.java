package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class ViewBillDialogFragment_ViewBinding implements Unbinder {
    private ViewBillDialogFragment target;
    private View view2131362246;

    @UiThread
    public ViewBillDialogFragment_ViewBinding(final ViewBillDialogFragment viewBillDialogFragment, View view) {
        this.target = viewBillDialogFragment;
        viewBillDialogFragment.noOfNightsCalTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.noOfNightsCalTxt, "field 'noOfNightsCalTxt'", TextView.class);
        viewBillDialogFragment.pricePerNight = (TextView) Utils.findRequiredViewAsType(view, R.id.price_per_night, "field 'pricePerNight'", TextView.class);
        viewBillDialogFragment.serviceFeeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.serviceFeeTxt, "field 'serviceFeeTxt'", TextView.class);
        viewBillDialogFragment.serviceFeeAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.serviceFeeAmount, "field 'serviceFeeAmount'", TextView.class);
        viewBillDialogFragment.taxTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tax_tv, "field 'taxTv'", TextView.class);
        viewBillDialogFragment.taxFeeAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.tax_fee_amount, "field 'taxFeeAmount'", TextView.class);
        viewBillDialogFragment.totalAmountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.total_amount_TV, "field 'totalAmountTv'", TextView.class);
        viewBillDialogFragment.totalAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.total_amount, "field 'totalAmount'", TextView.class);
        viewBillDialogFragment.layout_discount = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_discount, "field 'layout_discount'", LinearLayout.class);
        viewBillDialogFragment.discountAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.discount_amount, "field 'discountAmount'", TextView.class);
        viewBillDialogFragment.discount_view = Utils.findRequiredView(view, R.id.discount_view, "field 'discount_view'");
        viewBillDialogFragment.bookingId = (TextView) Utils.findRequiredViewAsType(view, R.id.booking_id, "field 'bookingId'", TextView.class);
        view = Utils.findRequiredView(view, R.id.img_close, "method 'clickOnClose'");
        this.view2131362246 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                viewBillDialogFragment.clickOnClose();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ViewBillDialogFragment viewBillDialogFragment = this.target;
        if (viewBillDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        viewBillDialogFragment.noOfNightsCalTxt = null;
        viewBillDialogFragment.pricePerNight = null;
        viewBillDialogFragment.serviceFeeTxt = null;
        viewBillDialogFragment.serviceFeeAmount = null;
        viewBillDialogFragment.taxTv = null;
        viewBillDialogFragment.taxFeeAmount = null;
        viewBillDialogFragment.totalAmountTv = null;
        viewBillDialogFragment.totalAmount = null;
        viewBillDialogFragment.layout_discount = null;
        viewBillDialogFragment.discountAmount = null;
        viewBillDialogFragment.discount_view = null;
        viewBillDialogFragment.bookingId = null;
        this.view2131362246.setOnClickListener(null);
        this.view2131362246 = null;
    }
}
