package com.padc.goldenmyanmartour.data.vo;

import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

import java.util.ArrayList;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class TourCompanyVO {

    @SerializedName("company-id")
    private long companyId;

    @SerializedName("company-name")
    private String companyName;

    @SerializedName("description")
    private String description;

    @SerializedName("photos")
    private String[] photos;

    @SerializedName("phone-numbers")
    private String[] phoneNo;

    @SerializedName("location")
    private LocationVO locationVO;

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String[] getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String[] phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocationVO getLocationVO() {
        return locationVO;
    }

    public void setLocationVO(LocationVO locationVO) {
        this.locationVO = locationVO;
    }
/*
    public static TourCompanyVO loadTourCompanyByPackageID(String packageId) {

        Context context = GMTApp.getContext();
        Cursor tourCompanyCursor = context.getContentResolver().query(DestinationContract.TourCompanyEntry.CONTENT_URI,
                null,
                DestinationContract.TourCompanyEntry.COLUMN_PACKAGE_ID + " = ?",
                new String[]{packageId},
                null);
        if (tourCompanyCursor != null && tourCompanyCursor.moveToFirst()) {
            TourCompanyVO tourCompanyVO = TourCompanyVO.parseFromCursor(tourCompanyCursor);
            tourCompanyVO.setLocationVO(LocationVO.loadLocationByPackageId(packageId));
            return tourCompanyVO;
        }
        return null;
    }

    private static TourCompanyVO parseFromCursor(Cursor data) {
        TourCompanyVO tourCompanyVO = new TourCompanyVO();
        tourCompanyVO.companyId = data.getLong(data.getColumnIndex(DestinationContract.TourCompanyEntry.COLUMN_ID));
        tourCompanyVO.companyName = data.getString(data.getColumnIndex(DestinationContract.TourCompanyEntry.COLUMN_NAME));
        tourCompanyVO.description = data.getString(data.getColumnIndex(DestinationContract.TourCompanyEntry.COLUMN_DESC));
        return tourCompanyVO;
    }
*/
}
