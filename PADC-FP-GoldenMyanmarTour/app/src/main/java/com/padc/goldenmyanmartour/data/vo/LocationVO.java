package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

import java.util.ArrayList;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class LocationVO {
    @SerializedName("lat")
    private float lat;
    @SerializedName("lng")
    private float lng;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private CityVO cityVO;

    @SerializedName("state")
    private StateVO stateVO;

    public StateVO getStateVO() {
        return stateVO;
    }

    public void setStateVO(StateVO stateVO) {
        this.stateVO = stateVO;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public CityVO getCityVO() {
        return cityVO;
    }

    public void setCityVO(CityVO cityVO) {
        this.cityVO = cityVO;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    private static LocationVO parseFromCursor(Cursor cursor) {
        LocationVO locationVO = new LocationVO();
        locationVO.setLat(cursor.getLong(cursor.getColumnIndex(DestinationContract.LocationEntry.COLUMN_LAT)));
        locationVO.setLng(cursor.getLong(cursor.getColumnIndex(DestinationContract.LocationEntry.COLUMN_LNG)));
        locationVO.setAddress(cursor.getString(cursor.getColumnIndex(DestinationContract.LocationEntry.COLUMN_ADDRESS)));
        return locationVO;
    }

    public static LocationVO loadLocationByDestinationTitle(String title) {
        Context context = GMTApp.getContext();
        Cursor locationCursor = context.getContentResolver().query(DestinationContract.LocationEntry.CONTENT_URI,
                null,
                DestinationContract.LocationEntry.COLUMN_DESTINATION_TITLE + " = ?",
                new String[]{title},
                null);
        if (locationCursor != null && locationCursor.moveToFirst()) {
            LocationVO locationVO = LocationVO.parseFromCursor(locationCursor);
            locationVO.setStateVO(StateVO.loadStateByDestinationTitle(title));
            locationVO.setCityVO(CityVO.loadCityByDestinationTitle(title));
            return locationVO;
        }
        return null;

    }

    public static void saveLocationByDestination(String title, LocationVO locationVO) {
        //Location Save
        Context context = GMTApp.getContext();
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.LocationEntry.COLUMN_LAT, locationVO.getLat());
        cv.put(DestinationContract.LocationEntry.COLUMN_LNG, locationVO.getLng());
        cv.put(DestinationContract.LocationEntry.COLUMN_ADDRESS, locationVO.getAddress());
        cv.put(DestinationContract.LocationEntry.COLUMN_DESTINATION_TITLE, title);

        CityVO.saveCityByDestinationTitle(title, locationVO.getCityVO());
        //CityVO.saveCityByDestinationTitlte(locationVO.getId(),locationVO.getCityVO());
        StateVO.saveStateByDestinationTitle(title, locationVO.getStateVO());

        Uri insertedUri = context.getContentResolver().insert(DestinationContract.LocationEntry.CONTENT_URI, cv);


        Log.d(GMTApp.TAG, " Location Inserted Uri : " + insertedUri);
    }
}
