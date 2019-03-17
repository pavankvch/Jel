package com.data.inbox;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InboxNotificationPayload implements Parcelable {
    public static final Creator<InboxNotificationPayload> CREATOR = new Creator<InboxNotificationPayload>() {
        public final InboxNotificationPayload createFromParcel(Parcel parcel) {
            return new InboxNotificationPayload(parcel);
        }

        public final InboxNotificationPayload[] newArray(int i) {
            return new InboxNotificationPayload[i];
        }
    };
    @SerializedName("badge")
    @Expose
    private int badge;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("data")
    @Expose
    private InboxNotificationPayloadData data;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("title")
    @Expose
    private String title;

    public int describeContents() {
        return 0;
    }

    protected InboxNotificationPayload(Parcel parcel) {
        this.title = (String) parcel.readValue(String.class.getClassLoader());
        this.body = (String) parcel.readValue(String.class.getClassLoader());
        this.icon = (String) parcel.readValue(String.class.getClassLoader());
        this.badge = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.data = (InboxNotificationPayloadData) parcel.readValue(InboxNotificationPayloadData.class.getClassLoader());
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public int getBadge() {
        return this.badge;
    }

    public void setBadge(int i) {
        this.badge = i;
    }

    public InboxNotificationPayloadData getData() {
        return this.data;
    }

    public void setData(InboxNotificationPayloadData inboxNotificationPayloadData) {
        this.data = inboxNotificationPayloadData;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.title);
        parcel.writeValue(this.body);
        parcel.writeValue(this.icon);
        parcel.writeValue(Integer.valueOf(this.badge));
        parcel.writeValue(this.data);
    }
}
