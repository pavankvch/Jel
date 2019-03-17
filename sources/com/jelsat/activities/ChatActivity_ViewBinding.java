package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class ChatActivity_ViewBinding implements Unbinder {
    private ChatActivity target;
    private View view2131361892;
    private View view2131362250;
    private View view2131362415;
    private View view2131362517;

    @UiThread
    public ChatActivity_ViewBinding(ChatActivity chatActivity) {
        this(chatActivity, chatActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChatActivity_ViewBinding(final ChatActivity chatActivity, View view) {
        this.target = chatActivity;
        chatActivity.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.list_messages, "field 'recyclerView'", RecyclerView.class);
        chatActivity.msgEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.et_msg, "field 'msgEditText'", EditText.class);
        chatActivity.headingTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heading, "field 'headingTextView'", TextView.class);
        chatActivity.img_profile = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_profile, "field 'img_profile'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.phone_call, "field 'phoneCall' and method 'phoneCall'");
        chatActivity.phoneCall = (ImageButton) Utils.castView(findRequiredView, R.id.phone_call, "field 'phoneCall'", ImageButton.class);
        this.view2131362415 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                chatActivity.phoneCall();
            }
        });
        chatActivity.swipeRefreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(view, R.id.swipeContainer, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
        chatActivity.noResultTV = (TextView) Utils.findRequiredViewAsType(view, R.id.no_result_textView, "field 'noResultTV'", TextView.class);
        chatActivity.noResultLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.no_result_layout, "field 'noResultLayout'", LinearLayout.class);
        chatActivity.noResultImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.no_result_image, "field 'noResultImage'", ImageView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.retry_button, "field 'retryButton' and method 'clickOnRetryButton'");
        chatActivity.retryButton = (FancyButton) Utils.castView(findRequiredView, R.id.retry_button, "field 'retryButton'", FancyButton.class);
        this.view2131362517 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                chatActivity.clickOnRetryButton();
            }
        });
        chatActivity.chatLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.chat_layout, "field 'chatLayout'", LinearLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                chatActivity.clickOnBack();
            }
        });
        view = Utils.findRequiredView(view, R.id.img_send, "method 'sendMessage'");
        this.view2131362250 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                chatActivity.sendMessage();
            }
        });
    }

    @CallSuper
    public void unbind() {
        ChatActivity chatActivity = this.target;
        if (chatActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        chatActivity.recyclerView = null;
        chatActivity.msgEditText = null;
        chatActivity.headingTextView = null;
        chatActivity.img_profile = null;
        chatActivity.phoneCall = null;
        chatActivity.swipeRefreshLayout = null;
        chatActivity.noResultTV = null;
        chatActivity.noResultLayout = null;
        chatActivity.noResultImage = null;
        chatActivity.retryButton = null;
        chatActivity.chatLayout = null;
        this.view2131362415.setOnClickListener(null);
        this.view2131362415 = null;
        this.view2131362517.setOnClickListener(null);
        this.view2131362517 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131362250.setOnClickListener(null);
        this.view2131362250 = null;
    }
}
