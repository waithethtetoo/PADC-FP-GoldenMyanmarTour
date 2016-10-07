package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class FestivalPeriodVO {
    @SerializedName("start-date")
    private String startDate;

    @SerializedName("end-date")
    private String endDate;

    @SerializedName("daily-start-time")
    private String startTime;

    @SerializedName("daily-end-time")
    private String endTime;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


//    public static void savePeriodByFestival(String festivalName, FestivalPeriodVO festivalPeriodVO) {
//        Context context = GMTApp.getContext();
//        ContentValues cv = new ContentValues();
//        cv.put(DestinationContract.FestivalPeriodEntry.COLUMN_FESTIVAL_TITLE, festivalName);
//        cv.put(DestinationContract.FestivalPeriodEntry.COLUMN_START_DATE, festivalPeriodVO.getStartDate());
//        cv.put(DestinationContract.FestivalPeriodEntry.COLUMN_END_DATE, festivalPeriodVO.getEndDate());
//        cv.put(DestinationContract.FestivalPeriodEntry.COLUMN_START_TIME, festivalPeriodVO.getStartTime());
//        cv.put(DestinationContract.FestivalPeriodEntry.COLUMN_END_TIME, festivalPeriodVO.getEndTime());
//        Uri insertedUri = context.getContentResolver().insert(DestinationContract.FestivalPeriodEntry.CONTENT_URI, cv);
//
//    }
}


