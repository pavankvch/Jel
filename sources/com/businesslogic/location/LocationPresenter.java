package com.businesslogic.location;

import android.content.Intent;
import com.data.location.GeoAddressService;
import com.data.location.GeoAddressService.LocationAddressEvent;
import com.data.location.LocationService;
import com.data.location.LocationService.LocationChangedEvent;
import com.data.location.LocationService.LocationRequestEvent;
import com.data.location.LocationService.PermissionDeniedEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class LocationPresenter {
    private ILocationView chooseLocationView;
    private LocationService locationService;

    public LocationPresenter(ILocationView iLocationView, boolean z, boolean z2) {
        this.chooseLocationView = iLocationView;
        this.locationService = new LocationService(iLocationView.getActivityInstance(), z, z2);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.locationService.onActivityResult(i, i2, intent);
    }

    public void requestPermissionResult(int i, String[] strArr, int[] iArr) {
        this.locationService.requestPermissionResult(i, strArr, iArr);
    }

    public void unRegisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void setCurrentLocation(LocationChangedEvent locationChangedEvent) {
        this.chooseLocationView.dismissProgress();
        this.chooseLocationView.setCurrentLocation(locationChangedEvent.getLocation());
        if (!locationChangedEvent.isFromAddProperty()) {
            GeoAddressService.getInstance(this.chooseLocationView.getActivityInstance()).requestAddress(locationChangedEvent.getLocation());
        }
    }

    @Subscribe
    public void onLocationRequest(LocationRequestEvent locationRequestEvent) {
        this.chooseLocationView.showLoading();
    }

    @Subscribe
    public void onLocationPermissionDenied(PermissionDeniedEvent permissionDeniedEvent) {
        this.chooseLocationView.showLocationPermissionDeniedAlert(permissionDeniedEvent.isCheckNeverAsk());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setCurrentAddressLocation(LocationAddressEvent locationAddressEvent) {
        this.chooseLocationView.dismissProgress();
        this.chooseLocationView.setAddress(locationAddressEvent.getAddress(), locationAddressEvent.getPinCode());
    }

    public void setShowGPSDialog(boolean z) {
        this.locationService.setShowGPSDialog(z);
    }

    public void onStop() {
        unRegisterEventBus();
    }

    public void onStart() {
        EventBus.getDefault().register(this);
    }

    public void requestLocationUpdate() {
        this.locationService.checkForLocationPermission();
    }
}
