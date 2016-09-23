package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract;

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
    private int totalDays;

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

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
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
        ContentValues[] packageCV = new ContentValues[mPackageList.size()];
        for (int index = 0; index < mPackageList.size(); index++) {
            PackageVO packageVO = mPackageList.get(index);
            packageCV[index] = packageVO.parseToContentValues();
            PackageVO.savePackageImages(packageVO.getPackageName(), packageVO.getPhotos());
        }
        int count = context.getContentResolver().bulkInsert(DestinationContract.PackageEntry.CONTENT_URI, packageCV);
    }

    private static void savePackageImages(String name, String[] photos) {
        ContentValues[] cv = new ContentValues[photos.length];
        for (int index = 0; index < photos.length; index++) {
            String image = photos[index];
            ContentValues cvs = new ContentValues();
            cvs.put(DestinationContract.PackageImageEntry.COLUMN_PACKAGE_NAME, name);
            cvs.put(DestinationContract.PackageImageEntry.COLUMN_IMAGE, image);
            cv[index] = cvs;
        }
        Context context = GMTApp.getContext();
        int count = context.getContentResolver().bulkInsert(DestinationContract.PackageImageEntry.CONTENT_URI, cv);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.PackageEntry.COLUMN_PACKAGE_NAME, packageName);
        cv.put(DestinationContract.PackageEntry.COLUMN_DESC, description);
        return cv;
    }

    public static PackageVO parseFromCursor(Cursor data) {
        PackageVO packageVO = new PackageVO();
        packageVO.packageName = data.getString(data.getColumnIndex(DestinationContract.PackageEntry.COLUMN_PACKAGE_NAME));
        packageVO.description = data.getString(data.getColumnIndex(DestinationContract.PackageEntry.COLUMN_DESC));
        return packageVO;
    }

    public static String[] loadPackageImageByName(String name) {
        Context context = GMTApp.getContext();
        ArrayList<String> photo = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DestinationContract.PackageImageEntry.buildPackageImageUriWithName(name),
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                photo.add(cursor.getString(cursor.getColumnIndex(DestinationContract.PackageImageEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }
        String[] imageArray = new String[photo.size()];
        photo.toArray(imageArray);
        return imageArray;
    }
}
