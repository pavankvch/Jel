package com.jelsat.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Space;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.businesslogic.PublishProperty.IPublishProperty;
import com.businesslogic.PublishProperty.PublishPropertyPresenter;
import com.data.amenitiesandhouserules.Amenity;
import com.data.amenitiesandhouserules.HouseRuleSafetyPolicy;
import com.data.amenitiesandhouserules.HouseSafety;
import com.data.amenitiesandhouserules.Houserule;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.propertydetail.FullInfo;
import com.data.propertydetail.PropertyImage;
import com.data.propertydetail.PropertyRoomsAndGuests;
import com.data.publishproperty.PublishPropertyRequest;
import com.data.publishproperty.PublishPropertyResponse;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.maps.model.LatLng;
import com.jelsat.R;
import com.jelsat.adapters.AmenitiesGridAdapter;
import com.jelsat.adapters.AmenitiesGridAdapter$ItemClickListener;
import com.jelsat.adapters.CustomExpandableListAdapter;
import com.jelsat.adapters.PropertyDetailImageAdapter;
import com.jelsat.adapters.PropertyDetailImageAdapter$ImageClickListener;
import com.jelsat.adapters.PropertyDetailRoomsAndGuestsAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.customclasses.ExpandableTextView;
import com.jelsat.customclasses.ItemOffsetDecoration;
import com.jelsat.customclasses.NonScrollExpandableListView;
import com.jelsat.fragments.AmenitiesDialogFragment;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.AlertDialogUtil;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PublishPropertyActivity extends BaseAppCompactActivity implements IPublishProperty, AmenitiesGridAdapter$ItemClickListener, PropertyDetailImageAdapter$ImageClickListener {
    String PropertyId;
    private AlertDialogUtil alertDialogUtil;
    private AmenitiesGridAdapter amenitiesGridAdapter;
    @BindView(2131361866)
    RecyclerView amenityGridRecyclerView;
    private List<Amenity> amenityList;
    @BindView(2131361871)
    LinearLayout amenitySection;
    SearchProperty basicInfo;
    int bathrooms;
    int beds;
    @BindView(2131361913)
    FancyButton book_now_button;
    int doubleBed;
    @BindView(2131362151)
    NonScrollExpandableListView expandableListView;
    Bundle extraBundle;
    @BindView(2131362180)
    TextView flatNo;
    FullInfo fullInfo;
    String hostId;
    private PropertyDetailImageAdapter imageAdapter;
    private List<String> imageTitle;
    private List<String> images;
    int kitchens;
    @BindView(2131362270)
    TextView landmark;
    int maxGuests;
    @BindView(2131362368)
    TextView noOfPropertyImagesCount;
    @BindView(2131362427)
    TextView priceTV;
    @BindView(2131362449)
    AppCompatTextView propertyAddressTV;
    @BindView(2131362450)
    TextView propertyArea;
    @BindView(2131362452)
    CustomTextView propertyBookingType;
    @BindView(2131362458)
    TextView propertyConstraint;
    @BindView(2131362459)
    ExpandableTextView propertyDescription;
    @BindView(2131362461)
    RecyclerView propertyDetailRoomsGuestsRecyclerView;
    @BindView(2131362462)
    NestedScrollView propertyDetailScrollView;
    @BindView(2131362465)
    ImageView propertyHostImage;
    @BindView(2131362466)
    TextView propertyHostName;
    @BindView(2131362469)
    RecyclerView propertyImageRecyclerView;
    @BindView(2131362477)
    AppCompatTextView propertyNameTV;
    @BindView(2131362482)
    TextView propertyType;
    private PublishPropertyPresenter publishPropertyPresenter = new PublishPropertyPresenter(this, RetrofitClient.getAPIService());
    PublishPropertyRequest publishPropertyRequest;
    PublishPropertyResponse publishPropertyResponse1;
    int rooms;
    private PropertyDetailRoomsAndGuestsAdapter roomsAndGuestsAdapter;
    SeedResponse seedResponse;
    int singleBed;
    int tents;

    public int getActivityLayout() {
        return R.layout.publish_property_activity;
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        this.alertDialogUtil = new AlertDialogUtil(this);
        bundle = getSupportActionBar();
        bundle.setDisplayHomeAsUpEnabled(true);
        bundle.setDisplayShowTitleEnabled(false);
        this.extraBundle = getIntent().getExtras();
        this.amenitiesGridAdapter = new AmenitiesGridAdapter(this, null, this);
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            this.seedResponse = PrefUtils.getInstance().getSeedResponse();
        }
        bundle = this.seedResponse.getLimits();
        this.rooms = bundle.getMaxRooms();
        this.beds = bundle.getMaxBeds();
        this.bathrooms = bundle.getMaxBathrooms();
        this.kitchens = bundle.getMaxKitchens();
        this.tents = bundle.getMaxTents();
        this.singleBed = bundle.getMaxSinglebeds();
        this.doubleBed = bundle.getMaxDoublebeds();
        this.maxGuests = bundle.getMaxGuests();
        if (this.extraBundle != null) {
            this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
            this.basicInfo = (SearchProperty) this.extraBundle.getParcelable("basicInfo");
            this.PropertyId = this.extraBundle.getString("PropertyId");
            this.hostId = this.fullInfo.getHostDetails().getHostedId();
            dataPloting();
            initPropertyImagesRecyclerView();
            initPropertyData(this.basicInfo);
            initPropertyDetailRoomsAndGuestsRecyclerView();
            initAmenitiesGridRecyclerView();
            return;
        }
        Log.e("noData", "Nodata");
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void initAmenitiesGridRecyclerView() {
        LayoutManager anonymousClass1 = new GridLayoutManager(this, 4) {
            public boolean canScrollHorizontally() {
                return false;
            }

            public boolean canScrollVertically() {
                return false;
            }

            public boolean isSmoothScrollbarEnabled() {
                return false;
            }
        };
        this.amenityGridRecyclerView.setHasFixedSize(true);
        this.amenityGridRecyclerView.setLayoutManager(anonymousClass1);
        this.amenityGridRecyclerView.setNestedScrollingEnabled(false);
        this.amenityGridRecyclerView.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.item_offset));
        this.amenityGridRecyclerView.setAdapter(this.amenitiesGridAdapter);
    }

    private void initPropertyDetailRoomsAndGuestsRecyclerView() {
        this.propertyDetailRoomsGuestsRecyclerView.setLayoutManager(new LinearLayoutManager(this, 0, false) {
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.propertyDetailRoomsGuestsRecyclerView.setNestedScrollingEnabled(false);
        this.roomsAndGuestsAdapter = new PropertyDetailRoomsAndGuestsAdapter(this, null);
        this.propertyDetailRoomsGuestsRecyclerView.setAdapter(this.roomsAndGuestsAdapter);
        this.roomsAndGuestsAdapter.setData(setPropertyComponentsData(this.fullInfo));
    }

    private void initPropertyData(SearchProperty searchProperty) {
        if (searchProperty.getImagesList() != null) {
            this.images = new ArrayList();
            this.imageTitle = new ArrayList();
            if (searchProperty.getImagesList().size() > 0) {
                for (int i = 0; i < searchProperty.getImagesList().size(); i++) {
                    this.images.add(((PropertyImage) searchProperty.getImagesList().get(i)).getPropertyImage());
                    this.imageTitle.add(((PropertyImage) searchProperty.getImagesList().get(i)).getTitle());
                }
            }
            this.imageAdapter.setData(this.images, this.imageTitle);
        } else {
            List arrayList = new ArrayList();
            arrayList.add(searchProperty.getImage());
            this.imageAdapter.setData(arrayList, this.imageTitle);
        }
        this.noOfPropertyImagesCount.setText(String.format("%s/%s", new Object[]{AppEventsConstants.EVENT_PARAM_VALUE_YES, Integer.valueOf(this.images.size())}));
    }

    private void initPropertyImagesRecyclerView() {
        final LayoutManager anonymousClass3 = new LinearLayoutManager(this, 0, false) {
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.propertyImageRecyclerView.setLayoutManager(anonymousClass3);
        this.propertyImageRecyclerView.setNestedScrollingEnabled(false);
        this.imageAdapter = new PropertyDetailImageAdapter(this, null, null, this);
        this.propertyImageRecyclerView.setAdapter(this.imageAdapter);
        new PagerSnapHelper().attachToRecyclerView(this.propertyImageRecyclerView);
        this.propertyImageRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i != 1 && i == 0) {
                    Log.e("123", String.valueOf(anonymousClass3.findFirstVisibleItemPosition()));
                    if (PublishPropertyActivity.this.basicInfo != null && PublishPropertyActivity.this.noOfPropertyImagesCount != null) {
                        PublishPropertyActivity.this.noOfPropertyImagesCount.setText(String.format("%s/%s", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(PublishPropertyActivity.this.images.size())}));
                    }
                }
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
    }

    @OnClick({2131362707})
    public void bookingPolicies() {
        String str = Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/content/booking-policy" : "https://jelsat.com/en/content/booking-policy";
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.TERMS_URL, str);
        goToActivity(WebViewActivity.class, bundle);
    }

    @OnClick({2131362385})
    public void clickOnMapButton() {
        LatLng latLng = new LatLng(Double.parseDouble(this.basicInfo.getLat()), Double.parseDouble(this.basicInfo.getLng()));
        String str = this.basicInfo.getPropertyAddress().toString();
        Intent intent = new Intent(this, ShowLocationActivity.class);
        intent.putExtra("latitude", latLng.latitude);
        intent.putExtra("longitude", latLng.longitude);
        intent.putExtra("Address", str);
        startActivity(intent);
    }

    @OnClick({2131362465})
    public void clickOnHostProfileImage() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.UID, this.hostId);
        goToActivity(PublicProfileActivity.class, bundle);
    }

    private void dataPloting() {
        if (!TextUtils.isEmpty(this.basicInfo.getLandMark())) {
            this.landmark.setText(String.format("%s : %s", new Object[]{getString(R.string.property_location_landmark), this.basicInfo.getLandMark()}));
        }
        if (!TextUtils.isEmpty(this.basicInfo.getFlatNo())) {
            this.flatNo.setText(this.basicInfo.getFlatNo());
        }
        if (this.basicInfo.getCurrencyCode() != null && this.basicInfo.getCurrencyCode().equalsIgnoreCase(PayFortPayment.CURRENCY_TYPE)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big> %s/ %s", new Object[]{Float.valueOf(this.basicInfo.getPrice()), getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
            } else {
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "%s <big>%.2f</big>/ %s", new Object[]{getString(R.string.saudi_currency), Float.valueOf(this.basicInfo.getPrice()), getString(R.string.per_night_label)})));
            }
        }
        this.propertyNameTV.setText(this.basicInfo.getName());
        initHostLoadImage(this.fullInfo.getHostDetails().getHostedImage());
        if (this.basicInfo.getImagesList() != null && ((PropertyImage) this.basicInfo.getImagesList().get(0)).getPropertyImage().isEmpty()) {
            Log.e("Image String", "Empty Image String");
        }
        this.propertyAddressTV.setText(Utils.getPropertyAddressWithoutNumbers(this.basicInfo.getPropertyAddress()));
        TextView textView = this.propertyHostName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.publish_hosted_by));
        stringBuilder.append(" ");
        stringBuilder.append(this.fullInfo.getHostDetails().getHostedName());
        textView.setText(stringBuilder.toString());
        textView = this.propertyType;
        stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.publish_property_type));
        stringBuilder.append(" <font color='");
        stringBuilder.append(getResources().getColor(R.color.normal_text_color));
        stringBuilder.append("'>");
        stringBuilder.append(this.fullInfo.getPropertyType());
        stringBuilder.append("</font>");
        textView.setText(Html.fromHtml(stringBuilder.toString()));
        textView = this.propertyArea;
        stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.publish_property_area));
        stringBuilder.append(" ");
        stringBuilder.append(this.basicInfo.getAreaInMeters());
        stringBuilder.append(" ");
        stringBuilder.append(getString(R.string.sqm_label));
        textView.setText(stringBuilder.toString());
        textView = this.propertyConstraint;
        stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.publish_minimum_nights));
        stringBuilder.append(" ");
        stringBuilder.append(this.fullInfo.getMinDays());
        textView.setText(stringBuilder.toString());
        if (VERSION.SDK_INT >= 24) {
            this.propertyDescription.setText(Html.fromHtml(this.fullInfo.getAboutProperty(), 63));
        } else {
            this.propertyDescription.setText(Html.fromHtml(this.fullInfo.getAboutProperty()));
        }
        if (this.basicInfo.getType().equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
            } else {
                this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
            }
            this.propertyBookingType.setText(getString(R.string.publish_24_hours));
        } else if (this.basicInfo.getType().equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
            } else {
                this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
            }
            this.propertyBookingType.setText(getString(R.string.publish_instant_booking));
        }
        if (this.fullInfo.getAmenities() == null || this.fullInfo.getAmenities().size() <= 0) {
            this.amenitySection.setVisibility(8);
        } else {
            this.amenityList = this.fullInfo.getAmenities();
            this.amenitySection.setVisibility(0);
            this.amenitiesGridAdapter.setData(this.fullInfo.getAmenities());
        }
        List<String> arrayList = new ArrayList();
        for (Houserule id : this.fullInfo.getHouseRules()) {
            arrayList.add(id.getId());
        }
        if (!TextUtils.isEmpty(this.fullInfo.getCustomHouseRule())) {
            arrayList.add("111");
        }
        Map linkedHashMap = new LinkedHashMap();
        for (Houserule houserule : PrefUtils.getInstance().getSeedResponse().getHouserules()) {
            HouseRuleSafetyPolicy houseRuleSafetyPolicy = new HouseRuleSafetyPolicy();
            houseRuleSafetyPolicy.setId(houserule.getId());
            houseRuleSafetyPolicy.setName(houserule.getName());
            houseRuleSafetyPolicy.setNotPolicy(true);
            linkedHashMap.put(houserule.getId(), houseRuleSafetyPolicy);
        }
        if (!TextUtils.isEmpty(this.fullInfo.getCustomHouseRule())) {
            HouseRuleSafetyPolicy houseRuleSafetyPolicy2 = new HouseRuleSafetyPolicy();
            houseRuleSafetyPolicy2.setId("111");
            houseRuleSafetyPolicy2.setName(this.fullInfo.getCustomHouseRule());
            houseRuleSafetyPolicy2.setNotPolicy(true);
            linkedHashMap.put("111", houseRuleSafetyPolicy2);
        }
        for (String str : arrayList) {
            if (linkedHashMap.containsKey(str)) {
                ((HouseRuleSafetyPolicy) linkedHashMap.get(str)).setChecked(true);
            }
        }
        Log.d("HouseRuleMap", linkedHashMap.values().toString());
        arrayList = new ArrayList();
        for (HouseSafety id2 : this.fullInfo.getHouseSafety()) {
            arrayList.add(id2.getId());
        }
        Map linkedHashMap2 = new LinkedHashMap();
        for (HouseSafety houseSafety : PrefUtils.getInstance().getSeedResponse().getHouseSafety()) {
            HouseRuleSafetyPolicy houseRuleSafetyPolicy3 = new HouseRuleSafetyPolicy();
            houseRuleSafetyPolicy3.setId(houseSafety.getId());
            houseRuleSafetyPolicy3.setName(houseSafety.getName());
            houseRuleSafetyPolicy3.setNotPolicy(true);
            linkedHashMap2.put(houseSafety.getId(), houseRuleSafetyPolicy3);
        }
        for (String str2 : arrayList) {
            if (linkedHashMap2.containsKey(str2)) {
                ((HouseRuleSafetyPolicy) linkedHashMap2.get(str2)).setChecked(true);
            }
        }
        List arrayList2 = new ArrayList();
        HouseRuleSafetyPolicy houseRuleSafetyPolicy4 = new HouseRuleSafetyPolicy();
        houseRuleSafetyPolicy4.setId(this.fullInfo.getCancellationPolicyId());
        houseRuleSafetyPolicy4.setName(this.fullInfo.getCancelDescription());
        houseRuleSafetyPolicy4.setNotPolicy(false);
        arrayList2.add(houseRuleSafetyPolicy4);
        Map linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(getString(R.string.house_rules_label), new ArrayList(linkedHashMap.values()));
        linkedHashMap3.put(getString(R.string.house_safety_label), new ArrayList(linkedHashMap2.values()));
        stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.cancellation_policy_label));
        stringBuilder.append("-  %10s");
        linkedHashMap3.put(String.format(stringBuilder.toString(), new Object[]{this.fullInfo.getCancellationPolicy()}), arrayList2);
        this.expandableListView.setAdapter(new CustomExpandableListAdapter(this, new ArrayList(linkedHashMap3.keySet()), linkedHashMap3, false));
    }

    private List<PropertyRoomsAndGuests> setPropertyComponentsData(FullInfo fullInfo) {
        PropertyRoomsAndGuests propertyRoomsAndGuests;
        StringBuilder stringBuilder;
        List<PropertyRoomsAndGuests> arrayList = new ArrayList();
        if (!TextUtils.isEmpty(fullInfo.getMaxGuests())) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_guest);
            if (this.maxGuests == Integer.parseInt(fullInfo.getMaxGuests())) {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.guests));
                String stringBuilder2 = stringBuilder.toString();
                Object[] objArr = new Object[1];
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(fullInfo.getMaxGuests());
                stringBuilder3.append("+");
                objArr[0] = stringBuilder3.toString();
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder2, objArr));
            } else {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.guests));
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getMaxGuests()}));
            }
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfRooms()) || fullInfo.getNoOfRooms().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_living_room);
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.rooms_label));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getNoOfRooms()}));
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfTents()) || fullInfo.getNoOfTents().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_tent);
            if (this.tents == Integer.parseInt(fullInfo.getNoOfTents())) {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.tents_label));
                stringBuilder2 = stringBuilder.toString();
                objArr = new Object[1];
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(fullInfo.getNoOfTents());
                stringBuilder3.append("+");
                objArr[0] = stringBuilder3.toString();
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder2, objArr));
            } else {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.tents_label));
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getNoOfTents()}));
            }
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfKitchens()) || fullInfo.getNoOfKitchens().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_kitchen);
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.kitchens_label));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getNoOfKitchens()}));
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfSinglebeds()) || fullInfo.getNoOfSinglebeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_single_bed);
            if (this.singleBed == Integer.parseInt(fullInfo.getNoOfSinglebeds())) {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.single_bed_label));
                stringBuilder2 = stringBuilder.toString();
                objArr = new Object[1];
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(fullInfo.getNoOfSinglebeds());
                stringBuilder3.append("+");
                objArr[0] = stringBuilder3.toString();
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder2, objArr));
            } else {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.single_bed_label));
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getNoOfSinglebeds()}));
            }
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfDoublebeds()) || fullInfo.getNoOfDoublebeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_double_bed);
            if (this.doubleBed == Integer.parseInt(fullInfo.getNoOfDoublebeds())) {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.double_bed_label));
                stringBuilder2 = stringBuilder.toString();
                objArr = new Object[1];
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(fullInfo.getNoOfDoublebeds());
                stringBuilder3.append("+");
                objArr[0] = stringBuilder3.toString();
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder2, objArr));
            } else {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.double_bed_label));
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getNoOfDoublebeds()}));
            }
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfBeds()) || fullInfo.getNoOfBeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_bed_room);
            if (this.beds == Integer.parseInt(fullInfo.getNoOfBeds())) {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.bed_room_label));
                stringBuilder2 = stringBuilder.toString();
                objArr = new Object[1];
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(fullInfo.getNoOfBeds());
                stringBuilder3.append("+");
                objArr[0] = stringBuilder3.toString();
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder2, objArr));
            } else {
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.bed_room_label));
                propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getNoOfBeds()}));
            }
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfBathrooms()) || fullInfo.getNoOfBathrooms().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_bath_rooms);
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.bathrooms));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{fullInfo.getNoOfBathrooms()}));
            arrayList.add(propertyRoomsAndGuests);
        }
        return arrayList;
    }

    private void initHostLoadImage(String str) {
        GlideApp.with((FragmentActivity) this).asBitmap().load(str).placeholder((int) R.drawable.ic_profile_image2).apply(new RequestOptions().transform(new RoundedCorners(12))).error((int) R.drawable.default_logo).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(this.propertyHostImage);
    }

    private void initAmenitiesData(List<Amenity> list) {
        int i;
        List arrayList = new ArrayList();
        for (Amenity amenity : list) {
            StringBuilder stringBuilder = new StringBuilder("ic_");
            stringBuilder.append(amenity.getId());
            arrayList.add(stringBuilder.toString());
        }
        if (list.size() > 6) {
            i = 6;
        } else {
            i = list.size();
        }
        for (int i2 = 0; i2 < i; i2++) {
            Space space = new Space(this);
            ImageView imageView = new ImageView(this);
            int resourseId = getResourseId(this, (String) arrayList.get(i2));
            GlideApp.with((FragmentActivity) this).load(list.get(i2)).placeholder(resourseId).error(resourseId).into(imageView);
            if (i == 6) {
                space.setLayoutParams(new LayoutParams(-1, -1, 1.0f));
            } else {
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.setMargins(getPixelsFromDPs(12), 0, getPixelsFromDPs(12), 0);
                space.setLayoutParams(layoutParams);
            }
            imageView.getLayoutParams().height = getPixelsFromDPs(24);
            imageView.getLayoutParams().width = getPixelsFromDPs(24);
            imageView.requestLayout();
        }
    }

    protected int getPixelsFromDPs(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    public int getResourseId(Context context, String str) throws RuntimeException {
        try {
            return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        } catch (Context context2) {
            throw new RuntimeException("Error getting Resource ID.", context2);
        }
    }

    @OnClick({2131361913})
    public void publishButton() {
        this.publishPropertyRequest = new PublishPropertyRequest();
        this.publishPropertyRequest.setPropertyId(this.PropertyId);
        this.publishPropertyRequest.setStatus(1);
        this.publishPropertyPresenter.getPublishPropertyResult(getString(R.string.please_wait), this.publishPropertyRequest);
    }

    public void onSuccessResponse(PublishPropertyResponse publishPropertyResponse) {
        this.publishPropertyResponse1 = publishPropertyResponse;
        Log.e("PublishProperty", "Success");
        this.alertDialogUtil.showAlertDialog(getString(R.string.success), publishPropertyResponse.getStatus(), getString(R.string.ok), null, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PublishPropertyActivity.this.callListingScreen();
                PublishPropertyActivity.this.alertDialogUtil.dismissDialog();
            }
        }, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    private void callListingScreen() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.addFlags(67108864);
        intent.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
        startActivity(intent);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void clickOnImage(List<String> list, List<String> list2, int i) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(StringConstants.PROPERTY_IMAGE, (ArrayList) list);
        bundle.putStringArrayList(StringConstants.PREOPERTY_IMAGE_TITLE, (ArrayList) list2);
        bundle.putInt(StringConstants.PROPERTY_IMAGE_POSITION, i);
        goToActivity(PropertyImageViewActivity.class, bundle);
    }

    public void clickOnItem(List<Amenity> list) {
        if (list != null && list.size() > 0) {
            AmenitiesDialogFragment.newInstance(list).show(getSupportFragmentManager(), "amenityDialog");
        }
    }
}
