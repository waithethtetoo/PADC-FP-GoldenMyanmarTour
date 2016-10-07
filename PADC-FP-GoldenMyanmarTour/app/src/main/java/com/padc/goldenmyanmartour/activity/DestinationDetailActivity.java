package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.AttractionPlacesAdapter;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.LocationVO;
import com.padc.goldenmyanmartour.dialog.SharedDialog;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.List;

import com.padc.goldenmyanmartour.views.holders.AttractionPlacesViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationDetailActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor>,
        AttractionPlacesViewHolder.ControllerAttractionPlaceItem {
    private static final String IE_DESTINATION_NAME = "IE_DESTINATION_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_dest_desc)
    TextView tvDestDesc;

    @BindView(R.id.pager_destination_images)
    ViewPager pagerDestImages;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.pi_destination_image_slider)
    PageIndicatorView piDestImageSlider;

//    @BindView(R.id.rv_attraction_places)
//    RecyclerView rvAttractionPlaces;

    @BindView(R.id.iv_one)
    ImageView ivOne;

    @BindView(R.id.iv_two)
    ImageView ivTwo;

    @BindView(R.id.iv_three)
    ImageView ivThree;

    @BindView(R.id.iv_four)
    ImageView ivFour;

    @BindView(R.id.tv_one)
    TextView tvOne;

    @BindView(R.id.tv_two)
    TextView tvTwo;

    @BindView(R.id.tv_three)
    TextView tvThree;

    @BindView(R.id.tv_four)
    TextView tvFour;


    private String mDestTitle;
    private DestinationVO mDestination;
    private AttractionPlacesAdapter attractionPlacesAdapter;

    public static Intent newIntent(String destinationName) {
        Intent intent = new Intent(GMTApp.getContext(), DestinationDetailActivity.class);
        intent.putExtra(IE_DESTINATION_NAME, destinationName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDestTitle = getIntent().getStringExtra(IE_DESTINATION_NAME);
        Log.v(GMTApp.TAG, mDestTitle);

        getSupportLoaderManager().initLoader(DestinationConstants.DESTINATION_LIST_LOADER, null, this);
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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                DestinationContract.DestinationEntry.buildDestinationUriWithTitle(mDestTitle),
                null,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst()) {
            mDestination = DestinationVO.parseFromCursor(data);
            mDestination.setDestination_photos(DestinationVO.loadDestinationImagesByTitle(mDestination.getTitle()));
            mDestination.setLocationVO(LocationVO.loadLocationByDestinationTitle(mDestination.getTitle()));
            mDestination.setAttractionPlacesVOs(AttractionPlacesVO.loadAttractionPlacesByDestinationTitle(mDestination.getTitle()));
            bindData(mDestination);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void bindData(DestinationVO destinationVO) {

        tvDestDesc.setText(destinationVO.getLocationVO().getCityVO().getDescription());

        //attraction places recycler view
//        attractionPlacesAdapter = new AttractionPlacesAdapter(destinationVO.getAttractionPlacesVOs());
//        rvAttractionPlaces.setAdapter(attractionPlacesAdapter);
//        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(DestinationDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
//        rvAttractionPlaces.setLayoutManager(horizontalLayoutManager);

        piDestImageSlider.setNumPage(destinationVO.getDestination_photos().length);
        String photo_length = String.valueOf(destinationVO.getDestination_photos().length);
        Log.v("DestinationDetail", photo_length);
        ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(destinationVO.getDestination_photos());
        pagerDestImages.setAdapter(pagerAdapter);

        pagerDestImages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                piDestImageSlider.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (destinationVO.getTitle().equalsIgnoreCase("Yangon")) {
            ivOne.setImageResource(R.drawable.shwedagon1);
            ivTwo.setImageResource(R.drawable.sulepagoda1);
            ivThree.setImageResource(R.drawable.nationalmuseum1);
            ivFour.setImageResource(R.drawable.kandawgyigarden1);

            tvOne.setText("Shwedagon Pagoda");
            tvTwo.setText("Sule Pagoda");
            tvThree.setText("National Museum");
            tvFour.setText("Kandawgyi Royal Lake And Karaweik Hall");
        }
        if (destinationVO.getTitle().equalsIgnoreCase("Bagan")) {
            ivOne.setImageResource(R.drawable.bupagoda1);
            ivTwo.setImageResource(R.drawable.htilominlo1);
            ivThree.setImageResource(R.drawable.pyathadar1);
//            ivFour.setImageResource(R.drawable.bagan_museum1);

            tvOne.setText("Bu Paya");
            tvTwo.setText("Htilominlo");
            tvThree.setText("Pyathatgyi");

        }
        if (destinationVO.getTitle().equalsIgnoreCase("Mandalay")) {
            ivOne.setImageResource(R.drawable.maharmyatmuni1);
            ivTwo.setImageResource(R.drawable.kuthodaw1);
            ivThree.setImageResource(R.drawable.atumashimonastery1);
            ivFour.setImageResource(R.drawable.shwenandawmonastey1);

            tvOne.setText("Maha Myat Muni Buddha Image(Payagyi)");
            tvTwo.setText("Kuthodaw Pagoda");
            tvThree.setText("Atumashi Monastery");
            tvFour.setText("Shwe Nandaw Monastery");
        }
        if (destinationVO.getTitle().equalsIgnoreCase("inle")) {
            ivOne.setImageResource(R.drawable.phaungdawoopagoda1);
            ivTwo.setImageResource(R.drawable.ywarmavillage01);
            ivThree.setImageResource(R.drawable.shweindein1);
            ivFour.setImageResource(R.drawable.market1);

            tvOne.setText("Phaung Daw Oo Pagoda");
            tvTwo.setText("Ywama Village");
            tvThree.setText("Shwe Indein Pagoda");
            tvFour.setText("Mine Thauk Market");

        }
        if (destinationVO.getTitle().equalsIgnoreCase("MRAUK U")) {
            ivOne.setImageResource(R.drawable.sittwe1);
            ivTwo.setImageResource(R.drawable.thandwe1);
            ivThree.setImageResource(R.drawable.sandamuniimage1);
//            ivFour.setImageResource(R.drawable.market1);

            tvOne.setText("Sittwe");
            tvTwo.setText("Thandwe");
            tvThree.setText("Sanda Muni Pagoda");
        }
        if (destinationVO.getTitle().equalsIgnoreCase("nay pyi taw")) {
            ivOne.setImageResource(R.drawable.uppatasantipagoda1);
            ivTwo.setImageResource(R.drawable.waterfountaingarden1);
            ivThree.setImageResource(R.drawable.zoo1);
            ivFour.setImageResource(R.drawable.gemmuseum1);

            tvOne.setText("Uppatasanti Pagoda");
            tvTwo.setText("Water Fountain Garden");
            tvThree.setText("Zoological Garden");
            tvFour.setText("The Gem Museum");
        }
        collapsingToolbar.setTitle(mDestTitle);
    }

    @OnClick(R.id.fab_share)
    public void onTapShare(View view) {
        String imageUrl = mDestination.getDestination_photos()[0];
        sendViaShareIntent(mDestination.getTitle() + "-" + imageUrl);

    }

    @OnClick(R.id.fab_bookmark)
    public void onTapBook(View view) {
        Toast.makeText(GMTApp.getContext(), "Your bookmark is recorded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSendScreenHit() {
        super.onSendScreenHit();
    }

    @Override
    public void onTapAttractionPlace(AttractionPlacesVO attractionPlacesVO, ImageView ivHotel) {
        Intent intent = AttractionPlacesViewHolder.newIntent(attractionPlacesVO.getTitle());
        startActivity(intent);
    }

}
