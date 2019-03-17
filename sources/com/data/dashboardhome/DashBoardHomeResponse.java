package com.data.dashboardhome;

import com.data.searchtoplocalities.Locality;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DashBoardHomeResponse {
    @SerializedName("guest_inbox_count")
    private int guestInboxCount;
    @SerializedName("host_inbox_count")
    private int hostInboxCount;
    @SerializedName("labels")
    private List<String> labelsList;
    @SerializedName("localities")
    private List<Locality> localitiesList;
    @SerializedName("order")
    private List<String> orderList;
    @SerializedName("properties")
    private List<PropertyItem> propertyList;

    public List<Locality> getLocalitiesList() {
        return this.localitiesList;
    }

    public List<String> getOrderList() {
        return this.orderList;
    }

    public List<String> getLabelsList() {
        return this.labelsList;
    }

    public List<PropertyItem> getPropertyList() {
        return this.propertyList;
    }

    public int getGuestInboxCount() {
        return this.guestInboxCount;
    }

    public int getHostInboxCount() {
        return this.hostInboxCount;
    }
}
