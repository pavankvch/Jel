package com.jelsat.activities;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.businesslogic.publicprofile.IPublicProfileView;
import com.businesslogic.publicprofile.PublicProfilePresenter;
import com.data.profile.GetGuestProfileRequest;
import com.data.propertydetail.Feed;
import com.data.publicprofile.PublicProfileResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.ExpandableTextView;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;

public class PublicProfileActivity extends BaseAppCompactActivity implements IPublicProfileView {
    @BindView(2131362700)
    ExpandableTextView about;
    @BindView(2131361821)
    LinearLayout aboutLayout;
    @BindView(2131362734)
    TextView hostName;
    @BindView(2131362736)
    TextView languages;
    @BindView(2131362737)
    TextView memberSince;
    private int needReview;
    @BindView(2131362350)
    NestedScrollView nestedScrollView;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private PublicProfilePresenter presenter = new PublicProfilePresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362248)
    ImageView profileImage;
    @BindView(2131362751)
    TextView profileName;
    @BindView(2131362520)
    RatingBar reviewRating;
    @BindView(2131362768)
    ExpandableTextView reviewText;
    @BindView(2131362521)
    ImageView reviewerImage;
    @BindView(2131362770)
    TextView reviewsCount;
    @BindView(2131362522)
    LinearLayout reviewsLayout;
    @BindView(2131362523)
    LinearLayout reviewsLinearLayout;
    @BindView(2131362769)
    TextView reviwedDate;
    @BindView(2131362773)
    FancyButton showMoreReviews;
    @BindView(2131362613)
    LinearLayout spokenLanguagesLayout;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    private String uid;
    @BindView(2131362787)
    TextView verifiedInfo;

    public int getActivityLayout() {
        return R.layout.activity_profile_publicview;
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getPublicProfile(true);
    }

    @OnClick({2131362773})
    public void showMoreReviews() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.UID, this.uid);
        bundle.putBoolean(StringConstants.FROM_PUBLIC_PROFILE, true);
        goToActivity(ReviewsActivity.class, bundle);
    }

    @OnClick({2131361892})
    public void goBack() {
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            this.uid = getIntent().getStringExtra(StringConstants.UID);
            this.needReview = getIntent().getIntExtra(StringConstants.NEED_REVIEW, 0);
        }
        initSwipeToRefresh();
        getPublicProfile(true);
        bundle = Utils.getScreenHeightInPixels(getApplicationContext());
        Log.e("123", String.valueOf(bundle));
        bundle /= 2;
        this.profileImage.setMinimumHeight(bundle);
        this.profileImage.setMaxHeight(bundle);
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                PublicProfileActivity.this.getPublicProfile(false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    private void getPublicProfile(boolean z) {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.nestedScrollView.setVisibility(0);
            if (!(this.presenter == null || this.uid == null)) {
                GetGuestProfileRequest getGuestProfileRequest = new GetGuestProfileRequest();
                getGuestProfileRequest.setGuestId(this.uid);
                getGuestProfileRequest.setShowReview(this.needReview);
                this.presenter.getGuestProfileData(getString(R.string.please_wait), getGuestProfileRequest, z);
                return;
            }
        }
        showSwipeToRefresh(false);
        this.nestedScrollView.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(true));
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

    public void onSuccess(PublicProfileResponse publicProfileResponse) {
        GlideApp.with((FragmentActivity) this).asBitmap().load(publicProfileResponse.getProfileImage()).placeholder((int) R.drawable.default_logo).error((int) R.drawable.default_logo).diskCacheStrategy(DiskCacheStrategy.DATA).into(this.profileImage);
        this.profileName.setText(publicProfileResponse.getName());
        String since = publicProfileResponse.getSince();
        this.memberSince.setText(String.format("%s %s", new Object[]{getString(R.string.membersince), since.substring(0, 4)}));
        if (TextUtils.isEmpty(publicProfileResponse.getDescription())) {
            this.aboutLayout.setVisibility(8);
        } else {
            this.aboutLayout.setVisibility(0);
            if (VERSION.SDK_INT >= 24) {
                this.about.setText(Html.fromHtml(publicProfileResponse.getDescription(), 63));
            } else {
                this.about.setText(Html.fromHtml(publicProfileResponse.getDescription()));
            }
        }
        if (TextUtils.isEmpty(publicProfileResponse.getSpokenLanguages())) {
            this.spokenLanguagesLayout.setVisibility(8);
        } else {
            this.spokenLanguagesLayout.setVisibility(0);
            this.languages.setText(publicProfileResponse.getSpokenLanguages());
        }
        CharSequence stringBuilder = new StringBuilder();
        if (publicProfileResponse.getVerifiedInfo().getPhoneNumberVerified() == 1) {
            stringBuilder.append(getString(R.string.phonenumber));
        }
        if (!TextUtils.isEmpty(publicProfileResponse.getVerifiedInfo().getEmailVerified()) && Integer.parseInt(publicProfileResponse.getVerifiedInfo().getEmailVerified()) == 1) {
            stringBuilder.append(" , ");
            stringBuilder.append(getString(R.string.email_label));
        }
        if (!TextUtils.isEmpty(publicProfileResponse.getVerifiedInfo().getNationalIdVerified()) && Integer.parseInt(publicProfileResponse.getVerifiedInfo().getNationalIdVerified()) == 1) {
            stringBuilder.append(" , ");
            stringBuilder.append(getString(R.string.national_id));
        }
        if (!TextUtils.isEmpty(publicProfileResponse.getVerifiedInfo().getDobVerified()) && Integer.parseInt(publicProfileResponse.getVerifiedInfo().getDobVerified()) == 1) {
            stringBuilder.append(" , ");
            stringBuilder.append(getString(R.string.date_of_birth));
        }
        this.verifiedInfo.setText(stringBuilder);
        setReviewsData(publicProfileResponse);
    }

    private void setReviewsData(PublicProfileResponse publicProfileResponse) {
        if (publicProfileResponse.getFeeds() == null || publicProfileResponse.getFeeds().size() <= 0) {
            this.reviewsLinearLayout.setVisibility(8);
            return;
        }
        this.reviewsLinearLayout.setVisibility(0);
        this.reviewsCount.setText(String.format("%s  %s", new Object[]{getString(R.string.reviews), Integer.valueOf(publicProfileResponse.getReviewed())}));
        if (publicProfileResponse.getReviewed() > 1) {
            this.showMoreReviews.setVisibility(0);
            Feed feed = (Feed) publicProfileResponse.getFeeds().get(0);
            this.reviewRating.setRating(feed.getRating());
            this.hostName.setText(feed.getHostname());
            this.reviwedDate.setText(Utils.reviewsDateFormatter(feed.getSubmitted()));
            this.reviewText.setText(feed.getComment());
            GlideApp.with(getApplicationContext()).asBitmap().load(feed.getGuestImage()).placeholder((int) R.drawable.ic_profile_image_1).error((int) R.drawable.ic_profile_image_1).diskCacheStrategy(DiskCacheStrategy.DATA).into(this.reviewerImage);
            return;
        }
        this.showMoreReviews.setVisibility(8);
    }

    public void onDetachedFromWindow() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
