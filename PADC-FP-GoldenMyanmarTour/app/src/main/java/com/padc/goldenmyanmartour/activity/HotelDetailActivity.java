package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ExpandableListAdapter;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.HotelVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/10/2016.
 */
public class HotelDetailActivity extends BaseActivity {

    private static final String IE_HOTEL_NAME = "IE_HOTEL_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

//    @BindView(R.id.tv_hotel_desc)
//    TextView tvHotelDesc;

    @BindView(R.id.pager_hotel_images)
    ViewPager pagerHotelImages;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;


    @BindView(R.id.pi_hotel_image_slider)
    PageIndicatorView piHotelImageSlider;


    @BindView(R.id.lvExp)
    ExpandableListView lvExpander;

    private String mHotelName;
    private HotelVO mHotel;

    //Expandable List View
    ExpandableListAdapter listAdapter;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;







    public static Intent newIntent(String hotelName) {
        Intent intent = new Intent(GMTApp.getContext(), HotelDetailActivity.class);
        intent.putExtra(IE_HOTEL_NAME, hotelName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hotel_detail);
        ButterKnife.bind(this, this);
//        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        prepareListData();

        listAdapter = new ExpandableListAdapter(this,listDataHeader,listDataChild);

        lvExpander.setAdapter(listAdapter);

        mHotelName = getIntent().getStringExtra(IE_HOTEL_NAME);

        bindData();

        //expandable list




        // preparing list data


//        List<String> top250 = new ArrayList<String>();
//        top250.add("The Shawshank Redemption");
//        top250.add("The Godfather");
//        top250.add("The Godfather: Part II");
//        top250.add("Pulp Fiction");
//        top250.add("The Good, the Bad and the Ugly");
//        top250.add("The Dark Knight");
//        top250.add("12 Angry Men");


        // setting list adapter


//        // Listview Group click listener
//        lvExpander.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                // Toast.makeText(getApplicationContext(),
//                // "Group Clicked " + listDataHeader.get(groupPosition),
//                // Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        // Listview Group expanded listener


    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Accomodation");
        listDataHeader.add("Restaurant");
        listDataHeader.add("Facilities&Services");


        // Adding child data
        List<String> accomodation = new ArrayList<String>();
        accomodation.add("Superior Room  ->   26.36m");
        accomodation.add("Ondol Room    ->   26.36m");
        accomodation.add("Premier Executive Room (Sea View) ->  26.36m");
        accomodation.add("Deluxe Room    ->   34m ");




        List<String> restaurant = new ArrayList<String>();
        restaurant.add("Breakfast   ->   6:30~11:30 \n Lunch   ->   12:00~15:00 \n Dinner   ->   17:30~22:00");
        restaurant.add("CoffeeShop  ->   7:00~23:00");
        restaurant.add("Barking     ->   6:30~22:00");


        List<String> facilities = new ArrayList<String>();
        facilities.add("Check-in : 3pm  Check-out :12 noon");
        facilities.add("HighSpeed Internet Access");
        facilities.add("Parking");
        facilities.add("Beauty Shop,FitnessRoom/Gym");
        facilities.add("Swimming Pool");
        facilities.add("Cash Machine/ATM");




        listDataChild.put(listDataHeader.get(0), accomodation); // Header, Child data
        listDataChild.put(listDataHeader.get(1), restaurant);
        listDataChild.put(listDataHeader.get(2), facilities);




//        lvExpander.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Expanded",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        // Listview Group collasped listener
//        lvExpander.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }


    private void bindData() {
        //tvHotelDesc.setText(R.string.hotel_desc);

        piHotelImageSlider.setNumPage(2);
        String[] images = {"R.drawable.ygnhotel_1", "R.drawable.ygnhotel_2"};
        ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(images);
        pagerHotelImages.setAdapter(pagerAdapter);
        pagerHotelImages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                piHotelImageSlider.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        collapsingToolbar.setTitle("Park Royal Yangon");
    }
}