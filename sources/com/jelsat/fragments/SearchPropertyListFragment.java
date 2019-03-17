package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.businesslogic.propertyfavourite.IPropertyFavouriteView;
import com.businesslogic.propertyfavourite.PropertyFavouritePresenter;
import com.data.propertyfavourite.PropertyFavouriteRequest;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.searchproperty.SelectedPropertyListData;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.activities.HomeActivity;
import com.jelsat.activities.PropertyDetailActivity;
import com.jelsat.adapters.SearchPropertyAdapter;
import com.jelsat.adapters.SearchPropertyAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.events.ChangePropertyFavouriteEvent;
import com.jelsat.events.FragmentLoadedEvent;
import com.jelsat.events.PropertiesListEvent;
import com.jelsat.events.PropertyDetailEvent;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SearchPropertyListFragment extends BaseFragment implements IPropertyFavouriteView, SearchPropertyAdapter$OnListItemClickListener {
    private boolean isSaved;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private PropertyFavouritePresenter propertyFavouritePresenter;
    private List<SearchProperty> propertyList;
    @BindView(2131362479)
    RecyclerView propertyRecyclerView;
    private SearchPropertyAdapter searchPropertyAdapter;
    private SelectedPropertyListData selectedPropertyListData;

    public int getFragmentLayoutId() {
        return R.layout.fragment_search_list_property;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.propertyFavouritePresenter = new PropertyFavouritePresenter(this, RetrofitClient.getAPIService());
        initPropertyRecyclerView();
        this.noResultLayout.setVisibility(8);
        return layoutInflater;
    }

    private void initPropertyRecyclerView() {
        this.searchPropertyAdapter = new SearchPropertyAdapter(requireContext(), null, this, false);
        this.propertyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.propertyRecyclerView.setAdapter(this.searchPropertyAdapter);
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new FragmentLoadedEvent(true));
    }

    public void clickOnListItem(SearchProperty searchProperty, int i) {
        Log.e("SearchPropListFrag", "clickOnListItem() has called");
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
        int i2 = 1;
        if (isUserLoggedIn()) {
            Log.e("SearchPropertyListFrag", "setFavouriteProperty() has called");
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
        Log.e("SearchPropertyListFrag", "onSuccess() has called");
        this.searchPropertyAdapter.setPropertyItemFavourite(Integer.parseInt(propertyFavouriteResponse.getFavourite()), i, this.isSaved);
        if (Integer.parseInt(propertyFavouriteResponse.getFavourite()) == 1) {
            showToast(getString(R.string.property_got_saved));
        } else {
            showToast(getString(R.string.property_got_unsaved));
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

    private boolean isUserLoggedIn() {
        return (PrefUtils.getInstance().getUserAccessToken() == null || PrefUtils.getInstance().getCookie() == null) ? false : true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendPropertiesListSticky(PropertiesListEvent propertiesListEvent) {
        if (propertiesListEvent != null) {
            this.selectedPropertyListData = propertiesListEvent.getSelectedPropertyListData();
            this.isSaved = propertiesListEvent.getSelectedPropertyListData().isSaved();
            this.propertyList = new ArrayList();
            this.propertyList.addAll(propertiesListEvent.getSelectedPropertyListData().getPropertyList());
            this.searchPropertyAdapter.setData(this.propertyList);
            showNoResultVisibility(this.propertyList, propertiesListEvent.getSelectedPropertyListData().isSaved());
        }
    }

    private void showNoResultVisibility(List<SearchProperty> list, boolean z) {
        if (z) {
            this.noResultImage.setImageResource(R.drawable.ic_no_saved_properties);
            this.noResultTV.setText(getText(R.string.no_saved_pro_found));
        } else {
            this.noResultImage.setImageResource(R.drawable.ic_noresults_found);
            this.noResultTV.setText(getText(R.string.no_search_res_fou));
        }
        if (list == null || list.size() <= null) {
            this.noResultLayout.setVisibility(0);
            if (z) {
                this.noResultImage.setImageResource(R.drawable.ic_no_saved_properties);
                this.noResultTV.setText(getText(R.string.no_saved_pro_found));
                return;
            }
            this.noResultImage.setImageResource(R.drawable.ic_noresults_found);
            this.noResultTV.setText(getText(R.string.no_search_res_fou));
            return;
        }
        this.noResultLayout.setVisibility(true);
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
}
