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
import android.widget.CheckedTextView;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.Houserule;
import com.jelsat.R;
import java.util.List;

public class HouseRulesAdapter extends Adapter<HouseRulesViewHolder> {
    private Context context;
    private List<Houserule> houseRuleList;
    private boolean isFromFilters;
    private boolean isLoadAll;
    private HouseRulesAdapter$OnListItemClickListener listItemClickListener;

    public class HouseRulesViewHolder extends ViewHolder {
        @BindView(2131361865)
        ImageView amenityImageview;
        @BindView(2131362001)
        CheckedTextView houseRule;
        View itemView;

        public HouseRulesViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class HouseRulesViewHolder_ViewBinding implements Unbinder {
        private HouseRulesViewHolder target;

        @UiThread
        public HouseRulesViewHolder_ViewBinding(HouseRulesViewHolder houseRulesViewHolder, View view) {
            this.target = houseRulesViewHolder;
            houseRulesViewHolder.houseRule = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.checked_TV, "field 'houseRule'", CheckedTextView.class);
            houseRulesViewHolder.amenityImageview = (ImageView) Utils.findRequiredViewAsType(view, R.id.amenityImageview, "field 'amenityImageview'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            HouseRulesViewHolder houseRulesViewHolder = this.target;
            if (houseRulesViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            houseRulesViewHolder.houseRule = null;
            houseRulesViewHolder.amenityImageview = null;
        }
    }

    public HouseRulesAdapter(Context context, List<Houserule> list, HouseRulesAdapter$OnListItemClickListener houseRulesAdapter$OnListItemClickListener, boolean z, boolean z2) {
        this.context = context;
        this.houseRuleList = list;
        this.listItemClickListener = houseRulesAdapter$OnListItemClickListener;
        this.isLoadAll = z;
        this.isFromFilters = z2;
    }

    public HouseRulesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HouseRulesViewHolder(LayoutInflater.from(this.context).inflate(R.layout.checked_textview_list_item, viewGroup, false));
    }

    public void onBindViewHolder(HouseRulesViewHolder houseRulesViewHolder, final int i) {
        final Houserule houserule = (Houserule) this.houseRuleList.get(i);
        houseRulesViewHolder.amenityImageview.setVisibility(8);
        houseRulesViewHolder.houseRule.setText(houserule.getName());
        houseRulesViewHolder.houseRule.setChecked(houserule.getChecked());
        houseRulesViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HouseRulesAdapter.this.listItemClickListener.clickOnListItem(houserule, i, houserule.getChecked() ^ 1);
            }
        });
    }

    public int getItemCount() {
        int size;
        if (this.isFromFilters) {
            if (this.houseRuleList != null) {
                if (this.houseRuleList.size() <= 4) {
                    size = this.houseRuleList.size();
                } else if (this.isLoadAll) {
                    return this.houseRuleList.size();
                } else {
                    return 4;
                }
            }
            return 0;
        }
        if (this.houseRuleList != null) {
            size = this.houseRuleList.size();
        }
        return 0;
        return size;
    }

    public void setItemStatusChanged(int i, boolean z) {
        ((Houserule) this.houseRuleList.get(i)).setChecked(z);
        notifyItemChanged(i);
    }

    public void loadAllItems(boolean z) {
        this.isLoadAll = z;
        notifyDataSetChanged();
    }

    public void resetAllData() {
        for (Houserule checked : this.houseRuleList) {
            checked.setChecked(false);
        }
        this.isLoadAll = false;
        notifyDataSetChanged();
    }

    public void setData(List<Houserule> list) {
        this.houseRuleList = list;
        notifyDataSetChanged();
    }
}
