package com.data.propertypricing;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyPricingRequest implements Parcelable {
    public static final Creator<PropertyPricingRequest> CREATOR = new Creator<PropertyPricingRequest>() {
        public final PropertyPricingRequest createFromParcel(Parcel parcel) {
            return new PropertyPricingRequest(parcel);
        }

        public final PropertyPricingRequest[] newArray(int i) {
            return new PropertyPricingRequest[i];
        }
    };
    @SerializedName("check_in_time")
    @Expose
    private String checkInTime;
    @SerializedName("check_out_time")
    @Expose
    private String checkOutTime;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("property_id")
    @Expose
    private int propertyId;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("step")
    @Expose
    private String step;
    @SerializedName("substep")
    @Expose
    private String substep;

    public int describeContents() {
        return 0;
    }

    public PropertyPricingRequest(Parcel parcel) {
        this.checkInTime = (String) parcel.readValue(String.class.getClassLoader());
        this.checkOutTime = (String) parcel.readValue(String.class.getClassLoader());
        this.endDate = (String) parcel.readValue(String.class.getClassLoader());
        this.price = (Price) parcel.readValue(Price.class.getClassLoader());
        this.propertyId = ((Integer) parcel.readValue(String.class.getClassLoader())).intValue();
        this.startDate = (String) parcel.readValue(String.class.getClassLoader());
        this.step = (String) parcel.readValue(String.class.getClassLoader());
        this.substep = (String) parcel.readValue(String.class.getClassLoader());
    }

    public Price getPrice() {
        return this.price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public int getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(int i) {
        this.propertyId = i;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getCheckInTime() {
        return this.checkInTime;
    }

    public void setCheckInTime(String str) {
        this.checkInTime = str;
    }

    public String getCheckOutTime() {
        return this.checkOutTime;
    }

    public void setCheckOutTime(String str) {
        this.checkOutTime = str;
    }

    public String getStep() {
        return this.step;
    }

    public void setStep(String str) {
        this.step = str;
    }

    public String getSubstep() {
        return this.substep;
    }

    public void setSubstep(String str) {
        this.substep = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.checkInTime);
        parcel.writeValue(this.checkOutTime);
        parcel.writeValue(this.endDate);
        parcel.writeValue(this.price);
        parcel.writeValue(Integer.valueOf(this.propertyId));
        parcel.writeValue(this.startDate);
        parcel.writeValue(this.step);
        parcel.writeValue(this.substep);
    }
}
