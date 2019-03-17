package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.amenitiesandhouserules.Amenity;
import com.jelsat.R;
import com.jelsat.utils.GlideApp;
import java.util.List;

public class AmenityDialogAdapter extends Adapter<ViewHolder> {
    private List<Amenity> amenityList;
    private Context context;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(2131361867)
        ImageView amenityIcon;
        @BindView(2131361869)
        TextView amenityName;

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
            viewHolder.amenityIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.amenity_icon, "field 'amenityIcon'", ImageView.class);
            viewHolder.amenityName = (TextView) Utils.findRequiredViewAsType(view, R.id.amenity_nameTV, "field 'amenityName'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.amenityIcon = null;
            viewHolder.amenityName = null;
        }
    }

    public AmenityDialogAdapter(Context context, List<Amenity> list) {
        this.context = context;
        this.amenityList = list;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.amenity_dialog_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Amenity amenity = (Amenity) this.amenityList.get(i);
        Context context = this.context;
        StringBuilder stringBuilder = new StringBuilder("ic_");
        stringBuilder.append(amenity.getId());
        int resourseId = getResourseId(context, stringBuilder.toString());
        GlideApp.with(this.context).load(amenity.getImage()).placeholder(resourseId).error(resourseId).into(viewHolder.amenityIcon);
        viewHolder.amenityName.setText(amenity.getName());
    }

    public int getItemCount() {
        return this.amenityList != null ? this.amenityList.size() : 0;
    }

    private int getResourseId(Context context, String str) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        } catch (Context context2) {
            throw new RuntimeException("Error getting Resource ID.", context2);
        }
    }
}
