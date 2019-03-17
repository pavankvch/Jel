package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.CancelPolicy;
import com.jelsat.R;
import java.util.List;

public class CancellationPolicyAdapter extends Adapter<ViewHolder> {
    private List<CancelPolicy> cancelPolicyList;
    private Context context;
    private CancellationPolicyAdapter$OnListItemClickListener itemClickListener;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(2131362001)
        CheckedTextView cancellationPolicyName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.cancellationPolicyName = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.checked_TV, "field 'cancellationPolicyName'", CheckedTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.cancellationPolicyName = null;
        }
    }

    public CancellationPolicyAdapter(Context context, List<CancelPolicy> list, CancellationPolicyAdapter$OnListItemClickListener cancellationPolicyAdapter$OnListItemClickListener) {
        this.context = context;
        this.cancelPolicyList = list;
        this.itemClickListener = cancellationPolicyAdapter$OnListItemClickListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.checked_textview_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final CancelPolicy cancelPolicy = (CancelPolicy) this.cancelPolicyList.get(i);
        viewHolder.cancellationPolicyName.setText(cancelPolicy.getName());
        viewHolder.cancellationPolicyName.setChecked(cancelPolicy.getChecked());
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CancellationPolicyAdapter.this.itemClickListener.clickOnListItem(cancelPolicy, i, cancelPolicy.getChecked() ^ 1);
            }
        });
    }

    public int getItemCount() {
        return this.cancelPolicyList != null ? this.cancelPolicyList.size() : 0;
    }

    public void setItemStatusChanged(int i, boolean z) {
        ((CancelPolicy) this.cancelPolicyList.get(i)).setChecked(z);
        notifyItemChanged(i);
    }

    public void resetAllData() {
        for (CancelPolicy checked : this.cancelPolicyList) {
            checked.setChecked(false);
        }
        notifyDataSetChanged();
    }

    public void setData(List<CancelPolicy> list) {
        this.cancelPolicyList = list;
        notifyDataSetChanged();
    }
}
