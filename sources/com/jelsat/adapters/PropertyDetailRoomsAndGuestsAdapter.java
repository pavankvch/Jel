package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.propertydetail.PropertyRoomsAndGuests;
import com.jelsat.R;
import com.jelsat.customclasses.CustomTextView;
import java.util.List;

public class PropertyDetailRoomsAndGuestsAdapter extends Adapter<ViewHolder> {
    private Context context;
    private List<PropertyRoomsAndGuests> list;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(2131362457)
        CustomTextView propertyComponent;

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
            viewHolder.propertyComponent = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.property_component, "field 'propertyComponent'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.propertyComponent = null;
        }
    }

    public PropertyDetailRoomsAndGuestsAdapter(Context context, List<PropertyRoomsAndGuests> list) {
        this.context = context;
        this.list = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.property_detail_rooms_and_guests_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.propertyComponent.setText(((PropertyRoomsAndGuests) this.list.get(i)).getPropertyComponentName());
        viewHolder.propertyComponent.setCompoundDrawablesWithIntrinsicBounds(0, ((PropertyRoomsAndGuests) this.list.get(i)).getPropertyComponentDrawable(), 0, 0);
    }

    public int getItemCount() {
        return this.list != null ? this.list.size() : 0;
    }

    public void setData(List<PropertyRoomsAndGuests> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
