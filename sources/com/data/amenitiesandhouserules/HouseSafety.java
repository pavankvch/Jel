package com.data.amenitiesandhouserules;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseSafety implements Parcelable {
    public static final Creator<HouseSafety> CREATOR = new Creator<HouseSafety>() {
        public final HouseSafety createFromParcel(Parcel parcel) {
            return new HouseSafety(parcel);
        }

        public final HouseSafety[] newArray(int i) {
            return new HouseSafety[i];
        }
    };
    @SerializedName("id")
    @Expose
    private String id;
    private boolean isChecked;
    @SerializedName("name")
    @Expose
    private String name;

    public int describeContents() {
        return 0;
    }

    protected HouseSafety(Parcel parcel) {
        this.id = (String) parcel.readValue(String.class.getClassLoader());
        this.name = (String) parcel.readValue(String.class.getClassLoader());
        this.isChecked = parcel.readByte() != null ? true : null;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean getChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.name);
        parcel.writeByte((byte) this.isChecked);
    }
}
