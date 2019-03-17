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
import android.widget.CheckedTextView;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.PropertyType;
import com.jelsat.R;
import java.util.List;

public class PropertyTypeAdapter extends Adapter<PropertyTypeViewHolder> {
    private Context context;
    private OnListItemClickListener listItemClickListener;
    private List<PropertyType> propertyTypes;

    public interface OnListItemClickListener {
        void clickOnListItem(PropertyType propertyType, int i, boolean z);
    }

    public class PropertyTypeViewHolder extends ViewHolder {
        @BindView(2131361865)
        ImageView imageView;
        @BindView(2131362001)
        CheckedTextView propertyTypeName;

        public PropertyTypeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class PropertyTypeViewHolder_ViewBinding implements Unbinder {
        private PropertyTypeViewHolder target;

        @UiThread
        public PropertyTypeViewHolder_ViewBinding(PropertyTypeViewHolder propertyTypeViewHolder, View view) {
            this.target = propertyTypeViewHolder;
            propertyTypeViewHolder.propertyTypeName = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.checked_TV, "field 'propertyTypeName'", CheckedTextView.class);
            propertyTypeViewHolder.imageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.amenityImageview, "field 'imageView'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PropertyTypeViewHolder propertyTypeViewHolder = this.target;
            if (propertyTypeViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            propertyTypeViewHolder.propertyTypeName = null;
            propertyTypeViewHolder.imageView = null;
        }
    }

    public PropertyTypeAdapter(Context context, List<PropertyType> list, OnListItemClickListener onListItemClickListener) {
        this.context = context;
        this.propertyTypes = list;
        this.listItemClickListener = onListItemClickListener;
    }

    @NonNull
    public PropertyTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PropertyTypeViewHolder(LayoutInflater.from(this.context).inflate(R.layout.checked_textview_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull PropertyTypeViewHolder propertyTypeViewHolder, final int i) {
        final PropertyType propertyType = (PropertyType) this.propertyTypes.get(i);
        propertyTypeViewHolder.imageView.setVisibility(8);
        propertyTypeViewHolder.propertyTypeName.setChecked(propertyType.getChecked());
        propertyTypeViewHolder.propertyTypeName.setText(propertyType.getName());
        propertyTypeViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PropertyTypeAdapter.this.listItemClickListener.clickOnListItem(propertyType, i, propertyType.getChecked() ^ 1);
            }
        });
    }

    public int getItemCount() {
        return this.propertyTypes != null ? this.propertyTypes.size() : 0;
    }

    public void setData(List<PropertyType> list) {
        this.propertyTypes = list;
        notifyDataSetChanged();
    }

    public void setItemStatusChanged(PropertyType propertyType, int i, boolean z) {
        ((PropertyType) this.propertyTypes.get(i)).setChecked(z);
        notifyItemChanged(i);
    }
}
