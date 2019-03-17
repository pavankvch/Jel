package com.data.addproperty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.data.propertydetail.FullInfo;
import com.data.searchproperty.SearchProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPropertyResponse implements Parcelable {
    public static final Creator<AddPropertyResponse> CREATOR = new Creator<AddPropertyResponse>() {
        public final AddPropertyResponse createFromParcel(Parcel parcel) {
            return new AddPropertyResponse(parcel);
        }

        public final AddPropertyResponse[] newArray(int i) {
            return new AddPropertyResponse[i];
        }
    };
    @SerializedName("basic_info")
    @Expose
    private SearchProperty basicInfo;
    @SerializedName("full_info")
    @Expose
    private FullInfo fullInfo;

    public int describeContents() {
        return 0;
    }

    protected AddPropertyResponse(Parcel parcel) {
        this.basicInfo = (SearchProperty) parcel.readValue(SearchProperty.class.getClassLoader());
        this.fullInfo = (FullInfo) parcel.readValue(FullInfo.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.basicInfo);
        parcel.writeValue(this.fullInfo);
    }

    public SearchProperty getBasicInfo() {
        return this.basicInfo;
    }

    public void setBasicInfo(SearchProperty searchProperty) {
        this.basicInfo = searchProperty;
    }

    public FullInfo getFullInfo() {
        return this.fullInfo;
    }

    public void setFullInfo(FullInfo fullInfo) {
        this.fullInfo = fullInfo;
    }
}
