package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.SmoothScroller;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.businesslogic.propertyfavourite.IPropertyFavouriteView;
import com.businesslogic.propertyfavourite.PropertyFavouritePresenter;
import com.data.propertyfavourite.PropertyFavouriteRequest;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.searchproperty.SelectedPropertyListData;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.jelsat.R;
import com.jelsat.activities.HomeActivity;
import com.jelsat.activities.PropertyDetailActivity;
import com.jelsat.adapters.SearchPropertyAdapter;
import com.jelsat.adapters.SearchPropertyAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.ChangePropertyFavouriteEvent;
import com.jelsat.events.FragmentLoadedEvent;
import com.jelsat.events.LatLngEvent;
import com.jelsat.events.MarkerOnClickEvent;
import com.jelsat.events.PropertiesListEvent;
import com.jelsat.events.PropertyDetailEvent;
import com.jelsat.utils.GoogleMapUtils;
import com.jelsat.utils.GoogleMapUtils.CameraAnimationFinishEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SearchPropertyListMapFragment extends BaseFragment implements IPropertyFavouriteView, OnMapReadyCallback, SearchPropertyAdapter$OnListItemClickListener {
    private GoogleMapUtils googleMapUtils;
    private boolean isSaved;
    @BindView(2131362324)
    RecyclerView mapPropertyRecyclerView;
    @BindView(2131362371)
    TextView noResultTV;
    private SearchProperty property;
    private PropertyFavouritePresenter propertyFavouritePresenter;
    private Map<LatLng, SearchProperty> propertyListMap = new LinkedHashMap();
    private SearchPropertyAdapter searchPropertyAdapter;
    private SelectedPropertyListData selectedPropertyListData;
    private SmoothScroller smoothScroller;

    public int getFragmentLayoutId() {
        return R.layout.fragment_property_list_map;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMapAnimationFinish(String str) {
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.propertyFavouritePresenter = new PropertyFavouritePresenter(this, RetrofitClient.getAPIService());
        initMapPropertyRecyclerView();
        return layoutInflater;
    }

    private void initMapPropertyRecyclerView() {
        this.searchPropertyAdapter = new SearchPropertyAdapter(requireActivity(), null, this, true);
        this.mapPropertyRecyclerView.setLayoutManager(new SearchPropertyListMapFragment$1(this, getActivity(), 0, false));
        this.mapPropertyRecyclerView.setAdapter(this.searchPropertyAdapter);
        new PagerSnapHelper().attachToRecyclerView(this.mapPropertyRecyclerView);
        this.smoothScroller = new SearchPropertyListMapFragment$2(this, requireContext());
        initGoogleMap();
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
        EventBus.getDefault().post(new FragmentLoadedEvent(true));
    }

    private boolean isUserLoggedIn() {
        return (PrefUtils.getInstance().getUserAccessToken() == null || PrefUtils.getInstance().getCookie() == null) ? false : true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPropertyLocations(PropertiesListEvent propertiesListEvent) {
        if (propertiesListEvent != null) {
            this.selectedPropertyListData = propertiesListEvent.getSelectedPropertyListData();
            this.isSaved = propertiesListEvent.getSelectedPropertyListData().isSaved();
            List arrayList = new ArrayList(propertiesListEvent.getSelectedPropertyListData().getPropertyList());
            this.searchPropertyAdapter.setData(arrayList);
            if (!(this.propertyListMap == null || this.propertyListMap.isEmpty())) {
                this.propertyListMap.clear();
            }
            for (SearchProperty searchProperty : propertiesListEvent.getSelectedPropertyListData().getPropertyList()) {
                if (!(TextUtils.isEmpty(searchProperty.getLat()) || TextUtils.isEmpty(searchProperty.getLng()))) {
                    this.propertyListMap.put(new LatLng(Double.parseDouble(searchProperty.getLat()), Double.parseDouble(searchProperty.getLng())), searchProperty);
                }
            }
            if (arrayList.size() > 0) {
                this.googleMapUtils.moveMapBasedOnLatLng(new LatLng(Double.parseDouble(((SearchProperty) arrayList.get(0)).getLat()), Double.parseDouble(((SearchProperty) arrayList.get(0)).getLng())));
            }
            getActivity().runOnUiThread(new SearchPropertyListMapFragment$3(this));
            showNoResultVisibility(arrayList, propertiesListEvent.getSelectedPropertyListData().isSaved());
        }
    }

    private void showNoResultVisibility(List<SearchProperty> list, boolean z) {
        if (z) {
            this.noResultTV.setText(getText(R.string.no_saved_pro_found));
        } else {
            this.noResultTV.setText(getText(R.string.no_search_res_fou));
        }
        if (list == null || list.size() <= null) {
            this.noResultTV.setVisibility(false);
        } else {
            this.noResultTV.setVisibility(true);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPropertyLatLng(LatLngEvent latLngEvent) {
        if (latLngEvent != null) {
            this.googleMapUtils.setInitialMapCenter(latLngEvent.getLatLng());
            this.googleMapUtils.initMarkerOnClickListener();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setMarkerOnClick(MarkerOnClickEvent markerOnClickEvent) {
        if (markerOnClickEvent != null) {
            this.property = markerOnClickEvent.getProperty();
            this.googleMapUtils.updateMapCentre(markerOnClickEvent.getLatLng());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMapMarkerAnimationFinish(CameraAnimationFinishEvent cameraAnimationFinishEvent) {
        if (cameraAnimationFinishEvent != null) {
            this.smoothScroller.setTargetPosition(this.searchPropertyAdapter.getPosition(this.property));
            this.mapPropertyRecyclerView.getLayoutManager().startSmoothScroll(this.smoothScroller);
        }
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetach() {
        if (this.propertyFavouritePresenter != null) {
            this.propertyFavouritePresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void clickOnListItem(SearchProperty searchProperty, int i) {
        i = new PropertyDetailEvent(searchProperty);
        if (isUserLoggedIn() != null) {
            i.setShowMessage(true);
        }
        i.setCheckInDate(this.selectedPropertyListData.getSelectedCheckInDate());
        i.setCheckoutDate(this.selectedPropertyListData.getSelectedCheckoutDate());
        i.setGuestCount(this.selectedPropertyListData.getGuestCount());
        i.setLocationName(this.selectedPropertyListData.getLocationName());
        EventBus.getDefault().postSticky(i);
        startActivity(new Intent(getActivity(), PropertyDetailActivity.class));
    }

    public void setFavouriteProperty(SearchProperty searchProperty, int i) {
        this.property = searchProperty;
        int i2 = 1;
        if (isUserLoggedIn()) {
            PropertyFavouriteRequest propertyFavouriteRequest = new PropertyFavouriteRequest();
            propertyFavouriteRequest.setPropertyId(searchProperty.getPropertyId());
            if (searchProperty.getFavourite() != null) {
                i2 = 0;
            }
            propertyFavouriteRequest.setStatus(String.valueOf(i2));
            this.propertyFavouritePresenter.setPropertyFavouriteToUser(getString(R.string.please_wait), propertyFavouriteRequest, i);
            return;
        }
        searchProperty = new Bundle();
        searchProperty.putBoolean(StringConstants.FROM_PROPERTY_SEARCH, true);
        goToActivity(HomeActivity.class, searchProperty);
    }

    public void onSuccess(PropertyFavouriteResponse propertyFavouriteResponse, int i) {
        if (Integer.parseInt(propertyFavouriteResponse.getFavourite()) == 1) {
            showToast(getString(R.string.property_got_saved));
        } else {
            showToast(getString(R.string.property_got_unsaved));
        }
        this.searchPropertyAdapter.setPropertyItemFavourite(Integer.parseInt(propertyFavouriteResponse.getFavourite()), i, this.isSaved);
        if (this.isSaved != 0) {
            this.googleMapUtils.removeMarker(this.property);
        }
        EventBus.getDefault().post(new ChangePropertyFavouriteEvent(propertyFavouriteResponse, this.isSaved));
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }
}
