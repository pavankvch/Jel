package com.jelsat.fragments;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.jelsat.R;
import com.jelsat.activities.AddYourPropertySteps;
import com.jelsat.adapters.ListingAdapter.PropertyListingAdapaterSectionRecycler;
import com.jelsat.adapters.ListingAdapter.PropertyListingAdapaterSectionRecycler.OnListItemClickListener;
import com.jelsat.adapters.ListingAdapter.PropertyListingSectionModel;
import com.jelsat.baseuiframework.BaseFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PropertyListingFragmentNew extends BaseFragment implements DeletePropertyView, PropertListingView, OnListItemClickListener {
    private PropertyListingAdapaterSectionRecycler adapaterSectionRecycler;
    private String clickedPropertyId;
    Map<String, List<Published>> listingDataMap;
    @BindView(2131362371)
    TextView noResultTextView;
    private int position1;
    private PropertyDeletingPresenter propertyDeletingPresenter = new PropertyDeletingPresenter(this, RetrofitClient.getAPIService());
    private PropertyListingPresenter propertyListingPresenter = new PropertyListingPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362498)
    RecyclerView recyclerView;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;

    public int getFragmentLayoutId() {
        return R.layout.fragment_property_listing_fragment_new;
    }

    @OnClick({2131362384})
    public void addProperty() {
        Intent intent = new Intent(getActivity(), AddYourPropertySteps.class);
        intent.putExtra("FromActivity", "fromProfileHostFragment");
        intent.putExtra("checkIntent", false);
        startActivity(intent);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        initSwipeToRefresh();
        setUpRecyclerView();
        getPropertiesList(true);
        return layoutInflater;
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                PropertyListingFragmentNew.this.getPropertiesList(false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    private void getPropertiesList(boolean z) {
        if (this.propertyListingPresenter != null) {
            this.propertyListingPresenter.getPropertListings(getString(R.string.please_wait), z);
        }
    }

    private void setUpRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.adapaterSectionRecycler = new PropertyListingAdapaterSectionRecycler(getActivity(), null, this);
        this.recyclerView.setAdapter(this.adapaterSectionRecycler);
    }

    public void onSuccess(PropertyListingResponse propertyListingResponse) {
        if (propertyListingResponse != null) {
            if (this.listingDataMap == null) {
                this.listingDataMap = new LinkedHashMap();
            } else {
                this.listingDataMap.clear();
            }
            this.listingDataMap.put("INCOMPLETED PROPERTIES", propertyListingResponse.getIncompleteList());
            this.listingDataMap.put("UNPUBLISHED PROPERTIES", propertyListingResponse.getUnpublishedList());
            this.listingDataMap.put("PUBLISHED PROPERTIES", propertyListingResponse.getPublishedList());
            populateRecyclerView(this.listingDataMap);
        }
    }

    private void populateRecyclerView(Map<String, List<Published>> map) {
        List arrayList = new ArrayList();
        for (String str : map.keySet()) {
            arrayList.add(new PropertyListingSectionModel(str, (List) map.get(str)));
        }
        this.adapaterSectionRecycler.notifyDataChanged(arrayList);
    }

    public void onSuccess(int i, int i2) {
        showToast(getString(R.string.listing_property_deleted));
        Log.e("aaa", String.format("section:%s child:%s", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        this.adapaterSectionRecycler.removeChild(i, i2);
    }

    public Entry<String, List<Published>> getEntry(int i) {
        int i2 = 0;
        for (Entry<String, List<Published>> entry : this.listingDataMap.entrySet()) {
            int i3 = i2 + 1;
            if (i2 == i) {
                return entry;
            }
            i2 = i3;
        }
        return 0;
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

    public void clickOnListItem(final int i, final int i2, final String str, int i3) {
        if (i3 == 0) {
            i = new Intent(getActivity(), AddYourPropertySteps.class);
            i.putExtra("listingPropertyId", str);
            i.putExtra("checkIntent", true);
            i.putExtra("checkPublishOrUnpublish", false);
            i.putExtra("FromActivity", "propertyListing");
            Log.d("listingPropertyId", str);
            startActivity(i);
        } else if (i3 == 1) {
            this.position1 = i2;
            this.clickedPropertyId = str;
            i3 = new Builder(requireActivity());
            i3.setTitle(getString(R.string.listing_confirmation));
            i3.setMessage(getString(R.string.listing_sure_you_want_to_delete_property));
            i3.setCancelable(false);
            i3.setPositiveButton(getString(R.string.listing_ok), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface = new DeletePropertyRequest();
                    dialogInterface.setPropertyId(Integer.parseInt(str));
                    PropertyListingFragmentNew.this.propertyDeletingPresenter.getPropertyDeleted(PropertyListingFragmentNew.this.getString(R.string.property_deleting), dialogInterface, i, i2);
                }
            });
            i3.setNegativeButton(getString(R.string.listing_cancel), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            i3.show();
        } else {
            if (i3 == 3) {
                i = new Intent(getActivity(), AddYourPropertySteps.class);
                i.putExtra("listingPropertyId", str);
                i.putExtra("checkIntent", true);
                i.putExtra("checkPublishOrUnpublish", true);
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
