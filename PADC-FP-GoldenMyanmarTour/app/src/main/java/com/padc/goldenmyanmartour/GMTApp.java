package com.padc.goldenmyanmartour;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

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

    public static GMTApp objInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        objInstance = this;

        RetrofitDataAgent.getInstance().loadDestinations();
        RetrofitDataAgent.getInstance().loadPackages();
//        RetrofitDataAgent.getInstance().loadHotels();
//        RetrofitDataAgent.getInstance().loadFestivals();
    }

    public static Context getContext() {
        return context;
    }
}
