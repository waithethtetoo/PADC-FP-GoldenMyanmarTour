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
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FestivalDetailActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String IE_FESTIVAL_NAME = "IE_FESTIVAL_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_festival_name)
    TextView tvFestName;
    @BindView(R.id.tv_festival_location)
    TextView tvFestLocation;
    @BindView(R.id.tv_festival_month)
    TextView tvFestStratDate;
    @BindView(R.id.tv_festival_date)
    TextView tvFestEndDate;
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
        getSupportLoaderManager().initLoader(DestinationConstants.DESTINATION_LIST_LOADER_GRIDVIEW, null, this);
    }

    private void bindData(FestivalVO festivalVO) {
        tvFestName.setText(festivalVO.getFestivalName());
        tvFestLocation.setText(festivalVO.getLocationVO().getCityVO().getName());
        tvFestStratDate.setText(festivalVO.getFestivalPeriodVO().getStartDate());
        tvFestEndDate.setText(festivalVO.getFestivalPeriodVO().getEndDate());
        tvFestDuration.setText(festivalVO.getFestivalPeriodVO().getStartTime());
        tvFestDesc.setText(festivalVO.getDescription());

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

        collapsingToolbar.setTitle(mFestivalName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                DestinationContract.FestivalEntry.buildFestivalUriWithTitle(mFestivalName),
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mFestival = FestivalVO.parseFromCursor(data);
            mFestival.setPhotos(FestivalVO.loadFestivalImagesByTitle(mFestival.getFestivalName()));
            bindData(mFestival);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    protected void onSendScreenHit() {
        super.onSendScreenHit();
    }

}
