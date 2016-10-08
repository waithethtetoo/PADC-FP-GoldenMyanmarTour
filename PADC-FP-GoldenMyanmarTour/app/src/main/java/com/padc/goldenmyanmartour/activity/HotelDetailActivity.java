package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ExpandableListAdapter;
import com.padc.goldenmyanmartour.adapters.HotelAdapter;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.adapters.ListViewAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.Models.HotelModel;
import com.padc.goldenmyanmartour.data.Models.PackageModel;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hp user on 9/10/2016.
 */
public class HotelDetailActivity extends BaseActivity {

    private static final String IE_HOTEL_NAME = "IE_HOTEL_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_hotel_address)
    TextView tvHotelAddress;

    @BindView(R.id.tv_direction)
    TextView tvDirection;

    @BindView(R.id.tv_hotel_phone)
    TextView tvHotelPhone;

    @BindView(R.id.tv_hotel_desc)
    TextView tvHotelDesc;

    @BindView(R.id.rv_rooms)
    RecyclerView rvRooms;

    @BindView(R.id.pager_hotel_images)
    ViewPager pagerHotelImages;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;


    @BindView(R.id.pi_hotel_image_slider)
    PageIndicatorView piHotelImageSlider;

    private String mHotelName;
    private HotelVO hotelVO;
    private HotelAdapter hotelAdapter;
    private ListViewAdapter listViewAdapter;
    private ListViewAdapter.ControllerRoomItem controllerRoomItem;

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
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mHotelName = getIntent().getStringExtra(IE_HOTEL_NAME);
        hotelVO = HotelModel.getInstance().getHotelsByName(mHotelName);

        if (hotelVO == null) {
            throw new RuntimeException("Can't find hotel with the title : " + mHotelName);
        } else {
            collapsingToolbar.setTitle(hotelVO.getHotelName());

            piHotelImageSlider.setNumPage(hotelVO.getRoomPhotos().length);
            ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(hotelVO.getRoomPhotos());
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

            tvDirection.setText(hotelVO.getDirection());
            tvHotelAddress.setText(hotelVO.getAddress());

            String phone = hotelVO.getPhoneNo()[0];
            tvHotelPhone.setText(phone);
            tvHotelDesc.setText(hotelVO.getDescription());

            String[] roomDesc = hotelVO.getRoomDesc();
            String[] charge = hotelVO.getCharge();
            String[] roomPhotos = hotelVO.getRoomPhotos();

            listViewAdapter = new ListViewAdapter(roomDesc, charge, roomPhotos, controllerRoomItem);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rvRooms.setAdapter(listViewAdapter);
            rvRooms.setLayoutManager(layoutManager);

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.fab_bookmark)
    public void clickOnPackageBookMark() {
        Toast.makeText(GMTApp.getContext(), "Your bookmark is recorded", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab_share)
    public void clickOnShare() {
        String imageUrl = hotelVO.getPhotos()[0];
        sendViaShareIntent(hotelVO.getHotelName() + "-" + imageUrl);
    }
}