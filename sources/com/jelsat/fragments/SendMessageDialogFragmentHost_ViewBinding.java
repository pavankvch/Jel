package com.jelsat.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;
import com.jelsat.widgets.FancyButton;

public class SendMessageDialogFragmentHost_ViewBinding implements Unbinder {
    private SendMessageDialogFragmentHost target;
    private View view2131361961;
    private View view2131362580;

    @UiThread
    public SendMessageDialogFragmentHost_ViewBinding(final SendMessageDialogFragmentHost sendMessageDialogFragmentHost, View view) {
        this.target = sendMessageDialogFragmentHost;
        sendMessageDialogFragmentHost.msgEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.et_message, "field 'msgEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.send_button_host, "field 'sendButton' and method 'clickOnSendButton'");
        sendMessageDialogFragmentHost.sendButton = (FancyButton) Utils.castView(findRequiredView, R.id.send_button_host, "field 'sendButton'", FancyButton.class);
        this.view2131362580 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sendMessageDialogFragmentHost.clickOnSendButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.cancel_button, "method 'clickOnCancelButton'");
        this.view2131361961 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sendMessageDialogFragmentHost.clickOnCancelButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SendMessageDialogFragmentHost sendMessageDialogFragmentHost = this.target;
        if (sendMessageDialogFragmentHost == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sendMessageDialogFragmentHost.msgEditText = null;
        sendMessageDialogFragmentHost.sendButton = null;
        this.view2131362580.setOnClickListener(null);
        this.view2131362580 = null;
        this.view2131361961.setOnClickListener(null);
        this.view2131361961 = null;
    }
}
