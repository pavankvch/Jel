package com.data.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.google.android.gms.maps.model.LatLng;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GeoAddressService {
    private static GeoAddressService geoAddressService;
    private final Geocoder geocoder;
    private boolean isProcessing;

    public class LocationAddressEvent {
        private Address address;
        private String pinCode;

        public LocationAddressEvent(Address address) {
            this.address = address;
        }

        private String getPinCodeFromAddress(String str) {
            Pattern compile = Pattern.compile("^([1-9])([0-9]){5}$");
            for (String str2 : Arrays.asList(str.split("\\s+"))) {
                if (compile.matcher(str2).matches()) {
                    return str2;
                }
            }
            return null;
        }

        public Address getAddress() {
            return this.address;
        }

        public String getPinCode() {
            return this.pinCode;
        }
    }

    class RequestAddressEvent {
        private final LatLng location;

        public RequestAddressEvent(LatLng latLng) {
            this.location = latLng;
        }

        public LatLng getLocation() {
            return this.location;
        }
    }

    private GeoAddressService(Context context) {
        this.geocoder = new Geocoder(context, Locale.getDefault());
        EventBus.getDefault().register(this);
    }

    public static GeoAddressService getInstance(Context context) {
        if (geoAddressService == null) {
            geoAddressService = new GeoAddressService(context);
        }
        return geoAddressService;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAddressRequest(RequestAddressEvent requestAddressEvent) {
        Double valueOf = Double.valueOf(requestAddressEvent.getLocation().latitude);
        requestAddressEvent = Double.valueOf(requestAddressEvent.getLocation().longitude);
        if (valueOf.doubleValue() == 0.0d || requestAddressEvent.doubleValue() == 0.0d) {
            this.isProcessing = false;
            return;
        }
        try {
            requestAddressEvent = this.geocoder.getFromLocation(valueOf.doubleValue(), requestAddressEvent.doubleValue(), 1);
            if (requestAddressEvent != null) {
                if (requestAddressEvent.size() != 0) {
                    EventBus.getDefault().post(new LocationAddressEvent((Address) requestAddressEvent.get(0)));
                    this.isProcessing = false;
                    return;
                }
            }
            this.isProcessing = false;
        } catch (RequestAddressEvent requestAddressEvent2) {
            requestAddressEvent2.printStackTrace();
        }
    }

    public void requestAddress(LatLng latLng) {
        if (!this.isProcessing) {
            this.isProcessing = true;
            EventBus.getDefault().post(new RequestAddressEvent(latLng));
        }
    }
}
