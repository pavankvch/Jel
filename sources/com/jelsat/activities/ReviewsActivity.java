package com.jelsat.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.propertyfeedback.FeedbackPresenter;
import com.businesslogic.propertyfeedback.IFeedbackView;
import com.data.profilefeedback.SubmitGuestFeedbackRequest;
import com.data.propertydetail.Feed;
import com.data.propertyfeedback.SubmitPropertyFeedbackRequest;
import com.data.propertyreviews.PropertyReviewsResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.ReviewAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.widgets.FancyButton;
import java.util.List;

public class ReviewsActivity extends BaseAppCompactActivity implements IFeedbackView {
    private boolean isFromPublicProfile;
    private ReviewAdapter mAdapter;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private FeedbackPresenter presenter = new FeedbackPresenter(this, RetrofitClient.getAPIService());
    private String propertyId;
    @BindView(2131362499)
    RecyclerView recyclerview_reviews;
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362733)
    TextView tv_heading;
    private String uid;

    public int getActivityLayout() {
        return R.layout.activity_profile_reviews;
    }

    public void onFeedbackSubmitSuccess(boolean z) {
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getAllReviews(true);
    }

    @OnClick({2131361892})
    public void goBack() {
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.tv_heading.setText(getResources().getString(R.string.reviews));
        if (getIntent() != null) {
            this.propertyId = getIntent().getStringExtra(StringConstants.PROPERTY_ID);
            this.isFromPublicProfile = getIntent().getBooleanExtra(StringConstants.FROM_PUBLIC_PROFILE, false);
            this.uid = getIntent().getStringExtra(StringConstants.UID);
        }
        initSwipeToRefresh();
        initRecyclerView();
        getAllReviews(true);
    }

    private void initRecyclerView() {
        this.recyclerview_reviews.setLayoutManager(new LinearLayoutManager(this));
        this.mAdapter = new ReviewAdapter(null, this, this.isFromPublicProfile);
        this.recyclerview_reviews.addItemDecoration(new DividerItemDecoration(this, 1));
        this.recyclerview_reviews.setAdapter(this.mAdapter);
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                ReviewsActivity.this.getAllReviews(false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    private void getAllReviews(boolean z) {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.recyclerview_reviews.setVisibility(0);
            if (this.presenter != null) {
                if (this.isFromPublicProfile) {
                    this.presenter.getGuestFeedbackReviews(getString(R.string.please_wait), getGuestReviewsRequestObject(), z);
                    return;
                } else {
                    this.presenter.getFeedbacks(getString(R.string.please_wait), getPropertyReviewsRequestObject(), z);
                    return;
                }
            }
        }
        showSwipeToRefresh(false);
        this.recyclerview_reviews.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(true));
    }

    private SubmitPropertyFeedbackRequest getPropertyReviewsRequestObject() {
        SubmitPropertyFeedbackRequest submitPropertyFeedbackRequest = new SubmitPropertyFeedbackRequest();
        submitPropertyFeedbackRequest.setPropertyId(this.propertyId);
        return submitPropertyFeedbackRequest;
    }

    private SubmitGuestFeedbackRequest getGuestReviewsRequestObject() {
        SubmitGuestFeedbackRequest submitGuestFeedbackRequest = new SubmitGuestFeedbackRequest();
        submitGuestFeedbackRequest.setUserId(this.uid);
        return submitGuestFeedbackRequest;
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onReviewsGet(PropertyReviewsResponse propertyReviewsResponse) {
        this.mAdapter.setData(propertyReviewsResponse.getReview());
        showNoResultVisibility(propertyReviewsResponse.getReview());
    }

    private void showNoResultVisibility(List<Feed> list) {
        this.noResultImage.setImageResource(R.drawable.ic_noresults_found);
        this.noResultTV.setText(getText(R.string.no_resu_found));
        if (list == null || list.size() <= null) {
            this.noResultLayout.setVisibility(0);
            this.retryButton.setVisibility(8);
            this.recyclerview_reviews.setVisibility(8);
            return;
        }
        this.noResultLayout.setVisibility(8);
        this.recyclerview_reviews.setVisibility(0);
    }

    public void onStop() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
