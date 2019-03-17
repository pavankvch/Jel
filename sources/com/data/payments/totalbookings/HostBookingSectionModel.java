package com.data.payments.totalbookings;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HostBookingSectionModel implements Parcelable {
    public static final Creator<HostBookingSectionModel> CREATOR = new Creator<HostBookingSectionModel>() {
        public final HostBookingSectionModel createFromParcel(Parcel parcel) {
            return new HostBookingSectionModel(parcel);
        }

        public final HostBookingSectionModel[] newArray(int i) {
            return new HostBookingSectionModel[i];
        }
    };
    @SerializedName("data")
    @Expose
    private List<HostBookingData> data;
    @SerializedName("month")
    @Expose
    private String month;

    public int describeContents() {
        return 0;
    }

    protected HostBookingSectionModel(Parcel parcel) {
        this.month = (String) parcel.readValue(String.class.getClassLoader());
        parcel.readList(this.data, HostBookingData.class.getClassLoader());
    }

    public HostBookingSectionModel(String str, List<HostBookingData> list) {
        this.month = str;
        this.data = list;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String str) {
        this.month = str;
    }

    public List<HostBookingData> getData() {
        return this.data;
    }

    public void setData(List<HostBookingData> list) {
        this.data = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.month);
        parcel.writeValue(this.data);
    }
}
