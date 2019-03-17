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
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.utils.FilterImage;
import com.jelsat.R;
import com.jelsat.constants.FilterConstants;
import java.util.List;

public class FilterImageAdapter extends Adapter<FilterImageViewHolder> {
    private Context context;
    private List<FilterImage> filterImageList;
    private FilterImageAdapter$OnListItemClickListener listItemClickListener;

    public class FilterImageViewHolder extends ViewHolder {
        @BindView(2131362171)
        ImageView filterIcon;
        @BindView(2131362173)
        View filterLineColor;
        View itemView;

        public FilterImageViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class FilterImageViewHolder_ViewBinding implements Unbinder {
        private FilterImageViewHolder target;

        @UiThread
        public FilterImageViewHolder_ViewBinding(FilterImageViewHolder filterImageViewHolder, View view) {
            this.target = filterImageViewHolder;
            filterImageViewHolder.filterLineColor = Utils.findRequiredView(view, R.id.filter_line_color, "field 'filterLineColor'");
            filterImageViewHolder.filterIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.filter_icon, "field 'filterIcon'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            FilterImageViewHolder filterImageViewHolder = this.target;
            if (filterImageViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            filterImageViewHolder.filterLineColor = null;
            filterImageViewHolder.filterIcon = null;
        }
    }

    public FilterImageAdapter(Context context, List<FilterImage> list, FilterImageAdapter$OnListItemClickListener filterImageAdapter$OnListItemClickListener) {
        this.context = context;
        this.filterImageList = list;
        this.listItemClickListener = filterImageAdapter$OnListItemClickListener;
        setHasStableIds(true);
    }

    public FilterImageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new FilterImageViewHolder(LayoutInflater.from(this.context).inflate(R.layout.filter_image_list_item, viewGroup, false));
    }

    public void onBindViewHolder(FilterImageViewHolder filterImageViewHolder, final int i) {
        final FilterImage filterImage = (FilterImage) this.filterImageList.get(i);
        filterImageViewHolder.filterLineColor.setBackgroundColor(getColor(filterImage.isSelected() ? R.color.app_background : R.color.textview_background_color));
        filterImageViewHolder.filterIcon.setImageResource(FilterConstants.getFilterImage(filterImage.getFilterName(), filterImage.isSelected()));
        filterImageViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FilterImageAdapter.this.listItemClickListener.clickOnItem(filterImage, i, filterImage.isSelected() ^ 1);
            }
        });
    }

    public int getColor(@ColorRes int i) {
        return ContextCompat.getColor(this.context, i);
    }

    public int getItemCount() {
        return this.filterImageList != null ? this.filterImageList.size() : 0;
    }

    public void setItemStatusChange(List<FilterImage> list, int i) {
        i = new FilterImage();
        this.filterImageList.clear();
        this.filterImageList = list;
        notifyDataSetChanged();
    }
}
