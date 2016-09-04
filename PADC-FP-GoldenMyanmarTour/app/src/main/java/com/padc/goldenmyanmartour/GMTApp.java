package com.padc.goldenmyanmartour;

import android.app.Application;
import android.content.Context;

/**
 * Created by WT on 9/4/2016.
 */
public class GMTApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
