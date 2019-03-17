package com.data.inbox;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InboxNotificationPayloadData implements Parcelable {
    public static final Creator<InboxNotificationPayloadData> CREATOR = new Creator<InboxNotificationPayloadData>() {
        public final InboxNotificationPayloadData createFromParcel(Parcel parcel) {
            return new InboxNotificationPayloadData(parcel);
        }

        public final InboxNotificationPayloadData[] newArray(int i) {
            return new InboxNotificationPayloadData[i];
        }
    };
    @SerializedName("attachment-url")
    @Expose
    private String attachmentUrl;
    @SerializedName("booking_hostname")
    @Expose
    private String bookingHostname;
    @SerializedName("booking_status")
    @Expose
    private String bookingStatus;
    @SerializedName("check_in_date")
    @Expose
    private String checkInDate;
    @SerializedName("check_out_date")
    @Expose
    private String checkOutDate;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("no_of_guests")
    @Expose
    private int noOfGuests;
    @SerializedName("no_of_nights")
    @Expose
    private int noOfNights;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("role")
    @Expose
    private int role;
    @SerializedName("save")
    @Expose
    private int save;
    @SerializedName("type")
    @Expose
    private String type;

    public int describeContents() {
        return 0;
    }

    protected InboxNotificationPayloadData(Parcel parcel) {
        this.code = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.type = (String) parcel.readValue(String.class.getClassLoader());
        this.propertyId = (String) parcel.readValue(String.class.getClassLoader());
        this.attachmentUrl = (String) parcel.readValue(String.class.getClassLoader());
        this.checkInDate = (String) parcel.readValue(String.class.getClassLoader());
        this.checkOutDate = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfNights = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.bookingStatus = (String) parcel.readValue(String.class.getClassLoader());
        this.bookingHostname = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfGuests = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.orderId = (String) parcel.readValue(String.class.getClassLoader());
        this.role = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.save = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getAttachmentUrl() {
        return this.attachmentUrl;
    }

    public void setAttachmentUrl(String str) {
        this.attachmentUrl = str;
    }

    public String getCheckInDate() {
        return this.checkInDate;
    }

    public void setCheckInDate(String str) {
        this.checkInDate = str;
    }

    public String getCheckOutDate() {
        return this.checkOutDate;
    }

    public void setCheckOutDate(String str) {
        this.checkOutDate = str;
    }

    public int getNoOfNights() {
        return this.noOfNights;
    }

    public void setNoOfNights(int i) {
        this.noOfNights = i;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String str) {
        this.bookingStatus = str;
    }

    public String getBookingHostname() {
        return this.bookingHostname;
    }

    public void setBookingHostname(String str) {
        this.bookingHostname = str;
    }

    public int getNoOfGuests() {
        return this.noOfGuests;
    }

    public void setNoOfGuests(int i) {
        this.noOfGuests = i;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public int getRole() {
        return this.role;
    }

    public void setRole(int i) {
        this.role = i;
    }

    public int getSave() {
        return this.save;
    }

    public void setSave(int i) {
        this.save = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(Integer.valueOf(this.code));
        parcel.writeValue(this.type);
        parcel.writeValue(this.propertyId);
        parcel.writeValue(this.attachmentUrl);
        parcel.writeValue(this.checkInDate);
        parcel.writeValue(this.checkOutDate);
        parcel.writeValue(Integer.valueOf(this.noOfNights));
        parcel.writeValue(this.bookingStatus);
        parcel.writeValue(this.bookingHostname);
        parcel.writeValue(Integer.valueOf(this.noOfGuests));
        parcel.writeValue(this.orderId);
        parcel.writeValue(Integer.valueOf(this.role));
        parcel.writeValue(Integer.valueOf(this.save));
    }
}
