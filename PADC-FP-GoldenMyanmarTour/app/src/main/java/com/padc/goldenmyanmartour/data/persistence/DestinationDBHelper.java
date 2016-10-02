package com.padc.goldenmyanmartour.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padc.goldenmyanmartour.data.persistence.DestinationContract.DestinationEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.DestinationImageEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.LocationEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.CityEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.StateEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.AttractionPlacesEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.AttractionPlaceImageEntry;

/**
 * Created by WT on 9/22/2016.
 */
public class DestinationDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 14;
    public static final String DATABASE_NAME = "golden_myanmar.db";

    private static final String SQL_CREATE_DESTINATION_TABLE = "CREATE TABLE " + DestinationEntry.TABLE_NAME + " (" +
            DestinationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DestinationEntry.COLUMN_ID + " LONG NOT NULL, " +
            DestinationEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            DestinationEntry.COLUMN_SORTED_ORDER + " INTEGER, " +
            DestinationEntry.COLUMN_NOTE_TO_VISITOR + " TEXT, " +
            " UNIQUE (" + DestinationEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            ");";

    private static final String SQL_CREATE_DESTINATION_IMAGE_TABLE = "CREATE TABLE " + DestinationImageEntry.TABLE_NAME + " (" +
            DestinationImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DestinationImageEntry.COLUMN_DESTINATION_TITLE + " TEXT NOT NULL, " +
            DestinationImageEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
            " UNIQUE (" + DestinationImageEntry.COLUMN_DESTINATION_TITLE + "," +
            DestinationImageEntry.COLUMN_IMAGE + ") ON CONFLICT IGNORE" +
            ");";

    private static final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + LocationEntry.TABLE_NAME + " (" +
            LocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            LocationEntry.COLUMN_DESTINATION_TITLE + " TEXT, " +
            LocationEntry.COLUMN_LAT + " TEXT,  " +
            LocationEntry.COLUMN_LNG + " TEXT, " +
            LocationEntry.COLUMN_ADDRESS + " TEXT, " +
            LocationEntry.COLUMN_CITY_ID + " LONG, " +
            LocationEntry.COLUMN_STATE_ID + " LONG, " +
            " UNIQUE (" + LocationEntry.COLUMN_DESTINATION_TITLE + " , " +
            LocationEntry.COLUMN_ADDRESS + ")ON CONFLICT IGNORE" +
            ");";

    private static final String SQL_CREATE_CITY_TABLE = "CREATE TABLE " + CityEntry.TABLE_NAME + " (" +
            CityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CityEntry.COLUMN_DESTINATION_TITLE + " TEXT, " +
            CityEntry.COLUMN_ID + " LONG, " +
            CityEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            CityEntry.COLUMN_DESC + " TEXT NOT NULL, " +
            " UNIQUE (" + CityEntry.COLUMN_DESTINATION_TITLE + " , " +
            CityEntry.COLUMN_NAME + ") ON CONFLICT IGNORE" +
            ");";

    private static final String SQL_CREATE_STATE_TABLE = "CREATE TABLE " + StateEntry.TABLE_NAME + " (" +
            StateEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            StateEntry.COLUMN_DESTINATION_TITLE + " TEXT, " +
            StateEntry.COLUMN_ID + " LONG, " +
            StateEntry.COLUMN_NAME + " TEXT NOT NULL, " +
            StateEntry.COLUMN_DESC + " TEXT NOT NULL, " +
            " UNIQUE (" + StateEntry.COLUMN_DESTINATION_TITLE + " , " +
            StateEntry.COLUMN_NAME + ") ON CONFLICT IGNORE " +
            ");";

    private static final String SQL_CREATE_ATTRACTION_PLACES_TABLE = "CREATE TABLE " + AttractionPlacesEntry.TABLE_NAME + " (" +
            AttractionPlacesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AttractionPlacesEntry.COLUMN_DESTINATION_TITLE + " TEXT, " +
            AttractionPlacesEntry.COLUMN_PLACES_ID + " LONG, " +
            AttractionPlacesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            AttractionPlacesEntry.COLUMN_DESC + " TEXT NOT NULL, " +
            AttractionPlacesEntry.COLUMN_NOTE + " TEXT, " +
            " UNIQUE (" + AttractionPlacesEntry.COLUMN_DESTINATION_TITLE + " , " +
            AttractionPlacesEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            ");";

    private static final String SQL_CREATE_ATTRACTION_PLACE_IMAGES_TABLE = "CREATE TABLE " + AttractionPlaceImageEntry.TABLE_NAME + " (" +
            AttractionPlaceImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            AttractionPlaceImageEntry.COLUMN_ATTRACTION_TITLE + " TEXT NOT NULL, " +
            AttractionPlaceImageEntry.COLUMN_IMAGES + " TEXT NOT NULL, " +
            " UNIQUE (" + AttractionPlaceImageEntry.COLUMN_ATTRACTION_TITLE + "," +
            AttractionPlaceImageEntry.COLUMN_IMAGES + ")ON CONFLICT IGNORE" +
            ");";

    public DestinationDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DESTINATION_TABLE);
        db.execSQL(SQL_CREATE_DESTINATION_IMAGE_TABLE);
        db.execSQL(SQL_CREATE_LOCATION_TABLE);
        db.execSQL(SQL_CREATE_CITY_TABLE);
        db.execSQL(SQL_CREATE_STATE_TABLE);
        db.execSQL(SQL_CREATE_ATTRACTION_PLACES_TABLE);
        db.execSQL(SQL_CREATE_ATTRACTION_PLACE_IMAGES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DestinationImageEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DestinationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CityEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StateEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AttractionPlacesEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + AttractionPlaceImageEntry.TABLE_NAME);
        onCreate(db);
    }
}
