package com.jelsat.adapters.ListingAdapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.data.propertylisting.Published;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.adapters.Inboxmessagesadapter.SectionRecyclerViewAdapter;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.utils.GlideApp;
import com.jelsat.widgets.FancyButton;
import java.util.List;

public class PropertyListingAdapaterSectionRecycler extends SectionRecyclerViewAdapter<PropertyListingSectionModel, Published, SectionViewHolder, ChildViewHolder> {
    private Context context;
    private OnListItemClickListener listItemClickListener;

    public interface OnListItemClickListener {
        void clickOnListItem(int i, int i2, String str, int i3);
    }

    public class ChildViewHolder extends ViewHolder {
        @BindView(2131361955)
        FancyButton calender;
        @BindView(2131362006)
        ProgressBar circularProgressbar;
        @BindView(2131362031)
        TextView completePublish;
        @BindView(2131362084)
        ImageView delete;
        @BindView(2131362111)
        FancyButton edit;
        @BindView(2131362112)
        LinearLayout editAndCalender;
        @BindView(2131362181)
        LinearLayout forPublishLay;
        @BindView(2131362182)
        LinearLayout forUnpublishLay;
        @BindView(2131362235)
        FrameLayout imagButtomLine;
        @BindView(2131362249)
        ImageView img_property;
        @BindView(2131362441)
        RelativeLayout progress_lay;
        @BindView(2131362444)
        CustomTextView propertyAddress;
        @BindView(2131362464)
        FrameLayout propertyFrameLayout;
        @BindView(2131362467)
        TextView property_id;
        @BindView(2131362476)
        TextView property_name;
        @BindView(2131362486)
        TextView publishYourProperty;
        @BindView(2131362698)
        ImageView transperencyImage;
        @BindView(2131362699)
        TextView tv;
        @BindView(2131362749)
        TextView tv_price;
        @BindView(2131362795)
        RelativeLayout unpublishLay;

