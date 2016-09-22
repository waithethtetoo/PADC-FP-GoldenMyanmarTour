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

    public static void saveFestivals(List<FestivalVO> festivalVOList) {
        Context context = GMTApp.getContext();
        ContentValues[] cv = new ContentValues[festivalVOList.size()];
        for (int index = 0; index < festivalVOList.size(); index++) {
            FestivalVO festivalVO = festivalVOList.get(index);
            FestivalVO.saveFestivalImage(festivalVO.getFestivalName(), festivalVO.getPhotos());

        }
        int insertedCount = context.getContentResolver().bulkInsert(DestinationContract.FestivalEntry.CONTENT_URI, cv);
    }

    private static void saveFestivalImage(String title, String[] photos) {
        ContentValues[] destCv = new ContentValues[photos.length];
        for (int index = 0; index < photos.length; index++) {
            String images = photos[index];
            ContentValues cv = new ContentValues();
            cv.put(DestinationContract.FestivalImageEntry.COLUMN_FESTIVAL_NAME, title);
            cv.put(DestinationContract.FestivalImageEntry.COLUMN_IMAGE, images);

            destCv[index] = cv;
        }
        Context context = GMTApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(DestinationContract.FestivalImageEntry.CONTENT_URI, destCv);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.FestivalImageEntry.COLUMN_FESTIVAL_NAME, festivalName);
        return cv;
    }

    public static FestivalVO parseFromCursor(Cursor data) {
        FestivalVO festivalVO = new FestivalVO();
        festivalVO.festivalName = data.getString(data.getColumnIndex(DestinationContract.FestivalImageEntry.COLUMN_FESTIVAL_NAME));
        return festivalVO;
    }

    public static String[] loadFestivalImagesByTitle(String title) {
        Context context = GMTApp.getContext();
        ArrayList<String> images = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DestinationContract.FestivalImageEntry.buildFestivalImageUriWithTitle(title),
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                images.add(cursor.getString(cursor.getColumnIndex(DestinationContract.FestivalImageEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }
        String[] imageArray = new String[images.size()];
        images.toArray(imageArray);
        return imageArray;
    }
}
