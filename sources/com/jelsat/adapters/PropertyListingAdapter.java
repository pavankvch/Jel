package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.media.ExifInterface;
import android.support.v7.widget.RecyclerView.Adapter;
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
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.GlideApp;
import com.jelsat.widgets.FancyButton;
import java.util.List;
import java.util.Locale;

public class PropertyListingAdapter extends Adapter<PropertyListingHolder> {
    private List<Published> allDataArray;
    private Context context;
    private OnListItemClickListener listItemClickListener;

    public interface OnListItemClickListener {
        void clickOnListItem(int i, String str, String str2, int i2);
    }

    public class PropertyListingHolder extends ViewHolder {
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
        View itemView;
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
        @BindView(2131362791)
        TextView underReviewTextDesc;
        @BindView(2131362794)
        ImageView unpublishIcon;
        @BindView(2131362795)
        RelativeLayout unpublishLay;
        @BindView(2131362797)
        TextView unpublishedText;

        public PropertyListingHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class PropertyListingHolder_ViewBinding implements Unbinder {
        private PropertyListingHolder target;

        @UiThread
        public PropertyListingHolder_ViewBinding(PropertyListingHolder propertyListingHolder, View view) {
            this.target = propertyListingHolder;
            propertyListingHolder.img_property = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_property, "field 'img_property'", ImageView.class);
            propertyListingHolder.tv_price = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_price, "field 'tv_price'", TextView.class);
            propertyListingHolder.delete = (ImageView) Utils.findRequiredViewAsType(view, R.id.delete, "field 'delete'", ImageView.class);
            propertyListingHolder.imagButtomLine = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.imagButtomLine, "field 'imagButtomLine'", FrameLayout.class);
            propertyListingHolder.tv = (TextView) Utils.findRequiredViewAsType(view, R.id.tv, "field 'tv'", TextView.class);
            propertyListingHolder.circularProgressbar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.circularProgressbar, "field 'circularProgressbar'", ProgressBar.class);
            propertyListingHolder.progress_lay = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.progress_lay, "field 'progress_lay'", RelativeLayout.class);
            propertyListingHolder.propertyFrameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.property_frameLayout, "field 'propertyFrameLayout'", FrameLayout.class);
            propertyListingHolder.transperencyImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.transperency_image, "field 'transperencyImage'", ImageView.class);
            propertyListingHolder.publishYourProperty = (TextView) Utils.findRequiredViewAsType(view, R.id.publishYourProperty, "field 'publishYourProperty'", TextView.class);
            propertyListingHolder.unpublishLay = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.unpublish_lay, "field 'unpublishLay'", RelativeLayout.class);
            propertyListingHolder.unpublishIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.unpublishIcon, "field 'unpublishIcon'", ImageView.class);
            propertyListingHolder.unpublishedText = (TextView) Utils.findRequiredViewAsType(view, R.id.unpublished_text, "field 'unpublishedText'", TextView.class);
            propertyListingHolder.underReviewTextDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.under_review_text_desc, "field 'underReviewTextDesc'", TextView.class);
            propertyListingHolder.forUnpublishLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.forUnpublishLay, "field 'forUnpublishLay'", LinearLayout.class);
            propertyListingHolder.property_id = (TextView) Utils.findRequiredViewAsType(view, R.id.property_id, "field 'property_id'", TextView.class);
            propertyListingHolder.completePublish = (TextView) Utils.findRequiredViewAsType(view, R.id.completePublish, "field 'completePublish'", TextView.class);
            propertyListingHolder.forPublishLay = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.forPublishLay, "field 'forPublishLay'", LinearLayout.class);
            propertyListingHolder.property_name = (TextView) Utils.findRequiredViewAsType(view, R.id.property_name, "field 'property_name'", TextView.class);
            propertyListingHolder.propertyAddress = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.propertyAddress, "field 'propertyAddress'", CustomTextView.class);
            propertyListingHolder.editAndCalender = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.editAndCalender, "field 'editAndCalender'", LinearLayout.class);
            propertyListingHolder.edit = (FancyButton) Utils.findRequiredViewAsType(view, R.id.edit, "field 'edit'", FancyButton.class);
            propertyListingHolder.calender = (FancyButton) Utils.findRequiredViewAsType(view, R.id.calender, "field 'calender'", FancyButton.class);
        }

        @CallSuper
        public void unbind() {
            PropertyListingHolder propertyListingHolder = this.target;
            if (propertyListingHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            propertyListingHolder.img_property = null;
            propertyListingHolder.tv_price = null;
            propertyListingHolder.delete = null;
            propertyListingHolder.imagButtomLine = null;
            propertyListingHolder.tv = null;
            propertyListingHolder.circularProgressbar = null;
            propertyListingHolder.progress_lay = null;
            propertyListingHolder.propertyFrameLayout = null;
            propertyListingHolder.transperencyImage = null;
            propertyListingHolder.publishYourProperty = null;
            propertyListingHolder.unpublishLay = null;
            propertyListingHolder.unpublishIcon = null;
            propertyListingHolder.unpublishedText = null;
            propertyListingHolder.underReviewTextDesc = null;
            propertyListingHolder.forUnpublishLay = null;
            propertyListingHolder.property_id = null;
            propertyListingHolder.completePublish = null;
            propertyListingHolder.forPublishLay = null;
            propertyListingHolder.property_name = null;
            propertyListingHolder.propertyAddress = null;
            propertyListingHolder.editAndCalender = null;
            propertyListingHolder.edit = null;
            propertyListingHolder.calender = null;
        }
    }

    public PropertyListingAdapter(List<Published> list, Context context, OnListItemClickListener onListItemClickListener) {
        this.allDataArray = list;
        this.context = context;
        this.listItemClickListener = onListItemClickListener;
    }

    @NonNull
    public PropertyListingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PropertyListingHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.property_adapter_view, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull PropertyListingHolder propertyListingHolder, final int i) {
        Published published = (Published) this.allDataArray.get(i);
        if (published.getCurrencyCode() != null && published.getCurrencyCode().equalsIgnoreCase(PayFortPayment.CURRENCY_TYPE)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                propertyListingHolder.tv_price.setText(Html.fromHtml(String.format("<b>%s</b> %s/ %s", new Object[]{Float.valueOf(published.getPrice()), this.context.getString(R.string.saudi_currency), this.context.getString(R.string.per_night_label)})));
            } else {
                propertyListingHolder.tv_price.setText(Html.fromHtml(String.format("%s <b>%s</b>/ %s", new Object[]{this.context.getString(R.string.saudi_currency), Float.valueOf(published.getPrice()), this.context.getString(R.string.per_night_label)})));
            }
        }
        GlideApp.with(this.context).load(((Published) this.allDataArray.get(i)).getImage()).placeholder((int) R.drawable.default_logo).apply(new RequestOptions().transform(new RoundedCorners(10))).error((int) R.drawable.default_logo).into(propertyListingHolder.img_property);
        propertyListingHolder.calender.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                PropertyListingAdapter.this.listItemClickListener.clickOnListItem(i, ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getPropertId(), ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getName(), 2);
            }
        });
        if (((Published) this.allDataArray.get(i)).getStatus().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            if (((Published) this.allDataArray.get(i)).getSteps().getStep().equalsIgnoreCase("finished")) {
                propertyListingHolder.forPublishLay.setVisibility(0);
                propertyListingHolder.editAndCalender.setVisibility(8);
                propertyListingHolder.publishYourProperty.setVisibility(0);
                propertyListingHolder.unpublishLay.setVisibility(0);
                propertyListingHolder.progress_lay.setVisibility(8);
                propertyListingHolder.imagButtomLine.setVisibility(0);
                propertyListingHolder.forUnpublishLay.setVisibility(8);
                propertyListingHolder.publishYourProperty.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        PropertyListingAdapter.this.listItemClickListener.clickOnListItem(i, ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getPropertId(), ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getName(), 3);
                    }
                });
            } else {
                propertyListingHolder.forPublishLay.setVisibility(8);
                propertyListingHolder.editAndCalender.setVisibility(8);
                propertyListingHolder.imagButtomLine.setVisibility(8);
                propertyListingHolder.publishYourProperty.setVisibility(8);
                propertyListingHolder.imagButtomLine.setVisibility(8);
                propertyListingHolder.unpublishLay.setVisibility(8);
                propertyListingHolder.progress_lay.setVisibility(0);
                propertyListingHolder.forUnpublishLay.setVisibility(0);
                propertyListingHolder.circularProgressbar.setProgress(((Published) this.allDataArray.get(i)).getPercentage());
                TextView textView = propertyListingHolder.tv;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(((Published) this.allDataArray.get(i)).getPercentage());
                stringBuilder.append("%");
                textView.setText(stringBuilder.toString());
                propertyListingHolder.property_id.setText(((Published) this.allDataArray.get(i)).getPropertId());
                propertyListingHolder.completePublish.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        PropertyListingAdapter.this.listItemClickListener.clickOnListItem(i, ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getPropertId(), ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getName(), 0);
                    }
                });
            }
            propertyListingHolder.delete.setVisibility(0);
            propertyListingHolder.transperencyImage.setVisibility(0);
            propertyListingHolder.delete.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PropertyListingAdapter.this.listItemClickListener.clickOnListItem(i, ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getPropertId(), ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getName(), 1);
                }
            });
        } else if (((Published) this.allDataArray.get(i)).getStatus().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            propertyListingHolder.forPublishLay.setVisibility(0);
            propertyListingHolder.editAndCalender.setVisibility(0);
            propertyListingHolder.publishYourProperty.setVisibility(8);
            propertyListingHolder.forUnpublishLay.setVisibility(8);
            propertyListingHolder.unpublishLay.setVisibility(8);
            propertyListingHolder.progress_lay.setVisibility(8);
            propertyListingHolder.delete.setVisibility(8);
            propertyListingHolder.transperencyImage.setVisibility(8);
            propertyListingHolder.imagButtomLine.setVisibility(0);
            propertyListingHolder.edit.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PropertyListingAdapter.this.listItemClickListener.clickOnListItem(i, ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getPropertId(), ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getName(), 4);
                }
            });
        } else if (((Published) this.allDataArray.get(i)).getStatus().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
            propertyListingHolder.underReviewTextDesc.setVisibility(0);
            propertyListingHolder.forPublishLay.setVisibility(0);
            propertyListingHolder.editAndCalender.setVisibility(8);
            propertyListingHolder.publishYourProperty.setVisibility(0);
            propertyListingHolder.unpublishLay.setVisibility(0);
            propertyListingHolder.progress_lay.setVisibility(8);
            propertyListingHolder.imagButtomLine.setVisibility(0);
            propertyListingHolder.forUnpublishLay.setVisibility(8);
            propertyListingHolder.unpublishIcon.setImageResource(R.drawable.ic_under_review);
            propertyListingHolder.unpublishedText.setText(R.string.under_review_text);
            propertyListingHolder.publishYourProperty.setText(R.string.under_review_text);
            propertyListingHolder.publishYourProperty.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    PropertyListingAdapter.this.listItemClickListener.clickOnListItem(i, ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getPropertId(), ((Published) PropertyListingAdapter.this.allDataArray.get(i)).getName(), 3);
                }
            });
        }
        propertyListingHolder.property_name.setText(((Published) this.allDataArray.get(i)).getName());
        propertyListingHolder.propertyAddress.setText(((Published) this.allDataArray.get(i)).getAddress());
    }

    public int getItemCount() {
        return this.allDataArray != null ? this.allDataArray.size() : 0;
    }

    public void setData(List<Published> list) {
        this.allDataArray = list;
        notifyDataSetChanged();
    }
}
