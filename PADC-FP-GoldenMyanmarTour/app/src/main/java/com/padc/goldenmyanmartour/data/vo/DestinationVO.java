package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationVO {
    @SerializedName("destination_id")
    private int id;

    @SerializedName("destination_title")
    private String title;

    @SerializedName("sort_order")
    private int sort_order;

    @SerializedName("destination_photos")
    private String[] destination_photos;

    @SerializedName("note-to-visitor")
    private String noteToVisitor;

    @SerializedName("location")
    private LocationVO locationVO;

    @SerializedName("attraction-places")
    private ArrayList<AttractionPlacesVO> attractionPlacesVOs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public String[] getDestination_photos() {
        return destination_photos;
    }

    public void setDestination_photos(String[] destination_photos) {
        this.destination_photos = destination_photos;
    }

    public String getNoteToVisitor() {
        return noteToVisitor;
    }

    public void setNoteToVisitor(String noteToVisitor) {
        this.noteToVisitor = noteToVisitor;
    }


    public LocationVO getLocationVO() {
        return locationVO;
    }

    public void setLocationVO(LocationVO locationVO) {
        this.locationVO = locationVO;
    }

    public ArrayList<AttractionPlacesVO> getAttractionPlacesVOs() {
        return attractionPlacesVOs;
    }

    public void setAttractionPlacesVOs(ArrayList<AttractionPlacesVO> attractionPlacesVOs) {
        this.attractionPlacesVOs = attractionPlacesVOs;
    }

    public static void saveDestinations(List<DestinationVO> destinationLst) {
        Context context = GMTApp.getContext();
        ContentValues[] destCv = new ContentValues[destinationLst.size()];
        for (int index = 0; index < destinationLst.size(); index++) {
            DestinationVO destinationVO = destinationLst.get(index);
            DestinationVO.saveDestinationImage(destinationVO.getTitle(), destinationVO.getDestination_photos());
        }
        int insertedCount = context.getContentResolver().bulkInsert(DestinationContract.DestinationEntry.CONTENT_URI, destCv);

    }



    private static void saveDestinationImage(String title, String[] photos) {
        ContentValues[] destCv = new ContentValues[photos.length];
        for (int index = 0; index < photos.length; index++) {
            String images = photos[index];
            ContentValues cv = new ContentValues();
            cv.put(DestinationContract.DestinationImageEntry.COLUMN_DESTINATION_TITLE, title);
            cv.put(DestinationContract.DestinationImageEntry.COLUMN_IMAGE, images);

            destCv[index] = cv;
        }
        Context context = GMTApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(DestinationContract.DestinationImageEntry.CONTENT_URI, destCv);
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.DestinationEntry.COLUMN_TITLE, title);
        return cv;
    }

    public static DestinationVO parseFromCursor(Cursor data) {
        DestinationVO destinationVO = new DestinationVO();
        destinationVO.title = data.getString(data.getColumnIndex(DestinationContract.DestinationEntry.COLUMN_TITLE));
        return destinationVO;
    }

    public static String[] loadDestinationImagesByTitle(String title) {
        Context context = GMTApp.getContext();
        ArrayList<String> images = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DestinationContract.DestinationImageEntry.buildDestinationImageUriWithTitle(title),
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                images.add(cursor.getString(cursor.getColumnIndex(DestinationContract.DestinationImageEntry.COLUMN_IMAGE)));
            } while (cursor.moveToNext());
        }
        String[] imageArray = new String[images.size()];
        images.toArray(imageArray);
        return imageArray;
    }
}
