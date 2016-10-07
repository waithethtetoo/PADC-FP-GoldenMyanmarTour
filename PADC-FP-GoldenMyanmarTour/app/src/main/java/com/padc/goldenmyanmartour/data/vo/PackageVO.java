package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/7/2016.
 */
public class PackageVO {
    @SerializedName("package-id")
    private long packageId;

    @SerializedName("package-name")
    private String packageName;

    @SerializedName("description-title-one")
    private String descTitleOne;

    @SerializedName("description-one")
    private String descriptionOne;

    @SerializedName("description-title-two")
    private String descTitleTwo;

    @SerializedName("description-two")
    private String descriptionTwo;

    @SerializedName("description-title-three")
    private String descTitleThree;

    @SerializedName("description-three")
    private String descriptionThree;

    @SerializedName("description-title-four")
    private String descTitleFour;

    @SerializedName("description-four")
    private String descriptionFour;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("estimate-price-per-person")
    private String price;

    @SerializedName("total-days")
    private String totalDays;

    @SerializedName("destination-title-one")
    private String destTitleOne;

    @SerializedName("destination-title-two")
    private String destTitleTwo;

    @SerializedName("destination-title-three")
    private String destTitleThree;

    @SerializedName("destination-title-four")
    private String destTitleFour;

    @SerializedName("destination-photo-one")
    private String destPhotoOne;

    @SerializedName("destination-photo-two")
    private String destPhotoTwo;

    @SerializedName("destination-photo-three")
    private String destPhotoThree;

    @SerializedName("destination-photo-four")
    private String destPhotoFour;

    @SerializedName("company-id")
    private long companyID;

    @SerializedName("company-name")
    private String companyName;

    @SerializedName("description")
    private String description;

    @SerializedName("phone-numbers")
    private String[] phones;

    @SerializedName("address")
    private String address;

    public PackageVO(long packageId, String packageName, String descTitleOne, String descriptionOne, String descTitleTwo, String descriptionTwo, String descTitleThree, String descriptionThree, String descTitleFour, String descriptionFour, String[] photos, String price, String totalDays, String destTitleOne, String destTitleTwo, String destTitleThree, String destTitleFour, String destPhotoOne, String destPhotoTwo, String destPhotoThree, String destPhotoFour, long companyID, String companyName, String description, String[] phones, String address) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.descTitleOne = descTitleOne;
        this.descriptionOne = descriptionOne;
        this.descTitleTwo = descTitleTwo;
        this.descriptionTwo = descriptionTwo;
        this.descTitleThree = descTitleThree;
        this.descriptionThree = descriptionThree;
        this.descTitleFour = descTitleFour;
        this.descriptionFour = descriptionFour;
        this.photos = photos;
        this.price = price;
        this.totalDays = totalDays;
        this.destTitleOne = destTitleOne;
        this.destTitleTwo = destTitleTwo;
        this.destTitleThree = destTitleThree;
        this.destTitleFour = destTitleFour;
        this.destPhotoOne = destPhotoOne;
        this.destPhotoTwo = destPhotoTwo;
        this.destPhotoThree = destPhotoThree;
        this.destPhotoFour = destPhotoFour;
        this.companyID = companyID;
        this.companyName = companyName;
        this.description = description;
        this.phones = phones;
        this.address = address;
    }

    public long getPackageId() {
        return packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getDescTitleOne() {
        return descTitleOne;
    }

    public String getDescriptionOne() {
        return descriptionOne;
    }

    public String getDescTitleTwo() {
        return descTitleTwo;
    }

    public String getDescriptionTwo() {
        return descriptionTwo;
    }

    public String getDescTitleThree() {
        return descTitleThree;
    }

    public String getDescriptionThree() {
        return descriptionThree;
    }

    public String getDescTitleFour() {
        return descTitleFour;
    }

    public String getDescriptionFour() {
        return descriptionFour;
    }

    public String[] getPhotos() {
        return photos;
    }

    public String getPrice() {
        return price;
    }

    public String getTotalDays() {
        return totalDays;
    }

    public String getDestTitleOne() {
        return destTitleOne;
    }

    public String getDestTitleTwo() {
        return destTitleTwo;
    }

    public String getDestTitleThree() {
        return destTitleThree;
    }

    public String getDestTitleFour() {
        return destTitleFour;
    }

    public String getDestPhotoOne() {
        return destPhotoOne;
    }

    public String getDestPhotoTwo() {
        return destPhotoTwo;
    }

    public String getDestPhotoThree() {
        return destPhotoThree;
    }

    public String getDestPhotoFour() {
        return destPhotoFour;
    }

    public long getCompanyID() {
        return companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDescription() {
        return description;
    }

    public String[] getPhones() {
        return phones;
    }

    public String getAddress() {
        return address;
    }

}
