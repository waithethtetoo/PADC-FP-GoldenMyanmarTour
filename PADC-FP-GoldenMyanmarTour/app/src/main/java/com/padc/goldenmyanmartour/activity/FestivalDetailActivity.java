package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.Models.FestivalModel;
import com.padc.goldenmyanmartour.data.Models.HotelModel;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FestivalDetailActivity extends BaseActivity {

    private static final String IE_FESTIVAL_NAME = "IE_FESTIVAL_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_festival_location)
    TextView tvLocation;

    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    @BindView(R.id.tv_end_date)
    TextView tvEndDate;

//    @BindView(R.id.tv_start_time)
//    TextView tvStartTime;

//    @BindView(R.id.tv_end_time)
//    TextView tvEndTime;

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
        FestivalVO festivalVO = FestivalModel.getInstance().getFestivalByName(mFestivalName);

        if (festivalVO == null) {
            throw new RuntimeException("Can't find festival with the title : " + mFestivalName);
        } else {
            collapsingToolbar.setTitle(mFestivalName);

            piFestImageSlider.setNumPage(festivalVO.getPhotos().length);
            ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(festivalVO.getPhotos());
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

            tvLocation.setText(festivalVO.getCityName() + " , " + festivalVO.getStateName());
            tvStartDate.setText(festivalVO.getStartDate());
//            tvStartTime.setText(festivalVO.getStartTime());

            tvEndDate.setText(festivalVO.getEndDate());
//            tvEndTime.setText(festivalVO.getEndTime());

            tvFestDesc.setText(festivalVO.getDescription());

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
}
