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

public class SendMessageDialogFragment_ViewBinding implements Unbinder {
    private SendMessageDialogFragment target;
    private View view2131361961;
    private View view2131362579;

    @UiThread
    public SendMessageDialogFragment_ViewBinding(final SendMessageDialogFragment sendMessageDialogFragment, View view) {
        this.target = sendMessageDialogFragment;
        sendMessageDialogFragment.msgEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.et_message, "field 'msgEditText'", EditText.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.send_button, "field 'sendButton' and method 'clickOnSendButton'");
        sendMessageDialogFragment.sendButton = (FancyButton) Utils.castView(findRequiredView, R.id.send_button, "field 'sendButton'", FancyButton.class);
        this.view2131362579 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sendMessageDialogFragment.clickOnSendButton();
            }
        });
        view = Utils.findRequiredView(view, R.id.cancel_button, "method 'clickOnCancelButton'");
        this.view2131361961 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sendMessageDialogFragment.clickOnCancelButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SendMessageDialogFragment sendMessageDialogFragment = this.target;
        if (sendMessageDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sendMessageDialogFragment.msgEditText = null;
        sendMessageDialogFragment.sendButton = null;
        this.view2131362579.setOnClickListener(null);
        this.view2131362579 = null;
        this.view2131361961.setOnClickListener(null);
        this.view2131361961 = null;
    }
}
