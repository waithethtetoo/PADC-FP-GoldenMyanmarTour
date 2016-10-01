package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import android.support.v7.widget.RecyclerView;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;

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

    @SerializedName("direction-to-festival")
    private String direction;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("festival-period")
    private FestivalPeriodVO festivalPeriodVO;


    @SerializedName("location")
    private LocationVO locationVO;



    public int getFestivalId() {
        return festivalId;
    }

    public void setFestivalId(int festivalId) {
        this.festivalId = festivalId;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoteToVisitor() {
        return noteToVisitor;
    }

    public void setNoteToVisitor(String noteToVisitor) {
        this.noteToVisitor = noteToVisitor;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public FestivalPeriodVO getFestivalPeriodVO() {
        return festivalPeriodVO;
    }

    public void setFestivalPeriodVO(FestivalPeriodVO festivalPeriodVO) {
        this.festivalPeriodVO = festivalPeriodVO;
    }

    public LocationVO getLocationVO() {
        return locationVO;
    }


    public void setLocationVO(LocationVO locationVO) {
        this.locationVO = locationVO;
    }

}
