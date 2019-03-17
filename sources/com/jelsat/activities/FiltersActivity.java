package com.jelsat.activities;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.amenitiesandhouserules.Amenity;
import com.data.amenitiesandhouserules.CancelPolicy;
import com.data.amenitiesandhouserules.Houserule;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.filter.FilterData;
import com.data.utils.FilterImage;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.adapters.AmenitiesAdapter;
import com.jelsat.adapters.AmenitiesAdapter$OnListItemClickListener;
import com.jelsat.adapters.CancellationPolicyAdapter;
import com.jelsat.adapters.CancellationPolicyAdapter$OnListItemClickListener;
import com.jelsat.adapters.FilterImageAdapter;
import com.jelsat.adapters.FilterImageAdapter$OnListItemClickListener;
import com.jelsat.adapters.HouseRulesAdapter;
import com.jelsat.adapters.HouseRulesAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.FilterConstants;
import com.jelsat.events.ApplyFilterEvent;
import com.jelsat.events.SetFilterDataEvent;
import com.jelsat.widgets.FancyButton;
import com.jelsat.widgets.PriceRangeSeekbar;
import com.jelsat.widgets.SegmentedGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FiltersActivity extends BaseAppCompactActivity implements AmenitiesAdapter$OnListItemClickListener, CancellationPolicyAdapter$OnListItemClickListener, FilterImageAdapter$OnListItemClickListener, HouseRulesAdapter$OnListItemClickListener {
    private static final String TAG = "FiltersActivity";
    private AmenitiesAdapter amenitiesAdapter;
    @BindView(2131361872)
    TextView amenitiesMoreTV;
    private Map<String, Amenity> amenityHashMap = new HashMap();
    @BindView(2131361873)
    RecyclerView aminitiesRecyclerView;
    @BindView(2131361881)
    FancyButton applyFilterButton;
    private Map<String, CancelPolicy> cancelPolicyMap = new HashMap();
    private CancellationPolicyAdapter cancellationPolicyAdapter;
    @BindView(2131361969)
    RecyclerView cancellationPolicyRecyclerView;
    @BindView(2131362107)
    SegmentedGroup doubleBedsRadioGroup;
    private FilterImageAdapter filterImageAdapter;
    @BindView(2131362172)
    RecyclerView filterImageRecyclerView;
    private String[] filterNames = new String[]{FilterConstants.BOOKING_TYPE, FilterConstants.PRICE_RANGE, FilterConstants.ROOMS, FilterConstants.BEDS, FilterConstants.AMENITIES, FilterConstants.HOUSE_RULES, FilterConstants.CANCELLATION_POLICY};
    @BindView(2131362174)
    NestedScrollView filterScrollView;
    @BindView(2131362218)
    CheckBox hoursBookingType;
    private Map<String, Houserule> houseRuleHashMap = new HashMap();
    private HouseRulesAdapter houseRulesAdapter;
    @BindView(2131362222)
    TextView houseRulesMoreTV;
    @BindView(2131362223)
    RecyclerView houseRulesRecyclerView;
    @BindView(2131362256)
    CheckBox instantBookingType;
    private boolean is24HoursSelected = false;
    private boolean isInstantSelected = false;
    private int maxPrice;
    private int minPrice;
    @BindView(2131362427)
    TextView priceTextView;
    @BindView(2131362492)
    PriceRangeSeekbar rangeSeekbar;
    @BindView(2131362529)
    SegmentedGroup roomsRadioGroup;
    private SeedResponse seedResponse;
    private int selectedMaxPrice;
    private int selectedMinPrice;
    @BindView(2131362595)
    SegmentedGroup singleBedsRadioGroup;

    public int getActivityLayout() {
        return R.layout.activity_filters;
    }

    @OnClick({2131362256})
    public void clickOnInstantBookingType() {
        Log.e(TAG, "clickOnInstantBookingType() is called, filter value : ");
        this.isInstantSelected ^= 1;
        if (this.hoursBookingType.isChecked()) {
            this.is24HoursSelected = false;
            changeBookingTypeAppearance(this.hoursBookingType, false);
            Log.e(TAG, "clickOnInstantBookingType() if condition");
        }
        changeBookingTypeAppearance(this.instantBookingType, this.isInstantSelected);
    }

    @OnClick({2131362218})
    public void clickOnHoursBookingType() {
        Log.e(TAG, "clickOnHoursBookingType() is called, filter value : ");
        this.is24HoursSelected ^= 1;
        if (this.instantBookingType.isChecked()) {
            this.isInstantSelected = false;
            Log.e(TAG, "clickOnHoursBookingType() if condition");
            changeBookingTypeAppearance(this.instantBookingType, false);
        }
        changeBookingTypeAppearance(this.hoursBookingType, this.is24HoursSelected);
    }

    @OnClick({2131361959})
    public void clickOnCancel() {
        Log.e(TAG, "clickOnCancel() is called, filter value : ");
        finish();
    }

    @OnClick({2131361872})
    public void clickOnAmenitiesMore() {
        Log.e(TAG, "clickOnAmenitiesMore() is called, filter value : ");
        if (this.amenitiesMoreTV.getText().toString().equalsIgnoreCase(getString(R.string.more))) {
            this.amenitiesMoreTV.setText(getString(R.string.less));
            this.amenitiesAdapter.loadAllItems(true);
            return;
        }
        this.amenitiesMoreTV.setText(getString(R.string.more));
        this.amenitiesAdapter.loadAllItems(false);
    }

    @OnClick({2131362222})
    public void clickOnHouseRulesMoreTV() {
        Log.e(TAG, "clickOnHouseRulesMoreTV()is called, filter value : ");
        if (this.houseRulesMoreTV.getText().toString().equalsIgnoreCase(getString(R.string.more))) {
            this.houseRulesMoreTV.setText(getString(R.string.less));
            this.houseRulesAdapter.loadAllItems(true);
            return;
        }
        this.houseRulesMoreTV.setText(getString(R.string.more));
        this.houseRulesAdapter.loadAllItems(false);
    }

    @OnClick({2131362509})
    public void clickOnReset() {
        resetAllFilters();
    }

    @OnClick({2131361881})
    public void clickOnApplyFilter() {
        Log.e(TAG, "clickOnApplyFilter() is called, filter value : ");
        EventBus.getDefault().postSticky(new ApplyFilterEvent(getFiltersData()));
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initFilterImageItems();
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            this.seedResponse = PrefUtils.getInstance().getSeedResponse();
        }
        if (this.seedResponse != null) {
            this.minPrice = this.seedResponse.getPriceRange().getMin();
            this.maxPrice = this.seedResponse.getPriceRange().getMax();
            this.selectedMinPrice = this.seedResponse.getPriceRange().getMin();
            this.selectedMaxPrice = this.seedResponse.getPriceRange().getMax();
        }
        initAmenitiesItems();
        initHouseRulesItems();
        initCancellationPolicyItems();
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
            this.applyFilterButton.setGravity(GravityCompat.START);
        }
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
            this.priceTextView.setText(String.format("%s %s - %s %s", new Object[]{Integer.valueOf(this.minPrice), getString(R.string.saudi_currency), Integer.valueOf(this.maxPrice), getString(R.string.saudi_currency)}));
        } else {
            this.priceTextView.setText(String.format("%s %s - %s %s", new Object[]{getString(R.string.saudi_currency), Integer.valueOf(this.minPrice), getString(R.string.saudi_currency), Integer.valueOf(this.maxPrice)}));
        }
        this.rangeSeekbar.setOnRangeSeekbarChangeListener(new FiltersActivity$1(this));
        this.rangeSeekbar.setOnRangeSeekbarFinalValueListener(new FiltersActivity$2(this));
        this.rangeSeekbar.setMinValue((float) this.minPrice).setMaxValue((float) this.maxPrice).setMinStartValue((float) this.selectedMinPrice).setMaxStartValue((float) this.selectedMaxPrice).setSteps(1.0f).apply();
        this.filterScrollView.setOnScrollChangeListener(new FiltersActivity$3(this, new Rect()));
        this.filterScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new FiltersActivity$4(this));
    }

    private int getBookingTypeColor(int i) {
        return ContextCompat.getColor(this, i);
    }

    private void scrollChange(NestedScrollView nestedScrollView, Rect rect) {
        int i = 0;
        LinearLayout linearLayout = (LinearLayout) nestedScrollView.getChildAt(0);
        int childCount = linearLayout.getChildCount();
        int i2 = childCount - 1;
        if (linearLayout.getChildAt(i2).getBottom() - (nestedScrollView.getHeight() + nestedScrollView.getScrollY()) == 0) {
            changeFilterIcon(i2);
            return;
        }
        while (i < childCount) {
            nestedScrollView = linearLayout.getChildAt(i);
            if (nestedScrollView == null || nestedScrollView.getLocalVisibleRect(rect) == null) {
                i++;
            } else {
                changeFilterIcon(i);
                return;
            }
        }
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void increaseLayoutMarginToLastPosition() {
        LinearLayout linearLayout = (LinearLayout) this.filterScrollView.getChildAt(0);
        linearLayout = (LinearLayout) linearLayout.getChildAt(linearLayout.getChildCount() - 1);
        int bottom = linearLayout.getBottom() - linearLayout.getTop();
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, this.filterScrollView.getBottom() - bottom);
        linearLayout.setLayoutParams(layoutParams);
    }

    private void changeFilterIcon(int i) {
        this.filterImageAdapter.setItemStatusChange(getFilterIconsList(i), i);
    }

    private void initFilterImageItems() {
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.filterImageAdapter = new FilterImageAdapter(this, getFilterIconsList(0), this);
        this.filterImageRecyclerView.setLayoutManager(linearLayoutManager);
        this.filterImageRecyclerView.setAdapter(this.filterImageAdapter);
    }

    private void initHouseRulesItems() {
        LayoutManager filtersActivity$5 = new FiltersActivity$5(this, this);
        this.houseRulesAdapter = new HouseRulesAdapter(this, null, this, false, true);
        this.houseRulesRecyclerView.setLayoutManager(filtersActivity$5);
        this.houseRulesRecyclerView.setAdapter(this.houseRulesAdapter);
    }

    private void initAmenitiesItems() {
        LayoutManager filtersActivity$6 = new FiltersActivity$6(this, this);
        this.amenitiesAdapter = new AmenitiesAdapter(this, null, this, false, true);
        this.aminitiesRecyclerView.setLayoutManager(filtersActivity$6);
        this.aminitiesRecyclerView.setAdapter(this.amenitiesAdapter);
    }

    private void initCancellationPolicyItems() {
        LayoutManager filtersActivity$7 = new FiltersActivity$7(this, this);
        this.cancellationPolicyAdapter = new CancellationPolicyAdapter(this, null, this);
        this.cancellationPolicyRecyclerView.setLayoutManager(filtersActivity$7);
        this.cancellationPolicyRecyclerView.setAdapter(this.cancellationPolicyAdapter);
    }

    private List<FilterImage> getFilterIconsList(int i) {
        List<FilterImage> arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.filterNames.length; i2++) {
            FilterImage filterImage = new FilterImage();
            filterImage.setFilterName(this.filterNames[i2]);
            if (i2 == i) {
                filterImage.setSelected(true);
            }
            arrayList.add(filterImage);
        }
        return arrayList;
    }

    public void clickOnListItem(Amenity amenity, int i, boolean z) {
        if (z) {
            this.amenityHashMap.put(amenity.getId(), amenity);
        } else if (this.amenityHashMap.containsKey(amenity.getId())) {
            this.amenityHashMap.remove(amenity.getId());
        }
        this.amenitiesAdapter.setItemStatusChanged(i, z);
    }

    public void clickOnItem(FilterImage filterImage, int i, boolean z) {
        getTheFilterTypePositionInNestedScrollView(i);
        this.filterImageAdapter.setItemStatusChange(getFilterIconsList(i), i);
    }

    private void getTheFilterTypePositionInNestedScrollView(int i) {
        LinearLayout linearLayout = (LinearLayout) ((LinearLayout) this.filterScrollView.getChildAt(0)).getChildAt(i);
        this.filterScrollView.scrollTo((int) this.filterScrollView.getX(), linearLayout.getBottom() - linearLayout.getHeight());
    }

    public void clickOnListItem(Houserule houserule, int i, boolean z) {
        if (z) {
            this.houseRuleHashMap.put(houserule.getId(), houserule);
        } else if (this.houseRuleHashMap.containsKey(houserule.getId())) {
            this.houseRuleHashMap.remove(houserule.getId());
        }
        this.houseRulesAdapter.setItemStatusChanged(i, z);
    }

    public void clickOnListItem(CancelPolicy cancelPolicy, int i, boolean z) {
        Log.e(TAG, "clickOnListItem() is called");
        if (z) {
            this.cancelPolicyMap.put(cancelPolicy.getId(), cancelPolicy);
        } else if (this.cancelPolicyMap.containsKey(cancelPolicy.getId())) {
            this.cancelPolicyMap.remove(cancelPolicy.getId());
        }
        this.cancellationPolicyAdapter.setItemStatusChanged(i, z);
    }

    private FilterData getFiltersData() {
        Log.e(TAG, "getFiltersData() is called");
        FilterData filterData = new FilterData();
        filterData.setBookingType(getBookingSelectedType());
        filterData.setPriceFrom(String.valueOf(this.selectedMinPrice));
        filterData.setPriceTo(String.valueOf(this.selectedMaxPrice));
        filterData.setSingleBeds(getSelectedNoOfSingleBeds());
        filterData.setDoubleBeds(getSelectedNoOfDoubleBeds());
        filterData.setRooms(getSelectedNoOfRooms());
        filterData.setAmenityMap(this.amenityHashMap);
        filterData.setHouseRuleMap(this.houseRuleHashMap);
        filterData.setCancelPolicyMap(this.cancelPolicyMap);
        StringBuilder stringBuilder = new StringBuilder(" BookingType : ");
        stringBuilder.append(filterData.getBookingType().toString());
        stringBuilder.append(" PriceFrom : ");
        stringBuilder.append(filterData.getPriceFrom().toString());
        stringBuilder.append(" PriceTo : ");
        stringBuilder.append(filterData.getPriceTo().toString());
        stringBuilder.append(" SingleBeds : ");
        stringBuilder.append(filterData.getSingleBeds().toString());
        stringBuilder.append(" DoubleBeds : ");
        stringBuilder.append(filterData.getDoubleBeds().toString());
        stringBuilder.append(" amenityHashMap : ");
        stringBuilder.append(this.amenityHashMap.toString());
        stringBuilder.append(" houseRuleHashMap : ");
        stringBuilder.append(this.houseRuleHashMap.toString());
        stringBuilder.append(" cancelPolicyMap : ");
        stringBuilder.append(this.cancelPolicyMap.toString());
        stringBuilder.append(" Rooms: ");
        stringBuilder.append(filterData.getRooms().toString());
        Log.e(" FilterValue ", stringBuilder.toString());
        if (filterData.getBookingType().equals("") && filterData.getPriceFrom().equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) && filterData.getPriceTo().equals("10000") && filterData.getSingleBeds().equals("") && filterData.getDoubleBeds().equals("") && filterData.getRooms().equals("") && this.amenityHashMap.isEmpty() && this.houseRuleHashMap.isEmpty()) {
            if (this.cancelPolicyMap.isEmpty()) {
                SearchPropertyActivity.filterApplied = false;
                Log.e("filter value : ", "changeBookingTypeAppearance :- false");
                return filterData;
            }
        }
        SearchPropertyActivity.filterApplied = true;
        Log.e("filter value : ", "changeBookingTypeAppearance :- true");
        return filterData;
    }

    private String getBookingSelectedType() {
        if ((!this.instantBookingType.isChecked() || !this.hoursBookingType.isChecked()) && (this.instantBookingType.isChecked() || this.hoursBookingType.isChecked())) {
            return this.instantBookingType.isChecked() ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } else {
            return "";
        }
    }

    private String getSelectedNoOfSingleBeds() {
        if (this.singleBedsRadioGroup.getCheckedRadioButtonId() == -1) {
            return "";
        }
        int indexOfChild = this.singleBedsRadioGroup.indexOfChild(this.singleBedsRadioGroup.findViewById(this.singleBedsRadioGroup.getCheckedRadioButtonId()));
        Log.d(TAG, String.valueOf(indexOfChild));
        return String.valueOf(indexOfChild);
    }

    private String getSelectedNoOfDoubleBeds() {
        Log.e(TAG, "getSelectedNoOfDoubleBeds() is called");
        if (this.doubleBedsRadioGroup.getCheckedRadioButtonId() == -1) {
            return "";
        }
        int indexOfChild = this.doubleBedsRadioGroup.indexOfChild(this.doubleBedsRadioGroup.findViewById(this.doubleBedsRadioGroup.getCheckedRadioButtonId()));
        Log.d(TAG, String.valueOf(indexOfChild));
        return String.valueOf(indexOfChild);
    }

    private String getSelectedNoOfRooms() {
        Log.e(TAG, "getSelectedNoOfRooms() is called");
        if (this.roomsRadioGroup.getCheckedRadioButtonId() == -1) {
            return "";
        }
        int indexOfChild = this.roomsRadioGroup.indexOfChild(this.roomsRadioGroup.findViewById(this.roomsRadioGroup.getCheckedRadioButtonId()));
        Log.d(TAG, String.valueOf(indexOfChild));
        return String.valueOf(indexOfChild);
    }

    private void resetAllFilters() {
        Log.e(TAG, "resetAllFilters() is called");
        if (this.seedResponse != null) {
            this.minPrice = this.seedResponse.getPriceRange().getMin();
            this.maxPrice = this.seedResponse.getPriceRange().getMax();
            this.selectedMinPrice = this.seedResponse.getPriceRange().getMin();
            this.selectedMaxPrice = this.seedResponse.getPriceRange().getMax();
        }
        this.singleBedsRadioGroup.setOnCheckedChangeListener(null);
        this.singleBedsRadioGroup.clearCheck();
        this.doubleBedsRadioGroup.setOnCheckedChangeListener(null);
        this.doubleBedsRadioGroup.clearCheck();
        this.roomsRadioGroup.setOnCheckedChangeListener(null);
        this.roomsRadioGroup.clearCheck();
        changeBookingTypeAppearance(this.instantBookingType, false);
        changeBookingTypeAppearance(this.hoursBookingType, false);
        this.rangeSeekbar.setMinValue((float) this.minPrice).setMaxValue((float) this.maxPrice).setMinStartValue((float) this.selectedMinPrice).setMaxStartValue((float) this.selectedMaxPrice).setGap(1.0f).apply();
        if (this.amenityHashMap != null) {
            this.amenityHashMap.clear();
        }
        if (this.houseRuleHashMap != null) {
            this.houseRuleHashMap.clear();
        }
        this.amenitiesAdapter.resetAllData();
        this.houseRulesAdapter.resetAllData();
        if (this.cancelPolicyMap != null) {
            this.cancelPolicyMap.clear();
        }
        this.cancellationPolicyAdapter.resetAllData();
    }

    private void changeBookingTypeAppearance(CheckBox checkBox, boolean z) {
        Log.e(TAG, "changeBookingTypeAppearance() is called");
        checkBox.setChecked(z);
        checkBox.setBackground(getResources().getDrawable(R.drawable.booking_type_state));
        if (z) {
            z = true;
        } else {
            z = true;
        }
        checkBox.setTextColor(getBookingTypeColor(z));
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void setFilterData(SetFilterDataEvent setFilterDataEvent) {
        Log.e(TAG, "setFilterData() is called");
        if (setFilterDataEvent != null) {
            FilterData request = setFilterDataEvent.getRequest();
            String bookingType = setFilterDataEvent.getBookingType();
            List amenityIds = setFilterDataEvent.getAmenityIds();
            if (request != null) {
                setFilterDataToViews(request);
                this.amenityHashMap = request.getAmenityMap();
                this.houseRuleHashMap = request.getHouseRuleMap();
                this.cancelPolicyMap = request.getCancelPolicyMap();
            }
            if (bookingType != null) {
                setBookingSelectedType(bookingType, false);
            }
            if (this.seedResponse != null) {
                applyAmenitySelections(this.seedResponse.getAmenities(), amenityIds);
                applyHouseRulesSelections(this.seedResponse.getHouserules());
                applyCancellationPolicySelections(this.seedResponse.getCancelPolicy());
                showMoreButtonForAmenity(this.seedResponse.getAmenities());
                showMoreButtonForHouseRules(this.seedResponse.getHouserules());
            }
            EventBus.getDefault().removeStickyEvent(setFilterDataEvent);
        }
    }

    private void setFilterDataToViews(FilterData filterData) {
        Log.e(TAG, "setFilterDataToViews() is called");
        setPriceRangeSeekbar(filterData.getPriceFrom(), filterData.getPriceTo());
        setSingleBed(filterData.getSingleBeds());
        setDoubleBed(filterData.getDoubleBeds());
        setRoom(filterData.getRooms());
    }

    private void setBookingSelectedType(String str, boolean z) {
        if (z) {
            changeBookingTypeAppearance(this.instantBookingType, true);
        } else if (str.equalsIgnoreCase("")) {
            changeBookingTypeAppearance(this.instantBookingType, false);
            changeBookingTypeAppearance(this.hoursBookingType, false);
            return;
        } else if (str.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES) != null) {
            changeBookingTypeAppearance(this.instantBookingType, true);
            return;
        }
        changeBookingTypeAppearance(this.hoursBookingType, true);
    }

    private void setPriceRangeSeekbar(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.selectedMinPrice = Integer.parseInt(str);
            this.selectedMaxPrice = Integer.parseInt(str2);
            this.rangeSeekbar.setMinValue((float) this.minPrice).setMaxValue((float) this.maxPrice).setMinStartValue((float) this.selectedMinPrice).setMaxStartValue((float) this.selectedMaxPrice).setGap(1.0f).apply();
        }
    }

    private void setSingleBed(String str) {
        if (TextUtils.isEmpty(str)) {
            this.singleBedsRadioGroup.setOnCheckedChangeListener(null);
            this.singleBedsRadioGroup.clearCheck();
            return;
        }
        this.singleBedsRadioGroup.check(((RadioButton) this.singleBedsRadioGroup.getChildAt(Integer.parseInt(str))).getId());
    }

    private void setDoubleBed(String str) {
        if (TextUtils.isEmpty(str)) {
            this.doubleBedsRadioGroup.setOnCheckedChangeListener(null);
            this.doubleBedsRadioGroup.clearCheck();
            return;
        }
        this.doubleBedsRadioGroup.check(((RadioButton) this.doubleBedsRadioGroup.getChildAt(Integer.parseInt(str))).getId());
    }

    private void setRoom(String str) {
        if (TextUtils.isEmpty(str)) {
            this.roomsRadioGroup.setOnCheckedChangeListener(null);
            this.roomsRadioGroup.clearCheck();
            return;
        }
        this.roomsRadioGroup.check(((RadioButton) this.roomsRadioGroup.getChildAt(Integer.parseInt(str))).getId());
    }

    private void applyAmenitySelections(List<Amenity> list, List<String> list2) {
        Map linkedHashMap = new LinkedHashMap();
        for (Amenity amenity : list) {
            linkedHashMap.put(amenity.getId(), amenity);
        }
        for (String str : list2) {
            if (linkedHashMap.containsKey(str)) {
                ((Amenity) linkedHashMap.get(str)).setEnabled(true);
            }
        }
        list = new ArrayList(linkedHashMap.values());
        for (Amenity amenity2 : list) {
            if (this.amenityHashMap != null && this.amenityHashMap.containsKey(amenity2.getId())) {
                amenity2.setChecked(true);
            }
        }
        this.amenitiesAdapter.setData(list);
    }

    private void applyHouseRulesSelections(List<Houserule> list) {
        for (Houserule houserule : list) {
            if (this.houseRuleHashMap != null && this.houseRuleHashMap.containsKey(houserule.getId())) {
                houserule.setChecked(true);
            }
        }
        this.houseRulesAdapter.setData(list);
    }

    private void applyCancellationPolicySelections(List<CancelPolicy> list) {
        for (CancelPolicy cancelPolicy : list) {
            if (this.cancelPolicyMap != null && this.cancelPolicyMap.containsKey(cancelPolicy.getId())) {
                cancelPolicy.setChecked(true);
            }
        }
        this.cancellationPolicyAdapter.setData(list);
    }

    private void showMoreButtonForAmenity(List<Amenity> list) {
        if (list == null || list.size() <= 4) {
            this.amenitiesMoreTV.setVisibility(8);
        } else {
            this.amenitiesMoreTV.setVisibility(0);
        }
    }

    private void showMoreButtonForHouseRules(List<Houserule> list) {
        if (list == null || list.size() <= 4) {
            this.houseRulesMoreTV.setVisibility(8);
        } else {
            this.houseRulesMoreTV.setVisibility(0);
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
