package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.adapters.ListViewAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationDetailActivity extends BaseActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String IE_DESTINATION_NAME = "IE_DESTINATION_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_dest_desc)
    TextView tvDestDesc;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.pager_destination_images)
    ViewPager pagerDestinationImages;

    @BindView(R.id.pi_destination_image_slider)
    PageIndicatorView piDestinationImageSlider;

    @BindView(R.id.lv_destination)
    ListView lvDest;

    private String mDestinationName;
    private DestinationVO mDestination;
    private List<AttractionPlacesVO> mAttraction;
    private MenuItemCompat.OnActionExpandListener mOnActionExpandListener;
    private ListViewAdapter adapter;

    public static Intent newIntent(String name) {
        Intent intent = new Intent(GMTApp.getContext(), DestinationDetailActivity.class);
        intent.putExtra(IE_DESTINATION_NAME, name);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDestinationName = getIntent().getStringExtra(IE_DESTINATION_NAME);

        getSupportLoaderManager().initLoader(DestinationConstants.DESTINATION_LIST_LOADER_GRIDVIEW, null, this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void bindData(DestinationVO destinationVO) {
//        tvDestDesc.setText("It is situated on the eastern bank of Ayeyarwaddy River and 688 km from Yangon. Bagan is one of the most remarkable archaeological sites in Asia with over 40000 ancient monuments built during 11- 13 century. It is also known as the centre of Myanmar Lacquer ware industry. Bagan was the capital of the first unified Empire of Anawrahta founded in 849 AD, and flourished from 1044 to 13th century.");
        tvDestDesc.setText(destinationVO.getNoteToVisitor());
        adapter = new ListViewAdapter(mAttraction, GMTApp.getContext());
        lvDest.setAdapter(adapter);

        piDestinationImageSlider.setNumPage(destinationVO.getDestination_photos().length);
        ImagesPagerAdapter adapter = new ImagesPagerAdapter(destinationVO.getDestination_photos());
        pagerDestinationImages.setAdapter(adapter);
        pagerDestinationImages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                piDestinationImageSlider.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        collapsingToolbar.setTitle(mDestinationName);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                DestinationContract.DestinationEntry.buildDestinationUriWithTitle(mDestinationName),
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
            bindData(mDestination);
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
