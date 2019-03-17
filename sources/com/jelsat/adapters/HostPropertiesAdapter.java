package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.data.payments.HostProperty;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.GlideApp;
import java.util.List;
import java.util.Locale;

public class HostPropertiesAdapter extends Adapter<HostPropertiesHolder> {
    private Context context;
    private List<HostProperty> hostPropertiesList;

    public class HostPropertiesHolder extends ViewHolder {
        @BindView(2131362249)
        ImageView img_property;
        View itemView;
        @BindView(2131362444)
        TextView propertyAddress;
        @BindView(2131362446)
        TextView propertyType;
        @BindView(2131362476)
        TextView property_name;
        @BindView(2131362495)
        AppCompatRatingBar ratingbar;
        @BindView(2131362496)
        TextView ratingsCount;
        @BindView(2131362749)
        TextView tv_price;
        @BindView(2131362764)
        TextView tv_published_date;

        public HostPropertiesHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class HostPropertiesHolder_ViewBinding implements Unbinder {
        private HostPropertiesHolder target;

        @UiThread
        public HostPropertiesHolder_ViewBinding(HostPropertiesHolder hostPropertiesHolder, View view) {
            this.target = hostPropertiesHolder;
            hostPropertiesHolder.img_property = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_property, "field 'img_property'", ImageView.class);
            hostPropertiesHolder.tv_price = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_price, "field 'tv_price'", TextView.class);
            hostPropertiesHolder.tv_published_date = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_published_date, "field 'tv_published_date'", TextView.class);
            hostPropertiesHolder.property_name = (TextView) Utils.findRequiredViewAsType(view, R.id.property_name, "field 'property_name'", TextView.class);
            hostPropertiesHolder.propertyAddress = (TextView) Utils.findRequiredViewAsType(view, R.id.propertyAddress, "field 'propertyAddress'", TextView.class);
            hostPropertiesHolder.ratingsCount = (TextView) Utils.findRequiredViewAsType(view, R.id.ratings_count, "field 'ratingsCount'", TextView.class);
            hostPropertiesHolder.propertyType = (TextView) Utils.findRequiredViewAsType(view, R.id.propertyType, "field 'propertyType'", TextView.class);
            hostPropertiesHolder.ratingbar = (AppCompatRatingBar) Utils.findRequiredViewAsType(view, R.id.ratingbar, "field 'ratingbar'", AppCompatRatingBar.class);
        }

        @CallSuper
        public void unbind() {
            HostPropertiesHolder hostPropertiesHolder = this.target;
            if (hostPropertiesHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            hostPropertiesHolder.img_property = null;
            hostPropertiesHolder.tv_price = null;
            hostPropertiesHolder.tv_published_date = null;
            hostPropertiesHolder.property_name = null;
            hostPropertiesHolder.propertyAddress = null;
            hostPropertiesHolder.ratingsCount = null;
            hostPropertiesHolder.propertyType = null;
            hostPropertiesHolder.ratingbar = null;
        }
    }

    public HostPropertiesAdapter(List<HostProperty> list, Context context) {
        this.hostPropertiesList = list;
        this.context = context;
    }

    public HostPropertiesHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new HostPropertiesHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.host_property_row, viewGroup, false));
    }

    public void onBindViewHolder(HostPropertiesHolder hostPropertiesHolder, int i) {
        StringBuilder stringBuilder;
        if (((HostProperty) this.hostPropertiesList.get(i)).getCurrencyCode() != null && ((HostProperty) this.hostPropertiesList.get(i)).getCurrencyCode().equalsIgnoreCase(PayFortPayment.CURRENCY_TYPE)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                hostPropertiesHolder.tv_price.setText(Html.fromHtml(String.format("<b>%s</b> %s/ %s", new Object[]{Float.valueOf(((HostProperty) this.hostPropertiesList.get(i)).getPrice()), this.context.getString(R.string.saudi_currency), this.context.getString(R.string.per_night_label)})));
            } else {
                hostPropertiesHolder.tv_price.setText(Html.fromHtml(String.format("%s <b>%s</b>/ %s", new Object[]{this.context.getString(R.string.saudi_currency), Float.valueOf(((HostProperty) this.hostPropertiesList.get(i)).getPrice()), this.context.getString(R.string.per_night_label)})));
            }
        }
        hostPropertiesHolder.propertyAddress.setText(((HostProperty) this.hostPropertiesList.get(i)).getAddress());
        TextView textView = hostPropertiesHolder.property_name;
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(this.context.getString(R.string.host_bookings_property_id));
        stringBuilder2.append(((HostProperty) this.hostPropertiesList.get(i)).getPropertyId());
        stringBuilder2.append(" (");
        stringBuilder2.append(((HostProperty) this.hostPropertiesList.get(i)).getPropertyName());
        stringBuilder2.append(")");
        textView.setText(stringBuilder2.toString());
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(((HostProperty) this.hostPropertiesList.get(i)).getReview().getReviewed());
        if (stringBuilder3.toString().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            hostPropertiesHolder.ratingbar.setVisibility(8);
            hostPropertiesHolder.ratingsCount.setVisibility(8);
        } else {
            hostPropertiesHolder.ratingbar.setVisibility(0);
            hostPropertiesHolder.ratingsCount.setVisibility(0);
            hostPropertiesHolder.ratingbar.setRating(((HostProperty) this.hostPropertiesList.get(i)).getReview().getAverageRating());
            textView = hostPropertiesHolder.ratingsCount;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((HostProperty) this.hostPropertiesList.get(i)).getReview().getReviewed());
            stringBuilder.append(" ");
            stringBuilder.append(this.context.getString(R.string.total_properties_reviews));
            textView.setText(stringBuilder.toString());
        }
        textView = hostPropertiesHolder.tv_published_date;
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.context.getString(R.string.total_properties_published_on));
        stringBuilder.append(" ");
        stringBuilder.append(com.jelsat.utils.Utils.showDateWithTwelveHoursTimeStamp(((HostProperty) this.hostPropertiesList.get(i)).getPublished_on()));
        textView.setText(stringBuilder.toString());
        GlideApp.with(this.context).load(((HostProperty) this.hostPropertiesList.get(i)).getImage()).placeholder((int) R.drawable.default_logo).apply(new RequestOptions().transform(new RoundedCorners(8))).error((int) R.drawable.default_logo).into(hostPropertiesHolder.img_property);
        setBookingTypeImage(hostPropertiesHolder, Integer.parseInt(((HostProperty) this.hostPropertiesList.get(i)).getType()));
    }

    private void setBookingTypeImage(HostPropertiesHolder hostPropertiesHolder, int i) {
        switch (i) {
            case 0:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    hostPropertiesHolder.property_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                    return;
                } else {
                    hostPropertiesHolder.property_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
                    return;
                }
            case 1:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") == 0) {
                    hostPropertiesHolder.property_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
                    break;
                } else {
                    hostPropertiesHolder.property_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                    return;
                }
            default:
                break;
        }
    }

    public int getItemCount() {
        return this.hostPropertiesList != null ? this.hostPropertiesList.size() : 0;
    }
}
