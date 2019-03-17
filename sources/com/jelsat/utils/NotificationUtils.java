package com.jelsat.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.app.NotificationManagerCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import com.jelsat.R;
import com.jelsat.firebase.Config;
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationUtils {
    private Context mContext;

    public NotificationUtils(Context context) {
        this.mContext = context;
    }

    public void showNotificationMessage(String str, String str2, PendingIntent pendingIntent) {
        showNotificationMessage(str, str2, pendingIntent, null);
    }

    public void showNotificationMessage(String str, String str2, PendingIntent pendingIntent, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            int notificationIcon = getNotificationIcon();
            Builder builder = new Builder(this.mContext, Config.ANDROID_CHANNEL_ID);
            Uri defaultUri = RingtoneManager.getDefaultUri(2);
            if (TextUtils.isEmpty(str3)) {
                showSmallNotification(builder, notificationIcon, str, str2, pendingIntent, defaultUri);
            } else if (str3.length() > 4 && Patterns.WEB_URL.matcher(str3).matches()) {
                Bitmap bitmapFromURL = getBitmapFromURL(str3);
                if (bitmapFromURL != null) {
                    showBigNotification(bitmapFromURL, builder, notificationIcon, str, str2, pendingIntent, defaultUri);
                } else {
                    showSmallNotification(builder, notificationIcon, str, str2, pendingIntent, defaultUri);
                }
            }
        }
    }

    private void showSmallNotification(Builder builder, int i, String str, String str2, PendingIntent pendingIntent, Uri uri) {
        Style inboxStyle = new InboxStyle();
        inboxStyle.addLine(str2);
        NotificationManagerCompat.from(this.mContext).notify(1002, builder.setSmallIcon(i).setTicker(str).setAutoCancel(1).setContentTitle(str).setContentIntent(pendingIntent).setSound(uri).setColor(this.mContext.getResources().getColor(R.color.app_background)).setStyle(inboxStyle).setContentText(str2).build());
    }

    private void showBigNotification(Bitmap bitmap, Builder builder, int i, String str, String str2, PendingIntent pendingIntent, Uri uri) {
        Style bigPictureStyle = new BigPictureStyle();
        bigPictureStyle.setBigContentTitle(str);
        bigPictureStyle.setSummaryText(Html.fromHtml(str2).toString());
        bigPictureStyle.bigPicture(bitmap);
        bigPictureStyle.bigLargeIcon(null);
        NotificationManagerCompat.from(this.mContext).notify(1001, builder.setSmallIcon(i).setTicker(str).setAutoCancel(1).setContentTitle(str).setContentIntent(pendingIntent).setSound(uri).setColor(this.mContext.getResources().getColor(R.color.app_background)).setStyle(bigPictureStyle).setLargeIcon(bitmap).setContentText(str2).build());
    }

    public Bitmap getBitmapFromURL(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        } catch (String str2) {
            str2.printStackTrace();
            return null;
        }
    }

    public static boolean isAppIsInBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        boolean z = true;
        if (VERSION.SDK_INT > 20) {
            for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.importance == 100) {
                    boolean z2 = z;
                    for (String equals : runningAppProcessInfo.pkgList) {
                        if (equals.equals(context.getPackageName())) {
                            z2 = false;
                        }
                    }
                    z = z2;
                }
            }
            return z;
        } else if (((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity.getPackageName().equals(context.getPackageName()) != null) {
            return false;
        } else {
            return true;
        }
    }

    public static void clearNotifications(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancelAll();
    }

    private int getNotificationIcon() {
        return (VERSION.SDK_INT >= 21 ? 1 : null) != null ? R.drawable.notification_icon_new1 : R.mipmap.ic_app_icon;
    }
}
