package com.data.publishproperty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublishPropertyRequest implements Parcelable {
    public static final Creator<PublishPropertyRequest> CREATOR = new Creator<PublishPropertyRequest>() {
        public final PublishPropertyRequest createFromParcel(Parcel parcel) {
            return new PublishPropertyRequest(parcel);
        }

        public final PublishPropertyRequest[] newArray(int i) {
            return new PublishPropertyRequest[i];
        }
    };
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("status")
    @Expose
    private int status;

    public int describeContents() {
        return 0;
    }

    protected PublishPropertyRequest(Parcel parcel) {
        this.propertyId = parcel.readString();
        this.status = parcel.readInt();
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.propertyId);
        parcel.writeInt(this.status);
    }
}
