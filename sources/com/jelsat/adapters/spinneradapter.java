package com.jelsat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jelsat.R;

public class spinneradapter extends BaseAdapter {
    Context context;
    String[] countryNames;
    int[] flags;
    LayoutInflater inflter;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public spinneradapter(Context context, int[] iArr, String[] strArr) {
        this.context = context;
        this.flags = iArr;
        this.countryNames = strArr;
        this.inflter = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.flags.length;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view = this.inflter.inflate(R.layout.spinner_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        view.findViewById(R.id.main_layout);
        imageView.setImageResource(this.flags[i]);
        textView.setText(this.countryNames[i]);
        if (i == 1) {
            textView2.setText(this.context.getResources().getString(R.string.total_earnings_booking_type_instant_rec));
        } else if (i == 2) {
            textView2.setText(this.context.getResources().getString(R.string.total_earnings_booking_type_regular));
        }
        if (i == 0) {
            imageView.setVisibility(8);
            textView.setVisibility(8);
            textView2.setText(this.countryNames[i]);
        }
        return view;
    }
}
