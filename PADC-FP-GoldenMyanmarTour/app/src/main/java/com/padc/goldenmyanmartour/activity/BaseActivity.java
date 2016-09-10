package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;

import com.padc.goldenmyanmartour.R;

/**
 * Created by hp user on 9/10/2016.
 */
public class BaseActivity extends AppCompatActivity{

    protected void sendViaShareIntent(String msg) {
        startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(BaseActivity.this)
                .setType("text/plain")
                .setText(msg)
                .getIntent(), "Share"));
    }
}
