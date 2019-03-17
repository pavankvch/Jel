package com.jelsat.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;

public class ShowLocationActivity extends BaseAppCompactActivity implements OnMapReadyCallback {
    String address;
    @BindView(2131361893)
    ImageView backArrowProperty;
    @BindView(2131362014)
    TextView close;
    Intent i;
    LatLng latLng;
    GoogleMap mMap;
    Marker marker;

    public int getActivityLayout() {
        return R.layout.showlocation_activity;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.i = getIntent();
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        this.address = this.i.getStringExtra("Address");
        this.latLng = new LatLng(this.i.getDoubleExtra("latitude", 0.0d), this.i.getDoubleExtra("longitude", 0.0d));
    }

    @OnClick({2131361893})
    public void backArrow() {
        finish();
    }

    @OnClick({2131362014})
    public void close() {
        finish();
    }

    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        try {
            if (googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.my_map_style)) == null) {
                Log.e("TAG", "Style parsing failed.");
            }
        } catch (GoogleMap googleMap2) {
            Log.e("TAG", "Can't find style. Error: ", googleMap2);
        }
        this.marker = this.mMap.addMarker(new MarkerOptions().position(this.latLng).title(this.address).icon(bitmapDescriptorFromVector(R.drawable.ic_location1)));
        this.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.latLng, 14.0f));
    }

    private BitmapDescriptor bitmapDescriptorFromVector(int i) {
        i = ContextCompat.getDrawable(this, i);
        i.setBounds(0, 0, i.getIntrinsicWidth(), i.getIntrinsicHeight());
        Bitmap createBitmap = Bitmap.createBitmap(i.getIntrinsicWidth(), i.getIntrinsicHeight(), Config.ARGB_8888);
        i.draw(new Canvas(createBitmap));
        return BitmapDescriptorFactory.fromBitmap(createBitmap);
    }
}
