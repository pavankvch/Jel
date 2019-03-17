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
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.data.addproperty.ImageUploadResponse;
import com.data.utils.AddPropertyOtherImage;
import com.jelsat.R;
import com.jelsat.utils.GlideApp;
import java.util.List;

public class OtherImagesAdapter extends Adapter<ViewHolder> {
    private Context context;
    private List<AddPropertyOtherImage> imageList;
    private OtherImagesAdapter$OnListItemClickListener listener;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(2131362241)
        TextView imageTitle;
        @BindView(2131362387)
        ImageView otherImage;

        @OnClick({2131362086})
        public void clickOnDeleteImage() {
            if (OtherImagesAdapter.this.listener != null) {
                OtherImagesAdapter.this.listener.clickOnCloseImage((AddPropertyOtherImage) OtherImagesAdapter.this.imageList.get(getAdapterPosition()), getAdapterPosition());
            }
        }

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;
        private View view2131362086;

        @UiThread
        public ViewHolder_ViewBinding(final ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.otherImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.other_image, "field 'otherImage'", ImageView.class);
            viewHolder.imageTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.image_title, "field 'imageTitle'", TextView.class);
            view = Utils.findRequiredView(view, R.id.delete_image, "method 'clickOnDeleteImage'");
            this.view2131362086 = view;
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    viewHolder.clickOnDeleteImage();
                }
            });
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.otherImage = null;
            viewHolder.imageTitle = null;
            this.view2131362086.setOnClickListener(null);
            this.view2131362086 = null;
        }
    }

    public OtherImagesAdapter(Context context, List<AddPropertyOtherImage> list, OtherImagesAdapter$OnListItemClickListener otherImagesAdapter$OnListItemClickListener) {
        this.context = context;
        this.imageList = list;
        this.listener = otherImagesAdapter$OnListItemClickListener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_upload_row, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AddPropertyOtherImage addPropertyOtherImage = (AddPropertyOtherImage) this.imageList.get(i);
        viewHolder.imageTitle.setText(addPropertyOtherImage.getTitle());
        if (addPropertyOtherImage.isUploaded()) {
            GlideApp.with(this.context).asBitmap().load(addPropertyOtherImage.getUrl()).placeholder((int) R.drawable.ic_add_image).apply(new RequestOptions().transform(new RoundedCorners(8))).error((int) R.drawable.ic_add_image).diskCacheStrategy(DiskCacheStrategy.DATA).into(viewHolder.otherImage);
        } else {
            viewHolder.otherImage.setImageBitmap(addPropertyOtherImage.getBitmap());
        }
    }

    public int getItemCount() {
        return this.imageList != null ? this.imageList.size() : 0;
    }

    public void setData(List<AddPropertyOtherImage> list) {
        this.imageList = list;
        notifyDataSetChanged();
    }

    public void updateItem(ImageUploadResponse imageUploadResponse, int i) {
        ((AddPropertyOtherImage) this.imageList.get(i)).setUploaded(true);
        ((AddPropertyOtherImage) this.imageList.get(i)).setUrl(imageUploadResponse.getUrl());
        ((AddPropertyOtherImage) this.imageList.get(i)).setId(imageUploadResponse.getId());
    }
}
