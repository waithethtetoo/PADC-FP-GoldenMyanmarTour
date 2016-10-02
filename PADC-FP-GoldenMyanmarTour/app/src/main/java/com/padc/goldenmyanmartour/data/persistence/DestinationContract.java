package com.padc.goldenmyanmartour.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import com.padc.goldenmyanmartour.GMTApp;

import retrofit2.http.PUT;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class DestinationContract {

    public static final String CONTENT_AUTHORITY = GMTApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DESTINATION = "destinations";
    public static final String PATH_DESTINATION_IMAGES = "destination_images";
    public static final String PATH_LOCATION = "locations";
    public static final String PATH_CITY = "city";
    public static final String PATH_STATE = "state";
    public static final String PATH_ATTRACTION_PLACES = "attraction_places";
    public static final String PATH_ATTRACTION_PLACE_IMAGE = "attraction_place_images";

    public static final String PATH_FESTIVAL = "festivals";
    public static final String PATH_FESTIVAL_IMAGES = "festival_images";
    public static final String PATH_HOTEL = "hotels";
    public static final String PATH_HOTEL_IMAGES = "hotel_images";
    public static final String PATH_PACKAGE = "packages";
    public static final String PATH_PACKAGE_IMAGES = "package_images";

    public static final String TAG = "Contract";

    /*Destination */
    public static final class DestinationEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;

        public static final String TABLE_NAME = "destinations";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_SORTED_ORDER = "sorted_order";
        public static final String COLUMN_NOTE_TO_VISITOR = "note";

        public static Uri buildDestinationUri(long id) {
            Log.d(GMTApp.TAG, GMTApp.class.getPackage().getName());
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

    /*Destination Image*/
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

    /*Location */
    public static final class LocationEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_LOCATION).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LOCATION;

        public static final String TABLE_NAME = "locations";
        public static final String COLUMN_LAT = "lat";
        public static final String COLUMN_LNG = "lng";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CITY_ID = "city_id";
        public static final String COLUMN_STATE_ID = "state_id";
        public static final String COLUMN_DESTINATION_TITLE = "destination_title";

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildLocationUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DESTINATION_TITLE, title)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DESTINATION_TITLE);
        }
    }

    /*City */
    public static final class CityEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CITY).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CITY;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CITY;
        public static final String TABLE_NAME = "city";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_DESTINATION_TITLE = "destination_title";

        public static Uri buildCityUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildCityUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DESTINATION_TITLE, name)
                    .build();
        }

        public static String getNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DESTINATION_TITLE);
        }
    }

    /*State */
    public static final class StateEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_STATE).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STATE;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STATE;

        public static final String TABLE_NAME = "state";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_DESTINATION_TITLE = "destination_title";

        public static Uri buildStateUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildStateUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DESTINATION_TITLE, name)
                    .build();
        }

        public static String getNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DESTINATION_TITLE);
        }
    }

    /*Attraction Places */
    public static final class AttractionPlacesEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ATTRACTION_PLACES).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_PLACES;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_PLACES;

        public static final String TABLE_NAME = "attraction_places";
        public static final String COLUMN_PLACES_ID = "places_id";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_NOTE = "note";
        public static final String COLUMN_DESTINATION_TITLE = "destination_title";

        public static Uri buildPlaceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildPlaceUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DESTINATION_TITLE, name)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_DESTINATION_TITLE);
        }
    }

    /*Attraction Places Image*/
    public static final class AttractionPlaceImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ATTRACTION_PLACE_IMAGE).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_PLACE_IMAGE;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ATTRACTION_PLACE_IMAGE;
        public static final String TABLE_NAME = "attraction_place_images";
        public static final String COLUMN_ATTRACTION_TITLE = "attraction_title";
        public static final String COLUMN_IMAGES = "images";

        public static Uri buildAttractionPlaceImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildAttractionPlaceImageUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_ATTRACTION_TITLE, title)
                    .build();
        }

        public static String getAttractionPlaceTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_ATTRACTION_TITLE);
        }
    }

    /*Festival  */
    public static final class FestivalEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String TABLE_NAME = "festival";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_NOTE_TO_VISITOR = "note";
        public static final String COLUMN_DIRECTION = "direction";

        public static Uri buildFestivalUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFestivalUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_NAME, name)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_NAME);
        }
    }

    /*Festival Image*/
    public static final class FestivalImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;

        public static final String TABLE_NAME = "festival_image";

        public static final String COLUMN_FESTIVAL_TITLE = "festival_title";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildFestivalImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildFestivalImageUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_FESTIVAL_TITLE, title)
                    .build();
        }

        public static String getFestivalTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_FESTIVAL_TITLE);
        }
    }

    /*Package */
    public static final class PackageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String TABLE_NAME = "package";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_TOTAL_DAY = "total_day";

        public static Uri buildPackageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildPackageUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_NAME, name)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_NAME);
        }
    }

    /*Package Image*/
    public static final class PackageImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;

        public static final String TABLE_NAME = "package_image";
        public static final String COLUMN_PACKAGE_TITLE = "package_title";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildPackageImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildPackageImageUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_PACKAGE_TITLE, title)
                    .build();
        }

        public static String getPackageTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_PACKAGE_TITLE);
        }

    }

    /*Hotel*/
    public static final class HotelEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION;
        public static final String TABLE_NAME = "hotel";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_DIRECTION = "direction";

        public static Uri buildHotelUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHotelUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_NAME, name)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_NAME);
        }
    }

    /*Hotel Image */
    public static final class HotelImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESTINATION_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DESTINATION_IMAGES;

        public static final String TABLE_NAME = "hotel_image";
        public static final String COLUMN_HOTEL_TITLE = "hotel_title";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildHotelImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildHotelImageUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_HOTEL_TITLE, title)
                    .build();
        }

        public static String getHotelTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_HOTEL_TITLE);
        }

    }
}
