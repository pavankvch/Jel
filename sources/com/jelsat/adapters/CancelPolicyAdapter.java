package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.CancelPolicy;
import com.jelsat.R;
import com.jelsat.activities.CancelationPolices;
import java.util.List;

public class CancelPolicyAdapter extends Adapter<CancelPolicyAdapterViewHolder> {
    private List<CancelPolicy> cancelPolicies;
    CancelationPolices cancelationPolices;
    private Context context;
    private RadioButton lastCheckedRB = null;
    private OnListItemClickListener listItemClickListener;
    int mCheckedPosition = -1;

    public interface OnListItemClickListener {
        void clickOnListItem(CancelPolicy cancelPolicy, int i, boolean z);
    }

    public class CancelPolicyAdapterViewHolder extends ViewHolder {
        @BindView(2131362002)
        RadioButton canceltype;
        View itemView;
        @BindView(2131362425)
        TextView policy_description;
        @BindView(2131362489)
        TextView radioButtonTxt;

        public CancelPolicyAdapterViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class CancelPolicyAdapterViewHolder_ViewBinding implements Unbinder {
        private CancelPolicyAdapterViewHolder target;

        @UiThread
        public CancelPolicyAdapterViewHolder_ViewBinding(CancelPolicyAdapterViewHolder cancelPolicyAdapterViewHolder, View view) {
            this.target = cancelPolicyAdapterViewHolder;
            cancelPolicyAdapterViewHolder.canceltype = (RadioButton) Utils.findRequiredViewAsType(view, R.id.checked_policy, "field 'canceltype'", RadioButton.class);
            cancelPolicyAdapterViewHolder.radioButtonTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.radioButtonTxt, "field 'radioButtonTxt'", TextView.class);
            cancelPolicyAdapterViewHolder.policy_description = (TextView) Utils.findRequiredViewAsType(view, R.id.policy_description, "field 'policy_description'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            CancelPolicyAdapterViewHolder cancelPolicyAdapterViewHolder = this.target;
            if (cancelPolicyAdapterViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            cancelPolicyAdapterViewHolder.canceltype = null;
            cancelPolicyAdapterViewHolder.radioButtonTxt = null;
            cancelPolicyAdapterViewHolder.policy_description = null;
        }
    }

    public CancelPolicyAdapter(Context context, List<CancelPolicy> list, OnListItemClickListener onListItemClickListener, CancelationPolices cancelationPolices) {
        this.context = context;
        this.cancelPolicies = list;
        this.listItemClickListener = onListItemClickListener;
        this.cancelationPolices = cancelationPolices;
    }

    public CancelPolicyAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CancelPolicyAdapterViewHolder(LayoutInflater.from(this.context).inflate(R.layout.cancel_policy_listview, viewGroup, false));
    }

    public void onBindViewHolder(CancelPolicyAdapterViewHolder cancelPolicyAdapterViewHolder, final int i) {
        final CancelPolicy cancelPolicy = (CancelPolicy) this.cancelPolicies.get(i);
        cancelPolicyAdapterViewHolder.radioButtonTxt.setText(cancelPolicy.getName());
        cancelPolicyAdapterViewHolder.policy_description.setText(cancelPolicy.getDescription());
        cancelPolicyAdapterViewHolder.canceltype.setOnCheckedChangeListener(null);
        cancelPolicyAdapterViewHolder.canceltype.setChecked(cancelPolicy.getChecked());
        cancelPolicyAdapterViewHolder.canceltype.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CancelPolicyAdapter.this.mCheckedPosition = i;
                CancelPolicyAdapter.this.notifyDataSetChanged();
            }
        });
        cancelPolicyAdapterViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CancelPolicyAdapter.this.listItemClickListener.clickOnListItem(cancelPolicy, i, cancelPolicy.getChecked() ^ 1);
            }
        });
    }

    public int getItemCount() {
        return this.cancelPolicies != null ? this.cancelPolicies.size() : 0;
    }

    public void setData(List<CancelPolicy> list) {
        this.cancelPolicies = list;
        notifyDataSetChanged();
    }

    public void setItemStatusChanged(CancelPolicy cancelPolicy, int i, int i2) {
        setToResetChecked();
        ((CancelPolicy) this.cancelPolicies.get(i)).setChecked(false);
        notifyItemChanged(i);
        ((CancelPolicy) this.cancelPolicies.get(i2)).setChecked(1);
        notifyItemChanged(i2);
    }

    public void setToResetChecked() {
        for (int i = 0; i < this.cancelPolicies.size(); i++) {
            if (((CancelPolicy) this.cancelPolicies.get(i)).getChecked()) {
                ((CancelPolicy) this.cancelPolicies.get(i)).setChecked(false);
            }
            notifyItemChanged(i);
        }
        notifyDataSetChanged();
    }
}
