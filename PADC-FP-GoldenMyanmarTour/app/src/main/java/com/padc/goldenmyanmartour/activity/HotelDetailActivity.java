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
        tvHotelDesc.setText("Offering a unique blend of gentle Myanmar hospitality combined with first class service and facilities, the Grand Plaza Parkroyal, Yangon stands resplendent amidst a magnificent landscape dotted with golden pagodas. It is located in Yangon's central business district, only minutes away from the legendary Shwedagon Pagoda and the famous Bogyoke Aung San Market.Our spacious and elegantly appointed 312 guest rooms and suites exude a sense of comfort and style. Moreover, the exclusive Orchid Club Floor offers an unprecedented level of privilege and a host of complimentary services to our valued guests."
                + "\n\n" + "Feast your senses at the hotel's fine restaurants where you will find our unmistakable hospitality and culinary excellence. Whether it is a gathering of family or friends, our restaurants can satisfy any palate for any occasion.From contract signing to seminars, to conferences, or even lavish banquets, Grand Plaza Parkroyal is Yangon's preferred meeting and conference venue. Our functions and meeting rooms offer unique flexibility backed by impeccable, efficient services and facilities to please every event organizer. Moreover, a professional banquet service team is readily available to see to your needs.After a long day of site-seeing or business meetings, pamper yourself with long relaxing massages and traditional spa therapies. For fitness buffs, there's the gym, tennis court, swimming pool and aerobic studio. Sooth tired muscles with some time out in the sauna or steam bath.Be it for pleasure or business, Grand Plaza Parkroyal, Yangon, is the ultimate place for you in Yangon.");

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