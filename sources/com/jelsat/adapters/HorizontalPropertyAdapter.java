package com.jelsat.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.searchproperty.SearchProperty;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.activities.PropertyDetailActivity;
import com.jelsat.compoundviews.PropertyCustomView;
import com.jelsat.compoundviews.PropertyCustomView.ViewToAdapterListener;
import com.jelsat.events.PropertyDetailEvent;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class HorizontalPropertyAdapter extends Adapter<PropertyViewHolder> implements ViewToAdapterListener {
    private Context context;
    private List<SearchProperty> propertyList;
    private String sectionHeader;

    class PropertyViewHolder extends ViewHolder {
        PropertyCustomView propertyListItem;

        PropertyViewHolder(View view) {
            super(view);
            this.propertyListItem = (PropertyCustomView) view.findViewById(R.id.property_list_item);
        }
    }

    public HorizontalPropertyAdapter(Context context, String str) {
        this.context = context;
        this.sectionHeader = str;
    }

    @NonNull
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PropertyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.horizontal_recyclerview_property_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull PropertyViewHolder propertyViewHolder, final int i) {
        propertyViewHolder.propertyListItem.setProperty((SearchProperty) this.propertyList.get(i), this.sectionHeader, i);
        propertyViewHolder.propertyListItem.setViewToAdapterListener(this);
        propertyViewHolder.propertyListItem.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                view = new PropertyDetailEvent((SearchProperty) HorizontalPropertyAdapter.this.propertyList.get(i));
                if (HorizontalPropertyAdapter.this.isUserLoggedIn()) {
                    view.setShowMessage(true);
                }
                EventBus.getDefault().postSticky(view);
                HorizontalPropertyAdapter.this.context.startActivity(new Intent(HorizontalPropertyAdapter.this.context, PropertyDetailActivity.class));
            }
        });
    }

    private boolean isUserLoggedIn() {
        return (PrefUtils.getInstance().getUserAccessToken() == null || PrefUtils.getInstance().getCookie() == null) ? false : true;
    }

    public int getItemCount() {
        return this.propertyList != null ? this.propertyList.size() : 0;
    }

    public void changeFavourite(PropertyFavouriteResponse propertyFavouriteResponse, int i) {
        ((SearchProperty) this.propertyList.get(i)).setFavourite(Integer.parseInt(propertyFavouriteResponse.getFavourite()));
        notifyItemChanged(i);
    }

    public void setData(List<SearchProperty> list) {
        this.propertyList = list;
        notifyDataSetChanged();
    }
}
