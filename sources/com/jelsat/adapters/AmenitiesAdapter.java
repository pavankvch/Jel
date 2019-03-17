package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
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
import com.data.amenitiesandhouserules.Amenity;
import com.jelsat.R;
import com.jelsat.utils.GlideApp;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.List;

public class AmenitiesAdapter extends Adapter<AmenitiesViewHolders> {
    private List<Amenity> amenityList;
    private Context context;
    private boolean isFromFilters;
    private boolean isLoadAll;
    private AmenitiesAdapter$OnListItemClickListener itemClickListener;

    public class AmenitiesViewHolders extends ViewHolder {
        @BindView(2131361865)
        ImageView amenityImageview;
        @BindView(2131362001)
        CheckedTextView aminityName;
        View itemView;

        public AmenitiesViewHolders(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class AmenitiesViewHolders_ViewBinding implements Unbinder {
        private AmenitiesViewHolders target;

        @UiThread
        public AmenitiesViewHolders_ViewBinding(AmenitiesViewHolders amenitiesViewHolders, View view) {
            this.target = amenitiesViewHolders;
            amenitiesViewHolders.amenityImageview = (ImageView) Utils.findRequiredViewAsType(view, R.id.amenityImageview, "field 'amenityImageview'", ImageView.class);
            amenitiesViewHolders.aminityName = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.checked_TV, "field 'aminityName'", CheckedTextView.class);
        }

        @CallSuper
        public void unbind() {
            AmenitiesViewHolders amenitiesViewHolders = this.target;
            if (amenitiesViewHolders == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            amenitiesViewHolders.amenityImageview = null;
            amenitiesViewHolders.aminityName = null;
        }
    }

    public AmenitiesAdapter(Context context, List<Amenity> list, AmenitiesAdapter$OnListItemClickListener amenitiesAdapter$OnListItemClickListener, boolean z, boolean z2) {
        this.context = context;
        this.amenityList = list;
        this.itemClickListener = amenitiesAdapter$OnListItemClickListener;
        this.isLoadAll = z;
        this.isFromFilters = z2;
    }

    @NonNull
    public AmenitiesViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AmenitiesViewHolders(LayoutInflater.from(this.context).inflate(R.layout.checked_textview_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull AmenitiesViewHolders amenitiesViewHolders, final int i) {
        final Amenity amenity = (Amenity) this.amenityList.get(i);
        if (!this.isFromFilters) {
            amenitiesViewHolders.amenityImageview.setVisibility(0);
            int resourseId = getResourseId(this.context, getAmenityDrawableResourceName(amenity.getId()));
            GlideApp.with(this.context).load(amenity.getImage()).placeholder(resourseId).error(resourseId).into(amenitiesViewHolders.amenityImageview);
            amenity.setEnabled(true);
        }
        amenitiesViewHolders.aminityName.setText(amenity.getName());
        amenitiesViewHolders.aminityName.setChecked(amenity.getChecked());
        amenitiesViewHolders.aminityName.setEnabled(amenity.isEnabled());
        if (!amenity.isEnabled()) {
            amenitiesViewHolders.aminityName.setTextColor(ContextCompat.getColor(this.context, R.color.walletbg));
        }
        if (amenity.isEnabled()) {
            amenitiesViewHolders.itemView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    AmenitiesAdapter.this.itemClickListener.clickOnListItem(amenity, i, amenity.getChecked() ^ 1);
                }
            });
        }
    }

    public int getItemCount() {
        int size;
        if (this.isFromFilters) {
            if (this.amenityList != null) {
                if (this.amenityList.size() <= 4) {
                    size = this.amenityList.size();
                } else if (this.isLoadAll) {
                    return this.amenityList.size();
                } else {
                    return 4;
                }
            }
            return 0;
        }
        if (this.amenityList != null) {
            size = this.amenityList.size();
        }
        return 0;
        return size;
    }

    public void setItemStatusChanged(int i, boolean z) {
        ((Amenity) this.amenityList.get(i)).setChecked(z);
        notifyItemChanged(i);
    }

    public void loadAllItems(boolean z) {
        this.isLoadAll = z;
        notifyDataSetChanged();
    }

    public void resetAllData() {
        for (Amenity checked : this.amenityList) {
            checked.setChecked(false);
        }
        this.isLoadAll = false;
        notifyDataSetChanged();
    }

    public void setData(List<Amenity> list) {
        this.amenityList = list;
        notifyDataSetChanged();
    }

    private String getAmenityDrawableResourceName(String str) {
        StringBuilder stringBuilder = new StringBuilder("ic_");
        stringBuilder.append(str.replaceAll(" ", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).toLowerCase());
        return stringBuilder.toString();
    }

    private int getResourseId(Context context, String str) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        } catch (Context context2) {
            throw new RuntimeException("Error getting Resource ID.", context2);
        }
    }
}
