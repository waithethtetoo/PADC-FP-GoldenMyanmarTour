package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WT on 9/16/2016.
 */
public class BookmarkVO  {
    @SerializedName("name")
    String name;

    @SerializedName("image")
    String image;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
     }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
