package com.jelsat.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.businesslogic.messages.IMessagesView;
import com.businesslogic.messages.MessagesPresenter;
import com.data.inbox.MessageData;
import com.data.inbox.MessageHistoryRequest;
import com.data.inbox.SendMessageRequest;
import com.data.messagehistory.MessageChat;
import com.data.messagehistory.MessagesData;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIConstants;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.conversationsadapter.ConversationsSectionModel;
import com.jelsat.adapters.conversationsadapter.ConversationsSectionRecyclerAdapter;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.GlideApp;
import com.jelsat.widgets.FancyButton;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;

public class ChatActivity extends BaseAppCompactActivity implements IMessagesView {
    private ConversationsSectionRecyclerAdapter adapter;
    @BindView(2131361985)
    LinearLayout chatLayout;
    private String conversationId;
    private String guestMobileNo;
    @BindView(2131362733)
    TextView headingTextView;
    private String hostMobileNo;
    @BindView(2131362248)
    ImageView img_profile;
    private boolean isGuest;
    private Map<String, List<MessageData>> messagesData;
    private MessagesPresenter messagesPresenter = new MessagesPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362144)
    EditText msgEditText;
    @BindView(2131362369)
    ImageView noResultImage;
    @BindView(2131362370)
    LinearLayout noResultLayout;
    @BindView(2131362371)
    TextView noResultTV;
    @BindView(2131362415)
    ImageButton phoneCall;
    private int readMessage;
    @BindView(2131362304)
    RecyclerView recyclerView;
    @BindView(2131362517)
    FancyButton retryButton;
    @BindView(2131362636)
    SwipeRefreshLayout swipeRefreshLayout;

    public class ReadMessage {
    }

    public int getActivityLayout() {
        return R.layout.activity_chat;
    }

    public void onSuccess() {
    }

    @OnClick({2131362517})
    public void clickOnRetryButton() {
        initNetWorkLayout(false);
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        if (this.readMessage == 0) {
            EventBus.getDefault().postSticky(new ReadMessage());
        }
        finish();
    }

    @OnClick({2131362415})
    public void phoneCall() {
        if (isPermissionGranted()) {
            call_action();
        }
    }

    private void call_action() {
        Intent intent = new Intent("android.intent.action.CALL");
        if (ActivityCompat.checkSelfPermission(this, "android.permission.CALL_PHONE") == 0) {
            StringBuilder stringBuilder;
            if (this.isGuest) {
                stringBuilder = new StringBuilder("tel:+");
                stringBuilder.append(this.hostMobileNo);
                intent.setData(Uri.parse(stringBuilder.toString()));
                startActivity(intent);
                return;
            }
            stringBuilder = new StringBuilder("tel:+");
            stringBuilder.append(this.guestMobileNo);
            intent.setData(Uri.parse(stringBuilder.toString()));
            startActivity(intent);
        }
    }

    public boolean isPermissionGranted() {
        if (VERSION.SDK_INT < 23) {
            Log.v("TAG", "Permission is granted");
            return true;
        } else if (checkSelfPermission("android.permission.CALL_PHONE") == 0) {
            Log.v("TAG", "Permission is granted");
            return true;
        } else {
            Log.v("TAG", "Permission is revoked");
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CALL_PHONE"}, 1);
            return false;
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(getApplicationContext(), "Permission denied", 0).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "Permission granted", 0).show();
            call_action();
        }
    }

    @OnClick({2131362250})
    public void sendMessage() {
        if (TextUtils.isEmpty(this.msgEditText.getText().toString().trim())) {
            showToast(getString(R.string.enter_your_meassage));
        } else {
            send(this.msgEditText.getText().toString().trim());
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.img_profile.setVisibility(0);
        this.phoneCall.setVisibility(0);
        this.msgEditText.clearFocus();
        if (getIntent() != null) {
            this.conversationId = getIntent().getStringExtra(StringConstants.CONVERSATION_ID);
            this.isGuest = getIntent().getBooleanExtra(StringConstants.FROM_GUEST, false);
            this.readMessage = getIntent().getIntExtra(StringConstants.READ_MESSAGE, 0);
        }
        initSwipeToRefresh();
        setUpRecyclerView();
        initNetWorkLayout(true);
    }

    private void initNetWorkLayout(boolean z) {
        if (isNetworkConnected()) {
            this.noResultLayout.setVisibility(8);
            this.chatLayout.setVisibility(0);
            if (this.messagesPresenter != null) {
                MessageHistoryRequest messageHistoryRequest = new MessageHistoryRequest();
                messageHistoryRequest.setConversationId(this.conversationId);
                if (this.isGuest) {
                    this.messagesPresenter.guestMessageHistory(messageHistoryRequest, z);
                    return;
                } else {
                    this.messagesPresenter.hostMessageHistory(messageHistoryRequest, z);
                    return;
                }
            }
        }
        showSwipeToRefresh(false);
        this.chatLayout.setVisibility(8);
        this.noResultLayout.setVisibility(0);
        this.noResultImage.setImageResource(R.drawable.ic_nointernet);
        this.noResultTV.setText(getString(R.string.error_message_network));
        showToast(getString(true));
    }

    private void initSwipeToRefresh() {
        this.swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                ChatActivity.this.initNetWorkLayout(false);
            }
        });
        this.swipeRefreshLayout.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    private void setUpRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new ConversationsSectionRecyclerAdapter(this, null);
        this.recyclerView.setAdapter(this.adapter);
    }

    private void send(String str) {
        hideKeyboard();
        SendMessageRequest sendMessageRequest = new SendMessageRequest();
        sendMessageRequest.setMessage(str);
        sendMessageRequest.setConversationId(this.conversationId);
        if (this.isGuest != null) {
            sendMessageRequest.setRole("guest");
        } else {
            sendMessageRequest.setRole(APIConstants.BECOME_HOST);
        }
        this.messagesPresenter.createMessage(sendMessageRequest);
    }

    private void prepareData(MessageChat messageChat) {
        this.messagesData.put(messageChat.getDate(), messageChat.getChatData());
    }

    private void populateRecyclerView(int i) {
        List arrayList = new ArrayList();
        for (String str : this.messagesData.keySet()) {
            arrayList.add(new ConversationsSectionModel(str, (List) this.messagesData.get(str)));
        }
        this.adapter.notifyDataChanged(arrayList);
        this.recyclerView.scrollToPosition(i);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeRefreshLayout != null) {
            this.swipeRefreshLayout.setRefreshing(z);
        }
    }

    public void onMessageSentSuccess(MessagesData messagesData) {
        this.msgEditText.setText("");
        if (messagesData != null) {
            for (int i = 0; i < messagesData.getMessageChats().size(); i++) {
                prepareData((MessageChat) messagesData.getMessageChats().get(i));
            }
            populateRecyclerView(messagesData.getMessageChats().size());
        }
    }

    public void onGetMessagesHistory(MessagesData messagesData) {
        if (messagesData != null) {
            if (messagesData.getUserDetails() != null) {
                this.guestMobileNo = messagesData.getUserDetails().getGuestMobileNo();
                this.hostMobileNo = messagesData.getUserDetails().getHostMobileNo();
                this.headingTextView.setText(messagesData.getUserDetails().getName());
                if (this.isGuest) {
                    if (this.hostMobileNo.length() <= 0) {
                        this.phoneCall.setVisibility(4);
                    }
                } else if (this.guestMobileNo.length() <= 0) {
                    this.phoneCall.setVisibility(4);
                }
                GlideApp.with((FragmentActivity) this).asBitmap().load(messagesData.getUserDetails().getOwnerImage()).placeholder((int) R.drawable.default_logo_small).error((int) R.drawable.default_logo_small).diskCacheStrategy(DiskCacheStrategy.DATA).into(this.img_profile);
            }
            if (this.messagesData == null) {
                this.messagesData = new LinkedHashMap();
            } else {
                this.messagesData.clear();
            }
            for (int i = 0; i < messagesData.getMessageChats().size(); i++) {
                prepareData((MessageChat) messagesData.getMessageChats().get(i));
            }
            populateRecyclerView(messagesData.getMessageChats().size());
        }
    }

    protected void onStop() {
        if (this.swipeRefreshLayout != null) {
            this.swipeRefreshLayout.setRefreshing(false);
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.messagesPresenter != null) {
            this.messagesPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    public void onBackPressed() {
        if (this.readMessage == 0) {
            EventBus.getDefault().postSticky(new ReadMessage());
        }
        super.onBackPressed();
    }
}
