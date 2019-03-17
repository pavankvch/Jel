package com.jelsat.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.businesslogic.cancelbooking.CancelBookingPresenter;
import com.businesslogic.cancelbooking.ICancelBookingView;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.propertydetail.IPropertyDetailView;
import com.businesslogic.propertydetail.PropertyDetailPresenter;
import com.businesslogic.propertyfavourite.IPropertyFavouriteView;
import com.businesslogic.propertyfavourite.PropertyFavouritePresenter;
import com.businesslogic.viewbill.IPropertyViewBillView;
import com.businesslogic.viewbill.PropertyViewBillPresenter;
import com.data.amenitiesandhouserules.Amenity;
import com.data.amenitiesandhouserules.HouseRuleSafetyPolicy;
import com.data.amenitiesandhouserules.HouseSafety;
import com.data.amenitiesandhouserules.Houserule;
import com.data.amenitiesandhouserules.Limits;
import com.data.amenitiesandhouserules.SeedResponse;
import com.data.bookings.BookingCancelResponse;
import com.data.propertydetail.Feed;
import com.data.propertydetail.FullInfo;
import com.data.propertydetail.PropertyDetailRequest;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.propertydetail.PropertyImage;
import com.data.propertydetail.PropertyRoomsAndGuests;
import com.data.propertydetail.Review;
import com.data.propertyfavourite.PropertyFavouriteRequest;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIConstants;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.data.viewbill.PropertyViewBillRequest;
import com.data.viewbill.PropertyViewBillResponse;
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
import com.jelsat.events.ChangeFavouriteIconEvent;
import com.jelsat.events.DateGuestPropertyDialogEvent;
import com.jelsat.events.LatLngEvent;
import com.jelsat.events.PropertyDetailEvent;
import com.jelsat.events.PropertyViewBillEvent;
import com.jelsat.fragments.AmenitiesDialogFragment;
import com.jelsat.fragments.DateGuestPropertySelectionDialog;
import com.jelsat.fragments.FeedbackDialogFragment;
import com.jelsat.fragments.SendMessageDialogFragment;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PropertyDetailActivity extends BaseAppCompactActivity implements ICancelBookingView, IPropertyDetailView, IPropertyFavouriteView, IPropertyViewBillView, AmenitiesGridAdapter$ItemClickListener, PropertyDetailImageAdapter$ImageClickListener {
    private String BookingId;
    private AlertDialog alertDialog;
    private AmenitiesGridAdapter amenitiesGridAdapter;
    @BindView(2131361866)
    RecyclerView amenityGridRecyclerView;
    @BindView(2131361871)
    LinearLayout amenitySection;
    Uri appLinkData;
    @BindView(2131361877)
    LinearLayout appRatingLayout;
    @BindView(2131361913)
    FancyButton bookNowButton;
    @BindView(2131361917)
    CardView bookingLayout;
    @BindView(2131362707)
    TextView bookingPolicies;
    private CancelBookingPresenter cancelBookingPresenter = new CancelBookingPresenter(this, RetrofitClient.getAPIService());
    private Date checkInDate;
    private String checkUserId;
    private Date checkoutDate;
    OnClickListener closeClickListener = new PropertyDetailActivity$7(this);
    private String cost;
    @BindView(2131362073)
    LinearLayout dateLayout;
    @BindView(2131362072)
    LinearLayout datesCompleteLayout;
    @BindView(2131362151)
    NonScrollExpandableListView expandableListView;
    private boolean fromBookings;
    private String guestCount = AppEventsConstants.EVENT_PARAM_VALUE_YES;
    private String hostId;
    @BindView(2131362734)
    TextView hostName;
    private PropertyDetailImageAdapter imageAdapter;
    private List<String> imageList;
    private List<String> imageTitle;
    private String locationName;
    private String maxGuestCount;
    private int minimumNights = 1;
    @BindView(2131362341)
    TextView moreReviewsTV;
    @BindView(2131362368)
    TextView noOfPropertyImagesCount;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    OnClickListener okOnclickListener = new PropertyDetailActivity$8(this);
    @BindView(2131362748)
    TextView policyTextView;
    @BindView(2131362427)
    TextView priceTV;
    @BindView(2131362449)
    TextView propertyAddressTV;
    @BindView(2131362450)
    TextView propertyAreaTV;
    @BindView(2131362452)
    CustomTextView propertyBookingType;
    @BindView(2131362455)
    TextView propertyCheckInDateTV;
    @BindView(2131362456)
    TextView propertyCheckOutDateTV;
    @BindView(2131362458)
    TextView propertyConstraint;
    @BindView(2131362459)
    ExpandableTextView propertyDescription;
    private PropertyDetailPresenter propertyDetailPresenter = new PropertyDetailPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362461)
    RecyclerView propertyDetailRoomsGuestsRecyclerView;
    @BindView(2131362462)
    NestedScrollView propertyDetailScrollView;
    @BindView(2131362463)
    FancyButton propertyDurationTV;
    private PropertyFavouritePresenter propertyFavouritePresenter = new PropertyFavouritePresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362465)
    ImageView propertyHostImage;
    @BindView(2131362466)
    TextView propertyHostName;
    @BindView(2131362469)
    RecyclerView propertyImageRecyclerView;
    private String propertyName = "";
    @BindView(2131362477)
    TextView propertyNameTV;
    private String propertyPrice = "";
    @BindView(2131362478)
    AppCompatRatingBar propertyRatingBar;
    @BindView(2131362480)
    TextView propertyReviews;
    @BindView(2131362482)
    TextView propertyType;
    @BindView(2131362767)
    CustomTextView reportPropertyTV;
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362520)
    RatingBar reviewRating;
    @BindView(2131362768)
    ExpandableTextView reviewText;
    @BindView(2131362521)
    ImageView reviewerImage;
    @BindView(2131362522)
    LinearLayout reviewsLayout;
    @BindView(2131362769)
    TextView reviwedDate;
    private PropertyDetailRoomsAndGuestsAdapter roomsAndGuestsAdapter;
    private SearchProperty searchProperty;
    @BindView(2131362578)
    TextView selectedGuestCountTV;
    @BindView(2131362581)
    CustomTextView sendMessage;
    private String shareUrl;
    private boolean showMessage;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362677)
    Toolbar toolbar;
    @BindView(2131362680)
    TextView toolbar_property_name;
    @BindView(2131362681)
    TextView toolbar_property_price;
    @BindView(2131362770)
    TextView tv_reviewscount;
    private PropertyViewBillPresenter viewBillPresenter = new PropertyViewBillPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362845)
    TextView youCantBookYourProperty;

    public int getActivityLayout() {
        return R.layout.activity_property_detail;
    }

    public void onSuccess(BookingCancelResponse bookingCancelResponse) {
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        if (this.searchProperty != null) {
            doPropertyDetailApiCall(this.searchProperty.getPropertyId(), false);
        }
    }

    @OnClick({2131362767})
    public void submitReport() {
        if (this.searchProperty != null) {
            new FeedbackDialogFragment().newInstance(this.searchProperty.getPropertyId(), false, this.BookingId, 0, null).show(getSupportFragmentManager(), APIConstants.SUBMIT_FEEDBACK);
        }
    }

    @OnClick({2131362707})
    public void bookingPolicies() {
        String str = Locale.getDefault().getLanguage().equalsIgnoreCase("ar") ? "https://jelsat.com/ar/content/booking-policy" : "https://jelsat.com/en/content/booking-policy";
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.TERMS_URL, str);
        goToActivity(WebViewActivity.class, bundle);
    }

    @OnClick({2131362713})
    public void clickOnCostCalendar() {
        if (this.searchProperty != null) {
            Bundle bundle = new Bundle();
            bundle.putString(StringConstants.PROPERTY_ID, this.searchProperty.getPropertyId());
            bundle.putInt(StringConstants.MIN_NIGHTS, this.minimumNights);
            goToActivity(TestActivity.class, bundle);
        }
    }

    @OnClick({2131362465})
    public void clickOnHostProfileImage() {
        if (!TextUtils.isEmpty(this.hostId)) {
            Bundle bundle = new Bundle();
            bundle.putString(StringConstants.UID, this.hostId);
            goToActivity(PublicProfileActivity.class, bundle);
        }
    }

    @OnClick({2131362385})
    public void clickOnMapButton() {
        if (this.searchProperty != null) {
            LatLngEvent latLngEvent = new LatLngEvent(new LatLng(Double.parseDouble(this.searchProperty.getLat()), Double.parseDouble(this.searchProperty.getLng())), this.searchProperty);
            latLngEvent.setFromBookings(this.fromBookings);
            EventBus.getDefault().postSticky(latLngEvent);
            startActivity(new Intent(this, PropertyMapActivity.class));
        }
    }

    @OnClick({2131362581})
    public void clickOnSendMessage() {
        if (this.searchProperty != null) {
            StringBuilder stringBuilder = new StringBuilder("ClickOnMessage() BookingId value : ");
            stringBuilder.append(this.BookingId);
            Log.e("PropertyDetailsActivity", stringBuilder.toString());
            SendMessageDialogFragment.newInstance(this.searchProperty.getPropertyId(), this.BookingId).show(getSupportFragmentManager(), "sendMessage");
        }
    }

    @OnClick({2131361913})
    public void clickOnBookNowButton() {
        if (validateDates()) {
            if (this.searchProperty != null) {
                if (this.searchProperty.getType().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                    doCancelApiCall(true);
                    return;
                } else if (this.searchProperty.getType().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                    show24HoursRequestDialog();
                }
            }
            return;
        }
        showDateGuestPropertyDialog(0);
    }

    private void doPropertyViewBillApiCall() {
        if (!isUserLoggedIn()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("from_property_detail", true);
            goToActivity(HomeActivity.class, bundle);
        } else if (this.searchProperty != null) {
            PropertyViewBillRequest propertyViewBillRequest = new PropertyViewBillRequest();
            propertyViewBillRequest.setPropertyId(this.searchProperty.getPropertyId());
            propertyViewBillRequest.setStartDate(convertDateToString("yyyy-MM-dd", this.checkInDate));
            propertyViewBillRequest.setEndDate(convertDateToString("yyyy-MM-dd", this.checkoutDate));
            propertyViewBillRequest.setGuests(this.guestCount);
            this.viewBillPresenter.getPropertyBill(getString(R.string.please_wait), propertyViewBillRequest);
        }
    }

    @OnClick({2131362480})
    public void clickOnRatingReviewsLayout() {
        LinearLayout linearLayout = (LinearLayout) this.propertyDetailScrollView.getChildAt(0);
        linearLayout = (LinearLayout) linearLayout.getChildAt(linearLayout.indexOfChild(this.reviewsLayout));
        this.propertyDetailScrollView.scrollTo((int) this.propertyDetailScrollView.getX(), linearLayout.getBottom() - linearLayout.getHeight());
    }

    @OnClick({2131362341})
    public void clickOnMoreReviews() {
        if (this.searchProperty != null) {
            Bundle bundle = new Bundle();
            bundle.putString(StringConstants.PROPERTY_ID, this.searchProperty.getPropertyId());
            goToActivity(ReviewsActivity.class, bundle);
        }
    }

    @OnClick({2131362073})
    public void clickOnDateLayout() {
        showDateGuestPropertyDialog(0);
    }

    @OnClick({2131362578})
    public void clickOnNoOfGuest() {
        showDateGuestPropertyDialog(1);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        findViewById(R.id.collasp_toolbar);
        setSupportActionBar(this.toolbar);
        this.youCantBookYourProperty.setVisibility(8);
        if (PrefUtils.getInstance().getSeedResponse() != null) {
            SeedResponse seedResponse = PrefUtils.getInstance().getSeedResponse();
            if (!TextUtils.isEmpty(seedResponse.getUser().getUid())) {
                this.checkUserId = seedResponse.getUser().getUid();
            }
        }
        this.imageList = new ArrayList();
        this.imageTitle = new ArrayList();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        StringBuilder stringBuilder = new StringBuilder(" propertyDetailScrollView.getHeight() = ");
        stringBuilder.append(this.propertyDetailScrollView.getHeight());
        Log.e("ScrollViewHeight", stringBuilder.toString());
        appBarLayout.addOnOffsetChangedListener(new PropertyDetailActivity$1(this, appBarLayout));
        initSwipeToRefresh();
        handeIntent();
        initPropertyImagesRecyclerView();
        initPropertyDetailRoomsAndGuestsRecyclerView();
        initAmenitiesGridRecyclerView();
        if (isUserLoggedIn() == null) {
            this.sendMessage.setVisibility(8);
            this.reportPropertyTV.setVisibility(8);
        }
    }

    private void handeIntent() {
        Intent intent = getIntent();
        intent.getAction();
        this.appLinkData = intent.getData();
        if (this.appLinkData != null) {
            doPropertyDetailApiCall(this.appLinkData.getLastPathSegment(), false);
            TextView textView = this.selectedGuestCountTV;
            StringBuilder stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.guests));
            textView.setText(String.format(stringBuilder.toString(), new Object[]{this.guestCount}));
            if (this.checkUserId.equalsIgnoreCase(this.hostId)) {
                this.sendMessage.setVisibility(8);
                this.reportPropertyTV.setVisibility(8);
                this.datesCompleteLayout.setVisibility(8);
                this.bookingLayout.setVisibility(8);
                if (this.fromBookings) {
                    this.youCantBookYourProperty.setVisibility(8);
                } else {
                    this.youCantBookYourProperty.setVisibility(0);
                }
            }
            if (this.checkInDate == null && this.checkoutDate == null) {
                this.bookNowButton.setText(getString(R.string.property_detail_check_availability_label));
            } else if (this.searchProperty.getType().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                this.bookNowButton.setText(getString(R.string.booknow));
            } else {
                this.bookNowButton.setText(getString(R.string.property_detail_request_book_label));
            }
        }
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new PropertyDetailActivity$2(this));
        this.swipeContainer.setColorSchemeResources(17170459, 17170452, 17170456, 17170454);
    }

    private void initPropertyImagesRecyclerView() {
        LayoutManager propertyDetailActivity$3 = new PropertyDetailActivity$3(this, this, 0, false);
        this.propertyImageRecyclerView.setLayoutManager(propertyDetailActivity$3);
        this.propertyImageRecyclerView.setNestedScrollingEnabled(false);
        this.imageAdapter = new PropertyDetailImageAdapter(this, null, null, this);
        this.propertyImageRecyclerView.setAdapter(this.imageAdapter);
        new PagerSnapHelper().attachToRecyclerView(this.propertyImageRecyclerView);
        this.propertyImageRecyclerView.addOnScrollListener(new PropertyDetailActivity$4(this, propertyDetailActivity$3));
    }

    private void initPropertyDetailRoomsAndGuestsRecyclerView() {
        this.propertyDetailRoomsGuestsRecyclerView.setLayoutManager(new PropertyDetailActivity$5(this, this, 0, false));
        this.propertyDetailRoomsGuestsRecyclerView.setNestedScrollingEnabled(false);
        this.roomsAndGuestsAdapter = new PropertyDetailRoomsAndGuestsAdapter(this, null);
        this.propertyDetailRoomsGuestsRecyclerView.setAdapter(this.roomsAndGuestsAdapter);
    }

    private void initAmenitiesGridRecyclerView() {
        LayoutManager propertyDetailActivity$6 = new PropertyDetailActivity$6(this, this, 4);
        this.amenityGridRecyclerView.setHasFixedSize(true);
        this.amenityGridRecyclerView.setLayoutManager(propertyDetailActivity$6);
        this.amenityGridRecyclerView.setNestedScrollingEnabled(false);
        this.amenityGridRecyclerView.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.item_offset));
        this.amenitiesGridAdapter = new AmenitiesGridAdapter(this, null, this);
        this.amenityGridRecyclerView.setAdapter(this.amenitiesGridAdapter);
    }

    public void clickOnImage(List<String> list, List<String> list2, int i) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(StringConstants.PROPERTY_IMAGE, (ArrayList) list);
        bundle.putStringArrayList(StringConstants.PREOPERTY_IMAGE_TITLE, (ArrayList) list2);
        bundle.putInt(StringConstants.PROPERTY_IMAGE_POSITION, i);
        goToActivity(PropertyImageViewActivity.class, bundle);
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getProperty(PropertyDetailEvent propertyDetailEvent) {
        if (propertyDetailEvent != null) {
            this.searchProperty = propertyDetailEvent.getProperty();
            this.checkInDate = propertyDetailEvent.getCheckInDate();
            this.BookingId = propertyDetailEvent.getBookingId();
            this.checkoutDate = propertyDetailEvent.getCheckoutDate();
            this.fromBookings = propertyDetailEvent.isFromBookings();
            this.showMessage = propertyDetailEvent.isShowMessage();
            if (propertyDetailEvent.getGuestCount() != null) {
                this.guestCount = propertyDetailEvent.getGuestCount();
            }
            this.locationName = propertyDetailEvent.getLocationName();
            if (this.checkInDate != null) {
                this.propertyCheckInDateTV.setText(convertDateToString(this.checkInDate));
            } else {
                this.propertyCheckInDateTV.setText(getString(R.string.select_date));
            }
            if (this.checkoutDate != null) {
                this.propertyCheckOutDateTV.setText(convertDateToString(this.checkoutDate));
            } else {
                this.propertyCheckOutDateTV.setText(getString(R.string.select_date));
            }
            if (this.checkInDate == null && this.checkoutDate == null) {
                this.bookNowButton.setText(getString(R.string.property_detail_check_availability_label));
            } else if (this.searchProperty.getType().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                this.bookNowButton.setText(getString(R.string.booknow));
            } else {
                this.bookNowButton.setText(getString(R.string.property_detail_request_book_label));
            }
            TextView textView = this.selectedGuestCountTV;
            StringBuilder stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.guests));
            textView.setText(String.format(stringBuilder.toString(), new Object[]{this.guestCount}));
            FancyButton fancyButton;
            StringBuilder stringBuilder2;
            if (this.checkInDate == null || this.checkoutDate == null) {
                fancyButton = this.propertyDurationTV;
                stringBuilder2 = new StringBuilder("0 ");
                stringBuilder2.append(getString(R.string.nights));
                fancyButton.setText(stringBuilder2.toString());
            } else {
                fancyButton = this.propertyDurationTV;
                stringBuilder2 = new StringBuilder("%s ");
                stringBuilder2.append(getString(R.string.nights));
                fancyButton.setText(String.format(stringBuilder2.toString(), new Object[]{Integer.valueOf(getDifferenceOfDates(this.checkInDate, this.checkoutDate))}));
            }
            if (this.fromBookings) {
                this.dateLayout.setClickable(false);
                this.selectedGuestCountTV.setClickable(false);
            } else {
                this.dateLayout.setClickable(true);
                this.selectedGuestCountTV.setClickable(true);
            }
            this.imageList.add(this.searchProperty.getImage());
            this.imageAdapter.setData(this.imageList, this.imageTitle);
            doPropertyDetailApiCall(this.searchProperty.getPropertyId(), true);
            EventBus.getDefault().removeStickyEvent(propertyDetailEvent);
        }
    }

    private void initPropertyData(SearchProperty searchProperty) {
        this.shareUrl = this.searchProperty.getShareUrl();
        this.imageList.clear();
        this.imageTitle.clear();
        boolean z = false;
        if (searchProperty.getImagesList() == null) {
            this.imageList.add(searchProperty.getImage());
        } else if (searchProperty.getImagesList().size() > 0) {
            for (int i = 0; i < searchProperty.getImagesList().size(); i++) {
                this.imageList.add(((PropertyImage) searchProperty.getImagesList().get(i)).getPropertyImage());
                this.imageTitle.add(((PropertyImage) searchProperty.getImagesList().get(i)).getTitle());
            }
        }
        this.imageAdapter.setData(this.imageList, this.imageTitle);
        if (searchProperty.getCurrencyCode() != null && searchProperty.getCurrencyCode().equalsIgnoreCase(PayFortPayment.CURRENCY_TYPE)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big>%s/ %s", new Object[]{Float.valueOf(searchProperty.getPrice()), getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
                this.propertyPrice = String.valueOf(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big>%s/ %s", new Object[]{Float.valueOf(searchProperty.getPrice()), getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
                this.toolbar_property_price.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big>%s/ %s", new Object[]{Float.valueOf(searchProperty.getPrice()), getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
            } else {
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "%s <big>%.2f</big>/ %s", new Object[]{getString(R.string.saudi_currency), Float.valueOf(searchProperty.getPrice()), getString(R.string.per_night_label)})));
                this.toolbar_property_price.setText(Html.fromHtml(String.format(Locale.getDefault(), "%s <big>%.2f</big>/ %s", new Object[]{getString(R.string.saudi_currency), Float.valueOf(searchProperty.getPrice()), getString(R.string.per_night_label)})));
                this.propertyPrice = String.valueOf(Html.fromHtml(String.format(Locale.getDefault(), "%s <big>%.2f</big>/ %s", new Object[]{getString(R.string.saudi_currency), Float.valueOf(searchProperty.getPrice()), getString(R.string.per_night_label)})));
            }
        }
        this.noOfPropertyImagesCount.setText(String.format("%s/%s", new Object[]{AppEventsConstants.EVENT_PARAM_VALUE_YES, Integer.valueOf(this.imageList.size())}));
        this.propertyNameTV.setText(searchProperty.getName());
        this.propertyName = searchProperty.getName();
        this.toolbar_property_name.setText(searchProperty.getName());
        CharSequence stringBuilder = new StringBuilder();
        if (this.fromBookings) {
            if (!TextUtils.isEmpty(searchProperty.getFlatNo())) {
                stringBuilder.append(searchProperty.getFlatNo());
                stringBuilder.append(",");
            }
            stringBuilder.append(searchProperty.getAddress());
            if (!TextUtils.isEmpty(searchProperty.getLandMark())) {
                stringBuilder.append("\n");
                stringBuilder.append(String.format("%s : %s", new Object[]{getString(R.string.property_location_landmark), searchProperty.getLandMark()}));
            }
            this.propertyAddressTV.setText(stringBuilder);
        } else {
            if (!TextUtils.isEmpty(searchProperty.getStreet())) {
                stringBuilder.append(searchProperty.getStreet());
                stringBuilder.append(",");
            }
            if (!TextUtils.isEmpty(searchProperty.getCity())) {
                stringBuilder.append(searchProperty.getCity());
                stringBuilder.append(",");
            }
            if (!TextUtils.isEmpty(searchProperty.getCountry())) {
                stringBuilder.append(searchProperty.getCountry());
                stringBuilder.append(".");
            }
            this.propertyAddressTV.setText(stringBuilder);
            this.propertyAddressTV.setMaxLines(2);
        }
        if (searchProperty.getRating() == null || searchProperty.getRating().trim().isEmpty()) {
            this.propertyRatingBar.setVisibility(8);
        } else {
            this.propertyRatingBar.setVisibility(0);
            this.propertyRatingBar.setRating(Float.parseFloat(searchProperty.getRating()));
        }
        setBookingTypeImage(Integer.parseInt(searchProperty.getType()));
        SearchProperty searchProperty2 = this.searchProperty;
        if (searchProperty.getFavourite() == 1) {
            z = true;
        }
        searchProperty2.setPropertyFavourite(z);
        invalidateOptionsMenu();
    }

    private void setBookingTypeImage(int i) {
        switch (i) {
            case 0:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_24hours_booking, 0);
                } else {
                    this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_24hours_booking, 0, 0, 0);
                }
                this.propertyBookingType.setText(R.string.property_detail_24hour_book_available_label);
                break;
            case 1:
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                    this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_instant_booking, 0);
                } else {
                    this.propertyBookingType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_instant_booking, 0, 0, 0);
                }
                this.propertyBookingType.setText(R.string.property_detail_instant_book_available_label);
                return;
            default:
                break;
        }
    }

    private void doPropertyDetailApiCall(String str, boolean z) {
        Log.e("PropertyDetailActivity", "doPropertyDetailApiCall() has called");
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.propertyDetailScrollView.setVisibility(0);
            if (this.propertyDetailPresenter != null) {
                PropertyDetailRequest propertyDetailRequest = new PropertyDetailRequest();
                propertyDetailRequest.setPropertyId(str);
                if (!(this.checkInDate == null || this.checkoutDate == null)) {
                    propertyDetailRequest.setCheckIn(convertDateToString("yyyy-MM-dd", this.checkInDate));
                    propertyDetailRequest.setCheckOut(convertDateToString("yyyy-MM-dd", this.checkoutDate));
                }
                this.propertyDetailPresenter.getPropertyDetailData(getString(R.string.please_wait), propertyDetailRequest, z);
                return;
            }
        }
        showSwipeToRefresh(false);
        this.propertyDetailScrollView.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(true);
        this.noResultTV.setText(getString(true));
        showToast(getString(R.string.internet_connection_label));
    }

    private void initHostData(FullInfo fullInfo) {
        Review review = fullInfo.getReview();
        this.hostId = fullInfo.getHostDetails().getHostedId();
        if (!(this.hostId == null || ((TextUtils.isEmpty(this.hostId) && TextUtils.isEmpty(this.checkUserId)) || this.checkUserId.equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO)))) {
            if (this.checkUserId.equalsIgnoreCase(this.hostId)) {
                this.sendMessage.setVisibility(8);
                this.reportPropertyTV.setVisibility(8);
                this.datesCompleteLayout.setVisibility(8);
                this.bookingLayout.setVisibility(8);
                if (this.fromBookings) {
                    this.youCantBookYourProperty.setVisibility(8);
                } else {
                    this.youCantBookYourProperty.setVisibility(0);
                }
            } else {
                if (this.fromBookings) {
                    if (this.showMessage) {
                        this.sendMessage.setVisibility(0);
                    } else {
                        this.sendMessage.setVisibility(8);
                    }
                    this.bookingLayout.setVisibility(8);
                    this.youCantBookYourProperty.setVisibility(8);
                } else {
                    this.bookingLayout.setVisibility(0);
                    this.youCantBookYourProperty.setVisibility(8);
                    this.sendMessage.setVisibility(0);
                }
                this.reportPropertyTV.setVisibility(0);
                this.datesCompleteLayout.setVisibility(0);
            }
        }
        if (!TextUtils.isEmpty(fullInfo.getMinDays())) {
            this.minimumNights = Integer.parseInt(fullInfo.getMinDays());
        }
        this.propertyHostName.setText(String.format("%s %s", new Object[]{getString(R.string.hosted_by), fullInfo.getHostDetails().getHostedName()}));
        initHostLoadImage(fullInfo.getHostDetails().getHostedImage());
        TextView textView = this.propertyType;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.Property_type));
        stringBuilder.append(" <font color='");
        stringBuilder.append(getResources().getColor(R.color.normal_text_color));
        stringBuilder.append("'>");
        stringBuilder.append(fullInfo.getPropertyType());
        stringBuilder.append("</font>");
        textView.setText(Html.fromHtml(stringBuilder.toString()));
        textView = this.propertyConstraint;
        stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.minimum_label));
        stringBuilder.append(" %s ");
        stringBuilder.append(getString(R.string.nights_label));
        textView.setText(String.format(stringBuilder.toString(), new Object[]{fullInfo.getMinDays()}));
        if (this.searchProperty.getAreaInMeters().equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            this.propertyAreaTV.setVisibility(8);
        } else {
            textView = this.propertyAreaTV;
            stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.property_area_label));
            stringBuilder.append(" %s ");
            stringBuilder.append(getString(R.string.sqm_label));
            textView.setText(String.format(stringBuilder.toString(), new Object[]{this.searchProperty.getAreaInMeters()}));
        }
        this.maxGuestCount = fullInfo.getMaxGuests();
        this.roomsAndGuestsAdapter.setData(setPropertyComponentsData(fullInfo));
        if (VERSION.SDK_INT >= 24) {
            this.propertyDescription.setText(Html.fromHtml(fullInfo.getAboutProperty(), 63));
        } else {
            this.propertyDescription.setText(Html.fromHtml(fullInfo.getAboutProperty()));
        }
        if (fullInfo.getAmenities() == null || fullInfo.getAmenities().size() <= 0) {
            this.amenitySection.setVisibility(8);
        } else {
            this.amenitySection.setVisibility(0);
            this.amenitiesGridAdapter.setData(fullInfo.getAmenities());
        }
        setReviewsData(review);
        setExpandableListViewData(fullInfo);
    }

    public void clickOnItem(List<Amenity> list) {
        if (list != null && list.size() > 0) {
            AmenitiesDialogFragment.newInstance(list).show(getSupportFragmentManager(), "amenityDialog");
        }
    }

    private List<PropertyRoomsAndGuests> setPropertyComponentsData(FullInfo fullInfo) {
        String maxGuests;
        List<PropertyRoomsAndGuests> arrayList = new ArrayList();
        Limits limits = PrefUtils.getInstance().getSeedResponse().getLimits();
        if (!TextUtils.isEmpty(fullInfo.getMaxGuests())) {
            PropertyRoomsAndGuests propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_guest);
            maxGuests = fullInfo.getMaxGuests();
            if (limits.getMaxGuests() == Integer.parseInt(fullInfo.getMaxGuests())) {
                maxGuests = String.format("%s+", new Object[]{fullInfo.getMaxGuests()});
            }
            StringBuilder stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.guests));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{maxGuests}));
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfTents()) || fullInfo.getNoOfTents().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_tent);
            maxGuests = fullInfo.getNoOfTents();
            if (limits.getMaxTents() == Integer.parseInt(fullInfo.getNoOfTents())) {
                maxGuests = String.format("%s+", new Object[]{fullInfo.getNoOfTents()});
            }
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.tents_label));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{maxGuests}));
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfSinglebeds()) || fullInfo.getNoOfSinglebeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_single_bed);
            maxGuests = fullInfo.getNoOfSinglebeds();
            if (limits.getMaxSinglebeds() == Integer.parseInt(fullInfo.getNoOfSinglebeds())) {
                maxGuests = String.format("%s+", new Object[]{fullInfo.getNoOfSinglebeds()});
            }
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.single_bed_label));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{maxGuests}));
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfDoublebeds()) || fullInfo.getNoOfDoublebeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_double_bed);
            maxGuests = fullInfo.getNoOfDoublebeds();
            if (limits.getMaxDoublebeds() == Integer.parseInt(fullInfo.getNoOfDoublebeds())) {
                maxGuests = String.format("%s+", new Object[]{fullInfo.getNoOfDoublebeds()});
            }
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.double_bed_label));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{maxGuests}));
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfBeds()) || fullInfo.getNoOfBeds().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_bed_room);
            maxGuests = fullInfo.getNoOfBeds();
            if (limits.getMaxBeds() == Integer.parseInt(fullInfo.getNoOfBeds())) {
                maxGuests = String.format("%s+", new Object[]{fullInfo.getNoOfBeds()});
            }
            stringBuilder = new StringBuilder("%s ");
            stringBuilder.append(getString(R.string.bed_room_label));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(stringBuilder.toString(), new Object[]{maxGuests}));
            arrayList.add(propertyRoomsAndGuests);
        }
        if (!(TextUtils.isEmpty(fullInfo.getNoOfBathrooms()) || fullInfo.getNoOfBathrooms().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO))) {
            propertyRoomsAndGuests = new PropertyRoomsAndGuests();
            propertyRoomsAndGuests.setPropertyComponentDrawable(R.drawable.ic_bath_rooms);
            maxGuests = fullInfo.getNoOfBathrooms();
            if (((float) limits.getMaxBathrooms()) == Float.parseFloat(fullInfo.getNoOfBathrooms())) {
                maxGuests = String.format("%s+", new Object[]{fullInfo.getNoOfBathrooms()});
            }
            fullInfo = new StringBuilder("%s ");
            fullInfo.append(getString(R.string.bathrooms));
            propertyRoomsAndGuests.setPropertyComponentName(String.format(fullInfo.toString(), new Object[]{maxGuests}));
            arrayList.add(propertyRoomsAndGuests);
        }
        return arrayList;
    }

    private void setExpandableListViewData(FullInfo fullInfo) {
        List<String> arrayList = new ArrayList();
        for (Houserule id : fullInfo.getHouseRules()) {
            arrayList.add(id.getId());
        }
        if (!TextUtils.isEmpty(fullInfo.getCustomHouseRule())) {
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
        if (!TextUtils.isEmpty(fullInfo.getCustomHouseRule())) {
            HouseRuleSafetyPolicy houseRuleSafetyPolicy2 = new HouseRuleSafetyPolicy();
            houseRuleSafetyPolicy2.setId("111");
            houseRuleSafetyPolicy2.setName(fullInfo.getCustomHouseRule());
            houseRuleSafetyPolicy2.setNotPolicy(true);
            linkedHashMap.put("111", houseRuleSafetyPolicy2);
        }
        for (String str : arrayList) {
            if (linkedHashMap.containsKey(str)) {
                ((HouseRuleSafetyPolicy) linkedHashMap.get(str)).setChecked(true);
            }
        }
        arrayList = new ArrayList();
        for (HouseSafety id2 : fullInfo.getHouseSafety()) {
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
        houseRuleSafetyPolicy4.setId(fullInfo.getCancellationPolicyId());
        houseRuleSafetyPolicy4.setName(fullInfo.getCancelDescription());
        houseRuleSafetyPolicy4.setNotPolicy(false);
        arrayList2.add(houseRuleSafetyPolicy4);
        Map linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(getString(R.string.house_rules_label), new ArrayList(linkedHashMap.values()));
        linkedHashMap3.put(getString(R.string.house_safety_label), new ArrayList(linkedHashMap2.values()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.cancellation_policy_label));
        stringBuilder.append("-%s");
        linkedHashMap3.put(String.format(stringBuilder.toString(), new Object[]{fullInfo.getCancellationPolicy()}), arrayList2);
        this.expandableListView.setAdapter(new CustomExpandableListAdapter(this, new ArrayList(linkedHashMap3.keySet()), linkedHashMap3, false));
    }

    private void setReviewsData(Review review) {
        if (review.getFeeds() == null || review.getFeeds().size() <= 0) {
            this.appRatingLayout.setVisibility(8);
            this.reviewsLayout.setVisibility(8);
            return;
        }
        this.appRatingLayout.setVisibility(0);
        this.reviewsLayout.setVisibility(0);
        this.tv_reviewscount.setText(String.format("%s  %s", new Object[]{getString(R.string.reviews), Integer.valueOf(review.getReviewed())}));
        this.propertyReviews.setText(String.format("%s  %s", new Object[]{getString(R.string.reviews), Integer.valueOf(review.getReviewed())}));
        if (review.getReviewed() > 1) {
            this.moreReviewsTV.setVisibility(0);
            Feed feed = (Feed) review.getFeeds().get(0);
            this.reviewRating.setRating(feed.getRating());
            this.hostName.setText(feed.getHostname());
            this.reviwedDate.setText(Utils.reviewsDateFormatter(feed.getSubmitted()));
            this.reviewText.setText(feed.getComment());
            GlideApp.with(getApplicationContext()).asBitmap().load(feed.getGuestImage()).placeholder(R.drawable.ic_profile_image_1).error(R.drawable.ic_profile_image_1).diskCacheStrategy(DiskCacheStrategy.DATA).into(this.reviewerImage);
            return;
        }
        this.moreReviewsTV.setVisibility(8);
    }

    private void initHostLoadImage(String str) {
        GlideApp.with(this).asBitmap().load(str).placeholder(R.drawable.ic_profile_image2).apply(new RequestOptions().transform(new RoundedCorners(12))).error(R.drawable.ic_profile_image2).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(this.propertyHostImage);
    }

    public void onSuccess(PropertyFavouriteResponse propertyFavouriteResponse, int i) {
        this.searchProperty.setFavourite(Integer.parseInt(propertyFavouriteResponse.getFavourite()));
        this.searchProperty.setPropertyFavourite(this.searchProperty.getFavourite() == 1 ? 1 : 0);
        if (this.searchProperty.getFavourite() == 1) {
            showToast(getString(R.string.property_got_saved));
        } else {
            showToast(getString(R.string.property_got_unsaved));
        }
        invalidateOptionsMenu();
        EventBus.getDefault().postSticky(new ChangeFavouriteIconEvent(this.searchProperty.getPropertyId(), this.searchProperty.getFavourite()));
    }

    public void onSuccess(PropertyDetailResponse propertyDetailResponse) {
        if (propertyDetailResponse != null) {
            this.searchProperty = propertyDetailResponse.getPropertyInfo();
            initPropertyData(propertyDetailResponse.getPropertyInfo());
            initHostData(propertyDetailResponse.getFullInfo());
        }
    }

    private String convertDateToString(String str, Date date) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    private String convertDateToString(Date date) {
        return convertDateToString("dd-MM-yy", date);
    }

    private boolean validateDates() {
        return (this.propertyCheckInDateTV.getText().toString().trim().equalsIgnoreCase(getString(R.string.select_date)) && this.propertyCheckOutDateTV.getText().toString().trim().equalsIgnoreCase(getString(R.string.select_date))) ? false : true;
    }

    private int getDifferenceOfDates(Date date, Date date2) {
        return (int) ((date2.getTime() - date.getTime()) / 86400000);
    }

    private void show24HoursRequestDialog() {
        View inflate = getLayoutInflater().inflate(R.layout.hours_24_request_dialog, null, false);
        ((ImageView) inflate.findViewById(R.id.close_image)).setOnClickListener(this.closeClickListener);
        ((FancyButton) inflate.findViewById(R.id.hours_request_ok_button)).setOnClickListener(this.okOnclickListener);
        this.alertDialog = new Builder(this).setView(inflate).create();
        this.alertDialog.setCanceledOnTouchOutside(false);
        this.alertDialog.show();
    }

    public void onSuccess(PropertyViewBillResponse propertyViewBillResponse) {
        if (propertyViewBillResponse != null) {
            PropertyViewBillEvent propertyViewBillEvent = new PropertyViewBillEvent(propertyViewBillResponse);
            propertyViewBillEvent.setCheckInDate(this.checkInDate);
            propertyViewBillEvent.setCheckOutDate(this.checkoutDate);
            propertyViewBillEvent.setLocationName(this.locationName);
            propertyViewBillEvent.setMaxGuestCount(this.maxGuestCount);
            propertyViewBillEvent.setMinimumNights(this.minimumNights);
            if (TextUtils.isEmpty(this.propertyAddressTV.getText().toString()) == null) {
                propertyViewBillEvent.setAddressName(this.propertyAddressTV.getText().toString());
            }
            EventBus.getDefault().postSticky(propertyViewBillEvent);
            startActivity(new Intent(this, PropertyViewBillActivity.class));
        }
    }

    public void onSuccess(boolean z) {
        if (z) {
            doPropertyViewBillApiCall();
        }
    }

    public void onCancelBookingError(APIError aPIError, int i) {
        showErrorMessage(aPIError, i);
    }

    private void showErrorMessage(APIError aPIError, int i) {
        if (i != ErrorCodes.API_ERROR) {
            if (i == ErrorCodes.INTERNAL_SERVER_ERROR) {
                showToast(getString(R.string.internal_server_error));
            } else if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                showToast(getString(R.string.socket_time_out_error));
            } else if (i == 511) {
                showToast(getString(R.string.network_error));
            }
        } else if (aPIError != null) {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onError(APIError aPIError, int i) {
        showErrorMessage(aPIError, i);
        showSwipeToRefresh(false);
        this.propertyDetailScrollView.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setVisibility(8);
        this.noResultTV.setText(getString(R.string.general_api_error_msg));
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    private void showDateGuestPropertyDialog(int i) {
        if (!TextUtils.isEmpty(this.searchProperty.getPropertyId())) {
            DateGuestPropertySelectionDialog.newInstance(i, this.checkInDate, this.checkoutDate, this.guestCount, this.maxGuestCount, this.locationName, Integer.parseInt(this.searchProperty.getPropertyId()), this.minimumNights).show(getSupportFragmentManager(), "DateGuestPropertySelectionDialog");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDateGuestPropertyValues(DateGuestPropertyDialogEvent dateGuestPropertyDialogEvent) {
        if (dateGuestPropertyDialogEvent != null) {
            this.guestCount = dateGuestPropertyDialogEvent.getGuestCount();
            this.checkInDate = dateGuestPropertyDialogEvent.getCheckInDate();
            this.checkoutDate = dateGuestPropertyDialogEvent.getCheckOutDate();
            this.cost = dateGuestPropertyDialogEvent.getCost();
            if (this.checkInDate != null) {
                this.propertyCheckInDateTV.setText(convertDateToString(this.checkInDate));
            } else {
                this.propertyCheckInDateTV.setText(getString(R.string.select_date));
            }
            if (this.checkoutDate != null) {
                this.propertyCheckOutDateTV.setText(convertDateToString(this.checkoutDate));
            } else {
                this.propertyCheckOutDateTV.setText(getString(R.string.select_date));
            }
            StringBuilder stringBuilder;
            if (this.checkInDate == null || this.checkoutDate == null) {
                dateGuestPropertyDialogEvent = this.propertyDurationTV;
                stringBuilder = new StringBuilder("0 ");
                stringBuilder.append(getString(R.string.nights));
                dateGuestPropertyDialogEvent.setText(stringBuilder.toString());
            } else {
                dateGuestPropertyDialogEvent = this.propertyDurationTV;
                stringBuilder = new StringBuilder("%s ");
                stringBuilder.append(getString(R.string.nights));
                dateGuestPropertyDialogEvent.setText(String.format(stringBuilder.toString(), new Object[]{Integer.valueOf(getDifferenceOfDates(this.checkInDate, this.checkoutDate))}));
            }
            dateGuestPropertyDialogEvent = this.selectedGuestCountTV;
            StringBuilder stringBuilder2 = new StringBuilder("%s ");
            stringBuilder2.append(getString(R.string.guests));
            dateGuestPropertyDialogEvent.setText(String.format(stringBuilder2.toString(), new Object[]{this.guestCount}));
            if (this.checkInDate == null && this.checkoutDate == null) {
                this.bookNowButton.setText(getString(R.string.property_detail_check_availability_label));
            } else if (this.searchProperty.getType().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_YES) != null) {
                this.bookNowButton.setText(getString(R.string.booknow));
                this.toolbar_property_price.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big>%s/ %s", new Object[]{this.cost, getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big>%s/ %s", new Object[]{this.cost, getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
            } else {
                this.bookNowButton.setText(getString(R.string.property_detail_request_book_label));
                this.toolbar_property_price.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big>%s/ %s", new Object[]{this.cost, getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
                this.priceTV.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big>%s/ %s", new Object[]{this.cost, getString(R.string.saudi_currency), getString(R.string.per_night_label)})));
            }
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.propertyDetailPresenter != null) {
            this.propertyDetailPresenter.unSubscribeDisposables();
        }
        if (this.propertyFavouritePresenter != null) {
            this.propertyFavouritePresenter.unSubscribeDisposables();
        }
        if (this.viewBillPresenter != null) {
            this.viewBillPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.property_detail_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            case R.id.favourite:
                doFavouriteApiCall();
                return true;
            case R.id.favourite_selected:
                doFavouriteApiCall();
                return true;
            case R.id.share:
                if (TextUtils.isEmpty(this.shareUrl) == null) {
                    menuItem = new Intent();
                    menuItem.setAction("android.intent.action.SEND");
                    menuItem.putExtra("android.intent.extra.SUBJECT", getString(R.string.property_link_label));
                    menuItem.putExtra("android.intent.extra.TEXT", this.shareUrl);
                    menuItem.setType("text/plain");
                    startActivity(Intent.createChooser(menuItem, getString(R.string.share_via)));
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void doFavouriteApiCall() {
        int i = 1;
        if (!isUserLoggedIn()) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("from_property_detail", true);
            goToActivity(HomeActivity.class, bundle);
        } else if (!isNetworkConnected()) {
            showToast(getString(R.string.error_message_network));
        } else if (this.searchProperty != null) {
            PropertyFavouriteRequest propertyFavouriteRequest = new PropertyFavouriteRequest();
            propertyFavouriteRequest.setPropertyId(this.searchProperty.getPropertyId());
            if (this.searchProperty.getFavourite() != 0) {
                i = 0;
            }
            propertyFavouriteRequest.setStatus(String.valueOf(i));
            this.propertyFavouritePresenter.setPropertyFavouriteToUser(getString(R.string.please_wait), propertyFavouriteRequest, 0);
        }
    }

    private boolean isUserLoggedIn() {
        return (PrefUtils.getInstance().getUserAccessToken() == null || PrefUtils.getInstance().getCookie() == null) ? false : true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (this.searchProperty != null) {
            menu.findItem(R.id.favourite).setVisible(this.searchProperty.isPropertyFavourite() ^ true);
            menu.findItem(R.id.favourite_selected).setVisible(this.searchProperty.isPropertyFavourite());
        }
        return true;
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getBooleanExtra(StringConstants.FROM_BOOKING_PAYMENT, false) != null) {
            doCancelApiCall(false);
        }
    }

    private void doCancelApiCall(boolean z) {
        if (!isUserLoggedIn()) {
            z = new Bundle();
            z.putBoolean("from_property_detail", true);
            goToActivity(HomeActivity.class, z);
        } else if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.propertyDetailScrollView.setVisibility(0);
            if (this.cancelBookingPresenter != null) {
                this.cancelBookingPresenter.cancelBooking(getString(R.string.please_wait), z);
            }
        } else {
            showSwipeToRefresh(false);
            this.propertyDetailScrollView.setVisibility(8);
            this.noResultLayout.setVisibility(0);
            this.noResultImage.setImageResource(R.drawable.ic_nointernet);
            this.noResultTV.setText(getString(R.string.error_message_network));
            showToast(getString(true));
        }
    }
}
