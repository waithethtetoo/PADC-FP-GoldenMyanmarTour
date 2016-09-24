package com.padc.goldenmyanmartour.data.vo.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract.DestinationEntry;
import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract.DestinationImageEntry;

/**
 * Created by WT on 9/22/2016.
 */
public class DestinationDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "destinations.db";

    private static final String SQL_CREATE_DESTINATION_TABLE = "CREATE TABLE " + DestinationEntry.TABLE_NAME + " (" +
            DestinationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DestinationEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
            " UNIQUE (" + DestinationEntry.COLUMN_TITLE + ") ON CONFLICT IGNORE" +
            " );";

    private static final String SQL_CREATE_DESTINATION_IMAGE_TABLE = "CREATE TABLE " + DestinationImageEntry.TABLE_NAME + " (" +
            DestinationImageEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DestinationImageEntry.COLUMN_DESTINATION_TITLE + " TEXT NOT NULL, " +
            DestinationImageEntry.COLUMN_IMAGE + " TEXT NOT NULL, " +
            " UNIQUE (" + DestinationImageEntry.COLUMN_DESTINATION_TITLE + ", " +
            DestinationImageEntry.COLUMN_IMAGE + ") ON CONFLICT IGNORE" +
            " );";

    public DestinationDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DESTINATION_TABLE);
        db.execSQL(SQL_CREATE_DESTINATION_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DestinationEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DestinationImageEntry.TABLE_NAME);

        onCreate(db);
    }
}
