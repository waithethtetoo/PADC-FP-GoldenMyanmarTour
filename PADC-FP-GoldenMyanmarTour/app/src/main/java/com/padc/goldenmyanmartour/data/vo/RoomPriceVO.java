package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class RoomPriceVO {
    @SerializedName("room-description")
    private String roomDescription;

    @SerializedName("charge-per-night")
    private String charge;

    @SerializedName("included-services")
    private String[] services;

    @SerializedName("photos")
    private String[] photos;

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String[] getServices() {
        return services;
    }

    public void setServices(String[] services) {
        this.services = services;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
}
