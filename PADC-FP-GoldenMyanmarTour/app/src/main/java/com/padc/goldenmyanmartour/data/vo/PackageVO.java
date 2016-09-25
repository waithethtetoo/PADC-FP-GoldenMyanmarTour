package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/7/2016.
 */
public class PackageVO {
    @SerializedName("package-id")
    private String packageId;

    @SerializedName("package-name")
    private String packageName;

    @SerializedName("description")
    private String description;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("estimate-price-per-person")
    private String price;

    @SerializedName("total-days")
    private String totalDays;

    @SerializedName("sub-destinations")
    private ArrayList<DestinationVO> destinationVOs;

    @SerializedName("tour-company")
    private TourCompanyVO tourCompanyVO;

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }

    public ArrayList<DestinationVO> getDestinationVOs() {
        return destinationVOs;
    }

    public void setDestinationVOs(ArrayList<DestinationVO> destinationVOs) {
        this.destinationVOs = destinationVOs;
    }

    public TourCompanyVO getTourCompanyVO() {
        return tourCompanyVO;
    }

    public void setTourCompanyVO(TourCompanyVO tourCompanyVO) {
        this.tourCompanyVO = tourCompanyVO;
    }
}
