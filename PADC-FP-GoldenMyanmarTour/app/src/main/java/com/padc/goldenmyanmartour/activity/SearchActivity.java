package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;

public class SearchActivity extends AppCompatActivity {
    private static final String IE_SEARCH = "IE_SEARCH";

    public static Intent newIntent() {
        Intent intent = new Intent(GMTApp.getContext(), SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
