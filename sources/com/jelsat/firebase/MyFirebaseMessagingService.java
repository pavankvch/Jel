package com.jelsat.firebase;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.jelsat.activities.DashBoardActivity;
import com.jelsat.activities.EditProfileActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.NotificationUtils;
import java.util.Map.Entry;
import org.greenrobot.eventbus.EventBus;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingService";

    public class EmailStatus {
        private int statusCode;

        public EmailStatus(int i) {
            this.statusCode = i;
        }

        public int getStatusCode() {
            return this.statusCode;
        }
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder("From: ");
        stringBuilder.append(remoteMessage.getFrom());
        Log.e(str, stringBuilder.toString());
        String str2 = null;
        if (remoteMessage.getNotification() != null) {
            str = TAG;
            stringBuilder = new StringBuilder("Notification Body: ");
            stringBuilder.append(remoteMessage.getNotification().getBody());
            Log.e(str, stringBuilder.toString());
            str2 = remoteMessage.getNotification().getTitle();
            str = remoteMessage.getNotification().getBody();
        } else {
            str = null;
        }
        if (remoteMessage.getData().size() > 0) {
            String str3 = TAG;
            StringBuilder stringBuilder2 = new StringBuilder("Data Payload: ");
            stringBuilder2.append(remoteMessage.getData().toString());
            Log.e(str3, stringBuilder2.toString());
            str3 = TAG;
            stringBuilder2 = new StringBuilder("Message data payload: ");
            stringBuilder2.append(remoteMessage.getData());
            Log.e(str3, stringBuilder2.toString());
            for (Entry entry : remoteMessage.getData().entrySet()) {
                String str4 = TAG;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append((String) entry.getKey());
                stringBuilder3.append("   ");
                stringBuilder3.append((String) entry.getValue());
                Log.e(str4, stringBuilder3.toString());
            }
            try {
                handleDataMessage(remoteMessage, str2, str);
            } catch (RemoteMessage remoteMessage2) {
                str = TAG;
                stringBuilder = new StringBuilder("Exception: ");
                stringBuilder.append(remoteMessage2.getMessage());
                Log.e(str, stringBuilder.toString());
            }
        }
    }

    private void handleDataMessage(RemoteMessage remoteMessage, String str, String str2) {
        try {
            Intent intent;
            remoteMessage = remoteMessage.getData();
            String str3 = (String) remoteMessage.get("attachment-url");
            String str4 = (String) remoteMessage.get("code");
            String str5 = (String) remoteMessage.get(StringConstants.PROPERTY_ID);
            String str6 = (String) remoteMessage.get("role");
            Log.w(TAG, remoteMessage.toString());
            remoteMessage = TAG;
            StringBuilder stringBuilder = new StringBuilder("title: ");
            stringBuilder.append(str);
            Log.e(remoteMessage, stringBuilder.toString());
            remoteMessage = TAG;
            stringBuilder = new StringBuilder("message: ");
            stringBuilder.append(str2);
            Log.e(remoteMessage, stringBuilder.toString());
            remoteMessage = TAG;
            stringBuilder = new StringBuilder("imageUrl: ");
            stringBuilder.append(str3);
            Log.e(remoteMessage, stringBuilder.toString());
            remoteMessage = TAG;
            stringBuilder = new StringBuilder("code: ");
            stringBuilder.append(str4);
            Log.e(remoteMessage, stringBuilder.toString());
            remoteMessage = TAG;
            stringBuilder = new StringBuilder("propertyId: ");
            stringBuilder.append(str5);
            Log.e(remoteMessage, stringBuilder.toString());
            remoteMessage = TaskStackBuilder.create(getApplicationContext());
            boolean z = true;
            if (!(Integer.parseInt(str4) == 1001 || Integer.parseInt(str4) == 1011)) {
                if (Integer.parseInt(str4) != 1012) {
                    intent = new Intent(this, DashBoardActivity.class);
                    intent.putExtra(StringConstants.FROM_PAYMENT_SUCCESS, true);
                    if (TextUtils.isEmpty(str6) || Integer.parseInt(str6) != 0) {
                        z = false;
                    }
                    intent.putExtra(StringConstants.FROM_GUEST, z);
                    remoteMessage.addNextIntentWithParentStack(intent);
                    remoteMessage = remoteMessage.getPendingIntent(0, 134217728);
                    if (!(Integer.parseInt(str4) == 1001 || Integer.parseInt(str4) == 1011)) {
                        if (Integer.parseInt(str4) != 1012) {
                            if (TextUtils.isEmpty(str3)) {
                                showNotificationMessageWithBigImage(getApplicationContext(), str, str2, remoteMessage, str3);
                            } else {
                                showNotificationMessage(getApplicationContext(), str, str2, remoteMessage);
                            }
                        }
                    }
                    showNotificationMessage(getApplicationContext(), str, str2, remoteMessage);
                    EventBus.getDefault().postSticky(new EmailStatus(Integer.parseInt(str4)));
                }
            }
            intent = new Intent(this, EditProfileActivity.class);
            intent.putExtra(StringConstants.FROM_FCM, true);
            remoteMessage.addNextIntentWithParentStack(intent);
            remoteMessage = remoteMessage.getPendingIntent(0, 134217728);
            if (Integer.parseInt(str4) != 1012) {
                showNotificationMessage(getApplicationContext(), str, str2, remoteMessage);
                EventBus.getDefault().postSticky(new EmailStatus(Integer.parseInt(str4)));
            } else if (TextUtils.isEmpty(str3)) {
                showNotificationMessageWithBigImage(getApplicationContext(), str, str2, remoteMessage, str3);
            } else {
                showNotificationMessage(getApplicationContext(), str, str2, remoteMessage);
            }
        } catch (RemoteMessage remoteMessage2) {
            remoteMessage2.printStackTrace();
            str = TAG;
            str2 = new StringBuilder("Exception: ");
            str2.append(remoteMessage2.getMessage());
            Log.e(str, str2.toString());
        }
    }

    private void showNotificationMessage(Context context, String str, String str2, PendingIntent pendingIntent) {
        new NotificationUtils(context).showNotificationMessage(str, str2, pendingIntent);
    }

    private void showNotificationMessageWithBigImage(Context context, String str, String str2, PendingIntent pendingIntent, String str3) {
        new NotificationUtils(context).showNotificationMessage(str, str2, pendingIntent, str3);
    }
}
