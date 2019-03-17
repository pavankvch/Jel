package com.jelsat.fragments;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.messages.IMessagesView;
import com.businesslogic.messages.MessagesPresenter;
import com.data.inbox.SendMessageRequest;
import com.data.messagehistory.MessagesData;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseDialogFragment;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import java.util.Locale;

public class SendMessageDialogFragment extends BaseDialogFragment implements IMessagesView {
    private MessagesPresenter messagesPresenter = new MessagesPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362143)
    EditText msgEditText;
    private String orderId;
    private String propertyId;
    @BindView(2131362579)
    FancyButton sendButton;

    public int getDialogFragmentLayoutId() {
        return R.layout.dialog_send_message;
    }

    public void onGetMessagesHistory(MessagesData messagesData) {
    }

    public void onMessageSentSuccess(MessagesData messagesData) {
    }

    public void showSwipeToRefresh(boolean z) {
    }

    @OnClick({2131361961})
    public void clickOnCancelButton() {
        Utils.hideKeyboard(getActivity());
        dismiss();
    }

    @OnClick({2131362579})
    public void clickOnSendButton() {
        if (this.msgEditText.getText().toString().trim().length() > 10) {
            if (isNetworkConnected()) {
                Utils.hideKeyboard(getActivity());
                SendMessageRequest sendMessageRequest = new SendMessageRequest();
                sendMessageRequest.setMessage(this.msgEditText.getText().toString().trim());
                sendMessageRequest.setPropertyId(this.propertyId);
                sendMessageRequest.setOrderId(this.orderId);
                StringBuilder stringBuilder = new StringBuilder("ClickOnSendButton() has called where propertyId : ");
                stringBuilder.append(this.propertyId);
                stringBuilder.append("& booking Id : ");
                stringBuilder.append(this.orderId);
                Log.e("SendMessageDialogFrag", stringBuilder.toString());
                this.messagesPresenter.sendMessage(sendMessageRequest);
                return;
            }
            showToast(getString(R.string.error_message_network));
        }
    }

    public static SendMessageDialogFragment newInstance(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder("newInstance() has called where propertyId : ");
        stringBuilder.append(str);
        stringBuilder.append("& booking Id : ");
        stringBuilder.append(str2);
        Log.e("SendMessageDialogFrag", stringBuilder.toString());
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.PROPERTY_ID, str);
        bundle.putString(StringConstants.ORDER_ID, str2);
        str = new SendMessageDialogFragment();
        str.setArguments(bundle);
        return str;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        return bundle;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.msgEditText.setImeOptions(6);
        this.msgEditText.setRawInputType(1);
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
            this.sendButton.setGravity(GravityCompat.START);
        }
        if (getArguments() != null) {
            this.propertyId = getArguments().getString(StringConstants.PROPERTY_ID, "");
            this.orderId = getArguments().getString(StringConstants.ORDER_ID, "");
        }
        viewGroup = new Rect();
        requireActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(viewGroup);
        if (layoutInflater != null) {
            layoutInflater.setMinimumWidth((int) (((float) viewGroup.width()) * 0.9f));
            layoutInflater.setMinimumHeight((int) (((float) viewGroup.height()) * 1058642330));
        }
        this.msgEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (SendMessageDialogFragment.this.msgEditText.getText().toString().trim().length() > 10) {
                    SendMessageDialogFragment.this.sendButton.setClickable(true);
                    SendMessageDialogFragment.this.sendButton.setEnabled(true);
                    SendMessageDialogFragment.this.sendButton.setFocusable(true);
                    return;
                }
                SendMessageDialogFragment.this.sendButton.setClickable(false);
                SendMessageDialogFragment.this.sendButton.setEnabled(false);
                SendMessageDialogFragment.this.sendButton.setFocusable(false);
            }
        });
        return layoutInflater;
    }

    public void onSuccess() {
        showToast(getString(R.string.message_sent_success_label));
        dismiss();
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onStop() {
        dismissProgress();
        super.onStop();
    }

    public void onDetach() {
        if (this.messagesPresenter != null) {
            this.messagesPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
