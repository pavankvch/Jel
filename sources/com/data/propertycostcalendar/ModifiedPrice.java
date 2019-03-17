package com.data.propertycostcalendar;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModifiedPrice implements Parcelable {
    public static final Creator<ModifiedPrice> CREATOR = new Creator<ModifiedPrice>() {
        public final ModifiedPrice createFromParcel(Parcel parcel) {
            return new ModifiedPrice(parcel);
        }

        public final ModifiedPrice[] newArray(int i) {
            return new ModifiedPrice[i];
        }
    };
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("price")
    @Expose
    private float price;

    public int describeContents() {
        return 0;
    }

    protected ModifiedPrice(Parcel parcel) {
        this.date = (String) parcel.readValue(String.class.getClassLoader());
        this.price = ((Float) parcel.readValue(Float.TYPE.getClassLoader())).floatValue();
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float f) {
        this.price = f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.date);
        parcel.writeValue(Float.valueOf(this.price));
    }
}
