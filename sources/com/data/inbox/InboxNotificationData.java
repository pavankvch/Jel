package com.data.inbox;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InboxNotificationData implements Parcelable {
    public static final Creator<InboxNotificationData> CREATOR = new Creator<InboxNotificationData>() {
        public final InboxNotificationData createFromParcel(Parcel parcel) {
            return new InboxNotificationData(parcel);
        }

        public final InboxNotificationData[] newArray(int i) {
            return new InboxNotificationData[i];
        }
    };
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("payload")
    @Expose
    private InboxNotificationPayload payload;
    @SerializedName("read_notification")
    @Expose
    private String readNotification;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("uid")
    @Expose
    private String uid;

    public int describeContents() {
        return 0;
    }

    protected InboxNotificationData(Parcel parcel) {
        this.id = (String) parcel.readValue(String.class.getClassLoader());
        this.uid = (String) parcel.readValue(String.class.getClassLoader());
        this.payload = (InboxNotificationPayload) parcel.readValue(InboxNotificationPayload.class.getClassLoader());
        this.type = (String) parcel.readValue(String.class.getClassLoader());
        this.role = (String) parcel.readValue(String.class.getClassLoader());
        this.timestamp = (String) parcel.readValue(String.class.getClassLoader());
        this.readNotification = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public InboxNotificationPayload getPayload() {
        return this.payload;
    }

    public void setPayload(InboxNotificationPayload inboxNotificationPayload) {
        this.payload = inboxNotificationPayload;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String str) {
        this.role = str;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String str) {
        this.timestamp = str;
    }

    public String getReadNotification() {
        return this.readNotification;
    }

    public void setReadNotification(String str) {
        this.readNotification = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.uid);
        parcel.writeValue(this.payload);
        parcel.writeValue(this.type);
        parcel.writeValue(this.role);
        parcel.writeValue(this.timestamp);
        parcel.writeValue(this.readNotification);
    }
}
