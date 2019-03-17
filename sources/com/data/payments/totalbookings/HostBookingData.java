package com.data.payments.totalbookings;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HostBookingData implements Parcelable {
    public static final Creator<HostBookingData> CREATOR = new Creator<HostBookingData>() {
        public final HostBookingData createFromParcel(Parcel parcel) {
            return new HostBookingData(parcel);
        }

        public final HostBookingData[] newArray(int i) {
            return new HostBookingData[i];
        }
    };
    @SerializedName("base_price")
    @Expose
    private String baseprice;
    @SerializedName("booked_on")
    @Expose
    private String bookedOn;
    @SerializedName("booking_status")
    @Expose
    private String bookingStatus;
    @SerializedName("booking_type")
    @Expose
    private String bookingType;
    @SerializedName("canceled_on")
    @Expose
    private String canceledDate;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("coupon_amount")
    @Expose
    private String couponamount;
    @SerializedName("earnings")
    @Expose
    private String earnings;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("guest_id")
    @Expose
    private String guestId;
    @SerializedName("guest_name")
    @Expose
    private String guestName;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("nights")
    @Expose
    private String nights;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("property_id")
    @Expose
    private String propertyId;
    @SerializedName("service_fee")
    @Expose
    private String servicefee;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("total_guests")
    @Expose
    private int totalGuests;
    @SerializedName("unit_price")
    @Expose
    private String unitprice;

    public int describeContents() {
        return 0;
    }

    protected HostBookingData(Parcel parcel) {
        this.orderId = (String) parcel.readValue(String.class.getClassLoader());
        this.bookingStatus = (String) parcel.readValue(String.class.getClassLoader());
        this.guestName = (String) parcel.readValue(String.class.getClassLoader());
        this.totalGuests = ((Integer) parcel.readValue(String.class.getClassLoader())).intValue();
        this.guestId = (String) parcel.readValue(String.class.getClassLoader());
        this.propertyId = (String) parcel.readValue(String.class.getClassLoader());
        this.name = (String) parcel.readValue(String.class.getClassLoader());
        this.price = (String) parcel.readValue(String.class.getClassLoader());
        this.bookedOn = (String) parcel.readValue(String.class.getClassLoader());
        this.startDate = (String) parcel.readValue(String.class.getClassLoader());
        this.endDate = (String) parcel.readValue(String.class.getClassLoader());
        this.nights = (String) parcel.readValue(String.class.getClassLoader());
        this.bookingType = (String) parcel.readValue(String.class.getClassLoader());
        this.country = (String) parcel.readValue(String.class.getClassLoader());
        this.image = (String) parcel.readValue(String.class.getClassLoader());
        this.canceledDate = (String) parcel.readValue(String.class.getClassLoader());
        this.earnings = (String) parcel.readValue(String.class.getClassLoader());
    }

    public String getProperty_id() {
        return this.propertyId;
    }

    public String getGuestName() {
        return this.guestName;
    }

    public void setGuestName(String str) {
        this.guestName = str;
    }

    public void setProperty_id(String str) {
        this.propertyId = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getBookedOn() {
        return this.bookedOn;
    }

    public void setBookedOn(String str) {
        this.bookedOn = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getNights() {
        return this.nights;
    }

    public void setNights(String str) {
        this.nights = str;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String str) {
        this.bookingType = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getGuestId() {
        return this.guestId;
    }

    public void setGuestId(String str) {
        this.guestId = str;
    }

    public int getTotalGuests() {
        return this.totalGuests;
    }

    public void setTotalGuests(int i) {
        this.totalGuests = i;
    }

    public String getCanceledDate() {
        return this.canceledDate;
    }

    public void setCanceledDate(String str) {
        this.canceledDate = str;
    }

    public String getBookingStatus() {
        return this.bookingStatus;
    }

    public void setBookingStatus(String str) {
        this.bookingStatus = str;
    }

    public String getEarnings() {
        return this.earnings;
    }

    public void setEarnings(String str) {
        this.earnings = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.orderId);
        parcel.writeValue(this.propertyId);
        parcel.writeValue(this.name);
        parcel.writeValue(this.price);
        parcel.writeValue(this.bookedOn);
        parcel.writeValue(this.startDate);
        parcel.writeValue(this.endDate);
        parcel.writeValue(this.nights);
        parcel.writeValue(this.bookingType);
        parcel.writeValue(this.country);
        parcel.writeValue(this.image);
        parcel.writeValue(this.guestId);
        parcel.writeValue(Integer.valueOf(this.totalGuests));
        parcel.writeValue(this.guestName);
        parcel.writeValue(this.bookingStatus);
        parcel.writeValue(this.earnings);
    }

    public String getTax() {
        return this.tax;
    }

    public void setTax(String str) {
        this.tax = str;
    }

    public String getServicefee() {
        return this.servicefee;
    }

    public void setServicefee(String str) {
        this.servicefee = str;
    }

    public String getBaseprice() {
        return this.baseprice;
    }

    public void setBaseprice(String str) {
        this.baseprice = str;
    }

    public String getUnitprice() {
        return this.unitprice;
    }

    public void setUnitprice(String str) {
        this.unitprice = str;
    }

    public String getCouponamount() {
        return this.couponamount;
    }

    public void setCouponamount(String str) {
        this.couponamount = str;
    }
}
