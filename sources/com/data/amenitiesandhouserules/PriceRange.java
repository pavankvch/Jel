package com.data.amenitiesandhouserules;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceRange implements Parcelable {
    public static final Creator<PriceRange> CREATOR = new Creator<PriceRange>() {
        public final PriceRange createFromParcel(Parcel parcel) {
            return new PriceRange(parcel);
        }

        public final PriceRange[] newArray(int i) {
            return new PriceRange[i];
        }
    };
    @SerializedName("max")
    @Expose
    private int max;
    @SerializedName("min")
    @Expose
    private int min;

    public int describeContents() {
        return 0;
    }

    protected PriceRange(Parcel parcel) {
        this.min = parcel.readInt();
        this.max = parcel.readInt();
    }

    public int getMin() {
        return this.min;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int i) {
        this.max = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.min);
        parcel.writeInt(this.max);
    }
}
