package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract;

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

    public static void saveHotels(List<HotelVO> hotelVOList) {
        Context context = GMTApp.getContext();
        ContentValues[] cv = new ContentValues[hotelVOList.size()];
        for (int index = 0; index < hotelVOList.size(); index++) {
            HotelVO hotelVO = hotelVOList.get(index);
            cv[index] = hotelVO.parseToContentValues();
            HotelVO.saveHotelImages(hotelVO.getHotelName(), hotelVO.getPhotos());
        }
        int count = context.getContentResolver().bulkInsert(DestinationContract.HotelEntry.CONTENT_URI, cv);

    }

    private static void saveHotelImages(String name, String[] images) {
        ContentValues[] cv = new ContentValues[images.length];
        for (int index = 0; index < images.length; index++) {
            String image = images[index];
            ContentValues hotelCV = new ContentValues();
            hotelCV.put(DestinationContract.HotelImageEntry.COLUMN_HOTEL_NAME, name);
            hotelCV.put(DestinationContract.HotelImageEntry.COLUMN_IMAGES, image);
            cv[index] = hotelCV;
        }
        Context context = GMTApp.getContext();
        int count = context.getContentResolver().bulkInsert(DestinationContract.HotelImageEntry.CONTENT_URI, cv);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.HotelEntry.COLUMN_NAME, hotelName);
        cv.put(DestinationContract.HotelEntry.COLUMN_DESC, description);
        return cv;
    }

    public static HotelVO parseFromCursor(Cursor data) {
        HotelVO hotelVO = new HotelVO();
        hotelVO.hotelName = data.getString(data.getColumnIndex(DestinationContract.HotelEntry.COLUMN_NAME));
        hotelVO.description = data.getString(data.getColumnIndex(DestinationContract.HotelEntry.COLUMN_DESC));
        return hotelVO;
    }

    public static String[] loadHotelImagesByName(String hotelName) {
        Context context = GMTApp.getContext();
        ArrayList<String> photos = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DestinationContract.HotelImageEntry.buildHotelImageUriWithName(hotelName),
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                photos.add(cursor.getString(cursor.getColumnIndex(DestinationContract.HotelImageEntry.COLUMN_IMAGES)));
            } while (cursor.moveToFirst());

        }
        String[] photoArray = new String[photos.size()];
        photos.toArray(photoArray);
        return photoArray;
    }
}