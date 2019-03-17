package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.adapters.RecyclerViewSectionAdapter.GetObject;
import com.jelsat.country.Country;
import com.jelsat.country.OnCountryPickerListener;
import java.util.List;

public class CountryPickerAdapter extends Adapter<CountryPickerViewHolder> implements GetObject<Country> {
    private Context context;
    private List<Country> countryList;
    private OnCountryPickerListener countryPickerListener;
    private boolean showCountryCode;

    public class CountryPickerViewHolder extends ViewHolder {
        @BindView(2131362038)
        LinearLayout countryCodeItem;
        @BindView(2131362658)
        TextView countryDialCodeTextView;
        @BindView(2131362657)
        TextView countryNameTextView;
        @BindView(2131362238)
        ImageView flagImageView;

        public CountryPickerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class CountryPickerViewHolder_ViewBinding implements Unbinder {
        private CountryPickerViewHolder target;

        @UiThread
        public CountryPickerViewHolder_ViewBinding(CountryPickerViewHolder countryPickerViewHolder, View view) {
            this.target = countryPickerViewHolder;
            countryPickerViewHolder.flagImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.image_flag, "field 'flagImageView'", ImageView.class);
            countryPickerViewHolder.countryNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.textView_countryName, "field 'countryNameTextView'", TextView.class);
            countryPickerViewHolder.countryDialCodeTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.textView_dial_code, "field 'countryDialCodeTextView'", TextView.class);
            countryPickerViewHolder.countryCodeItem = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.country_code_item, "field 'countryCodeItem'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            CountryPickerViewHolder countryPickerViewHolder = this.target;
            if (countryPickerViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            countryPickerViewHolder.flagImageView = null;
            countryPickerViewHolder.countryNameTextView = null;
            countryPickerViewHolder.countryDialCodeTextView = null;
            countryPickerViewHolder.countryCodeItem = null;
        }
    }

    public CountryPickerAdapter(Context context, List<Country> list, OnCountryPickerListener onCountryPickerListener, boolean z) {
        this.context = context;
        this.countryList = list;
        this.countryPickerListener = onCountryPickerListener;
        this.showCountryCode = z;
    }

    public CountryPickerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CountryPickerViewHolder(LayoutInflater.from(this.context).inflate(R.layout.country_picker_list_item, viewGroup, false));
    }

    public void onBindViewHolder(CountryPickerViewHolder countryPickerViewHolder, int i) {
        final Country country = (Country) this.countryList.get(i);
        countryPickerViewHolder.flagImageView.setImageResource(country.getFlag());
        TextView textView = countryPickerViewHolder.countryNameTextView;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(country.getName());
        stringBuilder.append(" (");
        stringBuilder.append(country.getCode());
        stringBuilder.append(")");
        textView.setText(stringBuilder.toString());
        if (this.showCountryCode) {
            countryPickerViewHolder.countryDialCodeTextView.setVisibility(0);
            countryPickerViewHolder.countryDialCodeTextView.setText(country.getDialCode());
        } else {
            countryPickerViewHolder.countryDialCodeTextView.setVisibility(8);
        }
        countryPickerViewHolder.countryCodeItem.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CountryPickerAdapter.this.countryPickerListener.clickOnItem(country);
            }
        });
    }

    public int getItemCount() {
        return (this.countryList == null || this.countryList.size() == 0) ? 0 : this.countryList.size();
    }

    public Country getObject(int i) {
        return (Country) this.countryList.get(i);
    }
}
