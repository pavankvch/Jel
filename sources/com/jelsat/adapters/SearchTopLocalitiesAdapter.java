package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
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
import com.data.searchtoplocalities.Locality;
import com.jelsat.R;
import com.jelsat.adapters.RecyclerViewSectionAdapter.GetObject;
import java.util.List;

public class SearchTopLocalitiesAdapter extends Adapter<SearchTopLocalitiesViewHolder> implements GetObject<Locality> {
    private Context context;
    private OnListItemClickListener listItemClickListener;
    private List<Locality> localityList;

    public interface OnListItemClickListener {
        void clickOnListItem(Locality locality, int i);
    }

    public class SearchTopLocalitiesViewHolder extends ViewHolder {
        View itemView;
        @BindView(2131362571)
        TextView searchTopLocalityName;

        public SearchTopLocalitiesViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class SearchTopLocalitiesViewHolder_ViewBinding implements Unbinder {
        private SearchTopLocalitiesViewHolder target;

        @UiThread
        public SearchTopLocalitiesViewHolder_ViewBinding(SearchTopLocalitiesViewHolder searchTopLocalitiesViewHolder, View view) {
            this.target = searchTopLocalitiesViewHolder;
            searchTopLocalitiesViewHolder.searchTopLocalityName = (TextView) Utils.findRequiredViewAsType(view, R.id.search_top_locality_name, "field 'searchTopLocalityName'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SearchTopLocalitiesViewHolder searchTopLocalitiesViewHolder = this.target;
            if (searchTopLocalitiesViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            searchTopLocalitiesViewHolder.searchTopLocalityName = null;
        }
    }

    public Locality getObject(int i) {
        return (this.localityList == null || this.localityList.size() <= 0) ? 0 : (Locality) this.localityList.get(i);
    }

    public SearchTopLocalitiesAdapter(Context context, List<Locality> list, OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.localityList = list;
        this.listItemClickListener = onListItemClickListener;
    }

    @NonNull
    public SearchTopLocalitiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchTopLocalitiesViewHolder(LayoutInflater.from(this.context).inflate(R.layout.search_localities_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull SearchTopLocalitiesViewHolder searchTopLocalitiesViewHolder, final int i) {
        final Locality locality = (Locality) this.localityList.get(i);
        searchTopLocalitiesViewHolder.searchTopLocalityName.setText(locality.getName());
        searchTopLocalitiesViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchTopLocalitiesAdapter.this.listItemClickListener.clickOnListItem(locality, i);
            }
        });
    }

    public int getItemCount() {
        return this.localityList != null ? this.localityList.size() : 0;
    }

    public void setData(List<Locality> list) {
        this.localityList = list;
        notifyDataSetChanged();
    }
}
