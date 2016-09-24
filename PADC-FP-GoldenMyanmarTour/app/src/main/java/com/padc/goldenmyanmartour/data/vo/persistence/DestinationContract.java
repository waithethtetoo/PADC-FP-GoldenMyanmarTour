package com.padc.goldenmyanmartour.data.vo.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.padc.goldenmyanmartour.GMTApp;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class DestinationContract {
    public static final String CONTENT_AUTHORITY = GMTApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DESTINATION = "";
    public static final String PATH_DESTINATION_IMAGES = "";
    public static final String PATH_FESTIVAL = "";
    public static final String PATH_FESTIVAL_IMAGES = "";


    public static final class DestinationEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String TABLE_NAME = "destinations";
        public static final String COLUMN_TITLE = "title";

        public static Uri buildDestinationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildDestinationUriWithTitle(String destTitle) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TITLE, destTitle)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TITLE);
        }
    }


    public static final class DestinationImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION_IMAGES).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;
        public static final String TABLE_NAME = "destination_images";
        public static final String COLUMN_DESTINATION_TITLE = "destination_title";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildDestinationImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildDestinationImageUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DESTINATION_TITLE, title)
                    .build();
        }

        public static String getDestinationTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DESTINATION_TITLE);
        }
    }


    public static final class FestivalEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FESTIVAL).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL;
        public static final String TABLE_NAME = "festivals";
        public static final String COLUMN_NAME = "name";

        public static Uri buildFestivalUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFestivalUriWithTitle(String festivalName) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_NAME, festivalName)
                    .build();
        }

        public static String getNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_NAME);
        }
    }

    public static final class FestivalImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FESTIVAL_IMAGES).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL_IMAGES;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL_IMAGES;
        public static final String TABLE_NAME = "festival_images";
        public static final String COLUMN_FESTIVAL_NAME = "festival_name";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildFestivalImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFestivalImageUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_FESTIVAL_NAME, title)
                    .build();
        }

        public static String getFestivalTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_FESTIVAL_NAME);
        }
    }
}
