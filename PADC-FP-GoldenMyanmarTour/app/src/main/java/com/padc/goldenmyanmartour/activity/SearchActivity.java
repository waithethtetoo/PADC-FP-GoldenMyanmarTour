package com.padc.goldenmyanmartour.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.adapters.PackageAdapter;
import com.padc.goldenmyanmartour.data.Models.DestinationModel;
import com.padc.goldenmyanmartour.data.Models.PackageModel;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.LocationVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.PackageViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class SearchActivity extends BaseActivity {

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

    DestinationViewHolder.ControllerDestinationItem dController;
    PackageViewHolder.ControllerItem pController;
    private String searchHint;

    List<DestinationVO> searchDestList;
    List<PackageVO> searchPackageList;

    private Context context = GMTApp.getContext();

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
                final String[] nameList = {"Yangon", "Bagan", "Mandalay", "Inle", "Maruk U", "Nay Pyi Taw"};
//                String name = destinationVO.getTitle();
//                final String[] nameList = {name};
                ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_dropdown_item_1line, nameList);
                searchView.setThreshold(1);
                searchView.setAdapter(mAdapter);
                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        searchHint = parent.getItemAtPosition(position).toString();
                        Log.d("SearchActivity", searchHint);
                         /*search function do here */
                        searchDestList = DestinationModel.getInstance().getDestinationListBySearchText(searchHint);

                        Log.d("SearchList", searchDestList.toString());
                        ivSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                gvSearchResult.setVisibility(View.VISIBLE);
                                dAdapter = new DestinationAdapter(searchDestList, dController);
                                gvSearchResult.setAdapter(dAdapter);

                            }
                        });

                    }
                });
                break;

            case "Package Fragment":
                searchView.setHint("Search by package destination");
                // destination list
                final String[] destList = {"Yangon-Bago-Thanlyin-Yangon",
                        "Kawthaung-Taung La Bo- 115 Island-Nga Man Island-Kyun Phila-Myauk Ni-Thay Yae Island-Kawthaung",
                        "Pindaya-Htut Ni-See Kya Inn-Yazakyi-Taung Myint Gyi-Ya Gyi",
                        "Yangon-Thanwe-Yangon (Golfing at the most beautiful beach)",
                        "Yangon-Bagan-Mandalay-Yangon"};

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_dropdown_item_1line, destList);
                searchView.setThreshold(1);
                searchView.setAdapter(adapter);
                searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        searchHint = parent.getItemAtPosition(position).toString();

                        /*search function do here */
                        searchPackageList = PackageModel.getInstance().getPackageBySearchText(searchHint);

                        ivSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                gvSearchResult.setVisibility(View.VISIBLE);

                                pAdapter = new PackageAdapter(searchPackageList, pController);
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

            dAdapter = new DestinationAdapter(destinationVOs, dController);
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
    public void onStart() {
        super.onStart();
        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //For Network Layer
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEvent(DataEvent.DestinationDataLoaded event) {
//        String extra = event.getExtraMessage();
//        Toast.makeText(GMTApp.getContext(), "Extra : " + extra, Toast.LENGTH_SHORT).show();

        List<DestinationVO> newDestList = event.getDestinationListBySearchText(searchHint);
        dAdapter.setNewDataBySearchText(newDestList, searchHint);
        dAdapter.notifyDataSetChanged();

//        List<PackageVO> newPackageList = pEvent.getPackageListBySearchText(searchHint);
//        pAdapter.setNewDataBySearchText(newPackageList, searchHint);
//        pAdapter.notifyDataSetChanged();
    }
}