package com.businesslogic.location;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import com.businesslogic.framework.IBaseView;
import com.google.android.gms.maps.model.LatLng;

public interface ILocationView extends IBaseView {
    Activity getActivityInstance();

    Context getSekenApplicationContext();

    void setAddress(Address address, String str);

    void setCurrentLocation(LatLng latLng);

    void showLocationPermissionDeniedAlert(boolean z);
}
