package com.padc.goldenmyanmartour;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.data.Models.DestinationModel;
import com.padc.goldenmyanmartour.data.agent.RetrofitDataAgent;

import java.util.concurrent.ExecutionException;

/**
 * Created by WT on 9/4/2016.
 */
public class GMTApp extends Application {

    public static final String TAG = "GoldenMyanmarApp";

    private static Context context;

    public static String DESTINATION_BASE_URL = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        RetrofitDataAgent.getInstance().loadDestinations();
    }

    public static Context getContext() {
        return context;
    }

}
