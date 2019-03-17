package com.data.propertypricing;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price implements Parcelable {
    public static final Creator<Price> CREATOR = new Creator<Price>() {
        public final Price createFromParcel(Parcel parcel) {
            return new Price(parcel);
        }

        public final Price[] newArray(int i) {
            return new Price[i];
        }
    };
    @SerializedName("0")
    @Expose
    private String _0;
    @SerializedName("1")
    @Expose
    private String _1;
    @SerializedName("2")
    @Expose
    private String _2;
    @SerializedName("3")
    @Expose
    private String _3;
    @SerializedName("4")
    @Expose
    private String _4;
    @SerializedName("5")
    @Expose
    private String _5;
    @SerializedName("6")
    @Expose
    private String _6;

    public int describeContents() {
        return 0;
    }

    protected Price(Parcel parcel) {
        this._6 = (String) parcel.readValue(String.class.getClassLoader());
        this._5 = (String) parcel.readValue(String.class.getClassLoader());
        this._4 = (String) parcel.readValue(String.class.getClassLoader());
        this._3 = (String) parcel.readValue(String.class.getClassLoader());
        this._2 = (String) parcel.readValue(String.class.getClassLoader());
        this._1 = (String) parcel.readValue(String.class.getClassLoader());
        this._0 = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String get0() {
        return this._0;
    }

    public void set0(String str) {
        this._0 = str;
    }

    public String get1() {
        return this._1;
    }

    public void set1(String str) {
        this._1 = str;
    }

    public String get2() {
        return this._2;
    }

    public void set2(String str) {
        this._2 = str;
    }

    public String get3() {
        return this._3;
    }

    public void set3(String str) {
        this._3 = str;
    }

    public String get4() {
        return this._4;
    }

    public void set4(String str) {
        this._4 = str;
    }

    public String get5() {
        return this._5;
    }

    public void set5(String str) {
        this._5 = str;
    }

    public String get6() {
        return this._6;
    }

    public void set6(String str) {
        this._6 = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this._6);
        parcel.writeValue(this._5);
        parcel.writeValue(this._4);
        parcel.writeValue(this._3);
        parcel.writeValue(this._2);
        parcel.writeValue(this._1);
        parcel.writeValue(this._0);
    }
}
