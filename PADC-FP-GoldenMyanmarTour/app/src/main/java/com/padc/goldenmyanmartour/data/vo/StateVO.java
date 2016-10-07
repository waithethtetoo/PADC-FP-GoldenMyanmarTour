package com.padc.goldenmyanmartour.data.vo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;

/**
 * Created by Lenovo on 9/21/2016.
 */
public class StateVO {
    @SerializedName("state-id")
    private long stateId;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    private static StateVO parseFromCursor(Cursor cursor) {
        StateVO stateVO = new StateVO();
        stateVO.stateId = cursor.getLong(cursor.getColumnIndex(DestinationContract.StateEntry.COLUMN_ID));
        stateVO.name = cursor.getString(cursor.getColumnIndex(DestinationContract.StateEntry.COLUMN_NAME));
        stateVO.description = cursor.getString(cursor.getColumnIndex(DestinationContract.StateEntry.COLUMN_DESC));

        return stateVO;
    }

    public static StateVO loadStateByDestinationTitle(String title) {
        Context context = GMTApp.getContext();
        Cursor stateCursor = context.getContentResolver().query(DestinationContract.StateEntry.CONTENT_URI,
                null,
                DestinationContract.StateEntry.COLUMN_DESTINATION_TITLE + " = ?",
                new String[]{title},
                null);
        if (stateCursor != null && stateCursor.moveToFirst()) {
            return StateVO.parseFromCursor(stateCursor);
        }
        return null;

    }

    public static void saveStateByDestinationTitle(String title, StateVO stateVO) {
        Context context = GMTApp.getContext();
        ContentValues cv = new ContentValues();
        cv.put(DestinationContract.StateEntry.COLUMN_ID, stateVO.getStateId());
        cv.put(DestinationContract.StateEntry.COLUMN_NAME, stateVO.getName());
        cv.put(DestinationContract.StateEntry.COLUMN_DESC, stateVO.getDescription());
        cv.put(DestinationContract.StateEntry.COLUMN_DESTINATION_TITLE, title);
        Uri insertedUri = context.getContentResolver().insert(DestinationContract.StateEntry.CONTENT_URI, cv);
        Log.d(GMTApp.TAG, " Location Inserted Uri : " + insertedUri);
    }


}
