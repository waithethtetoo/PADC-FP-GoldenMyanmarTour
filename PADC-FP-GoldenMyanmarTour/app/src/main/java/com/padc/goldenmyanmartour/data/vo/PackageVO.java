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

    public static void savePackages(List<PackageVO> mPackageList) {

        Context context = GMTApp.getContext();
        ContentValues[] destCv = new ContentValues[mPackageList.size()];
        for (int index = 0; index < mPackageList.size(); index++) {
            PackageVO packageVO = mPackageList.get(index);
            PackageVO.savePackagesImage(packageVO.getPackageName(), packageVO.getPhotos());
        }
        int insertedCount = context.getContentResolver().bulkInsert(DestinationContract.PackageEntry.CONTENT_URI, destCv);
    }

    private static void savePackagesImage(String packageName, String[] photos) {
        ContentValues[] destCv = new ContentValues[photos.length];
        for (int index = 0; index < photos.length; index++) {
            String images = photos[index];
            ContentValues cv = new ContentValues();
            cv.put(DestinationContract.FestivalEntry.COLUMN_NAME, packageName);
            cv.put(DestinationContract.FestivalImageEntry.COLUMN_IMAGE, images);
            destCv[index] = cv;
        }
        Context context = GMTApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(DestinationContract.PackageImageEntry.CONTENT_URI, destCv);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.PackageEntry.COLUMN_NAME, packageName);
        return cv;
    }

    public static PackageVO parseFromCursor(Cursor data) {
        PackageVO packageVO = new PackageVO();
        packageVO.packageName = data.getString(data.getColumnIndex(DestinationContract.PackageEntry.COLUMN_NAME));
        packageVO.description = data.getString(data.getColumnIndex(DestinationContract.PackageEntry.COLUMN_DESC));
        packageVO.price = data.getString(data.getColumnIndex(DestinationContract.PackageEntry.COLUMN_PRICE));
        packageVO.totalDays = data.getString(data.getColumnIndex(DestinationContract.PackageEntry.COLUMN_TOTAL_DAY));
        return packageVO;
    }

    public static String[] loadPackageImagesByTitle(String title) {
        Context context = GMTApp.getContext();
        ArrayList<String> images = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DestinationContract.PackageImageEntry.buildPackageImageUriWithTitle(title),
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                images.add(cursor.getString(cursor.getColumnIndex(DestinationContract.PackageImageEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }
        String[] imageArray = new String[images.size()];
        images.toArray(imageArray);
        return imageArray;
    }
}
