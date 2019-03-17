package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.PropertyType;
import com.jelsat.R;
import java.util.List;

public class AddPropertyTypeAdapter extends Adapter<ViewHolder> {
    private Context context;
    private AddPropertyTypeAdapter$OnListItemClickListener listItemClickListener;
    private List<PropertyType> propertyTypes;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(2131362491)
        RadioButton radioButton;
        @BindView(2131362489)
        TextView radioButtonTxt;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.radioButton = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_button, "field 'radioButton'", RadioButton.class);
            viewHolder.radioButtonTxt = (TextView) Utils.findRequiredViewAsType(view, R.id.radioButtonTxt, "field 'radioButtonTxt'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.radioButton = null;
            viewHolder.radioButtonTxt = null;
        }
    }

    public AddPropertyTypeAdapter(Context context, List<PropertyType> list, AddPropertyTypeAdapter$OnListItemClickListener addPropertyTypeAdapter$OnListItemClickListener) {
        this.context = context;
        this.propertyTypes = list;
        this.listItemClickListener = addPropertyTypeAdapter$OnListItemClickListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.checked_radiobuttons_items, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final PropertyType propertyType = (PropertyType) this.propertyTypes.get(i);
        viewHolder.radioButton.setChecked(propertyType.getChecked());
        viewHolder.radioButtonTxt.setText(propertyType.getName());
        viewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AddPropertyTypeAdapter.this.listItemClickListener.clickOnListItem(propertyType, i, propertyType.getChecked() ^ 1);
            }
        });
    }

    public void setData(List<PropertyType> list) {
        this.propertyTypes = list;
        notifyDataSetChanged();
    }

    public void editData(List<PropertyType> list) {
        this.propertyTypes = list;
        notifyDataSetChanged();
    }

    public void setItemStatusChanged(PropertyType propertyType, int i, int i2) {
        ((PropertyType) this.propertyTypes.get(i)).setChecked(false);
        notifyItemChanged(i);
        ((PropertyType) this.propertyTypes.get(i2)).setChecked(1);
        notifyItemChanged(i2);
    }

    public int getItemCount() {
        return this.propertyTypes != null ? this.propertyTypes.size() : 0;
    }
}
