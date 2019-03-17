package com.data.filter;

import com.data.amenitiesandhouserules.Amenity;
import com.data.amenitiesandhouserules.CancelPolicy;
import com.data.amenitiesandhouserules.Houserule;
import java.util.Map;

public class FilterData {
    private Map<String, Amenity> amenityMap;
    private String bookingType;
    private boolean bothBookingTypesChecked;
    private Map<String, CancelPolicy> cancelPolicyMap;
    private String doubleBeds;
    private Map<String, Houserule> houseRuleMap;
    private String priceFrom;
    private String priceTo;
    private String rooms;
    private String singleBeds;

    public boolean isBothBookingTypesChecked() {
        return this.bothBookingTypesChecked;
    }

    public void setBothBookingTypesChecked(boolean z) {
        this.bothBookingTypesChecked = z;
    }

    public String getBookingType() {
        return this.bookingType;
    }

    public void setBookingType(String str) {
        this.bookingType = str;
    }

    public String getPriceFrom() {
        return this.priceFrom;
    }

    public void setPriceFrom(String str) {
        this.priceFrom = str;
    }

    public String getPriceTo() {
        return this.priceTo;
    }

    public void setPriceTo(String str) {
        this.priceTo = str;
    }

    public String getRooms() {
        return this.rooms;
    }

    public void setRooms(String str) {
        this.rooms = str;
    }

    public String getSingleBeds() {
        return this.singleBeds;
    }

    public void setSingleBeds(String str) {
        this.singleBeds = str;
    }

    public Map<String, CancelPolicy> getCancelPolicyMap() {
        return this.cancelPolicyMap;
    }

    public void setCancelPolicyMap(Map<String, CancelPolicy> map) {
        this.cancelPolicyMap = map;
    }

    public Map<String, Amenity> getAmenityMap() {
        return this.amenityMap;
    }

    public void setAmenityMap(Map<String, Amenity> map) {
        this.amenityMap = map;
    }

    public Map<String, Houserule> getHouseRuleMap() {
        return this.houseRuleMap;
    }

    public void setHouseRuleMap(Map<String, Houserule> map) {
        this.houseRuleMap = map;
    }

    public String getDoubleBeds() {
        return this.doubleBeds;
    }

    public void setDoubleBeds(String str) {
        this.doubleBeds = str;
    }
}
