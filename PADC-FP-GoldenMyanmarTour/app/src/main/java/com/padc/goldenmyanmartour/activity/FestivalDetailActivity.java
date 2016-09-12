package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.HotelVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FestivalDetailActivity extends BaseActivity {

    private static final String IE_FESTIVAL_NAME = "IE_FESTIVAL_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_festival_name)
    TextView tvFestName;
    @BindView(R.id.tv_festival_location)
    TextView tvFestLocation;
    @BindView(R.id.tv_festival_month)
    TextView tvFestMonth;
    @BindView(R.id.tv_festival_date)
    TextView tvFestDate;
    @BindView(R.id.tv_festival_duration)
    TextView tvFestDuration;
    @BindView(R.id.tv_festival_desc)
    TextView tvFestDesc;

    @BindView(R.id.pager_festival_images)
    ViewPager pagerFestImages;

    @BindView(R.id.festival_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;


    @BindView(R.id.pi_festival_image_slider)
    PageIndicatorView piFestImageSlider;

    private String mFestivalName;
    private FestivalVO mFestival;

    public static Intent newIntent(String festivalName) {
        Intent intent = new Intent(GMTApp.getContext(), FestivalDetailActivity.class);
        intent.putExtra(IE_FESTIVAL_NAME, festivalName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_festival_detail);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mFestivalName = getIntent().getStringExtra(IE_FESTIVAL_NAME);

        bindData();
    }

    private void bindData() {
        tvFestName.setText("Ananda Pagoda Festival");
        tvFestLocation.setText("Khamti, Lahe, Lashi in Sagaing");
        tvFestMonth.setText("January");
        tvFestDate.setText("Full Moon of Pyatho to 15th Waning of Pyatho");
        tvFestDuration.setText("15 Days");
        tvFestDesc.setText("The authentic Naga Hill Tribes (about 68 different groups) wear their respective traditional costumes to participate this important festival. They worship to their deities by scarifying the animals. Their traditional dance, martial music and cults of animism are unique and interesting.");

        piFestImageSlider.setNumPage(5);
        String[] images = {"R.drawable.ananda_pagoda_festival", "R.drawable.ananda_pagoda_festival", "R.drawable.ananda_pagoda_festival", "R.drawable.ananda_pagoda_festival", "R.drawable.ananda_pagoda_festival"};
        ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(images);
        pagerFestImages.setAdapter(pagerAdapter);
        pagerFestImages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                piFestImageSlider.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        collapsingToolbar.setTitle(mFestivalName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
