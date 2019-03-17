package com.jelsat.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.propertyfavourite.IPropertyFavouriteView;
import com.businesslogic.propertyfavourite.PropertyFavouritePresenter;
import com.businesslogic.savedproperties.ISavedPropertiesView;
import com.businesslogic.savedproperties.SavedPropertiesPresenter;
import com.data.propertyfavourite.PropertyFavouriteRequest;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.activities.PropertyDetailActivity;
import com.jelsat.adapters.SearchPropertyAdapter;
import com.jelsat.adapters.SearchPropertyAdapter$OnListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.events.ChangeFavouriteIconEvent;
import com.jelsat.events.PropertyDetailEvent;
import com.jelsat.widgets.FancyButton;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DashboardSavedFragment extends BaseFragment implements IPropertyFavouriteView, ISavedPropertiesView, SearchPropertyAdapter$OnListItemClickListener {
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private SavedPropertiesPresenter propertiesPresenter = new SavedPropertiesPresenter(this, RetrofitClient.getAPIService());
    private PropertyFavouritePresenter propertyFavouritePresenter = new PropertyFavouritePresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362542)
    RecyclerView savedPropertiesRecyclerView;
    private SearchPropertyAdapter searchPropertyAdapter;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;

    public int getFragmentLayoutId() {
        return R.layout.fragment_dashboard_saved;
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getSavedPropertiesData(false);
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        initSwipeToRefresh();
        initSavedPropertiesRecyclerView();
        getSavedPropertiesData(true);
        return layoutInflater;
    }

    private void getSavedPropertiesData(boolean z) {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            if (this.propertiesPresenter != null) {
                this.propertiesPresenter.getSavedProperties(getString(R.string.getting_saved_property), z);
                return;
            }
        }
        showSwipeToRefresh(false);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        this.retryButton.setVisibility(0);
        showToast(getString(true));
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new DashboardSavedFragment$1(this));
        this.swipeContainer.setColorSchemeResources(17170459, 17170452, 17170456, 17170454);
    }

    private void initSavedPropertiesRecyclerView() {
        this.savedPropertiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.searchPropertyAdapter = new SearchPropertyAdapter(requireActivity(), null, this, false);
        this.savedPropertiesRecyclerView.setAdapter(this.searchPropertyAdapter);
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void clickOnListItem(SearchProperty searchProperty, int i) {
        i = new PropertyDetailEvent(searchProperty);
        i.setShowMessage(true);
        EventBus.getDefault().postSticky(i);
        startActivity(new Intent(getActivity(), PropertyDetailActivity.class));
    }

    public void setFavouriteProperty(SearchProperty searchProperty, int i) {
        PropertyFavouriteRequest propertyFavouriteRequest = new PropertyFavouriteRequest();
        propertyFavouriteRequest.setPropertyId(searchProperty.getPropertyId());
        propertyFavouriteRequest.setStatus(String.valueOf(searchProperty.getFavourite() == null ? true : null));
        this.propertyFavouritePresenter.setPropertyFavouriteToUser(getString(R.string.please_wait), propertyFavouriteRequest, i);
    }

    public void onSuccess(List<SearchProperty> list) {
        this.searchPropertyAdapter.setData(list);
        showNoResultVisibility(list);
    }

    public void onError(String str, int i) {
        if (i != ErrorCodes.API_ERROR) {
            if (i == ErrorCodes.INTERNAL_SERVER_ERROR) {
                str = getString(R.string.internal_server_error);
            } else if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                str = getString(R.string.socket_time_out_error);
            } else if (i != 511) {
                str = getString(R.string.general_api_error_msg);
            } else {
                str = getString(R.string.network_error);
            }
        }
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_close_red);
        this.noResultTV.setText(str);
        this.retryButton.setVisibility(0);
        this.searchPropertyAdapter.setData(0);
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    private void showNoResultVisibility(List<SearchProperty> list) {
        this.noResultImage.setImageResource(R.drawable.ic_no_saved_properties);
        this.noResultTV.setText(getText(R.string.no_saved_pro_found));
        if (list == null || list.size() <= null) {
            this.noResultLayout.setVisibility(0);
            this.retryButton.setVisibility(8);
            return;
        }
        this.noResultLayout.setVisibility(8);
    }

    public void onSuccess(PropertyFavouriteResponse propertyFavouriteResponse, int i) {
        this.searchPropertyAdapter.setPropertyItemFavourite(Integer.parseInt(propertyFavouriteResponse.getFavourite()), i, true);
        if (Integer.parseInt(propertyFavouriteResponse.getFavourite()) == 1) {
            showToast(getString(R.string.property_got_saved));
        } else {
            showToast(getString(R.string.property_got_unsaved));
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void changePropertyFavourite(ChangeFavouriteIconEvent changeFavouriteIconEvent) {
        if (changeFavouriteIconEvent != null) {
            if (!(this.searchPropertyAdapter == null || changeFavouriteIconEvent.getFavourite() == 1)) {
                this.searchPropertyAdapter.changePropertyBasedOnFavourite(changeFavouriteIconEvent.getPropertyId(), changeFavouriteIconEvent.getFavourite(), true);
            }
            EventBus.getDefault().removeStickyEvent(changeFavouriteIconEvent);
        }
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        dismissProgress();
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onStop();
    }

    public void onDetach() {
        if (this.propertiesPresenter != null) {
            this.propertiesPresenter.unSubscribeDisposables();
        }
        if (this.propertyFavouritePresenter != null) {
            this.propertyFavouritePresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
