package com.jelsat.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.location.ILocationView;
import com.businesslogic.location.LocationPresenter;
import com.businesslogic.searchtoplocalities.ISearchTopLocalitiesView;
import com.businesslogic.searchtoplocalities.SearchTopLocalitiesPresenter;
import com.data.retrofit.RetrofitClient;
import com.data.searchtoplocalities.Locality;
import com.data.searchtoplocalities.SearchTopLocality;
import com.data.utils.APIError;
import com.google.android.gms.location.places.AutocompleteFilter.Builder;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.jelsat.R;
import com.jelsat.adapters.PlaceAutocompleteAdapter;
import com.jelsat.adapters.RecyclerViewSectionAdapter;
import com.jelsat.adapters.SearchTopLocalitiesAdapter;
import com.jelsat.adapters.SearchTopLocalitiesAdapter.OnListItemClickListener;
import com.jelsat.adapters.SectionNameAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.events.SearchPropertyEvent;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class ChooseLocationActivity extends BaseAppCompactActivity implements ILocationView, ISearchTopLocalitiesView, OnListItemClickListener {
    private static final String TAG = "ChooseLocationActivity";
    private LocationPresenter chooseLocationPresenter;
    private LatLng currentLocation;
    private PlaceAutocompleteAdapter mAdapter;
    private OnItemClickListener mAutocompleteClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            adapterView = ChooseLocationActivity.this.mAdapter.getItem(i);
            if (adapterView != null) {
                view = adapterView.getPlaceId();
                adapterView = adapterView.getPrimaryText(0);
                i = ChooseLocationActivity.TAG;
                j = new StringBuilder("Autocomplete item selected: ");
                j.append(adapterView);
                Log.i(i, j.toString());
                ChooseLocationActivity.this.mGeoDataClient.getPlaceById(new String[]{view}).addOnCompleteListener(ChooseLocationActivity.this.mUpdatePlaceDetailsCallback);
                i = ChooseLocationActivity.this.getApplicationContext();
                j = new StringBuilder("Clicked: ");
                j.append(adapterView);
                Toast.makeText(i, j.toString(), 0).show();
                adapterView = ChooseLocationActivity.TAG;
                i = new StringBuilder("Called getPlaceById to get Place details for ");
                i.append(view);
                Log.i(adapterView, i.toString());
            }
        }
    };
    @BindView(2131361887)
    AutoCompleteTextView mAutocompleteView;
    protected GeoDataClient mGeoDataClient;
    private OnCompleteListener<PlaceBufferResponse> mUpdatePlaceDetailsCallback = new OnCompleteListener<PlaceBufferResponse>() {
        public void onComplete(Task<PlaceBufferResponse> task) {
            try {
                PlaceBufferResponse placeBufferResponse = (PlaceBufferResponse) task.getResult();
                Place place = (Place) placeBufferResponse.get(0);
                Locality locality = new Locality();
                locality.setName(place.getName().toString());
                locality.setLat(String.valueOf(place.getLatLng().latitude));
                locality.setLng(String.valueOf(place.getLatLng().longitude));
                EventBus.getDefault().postSticky(new SearchPropertyEvent(locality, false));
                ChooseLocationActivity.this.startActivity(new Intent(ChooseLocationActivity.this, SearchPropertyActivity.class));
                String access$100 = ChooseLocationActivity.TAG;
                StringBuilder stringBuilder = new StringBuilder("Place details received: ");
                stringBuilder.append(place.getName());
                Log.i(access$100, stringBuilder.toString());
                placeBufferResponse.release();
            } catch (Task<PlaceBufferResponse> task2) {
                task2.printStackTrace();
                Log.e(ChooseLocationActivity.TAG, "Place query did not complete.", task2);
            }
        }
    };
    private SearchTopLocalitiesAdapter searchTopLocalitiesAdapter;
    private SearchTopLocalitiesPresenter searchTopLocalitiesPresenter;
    @BindView(2131362572)
    RecyclerView searchTopLocalityRecyclerView;

    public Activity getActivityInstance() {
        return this;
    }

    public int getActivityLayout() {
        return R.layout.activity_choose_location;
    }

    @OnClick({2131362349})
    public void clickOnNearByTextView() {
        hideKeyboard();
        this.chooseLocationPresenter.requestLocationUpdate();
    }

    @OnClick({2131361892})
    public void clickOnBackArrow() {
        hideKeyboard();
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.chooseLocationPresenter = new LocationPresenter(this, true, false);
        this.mGeoDataClient = Places.getGeoDataClient(this);
        this.searchTopLocalitiesAdapter = new SearchTopLocalitiesAdapter(this, null, this);
        this.searchTopLocalityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initPresenter();
        this.mAutocompleteView.setOnItemClickListener(this.mAutocompleteClickListener);
        this.mAdapter = new PlaceAutocompleteAdapter(this, this.mGeoDataClient, null, new Builder().setTypeFilter(0).build());
        this.mAutocompleteView.setAdapter(this.mAdapter);
        this.searchTopLocalityRecyclerView.setAdapter(new RecyclerViewSectionAdapter<Locality, SearchTopLocalitiesAdapter>(new SectionNameAdapter() {
            public String getFirstSectionName() {
                return ChooseLocationActivity.this.getString(R.string.recent_searches);
            }

            public String getSecondSectionName() {
                return ChooseLocationActivity.this.getString(R.string.top_localities);
            }

            public int getBackgroundColor() {
                return ContextCompat.getColor(ChooseLocationActivity.this, R.color.dash_board_bg_color);
            }

            public int getSectionTextColor() {
                return ContextCompat.getColor(ChooseLocationActivity.this, R.color.normal_text_color);
            }
        }, this.searchTopLocalitiesAdapter) {
            public int getIndexOfSection(Locality locality) {
                return (locality == null || locality.isTopRated() == null) ? 0 : 1;
            }
        });
    }

    private void initPresenter() {
        this.searchTopLocalitiesPresenter = new SearchTopLocalitiesPresenter(this, RetrofitClient.getAPIService());
        this.searchTopLocalitiesPresenter.fetchTopLocalities(getString(R.string.fetching_top_localities));
    }

    protected void onStart() {
        super.onStart();
        this.chooseLocationPresenter.onStart();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.chooseLocationPresenter.requestPermissionResult(i, strArr, iArr);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.chooseLocationPresenter.onActivityResult(i, i2, intent);
    }

    public void clickOnListItem(Locality locality, int i) {
        if (locality != null) {
            EventBus.getDefault().postSticky(new SearchPropertyEvent(locality, false));
            startActivity(new Intent(this, SearchPropertyActivity.class));
        }
    }

    public void onSuccessResponse(SearchTopLocality searchTopLocality) {
        List arrayList = new ArrayList();
        if (searchTopLocality != null) {
            arrayList.addAll(searchTopLocality.getRecent());
            for (Locality locality : searchTopLocality.getToprated()) {
                locality.setTopRated(true);
                arrayList.add(locality);
            }
        }
        this.searchTopLocalitiesAdapter.setData(arrayList);
    }

    public void onErrorResponse(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    protected void onStop() {
        if (this.chooseLocationPresenter != null) {
            this.chooseLocationPresenter.onStop();
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.searchTopLocalitiesPresenter != null) {
            this.searchTopLocalitiesPresenter.unSubscribeDisposable();
        }
        super.onDetachedFromWindow();
    }

    public void setCurrentLocation(LatLng latLng) {
        this.currentLocation = latLng;
    }

    public void setAddress(Address address, String str) {
        if (address != null) {
            str = new StringBuilder();
            if (!TextUtils.isEmpty(address.getSubLocality())) {
                str.append(address.getSubLocality());
                str.append(" ");
            }
            if (!TextUtils.isEmpty(address.getLocality())) {
                str.append(address.getLocality());
            }
            address = new Locality();
            address.setName(str.toString());
            address.setLat(String.valueOf(this.currentLocation.latitude));
            address.setLng(String.valueOf(this.currentLocation.longitude));
            EventBus.getDefault().postSticky(new SearchPropertyEvent(address, true));
            startActivity(new Intent(this, SearchPropertyActivity.class));
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
        context = new AlertDialog.Builder(context).setTitle(R.string.settings_label).setIcon(R.mipmap.ic_app_icon).setMessage(str).setPositiveButton(17039370, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface = new Intent();
                dialogInterface.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                dialogInterface.setData(Uri.fromParts("package", ChooseLocationActivity.this.getActivityInstance().getPackageName(), null));
                ChooseLocationActivity.this.startActivity(dialogInterface);
            }
        }).create();
        context.setCanceledOnTouchOutside(null);
        context.show();
    }
}
