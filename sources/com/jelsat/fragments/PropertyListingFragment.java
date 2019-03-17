package com.jelsat.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.propertylisting.DeletePropertyView;
import com.businesslogic.propertylisting.PropertListingView;
import com.businesslogic.propertylisting.PropertyDeletingPresenter;
import com.businesslogic.propertylisting.PropertyListingPresenter;
import com.data.propertylisting.DeletePropertyRequest;
import com.data.propertylisting.PropertyListingResponse;
import com.data.propertylisting.Published;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.activities.AddYourPropertySteps;
import com.jelsat.activities.CalendarActivity;
import com.jelsat.adapters.PropertyListingAdapter;
import com.jelsat.adapters.PropertyListingAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.widgets.FancyButton;
import java.util.ArrayList;
import java.util.List;

public class PropertyListingFragment extends BaseFragment implements DeletePropertyView, PropertListingView, OnListItemClickListener {
    private List<Published> allDataArray;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private int position1;
    private PropertyDeletingPresenter propertyDeletingPresenter = new PropertyDeletingPresenter(this, RetrofitClient.getAPIService());
    private PropertyListingAdapter propertyListingAdapter;
    private PropertyListingPresenter propertyListingPresenter = new PropertyListingPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362498)
    RecyclerView recyclerView;
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;

    public int getFragmentLayoutId() {
        return R.layout.fragment_property_listing;
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getPropertiesList(true);
    }

    @OnClick({2131362384})
    public void addProperty() {
        Intent intent = new Intent(getActivity(), AddYourPropertySteps.class);
        intent.putExtra("FromActivity", "fromProfileHostFragment");
        intent.putExtra("checkIntent", false);
        startActivity(intent);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        initSwipeToRefresh();
        initView();
        getPropertiesList(true);
        return layoutInflater;
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                PropertyListingFragment.this.getPropertiesList(false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    private void getPropertiesList(boolean z) {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            if (this.propertyListingPresenter != null) {
                this.propertyListingPresenter.getPropertListings(getString(R.string.please_wait), z);
                return;
            }
        }
        showSwipeToRefresh(false);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(true));
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    private void initView() {
        this.propertyListingAdapter = new PropertyListingAdapter(this.allDataArray, getActivity(), this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.propertyListingAdapter);
    }

    public void onSuccess(PropertyListingResponse propertyListingResponse) {
        this.allDataArray = new ArrayList();
        this.allDataArray.addAll(propertyListingResponse.getIncompleteList());
        this.allDataArray.addAll(propertyListingResponse.getUnpublishedList());
        this.allDataArray.addAll(propertyListingResponse.getReviewList());
        this.allDataArray.addAll(propertyListingResponse.getPublishedList());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.allDataArray.size());
        Log.d("allDataArraySize", stringBuilder.toString());
        this.propertyListingAdapter.setData(this.allDataArray);
        showNoResultVisibility(this.allDataArray);
    }

    private void showNoResultVisibility(List<Published> list) {
        this.noResultImage.setImageResource(R.drawable.ic_noresults_found);
        this.noResultTV.setText(getText(R.string.no_properties_found_label));
        if (list == null || list.size() <= null) {
            this.noResultLayout.setVisibility(0);
            this.retryButton.setVisibility(8);
            return;
        }
        this.noResultLayout.setVisibility(8);
    }

    public void onSuccess(int i, int i2) {
        showToast(getString(R.string.listing_property_deleted));
        this.allDataArray.remove(this.position1);
        this.propertyListingAdapter.notifyItemRemoved(this.position1);
        this.propertyListingAdapter.notifyItemRangeChanged(this.position1, this.allDataArray.size());
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void clickOnListItem(final int i, final String str, String str2, int i2) {
        Log.d("PropertyId", str);
        if (i2 == 0) {
            PrefUtils.getInstance().saveSkipAddressBollen(Boolean.valueOf(false));
            i = new Intent(getActivity(), AddYourPropertySteps.class);
            i.putExtra("listingPropertyId", str);
            i.putExtra("checkIntent", true);
            i.putExtra("checkPublishOrUnpublish", false);
            i.putExtra("FromActivity", "propertyListing");
            Log.d("listingPropertyId", str);
            startActivity(i);
        } else if (i2 == 1) {
            this.position1 = i;
            str2 = new Builder(requireActivity());
            str2.setTitle(getString(R.string.alert));
            str2.setMessage(getString(R.string.listing_sure_you_want_to_delete_property));
            str2.setCancelable(false);
            str2.setPositiveButton(getString(R.string.listing_ok), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface = new DeletePropertyRequest();
                    dialogInterface.setPropertyId(Integer.parseInt(str));
                    PropertyListingFragment.this.propertyDeletingPresenter.getPropertyDeleted(PropertyListingFragment.this.getString(R.string.property_deleting), dialogInterface, i, i);
                }
            });
            str2.setNegativeButton(getString(R.string.listing_cancel), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            str2.show();
        } else if (i2 == 2) {
            i = new Bundle();
            i.putString(StringConstants.PROPERTY_ID, str);
            i.putString(StringConstants.PROPERTY_NAME, str2);
            i.putBoolean(StringConstants.IS_EDIT_PROPERTY, true);
            goToActivity(CalendarActivity.class, i);
        } else if (i2 == 3) {
            PrefUtils.getInstance().saveSkipAddressBollen(Boolean.valueOf(false));
            i = new Intent(getActivity(), AddYourPropertySteps.class);
            i.putExtra("listingPropertyId", str);
            i.putExtra("checkIntent", true);
            i.putExtra("checkPublishOrUnpublish", true);
            i.putExtra("FromActivity", "propertyListing");
            Log.d("listingPropertyId", str);
            startActivity(i);
        } else {
            if (i2 == 4) {
                PrefUtils.getInstance().saveSkipAddressBollen(Boolean.valueOf(true));
                i = new Intent(getActivity(), AddYourPropertySteps.class);
                i.putExtra("listingPropertyId", str);
                i.putExtra("checkIntent", true);
                i.putExtra("checkPublishOrUnpublish", false);
                i.putExtra("FromActivity", "propertyListing");
                Log.d("listingPropertyId", str);
                startActivity(i);
            }
        }
    }

    public void onStop() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onStop();
    }

    public void onDetach() {
        if (this.propertyListingPresenter != null) {
            this.propertyListingPresenter.unSubscribeDisposables();
        }
        if (this.propertyDeletingPresenter != null) {
            this.propertyDeletingPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
