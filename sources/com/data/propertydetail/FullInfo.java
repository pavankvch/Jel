package com.data.propertydetail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.data.amenitiesandhouserules.Amenity;
import com.data.amenitiesandhouserules.HouseSafety;
import com.data.amenitiesandhouserules.Houserule;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class FullInfo implements Parcelable {
    public static final Creator<FullInfo> CREATOR = new Creator<FullInfo>() {
        public final FullInfo createFromParcel(Parcel parcel) {
            return new FullInfo(parcel);
        }

        public final FullInfo[] newArray(int i) {
            return new FullInfo[i];
        }
    };
    @SerializedName("about_property")
    @Expose
    private String aboutProperty;
    @SerializedName("amenities")
    @Expose
    private List<Amenity> amenities = new ArrayList();
    @SerializedName("cancell_description")
    @Expose
    private String cancelDescription;
    @SerializedName("cancellation_policy")
    @Expose
    private String cancellationPolicy;
    @SerializedName("cancellation_policy_id")
    @Expose
    private String cancellationPolicyId;
    @SerializedName("check_in_time")
    @Expose
    private String checkInTime;
    @SerializedName("check_out_time")
    @Expose
    private String checkOutTime;
    @SerializedName("custom_house_rule")
    @Expose
    private String customHouseRule;
    @SerializedName("host_details")
    @Expose
    private HostDetails hostDetails;
    @SerializedName("house_rules")
    @Expose
    private List<Houserule> houseRules = new ArrayList();
    @SerializedName("house_safety")
    @Expose
    private List<HouseSafety> houseSafety = new ArrayList();
    @SerializedName("max_guests")
    @Expose
    private String maxGuests;
    @SerializedName("min_days")
    @Expose
    private String minDays;
    @SerializedName("no_of_bathrooms")
    @Expose
    private String noOfBathrooms;
    @SerializedName("no_of_bedrooms")
    @Expose
    private String noOfBeds;
    @SerializedName("no_of_double_beds")
    @Expose
    private String noOfDoublebeds;
    @SerializedName("no_of_kitchens")
    @Expose
    private String noOfKitchens;
    @SerializedName("no_of_living_rooms")
    @Expose
    private String noOfRooms;
    @SerializedName("no_of_single_beds")
    @Expose
    private String noOfSinglebeds;
    @SerializedName("no_of_tents")
    @Expose
    private String noOfTents;
    @SerializedName("property_type")
    @Expose
    private String propertyType;
    @SerializedName("property_type_id")
    @Expose
    private String propertyTypeId;
    @SerializedName("reviews")
    @Expose
    private Review review;

    public int describeContents() {
        return 0;
    }

    protected FullInfo(Parcel parcel) {
        this.propertyTypeId = (String) parcel.readValue(String.class.getClassLoader());
        this.propertyType = (String) parcel.readValue(String.class.getClassLoader());
        this.minDays = (String) parcel.readValue(String.class.getClassLoader());
        this.maxGuests = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfBeds = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfSinglebeds = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfDoublebeds = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfRooms = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfKitchens = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfTents = (String) parcel.readValue(String.class.getClassLoader());
        this.noOfBathrooms = (String) parcel.readValue(String.class.getClassLoader());
        this.aboutProperty = (String) parcel.readValue(String.class.getClassLoader());
        parcel.readList(this.amenities, Amenity.class.getClassLoader());
        parcel.readList(this.houseRules, Houserule.class.getClassLoader());
        parcel.readList(this.houseSafety, HouseSafety.class.getClassLoader());
        this.cancellationPolicyId = (String) parcel.readValue(String.class.getClassLoader());
        this.cancellationPolicy = (String) parcel.readValue(String.class.getClassLoader());
        this.cancelDescription = (String) parcel.readValue(String.class.getClassLoader());
        this.checkInTime = (String) parcel.readValue(String.class.getClassLoader());
        this.checkOutTime = (String) parcel.readValue(String.class.getClassLoader());
        this.customHouseRule = (String) parcel.readValue(String.class.getClassLoader());
        this.hostDetails = (HostDetails) parcel.readValue(HostDetails.class.getClassLoader());
        this.review = (Review) parcel.readValue(HostDetails.class.getClassLoader());
    }

    public String getPropertyTypeId() {
        return this.propertyTypeId;
    }

    public void setPropertyTypeId(String str) {
        this.propertyTypeId = str;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String str) {
        this.propertyType = str;
    }

    public String getMinDays() {
        return this.minDays;
    }

    public void setMinDays(String str) {
        this.minDays = str;
    }

    public String getMaxGuests() {
        return this.maxGuests;
    }

    public void setMaxGuests(String str) {
        this.maxGuests = str;
    }

    public String getNoOfRooms() {
        return this.noOfRooms;
    }

    public void setNoOfBeds(String str) {
        this.noOfBeds = str;
    }

    public String getNoOfBathrooms() {
        return this.noOfBathrooms;
    }

    public void setNoOfRooms(String str) {
        this.noOfRooms = str;
    }

    public String getNoOfBeds() {
        return this.noOfBeds;
    }

    public String getNoOfSinglebeds() {
        return this.noOfSinglebeds;
    }

    public void setNoOfSinglebeds(String str) {
        this.noOfSinglebeds = str;
    }

    public String getNoOfDoublebeds() {
        return this.noOfDoublebeds;
    }

    public void setNoOfDoublebeds(String str) {
        this.noOfDoublebeds = str;
    }

    public String getNoOfKitchens() {
        return this.noOfKitchens;
    }

    public void setNoOfKitchens(String str) {
        this.noOfKitchens = str;
    }

    public String getNoOfTents() {
        return this.noOfTents;
    }

    public void setNoOfTents(String str) {
        this.noOfTents = str;
    }

    public void setNoOfBathrooms(String str) {
        this.noOfBathrooms = str;
    }

    public String getAboutProperty() {
        return this.aboutProperty;
    }

    public void setAboutProperty(String str) {
        this.aboutProperty = str;
    }

    public List<Amenity> getAmenities() {
        return this.amenities;
    }

    public void setAmenities(List<Amenity> list) {
        this.amenities = list;
    }

    public List<Houserule> getHouseRules() {
        return this.houseRules;
    }

    public void setHouseRules(List<Houserule> list) {
        this.houseRules = list;
    }

    public List<HouseSafety> getHouseSafety() {
        return this.houseSafety;
    }

    public void setHouseSafety(List<HouseSafety> list) {
        this.houseSafety = list;
    }

    public String getCancellationPolicyId() {
        return this.cancellationPolicyId;
    }

    public void setCancellationPolicyId(String str) {
        this.cancellationPolicyId = str;
    }

    public String getCancellationPolicy() {
        return this.cancellationPolicy;
    }

    public void setCancellationPolicy(String str) {
        this.cancellationPolicy = str;
    }

    public String getCancelDescription() {
        return this.cancelDescription;
    }

    public void setCancelDescription(String str) {
        this.cancelDescription = str;
    }

    public HostDetails getHostDetails() {
        return this.hostDetails;
    }

    public void setHostDetails(HostDetails hostDetails) {
        this.hostDetails = hostDetails;
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

    public String getCustomHouseRule() {
        return this.customHouseRule;
    }

    public void setCustomHouseRule(String str) {
        this.customHouseRule = str;
    }

    public Review getReview() {
        return this.review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.propertyTypeId);
        parcel.writeValue(this.propertyType);
        parcel.writeValue(this.minDays);
        parcel.writeValue(this.maxGuests);
        parcel.writeValue(this.noOfBeds);
        parcel.writeValue(this.noOfSinglebeds);
        parcel.writeValue(this.noOfDoublebeds);
        parcel.writeValue(this.noOfRooms);
        parcel.writeValue(this.noOfKitchens);
        parcel.writeValue(this.noOfTents);
        parcel.writeValue(this.noOfBathrooms);
        parcel.writeValue(this.aboutProperty);
        parcel.writeList(this.amenities);
        parcel.writeList(this.houseRules);
        parcel.writeList(this.houseSafety);
        parcel.writeValue(this.cancellationPolicyId);
        parcel.writeValue(this.cancellationPolicy);
        parcel.writeValue(this.cancelDescription);
        parcel.writeValue(this.checkInTime);
        parcel.writeValue(this.checkOutTime);
        parcel.writeValue(this.customHouseRule);
        parcel.writeValue(this.hostDetails);
        parcel.writeValue(this.review);
    }
}
