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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.HouseSafety;
import com.jelsat.R;
import java.util.List;

public class HouseSafetyAdapter extends Adapter<HouseSafetyViewHolders> {
    private Context context;
    private List<HouseSafety> housesafetyList;
    private boolean isLoadAll;
    private HouseSafetyAdapter$OnListItemClickListener itemClickListener;

    public class HouseSafetyViewHolders extends ViewHolder {
        @BindView(2131362001)
        CheckedTextView houseSaftyName;
        View itemView;

        public HouseSafetyViewHolders(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class HouseSafetyViewHolders_ViewBinding implements Unbinder {
        private HouseSafetyViewHolders target;

        @UiThread
        public HouseSafetyViewHolders_ViewBinding(HouseSafetyViewHolders houseSafetyViewHolders, View view) {
            this.target = houseSafetyViewHolders;
            houseSafetyViewHolders.houseSaftyName = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.checked_TV, "field 'houseSaftyName'", CheckedTextView.class);
        }

        @CallSuper
        public void unbind() {
            HouseSafetyViewHolders houseSafetyViewHolders = this.target;
            if (houseSafetyViewHolders == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            houseSafetyViewHolders.houseSaftyName = null;
        }
    }

    public HouseSafetyAdapter(Context context, List<HouseSafety> list, HouseSafetyAdapter$OnListItemClickListener houseSafetyAdapter$OnListItemClickListener, boolean z) {
        this.context = context;
        this.housesafetyList = list;
        this.itemClickListener = houseSafetyAdapter$OnListItemClickListener;
        this.isLoadAll = z;
    }

    public HouseSafetyViewHolders onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HouseSafetyViewHolders(LayoutInflater.from(this.context).inflate(R.layout.checked_housesefty_list_item, viewGroup, false));
    }

    public void onBindViewHolder(HouseSafetyViewHolders houseSafetyViewHolders, final int i) {
        final HouseSafety houseSafety = (HouseSafety) this.housesafetyList.get(i);
        houseSafetyViewHolders.houseSaftyName.setText(houseSafety.getName());
        houseSafetyViewHolders.houseSaftyName.setChecked(houseSafety.getChecked());
        houseSafetyViewHolders.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                HouseSafetyAdapter.this.itemClickListener.clickOnListItem(houseSafety, i, houseSafety.getChecked() ^ 1);
            }
        });
    }

    public int getItemCount() {
        return this.housesafetyList != null ? this.housesafetyList.size() : 0;
    }

    public void setItemStatusChanged(int i, boolean z) {
        ((HouseSafety) this.housesafetyList.get(i)).setChecked(z);
        notifyItemChanged(i);
    }

    public void setData(List<HouseSafety> list) {
        this.housesafetyList = list;
        notifyDataSetChanged();
    }
}
