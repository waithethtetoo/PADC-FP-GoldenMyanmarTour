package com.padc.goldenmyanmartour.data.vo.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;


/**
 * Created by Lenovo on 9/21/2016.
 */
public class DestinationProvider extends ContentProvider {
    public static final int DESTINATION = 20;
    public static final int DESTINATION_IMAGE = 30;

    private static final String sDestinationTitle = DestinationContract.DestinationEntry.COLUMN_TITLE + "=?";
    private static final String sDestinationImageWithTitle = DestinationContract.DestinationImageEntry.COLUMN_DESTINATION_TITLE + "=?";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DestinationDBHelper mDestinationDBHelper;

    public boolean onCreate() {
        mDestinationDBHelper = new DestinationDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;
        int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case DESTINATION:
                String destinationTitle = DestinationContract.DestinationEntry.getTitleFromParam(uri);
                if (!TextUtils.isEmpty(destinationTitle)) {
                    selection = sDestinationTitle;
                    selectionArgs = new String[]{destinationTitle};
                }
                queryCursor = mDestinationDBHelper.getReadableDatabase().query(DestinationContract.DestinationEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case DESTINATION_IMAGE:
                String title = DestinationContract.DestinationImageEntry.getDestinationTitleFromParam(uri);
                if (title != null) {
                    selection = sDestinationImageWithTitle;
                    selectionArgs = new String[]{title};
                }
                queryCursor = mDestinationDBHelper.getReadableDatabase().query(DestinationContract.DestinationImageEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("unknown uri :" + uri);
        }
        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }
        return queryCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);
        switch (matchUri) {
            case DESTINATION:
                return DestinationContract.DestinationEntry.DIR_TYPE;
            case DESTINATION_IMAGE:
                return DestinationContract.DestinationImageEntry.DIR_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri :" + uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mDestinationDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;
        switch (matchUri) {
            case DESTINATION:
                long id = db.insert(DestinationContract.DestinationEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    insertedUri = DestinationContract.DestinationEntry.buildDestinationUri(id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            case DESTINATION_IMAGE:
                long _id = db.insert(DestinationContract.DestinationImageEntry.TABLE_NAME, null, values);
                if (_id > 0) {
                    insertedUri = DestinationContract.DestinationImageEntry.buildDestinationImageUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri :" + uri);
        }
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return insertedUri;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mDestinationDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int count = 0;
        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long id = db.insert(tableName, null, cv);
                if (id > 0) {
                    count++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mDestinationDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDestinationDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, values, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(DestinationContract.CONTENT_AUTHORITY, DestinationContract.PATH_DESTINATION, DESTINATION);
        uriMatcher.addURI(DestinationContract.CONTENT_AUTHORITY, DestinationContract.PATH_DESTINATION_IMAGES, DESTINATION_IMAGE);


        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case DESTINATION:
                return DestinationContract.DestinationEntry.TABLE_NAME;
            case DESTINATION_IMAGE:
                return DestinationContract.DestinationImageEntry.TABLE_NAME;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }
}
