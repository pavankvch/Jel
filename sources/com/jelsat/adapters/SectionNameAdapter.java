package com.jelsat.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jelsat.R;

public abstract class SectionNameAdapter extends Adapter<CountryPickerSectionViewHolder> {

    public class CountryPickerSectionViewHolder extends ViewHolder {
        @BindView(2131362575)
        TextView sectionTextView;

        public CountryPickerSectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class CountryPickerSectionViewHolder_ViewBinding implements Unbinder {
        private CountryPickerSectionViewHolder target;

        @UiThread
        public CountryPickerSectionViewHolder_ViewBinding(CountryPickerSectionViewHolder countryPickerSectionViewHolder, View view) {
            this.target = countryPickerSectionViewHolder;
            countryPickerSectionViewHolder.sectionTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.section_textView, "field 'sectionTextView'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            CountryPickerSectionViewHolder countryPickerSectionViewHolder = this.target;
            if (countryPickerSectionViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            countryPickerSectionViewHolder.sectionTextView = null;
        }
    }

    public abstract int getBackgroundColor();

    public abstract String getFirstSectionName();

    public int getItemCount() {
        return 0;
    }

    public abstract String getSecondSectionName();

    public abstract int getSectionTextColor();

    @NonNull
    public CountryPickerSectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CountryPickerSectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.country_picker_section, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull CountryPickerSectionViewHolder countryPickerSectionViewHolder, int i) {
        countryPickerSectionViewHolder.sectionTextView.setBackgroundColor(getBackgroundColor());
        countryPickerSectionViewHolder.sectionTextView.setTextColor(getSectionTextColor());
        countryPickerSectionViewHolder.sectionTextView.setText(getSectionName(i));
    }

    private String getSectionName(int i) {
        return i == 0 ? getFirstSectionName() : getSecondSectionName();
    }
}
