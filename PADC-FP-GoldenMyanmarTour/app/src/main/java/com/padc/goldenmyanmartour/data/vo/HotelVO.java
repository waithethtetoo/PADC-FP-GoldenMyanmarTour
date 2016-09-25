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

    @SerializedName("direction-to-hotel")
    private String direction;

    @SerializedName("phone-numbers")
    private String[] phoneNo;

    @SerializedName("location")
    private LocationVO locationVO;

    @SerializedName("room-prices")
    private ArrayList<RoomPriceVO> roomPriceVOArrayList;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String[] getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String[] phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocationVO getLocationVO() {
        return locationVO;
    }

    public void setLocationVO(LocationVO locationVO) {
        this.locationVO = locationVO;
    }

    public ArrayList<RoomPriceVO> getRoomPriceVOArrayList() {
        return roomPriceVOArrayList;
    }

    public void setRoomPriceVOArrayList(ArrayList<RoomPriceVO> roomPriceVOArrayList) {
        this.roomPriceVOArrayList = roomPriceVOArrayList;
    }
}
