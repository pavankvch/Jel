package com.jelsat.adapters;

import android.content.Context;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PlaceAutocompleteAdapter extends ArrayAdapter<AutocompletePrediction> implements Filterable {
    private static final CharacterStyle STYLE_BOLD = new StyleSpan(1);
    private static final String TAG = "PlaceAutocompleteAdapter";
    private LatLngBounds mBounds;
    private GeoDataClient mGeoDataClient;
    private AutocompleteFilter mPlaceFilter;
    private ArrayList<AutocompletePrediction> mResultList;

    public PlaceAutocompleteAdapter(Context context, GeoDataClient geoDataClient, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        super(context, 17367047, 16908308);
        this.mGeoDataClient = geoDataClient;
        this.mBounds = latLngBounds;
        this.mPlaceFilter = autocompleteFilter;
    }

    public void setBounds(LatLngBounds latLngBounds) {
        this.mBounds = latLngBounds;
    }

    public int getCount() {
        return this.mResultList.size();
    }

    public AutocompletePrediction getItem(int i) {
        return (AutocompletePrediction) this.mResultList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        view = super.getView(i, view, viewGroup);
        i = getItem(i);
        TextView textView = (TextView) view.findViewById(16908309);
        ((TextView) view.findViewById(16908308)).setText(i.getPrimaryText(STYLE_BOLD));
        textView.setText(i.getSecondaryText(STYLE_BOLD));
        return view;
    }

    public Filter getFilter() {
        return new Filter() {
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                ArrayList arrayList = new ArrayList();
                if (charSequence != null) {
                    arrayList = PlaceAutocompleteAdapter.this.getAutocomplete(charSequence);
                }
                filterResults.values = arrayList;
                if (arrayList != null) {
                    filterResults.count = arrayList.size();
                } else {
                    filterResults.count = null;
                }
                return filterResults;
            }

            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults == null || filterResults.count <= null) {
                    PlaceAutocompleteAdapter.this.notifyDataSetInvalidated();
                    return;
                }
                PlaceAutocompleteAdapter.this.mResultList = (ArrayList) filterResults.values;
                PlaceAutocompleteAdapter.this.notifyDataSetChanged();
            }

            public CharSequence convertResultToString(Object obj) {
                if (obj instanceof AutocompletePrediction) {
                    return ((AutocompletePrediction) obj).getFullText(null);
                }
                return super.convertResultToString(obj);
            }
        };
    }

    private ArrayList<AutocompletePrediction> getAutocomplete(CharSequence charSequence) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder("Starting autocomplete query for: ");
        stringBuilder.append(charSequence);
        Log.i(str, stringBuilder.toString());
        charSequence = this.mGeoDataClient.getAutocompletePredictions(charSequence.toString(), this.mBounds, this.mPlaceFilter);
        try {
            Tasks.await(charSequence, 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            AutocompletePredictionBufferResponse autocompletePredictionBufferResponse = (AutocompletePredictionBufferResponse) charSequence.getResult();
            str = TAG;
            stringBuilder = new StringBuilder("Query completed. Received ");
            stringBuilder.append(autocompletePredictionBufferResponse.getCount());
            stringBuilder.append(" predictions.");
            Log.i(str, stringBuilder.toString());
            return DataBufferUtils.freezeAndClose(autocompletePredictionBufferResponse);
        } catch (CharSequence charSequence2) {
            Context context = getContext();
            stringBuilder = new StringBuilder("Error contacting API: ");
            stringBuilder.append(charSequence2.toString());
            Toast.makeText(context, stringBuilder.toString(), 0).show();
            Log.e(TAG, "Error getting autocomplete prediction API call", charSequence2);
            return null;
        }
    }
}
