package com.jelsat.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.data.alllocalities.CountryDetail;
import com.jelsat.R;
import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<CountryDetail> {
    private Context context;
    private List<CountryDetail> data;
    private LayoutInflater inflater = ((LayoutInflater) this.context.getSystemService("layout_inflater"));
    public Resources res;

    public CustomSpinnerAdapter(Context context, List<CountryDetail> list) {
        super(context, R.layout.spinner_row, list);
        this.context = context;
        this.data = list;
    }

    public View getDropDownView(int i, View view, @NonNull ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    @NonNull
    public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    public View getCustomView(int i, View view, ViewGroup viewGroup) {
        return this.inflater.inflate(R.layout.spinner_row, viewGroup, false);
    }
}
