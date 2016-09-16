package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationVO {
    @SerializedName("name")
    private String name;

    @SerializedName("desc")
    private String desc;

    @SerializedName("images")
    private String[] images;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String[] getImages() {
        return images;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
