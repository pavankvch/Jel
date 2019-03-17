package com.jelsat.fragments;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.businesslogic.inbox.ReadNotificationPresenter;
import com.data.inbox.InboxNotificationData;
import com.data.inbox.ReadNotificationRequest;
import com.data.retrofit.RetrofitClient;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseDialogFragment;
import com.jelsat.customclasses.CustomTextView;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.Utils;
import com.jelsat.widgets.FancyButton;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;

public class NotificationDialogFragment extends BaseDialogFragment {
    @BindView(2131361915)
    TextView bookingId;
    @BindView(2131362076)
    LinearLayout datesLayout;
    @BindView(2131362734)
    TextView hostName;
    @BindView(2131362258)
    LinearLayout invisibleDatesLayout;
    @BindView(2131362361)
    CustomTextView noOfGuests;
    @BindView(2131362747)
    FancyButton noOfNights;
    private int position;
    private ReadNotificationPresenter presenter;
    @BindView(2131362456)
    TextView proeprtyCheckOutDate;
    @BindView(2131362455)
    TextView propertyCheckInDate;
    @BindView(2131362468)
    CircleImageView propertyImage;
    @BindView(2131362477)
    TextView propertyName;
    @BindView(2131362481)
    TextView propertyStatus;
    @BindView(2131362818)
    View view1;
    @BindView(2131362819)
    View view2;

    class ReadNotificationEvent {
        private int position;

        ReadNotificationEvent(int i) {
            this.position = i;
        }

        public int getPosition() {
            return this.position;
        }
    }

    public int getDialogFragmentLayoutId() {
        return R.layout.dialogue_notification;
    }

    @OnClick({2131361892})
    public void clickClose() {
        if (this.presenter != null) {
            EventBus.getDefault().post(new ReadNotificationEvent(this.position));
        }
        getDialog().dismiss();
    }

    public static NotificationDialogFragment newInstance(InboxNotificationData inboxNotificationData, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", i);
        bundle.putParcelable("notification_data", inboxNotificationData);
        inboxNotificationData = new NotificationDialogFragment();
        inboxNotificationData.setArguments(bundle);
        return inboxNotificationData;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        bundle = super.onCreateDialog(bundle);
        if (bundle.getWindow() != null) {
            bundle.getWindow().requestFeature(1);
        }
        bundle.setCanceledOnTouchOutside(false);
        bundle.setCancelable(false);
        return bundle;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        viewGroup = new Rect();
        requireActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(viewGroup);
        if (layoutInflater != null) {
            layoutInflater.setMinimumWidth((int) (((float) viewGroup.width()) * 0.9f));
            layoutInflater.setMinimumHeight((int) (((float) viewGroup.height()) * 1058642330));
        }
        return layoutInflater;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            InboxNotificationData inboxNotificationData = (InboxNotificationData) getArguments().getParcelable("notification_data");
            this.position = getArguments().getInt("position", 0);
            if (inboxNotificationData != null) {
                if (Integer.parseInt(inboxNotificationData.getReadNotification()) == null) {
                    this.presenter = new ReadNotificationPresenter(RetrofitClient.getAPIService());
                    bundle = new ReadNotificationRequest();
                    bundle.setId(inboxNotificationData.getId());
                    bundle.setType(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    this.presenter.readNotification(bundle);
                }
                GlideApp.with(requireActivity()).asBitmap().load(inboxNotificationData.getPayload().getData().getAttachmentUrl()).placeholder((int) R.drawable.default_logo_small).error((int) R.drawable.default_logo_small).diskCacheStrategy(DiskCacheStrategy.DATA).into(this.propertyImage);
                this.propertyCheckInDate.setText(Utils.bookingsDateFormat(inboxNotificationData.getPayload().getData().getCheckInDate()));
                this.proeprtyCheckOutDate.setText(Utils.bookingsDateFormat(inboxNotificationData.getPayload().getData().getCheckOutDate()));
                this.noOfNights.setText(String.format(Locale.getDefault(), "%d %s", new Object[]{Integer.valueOf(inboxNotificationData.getPayload().getData().getNoOfNights()), getString(R.string.nights)}));
                this.propertyName.setText(inboxNotificationData.getPayload().getTitle());
                this.propertyStatus.setText(inboxNotificationData.getPayload().getData().getBookingStatus());
                if (!(inboxNotificationData.getPayload().getData().getCode() == 1002 || inboxNotificationData.getPayload().getData().getCode() == 1003 || inboxNotificationData.getPayload().getData().getCode() == 1004 || inboxNotificationData.getPayload().getData().getCode() == 1005 || inboxNotificationData.getPayload().getData().getCode() == 1006 || inboxNotificationData.getPayload().getData().getCode() == 1007 || inboxNotificationData.getPayload().getData().getCode() == 1008)) {
                    if (inboxNotificationData.getPayload().getData().getCode() != 1009) {
                        this.invisibleDatesLayout.setVisibility(8);
                        this.hostName.setText(inboxNotificationData.getPayload().getData().getBookingHostname());
                        this.noOfGuests.setText(String.format(Locale.getDefault(), "%d %s", new Object[]{Integer.valueOf(inboxNotificationData.getPayload().getData().getNoOfGuests()), getString(R.string.guests)}));
                        this.bookingId.setText(String.format("%s : %s", new Object[]{getString(R.string.booking_id_label), inboxNotificationData.getPayload().getData().getOrderId()}));
                    }
                }
                this.invisibleDatesLayout.setVisibility(0);
                this.hostName.setText(inboxNotificationData.getPayload().getData().getBookingHostname());
                this.noOfGuests.setText(String.format(Locale.getDefault(), "%d %s", new Object[]{Integer.valueOf(inboxNotificationData.getPayload().getData().getNoOfGuests()), getString(R.string.guests)}));
                this.bookingId.setText(String.format("%s : %s", new Object[]{getString(R.string.booking_id_label), inboxNotificationData.getPayload().getData().getOrderId()}));
            }
        }
    }

    public void onDetach() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetach();
    }
}
