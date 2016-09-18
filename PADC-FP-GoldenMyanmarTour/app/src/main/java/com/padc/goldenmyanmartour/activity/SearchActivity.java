package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;

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

    private String fragmentName;

    @BindView(R.id.searchView)
    SearchView searchView;

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

        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();

        fragmentName = getIntent().getStringExtra(IE_SEARCH);

        switch (fragmentName) {
            case "Home Fragment":
                searchView.setQueryHint("Search by destination");
                // destination list
                final String[] nameList = {"Yangon", "Mandalay", "Bagan", "Inle Lake", "Golden Rock Pagoda"};
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(GMTApp.getContext(),
                        android.R.layout.simple_list_item_1, nameList);
                lvSearch.setAdapter(mAdapter);
                lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        searchHint = lvSearch.getItemAtPosition(position).toString();
                        searchView.setQuery(searchHint, false); // show ListView selected item
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
//                                searchAction(query);
                                lvSearch.setVisibility(View.INVISIBLE);
                                gvSearchResult.setVisibility(View.VISIBLE);

                                dAdapter = new DestinationAdapter(null, dController);
                                gvSearchResult.setAdapter(dAdapter);
                                return true;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                lvSearch.setVisibility(View.VISIBLE);
                                gvSearchResult.setVisibility(View.INVISIBLE);
                                return false;
                            }
                        });
                    }
                });

                break;

            case "Package Fragment":
                searchView.setQueryHint("Search by Destination in Package ");

                // package destination list
                final String[] destList = {"Yangon", "Bago", "Kyaikhtiyo", "Bagan", "Mt.Popa"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(GMTApp.getContext(),
                        android.R.layout.simple_list_item_1, destList);
                lvSearch.setAdapter(adapter);
                lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        searchHint = lvSearch.getItemAtPosition(position).toString();
                        searchView.setQuery(searchHint, false); // show ListView selected item
                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
//                                searchAction(query);
                                lvSearch.setVisibility(View.INVISIBLE);
                                gvSearchResult.setVisibility(View.VISIBLE);

                                pAdapter = new PackageAdapter(null, pController);
                                gvSearchResult.setAdapter(pAdapter);

                                return true;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                lvSearch.setVisibility(View.VISIBLE);
                                gvSearchResult.setVisibility(View.INVISIBLE);
                                return false;
                            }
                        });
                    }
                });
                break;
        }
    }

    // search function do here
   /* public void searchAction(String query) {
        String searchQuery = query.toString();

        if (searchQuery.equalsIgnoreCase(searchHint)) {
            Log.d("SearchActivity", searchQuery);
            lvSearch.setVisibility(View.INVISIBLE);
            gvSearchResult.setVisibility(View.VISIBLE);

            dAdapter = new DestinationAdapter(null, dController);
            gvSearchResult.setAdapter(dAdapter);

        } else if (searchQuery.equalsIgnoreCase(searchHint)) {
            Log.d("SearchActivity", searchQuery);
            lvSearch.setVisibility(View.INVISIBLE);
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
