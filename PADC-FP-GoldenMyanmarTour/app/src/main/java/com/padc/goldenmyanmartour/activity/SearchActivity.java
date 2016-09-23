package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.adapters.PackageAdapter;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.PackageViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    private static final String IE_SEARCH = "IE_SEARCH";

    private String fragmentName;

    @BindView(R.id.searchView)
    AutoCompleteTextView searchView;

    @BindView(R.id.iv_search)
    ImageView ivSearch;

    @BindView(R.id.gv_search_result)
    GridView gvSearchResult;

    @BindView(R.id.lv_search)
    ListView lvSearch;

    DestinationAdapter dAdapter;
    PackageAdapter pAdapter;

    DestinationViewHolder.ControllerDestinationItem dController;
    PackageViewHolder.ControllerItem pController;
    private String searchHint;


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

        fragmentName = getIntent().getStringExtra(IE_SEARCH);

        switch (fragmentName) {
            case "Home Fragment":
                            searchView.setHint("Search by destination");
                // destination list
                final String[] nameList = {"Yangon", "Mandalay", "Bagan", "Inle Lake", "Golden Rock Pagoda"};
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(GMTApp.getContext(),
                        android.R.layout.simple_dropdown_item_1line, nameList);
                searchView.setThreshold(1);
                searchView.setAdapter(mAdapter);
                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        searchHint = parent.getItemAtPosition(position).toString();
////                        searchAction(searchHint);
                         /*search function do here */
                ivSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gvSearchResult.setVisibility(View.VISIBLE);

                        dAdapter = new DestinationAdapter(null, dController);
                        gvSearchResult.setAdapter(dAdapter);
                    }
                });

                    }
                });
                break;

            case "Package Fragment":
                searchView.setHint("Search by destination");
                // destination list
                final String[] pnameList = {"Yangon", "Mandalay", "Bagan", "Inle Lake", "Golden Rock Pagoda"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(GMTApp.getContext(),
                        android.R.layout.simple_dropdown_item_1line, pnameList);
                searchView.setThreshold(1);
                searchView.setAdapter(adapter);
                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        searchHint = parent.getItemAtPosition(position).toString();
////                        searchAction(searchHint);
                         /*search function do here */
                        ivSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gvSearchResult.setVisibility(View.VISIBLE);

                                dAdapter = new DestinationAdapter(null, dController);
                                gvSearchResult.setAdapter(dAdapter);
                            }
                        });

                    }
                });
                break;
                }
        }

    // search function do here

 /*   public void searchAction(String query) {

=======
    public void searchAction(String query) {
>>>>>>> 1. change fab setVisbility from INVISIBLE to VISIBLE at HomeActivity.java;
        String searchQuery = query.toString();
        if (searchQuery.equalsIgnoreCase(searchHint)) {
            lvSearch.setVisibility(View.INVISIBLE);
            gvSearchResult.setVisibility(View.VISIBLE);

            dAdapter = new DestinationAdapter(null, dController);
            gvSearchResult.setAdapter(dAdapter);
        } else if (searchQuery.equalsIgnoreCase("20000")) {
            gvSearchResult.setVisibility(View.VISIBLE);

<<<<<<< 037983194aa024d9ec03f3af74bcb29336806d6f
        if (searchQuery.equalsIgnoreCase(searchHint)) {
            Log.d("SearchActivity", searchQuery);
            gvSearchResult.setVisibility(View.VISIBLE);

            dAdapter = new DestinationAdapter(null, dController);
            gvSearchResult.setAdapter(dAdapter);

        }
        if (searchQuery.equalsIgnoreCase(searchHint)) {
            Log.d("SearchActivity", searchQuery);
            gvSearchResult.setVisibility(View.VISIBLE);

=======
>>>>>>> 1. change fab setVisbility from INVISIBLE to VISIBLE at HomeActivity.java;
            pAdapter = new PackageAdapter(null, pController);
            gvSearchResult.setAdapter(pAdapter);
        }
    }
<<<<<<< 037983194aa024d9ec03f3af74bcb29336806d6f
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}