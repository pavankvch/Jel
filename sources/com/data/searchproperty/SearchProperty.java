package com.data.searchproperty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.data.propertydetail.PropertyImage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class SearchProperty implements Parcelable {
    public static final Creator<SearchProperty> CREATOR = new Creator<SearchProperty>() {
        public final SearchProperty createFromParcel(Parcel parcel) {
            return new SearchProperty(parcel);
        }

        public final SearchProperty[] newArray(int i) {
            return new SearchProperty[i];
        }
    };
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("area_in_meters")
    @Expose
    private String areaInMeters;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("favourite")
    @Expose
    private int favourite;
    @SerializedName("flat_no")
    @Expose
    private String flatNo;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("images")
    @Expose
    private List<PropertyImage> imagesList;
    private boolean isPropertyFavourite;
    @SerializedName("land_mark")
    @Expose
    private String landMark;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("price")
    @Expose
    private float price;
    @SerializedName("property_address")
    @Expose
    private String propertyAddress;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("property_type")
    @Expose
    private String propertyType;
    @SerializedName("province")
    @Expose
    private String province;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("reviews")
    @Expose
    private String reviews;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("type")
    @Expose
    private String type;

    public int describeContents() {
        return 0;
    }

    public String getAreaInMeters() {
        return this.areaInMeters;
    }

    public void setAreaInMeters(String str) {
        this.areaInMeters = str;
    }

    protected SearchProperty(Parcel parcel) {
        this.imagesList = new ArrayList();
        this.propertyId = (String) parcel.readValue(String.class.getClassLoader());
        this.propertyType = (String) parcel.readValue(String.class.getClassLoader());
        this.name = (String) parcel.readValue(String.class.getClassLoader());
        this.type = (String) parcel.readValue(String.class.getClassLoader());
        this.price = ((Float) parcel.readValue(Float.TYPE.getClassLoader())).floatValue();
        this.currencyCode = (String) parcel.readValue(String.class.getClassLoader());
        this.image = (String) parcel.readValue(String.class.getClassLoader());
        parcel.readList(this.imagesList, PropertyImage.class.getClassLoader());
        this.favourite = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
        this.address = (String) parcel.readValue(String.class.getClassLoader());
        this.propertyAddress = (String) parcel.readValue(String.class.getClassLoader());
        this.city = (String) parcel.readValue(String.class.getClassLoader());
        this.province = (String) parcel.readValue(String.class.getClassLoader());
        this.country = (String) parcel.readValue(String.class.getClassLoader());
        this.flatNo = (String) parcel.readValue(String.class.getClassLoader());
        this.postalCode = (String) parcel.readValue(String.class.getClassLoader());
        this.landMark = (String) parcel.readValue(String.class.getClassLoader());
        this.lat = (String) parcel.readValue(String.class.getClassLoader());
        this.lng = (String) parcel.readValue(String.class.getClassLoader());
        this.reviews = (String) parcel.readValue(String.class.getClassLoader());
        this.rating = (String) parcel.readValue(String.class.getClassLoader());
        this.distance = (String) parcel.readValue(String.class.getClassLoader());
        this.areaInMeters = (String) parcel.readValue(String.class.getClassLoader());
        this.currencyCode = (String) parcel.readValue(String.class.getClassLoader());
        this.shareUrl = (String) parcel.readValue(String.class.getClassLoader());
        this.isPropertyFavourite = parcel.readByte() != (byte) 0;
        this.street = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String str) {
        this.propertyId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float f) {
        this.price = f;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public List<PropertyImage> getImagesList() {
        return this.imagesList;
    }

    public void setImagesList(List<PropertyImage> list) {
        this.imagesList = list;
    }

    public int getFavourite() {
        return this.favourite;
    }

    public void setFavourite(int i) {
        this.favourite = i;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String str) {
        this.lat = str;
    }

    public String getLng() {
        return this.lng;
    }

    public void setLng(String str) {
        this.lng = str;
    }

    public String getReviews() {
        return this.reviews;
    }

    public void setReviews(String str) {
        this.reviews = str;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String str) {
        this.distance = str;
    }

    public boolean isPropertyFavourite() {
        return this.isPropertyFavourite;
    }

    public void setPropertyFavourite(boolean z) {
        this.isPropertyFavourite = z;
    }

    public String getFlatNo() {
        return this.flatNo;
    }

    public void setFlatNo(String str) {
        this.flatNo = str;
    }

    public String getLandMark() {
        return this.landMark;
    }

    public void setLandMark(String str) {
        this.landMark = str;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public void setShareUrl(String str) {
        this.shareUrl = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.propertyId);
        parcel.writeValue(this.propertyType);
        parcel.writeValue(this.name);
        parcel.writeValue(this.type);
        parcel.writeValue(Float.valueOf(this.price));
        parcel.writeValue(this.currencyCode);
        parcel.writeValue(this.image);
        parcel.writeList(this.imagesList);
        parcel.writeValue(Integer.valueOf(this.favourite));
        parcel.writeValue(this.address);
        parcel.writeValue(this.propertyAddress);
        parcel.writeValue(this.city);
        parcel.writeValue(this.province);
        parcel.writeValue(this.country);
        parcel.writeValue(this.flatNo);
        parcel.writeValue(this.postalCode);
        parcel.writeValue(this.landMark);
        parcel.writeValue(this.lat);
        parcel.writeValue(this.lng);
        parcel.writeValue(this.reviews);
        parcel.writeValue(this.rating);
        parcel.writeValue(this.distance);
        parcel.writeValue(this.areaInMeters);
        parcel.writeValue(this.currencyCode);
        parcel.writeValue(this.shareUrl);
        parcel.writeByte((byte) this.isPropertyFavourite);
        parcel.writeValue(this.street);
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String str) {
        this.postalCode = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getPropertyType() {
        return this.propertyType;
    }

    public void setPropertyType(String str) {
        this.propertyType = str;
    }

    public String getPropertyAddress() {
        return this.propertyAddress;
    }

    public void setPropertyAddress(String str) {
        this.propertyAddress = str;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String str) {
        this.street = str;
    }
}
