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
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.data.searchtoplocalities.Locality;
import com.jelsat.R;
import com.jelsat.utils.GlideApp;
import java.util.List;

public class TopCitiesRecyclerViewAdapter extends Adapter<TopCitiesRecyclerViewHolder> {
    private Context context;
    private OnListItemClickListener listItemClickListener;
    private List<Locality> localityList;

    public interface OnListItemClickListener {
        void clickOnItem(Locality locality, int i);
    }

    public class TopCitiesRecyclerViewHolder extends ViewHolder {
        @BindView(2131362007)
        ImageView cityImageView;
        @BindView(2131362009)
        TextView cityNameTextView;
        public View itemView;

        public TopCitiesRecyclerViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class TopCitiesRecyclerViewHolder_ViewBinding implements Unbinder {
        private TopCitiesRecyclerViewHolder target;

        @UiThread
        public TopCitiesRecyclerViewHolder_ViewBinding(TopCitiesRecyclerViewHolder topCitiesRecyclerViewHolder, View view) {
            this.target = topCitiesRecyclerViewHolder;
            topCitiesRecyclerViewHolder.cityImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.city_imageView, "field 'cityImageView'", ImageView.class);
            topCitiesRecyclerViewHolder.cityNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.city_name_TV, "field 'cityNameTextView'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            TopCitiesRecyclerViewHolder topCitiesRecyclerViewHolder = this.target;
            if (topCitiesRecyclerViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            topCitiesRecyclerViewHolder.cityImageView = null;
            topCitiesRecyclerViewHolder.cityNameTextView = null;
        }
    }

    public void setData(List<Locality> list) {
        this.localityList = list;
        notifyDataSetChanged();
    }

    public TopCitiesRecyclerViewAdapter(Context context, List<Locality> list, OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.localityList = list;
        this.listItemClickListener = onListItemClickListener;
    }

    public TopCitiesRecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TopCitiesRecyclerViewHolder(LayoutInflater.from(this.context).inflate(R.layout.top_cities_list_item, viewGroup, false));
    }

    public void onBindViewHolder(TopCitiesRecyclerViewHolder topCitiesRecyclerViewHolder, final int i) {
        final Locality locality = (Locality) this.localityList.get(i);
        GlideApp.with(this.context).load(locality.getImage()).placeholder((int) R.drawable.default_logo_small).apply(new RequestOptions().transform(new RoundedCorners(3))).error((int) R.drawable.default_logo_small).into(topCitiesRecyclerViewHolder.cityImageView);
        topCitiesRecyclerViewHolder.cityNameTextView.setText(locality.getName());
        topCitiesRecyclerViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                TopCitiesRecyclerViewAdapter.this.listItemClickListener.clickOnItem(locality, i);
            }
        });
    }

    public int getItemCount() {
        return this.localityList != null ? this.localityList.size() : 0;
    }
}
