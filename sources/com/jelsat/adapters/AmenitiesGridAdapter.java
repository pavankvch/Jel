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

public class AmenitiesGridAdapter extends Adapter<ViewHolder> {
    private List<Amenity> amenityList;
    private Context context;
    private AmenitiesGridAdapter$ItemClickListener listener;

    class AmenityViewHolder extends ViewHolder {
        @BindView(2131361867)
        ImageView amenityIcon;
        @BindView(2131361869)
        TextView amenityName;

        public AmenityViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class AmenityViewHolder_ViewBinding implements Unbinder {
        private AmenityViewHolder target;

        @UiThread
        public AmenityViewHolder_ViewBinding(AmenityViewHolder amenityViewHolder, View view) {
            this.target = amenityViewHolder;
            amenityViewHolder.amenityName = (TextView) Utils.findRequiredViewAsType(view, R.id.amenity_nameTV, "field 'amenityName'", TextView.class);
            amenityViewHolder.amenityIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.amenity_icon, "field 'amenityIcon'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            AmenityViewHolder amenityViewHolder = this.target;
            if (amenityViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            amenityViewHolder.amenityName = null;
            amenityViewHolder.amenityIcon = null;
        }
    }

    class ShowMoreViewHolder extends ViewHolder {
        public ShowMoreViewHolder(View view) {
            super(view);
        }
    }

    public int getItemViewType(int i) {
        return i != 7 ? R.layout.amenities_grid_item : R.layout.amenities_more;
    }

    public AmenitiesGridAdapter(Context context, List<Amenity> list, AmenitiesGridAdapter$ItemClickListener amenitiesGridAdapter$ItemClickListener) {
        this.context = context;
        this.amenityList = list;
        this.listener = amenitiesGridAdapter$ItemClickListener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        viewGroup = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        if (i == R.layout.amenities_grid_item) {
            return new AmenityViewHolder(viewGroup);
        }
        if (i != R.layout.amenities_more) {
            return new AmenityViewHolder(viewGroup);
        }
        return new ShowMoreViewHolder(viewGroup);
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int itemViewType = viewHolder.getItemViewType();
        if (itemViewType != R.layout.amenities_grid_item) {
            if (itemViewType == R.layout.amenities_more) {
                viewHolder.itemView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        AmenitiesGridAdapter.this.listener.clickOnItem(AmenitiesGridAdapter.this.amenityList);
                    }
                });
            }
            return;
        }
        Amenity amenity = (Amenity) this.amenityList.get(i);
        Context context = this.context;
        StringBuilder stringBuilder = new StringBuilder("ic_");
        stringBuilder.append(amenity.getId());
        itemViewType = getResourseId(context, stringBuilder.toString());
        AmenityViewHolder amenityViewHolder = (AmenityViewHolder) viewHolder;
        amenityViewHolder.amenityName.setText(amenity.getName());
        GlideApp.with(this.context).asBitmap().load(amenity.getImage()).placeholder(itemViewType).error(itemViewType).into(amenityViewHolder.amenityIcon);
    }

    private int getResourseId(Context context, String str) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        } catch (Context context2) {
            throw new RuntimeException("Error getting Resource ID.", context2);
        }
    }

    public void setData(List<Amenity> list) {
        this.amenityList = list;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        if (this.amenityList == null) {
            return 0;
        }
        if (this.amenityList.size() > 7) {
            return 8;
        }
        return this.amenityList.size();
    }
}
