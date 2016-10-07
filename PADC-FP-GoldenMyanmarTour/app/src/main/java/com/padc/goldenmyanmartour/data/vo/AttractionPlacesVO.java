package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class AttractionPlacesVO {

    @SerializedName("place-id")
    public long placeId;

    @SerializedName("title")
    public String title;

    @SerializedName("image")
    public String[] image;

    @SerializedName("description")
    public String description;

    @SerializedName("note-to-visitor")
    public String noteToVisitor;

    public long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(long placeId) {
        this.placeId = placeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
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

    public static void saveAttractionPlaceByDestinationTitle(String title, ArrayList<AttractionPlacesVO> attractionPlacesVOs) {
        Context context = GMTApp.getContext();
        ContentValues[] cvs = new ContentValues[attractionPlacesVOs.size()];
        for (int index = 0; index < attractionPlacesVOs.size(); index++) {
            AttractionPlacesVO attractionPlace = attractionPlacesVOs.get(index);
            attractionPlace.saveAttractionPlaceImage(title, attractionPlace.getImage());
            cvs[index] = attractionPlace.parseToContentValues(title);
        }
        int count = context.getContentResolver().bulkInsert(DestinationContract.AttractionPlacesEntry.CONTENT_URI, cvs);
        Log.d(GMTApp.TAG, "Bulk inserted into attraction place table : " + count);
    }

    public static ArrayList<AttractionPlacesVO> loadAttractionPlacesByDestinationTitle(String title) {
        Context context = GMTApp.getContext();
        ArrayList<AttractionPlacesVO> attractionPlacesVOs = new ArrayList<>();
        Cursor attractionPlaceCursor = context.getContentResolver().query(DestinationContract.AttractionPlacesEntry.CONTENT_URI,
                null,
                DestinationContract.AttractionPlacesEntry.COLUMN_DESTINATION_TITLE + " = ?",
                new String[]{String.valueOf(title)},
                null);

        if (attractionPlaceCursor != null && attractionPlaceCursor.moveToFirst()) {
            return AttractionPlacesVO.parseFromCursor(attractionPlaceCursor);
        }
        return attractionPlacesVOs;
    }


    private ContentValues parseToContentValues(String destTitle) {
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.AttractionPlacesEntry.COLUMN_PLACES_ID, placeId);
        cv.put(DestinationContract.AttractionPlacesEntry.COLUMN_TITLE, title);
        cv.put(DestinationContract.AttractionPlacesEntry.COLUMN_DESC, description);
        cv.put(DestinationContract.AttractionPlacesEntry.COLUMN_NOTE, noteToVisitor);
        cv.put(DestinationContract.AttractionPlacesEntry.COLUMN_DESTINATION_TITLE, destTitle);
        return cv;
    }

    private static ArrayList<AttractionPlacesVO> parseFromCursor(Cursor cursor) {
        AttractionPlacesVO attractionPlacesVO = new AttractionPlacesVO();

        attractionPlacesVO.setDescription(cursor.getString(cursor.getColumnIndex(DestinationContract.AttractionPlacesEntry.COLUMN_DESC)));
        attractionPlacesVO.setTitle(cursor.getString(cursor.getColumnIndex(DestinationContract.AttractionPlacesEntry.COLUMN_TITLE)));
        attractionPlacesVO.setNoteToVisitor(cursor.getString(cursor.getColumnIndex(DestinationContract.AttractionPlacesEntry.COLUMN_NOTE)));
        attractionPlacesVO.setPlaceId(cursor.getLong(cursor.getColumnIndex(DestinationContract.AttractionPlacesEntry.COLUMN_PLACES_ID)));
        attractionPlacesVO.setImage(AttractionPlacesVO.loadAttractionImagesByTitle(attractionPlacesVO.getTitle()));

        ArrayList<AttractionPlacesVO> newAttractionList = new ArrayList<>();
        newAttractionList.add(attractionPlacesVO);
        return newAttractionList;
    }

    private static void saveAttractionPlaceImage(String title, String[] photos) {
        ContentValues[] attractionImgCv = new ContentValues[photos.length];
        for (int index = 0; index < photos.length; index++) {
            String images = photos[index];
            ContentValues cv = new ContentValues();
            cv.put(DestinationContract.AttractionPlaceImageEntry.COLUMN_ATTRACTION_TITLE, title);
            cv.put(DestinationContract.AttractionPlaceImageEntry.COLUMN_IMAGES, images);

            attractionImgCv[index] = cv;
        }
        Context context = GMTApp.getContext();
        int insertCount = context.getContentResolver().bulkInsert(DestinationContract.AttractionPlaceImageEntry.CONTENT_URI, attractionImgCv);
    }

    public static String[] loadAttractionImagesByTitle(String title) {
        Context context = GMTApp.getContext();
        ArrayList<String> images = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DestinationContract.AttractionPlaceImageEntry.buildAttractionPlaceImageUriWithAttractionTitle(title),
                null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                images.add(cursor.getString(cursor.getColumnIndex(DestinationContract.AttractionPlaceImageEntry.COLUMN_IMAGES)));
            } while (cursor.moveToNext());
        }
        String[] imageArray = new String[images.size()];
        images.toArray(imageArray);
        return imageArray;
    }

}
