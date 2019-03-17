package com.data.amenitiesandhouserules;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Limits implements Parcelable {
    public static final Creator<Limits> CREATOR = new Creator<Limits>() {
        public final Limits createFromParcel(Parcel parcel) {
            return new Limits(parcel);
        }

        public final Limits[] newArray(int i) {
            return new Limits[i];
        }
    };
    @SerializedName("max_bathrooms")
    @Expose
    private int maxBathrooms;
    @SerializedName("no_of_bedrooms")
    @Expose
    private int maxBeds;
    @SerializedName("no_of_double_beds")
    @Expose
    private int maxDoublebeds;
    @SerializedName("max_guests")
    @Expose
    private int maxGuests;
    @SerializedName("no_of_kitchens")
    @Expose
    private int maxKitchens;
    @SerializedName("no_of_living_rooms")
    @Expose
    private int maxRooms;
    @SerializedName("no_of_single_beds")
    @Expose
    private int maxSinglebeds;
    @SerializedName("no_of_tents")
    @Expose
    private int maxTents;
    @SerializedName("min_nights")
    @Expose
    private int minNights;

    public int describeContents() {
        return 0;
    }

    protected Limits(Parcel parcel) {
        this.maxRooms = parcel.readInt();
        this.maxBeds = parcel.readInt();
        this.maxBathrooms = parcel.readInt();
        this.minNights = parcel.readInt();
        this.maxKitchens = parcel.readInt();
        this.maxTents = parcel.readInt();
        this.maxSinglebeds = parcel.readInt();
        this.maxDoublebeds = parcel.readInt();
        this.maxGuests = parcel.readInt();
    }

    public int getMaxRooms() {
        return this.maxRooms;
    }

    public void setMaxRooms(int i) {
        this.maxRooms = i;
    }

    public int getMaxBeds() {
        return this.maxBeds;
    }

    public void setMaxBeds(int i) {
        this.maxBeds = i;
    }

    public int getMaxBathrooms() {
        return this.maxBathrooms;
    }

    public void setMaxBathrooms(int i) {
        this.maxBathrooms = i;
    }

    public int getMinNights() {
        return this.minNights;
    }

    public void setMinNights(int i) {
        this.minNights = i;
    }

    public int getMaxTents() {
        return this.maxTents;
    }

    public void setMaxTents(int i) {
        this.maxTents = i;
    }

    public int getMaxKitchens() {
        return this.maxKitchens;
    }

    public void setMaxKitchens(int i) {
        this.maxKitchens = i;
    }

    public int getMaxSinglebeds() {
        return this.maxSinglebeds;
    }

    public void setMaxSinglebeds(int i) {
        this.maxSinglebeds = i;
    }

    public int getMaxDoublebeds() {
        return this.maxDoublebeds;
    }

    public void setMaxDoublebeds(int i) {
        this.maxDoublebeds = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.maxRooms);
        parcel.writeInt(this.maxBeds);
        parcel.writeInt(this.maxBathrooms);
        parcel.writeInt(this.minNights);
        parcel.writeInt(this.maxKitchens);
        parcel.writeInt(this.maxTents);
        parcel.writeInt(this.maxSinglebeds);
        parcel.writeInt(this.maxDoublebeds);
        parcel.writeInt(this.maxGuests);
    }

    public int getMaxGuests() {
        return this.maxGuests;
    }

    public void setMaxGuests(int i) {
        this.maxGuests = i;
    }
}
