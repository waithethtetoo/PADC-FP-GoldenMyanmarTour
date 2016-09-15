package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.adapters.PackageAdapter;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.PackageViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {
    private static final String IE_SEARCH = "IE_SEARCH";

    String fragmentName;

    @BindView(R.id.searchView)
    SearchView searchView;

    @BindView(R.id.gv_search_result)
    GridView gvSearchResult;

    DestinationAdapter dAdapter;
    PackageAdapter pAdapter;

    DestinationViewHolder.ControllerDestinationItem dController;
    PackageViewHolder.ControllerItem pController;

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
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        dAdapter = new DestinationAdapter(null, dController);
                        gvSearchResult.setAdapter(dAdapter);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        return false;
                    }
                });

                break;
            case "Package Fragment":
                searchView.setQueryHint("Search by price");
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        pAdapter = new PackageAdapter(null, pController);
                        gvSearchResult.setAdapter(pAdapter);
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

                break;
        }
    }

    public void searchAction(String query) {
        String searchQuery = query.toString();

    }
}
