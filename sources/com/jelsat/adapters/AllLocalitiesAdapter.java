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

public class AllLocalitiesAdapter extends Adapter<AllLocalitiesViewHolder> {
    private Context context;
    private OnListItemClickListener listItemClickListener;
    private List<Locality> localities;

    public interface OnListItemClickListener {
        void clickOnListItem(Locality locality, int i);
    }

    public class AllLocalitiesViewHolder extends ViewHolder {
        @BindView(2131362007)
        ImageView cityImageView;
        @BindView(2131362009)
        TextView cityNameTV;
        View itemView;

        public AllLocalitiesViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class AllLocalitiesViewHolder_ViewBinding implements Unbinder {
        private AllLocalitiesViewHolder target;

        @UiThread
        public AllLocalitiesViewHolder_ViewBinding(AllLocalitiesViewHolder allLocalitiesViewHolder, View view) {
            this.target = allLocalitiesViewHolder;
            allLocalitiesViewHolder.cityImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.city_imageView, "field 'cityImageView'", ImageView.class);
            allLocalitiesViewHolder.cityNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.city_name_TV, "field 'cityNameTV'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            AllLocalitiesViewHolder allLocalitiesViewHolder = this.target;
            if (allLocalitiesViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            allLocalitiesViewHolder.cityImageView = null;
            allLocalitiesViewHolder.cityNameTV = null;
        }
    }

    public AllLocalitiesAdapter(Context context, List<Locality> list, OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.localities = list;
        this.listItemClickListener = onListItemClickListener;
    }

    public AllLocalitiesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AllLocalitiesViewHolder(LayoutInflater.from(this.context).inflate(R.layout.all_localities_list_item, viewGroup, false));
    }

    public void onBindViewHolder(AllLocalitiesViewHolder allLocalitiesViewHolder, final int i) {
        final Locality locality = (Locality) this.localities.get(i);
        GlideApp.with(this.context).load(locality.getImage()).placeholder((int) R.drawable.default_logo).error((int) R.drawable.default_logo).apply(new RequestOptions().transform(new RoundedCorners(8))).into(allLocalitiesViewHolder.cityImageView);
        allLocalitiesViewHolder.cityNameTV.setText(locality.getName());
        allLocalitiesViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AllLocalitiesAdapter.this.listItemClickListener.clickOnListItem(locality, i);
            }
        });
    }

    public int getItemCount() {
        return this.localities != null ? this.localities.size() : 0;
    }

    public void setData(List<Locality> list) {
        this.localities = list;
        notifyDataSetChanged();
    }
}
