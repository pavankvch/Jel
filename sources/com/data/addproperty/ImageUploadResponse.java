package com.data.addproperty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageUploadResponse implements Parcelable {
    public static final Creator<ImageUploadResponse> CREATOR = new Creator<ImageUploadResponse>() {
        public final ImageUploadResponse createFromParcel(Parcel parcel) {
            return new ImageUploadResponse(parcel);
        }

        public final ImageUploadResponse[] newArray(int i) {
            return new ImageUploadResponse[i];
        }
    };
    @SerializedName("featured")
    @Expose
    private String featured;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tittle")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;

    public int describeContents() {
        return 0;
    }

    protected ImageUploadResponse(Parcel parcel) {
        this.id = parcel.readString();
        this.url = parcel.readString();
        this.featured = parcel.readString();
        this.title = parcel.readString();
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getFeatured() {
        return this.featured;
    }

    public void setFeatured(String str) {
        this.featured = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.url);
        parcel.writeString(this.featured);
        parcel.writeString(this.title);
    }
}
