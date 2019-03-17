package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.data.searchproperty.SearchProperty;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.activities.SearchPropertyActivity;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.GlideApp;
import java.util.List;
import java.util.Locale;

public class SearchPropertyAdapter extends Adapter<SearchPropertyViewHolder> {
    private Context context;
    private int deviceHeight;
    private int deviceWidth;
    private boolean isHorizontal;
    private boolean isMapSelected;
    private SearchPropertyAdapter$OnListItemClickListener listItemClickListener;
    private List<SearchProperty> propertyResponseList;

    public class SearchPropertyViewHolder extends ViewHolder {
        @BindView(2131361877)
        LinearLayout appRatingLayout;
        @BindView(2131362102)
        TextView distanceTV;
        @BindView(2131362164)
        ImageView favourite;
        @BindView(2131362428)
        LinearLayout priceLayout;
        @BindView(2131362427)
        TextView priceTV;
        @BindView(2131362451)
        CustomTextView propertyBookType;
        @BindView(2131362454)
        CardView propertyCardView;
        @BindView(2131362468)
        ImageView propertyImage;
        @BindView(2131362473)
        View propertyLine;
        @BindView(2131362477)
        CustomTextView propertyNameTV;
        @BindView(2131362478)
        AppCompatRatingBar propertyRatingBar;
        @BindView(2131362480)
        TextView propertyReviews;
        @BindView(2131362446)
        TextView propertyType;
        @BindView(2131362569)
        LinearLayout searchPropertyLayout;

        SearchPropertyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            if (SearchPropertyAdapter.this.isHorizontal != null) {
                view = new LayoutParams((int) (((double) SearchPropertyAdapter.this.deviceWidth) / 1.5d), (int) (((double) SearchPropertyAdapter.this.deviceWidth) / 1.9d));
                view.setMargins(6, 0, 20, 0);
                this.searchPropertyLayout.setLayoutParams(view);
                this.propertyCardView.setLayoutParams(new LayoutParams(-1, SearchPropertyAdapter.this.deviceWidth / 3));
                this.priceLayout.setPadding(0, 0, 0, 0);
                this.distanceTV.setTextSize(10.0f);
                this.priceTV.setTextSize(10.0f);
                this.propertyNameTV.setTextSize(10.0f);
                this.propertyType.setTextSize(10.0f);
                this.propertyReviews.setTextSize(8.0f);
                this.propertyBookType.setTextSize(10.0f);
                return;
            }
            view = new LayoutParams(-1, (int) (((double) SearchPropertyAdapter.this.deviceHeight) / 2.5d));
            view.setMargins(getPixelsFromDPs(10), getPixelsFromDPs(10), getPixelsFromDPs(10), getPixelsFromDPs(0));
            this.searchPropertyLayout.setLayoutParams(view);
            this.propertyCardView.setLayoutParams(new LayoutParams(-1, (int) (((double) SearchPropertyAdapter.this.deviceHeight) / 3.8d)));
        }

