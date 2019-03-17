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
import com.data.inbox.InboxMessageData;
import com.data.inbox.MessageData;
import com.jelsat.R;
import com.jelsat.activities.ChatActivity;
import com.jelsat.adapters.Inboxmessagesadapter.AdapterSectionRecycler;
import com.jelsat.adapters.Inboxmessagesadapter.AdapterSectionRecycler.ListItemClickListener;
import com.jelsat.adapters.Inboxmessagesadapter.SectionModel;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.StringConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InboxMessagesFragment extends BaseFragment implements ListItemClickListener {
    private AdapterSectionRecycler adapterRecycler;
    private boolean isGuest;
    private Map<String, List<MessageData>> messagesMap;
    @BindView(2131362371)
    TextView noResultTextView;
    @BindView(2131362498)
    RecyclerView recyclerView;

    public int getFragmentLayoutId() {
        return R.layout.fragment_recyclerview;
    }

    public static InboxMessagesFragment newInstance(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(StringConstants.FROM_GUEST, z);
        z = new InboxMessagesFragment();
        z.setArguments(bundle);
        return z;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (getArguments() != null) {
            this.isGuest = getArguments().getBoolean(StringConstants.FROM_GUEST, false);
        }
        setUpRecyclerView();
        return layoutInflater;
    }

    private void setUpRecyclerView() {
        LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.addItemDecoration(new DividerItemDecoration(this.recyclerView.getContext(), linearLayoutManager.getOrientation()));
        this.adapterRecycler = new AdapterSectionRecycler(getActivity(), null, this);
        this.recyclerView.setAdapter(this.adapterRecycler);
    }

    public void setMessagesMap(List<InboxMessageData> list) {
        int i = 0;
        if (list == null || list.size() <= 0) {
            this.noResultTextView.setVisibility(0);
            list = ContextCompat.getDrawable(getContext(), R.drawable.ic_messages_unselect);
            list.setBounds(0, 0, 150, 150);
            this.noResultTextView.setCompoundDrawables(null, list, null, null);
            this.noResultTextView.setText(getString(R.string.no_messages_found));
            return;
        }
        if (this.messagesMap == null) {
            this.messagesMap = new LinkedHashMap();
        } else {
            this.messagesMap.clear();
        }
        while (i < list.size()) {
            prepareData((InboxMessageData) list.get(i));
            i++;
        }
        populateRecyclerView(this.messagesMap);
        this.noResultTextView.setVisibility(8);
    }

    private void prepareData(InboxMessageData inboxMessageData) {
        this.messagesMap.put(inboxMessageData.getDate(), inboxMessageData.getData());
    }

    private void populateRecyclerView(Map<String, List<MessageData>> map) {
        List arrayList = new ArrayList();
        for (String str : map.keySet()) {
            arrayList.add(new SectionModel(str, (List) map.get(str)));
        }
        this.adapterRecycler.notifyDataChanged(arrayList);
    }

    public void clickOnListItem(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.CONVERSATION_ID, str);
        bundle.putInt(StringConstants.READ_MESSAGE, Integer.parseInt(str2));
        bundle.putBoolean(StringConstants.FROM_GUEST, this.isGuest);
        goToActivity(ChatActivity.class, bundle);
    }
}
