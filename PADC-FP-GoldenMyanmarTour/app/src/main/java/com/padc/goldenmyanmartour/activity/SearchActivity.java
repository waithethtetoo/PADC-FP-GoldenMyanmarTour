package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    private static final String IE_SEARCH = "IE_SEARCH";

    String fragmentName;

    @BindView(R.id.searchView)
    SearchView searchView;

    public static Intent newIntent(String name) {
        Intent intent = new Intent(GMTApp.getContext(), SearchActivity.class);
        intent.putExtra(IE_SEARCH, name);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this, this);


        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();

        fragmentName = getIntent().getStringExtra(IE_SEARCH);
        switch (fragmentName) {
            case "Home Fragment":
                searchView.setQueryHint("Search by destination");
                break;
            case "Package Fragment":
                searchView.setQueryHint("Search by price");
                break;
        }
    }

}
