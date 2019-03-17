package com.data.propertycostcalendar;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PropertyCostCalendarResponse implements Parcelable {
    public static final Creator<PropertyCostCalendarResponse> CREATOR = new Creator<PropertyCostCalendarResponse>() {
        public final PropertyCostCalendarResponse createFromParcel(Parcel parcel) {
            return new PropertyCostCalendarResponse(parcel);
        }

        public final PropertyCostCalendarResponse[] newArray(int i) {
            return new PropertyCostCalendarResponse[i];
        }
    };
    @SerializedName("available_dates")
    @Expose
    private List<String> availableDates = null;
    @SerializedName("booked_dates")
    @Expose
    private List<String> bookedDates = null;
    @SerializedName("modified_price")
    @Expose
    private List<ModifiedPrice> modifiedPrice = null;
    @SerializedName("months")
    @Expose
    private int months;
    @SerializedName("price")
    @Expose
    private float price;

    public int describeContents() {
        return 0;
    }

    protected PropertyCostCalendarResponse(Parcel parcel) {
        this.months = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.price = ((Float) parcel.readValue(Float.TYPE.getClassLoader())).floatValue();
        parcel.readList(this.availableDates, String.class.getClassLoader());
        parcel.readList(this.bookedDates, String.class.getClassLoader());
        parcel.readList(this.modifiedPrice, ModifiedPrice.class.getClassLoader());
    }

    public int getMonths() {
        return this.months;
    }

    public void setMonths(int i) {
        this.months = i;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float f) {
        this.price = f;
    }

    public List<String> getBookedDates() {
        return this.bookedDates;
    }

    public void setBookedDates(List<String> list) {
        this.bookedDates = list;
    }

    public List<ModifiedPrice> getModifiedPrice() {
        return this.modifiedPrice;
    }

    public void setModifiedPrice(List<ModifiedPrice> list) {
        this.modifiedPrice = list;
    }

    public List<String> getAvailableDates() {
        return this.availableDates;
    }

    public void setAvailableDates(List<String> list) {
        this.availableDates = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(Integer.valueOf(this.months));
        parcel.writeValue(Float.valueOf(this.price));
        parcel.writeList(this.availableDates);
        parcel.writeList(this.bookedDates);
        parcel.writeList(this.modifiedPrice);
    }
}
