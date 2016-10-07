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
    /**
     * public static final String PATH_FESTIVAL = "festivals";
     * public static final String PATH_FESTIVAL_PERIOD = "festival_period";
     * public static final String PATH_FESTIVAL_IMAGES = "festival_images";
     * <p/>
     * public static final String PATH_HOTEL = "hotels";
     * public static final String PATH_HOTEL_IMAGES = "hotel_images";
     * <p/>
     * public static final String PATH_PACKAGE = "packages";
     * public static final String PATH_PACKAGE_DESC = "package_desc";
     * public static final String PATH_PACKAGE_IMAGES = "package_images";
     * <p/>
     * public static final String PATH_TOUR_COMPANY = "tour_company";
     * public static final String PATH_TOUR_COMPANY_PHOTOS = "tour_company_photos";
     * public static final String PATH_TOUR_COMPANY_PHONES = "tour_company_phones";
     */
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

        public static Uri buildLocationUriWithDestinationTitle(String title) {
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

        public static Uri buildCityUriWithDestinationName(String name) {
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

        public static Uri buildStateUriWithDestinationName(String name) {
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

        public static Uri buildPlaceUriWithDestinationTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_DESTINATION_TITLE, title)
                    .build();
        }

        public static String getNameFromParam(Uri uri) {
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

        public static Uri buildAttractionPlaceImageUriWithAttractionTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_ATTRACTION_TITLE, title)
                    .build();
        }

        public static String getNameFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_ATTRACTION_TITLE);
        }
    }
/*

    //Package
    public static final class PackageEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PACKAGE).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PACKAGE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PACKAGE;

        public static final String TABLE_NAME = "packages";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_TOTAL_DAY = "total_day";
        public static final String COLUMN_DESTINATION_ID = "destination_id";
        public static final String COLUMN_TOUR_COMPANY_ID = "tour_company_id";

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


    public static final class PackageDescriptionEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PACKAGE_DESC).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PACKAGE_DESC;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PACKAGE_DESC;

        public static final String TABLE_NAME = "package_desc";
        public static final String COLUMN_PACKAGE_ID = "package_id";
        public static final String COLUMN_DESC = "package_desc";

        public static Uri buildPackageDescUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildPackageDescUriWithId(String id) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_PACKAGE_ID, id)
                    .build();
        }

        public static String getPackageDescFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_PACKAGE_ID);
        }

    }


    public static final class PackageImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PACKAGE_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PACKAGE_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PACKAGE_IMAGES;

        public static final String TABLE_NAME = "package_image";
        public static final String COLUMN_PACKAGE_ID = "package_id";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildPackageImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildPackageImageUriWithPackageId(long packageId) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_PACKAGE_ID, String.valueOf(packageId))
                    .build();
        }

        public static String getPackageTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_PACKAGE_ID);
        }

    }

    public static final class TourCompanyEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TOUR_COMPANY).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOUR_COMPANY;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOUR_COMPANY;

        public static final String TABLE_NAME = "tour_company";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_PACKAGE_ID = "package_id";
        public static final String COLUMN_CITY_ID = "city_id";
        public static final String COLUMN_STATE_ID = "state_id";

        public static Uri buildTourCompanyUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildTourCompanyUriWithName(String name) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_NAME, name)
                    .build();
        }

        public static String getTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_NAME);
        }
    }


    public static final class TourCompanyImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TOUR_COMPANY_PHOTOS).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOUR_COMPANY_PHOTOS;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOUR_COMPANY_PHOTOS;

        public static final String TABLE_NAME = "tour_company_image";
        public static final String COLUMN_TOUR_COMPANY_NAME = "tour_company_name";
        public static final String COLUMN_IMAGE = "image";

        public static Uri buildTourCompanyImageUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildTourCompanyImageUriWithTitle(String title) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TOUR_COMPANY_NAME, title)
                    .build();
        }

        public static String getTourCompanyTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TOUR_COMPANY_NAME);
        }

    }

    public static final class TourCompanyPhoneEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TOUR_COMPANY_PHONES).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOUR_COMPANY_PHONES;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TOUR_COMPANY_PHONES;

        public static final String TABLE_NAME = "tour_company_photo";
        public static final String COLUMN_TOUR_COMPANY_ID = "tour_company_id";
        public static final String COLUMN_PHONE = "phone";

        public static Uri buildTourCompanyPhoneUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildTourCompanyPhoneUriWithCompanyId(long companyId) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_TOUR_COMPANY_ID, String.valueOf(companyId))
                    .build();
        }

        public static String getTourCompanyIdFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_TOUR_COMPANY_ID);
        }
    }

    public static final class FestivalEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FESTIVAL).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL;

        public static final String TABLE_NAME = "festival";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_NOTE_TO_VISITOR = "note";
        public static final String COLUMN_DIRECTION = "direction";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_START_TIME = "start_time";
        public static final String COLUMN_END_TIME = "end_time";
        public static final String COLUMN_CITY_ID = "city_id";
        public static final String COLUMN_STATE_ID = "state_id";


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
*/
    /*
        public static final class FestivalPeriodEntry implements BaseColumns {
            public static final Uri CONTENT_URI =
                    BASE_CONTENT_URI.buildUpon().appendPath(PATH_FESTIVAL_PERIOD).build();
            public static final String DIR_TYPE =
                    ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL_PERIOD;

            public static final String ITEM_TYPE =
                    ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL_PERIOD;

            public static final String TABLE_NAME = "festival_period";
            public static final String COLUMN_START_DATE = "start_date";
            public static final String COLUMN_END_DATE = "end_date";
            public static final String COLUMN_START_TIME = "start_time";
            public static final String COLUMN_END_TIME = "end_time";
            public static final String COLUMN_FESTIVAL_TITLE = "festival_title";

            public static Uri buildFestivalPeriodUri(long id) {
                return ContentUris.withAppendedId(CONTENT_URI, id);
            }

            public static Uri buildFestivalPeriodUriWithTitle(String title) {
                return CONTENT_URI.buildUpon()
                        .appendQueryParameter(COLUMN_FESTIVAL_TITLE, title)
                        .build();
            }

            public static String getFestivalPeriodFromParam(Uri uri) {
                return uri.getQueryParameter(COLUMN_FESTIVAL_TITLE);
            }
        }


    public static final class FestivalImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FESTIVAL_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FESTIVAL_IMAGES;

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
*/
/*
    public static final class HotelEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HOTEL).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HOTEL;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HOTEL;
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


    public static final class HotelImageEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_HOTEL_IMAGES).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HOTEL_IMAGES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_HOTEL_IMAGES;

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

    */
}
