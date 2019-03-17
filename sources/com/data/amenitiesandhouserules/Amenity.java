package com.data.amenitiesandhouserules;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amenity implements Parcelable {
    public static final Creator<Amenity> CREATOR = new Creator<Amenity>() {
        public final Amenity createFromParcel(Parcel parcel) {
            return new Amenity(parcel);
        }

        public final Amenity[] newArray(int i) {
            return new Amenity[i];
        }
    };
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    private boolean isChecked;
    private boolean isEnabled;
    @SerializedName("name")
    @Expose
    private String name;

    public int describeContents() {
        return 0;
    }

    protected Amenity(Parcel parcel) {
        this.id = (String) parcel.readValue(String.class.getClassLoader());
        this.name = (String) parcel.readValue(String.class.getClassLoader());
        this.image = (String) parcel.readValue(String.class.getClassLoader());
        this.isChecked = parcel.readByte() != null ? true : null;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public boolean getChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.name);
        parcel.writeValue(this.image);
        parcel.writeByte((byte) this.isChecked);
    }
}
