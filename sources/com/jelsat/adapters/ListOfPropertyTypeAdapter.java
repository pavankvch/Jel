package com.jelsat.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.PropertyType;
import com.jelsat.R;
import java.util.List;

public class ListOfPropertyTypeAdapter extends Adapter<ViewHolder> {
    private OnListItemClickListener listItemClickListener;
    private List<PropertyType> propertyTypes;

    public interface OnListItemClickListener {
        void clickOnListItem(PropertyType propertyType, int i);
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder implements OnClickListener {
        @BindView(2131362484)
        AppCompatRadioButton propertyTypeRadioButton;
        @BindView(2131362483)
        TextView propertyTypeTV;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            ListOfPropertyTypeAdapter.this.listItemClickListener.clickOnListItem((PropertyType) ListOfPropertyTypeAdapter.this.propertyTypes.get(getAdapterPosition()), getAdapterPosition());
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.propertyTypeTV = (TextView) Utils.findRequiredViewAsType(view, R.id.property_type_Tv, "field 'propertyTypeTV'", TextView.class);
            viewHolder.propertyTypeRadioButton = (AppCompatRadioButton) Utils.findRequiredViewAsType(view, R.id.property_type_radio_button, "field 'propertyTypeRadioButton'", AppCompatRadioButton.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.propertyTypeTV = null;
            viewHolder.propertyTypeRadioButton = null;
        }
    }

    public ListOfPropertyTypeAdapter(List<PropertyType> list, OnListItemClickListener onListItemClickListener) {
        this.propertyTypes = list;
        this.listItemClickListener = onListItemClickListener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_of_property_type_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PropertyType propertyType = (PropertyType) this.propertyTypes.get(i);
        viewHolder.propertyTypeTV.setText(propertyType.getName());
        viewHolder.propertyTypeRadioButton.setChecked(propertyType.getChecked());
    }

    public int getItemCount() {
        return this.propertyTypes != null ? this.propertyTypes.size() : 0;
    }

    public void setItemStatusChanged(PropertyType propertyType, int i, int i2) {
        ((PropertyType) this.propertyTypes.get(i)).setChecked(false);
        notifyItemChanged(i);
        ((PropertyType) this.propertyTypes.get(i2)).setChecked(1);
        notifyItemChanged(i2);
    }
}
