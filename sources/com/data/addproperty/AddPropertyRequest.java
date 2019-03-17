package com.data.addproperty;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AddPropertyRequest {
    @SerializedName("about_property")
    @Expose
    private String aboutProperty;
    @SerializedName("amenities")
    @Expose
    private List<String> amenities;
    @SerializedName("area_in_meters")
    @Expose
    private String areaInMeters;
    @SerializedName("booking_type")
    @Expose
    private String bookingType;
    @SerializedName("cancellation_policy")
    @Expose
    private String cancellationPolicy;
    @SerializedName("check_in_time")
    @Expose
    private String checkInTime;
    @SerializedName("check_out_time")
    @Expose
    private String checkOutTime;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("city_lat")
    @Expose
    private String cityLat;
    @SerializedName("city_lng")
    @Expose
    private String cityLng;
    @SerializedName("cleaning_fee")
    @Expose
    private String cleaningFee;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("country_short")
    @Expose
    private String countryShot;
    @SerializedName("custom_house_rule")
    @Expose
    private String customHouseRule;
    @SerializedName("flat_no")
    @Expose
    private String flatNo;
    @SerializedName("house_rules")
    @Expose
    private List<String> houseRules;
    @SerializedName("house_safety")
    @Expose
    private List<String> houseSafety;
    @SerializedName("land_mark")
    @Expose
    private String landMark;
    @SerializedName("lat")
    @Expose
    private String latitude;
    @SerializedName("lng")
    @Expose
    private String longitude;
    @SerializedName("max_guests")
    @Expose
    private String maxGuests;
    @SerializedName("min_nights")
    @Expose
    private String minNights;
    @SerializedName("no_of_bathrooms")
    @Expose
    private String noOfBathroom;
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
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("property_address")
    @Expose
    private String propertyAddress;
    @SerializedName("property_images")
    @Expose
    private List<String> propertyImages;
    @SerializedName("property_name")
    @Expose
    private String propertyName;
    @SerializedName("property_id")
    @Expose
    private String propertyid;
    @SerializedName("property_type")
    @Expose
    private String propertytype;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("security_deposit")
    @Expose
    private String securityDeposit;
    @SerializedName("step")
    @Expose
    private String step;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("substep")
    @Expose
    private String substep;

    public String getPropertytype() {
        return this.propertytype;
    }

    public void setPropertytype(String str) {
        this.propertytype = str;
    }

    public String getNoOfRooms() {
        return this.noOfRooms;
    }

    public void setNoOfRooms(String str) {
        this.noOfRooms = str;
    }

    public String getNoOfBeds() {
        return this.noOfBeds;
    }

    public void setNoOfBeds(String str) {
        this.noOfBeds = str;
    }

    public String getNoOfBathroom() {
        return this.noOfBathroom;
    }

    public void setNoOfBathroom(String str) {
        this.noOfBathroom = str;
    }

    public String getMaxGuests() {
        return this.maxGuests;
    }

    public void setMaxGuests(String str) {
        this.maxGuests = str;
    }

    public String getPropertyAddress() {
        return this.propertyAddress;
    }

    public void setPropertyAddress(String str) {
        this.propertyAddress = str;
    }

    public String getFlatNo() {
        return this.flatNo;
    }

    public void setFlatNo(String str) {
        this.flatNo = str;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String str) {
        this.postalCode = str;
    }

    public String getLandMark() {
        return this.landMark;
    }

    public void setLandMark(String str) {
        this.landMark = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getCountryShot() {
        return this.countryShot;
    }

    public void setCountryShot(String str) {
        this.countryShot = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public List<String> getAmenities() {
        return this.amenities;
    }

    public void setAmenities(List<String> list) {
        this.amenities = list;
    }

    public List<String> getHouseSafety() {
        return this.houseSafety;
    }

    public void setHouseSafety(List<String> list) {
        this.houseSafety = list;
    }

    public List<String> getHouseRules() {
        return this.houseRules;
    }

    public void setHouseRules(List<String> list) {
        this.houseRules = list;
    }

    public String getCustomHouseRule() {
        return this.customHouseRule;
    }

    public void setCustomHouseRule(String str) {
        this.customHouseRule = str;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String str) {
        this.propertyName = str;
    }

    public List<String> getPropertyImages() {
        return this.propertyImages;
    }

    public void setPropertyImages(List<String> list) {
        this.propertyImages = list;
    }

    public String getAboutProperty() {
        return this.aboutProperty;
    }

    public void setAboutProperty(String str) {
        this.aboutProperty = str;
    }

    public String getCleaningFee() {
        return this.cleaningFee;
    }

    public void setCleaningFee(String str) {
        this.cleaningFee = str;
    }

    public String getSecurityDeposit() {
        return this.securityDeposit;
    }

    public void setSecurityDeposit(String str) {
        this.securityDeposit = str;
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

    public String getMinNights() {
        return this.minNights;
    }

    public void setMinNights(String str) {
        this.minNights = str;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String str) {
        this.bookingType = str;
    }

    public String getCancellationPolicy() {
        return this.cancellationPolicy;
    }

    public void setCancellationPolicy(String str) {
        this.cancellationPolicy = str;
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

    public String getPropertyid() {
        return this.propertyid;
    }

    public void setPropertyid(String str) {
        this.propertyid = str;
    }

    public String getNoOfTents() {
        return this.noOfTents;
    }

    public void setNoOfTents(String str) {
        this.noOfTents = str;
    }

    public String getNoOfKitchens() {
        return this.noOfKitchens;
    }

    public void setNoOfKitchens(String str) {
        this.noOfKitchens = str;
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

    public String getAreaInMeters() {
        return this.areaInMeters;
    }

    public void setAreaInMeters(String str) {
        this.areaInMeters = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCityLat() {
        return this.cityLat;
    }

    public void setCityLat(String str) {
        this.cityLat = str;
    }

    public String getCityLng() {
        return this.cityLng;
    }

    public void setCityLng(String str) {
        this.cityLng = str;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String str) {
        this.street = str;
    }
}
