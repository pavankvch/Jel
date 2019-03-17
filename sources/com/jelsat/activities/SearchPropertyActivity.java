package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.searchproperty.ISearchPropertyView;
import com.businesslogic.searchproperty.SearchPropertyPresenter;
import com.data.amenitiesandhouserules.PropertyType;
import com.data.filter.FilterData;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.searchproperty.SearchPropertyRequest;
import com.data.searchproperty.SearchPropertyResponse;
import com.data.searchproperty.SelectedPropertyListData;
import com.data.utils.APIError;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.maps.model.LatLng;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.events.ApplyFilterEvent;
import com.jelsat.events.ChangeFavouriteIconEvent;
import com.jelsat.events.ChangePropertyFavouriteEvent;
import com.jelsat.events.DateGuestPropertyDialogEvent;
import com.jelsat.events.FragmentLoadedEvent;
import com.jelsat.events.LatLngEvent;
import com.jelsat.events.PropertiesListEvent;
import com.jelsat.events.SearchPropertyEvent;
import com.jelsat.events.SetFilterDataEvent;
import com.jelsat.fragments.DateGuestPropertySelectionDialog;
import com.jelsat.fragments.SearchPropertyListFragment;
import com.jelsat.fragments.SearchPropertyListMapFragment;
import com.jelsat.fragments.SortDialogFragment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SearchPropertyActivity extends BaseAppCompactActivity implements ISearchPropertyView {
    public static boolean checkNearBy = false;
    public static boolean filterApplied = false;
    public static String selecteCheckInDate = "";
    public static String selectedCheckOutDate = "";
    private String TAG = SearchPropertyActivity.class.getSimpleName();
    private List<String> amenityIds;
    @BindView(2131362186)
    TextView badgeCount;
    private String bookingType;
    @BindView(2131361920)
    LinearLayout bottomFilter;
    @BindView(2131361922)
    CustomTextView bottomMap;
    @BindView(2131361923)
    CustomTextView bottomSaved;
    @BindView(2131361925)
    CustomTextView bottomSort;
    private Date checkInDate;
    @BindView(2131361990)
    TextView checkInOutDate;
    private Date checkoutDate;
    private FilterData filterData;
    private String guestCount = AppEventsConstants.EVENT_PARAM_VALUE_YES;
    @BindView(2131362205)
    TextView guestsTV;
    private boolean isMapSelected = false;
    public boolean isNearBy;
    private boolean isSavedSelected = false;
    private LatLng latLng;
    private String locationName = "";
    @BindView(2131362310)
    TextView locationText;
    @BindView(2131362448)
    TextView propertyTV;
    private Map<String, PropertyType> propertyTypeMap = new HashMap();
    private Map<String, SearchProperty> savedPropertyMap = new LinkedHashMap();
    @BindView(2131362565)
    View searchHead_main;
    private Map<String, SearchProperty> searchPropertyMap = new LinkedHashMap();
    private SearchPropertyPresenter searchPropertyPresenter = new SearchPropertyPresenter(this, RetrofitClient.getAPIService());
    private SearchPropertyRequest searchPropertyRequest;
    private int sortIndex = -1;
    @BindView(2131362071)
    CustomTextView toolbar_date;
    @BindView(2131362199)
    CustomTextView toolbar_guest;
    @BindView(2131362443)
    CustomTextView toolbar_property;

    public int getActivityLayout() {
        return R.layout.activity_search_property;
    }

    @OnClick({2131361892})
    public void clickOnBack(View view) {
        finish();
    }

    @OnClick({2131362073})
    public void clickOnDate() {
        showDateGuestPropertyDialog(0);
    }

    @OnClick({2131362201})
    public void clickOnGuests() {
        showDateGuestPropertyDialog(1);
    }

    @OnClick({2131362471})
    public void clickOnProperty() {
        showDateGuestPropertyDialog(2);
    }

    @OnClick({2131362310})
    public void clickOnALLCities() {
        Intent intent = new Intent(this, AllLocalitiesActivity.class);
        intent.setFlags(67108864);
        startActivity(intent);
    }

    @OnClick({2131361923})
    public void clickOnSaved() {
        if (filterApplied) {
            this.badgeCount.setVisibility(0);
        }
        this.isSavedSelected ^= true;
        changeSavedIconBackground(this.isSavedSelected);
        changeAdapterDataAccordingToSaved(this.isSavedSelected);
    }

    @OnClick({2131361925})
    public void clickOnSort() {
        showBottomDialogFragment(this.sortIndex);
    }

    @OnClick({2131361920})
    public void clickOnFilter() {
        EventBus.getDefault().postSticky(new SetFilterDataEvent(this.filterData, this.bookingType, this.amenityIds));
        startActivity(new Intent(this, FiltersActivity.class));
    }

    @OnClick({2131361922})
    public void clickOnMap() {
        if (filterApplied) {
            this.badgeCount.setVisibility(0);
        }
        this.isMapSelected ^= true;
        changeMapTabIcons(this.isMapSelected);
        changeFragmentAccordingToSelection(this.isMapSelected);
    }

    private void changeSavedIconBackground(boolean z) {
        if (filterApplied) {
            this.badgeCount.setVisibility(0);
        }
        if (z) {
            this.bottomSaved.setTextColor(applyColor(R.color.white));
            this.bottomSaved.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_saved, 0, 0);
            this.bottomSaved.setBackgroundResource(R.color.app_background);
            return;
        }
        this.bottomSaved.setTextColor(applyColor(R.color.light_text_color));
        this.bottomSaved.setBackgroundResource(R.color.white);
        this.bottomSaved.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_saved_grey, 0, 0);
    }

    private void changeMapTabIcons(boolean z) {
        if (filterApplied) {
            this.badgeCount.setVisibility(0);
        }
        if (z) {
            this.bottomMap.setText(getString(R.string.search_bottom_layout_list));
            this.bottomMap.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_list_view, 0, 0);
            return;
        }
        this.bottomMap.setText(getString(R.string.search_bottom_layout_map));
        this.bottomMap.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_map_view, 0, 0);
    }

    private void changeAdapterDataAccordingToSaved(boolean z) {
        if (z) {
            EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.savedPropertyMap, true)));
        } else {
            EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.searchPropertyMap, false)));
        }
    }

    private void changeFragmentAccordingToSelection(boolean z) {
        if (z) {
            this.bottomSort.setTextColor(applyColor(R.color.grey));
            this.bottomSort.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sort_disable, 0, 0);
            loadFragment(new SearchPropertyListMapFragment());
            this.searchHead_main.setVisibility(8);
        } else {
            this.searchHead_main.setVisibility(0);
            this.bottomSort.setTextColor(applyColor(R.color.light_text_color));
            this.bottomSort.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sort, 0, 0);
            loadFragment(new SearchPropertyListFragment());
        }
        this.bottomSort.setEnabled(z ^ 1);
        this.bottomSort.setClickable(z ^ 1);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        loadFragment(new SearchPropertyListFragment());
    }

    protected void onStart() {
        super.onStart();
        Log.e(this.TAG, "OnStart() call");
        this.badgeCount.setVisibility(8);
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getLocality(SearchPropertyEvent searchPropertyEvent) {
        Log.e(this.TAG, "getLocality() call");
        if (searchPropertyEvent != null) {
            this.isNearBy = searchPropertyEvent.isNearBy();
            this.bookingType = searchPropertyEvent.getBookingType();
            if (searchPropertyEvent.getLocality() != null) {
                this.locationName = searchPropertyEvent.getLocality().getName();
                this.latLng = new LatLng(Double.parseDouble(searchPropertyEvent.getLocality().getLat()), Double.parseDouble(searchPropertyEvent.getLocality().getLng()));
            }
            TextView textView;
            Object[] objArr;
            StringBuilder stringBuilder;
            if (TextUtils.isEmpty(this.locationName)) {
                textView = this.locationText;
                objArr = new Object[2];
                objArr[0] = getString(R.string.search_you_are_in_label);
                stringBuilder = new StringBuilder("<font color='");
                stringBuilder.append(getResources().getColor(R.color.app_background));
                stringBuilder.append("'>");
                stringBuilder.append(getString(R.string.dashboard_home_all_cities));
                stringBuilder.append("</font>");
                objArr[1] = stringBuilder.toString();
                textView.setText(Html.fromHtml(String.format("%s %s", objArr)));
            } else {
                textView = this.locationText;
                objArr = new Object[2];
                objArr[0] = getString(R.string.search_you_are_in_label);
                stringBuilder = new StringBuilder("<font color='");
                stringBuilder.append(getResources().getColor(R.color.app_background));
                stringBuilder.append("'>");
                stringBuilder.append(this.locationName);
                stringBuilder.append("</font>");
                objArr[1] = stringBuilder.toString();
                textView.setText(Html.fromHtml(String.format("%s %s", objArr)));
            }
            EventBus.getDefault().removeStickyEvent(searchPropertyEvent);
            setSearchPropertyRequest(buildSearchPropertyRequest());
            doSearchPropertyNetworkCall();
        }
    }

    private SearchPropertyRequest buildSearchPropertyRequest() {
        Log.e(this.TAG, "SearchPropertyRequest() call");
        String str = "";
        String str2 = "";
        this.searchPropertyRequest = new SearchPropertyRequest();
        if (this.latLng != null) {
            str = String.valueOf(this.latLng.latitude);
            str2 = String.valueOf(this.latLng.longitude);
        }
        if (this.bookingType != null) {
            this.searchPropertyRequest.setBookingType(this.bookingType);
        }
        this.searchPropertyRequest.setLat(str);
        this.searchPropertyRequest.setLng(str2);
        this.searchPropertyRequest.setNearBy(this.isNearBy);
        checkNearBy = this.isNearBy;
        this.searchPropertyRequest.setNoOfGuests(this.guestCount);
        return this.searchPropertyRequest;
    }

    public SearchPropertyRequest getSearchPropertyRequest() {
        Log.e(this.TAG, "getSearchPropertyRequest() call");
        return this.searchPropertyRequest;
    }

    public void setSearchPropertyRequest(SearchPropertyRequest searchPropertyRequest) {
        Log.e(this.TAG, "setSearchPropertyRequest() call ");
        this.searchPropertyRequest = searchPropertyRequest;
    }

    private void doSearchPropertyNetworkCall() {
        Log.e(this.TAG, "doSearchPropertyNetworkCall() call");
        if (isNetworkConnected()) {
            this.searchPropertyPresenter.getSearchPropertyResults(getString(R.string.please_wait), getSearchPropertyRequest());
        } else {
            showToast(getString(R.string.internet_connection_label));
        }
    }

    public void onSuccessResponse(SearchPropertyResponse searchPropertyResponse) {
        if (searchPropertyResponse != null) {
            if (searchPropertyResponse.getProperties() != null) {
                if (!(this.searchPropertyMap == null || this.searchPropertyMap.isEmpty())) {
                    this.searchPropertyMap.clear();
                }
                for (SearchProperty searchProperty : searchPropertyResponse.getProperties()) {
                    this.searchPropertyMap.put(searchProperty.getPropertyId(), searchProperty);
                }
                filterSavedProperties(this.searchPropertyMap.values());
            }
            this.amenityIds = new ArrayList();
            this.amenityIds.addAll(searchPropertyResponse.getAmenities());
            if (this.isSavedSelected != null) {
                EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.savedPropertyMap, true)));
                return;
            } else {
                EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.searchPropertyMap, false)));
                return;
            }
        }
        showToast(getString(R.string.search_no_property_found));
    }

    private void filterSavedProperties(Collection<SearchProperty> collection) {
        if (!(this.savedPropertyMap == null || this.savedPropertyMap.isEmpty())) {
            this.savedPropertyMap.clear();
        }
        for (SearchProperty searchProperty : collection) {
            if (searchProperty.getFavourite() == 1) {
                this.savedPropertyMap.put(searchProperty.getPropertyId(), searchProperty);
            }
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    private void showBottomDialogFragment(int i) {
        Log.e(this.TAG, "showBottomDialogFragment() call ");
        i = SortDialogFragment.newInstance(i);
        i.show(getSupportFragmentManager(), "sortDialog");
        i.setListener(new SearchPropertyActivity$1(this));
    }

    private void showDateGuestPropertyDialog(int i) {
        Log.e(this.TAG, "showDateGuestPropertyDialog() call");
        DateGuestPropertySelectionDialog.newInstance(i, this.checkInDate, this.checkoutDate, this.guestCount, this.propertyTypeMap, this.locationName).show(getSupportFragmentManager(), "DateGuestPropertySelectionDialog");
    }

    private void loadFragment(Fragment fragment) {
        Log.e(this.TAG, "loadFragment() call ");
        if (filterApplied) {
            this.badgeCount.setVisibility(0);
        }
        new Handler().post(new SearchPropertyActivity$2(this, fragment));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getFilterData(ApplyFilterEvent applyFilterEvent) {
        Log.e(this.TAG, "getFilterData() call ");
        if (applyFilterEvent != null) {
            if (this.searchPropertyPresenter != null) {
                if (filterApplied) {
                    this.badgeCount.setVisibility(0);
                }
                this.filterData = applyFilterEvent.getFilterData();
                this.bookingType = this.filterData.getBookingType();
                this.searchPropertyRequest.setBookingType(this.filterData.getBookingType());
                this.searchPropertyRequest.setAmenities(new ArrayList(this.filterData.getAmenityMap().keySet()));
                this.searchPropertyRequest.setSingleBeds(this.filterData.getSingleBeds());
                this.searchPropertyRequest.setDoubleBeds(this.filterData.getDoubleBeds());
                this.searchPropertyRequest.setCancelPolicy(new ArrayList(this.filterData.getCancelPolicyMap().keySet()));
                this.searchPropertyRequest.setHouseRules(new ArrayList(this.filterData.getHouseRuleMap().keySet()));
                this.searchPropertyRequest.setPriceFrom(this.filterData.getPriceFrom());
                this.searchPropertyRequest.setPriceTo(this.filterData.getPriceTo());
                this.searchPropertyRequest.setRooms(this.filterData.getRooms());
                this.searchPropertyPresenter.getSearchPropertyResults(getString(R.string.please_wait), this.searchPropertyRequest);
            }
            EventBus.getDefault().removeStickyEvent(applyFilterEvent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void isFragmentLoaded(FragmentLoadedEvent fragmentLoadedEvent) {
        if (fragmentLoadedEvent != null) {
            EventBus.getDefault().post(new LatLngEvent(this.latLng));
            if (this.isSavedSelected != null) {
                EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.savedPropertyMap, true)));
                return;
            }
            EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.searchPropertyMap, false)));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateSavedAndSearchPropertyMap(ChangePropertyFavouriteEvent changePropertyFavouriteEvent) {
        if (changePropertyFavouriteEvent != null) {
            int parseInt = Integer.parseInt(changePropertyFavouriteEvent.getResponse().getFavourite());
            changePropertyFavouriteEvent = changePropertyFavouriteEvent.getResponse().getPropertyId();
            if (this.searchPropertyMap.containsKey(changePropertyFavouriteEvent)) {
                SearchProperty searchProperty = (SearchProperty) this.searchPropertyMap.get(changePropertyFavouriteEvent);
                searchProperty.setFavourite(parseInt);
                this.searchPropertyMap.put(changePropertyFavouriteEvent, searchProperty);
                if (this.savedPropertyMap.containsKey(changePropertyFavouriteEvent)) {
                    this.savedPropertyMap.remove(changePropertyFavouriteEvent);
                    return;
                }
                this.savedPropertyMap.put(changePropertyFavouriteEvent, searchProperty);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDateGuestPropertyValues(DateGuestPropertyDialogEvent dateGuestPropertyDialogEvent) {
        if (dateGuestPropertyDialogEvent != null) {
            this.propertyTypeMap = dateGuestPropertyDialogEvent.getSelectedPropertyTypes();
            this.guestCount = dateGuestPropertyDialogEvent.getGuestCount();
            this.checkInDate = dateGuestPropertyDialogEvent.getCheckInDate();
            this.checkoutDate = dateGuestPropertyDialogEvent.getCheckOutDate();
            if (this.checkInDate == null && this.checkoutDate == null) {
                this.checkInOutDate.setVisibility(8);
                this.toolbar_date.setVisibility(0);
            } else {
                this.toolbar_date.setVisibility(8);
                this.checkInOutDate.setVisibility(0);
                this.checkInOutDate.setText(String.format("%s - %s", new Object[]{convertDateToString("dd MMM", this.checkInDate), convertDateToString("dd MMM", this.checkoutDate)}));
                selecteCheckInDate = convertDateToString("dd-MM-yyyy", this.checkInDate);
                selectedCheckOutDate = convertDateToString("dd-MM-yyyy", this.checkoutDate);
            }
            TextView textView;
            StringBuilder stringBuilder;
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.toolbar_guest.setVisibility(8);
                this.guestsTV.setVisibility(0);
                textView = this.guestsTV;
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(dateGuestPropertyDialogEvent.getGuestCount());
                textView.setText(String.format(stringBuilder.toString(), new Object[]{getString(R.string.search_guests_label)}));
            } else {
                this.toolbar_guest.setVisibility(8);
                this.guestsTV.setVisibility(0);
                textView = this.guestsTV;
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.search_guests_label));
                textView.setText(String.format(stringBuilder.toString(), new Object[]{dateGuestPropertyDialogEvent.getGuestCount()}));
            }
            setSelectedPropertiesToTextView(dateGuestPropertyDialogEvent.getSelectedPropertyTypes());
            if (dateGuestPropertyDialogEvent.getCheckInDate() != null) {
                this.searchPropertyRequest.setCheckIn(convertDateToString("yyyy-MM-dd", dateGuestPropertyDialogEvent.getCheckInDate()));
            }
            if (dateGuestPropertyDialogEvent.getCheckOutDate() != null) {
                this.searchPropertyRequest.setCheckOut(convertDateToString("yyyy-MM-dd", dateGuestPropertyDialogEvent.getCheckOutDate()));
            }
            this.searchPropertyRequest.setNoOfGuests(this.guestCount);
            if (this.propertyTypeMap != null && this.propertyTypeMap.isEmpty() == null) {
                this.searchPropertyRequest.setPropertyType(new ArrayList(this.propertyTypeMap.keySet()));
            } else if (!(this.propertyTypeMap == null || this.propertyTypeMap.isEmpty() == null)) {
                this.searchPropertyRequest.setPropertyType(new ArrayList(this.propertyTypeMap.keySet()));
            }
            doSearchPropertyNetworkCall();
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void changeProperty(ChangeFavouriteIconEvent changeFavouriteIconEvent) {
        if (changeFavouriteIconEvent != null) {
            int favourite = changeFavouriteIconEvent.getFavourite();
            String propertyId = changeFavouriteIconEvent.getPropertyId();
            if (this.searchPropertyMap.containsKey(propertyId)) {
                SearchProperty searchProperty = (SearchProperty) this.searchPropertyMap.get(propertyId);
                searchProperty.setFavourite(favourite);
                this.searchPropertyMap.put(propertyId, searchProperty);
                if (this.savedPropertyMap.containsKey(propertyId)) {
                    this.savedPropertyMap.remove(propertyId);
                } else {
                    this.savedPropertyMap.put(propertyId, searchProperty);
                }
                if (this.isSavedSelected) {
                    EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.savedPropertyMap, true)));
                } else {
                    EventBus.getDefault().postSticky(new PropertiesListEvent(getPropertyDataObject(this.searchPropertyMap, false)));
                }
            }
            EventBus.getDefault().removeStickyEvent(changeFavouriteIconEvent);
        }
    }

    private void setSelectedPropertiesToTextView(Map<String, PropertyType> map) {
        List arrayList = new ArrayList(map.values());
        Log.e(this.TAG, "setSelectedPropertiesToTextView() call/*, filter value : */");
        if (map.isEmpty()) {
            this.toolbar_property.setVisibility(0);
            this.propertyTV.setVisibility(8);
            this.propertyTV.setText(getString(R.string.search_select_property_label));
            return;
        }
        this.toolbar_property.setVisibility(8);
        this.propertyTV.setVisibility(0);
        StringBuilder stringBuilder;
        if (map.size() > 2) {
            map.keySet().iterator().toString();
            map = this.propertyTV;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((PropertyType) arrayList.get(0)).getName().toString());
            stringBuilder.append(", ");
            stringBuilder.append(((PropertyType) arrayList.get(1)).getName().toString());
            stringBuilder.append(" +");
            stringBuilder.append(arrayList.size() - 2);
            map.setText(stringBuilder.toString());
        } else if (map.size() == 2) {
            map = this.propertyTV;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((PropertyType) arrayList.get(0)).getName().toString());
            stringBuilder.append(", ");
            stringBuilder.append(((PropertyType) arrayList.get(1)).getName().toString());
            map.setText(stringBuilder.toString());
        } else {
            this.propertyTV.setText(((PropertyType) arrayList.get(0)).getName().toString());
        }
    }

    protected void onStop() {
        Log.e(this.TAG, " onStop() call");
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        Log.e(this.TAG, " onDetachedFromWindow() call");
        if (this.searchPropertyPresenter != null) {
            this.searchPropertyPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    private String convertDateToString(String str, Date date) {
        Log.e(this.TAG, "convertDateToString() call");
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    private SelectedPropertyListData getPropertyDataObject(Map<String, SearchProperty> map, boolean z) {
        Log.e(this.TAG, "getPropertyDataObject() call");
        SelectedPropertyListData selectedPropertyListData = new SelectedPropertyListData();
        selectedPropertyListData.setPropertyList(map.values());
        selectedPropertyListData.setGuestCount(this.guestCount);
        selectedPropertyListData.setSaved(z);
        selectedPropertyListData.setSelectedCheckInDate(this.checkInDate);
        selectedPropertyListData.setSelectedCheckoutDate(this.checkoutDate);
        selectedPropertyListData.setLocationName(this.locationName);
        return selectedPropertyListData;
    }
}