        private int getPixelsFromDPs(int i) {
            return (int) TypedValue.applyDimension(1, (float) i, SearchPropertyAdapter.this.context.getResources().getDisplayMetrics());
        }
    }

    public class SearchPropertyViewHolder_ViewBinding implements Unbinder {
        private SearchPropertyViewHolder target;

        @UiThread
        public SearchPropertyViewHolder_ViewBinding(SearchPropertyViewHolder searchPropertyViewHolder, View view) {
            this.target = searchPropertyViewHolder;
            searchPropertyViewHolder.searchPropertyLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.search_property_layout, "field 'searchPropertyLayout'", LinearLayout.class);
            searchPropertyViewHolder.propertyCardView = (CardView) Utils.findRequiredViewAsType(view, R.id.property_cardView, "field 'propertyCardView'", CardView.class);
            searchPropertyViewHolder.priceLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.price_layout, "field 'priceLayout'", LinearLayout.class);
            searchPropertyViewHolder.propertyImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.property_image, "field 'propertyImage'", ImageView.class);
            searchPropertyViewHolder.favourite = (ImageView) Utils.findRequiredViewAsType(view, R.id.favourite, "field 'favourite'", ImageView.class);
            searchPropertyViewHolder.priceTV = (TextView) Utils.findRequiredViewAsType(view, R.id.price_TV, "field 'priceTV'", TextView.class);
            searchPropertyViewHolder.distanceTV = (TextView) Utils.findRequiredViewAsType(view, R.id.distance_TV, "field 'distanceTV'", TextView.class);
            searchPropertyViewHolder.propertyNameTV = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.property_name_TV, "field 'propertyNameTV'", CustomTextView.class);
            searchPropertyViewHolder.propertyRatingBar = (AppCompatRatingBar) Utils.findRequiredViewAsType(view, R.id.property_rating_bar, "field 'propertyRatingBar'", AppCompatRatingBar.class);
            searchPropertyViewHolder.propertyReviews = (TextView) Utils.findRequiredViewAsType(view, R.id.property_reviews, "field 'propertyReviews'", TextView.class);
            searchPropertyViewHolder.propertyLine = Utils.findRequiredView(view, R.id.property_line, "field 'propertyLine'");
            searchPropertyViewHolder.propertyType = (TextView) Utils.findRequiredViewAsType(view, R.id.propertyType, "field 'propertyType'", TextView.class);
            searchPropertyViewHolder.propertyBookType = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.property_book_type, "field 'propertyBookType'", CustomTextView.class);
            searchPropertyViewHolder.appRatingLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.app_rating_layout, "field 'appRatingLayout'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            SearchPropertyViewHolder searchPropertyViewHolder = this.target;
            if (searchPropertyViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            searchPropertyViewHolder.searchPropertyLayout = null;
            searchPropertyViewHolder.propertyCardView = null;
            searchPropertyViewHolder.priceLayout = null;
            searchPropertyViewHolder.propertyImage = null;
            searchPropertyViewHolder.favourite = null;
            searchPropertyViewHolder.priceTV = null;
            searchPropertyViewHolder.distanceTV = null;
            searchPropertyViewHolder.propertyNameTV = null;
            searchPropertyViewHolder.propertyRatingBar = null;
            searchPropertyViewHolder.propertyReviews = null;
            searchPropertyViewHolder.propertyLine = null;
            searchPropertyViewHolder.propertyType = null;
            searchPropertyViewHolder.propertyBookType = null;
            searchPropertyViewHolder.appRatingLayout = null;
        }
    }

    private int getBookingTypeImage(int i, boolean z) {
        switch (i) {
            case 0:
                return z ? R.drawable.ic_24hours_booking_small : R.drawable.ic_24hours_booking;
            case 1:
                return z ? R.drawable.ic_instant_booking_small : R.drawable.ic_instant_booking;
            default:
                return z ? R.drawable.ic_instant_booking_small : R.drawable.ic_instant_booking;
        }
    }

    public void setData(List<SearchProperty> list) {
        if (this.propertyResponseList == null) {
            this.propertyResponseList = list;
        } else {
            this.propertyResponseList.clear();
            this.propertyResponseList = list;
        }
        notifyDataSetChanged();
    }

    public SearchPropertyAdapter(Context context, List<SearchProperty> list, SearchPropertyAdapter$OnListItemClickListener searchPropertyAdapter$OnListItemClickListener, boolean z) {
        this.context = context;
        this.propertyResponseList = list;
        this.listItemClickListener = searchPropertyAdapter$OnListItemClickListener;
        this.isHorizontal = z;
        this.deviceHeight = com.jelsat.utils.Utils.getScreenHeightInPixels(context.getApplicationContext());
        this.deviceWidth = com.jelsat.utils.Utils.getScreenWidthInPixels(context.getApplicationContext());
    }

    @NonNull
    public SearchPropertyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchPropertyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.search_property_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull SearchPropertyViewHolder searchPropertyViewHolder, int i) {
        SearchPropertyViewHolder searchPropertyViewHolder2 = searchPropertyViewHolder;
        final int i2 = i;
        final SearchProperty searchProperty = (SearchProperty) this.propertyResponseList.get(i2);
        StringBuilder stringBuilder = new StringBuilder("addressFiled(searchProperty) : ");
        stringBuilder.append(addressFiled(searchProperty));
        Log.e("searchPropertyAdapter", stringBuilder.toString());
        String str = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        searchProperty.setPropertyFavourite(searchProperty.getFavourite() == 1);
        loadPropertyImage(searchProperty.getImage(), searchPropertyViewHolder2.propertyImage);
        searchPropertyViewHolder2.favourite.setImageResource(searchProperty.isPropertyFavourite() ? R.drawable.ic_favorite_red_24dp : R.drawable.ic_favourite);
        if (searchProperty.getCurrencyCode() != null && searchProperty.getCurrencyCode().equalsIgnoreCase(PayFortPayment.CURRENCY_TYPE)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                searchPropertyViewHolder2.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big><b>%.2f</></big> %s/%s", new Object[]{Float.valueOf(searchProperty.getPrice()), r0.context.getString(R.string.saudi_currency), r0.context.getString(R.string.per_night_label)})));
            } else {
                searchPropertyViewHolder2.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "%s <big><b>%.2f</b></big> /%s", new Object[]{r0.context.getString(R.string.saudi_currency), Float.valueOf(searchProperty.getPrice()), r0.context.getString(R.string.per_night_label)})));
            }
        }
        if (TextUtils.isEmpty(searchProperty.getDistance()) || searchProperty.getDistance().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            searchPropertyViewHolder2.distanceTV.setText(addressFiled(searchProperty));
        } else {
            searchPropertyViewHolder2.distanceTV.setVisibility(0);
            TextView textView;
            StringBuilder stringBuilder2;
            if (SearchPropertyActivity.checkNearBy) {
                textView = searchPropertyViewHolder2.distanceTV;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(addressFiled(searchProperty));
                stringBuilder2.append(", ");
                stringBuilder2.append(String.format(Locale.getDefault(), "%.1f %s from you", new Object[]{Float.valueOf(Float.parseFloat(searchProperty.getDistance())), r0.context.getString(R.string.search_km_label)}));
                textView.setText(stringBuilder2.toString());
            } else {
                textView = searchPropertyViewHolder2.distanceTV;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(addressFiled(searchProperty));
                stringBuilder2.append(", ");
                stringBuilder2.append(String.format(Locale.getDefault(), "%.1f %s", new Object[]{Float.valueOf(Float.parseFloat(searchProperty.getDistance())), r0.context.getString(R.string.search_km_label)}));
                textView.setText(stringBuilder2.toString());
            }
        }
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            searchPropertyViewHolder2.propertyBookType.setCompoundDrawablesWithIntrinsicBounds(0, 0, getBookingTypeImage(Integer.parseInt(searchProperty.getType()), r0.isHorizontal), 0);
        } else {
            searchPropertyViewHolder2.propertyBookType.setCompoundDrawablesWithIntrinsicBounds(getBookingTypeImage(Integer.parseInt(searchProperty.getType()), r0.isHorizontal), 0, 0, 0);
        }
        if (searchProperty.getType().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            searchPropertyViewHolder2.propertyBookType.setText(R.string.request);
        } else {
            searchPropertyViewHolder2.propertyBookType.setText(R.string.instant);
        }
        if (!(searchProperty.getRating() == null || searchProperty.getRating().trim().isEmpty())) {
            str = searchProperty.getRating();
        }
        searchPropertyViewHolder2.propertyType.setText(searchProperty.getPropertyType());
        if (str.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            searchPropertyViewHolder2.appRatingLayout.setVisibility(8);
            searchPropertyViewHolder2.propertyReviews.setVisibility(8);
        } else {
            searchPropertyViewHolder2.appRatingLayout.setVisibility(0);
            searchPropertyViewHolder2.propertyReviews.setVisibility(0);
            searchPropertyViewHolder2.propertyRatingBar.setRating(Float.parseFloat(str));
            searchPropertyViewHolder2.propertyReviews.setText(String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(Integer.parseInt(searchProperty.getReviews()))}));
        }
        StringBuilder stringBuilder3;
        String charSequence;
        CustomTextView customTextView;
        ViewGroup.LayoutParams layoutParams;
        if (searchProperty.getName() != null && searchProperty.getName().length() > 37 && !r0.isHorizontal && searchPropertyViewHolder2.appRatingLayout.getVisibility() == 0) {
            stringBuilder3 = new StringBuilder("setMaxLines(2),setLines(2) of property name , isMapSelected== ");
            stringBuilder3.append(r0.isHorizontal ^ true);
            Log.e("SearchPropertyAdapter", stringBuilder3.toString());
            searchPropertyViewHolder2.propertyNameTV.setMaxLines(2);
            str = searchProperty.getName().subSequence(0, 37).toString();
            if (searchProperty.getName().length() <= 37 || searchProperty.getName().length() > 60) {
                charSequence = searchProperty.getName().subSequence(37, 60).toString();
            } else {
                charSequence = searchProperty.getName().subSequence(37, searchProperty.getName().length() - 1).toString();
            }
            customTextView = searchPropertyViewHolder2.propertyNameTV;
            stringBuilder3 = new StringBuilder("\n-");
            stringBuilder3.append(charSequence);
            stringBuilder3.append("...");
            customTextView.setText(str.concat(stringBuilder3.toString()));
            stringBuilder3 = new StringBuilder("\n-");
            stringBuilder3.append(charSequence);
            stringBuilder3.append("...");
            Log.e("SearchPropertyAdapter", str.concat(stringBuilder3.toString()));
            layoutParams = new LayoutParams(-1, (int) (((double) r0.deviceHeight) / 2.3d));
            layoutParams.setMargins(searchPropertyViewHolder2.getPixelsFromDPs(10), searchPropertyViewHolder2.getPixelsFromDPs(10), searchPropertyViewHolder2.getPixelsFromDPs(10), searchPropertyViewHolder2.getPixelsFromDPs(0));
            searchPropertyViewHolder2.searchPropertyLayout.setLayoutParams(layoutParams);
            searchPropertyViewHolder2.propertyCardView.setLayoutParams(new LayoutParams(-1, (int) (((double) r0.deviceHeight) / 3.6d)));
        } else if (searchProperty.getName() != null && searchProperty.getName().length() > 37 && r0.isHorizontal && searchPropertyViewHolder2.appRatingLayout.getVisibility() == 0) {
            stringBuilder = new StringBuilder(", isMapSelected== ");
            stringBuilder.append(r0.isHorizontal);
            Log.e("SearchPropertyAdapter", stringBuilder.toString());
            str = searchProperty.getName().subSequence(0, 37).toString();
            r5 = searchPropertyViewHolder2.propertyNameTV;
            r6 = new StringBuilder();
            r6.append(str);
            r6.append("...");
            r5.setText(r6.toString());
        } else if (searchProperty.getName() != null && searchProperty.getName().length() > 37 && r0.isHorizontal && searchPropertyViewHolder2.appRatingLayout.getVisibility() != 0) {
            stringBuilder = new StringBuilder(", isMapSelected== ");
            stringBuilder.append(r0.isHorizontal);
            Log.e("SearchPropertyAdapter", stringBuilder.toString());
            searchPropertyViewHolder2.propertyNameTV.setSingleLine();
            str = searchProperty.getName().subSequence(0, 37).toString();
            r5 = searchPropertyViewHolder2.propertyNameTV;
            r6 = new StringBuilder();
            r6.append(str);
            r6.append("...");
            r5.setText(r6.toString());
        } else if (searchProperty.getName() == null || searchProperty.getName().length() <= 37 || r0.isHorizontal || searchPropertyViewHolder2.appRatingLayout.getVisibility() == 0) {
            searchPropertyViewHolder2.propertyNameTV.setText(searchProperty.getName());
        } else {
            Log.e("SearchPropertyAdapter", "ifelse condition fired");
            searchPropertyViewHolder2.propertyNameTV.setMaxLines(2);
            str = searchProperty.getName().subSequence(0, 37).toString();
            if (searchProperty.getName().length() <= 37 || searchProperty.getName().length() > 60) {
                charSequence = searchProperty.getName().subSequence(37, 60).toString();
            } else {
                charSequence = searchProperty.getName().subSequence(37, searchProperty.getName().length() - 1).toString();
            }
            customTextView = searchPropertyViewHolder2.propertyNameTV;
            stringBuilder3 = new StringBuilder("\n- ");
            stringBuilder3.append(charSequence);
            stringBuilder3.append("...");
            customTextView.setText(str.concat(stringBuilder3.toString()));
            stringBuilder3 = new StringBuilder("\n-");
            stringBuilder3.append(charSequence);
            stringBuilder3.append("...");
            Log.e("SearchPropertyAdapter", str.concat(stringBuilder3.toString()));
            layoutParams = new LayoutParams(-1, (int) (((double) r0.deviceHeight) / 2.3d));
            layoutParams.setMargins(searchPropertyViewHolder2.getPixelsFromDPs(10), searchPropertyViewHolder2.getPixelsFromDPs(10), searchPropertyViewHolder2.getPixelsFromDPs(10), searchPropertyViewHolder2.getPixelsFromDPs(0));
            searchPropertyViewHolder2.searchPropertyLayout.setLayoutParams(layoutParams);
            searchPropertyViewHolder2.propertyCardView.setLayoutParams(new LayoutParams(-1, (int) (((double) r0.deviceHeight) / 3.6d)));
        }
        searchPropertyViewHolder2.favourite.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchPropertyAdapter.this.listItemClickListener.setFavouriteProperty(searchProperty, i2);
            }
        });
        searchPropertyViewHolder2.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SearchPropertyAdapter.this.listItemClickListener.clickOnListItem(searchProperty, i2);
            }
        });
    }

    private String addressFiled(SearchProperty searchProperty) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(searchProperty.getStreet())) {
            stringBuilder.append(searchProperty.getStreet());
            stringBuilder.append(",");
        }
        if (!TextUtils.isEmpty(searchProperty.getCity())) {
            stringBuilder.append(searchProperty.getCity());
        }
        return stringBuilder.toString();
    }

    private void loadPropertyImage(String str, ImageView imageView) {
        GlideApp.with(this.context).asBitmap().load(str).placeholder((int) R.drawable.default_logo).apply(new RequestOptions().transform(new RoundedCorners(3))).error((int) R.drawable.default_logo).diskCacheStrategy(DiskCacheStrategy.DATA).into(imageView);
    }

    public int getItemCount() {
        return this.propertyResponseList != null ? this.propertyResponseList.size() : 0;
    }

    public void setPropertyItemFavourite(int i, int i2, boolean z) {
        if (z) {
            try {
                this.propertyResponseList.remove(i2);
                notifyItemRemoved(i2);
                notifyItemRangeChanged(i2, getItemCount());
                return;
            } catch (int i3) {
                i3.printStackTrace();
                return;
            }
        }
        ((SearchProperty) this.propertyResponseList.get(i2)).setFavourite(i3);
        notifyItemChanged(i2);
    }

    public void changePropertyBasedOnFavourite(String str, int i, boolean z) {
        if (this.propertyResponseList != null) {
            for (SearchProperty searchProperty : this.propertyResponseList) {
                if (searchProperty.getPropertyId().equals(str)) {
                    str = this.propertyResponseList.indexOf(searchProperty);
                    if (z) {
                        try {
                            this.propertyResponseList.remove(str);
                            notifyItemRemoved(str);
                            notifyItemRangeChanged(str, getItemCount());
                            return;
                        } catch (String str2) {
                            str2.printStackTrace();
                            return;
                        }
                    }
                    ((SearchProperty) this.propertyResponseList.get(str2)).setFavourite(i);
                    notifyItemChanged(str2);
                    return;
                }
            }
        }
    }

    public int getPosition(SearchProperty searchProperty) {
        return this.propertyResponseList.indexOf(searchProperty);
    }
}
