package com.data.propertydetail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Review implements Parcelable {
    public static final Creator<Review> CREATOR = new Creator<Review>() {
        public final Review createFromParcel(Parcel parcel) {
            return new Review(parcel);
        }

        public final Review[] newArray(int i) {
            return new Review[i];
        }
    };
    @SerializedName("average_rating")
    @Expose
    private float averageRating;
    @SerializedName("feeds")
    @Expose
    private List<Feed> feeds = new ArrayList();
    @SerializedName("reviewed")
    @Expose
    private int reviewed;

    public int describeContents() {
        return 0;
    }

    protected Review(Parcel parcel) {
        parcel.readList(this.feeds, Feed.class.getClassLoader());
        this.averageRating = ((Float) parcel.readValue(Integer.TYPE.getClassLoader())).floatValue();
        this.reviewed = ((Integer) parcel.readValue(Integer.TYPE.getClassLoader())).intValue();
    }

    public List<Feed> getFeeds() {
        return this.feeds;
    }

    public void setFeeds(List<Feed> list) {
        this.feeds = list;
    }

    public float getAverageRating() {
        return this.averageRating;
    }

    public void setAverageRating(float f) {
        this.averageRating = f;
    }

    public int getReviewed() {
        return this.reviewed;
    }

    public void setReviewed(int i) {
        this.reviewed = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.feeds);
        parcel.writeValue(Float.valueOf(this.averageRating));
        parcel.writeValue(Integer.valueOf(this.reviewed));
    }
}
