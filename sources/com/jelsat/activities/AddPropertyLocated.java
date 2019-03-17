package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.GoogleMapUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AddPropertyLocated extends BaseAppCompactActivity implements IAddYourPropertyView, OnMapReadyCallback {
    String PropertyId;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361850)
    Button add_your_property;
    @BindView(2131361852)
    TextView addressLine1;
    String checkClick;
    boolean checkIntent;
    double cityLat;
    double cityLong;
    String editPropertyId;
    Bundle extraBundle;
    @BindView(2131362180)
    TextView flat_No;
    boolean forSkippingAddress;
    private GoogleMapUtils googleMapUtils;
    Intent intent;
    @BindView(2131362269)
    TextView landMark;
    LatLng latLng;
    String mainStep;
    @BindView(2131362321)
    ImageView mapImage;
    @BindView(2131362322)
    FrameLayout mapImageLayout;
    @BindView(2131362323)
    FrameLayout mapLayout;
    @BindView(2131362531)
    TextView saveAndExit;
    SearchProperty searchProperty = null;
    String streetName;
    String subStep;

    public int getActivityLayout() {
        return R.layout.add_property_located;
    }

    @OnClick({2131361893})
    public void backButton() {
        finish();
    }

    @OnClick({2131361852})
    public void clickOnAddress() {
        if (!this.forSkippingAddress) {
            finish();
        }
    }

    @OnClick({2131362322})
    public void clickOnMapLayout() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.intent = getIntent();
        this.extraBundle = this.intent.getExtras();
        this.PropertyId = this.extraBundle.getString("PropertyId");
        this.checkIntent = this.extraBundle.getBoolean("checkIntent");
        this.forSkippingAddress = PrefUtils.getInstance().getSkipAddressBollen().booleanValue();
        this.googleMapUtils = new GoogleMapUtils(this);
        if (this.checkIntent != null) {
            this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
            Steps steps = (Steps) this.extraBundle.getParcelable("steps");
            this.mainStep = steps.getStep();
            this.subStep = steps.getSubstep();
            this.editPropertyId = this.searchProperty.getPropertyId();
        }
        if (this.forSkippingAddress != null) {
            this.mapImageLayout.setVisibility(8);
            this.mapLayout.setVisibility(0);
            if (this.searchProperty.getFlatNo() != null || TextUtils.isEmpty(this.searchProperty.getFlatNo()) == null) {
                this.flat_No.setVisibility(0);
                this.flat_No.setText(this.searchProperty.getFlatNo());
            } else {
                this.flat_No.setVisibility(8);
            }
            if (TextUtils.isEmpty(this.searchProperty.getLandMark()) != null) {
                this.landMark.setVisibility(8);
            } else {
                this.landMark.setVisibility(0);
                this.landMark.setText(String.format("%s : %s", new Object[]{getString(R.string.property_location_landmark), this.searchProperty.getLandMark()}));
            }
            this.addressLine1.setText(this.searchProperty.getAddress());
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        } else {
            this.mapImageLayout.setVisibility(0);
            this.mapLayout.setVisibility(8);
            this.addressLine1.setText(this.intent.getStringExtra("property_address"));
            bundle = this.intent.getStringExtra("flat_no");
            if (!(this.intent.getStringExtra("area_name") == null || TextUtils.isEmpty(this.intent.getStringExtra("area_name")))) {
                this.streetName = this.intent.getStringExtra("area_name");
            }
            if (TextUtils.isEmpty(bundle)) {
                this.flat_No.setVisibility(8);
            } else {
                this.flat_No.setVisibility(0);
                this.flat_No.setText(bundle);
            }
            if (TextUtils.isEmpty(this.intent.getStringExtra("save_landmark"))) {
                this.landMark.setVisibility(8);
            } else {
                this.landMark.setVisibility(0);
                this.landMark.setText(String.format("%s : %s", new Object[]{getString(R.string.property_location_landmark), bundle}));
            }
            this.cityLat = this.intent.getDoubleExtra("cityLat", 0.0d);
            this.cityLong = this.intent.getDoubleExtra("cityLong", 0.0d);
            this.latLng = new LatLng(getIntent().getDoubleExtra("latitudeValue", 0.0d), getIntent().getDoubleExtra("longitudeValue", 0.0d));
        }
        this.saveAndExit.setOnClickListener(new AddPropertyLocated$1(this));
        this.add_your_property.setOnClickListener(new AddPropertyLocated$2(this));
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void ApiCallExecute() {
        AddPropertyRequest addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent) {
            addPropertyRequest.setPropertyid(this.PropertyId);
            addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
            addPropertyRequest.setSubstep("4");
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            addPropertyRequest.setPropertyid(this.editPropertyId);
        } else {
            addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) < 2 || Integer.parseInt(this.subStep) < 4) {
                addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
                addPropertyRequest.setSubstep("4");
            }
        }
        addPropertyRequest.setPropertyAddress(this.intent.getStringExtra("property_address"));
        addPropertyRequest.setFlatNo(this.intent.getStringExtra("flat_no"));
        addPropertyRequest.setPostalCode(this.intent.getStringExtra("postal_code"));
        if (TextUtils.isEmpty(this.intent.getStringExtra("save_landmark"))) {
            addPropertyRequest.setLandMark(this.intent.getStringExtra(""));
        } else {
            addPropertyRequest.setLandMark(this.intent.getStringExtra("save_landmark"));
        }
        addPropertyRequest.setCity(this.intent.getStringExtra("city_name"));
        if (TextUtils.isEmpty(this.streetName)) {
            addPropertyRequest.setStreet("");
        } else {
            addPropertyRequest.setStreet(this.streetName);
        }
        addPropertyRequest.setProvince(this.intent.getStringExtra("province"));
        addPropertyRequest.setCountry(this.intent.getStringExtra(StringConstants.COUNTRY_NAME));
        addPropertyRequest.setCountryShot(this.intent.getStringExtra("country_shot"));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.latLng.latitude);
        addPropertyRequest.setLatitude(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.latLng.longitude);
        addPropertyRequest.setLongitude(stringBuilder.toString());
        if (this.cityLat == 0.0d) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.latLng.latitude);
            addPropertyRequest.setCityLat(stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.cityLat);
            addPropertyRequest.setCityLat(stringBuilder.toString());
        }
        if (this.cityLong == 0.0d) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.latLng.longitude);
            addPropertyRequest.setCityLng(stringBuilder.toString());
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.cityLong);
            addPropertyRequest.setCityLng(stringBuilder.toString());
        }
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), addPropertyRequest);
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        if (this.checkClick.equals("nextbutton") != null) {
            addPropertyResponse = new Intent(this, AddYourPropertySteps.class);
            addPropertyResponse.setFlags(67108864);
            addPropertyResponse.putExtras(this.extraBundle);
            addPropertyResponse.putExtra("checkIntent", this.checkIntent);
            addPropertyResponse.putExtra("FromActivity", "fromStep2");
            startActivity(addPropertyResponse);
            return;
        }
        if (this.checkClick.equals("saveAndExit") != null) {
            addPropertyResponse = new Intent(this, DashBoardActivity.class);
            addPropertyResponse.addFlags(67108864);
            addPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
            startActivity(addPropertyResponse);
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getBitmap(SaveLocation$SendBitmapToAddPropertyLocatedScreen saveLocation$SendBitmapToAddPropertyLocatedScreen) {
        if (saveLocation$SendBitmapToAddPropertyLocatedScreen != null) {
            GlideApp.with(this).load(saveLocation$SendBitmapToAddPropertyLocatedScreen.getBitmap()).placeholder(R.drawable.default_logo).apply(new RequestOptions().transform(new RoundedCorners(8))).error(R.drawable.default_logo).into(this.mapImage);
            EventBus.getDefault().removeStickyEvent(saveLocation$SendBitmapToAddPropertyLocatedScreen);
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.addYourPropertyPresenter != null) {
            this.addYourPropertyPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
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
        this.googleMapUtils.initCameraIdleListener();
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        if (TextUtils.isEmpty(this.searchProperty.getLat()) == null && TextUtils.isEmpty(this.searchProperty.getLng()) == null) {
            double parseDouble = Double.parseDouble(this.searchProperty.getLat());
            double parseDouble2 = Double.parseDouble(this.searchProperty.getLng());
            if (parseDouble != 0.0d && parseDouble2 != 0.0d) {
                this.googleMapUtils.moveMapBasedOnLatLng(new LatLng(parseDouble, parseDouble2));
            }
        }
    }
}
