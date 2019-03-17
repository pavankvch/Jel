package com.jelsat.activities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.location.ILocationView;
import com.businesslogic.location.LocationPresenter;
import com.data.addproperty.Steps;
import com.data.location.GeoAddressService;
import com.data.propertydetail.FullInfo;
import com.data.searchproperty.SearchProperty;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.GoogleMapUtils;
import com.jelsat.utils.GoogleMapUtils.CameraIdleEvent;
import com.jelsat.utils.GoogleMapUtils.GoogleMapSnapShot;
import com.jelsat.utils.GooglePlaceUtils;
import com.jelsat.utils.GooglePlaceUtils.PlaceReceivedEvent;
import com.jelsat.utils.Utils;
import java.io.IOException;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SaveLocation extends BaseAppCompactActivity implements ILocationView, OnMapReadyCallback {
    @BindView(2131361850)
    Button add_your_property;
    private String areaName;
    private boolean checkIntent;
    private LocationPresenter chooseLocationPresenter;
    private double cityLat;
    private double cityLon;
    @BindView(2131362008)
    EditText city_name;
    private String countryCode;
    private String countryName;
    private String editLatValue = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private String editLonValue = AppEventsConstants.EVENT_PARAM_VALUE_NO;
    private Bundle extraBundle;
    private FullInfo fullInfo;
    private GoogleMapUtils googleMapUtils;
    private GooglePlaceUtils googlePlaceUtils;
    private LatLng latLngValue;
    @BindView(2131362320)
    FrameLayout mapFrameLayout;
    private String postalcode;
    @BindView(2131362532)
    EditText save_address;
    @BindView(2131362537)
    EditText save_house;
    @BindView(2131362539)
    EditText save_landmark;
    private SearchProperty searchProperty;
    private String stateName;
    private Steps steps;
    private final TextWatcher watcher = new SaveLocation$1(this);

    public Activity getActivityInstance() {
        return this;
    }

    public int getActivityLayout() {
        return R.layout.activity_save_location;
    }

    @OnClick({2131362343})
    public void clickOnMyLocationFab() {
        this.chooseLocationPresenter.setShowGPSDialog(true);
        this.chooseLocationPresenter.requestLocationUpdate();
    }

    @OnClick({2131361850})
    public void addYourProperty() {
        this.googleMapUtils.getGoogleMapScreenShot();
    }

    @OnClick({2131362566})
    public void searchIcon() {
        this.googlePlaceUtils.openSearch(this, false, this.countryCode);
    }

    @OnClick({2131362532})
    public void saveAddress() {
        this.googlePlaceUtils.openSearch(this, false, this.countryCode);
    }

    @OnClick({2131362008})
    public void cityName() {
        if (this.save_address.getText().toString().trim().length() > 0 && this.city_name.getText().toString().trim().length() <= 0) {
            this.googlePlaceUtils.openSearch(this, true, this.countryCode);
        }
    }

    @OnClick({2131361890})
    public void back() {
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mapFrameLayout.setLayoutParams(new LayoutParams(-1, (int) (((double) Utils.getScreenHeightInPixels(this)) / 2.5d)));
        this.googleMapUtils = new GoogleMapUtils(this);
        this.googlePlaceUtils = new GooglePlaceUtils();
        this.chooseLocationPresenter = new LocationPresenter(this, false, true);
        this.chooseLocationPresenter.setShowGPSDialog(false);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        bundle = getIntent();
        if (getIntent() != null) {
            this.checkIntent = getIntent().getBooleanExtra("checkIntent", false);
        }
        this.extraBundle = bundle.getExtras();
        this.add_your_property.setEnabled(false);
        this.city_name.addTextChangedListener(this.watcher);
        this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
        if (this.checkIntent != null) {
            this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
            this.steps = (Steps) this.extraBundle.getParcelable("steps");
            bundle = this.searchProperty.getAddress();
            this.editLatValue = this.searchProperty.getLat();
            this.editLonValue = this.searchProperty.getLng();
            if (!(bundle == null || TextUtils.isEmpty(bundle))) {
                this.save_address.setText(bundle);
                this.save_house.setText(this.searchProperty.getFlatNo());
                this.city_name.setText(this.searchProperty.getCity());
                this.save_landmark.setText(this.searchProperty.getLandMark());
            }
            return;
        }
        if (this.searchProperty != null && TextUtils.isEmpty(this.searchProperty.getLat()) == null && TextUtils.isEmpty(this.searchProperty.getLng()) == null) {
            this.editLatValue = this.searchProperty.getLat();
            this.editLonValue = this.searchProperty.getLng();
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMapUtils.onGoogleMapReady(googleMap, true);
        this.googleMapUtils.initCameraIdleListener();
        if (!(TextUtils.isEmpty(this.editLatValue) || TextUtils.isEmpty(this.editLonValue))) {
            double parseDouble = Double.parseDouble(this.editLatValue);
            double parseDouble2 = Double.parseDouble(this.editLonValue);
            if (!(parseDouble == 0.0d || parseDouble2 == 0.0d)) {
                this.googleMapUtils.moveMapBasedOnLatLng(new LatLng(parseDouble, parseDouble2));
            }
        }
        try {
            if (googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.my_map_style)) == null) {
                Log.e("TAG", "Style parsing failed.");
            }
        } catch (GoogleMap googleMap2) {
            Log.e("TAG", "Can't find style. Error: ", googleMap2);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCameraIdleEventTriggered(CameraIdleEvent cameraIdleEvent) {
        if (cameraIdleEvent != null) {
            this.latLngValue = cameraIdleEvent.getLatLng();
            if (cameraIdleEvent.getLatLng().latitude != 0.0d && cameraIdleEvent.getLatLng().longitude != 0.0d) {
                GeoAddressService.getInstance(this).requestAddress(cameraIdleEvent.getLatLng());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void googleMapSnapShot(GoogleMapSnapShot googleMapSnapShot) {
        if (googleMapSnapShot != null) {
            EventBus.getDefault().postSticky(new SaveLocation$SendBitmapToAddPropertyLocatedScreen(this, googleMapSnapShot.getBitmap()));
            googleMapSnapShot = new Intent(this, AddPropertyLocated.class);
            if (this.checkIntent) {
                this.extraBundle.putParcelable("searchProperty", this.searchProperty);
                this.extraBundle.putParcelable("fullInfo", this.fullInfo);
                this.extraBundle.putParcelable("steps", this.steps);
            }
            this.extraBundle.putDouble("latitudeValue", this.latLngValue.latitude);
            this.extraBundle.putDouble("longitudeValue", this.latLngValue.longitude);
            googleMapSnapShot.putExtra("checkIntent", this.checkIntent);
            googleMapSnapShot.putExtras(this.extraBundle);
            PrefUtils.getInstance().saveSkipAddressBollen(Boolean.valueOf(false));
            googleMapSnapShot.putExtra("property_address", this.save_address.getText().toString());
            googleMapSnapShot.putExtra("cityLat", this.cityLat);
            googleMapSnapShot.putExtra("cityLong", this.cityLon);
            googleMapSnapShot.putExtra("flat_no", this.save_house.getText().toString());
            googleMapSnapShot.putExtra("postal_code", this.postalcode);
            googleMapSnapShot.putExtra("save_landmark", this.save_landmark.getText().toString());
            googleMapSnapShot.putExtra("city_name", this.city_name.getText().toString());
            googleMapSnapShot.putExtra("province", this.stateName);
            googleMapSnapShot.putExtra("area_name", this.areaName);
            googleMapSnapShot.putExtra(StringConstants.COUNTRY_NAME, this.countryName);
            googleMapSnapShot.putExtra("country_shot", this.countryCode);
            startActivity(googleMapSnapShot);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void googlePlaceReceivedEvent(PlaceReceivedEvent placeReceivedEvent) {
        if (placeReceivedEvent != null) {
            Place place = placeReceivedEvent.getPlace();
            if (placeReceivedEvent.isNeedFilter() == null) {
                this.latLngValue = place.getLatLng();
                this.googleMapUtils.moveMapBasedOnLatLng(place.getLatLng());
                return;
            }
            this.cityLat = place.getLatLng().latitude;
            this.cityLon = place.getLatLng().longitude;
            placeReceivedEvent = parseAddressBasedOnLocation(place.getLatLng());
            if (placeReceivedEvent != null) {
                this.city_name.setText(placeReceivedEvent.getLocality());
            }
        }
    }

    private Address parseAddressBasedOnLocation(LatLng latLng) {
        Address address = null;
        try {
            latLng = new Geocoder(this, Locale.getDefault()).getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (latLng == null || latLng.size() == 0) {
                return null;
            }
            Address address2 = (Address) latLng.get(0);
            try {
                this.city_name.setText(((Address) latLng.get(0)).getLocality());
                return address2;
            } catch (IOException e) {
                latLng = e;
                address = address2;
                latLng.printStackTrace();
                return address;
            }
        } catch (IOException e2) {
            latLng = e2;
            latLng.printStackTrace();
            return address;
        }
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        this.chooseLocationPresenter.onStart();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.chooseLocationPresenter.requestPermissionResult(i, strArr, iArr);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.chooseLocationPresenter.onActivityResult(i, i2, intent);
        this.googlePlaceUtils.onActivityResult(this, i, i2, intent);
    }

    protected void onStop() {
        if (this.chooseLocationPresenter != null) {
            this.chooseLocationPresenter.onStop();
        }
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void setCurrentLocation(LatLng latLng) {
        this.latLngValue = latLng;
        this.googleMapUtils.moveMapBasedOnLatLng(latLng);
    }

    public void setAddress(Address address, String str) {
        if (address != null) {
            Log.v("Full Address", address.toString());
            str = new StringBuilder();
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                if (address.getAddressLine(i) != null) {
                    str.append(address.getAddressLine(i));
                    str.append(", ");
                }
            }
            this.save_address.setText(str.toString());
            this.city_name.setText(address.getLocality());
            this.postalcode = address.getPostalCode();
            this.countryName = address.getCountryName();
            this.stateName = address.getAdminArea();
            this.areaName = address.getSubLocality();
            this.countryCode = address.getCountryCode();
        }
    }

    public Context getSekenApplicationContext() {
        return getApplicationContext();
    }

    public void showLocationPermissionDeniedAlert(boolean z) {
        hideKeyboard();
        if (z) {
            showPermissionDeniedDialogIfCheckNeverAskAgain(this, getString(true));
        } else {
            showToast(getString(true));
        }
    }

    private void showPermissionDeniedDialogIfCheckNeverAskAgain(Context context, String str) {
        context = new Builder(context).setTitle(R.string.settings_label).setIcon(R.mipmap.ic_app_icon).setMessage(str).setPositiveButton(17039370, new SaveLocation$2(this)).create();
        context.setCanceledOnTouchOutside(null);
        context.show();
    }
}
