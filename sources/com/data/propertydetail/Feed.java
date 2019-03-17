package com.data.propertydetail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feed implements Parcelable {
    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        public final Feed createFromParcel(Parcel parcel) {
            return new Feed(parcel);
        }

        public final Feed[] newArray(int i) {
            return new Feed[i];
        }
    };
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("guest_image")
    @Expose
    private String guestImage;
    @SerializedName("guestname")
    @Expose
    private String guestname;
    @SerializedName("hostname")
    @Expose
    private String hostname;
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("submitted")
    @Expose
    private String submitted;

    public int describeContents() {
        return 0;
    }

    protected Feed(Parcel parcel) {
        this.submitted = (String) parcel.readValue(String.class.getClassLoader());
        this.guestname = (String) parcel.readValue(String.class.getClassLoader());
        this.hostname = (String) parcel.readValue(String.class.getClassLoader());
        this.comment = (String) parcel.readValue(String.class.getClassLoader());
        this.rating = ((Float) parcel.readValue(Integer.TYPE.getClassLoader())).floatValue();
        this.guestImage = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String getSubmitted() {
        return this.submitted;
    }

    public void setSubmitted(String str) {
        this.submitted = str;
    }

    public String getGuestname() {
        return this.guestname;
    }

    public void setGuestname(String str) {
        this.guestname = str;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public float getRating() {
        return this.rating;
    }

    public void setRating(float f) {
        this.rating = f;
    }

    public String getGuestImage() {
        return this.guestImage;
    }

    public void setGuestImage(String str) {
        this.guestImage = str;
    }

    public String getHostname() {
        return this.hostname;
    }

    public void setHostname(String str) {
        this.hostname = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.submitted);
        parcel.writeValue(this.guestname);
        parcel.writeValue(this.hostname);
        parcel.writeValue(this.comment);
        parcel.writeValue(Float.valueOf(this.rating));
        parcel.writeValue(this.guestImage);
    }
}
