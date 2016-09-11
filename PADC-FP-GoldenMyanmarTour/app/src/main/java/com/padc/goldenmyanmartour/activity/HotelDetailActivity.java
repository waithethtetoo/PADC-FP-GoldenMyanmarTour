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
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.HotelVO;

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

    @BindView(R.id.tv_hotel_desc)
    TextView tvHotelDesc;

    @BindView(R.id.pager_hotel_images)
    ViewPager pagerHotelImages;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;


    @BindView(R.id.pi_hotel_image_slider)
    PageIndicatorView piHotelImageSlider;

    private String mHotelName;
    private HotelVO mHotel;


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

        mHotelName = getIntent().getStringExtra(IE_HOTEL_NAME);

        bindData();
    }

    private void bindData() {
        tvHotelDesc.setText(R.string.hotel_desc);

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

        collapsingToolbar.setTitle(mHotelName);
    }
}