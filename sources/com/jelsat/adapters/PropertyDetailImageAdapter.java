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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jelsat.R;
import com.jelsat.utils.GlideApp;
import java.util.List;

public class PropertyDetailImageAdapter extends Adapter<PropertyDetailImageViewHolder> {
    private Context context;
    private int deviceHeight;
    private List<String> imageTitle;
    private List<String> imageUrlList;
    private PropertyDetailImageAdapter$ImageClickListener listener;

    public class PropertyDetailImageViewHolder extends ViewHolder {
        @BindView(2131362460)
        ImageView propertyDetailImage;

        public PropertyDetailImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class PropertyDetailImageViewHolder_ViewBinding implements Unbinder {
        private PropertyDetailImageViewHolder target;

        @UiThread
        public PropertyDetailImageViewHolder_ViewBinding(PropertyDetailImageViewHolder propertyDetailImageViewHolder, View view) {
            this.target = propertyDetailImageViewHolder;
            propertyDetailImageViewHolder.propertyDetailImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.property_detail_image, "field 'propertyDetailImage'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            PropertyDetailImageViewHolder propertyDetailImageViewHolder = this.target;
            if (propertyDetailImageViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            propertyDetailImageViewHolder.propertyDetailImage = null;
        }
    }

    public PropertyDetailImageAdapter(Context context, List<String> list, List<String> list2, PropertyDetailImageAdapter$ImageClickListener propertyDetailImageAdapter$ImageClickListener) {
        this.context = context;
        this.imageUrlList = list;
        this.imageTitle = list2;
        this.listener = propertyDetailImageAdapter$ImageClickListener;
        this.deviceHeight = com.jelsat.utils.Utils.getScreenHeightInPixels(context.getApplicationContext());
    }

    @NonNull
    public PropertyDetailImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ImageView imageView = (ImageView) LayoutInflater.from(this.context).inflate(R.layout.property_detail_image_list_item, viewGroup, false);
        imageView.setMinimumHeight(this.deviceHeight / 2);
        imageView.setMaxHeight(this.deviceHeight / 2);
        return new PropertyDetailImageViewHolder(imageView);
    }

    public void onBindViewHolder(@NonNull PropertyDetailImageViewHolder propertyDetailImageViewHolder, final int i) {
        GlideApp.with(this.context).asBitmap().load((String) this.imageUrlList.get(i)).placeholder((int) R.drawable.default_logo).error((int) R.drawable.default_logo).diskCacheStrategy(DiskCacheStrategy.DATA).into(propertyDetailImageViewHolder.propertyDetailImage);
        propertyDetailImageViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PropertyDetailImageAdapter.this.listener.clickOnImage(PropertyDetailImageAdapter.this.imageUrlList, PropertyDetailImageAdapter.this.imageTitle, i);
            }
        });
    }

    public int getItemCount() {
        return this.imageUrlList != null ? this.imageUrlList.size() : 0;
    }

    public void setData(List<String> list, List<String> list2) {
        this.imageUrlList = list;
        this.imageTitle = list2;
        notifyDataSetChanged();
    }
}
