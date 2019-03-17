package com.data.amenitiesandhouserules;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.data.signin.UserModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class SeedResponse implements Parcelable {
    public static final Creator<SeedResponse> CREATOR = new Creator<SeedResponse>() {
        public final SeedResponse createFromParcel(Parcel parcel) {
            return new SeedResponse(parcel);
        }

        public final SeedResponse[] newArray(int i) {
            return new SeedResponse[i];
        }
    };
    @SerializedName("amenities")
    @Expose
    private List<Amenity> amenities;
    @SerializedName("booking_desc")
    @Expose
    private BookingDesc bookingDesc;
    @SerializedName("cancel_policy")
    @Expose
    private List<CancelPolicy> cancelPolicy;
    @SerializedName("house_safety")
    @Expose
    private List<HouseSafety> houseSafety;
    @SerializedName("houserules")
    @Expose
    private List<Houserule> houserules;
    @SerializedName("is_rate_sort")
    @Expose
    private boolean isRateSort;
    @SerializedName("limits")
    @Expose
    private Limits limits;
    @SerializedName("price_range")
    @Expose
    private PriceRange priceRange;
    @SerializedName("property_types")
    @Expose
    private List<PropertyType> propertyTypes;
    @SerializedName("referral_amount")
    @Expose
    private String refferalAmount;
    @SerializedName("referred_amount")
    @Expose
    private String refferedAmount;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("user")
    @Expose
    private UserModel user;
    @SerializedName("version")
    @Expose
    private String version;

    public int describeContents() {
        return 0;
    }

    protected SeedResponse(Parcel parcel) {
        this.amenities = new ArrayList();
        this.houserules = new ArrayList();
        this.houseSafety = new ArrayList();
        this.propertyTypes = new ArrayList();
        this.cancelPolicy = new ArrayList();
        parcel.readList(this.amenities, Amenity.class.getClassLoader());
        parcel.readList(this.houserules, Houserule.class.getClassLoader());
        parcel.readList(this.houseSafety, HouseSafety.class.getClassLoader());
        parcel.readList(this.propertyTypes, PropertyType.class.getClassLoader());
        parcel.readList(this.cancelPolicy, CancelPolicy.class.getClassLoader());
        this.priceRange = (PriceRange) parcel.readValue(PriceRange.class.getClassLoader());
        this.limits = (Limits) parcel.readValue(Limits.class.getClassLoader());
        this.isRateSort = parcel.readByte() != (byte) 0;
        this.user = (UserModel) parcel.readValue(UserModel.class.getClassLoader());
        this.shareUrl = parcel.readString();
        this.version = parcel.readString();
        this.refferalAmount = parcel.readString();
        this.refferedAmount = parcel.readString();
    }

    public List<Amenity> getAmenities() {
        return this.amenities;
    }

    public void setAmenities(List<Amenity> list) {
        this.amenities = list;
    }

    public List<Houserule> getHouserules() {
        return this.houserules;
    }

    public void setHouserules(List<Houserule> list) {
        this.houserules = list;
    }

    public List<PropertyType> getPropertyTypes() {
        return this.propertyTypes;
    }

    public void setPropertyTypes(List<PropertyType> list) {
        this.propertyTypes = list;
    }

    public List<HouseSafety> getHouseSafety() {
        return this.houseSafety;
    }

    public void setHouseSafety(List<HouseSafety> list) {
        this.houseSafety = list;
    }

    public List<CancelPolicy> getCancelPolicy() {
        return this.cancelPolicy;
    }

    public void setCancelPolicy(List<CancelPolicy> list) {
        this.cancelPolicy = list;
    }

    public PriceRange getPriceRange() {
        return this.priceRange;
    }

    public void setPriceRange(PriceRange priceRange) {
        this.priceRange = priceRange;
    }

    public Limits getLimits() {
        return this.limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }

    public boolean isRateSort() {
        return this.isRateSort;
    }

    public void setRateSort(boolean z) {
        this.isRateSort = z;
    }

    public UserModel getUser() {
        return this.user;
    }

    public void setUser(UserModel userModel) {
        this.user = userModel;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.amenities);
        parcel.writeList(this.houserules);
        parcel.writeList(this.houseSafety);
        parcel.writeList(this.propertyTypes);
        parcel.writeList(this.cancelPolicy);
        parcel.writeValue(this.priceRange);
        parcel.writeValue(this.limits);
        parcel.writeByte((byte) this.isRateSort);
        parcel.writeValue(this.user);
        parcel.writeString(this.shareUrl);
        parcel.writeString(this.version);
        parcel.writeString(this.refferalAmount);
        parcel.writeString(this.refferedAmount);
    }

    public String getRefferalAmount() {
        return this.refferalAmount;
    }

    public void setRefferalAmount(String str) {
        this.refferalAmount = str;
    }

    public String getRefferedAmount() {
        return this.refferedAmount;
    }

    public void setRefferedAmount(String str) {
        this.refferedAmount = str;
    }

    public BookingDesc getBookingDesc() {
        return this.bookingDesc;
    }

    public void setBookingDesc(BookingDesc bookingDesc) {
        this.bookingDesc = bookingDesc;
    }
}
