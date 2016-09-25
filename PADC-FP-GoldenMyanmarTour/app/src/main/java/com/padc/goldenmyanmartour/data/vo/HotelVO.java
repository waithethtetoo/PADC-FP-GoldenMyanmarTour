package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

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

    public static void saveHotels(List<HotelVO> mHotelList) {
        Context context = GMTApp.getContext();
        ContentValues[] destCv = new ContentValues[mHotelList.size()];
        for (int index = 0; index < mHotelList.size(); index++) {
            HotelVO hotelVO = mHotelList.get(index);
            HotelVO.saveHotelsImage(hotelVO.getHotelName(), hotelVO.getPhotos());
        }
        int insertedCount = context.getContentResolver().bulkInsert(DestinationContract.HotelEntry.CONTENT_URI, destCv);
    }

    private static void saveHotelsImage(String hotelName, String[] photos) {
        ContentValues[] destCv = new ContentValues[photos.length];
        for (int index = 0; index < photos.length; index++) {
            String images = photos[index];
            ContentValues cv = new ContentValues();
            cv.put(DestinationContract.HotelEntry.COLUMN_NAME, hotelName);
            cv.put(DestinationContract.HotelImageEntry.COLUMN_IMAGE, images);
            destCv[index] = cv;
        }
        Context context = GMTApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(DestinationContract.HotelImageEntry.CONTENT_URI, destCv);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.HotelEntry.COLUMN_NAME, hotelName);
        return cv;
    }

    public static HotelVO parseFromCursor(Cursor data) {
        HotelVO hotelVO = new HotelVO();
        hotelVO.hotelName = data.getString(data.getColumnIndex(DestinationContract.HotelEntry.COLUMN_NAME));
        hotelVO.description = data.getString(data.getColumnIndex(DestinationContract.HotelEntry.COLUMN_DESC));
        hotelVO.direction = data.getString(data.getColumnIndex(DestinationContract.HotelEntry.COLUMN_DIRECTION));
        return hotelVO;
    }

    public static String[] loadHotelImagesByTitle(String title) {
        Context context = GMTApp.getContext();
        ArrayList<String> images = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DestinationContract.HotelImageEntry.buildHotelImageUriWithTitle(title),
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                images.add(cursor.getString(cursor.getColumnIndex(DestinationContract.HotelImageEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }
        String[] imageArray = new String[images.size()];
        images.toArray(imageArray);
        return imageArray;
    }
}
