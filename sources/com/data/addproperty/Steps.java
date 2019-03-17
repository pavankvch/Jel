package com.data.addproperty;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Steps implements Parcelable {
    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        public final Steps createFromParcel(Parcel parcel) {
            return new Steps(parcel);
        }

        public final Steps[] newArray(int i) {
            return new Steps[i];
        }
    };
    @SerializedName("step")
    @Expose
    private String step;
    @SerializedName("substep")
    @Expose
    private String substep;

    public int describeContents() {
        return 0;
    }

    protected Steps(Parcel parcel) {
        this.step = (String) parcel.readValue(String.class.getClassLoader());
        this.substep = (String) parcel.readValue(String.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.step);
        parcel.writeValue(this.substep);
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
}
