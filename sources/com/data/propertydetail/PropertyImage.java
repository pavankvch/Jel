package com.data.propertydetail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PropertyImage implements Parcelable {
    public static final Creator<PropertyImage> CREATOR = new Creator<PropertyImage>() {
        public final PropertyImage createFromParcel(Parcel parcel) {
            return new PropertyImage(parcel);
        }

        public final PropertyImage[] newArray(int i) {
            return new PropertyImage[i];
        }
    };
    @SerializedName("fid")
    @Expose
    private String fileId;
    @SerializedName("image")
    @Expose
    private String propertyImage;
    @SerializedName("title")
    @Expose
    private String title;

    public int describeContents() {
        return 0;
    }

    protected PropertyImage(Parcel parcel) {
        this.fileId = (String) parcel.readValue(String.class.getClassLoader());
        this.propertyImage = (String) parcel.readValue(String.class.getClassLoader());
        this.title = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String str) {
        this.fileId = str;
    }

    public String getPropertyImage() {
        return this.propertyImage;
    }

    public void setPropertyImage(String str) {
        this.propertyImage = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.fileId);
        parcel.writeValue(this.propertyImage);
        parcel.writeValue(this.title);
    }
}
