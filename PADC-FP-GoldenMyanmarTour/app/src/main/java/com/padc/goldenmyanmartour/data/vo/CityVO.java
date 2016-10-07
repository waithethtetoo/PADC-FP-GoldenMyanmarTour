package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class CityVO {
    @SerializedName("city-id")
    private long cityId;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private static CityVO parseFromCursor(Cursor cursor) {
        CityVO cityVO = new CityVO();
        cityVO.cityId = cursor.getLong(cursor.getColumnIndex(DestinationContract.CityEntry.COLUMN_ID));
        cityVO.name = cursor.getString(cursor.getColumnIndex(DestinationContract.CityEntry.COLUMN_NAME));
        cityVO.description = cursor.getString(cursor.getColumnIndex(DestinationContract.CityEntry.COLUMN_DESC));
        return cityVO;
    }

    public static CityVO loadCityByDestinationTitle(String title) {
        Context context = GMTApp.getContext();
        Cursor cityCursor = context.getContentResolver().query(DestinationContract.CityEntry.CONTENT_URI,
                null,
                DestinationContract.CityEntry.COLUMN_DESTINATION_TITLE + " = ?",
                new String[]{title},
                null);
        if (cityCursor != null && cityCursor.moveToFirst()) {
            return CityVO.parseFromCursor(cityCursor);
        }
        return null;

    }

    public static void saveCityByDestinationTitle(String title, CityVO cityVO) {
        Context context = GMTApp.getContext();
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.CityEntry.COLUMN_ID, cityVO.getCityId());
        cv.put(DestinationContract.CityEntry.COLUMN_NAME, cityVO.getName());
        cv.put(DestinationContract.CityEntry.COLUMN_DESC, cityVO.getDescription());
        cv.put(DestinationContract.CityEntry.COLUMN_DESTINATION_TITLE, title);
        Uri insertedUri = context.getContentResolver().insert(DestinationContract.CityEntry.CONTENT_URI, cv);
        Log.d(GMTApp.TAG, " Location Inserted Uri : " + insertedUri);
    }

}
