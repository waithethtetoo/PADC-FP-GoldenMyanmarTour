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

    DestinationAdapter dAdapter;
    PackageAdapter pAdapter;

    DestinationVO destinationVO;

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
                final String[] nameList = {"Yangon", "Bagan", "Mandalay", "Inle Lake", "Maruk U", "Nay Pyi Taw"};
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
                searchView.setHint("Search by package destination");
                // destination list
                final String[] destList = {"Yangon", "Mandalay", "Bagan", "Inle Lake", "Golden Rock Pagoda"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(GMTApp.getContext(),
                        android.R.layout.simple_dropdown_item_1line, destList);
                searchView.setThreshold(1);
                searchView.setAdapter(adapter);
                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        searchHint = parent.getItemAtPosition(position).toString();
//                        searchAction(searchHint);
                        /*search function do here */
                        ivSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gvSearchResult.setVisibility(View.VISIBLE);

                                pAdapter = new PackageAdapter(null, pController);
                                gvSearchResult.setAdapter(pAdapter);
                            }
                        });

                    }
                });
                break;
        }
    }

    // search function do here
 /*   public void searchAction(String query) {

        String searchQuery = query.toString();

        if (searchQuery.equalsIgnoreCase(searchHint)) {
            Log.d("SearchActivity", searchQuery);
            gvSearchResult.setVisibility(View.VISIBLE);

            dAdapter = new DestinationAdapter(null, dController);
            gvSearchResult.setAdapter(dAdapter);

        }
        if (searchQuery.equalsIgnoreCase(searchHint)) {
            Log.d("SearchActivity", searchQuery);
            gvSearchResult.setVisibility(View.VISIBLE);

            pAdapter = new PackageAdapter(null, pController);
            gvSearchResult.setAdapter(pAdapter);
        }
    }
*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}