        public ChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ChildViewHolder_ViewBinding implements Unbinder {
        private ChildViewHolder target;

        @UiThread
        public ChildViewHolder_ViewBinding(ChildViewHolder childViewHolder, View view) {
            this.target = childViewHolder;
            childViewHolder.img_property = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_property, "field 'img_property'", ImageView.class);
            childViewHolder.tv_price = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_price, "field 'tv_price'", TextView.class);
            childViewHolder.delete = (ImageView) Utils.findRequiredViewAsType(view, R.id.delete, "field 'delete'", ImageView.class);
            childViewHolder.imagButtomLine = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.imagButtomLine, "field 'imagButtomLine'", FrameLayout.class);
            childViewHolder.tv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv, "field 'tv'", TextView.class);
            childViewHolder.circularProgressbar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.circularProgressbar, "field 'circularProgressbar'", ProgressBar.class);
            childViewHolder.progress_lay = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.progress_lay, "field 'progress_lay'", RelativeLayout.class);
            childViewHolder.propertyFrameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.property_frameLayout, "field 'propertyFrameLayout'", FrameLayout.class);
            childViewHolder.transperencyImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.transperency_image, "field 'transperencyImage'", ImageView.class);
            childViewHolder.publishYourProperty = (TextView) Utils.findRequiredViewAsType(view, R.id.publishYourProperty, "field 'publishYourProperty'", TextView.class);
            childViewHolder.unpublishLay = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.unpublish_lay, "field 'unpublishLay'", RelativeLayout.class);
            childViewHolder.forUnpublishLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.forUnpublishLay, "field 'forUnpublishLay'", LinearLayout.class);
            childViewHolder.property_id = (TextView) Utils.findRequiredViewAsType(view, R.id.property_id, "field 'property_id'", TextView.class);
            childViewHolder.completePublish = (TextView) Utils.findRequiredViewAsType(view, R.id.completePublish, "field 'completePublish'", TextView.class);
            childViewHolder.forPublishLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.forPublishLay, "field 'forPublishLay'", LinearLayout.class);
            childViewHolder.property_name = (TextView) Utils.findRequiredViewAsType(view, R.id.property_name, "field 'property_name'", TextView.class);
            childViewHolder.propertyAddress = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.propertyAddress, "field 'propertyAddress'", CustomTextView.class);
            childViewHolder.editAndCalender = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.editAndCalender, "field 'editAndCalender'", LinearLayout.class);
            childViewHolder.edit = (FancyButton) Utils.findRequiredViewAsType(view, R.id.edit, "field 'edit'", FancyButton.class);
            childViewHolder.calender = (FancyButton) Utils.findRequiredViewAsType(view, R.id.calender, "field 'calender'", FancyButton.class);
        }

        @CallSuper
        public void unbind() {
            ChildViewHolder childViewHolder = this.target;
            if (childViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            childViewHolder.img_property = null;
            childViewHolder.tv_price = null;
            childViewHolder.delete = null;
            childViewHolder.imagButtomLine = null;
            childViewHolder.tv = null;
            childViewHolder.circularProgressbar = null;
            childViewHolder.progress_lay = null;
            childViewHolder.propertyFrameLayout = null;
            childViewHolder.transperencyImage = null;
            childViewHolder.publishYourProperty = null;
            childViewHolder.unpublishLay = null;
            childViewHolder.forUnpublishLay = null;
            childViewHolder.property_id = null;
            childViewHolder.completePublish = null;
            childViewHolder.forPublishLay = null;
            childViewHolder.property_name = null;
            childViewHolder.propertyAddress = null;
            childViewHolder.editAndCalender = null;
            childViewHolder.edit = null;
            childViewHolder.calender = null;
        }
    }

    public class SectionViewHolder extends ViewHolder {
        @BindView(2131362574)
        TextView section_label;

        public SectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class SectionViewHolder_ViewBinding implements Unbinder {
        private SectionViewHolder target;

        @UiThread
        public SectionViewHolder_ViewBinding(SectionViewHolder sectionViewHolder, View view) {
            this.target = sectionViewHolder;
            sectionViewHolder.section_label = (TextView) Utils.findRequiredViewAsType(view, R.id.section_label, "field 'section_label'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SectionViewHolder sectionViewHolder = this.target;
            if (sectionViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            sectionViewHolder.section_label = null;
        }
    }

    public PropertyListingAdapaterSectionRecycler(Context context, List<PropertyListingSectionModel> list, OnListItemClickListener onListItemClickListener) {
        super(context, list);
        this.context = context;
        this.listItemClickListener = onListItemClickListener;
    }

    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_messages_date_section, viewGroup, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.property_adapter_view, viewGroup, false));
    }

    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int i, PropertyListingSectionModel propertyListingSectionModel) {
        sectionViewHolder.section_label.setText(propertyListingSectionModel.getSectionLabel());
    }

    public void onBindChildViewHolder(ChildViewHolder childViewHolder, final int i, final int i2, final Published published) {
        childViewHolder.tv_price.setText(Html.fromHtml(String.format("%s <b>%s</b>/ %s", new Object[]{this.context.getString(R.string.saudi_currency), Float.valueOf(published.getPrice()), this.context.getString(R.string.per_night_label)})));
        GlideApp.with(this.context).load(published.getImage()).placeholder((int) R.drawable.default_logo).apply(new RequestOptions().transform(new RoundedCorners(10))).error((int) R.drawable.default_logo).into(childViewHolder.img_property);
        if (published.getStatus().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            if (published.getSteps().getStep().equalsIgnoreCase("finished")) {
                childViewHolder.forPublishLay.setVisibility(0);
                childViewHolder.editAndCalender.setVisibility(8);
                childViewHolder.publishYourProperty.setVisibility(0);
                childViewHolder.unpublishLay.setVisibility(0);
                childViewHolder.progress_lay.setVisibility(8);
                childViewHolder.imagButtomLine.setVisibility(0);
                childViewHolder.forUnpublishLay.setVisibility(8);
                childViewHolder.publishYourProperty.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        PropertyListingAdapaterSectionRecycler.this.listItemClickListener.clickOnListItem(i, i2, published.getPropertId(), 3);
                    }
                });
            } else {
                childViewHolder.forPublishLay.setVisibility(8);
                childViewHolder.editAndCalender.setVisibility(8);
                childViewHolder.imagButtomLine.setVisibility(8);
                childViewHolder.publishYourProperty.setVisibility(8);
                childViewHolder.imagButtomLine.setVisibility(8);
                childViewHolder.unpublishLay.setVisibility(8);
                childViewHolder.progress_lay.setVisibility(0);
                childViewHolder.forUnpublishLay.setVisibility(0);
                childViewHolder.circularProgressbar.setProgress(published.getPercentage());
                TextView textView = childViewHolder.tv;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(published.getPercentage());
                stringBuilder.append("%");
                textView.setText(stringBuilder.toString());
                childViewHolder.property_id.setText(published.getPropertId());
                childViewHolder.completePublish.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        PropertyListingAdapaterSectionRecycler.this.listItemClickListener.clickOnListItem(i, i2, published.getPropertId(), 0);
                    }
                });
            }
            childViewHolder.delete.setVisibility(0);
            childViewHolder.transperencyImage.setVisibility(0);
            childViewHolder.delete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PropertyListingAdapaterSectionRecycler.this.listItemClickListener.clickOnListItem(i, i2, published.getPropertId(), 1);
                }
            });
        } else if (published.getStatus().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            childViewHolder.forPublishLay.setVisibility(0);
            childViewHolder.editAndCalender.setVisibility(0);
            childViewHolder.publishYourProperty.setVisibility(8);
            childViewHolder.forUnpublishLay.setVisibility(8);
            childViewHolder.unpublishLay.setVisibility(8);
            childViewHolder.progress_lay.setVisibility(8);
            childViewHolder.delete.setVisibility(8);
            childViewHolder.transperencyImage.setVisibility(8);
            childViewHolder.imagButtomLine.setVisibility(0);
            childViewHolder.edit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PropertyListingAdapaterSectionRecycler.this.listItemClickListener.clickOnListItem(i, i2, published.getPropertId(), 0);
                }
            });
        }
        childViewHolder.property_name.setText(published.getName());
        childViewHolder.propertyAddress.setText(published.getAddress());
    }
}
