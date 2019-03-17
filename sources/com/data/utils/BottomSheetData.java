package com.data.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class BottomSheetData implements Parcelable {
    public static final Creator<BottomSheetData> CREATOR = new Creator<BottomSheetData>() {
        public final BottomSheetData createFromParcel(Parcel parcel) {
            return new BottomSheetData(parcel);
        }

        public final BottomSheetData[] newArray(int i) {
            return new BottomSheetData[i];
        }
    };
    private float number;
    private String text;

    public int describeContents() {
        return 0;
    }

    private BottomSheetData(Parcel parcel) {
        this.number = ((Float) parcel.readValue(Integer.TYPE.getClassLoader())).floatValue();
        this.text = (String) parcel.readValue(String.class.getClassLoader());
    }

    public float getNumber() {
        return this.number;
    }

    public void setNumber(float f) {
        this.number = f;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(Float.valueOf(this.number));
        parcel.writeValue(this.text);
    }
}
