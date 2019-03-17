package com.data.propertydetail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HostDetails implements Parcelable {
    public static final Creator<HostDetails> CREATOR = new Creator<HostDetails>() {
        public final HostDetails createFromParcel(Parcel parcel) {
            return new HostDetails(parcel);
        }

        public final HostDetails[] newArray(int i) {
            return new HostDetails[i];
        }
    };
    @SerializedName("host_uid")
    @Expose
    private String hostedId;
    @SerializedName("hosted_image")
    @Expose
    private String hostedImage;
    @SerializedName("hosted_name")
    @Expose
    private String hostedName;

    public int describeContents() {
        return 0;
    }

    protected HostDetails(Parcel parcel) {
        this.hostedName = (String) parcel.readValue(String.class.getClassLoader());
        this.hostedImage = (String) parcel.readValue(String.class.getClassLoader());
        this.hostedId = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String getHostedName() {
        return this.hostedName;
    }

    public void setHostedName(String str) {
        this.hostedName = str;
    }

    public String getHostedImage() {
        return this.hostedImage;
    }

    public void setHostedImage(String str) {
        this.hostedImage = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.hostedName);
        parcel.writeValue(this.hostedImage);
        parcel.writeValue(this.hostedId);
    }

    public String getHostedId() {
        return this.hostedId;
    }

    public void setHostedId(String str) {
        this.hostedId = str;
    }
}
