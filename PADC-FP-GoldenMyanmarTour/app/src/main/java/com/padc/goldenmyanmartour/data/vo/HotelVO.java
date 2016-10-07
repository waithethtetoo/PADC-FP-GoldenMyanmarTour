package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp user on 9/9/2016.
 */
public class HotelVO {
    @SerializedName("hotel-id")
    private int hotelId;

    @SerializedName("hotel-name")
    private String hotelName;

    @SerializedName("description")
    private String description;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("rating")
    private float rating;

    @SerializedName("direction-to-hotel")
    private String direction;

    @SerializedName("phone-numbers")
    private String[] phoneNo;

    @SerializedName("address")
    private String address;

    @SerializedName("city-name")
    private String cityName;

    @SerializedName("state-name")
    private String stateName;

    @SerializedName("included-services")
    private String[] services;

    @SerializedName("room-description")
    private String[] roomDesc;

    @SerializedName("charge-per-night")
    private String[] charge;

    @SerializedName("room-photos")
    private String[] roomPhotos;

    public HotelVO(int hotelId, String hotelName, String description, String[] photos, float rating, String direction, String[] phoneNo, String address, String cityName, String stateName, String[] services, String[] roomDesc, String[] charge, String[] roomPhotos) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.description = description;
        this.photos = photos;
        this.rating = rating;
        this.direction = direction;
        this.phoneNo = phoneNo;
        this.address = address;
        this.cityName = cityName;
        this.stateName = stateName;
        this.services = services;
        this.roomDesc = roomDesc;
        this.charge = charge;
        this.roomPhotos = roomPhotos;
    }

    public int getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getDescription() {
        return description;
    }

    public String[] getPhotos() {
        return photos;
    }

    public float getRating() {
        return rating;
    }

    public String getDirection() {
        return direction;
    }

    public String[] getPhoneNo() {
        return phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public String[] getServices() {
        return services;
    }

    public String[] getRoomDesc() {
        return roomDesc;
    }

    public String[] getCharge() {
        return charge;
    }

    public String[] getRoomPhotos() {
        return roomPhotos;
    }
}
