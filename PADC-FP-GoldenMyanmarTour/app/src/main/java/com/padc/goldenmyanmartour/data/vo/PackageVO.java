package com.padc.goldenmyanmartour.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WT on 9/7/2016.
 */
public class PackageVO {
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("desc")
    private String desc;
    @SerializedName("images")
    private String[] images;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
