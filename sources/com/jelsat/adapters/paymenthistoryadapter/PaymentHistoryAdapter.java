package com.jelsat.adapters.paymenthistoryadapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.paymenthistory.TransactionData;
import com.jelsat.R;
import com.jelsat.adapters.Inboxmessagesadapter.SectionRecyclerViewAdapter;
import java.util.List;
import java.util.Locale;

public class PaymentHistoryAdapter extends SectionRecyclerViewAdapter<PaymentHistorySectionModel, TransactionData, SectionViewHolder, ChildViewHolder> {
    private Context context;

    public class ChildViewHolder extends ViewHolder {
        @BindView(2131362701)
        TextView amountTv;
        @BindView(2131362702)
        TextView amountTypeTv;
        @BindView(2131362338)
        ImageView moneystatusIV;
        @BindView(2131362786)
        TextView txnidTv;

        public ChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ChildViewHolder_ViewBinding implements Unbinder {
        private ChildViewHolder target;

        @UiThread
        public ChildViewHolder_ViewBinding(ChildViewHolder childViewHolder, View view) {
            this.target = childViewHolder;
            childViewHolder.moneystatusIV = (ImageView) Utils.findRequiredViewAsType(view, R.id.moneystatusIV, "field 'moneystatusIV'", ImageView.class);
            childViewHolder.amountTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_amount, "field 'amountTv'", TextView.class);
            childViewHolder.amountTypeTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_amount_type, "field 'amountTypeTv'", TextView.class);
            childViewHolder.txnidTv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_txnid, "field 'txnidTv'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ChildViewHolder childViewHolder = this.target;
            if (childViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            childViewHolder.moneystatusIV = null;
            childViewHolder.amountTv = null;
            childViewHolder.amountTypeTv = null;
            childViewHolder.txnidTv = null;
        }
    }

    public class SectionViewHolder extends ViewHolder {
        @BindView(2131362574)
        TextView section_label;

        public SectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class SectionViewHolder_ViewBinding implements Unbinder {
        private SectionViewHolder target;

        @UiThread
        public SectionViewHolder_ViewBinding(SectionViewHolder sectionViewHolder, View view) {
            this.target = sectionViewHolder;
            sectionViewHolder.section_label = (TextView) Utils.findRequiredViewAsType(view, R.id.section_label, "field 'section_label'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SectionViewHolder sectionViewHolder = this.target;
            if (sectionViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            sectionViewHolder.section_label = null;
        }
    }

    public PaymentHistoryAdapter(Context context, List<PaymentHistorySectionModel> list) {
        super(context, list);
        this.context = context;
    }

    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(this.context).inflate(R.layout.fragment_messages_date_section, viewGroup, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(this.context).inflate(R.layout.payment_history_row, viewGroup, false));
    }

    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int i, PaymentHistorySectionModel paymentHistorySectionModel) {
        sectionViewHolder.section_label.setText(paymentHistorySectionModel.getSectionLabel());
        sectionViewHolder.section_label.setGravity(17);
    }

    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int i, int i2, TransactionData transactionData) {
        childViewHolder.amountTypeTv.setText(transactionData.getTransactionName());
        i = childViewHolder.txnidTv;
        i2 = new StringBuilder();
        i2.append(this.context.getString(R.string.wallet_payment_history_transaction_id));
        i2.append(" # %s");
        i.setText(String.format(i2.toString(), new Object[]{transactionData.getTransactionId()}));
        if (transactionData.getTransactionType().equals("in") != 0) {
            childViewHolder.amountTv.setTextColor(this.context.getResources().getColor(R.color.property_instant_color));
            childViewHolder.amountTv.setText(String.format(Locale.getDefault(), "+ %.2f", new Object[]{Float.valueOf(transactionData.getAmount())}));
            childViewHolder.moneystatusIV.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_money_in));
            return;
        }
        childViewHolder.amountTv.setTextColor(this.context.getResources().getColor(R.color.resend_color));
        childViewHolder.amountTv.setText(String.format(Locale.getDefault(), "- %.2f", new Object[]{Float.valueOf(transactionData.getAmount())}));
        childViewHolder.moneystatusIV.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_money_out));
    }
}
