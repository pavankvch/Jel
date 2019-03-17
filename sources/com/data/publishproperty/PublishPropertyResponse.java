package com.data.publishproperty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PublishPropertyResponse implements Parcelable {
    public static final Creator<PublishPropertyResponse> CREATOR = new Creator<PublishPropertyResponse>() {
        public final PublishPropertyResponse createFromParcel(Parcel parcel) {
            return new PublishPropertyResponse(parcel);
        }

        public final PublishPropertyResponse[] newArray(int i) {
            return new PublishPropertyResponse[i];
        }
    };
    @SerializedName("status")
    @Expose
    private String status;

    public int describeContents() {
        return 0;
    }

    protected PublishPropertyResponse(Parcel parcel) {
        this.status = parcel.readString();
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.status);
    }
}
