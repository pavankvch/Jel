package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class WalletActivity_ViewBinding implements Unbinder {
    private WalletActivity target;
    private View view2131361892;
    private View view2131361937;
    private View view2131361938;

    @UiThread
    public WalletActivity_ViewBinding(WalletActivity walletActivity) {
        this(walletActivity, walletActivity.getWindow().getDecorView());
    }

    @UiThread
    public WalletActivity_ViewBinding(final WalletActivity walletActivity, View view) {
        this.target = walletActivity;
        walletActivity.promoAmountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_promoamount, "field 'promoAmountTv'", TextView.class);
        walletActivity.refundamountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_refundamount, "field 'refundamountTv'", TextView.class);
        walletActivity.totalBalanceTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_balance, "field 'totalBalanceTv'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.btn_refund, "field 'refundButton' and method 'clickOnRefund'");
        walletActivity.refundButton = (FancyButton) Utils.castView(findRequiredView, R.id.btn_refund, "field 'refundButton'", FancyButton.class);
        this.view2131361938 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                walletActivity.clickOnRefund();
            }
        });
        walletActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.cards_recyclerview, "field 'recyclerView'", RecyclerView.class);
        walletActivity.swipeContainer = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeContainer'", SwipeRefreshLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                walletActivity.clickOnBack();
            }
        });
        view = Utils.findRequiredView(view, R.id.btn_payment_history, "method 'clickOnPaymentHistory'");
        this.view2131361937 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                walletActivity.clickOnPaymentHistory();
            }
        });
    }

    @CallSuper
    public void unbind() {
        WalletActivity walletActivity = this.target;
        if (walletActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        walletActivity.promoAmountTv = null;
        walletActivity.refundamountTv = null;
        walletActivity.totalBalanceTv = null;
        walletActivity.refundButton = null;
        walletActivity.recyclerView = null;
        walletActivity.swipeContainer = null;
        this.view2131361938.setOnClickListener(null);
        this.view2131361938 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131361937.setOnClickListener(null);
        this.view2131361937 = null;
    }
}
