package com.data.propertydetail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.data.addproperty.Steps;
import com.data.propertypricing.PropertyPricingRequest;
import com.data.searchproperty.SearchProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyDetailResponse implements Parcelable {
    public static final Creator<PropertyDetailResponse> CREATOR = new Creator<PropertyDetailResponse>() {
        public final PropertyDetailResponse createFromParcel(Parcel parcel) {
            return new PropertyDetailResponse(parcel);
        }

        public final PropertyDetailResponse[] newArray(int i) {
            return new PropertyDetailResponse[i];
        }
    };
    @SerializedName("full_info")
    @Expose
    private FullInfo fullInfo;
    @SerializedName("pricing")
    @Expose
    private PropertyPricingRequest pricing;
    @SerializedName("basic_info")
    @Expose
    private SearchProperty propertyInfo;
    @SerializedName("steps")
    @Expose
    private Steps steps;

    public int describeContents() {
        return 0;
    }

    protected PropertyDetailResponse(Parcel parcel) {
        this.propertyInfo = (SearchProperty) parcel.readValue(SearchProperty.class.getClassLoader());
        this.fullInfo = (FullInfo) parcel.readValue(FullInfo.class.getClassLoader());
        this.steps = (Steps) parcel.readValue(Steps.class.getClassLoader());
        this.pricing = (PropertyPricingRequest) parcel.readValue(PropertyPricingRequest.class.getClassLoader());
    }

    public SearchProperty getPropertyInfo() {
        return this.propertyInfo;
    }

    public void setPropertyInfo(SearchProperty searchProperty) {
        this.propertyInfo = searchProperty;
    }

    public FullInfo getFullInfo() {
        return this.fullInfo;
    }

    public void setFullInfo(FullInfo fullInfo) {
        this.fullInfo = fullInfo;
    }

    public Steps getSteps() {
        return this.steps;
    }

    public void setSteps(Steps steps) {
        this.steps = steps;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.propertyInfo);
        parcel.writeValue(this.fullInfo);
        parcel.writeValue(this.steps);
        parcel.writeValue(this.pricing);
    }

    public PropertyPricingRequest getPricing() {
        return this.pricing;
    }

    public void setPricing(PropertyPricingRequest propertyPricingRequest) {
        this.pricing = propertyPricingRequest;
    }
}
