package com.data.amenitiesandhouserules;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelPolicy implements Parcelable {
    public static final Creator<CancelPolicy> CREATOR = new Creator<CancelPolicy>() {
        public final CancelPolicy createFromParcel(Parcel parcel) {
            return new CancelPolicy(parcel);
        }

        public final CancelPolicy[] newArray(int i) {
            return new CancelPolicy[i];
        }
    };
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private String id;
    private boolean isChecked;
    @SerializedName("name")
    @Expose
    private String name;

    public int describeContents() {
        return 0;
    }

    protected CancelPolicy(Parcel parcel) {
        this.id = (String) parcel.readValue(String.class.getClassLoader());
        this.name = (String) parcel.readValue(String.class.getClassLoader());
        this.description = (String) parcel.readValue(String.class.getClassLoader());
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

    public boolean getChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.id);
        parcel.writeValue(this.name);
        parcel.writeValue(this.description);
        parcel.writeByte((byte) this.isChecked);
    }
}
