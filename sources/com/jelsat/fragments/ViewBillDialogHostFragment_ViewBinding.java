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

public class ViewBillDialogHostFragment_ViewBinding implements Unbinder {
    private ViewBillDialogHostFragment target;
    private View view2131362246;

    @UiThread
    public ViewBillDialogHostFragment_ViewBinding(final ViewBillDialogHostFragment viewBillDialogHostFragment, View view) {
        this.target = viewBillDialogHostFragment;
        viewBillDialogHostFragment.noOfNightsCalTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.noOfNightsCalTxt, "field 'noOfNightsCalTxt'", TextView.class);
        viewBillDialogHostFragment.pricePerNight = (TextView) Utils.findRequiredViewAsType(view, R.id.price_per_night, "field 'pricePerNight'", TextView.class);
        viewBillDialogHostFragment.serviceFeeTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.serviceFeeTxt, "field 'serviceFeeTxt'", TextView.class);
        viewBillDialogHostFragment.serviceFeeAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.serviceFeeAmount, "field 'serviceFeeAmount'", TextView.class);
        viewBillDialogHostFragment.taxTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tax_tv, "field 'taxTv'", TextView.class);
        viewBillDialogHostFragment.taxFeeAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.tax_fee_amount, "field 'taxFeeAmount'", TextView.class);
        viewBillDialogHostFragment.totalAmountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.total_amount_TV, "field 'totalAmountTv'", TextView.class);
        viewBillDialogHostFragment.totalAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.total_amount, "field 'totalAmount'", TextView.class);
        viewBillDialogHostFragment.layout_discount = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_discount, "field 'layout_discount'", LinearLayout.class);
        viewBillDialogHostFragment.discountAmount = (TextView) Utils.findRequiredViewAsType(view, R.id.discount_amount, "field 'discountAmount'", TextView.class);
        viewBillDialogHostFragment.discount_view = Utils.findRequiredView(view, R.id.discount_view, "field 'discount_view'");
        viewBillDialogHostFragment.bookingId = (TextView) Utils.findRequiredViewAsType(view, R.id.booking_id, "field 'bookingId'", TextView.class);
        view = Utils.findRequiredView(view, R.id.img_close, "method 'clickOnClose'");
        this.view2131362246 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                viewBillDialogHostFragment.clickOnClose();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ViewBillDialogHostFragment viewBillDialogHostFragment = this.target;
        if (viewBillDialogHostFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        viewBillDialogHostFragment.noOfNightsCalTxt = null;
        viewBillDialogHostFragment.pricePerNight = null;
        viewBillDialogHostFragment.serviceFeeTxt = null;
        viewBillDialogHostFragment.serviceFeeAmount = null;
        viewBillDialogHostFragment.taxTv = null;
        viewBillDialogHostFragment.taxFeeAmount = null;
        viewBillDialogHostFragment.totalAmountTv = null;
        viewBillDialogHostFragment.totalAmount = null;
        viewBillDialogHostFragment.layout_discount = null;
        viewBillDialogHostFragment.discountAmount = null;
        viewBillDialogHostFragment.discount_view = null;
        viewBillDialogHostFragment.bookingId = null;
        this.view2131362246.setOnClickListener(null);
        this.view2131362246 = null;
    }
}
