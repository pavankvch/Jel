package com.jelsat.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.jelsat.R;
import com.jelsat.adapters.CountryPickerAdapter;
import com.jelsat.adapters.RecyclerViewSectionAdapter;
import com.jelsat.adapters.SectionNameAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.country.Country;
import com.jelsat.country.OnCountryPickerListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryPickerActivity extends BaseAppCompactActivity implements OnCountryPickerListener {
    @BindView(2131361959)
    public TextView cancelTextView;
    private List<Country> countriesList = new ArrayList();
    @BindView(2131362039)
    public RecyclerView countryCodeRecyclerView;
    private CountryPickerAdapter countryPickerAdapter;
    @BindView(2131362041)
    public EditText countrySearchEditText;
    @BindView(2131362659)
    public TextView noResultTextView;
    private List<Country> selectedCountriesList = new ArrayList();

    public int getActivityLayout() {
        return R.layout.activity_country_picker;
    }

    @OnClick({2131361959})
    public void clickOnCancel(View view) {
        finish();
    }

    public CountryPickerActivity() {
        setCountriesList(Country.getAllCountries());
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.selectedCountriesList = new ArrayList(this.countriesList.size());
        this.selectedCountriesList.addAll(this.countriesList);
        this.countryPickerAdapter = new CountryPickerAdapter(this, this.selectedCountriesList, this, getIntent().getBooleanExtra(StringConstants.SHOW_COUNTRY_CODE, true));
        Adapter anonymousClass2 = new RecyclerViewSectionAdapter<Country, CountryPickerAdapter>(new SectionNameAdapter() {
            public String getFirstSectionName() {
                return CountryPickerActivity.this.getString(R.string.top_countries);
            }

            public String getSecondSectionName() {
                return CountryPickerActivity.this.getString(R.string.other_countries);
            }

            public int getBackgroundColor() {
                return ContextCompat.getColor(CountryPickerActivity.this, R.color.dash_board_bg_color);
            }

            public int getSectionTextColor() {
                return ContextCompat.getColor(CountryPickerActivity.this, R.color.normal_text_color);
            }
        }, this.countryPickerAdapter) {
            public int getIndexOfSection(Country country) {
                return country.isPreferredCountry() != null ? null : 1;
            }
        };
        this.countryCodeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.countryCodeRecyclerView.setAdapter(anonymousClass2);
        this.countrySearchEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                CountryPickerActivity.this.search(editable.toString());
            }
        });
    }

    @SuppressLint({"DefaultLocale"})
    private void search(String str) {
        this.selectedCountriesList.clear();
        for (Country country : this.countriesList) {
            if (country.getName().toLowerCase(Locale.ENGLISH).contains(str.toLowerCase())) {
                this.selectedCountriesList.add(country);
            }
        }
        this.noResultTextView.setVisibility(this.selectedCountriesList.size() != 0 ? 8 : 0);
        this.countryPickerAdapter.notifyDataSetChanged();
    }

    public void clickOnItem(Country country) {
        Intent intent = new Intent();
        intent.putExtra(StringConstants.COUNTRY_DIALCODE, country.getDialCode());
        intent.putExtra(StringConstants.COUNTRY_FLAG, country.getFlag());
        intent.putExtra(StringConstants.COUNTRY_NAME, country.getName());
        setResult(101, intent);
        finish();
    }

    public void setCountriesList(List<Country> list) {
        this.countriesList.clear();
        this.countriesList.addAll(list);
    }
}
