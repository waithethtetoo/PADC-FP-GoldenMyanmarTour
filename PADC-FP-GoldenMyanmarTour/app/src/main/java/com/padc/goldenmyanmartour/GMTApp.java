package com.padc.goldenmyanmartour;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.data.Models.DestinationModel;

import java.util.concurrent.ExecutionException;

/**
 * Created by WT on 9/4/2016.
 */
public class GMTApp extends Application {

    public static final String TAG = "GoldenMyanmarApp";

    private static Context context;
    private static Bitmap destinationSight;

    public static GMTApp INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        INSTANCE = this;
    }

    public static Context getContext() {
        return context;
    }

    public static Bitmap getDestinationSight() {
        return destinationSight;
    }

    private void encodeDestinationSight() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Context context = GMTApp.getContext();
                int largeWidth = context.getResources().getDimensionPixelSize(R.dimen.sight_width);
                int largeHeight = context.getResources().getDimensionPixelSize(R.dimen.sight_height);
                try {
                    destinationSight = Glide.with(context)
                            .load(DestinationModel.getInstance().getRandomDestinationImage())
                            .asBitmap()
                            .into(largeWidth, largeHeight)
                            .get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
