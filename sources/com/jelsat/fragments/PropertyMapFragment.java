package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.searchproperty.SearchProperty;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.events.LatLngEvent;
import com.jelsat.utils.GoogleMapUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PropertyMapFragment extends BaseFragment implements OnMapReadyCallback {
    @BindView(2131361853)
    TextView addressApperenceTextview;
    private boolean fromBookings;
    private GoogleMapUtils googleMapUtils;
    private LatLng propertyLatLng;
    private SearchProperty searchProperty;

    public int getFragmentLayoutId() {
        return R.layout.fragment_property_map;
    }

    @OnClick({2131361892})
    public void clickOnBackArrow() {
        getActivity().finish();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        initGoogleMap();
        return layoutInflater;
    }

    private void initGoogleMap() {
        this.googleMapUtils = new GoogleMapUtils(getActivity());
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.property_map)).getMapAsync(this);
        Log.d("Map", "Map loaded");
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        try {
            if (!googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.my_map_style))) {
                Log.e("TAG", "Style parsing failed.");
            }
        } catch (Throwable e) {
            Log.e("TAG", "Can't find style. Error: ", e);
        }
        this.googleMapUtils.onGoogleMapReady(googleMap, false);
        this.googleMapUtils.addMarker(this.propertyLatLng, this.searchProperty, this.fromBookings);
        if (this.fromBookings != null) {
            this.addressApperenceTextview.setVisibility(8);
        } else {
            this.addressApperenceTextview.setVisibility(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMapAnimationFinish(String str) {
        showToast("Camera Animation finished");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getPropertyLatLng(LatLngEvent latLngEvent) {
        if (latLngEvent != null) {
            this.propertyLatLng = latLngEvent.getLatLng();
            this.searchProperty = latLngEvent.getSearchProperty();
            this.fromBookings = latLngEvent.isFromBookings();
            EventBus.getDefault().removeStickyEvent(LatLngEvent.class);
        }
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
