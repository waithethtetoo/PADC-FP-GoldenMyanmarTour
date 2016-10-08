package com.padc.goldenmyanmartour.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.PackageAdapter;
import com.padc.goldenmyanmartour.data.Models.PackageModel;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.PackageViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by WT on 10/8/2016.
 */
public class PackageSearchActivity extends BaseActivity {

    private static final String IE_PACKAGE_SEARCH = "IE_PACKAGE_SEARCH";

    private String fragmentName;
    @BindView(R.id.searchView)
    AutoCompleteTextView searchView;

    @BindView(R.id.iv_search)
    ImageView ivSearch;

    @BindView(R.id.gv_search_result)
    GridView gvSearchResult;

    PackageAdapter pAdapter;

    PackageViewHolder.ControllerItem pController;
    private String searchHint;

    List<PackageVO> searchPackageList;

    private Context context = GMTApp.getContext();

    public static Intent newIntent(String name) {
        Intent intent = new Intent(GMTApp.getContext(), PackageSearchActivity.class);
        intent.putExtra(IE_PACKAGE_SEARCH, name);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_search);
        ButterKnife.bind(this, this);

        fragmentName = getIntent().getStringExtra(IE_PACKAGE_SEARCH);

        switch (fragmentName) {
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

    public void onEvent(DataEvent.PackageDataLoaded event) {

        List<PackageVO> newPackageList = event.getPackageListBySearchText(searchHint);
        pAdapter.setNewDataBySearchText(newPackageList, searchHint);
        pAdapter.notifyDataSetChanged();
    }
}
