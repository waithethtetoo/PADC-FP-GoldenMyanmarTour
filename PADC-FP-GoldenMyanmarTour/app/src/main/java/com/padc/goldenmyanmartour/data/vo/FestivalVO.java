package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;

import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/12/2016.
 */
public class FestivalVO {

    @SerializedName("festival-id")
    private int festivalId;

    @SerializedName("festival-name")
    private String festivalName;

    @SerializedName("description")
    private String description;

    @SerializedName("note-to-visitor")
    private String noteToVisitor;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("start-date")
    private String startDate;

    @SerializedName("end-date")
    private String endDate;

    @SerializedName("daily-start-time")
    private String startTime;

    @SerializedName("daily-end-time")
    private String endTime;

    @SerializedName("city-name")
    private String cityName;

    @SerializedName("state-name")
    private String stateName;

    public FestivalVO(int festivalId, String festivalName, String description, String noteToVisitor, String[] photos, String startDate, String endDate, String startTime, String endTime, String cityName, String stateName) {
        this.festivalId = festivalId;
        this.festivalName = festivalName;
        this.description = description;
        this.noteToVisitor = noteToVisitor;
        this.photos = photos;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cityName = cityName;
        this.stateName = stateName;
    }

    public int getFestivalId() {
        return festivalId;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public String getDescription() {
        return description;
    }

    public String getNoteToVisitor() {
        return noteToVisitor;
    }

    public String[] getPhotos() {
        return photos;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return stateName;
    }
}
