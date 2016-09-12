package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WT on 9/12/2016.
 */
public class FestivalVO {
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String locaton;
    @SerializedName("month")
    private String month;
    @SerializedName("date")
    private String date;
    @SerializedName("duration")
    private String duration;
    @SerializedName("description")
    private String description;
    @SerializedName("images")
    private String[] images;

    public String getName() {
        return name;
    }

    public String getLocaton() {
        return locaton;
    }

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
