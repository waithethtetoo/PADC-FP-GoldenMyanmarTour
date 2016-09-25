package com.padc.goldenmyanmartour.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padc.goldenmyanmartour.data.persistence.DestinationContract.DestinationEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.DestinationImageEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.LocationEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.CityEntry;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract.StateEntry;

/**
 * Created by WT on 9/22/2016.
 */
public class DestinationDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 10;
    public static final String DATABASE_NAME = "destination.db";

    private static final String SQL_CREATE_DESTINATION_TABLE = "CREATE TABLE" + DestinationEntry.TABLE_NAME + "(" +
            DestinationEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
            DestinationEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
            DestinationEntry.COLUMN_NOTE_TO_VISITOR + "TEXT NOT NULL," +
            "UNIQUE (" + DestinationEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            ");";
    private static final String SQL_CREATE_DESTINATION_IMAGE_TABLE = "CREATE TABLE" + DestinationImageEntry.TABLE_NAME + "(" +
            DestinationImageEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
            DestinationImageEntry.COLUMN_DESTINATION_TITLE + "TEXT NOT NULL," +
            DestinationImageEntry.COLUMN_IMAGE + "TEXT NOT NULL," +
            "UNIQUE (" + DestinationImageEntry.COLUMN_IMAGE + ") ON CONFLICT IGNORE" +
            ");";

    private static final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE" + LocationEntry.TABLE_NAME + "(" +
            LocationEntry.COLUMN_LAT + "TEXT NOT NULL, " +
            LocationEntry.COLUMN_LNG + "TEXT NOT NULL, " +
            LocationEntry.COLUMN_ADDRESS + "TEXT NOT NULL," +
            LocationEntry.COLUMN_CITY_ID + "LONG NOT NULL," +
            LocationEntry.COLUMN_STATE_ID + "LONG NOT NULL," +
            "UNIQUE (" + LocationEntry.COLUMN_ADDRESS + ")ON CONFLICT IGNORE" +
            ");";


    private static final String SQL_CREATE_CITY_TABLE = "CREATE TABLE" + CityEntry.TABLE_NAME + "(" +
            CityEntry.COLUMN_ID + "LONG PRIMARY KEY AUTOINCREMENT," +
            CityEntry.COLUMN_NAME + "TEXT NOT NULL," +
            CityEntry.COLUMN_DESC + "TEXT NOT NULL," +
            "UNIQUE (" + CityEntry.COLUMN_ID + ") ON CONFLICT IGNORE" +
            ");";

    private static final String SQL_CREATE_STATE_TABLE = "CREATE TABLE" + StateEntry.TABLE_NAME + "(" +
            StateEntry.COLUMN_ID + "LONG PRIMARY KEY AUTOINCREMENT," +
            StateEntry.COLUMN_NAME + "TEXT NOT NULL, " +
            StateEntry.COLUMN_DESC + "TEXT NOT NULL, " +
            "UNIQUE (" + StateEntry.COLUMN_ID + ") ON CONFLICT IGNORE " +
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + DestinationImageEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DestinationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+LocationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+CityEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+StateEntry.TABLE_NAME);
        onCreate(db);
    }
}
