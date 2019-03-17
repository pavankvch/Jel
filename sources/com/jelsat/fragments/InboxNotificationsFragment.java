package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.data.inbox.InboxNotificationData;
import com.data.inbox.InboxNotificationSectionModel;
import com.jelsat.R;
import com.jelsat.adapters.notificationsadapter.NotificationSectionModel;
import com.jelsat.adapters.notificationsadapter.NotificationsSectionRecyclerAdapter;
import com.jelsat.adapters.notificationsadapter.NotificationsSectionRecyclerAdapter.ListItemClickListener;
import com.jelsat.baseuiframework.BaseFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InboxNotificationsFragment extends BaseFragment implements ListItemClickListener {
    private NotificationsSectionRecyclerAdapter adapterRecycler;
    @BindView(2131362371)
    TextView noResultTextView;
    private Map<String, List<InboxNotificationData>> notificationsMap;
    @BindView(2131362498)
    RecyclerView recyclerView;

    public int getFragmentLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        setUpRecyclerView();
        return layoutInflater;
    }

    private void setUpRecyclerView() {
        LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this.recyclerView.getContext(), linearLayoutManager.getOrientation()));
        this.adapterRecycler = new NotificationsSectionRecyclerAdapter(getActivity(), null, this);
        this.recyclerView.setAdapter(this.adapterRecycler);
    }

    private void populateRecyclerView(Map<String, List<InboxNotificationData>> map) {
        List arrayList = new ArrayList();
        for (String str : map.keySet()) {
            arrayList.add(new NotificationSectionModel(str, (List) map.get(str)));
        }
        this.adapterRecycler.notifyDataChanged(arrayList);
    }

    private void prepareData(InboxNotificationSectionModel inboxNotificationSectionModel) {
        this.notificationsMap.put(inboxNotificationSectionModel.getMonth(), inboxNotificationSectionModel.getData());
    }

    public void setNotificationData(List<InboxNotificationSectionModel> list) {
        int i = 0;
        if (list == null || list.size() <= 0) {
            this.noResultTextView.setVisibility(0);
            list = ContextCompat.getDrawable(getContext(), R.drawable.ic_notifications_unselect);
            list.setBounds(0, 0, 150, 150);
            this.noResultTextView.setCompoundDrawables(null, list, null, null);
            this.noResultTextView.setText(getString(R.string.no_notifications_found));
            return;
        }
        if (this.notificationsMap == null) {
            this.notificationsMap = new LinkedHashMap();
        } else {
            this.notificationsMap.clear();
        }
        while (i < list.size()) {
            prepareData((InboxNotificationSectionModel) list.get(i));
            i++;
        }
        populateRecyclerView(this.notificationsMap);
        this.noResultTextView.setVisibility(8);
    }

    public void clickOnListItem(InboxNotificationData inboxNotificationData, int i) {
        NotificationDialogFragment.newInstance(inboxNotificationData, i).show(getChildFragmentManager(), "notification");
    }
}
