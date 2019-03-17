package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.inbox.IInboxView;
import com.businesslogic.inbox.InboxPresenter;
import com.data.inbox.InboxMergeResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.activities.ChatActivity.ReadMessage;
import com.jelsat.adapters.ViewPagerAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.events.BadgeEvent;
import com.jelsat.fragments.NotificationDialogFragment.ReadNotificationEvent;
import com.jelsat.widgets.FancyButton;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DashBoardHostInboxFragment extends BaseFragment implements OnTabSelectedListener, IInboxView {
    @BindView(2131362641)
    TabLayout inboxTabs;
    private TextView messagesCount;
    private InboxMessagesFragment messagesFragment;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    private TextView notificationCount;
    private InboxNotificationsFragment notificationsFragment;
    private InboxPresenter presenter = new InboxPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362832)
    ViewPager viewpager;

    public int getFragmentLayoutId() {
        return R.layout.fragment_dashboard_inbox;
    }

    public void onTabReselected(Tab tab) {
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        getHostInboxData(false);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        setupViewPager(this.viewpager);
        initSwipeToRefresh();
        getHostInboxData(true);
        return layoutInflater;
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        this.inboxTabs.addOnTabSelectedListener(this);
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new DashBoardHostInboxFragment$1(this));
        this.swipeContainer.setColorSchemeResources(17170459, 17170452, 17170456, 17170454);
    }

    private void getHostInboxData(boolean z) {
        this.messagesCount.setVisibility(8);
        this.notificationCount.setVisibility(8);
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.viewpager.setVisibility(0);
            if (this.presenter != null) {
                this.presenter.getHostInboxData(getString(R.string.please_wait), z);
                return;
            }
        }
        this.viewpager.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showSwipeToRefresh(false);
        showToast(getString(true));
    }

    private void setupViewPager(ViewPager viewPager) {
        PagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        Fragment newInstance = InboxMessagesFragment.newInstance(false);
        this.messagesFragment = newInstance;
        viewPagerAdapter.addFragment(newInstance, getString(R.string.inbox_messages));
        newInstance = new InboxNotificationsFragment();
        this.notificationsFragment = newInstance;
        viewPagerAdapter.addFragment(newInstance, getString(R.string.inbox_notifications));
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        this.inboxTabs.setupWithViewPager(this.viewpager);
        setTabIcons();
    }

    private void setTabIcons() {
        this.inboxTabs.getTabAt(0).setCustomView(getMessagesCustomView());
        this.inboxTabs.getTabAt(1).setCustomView(getNotificationCustomView());
    }

    private View getMessagesCustomView() {
        View inflate = getLayoutInflater().inflate(R.layout.custom_inbox_tab, null);
        ((ImageView) inflate.findViewById(R.id.fragment_icon)).setImageResource(R.drawable.ic_inbox_message);
        this.messagesCount = (TextView) inflate.findViewById(R.id.fragment_count);
        TextView textView = (TextView) inflate.findViewById(R.id.fragment_title);
        textView.setText(getString(R.string.inbox_messages));
        textView.setTextColor(applyColor(R.color.white));
        return inflate;
    }

    private View getNotificationCustomView() {
        View inflate = getLayoutInflater().inflate(R.layout.custom_inbox_tab, null);
        ((ImageView) inflate.findViewById(R.id.fragment_icon)).setImageResource(R.drawable.ic_inbox_notification);
        this.notificationCount = (TextView) inflate.findViewById(R.id.fragment_count);
        ((TextView) inflate.findViewById(R.id.fragment_title)).setText(getString(R.string.inbox_notifications));
        return inflate;
    }

    private int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(requireActivity(), i);
    }

    public void onSuccess(InboxMergeResponse inboxMergeResponse) {
        if (inboxMergeResponse != null) {
            if (inboxMergeResponse.getMessagesCount() != 0) {
                this.messagesCount.setVisibility(0);
                if (inboxMergeResponse.getMessagesCount() <= 99) {
                    this.messagesCount.setText(String.valueOf(inboxMergeResponse.getMessagesCount()));
                } else {
                    this.messagesCount.setText(String.format(Locale.getDefault(), "%d%s", new Object[]{Integer.valueOf(99), "+"}));
                }
            } else {
                this.messagesCount.setVisibility(8);
            }
            if (this.messagesFragment != null) {
                this.messagesFragment.setMessagesMap(inboxMergeResponse.getMessages());
            }
            if (TextUtils.isEmpty(inboxMergeResponse.getNotificationsCount()) || inboxMergeResponse.getNotificationsCount().equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                this.notificationCount.setVisibility(8);
            } else {
                this.notificationCount.setVisibility(0);
                if (Integer.parseInt(inboxMergeResponse.getNotificationsCount()) <= 99) {
                    this.notificationCount.setText(inboxMergeResponse.getNotificationsCount());
                } else {
                    this.notificationCount.setText(String.format(Locale.getDefault(), "%d%s", new Object[]{Integer.valueOf(99), "+"}));
                }
            }
            if (this.notificationsFragment != null) {
                this.notificationsFragment.setNotificationData(inboxMergeResponse.getNotifications());
            }
            EventBus.getDefault().post(new BadgeEvent(inboxMergeResponse.getInboxCount()));
        }
    }

    public void onError(APIError aPIError, int i) {
        if (i != ErrorCodes.API_ERROR) {
            if (i == ErrorCodes.INTERNAL_SERVER_ERROR) {
                showToast(getString(R.string.internal_server_error));
            } else if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                showToast(getString(R.string.socket_time_out_error));
            } else if (i == 511) {
                showToast(getString(R.string.network_error));
            }
        } else if (!(aPIError == null || aPIError.getSeken_errors() == 0)) {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void readNotification(ReadNotificationEvent readNotificationEvent) {
        if (readNotificationEvent != null) {
            this.presenter.getHostInboxData(getString(R.string.please_wait), false);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void readMessage(ReadMessage readMessage) {
        readMessage = (ReadMessage) EventBus.getDefault().getStickyEvent(ReadMessage.class);
        if (readMessage != null) {
            this.presenter.getHostInboxData(getString(R.string.please_wait), false);
            EventBus.getDefault().removeStickyEvent(readMessage);
        }
    }

    public void onStop() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        this.inboxTabs.removeOnTabSelectedListener(this);
        super.onStop();
    }

    public void onDetach() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void onTabSelected(Tab tab) {
        tab = tab.getCustomView();
        if (tab != null) {
            ((TextView) tab.findViewById(R.id.fragment_title)).setTextColor(applyColor(R.color.white));
        }
    }

    public void onTabUnselected(Tab tab) {
        tab = tab.getCustomView();
        if (tab != null) {
            ((TextView) tab.findViewById(R.id.fragment_title)).setTextColor(applyColor(R.color.background_login));
        }
    }
}
