package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.utils.FilterImage;
import com.jelsat.R;
import java.util.List;

public class GuestsAdapter extends Adapter<GuestsViewHolder> {
    private OnListItemClickListener clickListener;
    private Context context;
    private List<FilterImage> list;

    public interface OnListItemClickListener {
        void clickOnListItem(FilterImage filterImage, int i, boolean z);
    }

    public class GuestsViewHolder extends ViewHolder {
        @BindView(2131362200)
        TextView guestCountTV;

        public GuestsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class GuestsViewHolder_ViewBinding implements Unbinder {
        private GuestsViewHolder target;

        @UiThread
        public GuestsViewHolder_ViewBinding(GuestsViewHolder guestsViewHolder, View view) {
            this.target = guestsViewHolder;
            guestsViewHolder.guestCountTV = (TextView) Utils.findRequiredViewAsType(view, R.id.guest_count_TV, "field 'guestCountTV'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            GuestsViewHolder guestsViewHolder = this.target;
            if (guestsViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            guestsViewHolder.guestCountTV = null;
        }
    }

    public GuestsAdapter(Context context, List<FilterImage> list, OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = onListItemClickListener;
    }

    public GuestsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GuestsViewHolder(LayoutInflater.from(this.context).inflate(R.layout.guests_list_item, viewGroup, false));
    }

    public void onBindViewHolder(GuestsViewHolder guestsViewHolder, final int i) {
        final FilterImage filterImage = (FilterImage) this.list.get(i);
        guestsViewHolder.guestCountTV.setText(filterImage.getFilterName());
        if (!filterImage.isEnabled()) {
            guestsViewHolder.guestCountTV.setTextColor(getColor(R.color.default_color));
            guestsViewHolder.guestCountTV.setEnabled(false);
        } else if (filterImage.isSelected()) {
            guestsViewHolder.guestCountTV.setBackgroundResource(R.drawable.textview_backgound);
            guestsViewHolder.guestCountTV.setTextColor(getColor(R.color.white));
        } else {
            guestsViewHolder.guestCountTV.setBackgroundColor(getColor(R.color.white));
            guestsViewHolder.guestCountTV.setTextColor(getColor(R.color.normal_text_color));
        }
        if (filterImage.isEnabled()) {
            guestsViewHolder.guestCountTV.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    GuestsAdapter.this.clickListener.clickOnListItem(filterImage, i, filterImage.isSelected() ^ 1);
                }
            });
        }
    }

    private int getColor(@ColorRes int i) {
        return ContextCompat.getColor(this.context, i);
    }

    public int getItemCount() {
        return this.list != null ? this.list.size() : 0;
    }

    public void setData(List<FilterImage> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setItemStatusChanged(FilterImage filterImage, int i, int i2) {
        if (i != i2) {
            ((FilterImage) this.list.get(i)).setSelected(false);
            notifyItemChanged(i);
            ((FilterImage) this.list.get(i2)).setSelected(1);
            notifyItemChanged(i2);
        }
    }
}
