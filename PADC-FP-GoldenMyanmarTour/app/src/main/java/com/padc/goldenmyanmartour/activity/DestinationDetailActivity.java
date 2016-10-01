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
import android.util.Log;
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
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.dialog.SharedDialog;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.List;

import com.padc.goldenmyanmartour.data.Models.DestinationModel;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import org.w3c.dom.Text;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationDetailActivity extends BaseActivity {

    private static final String IE_DESTINATION_NAME = "IE_DESTINATION_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.fab)
//    FloatingActionButton fab;

    @BindView(R.id.tv_dest_desc)
    TextView tvDestDesc;

    @BindView(R.id.pager_destination_images)
    ViewPager pagerDestImages;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.pi_destination_image_slider)
    PageIndicatorView piDestImageSlider;

    private String mDestTitle;
    private DestinationVO mDestination;

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
        bindData();
//        getSupportLoaderManager().initLoader(DestinationConstants.DESTINATION_LIST_LOADER_GRIDVIEW, null, this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /*
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
                bindData(mDestination);
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    */
    private void bindData() {
//        tvDestDesc.setText(destinationVO.getLocationVO().getCityVO().getDescription());
        tvDestDesc.setText("Yangon, the commercial capital, is the main gateway to Myanmar. Evergreen and cool with lush tropical trees, shady parks and beautiful lakes, Yangon has earned the name of The Garden City of the East. Yangon was founded by King Alaungpaya on the site of a small settlement called Dagon when he conquered Lower Myanmar in 1755.");
//        piDestImageSlider.setNumPage(destinationVO.getDestination_photos().length);

        piDestImageSlider.setNumPage(3);
        String[]imageurl={"image"};
        ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(imageurl);
//        ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(destinationVO.getDestination_photos());
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

        collapsingToolbar.setTitle(mDestTitle);
    }

    @OnClick(R.id.fab_share)
    public void onTapShare(View view) {
        //        String imageUrl = DestinationConstants.IMAGE_ROOT_DIR + mDestination.getDestination_photos()[0];
//        sendViaShareIntent(mDestination.getTitle() + "-" + imageUrl);
        sendViaShareIntent("Destination");
    }

    @OnClick(R.id.fab_bookmark)
    public void onTapBook(View view) {

        String msg = getString(R.string.format_contact_option_confirmation, mDestination.getTitle());

        SharedDialog.confirmYesNoWithTheme(this, msg,
                getString(R.string.booking_phone), getString(R.string.booking_email), new SharedDialog.YesNoConfirmDelegate() {
                    @Override
                    public void onConfirmYes() {
                        makeCall(DestinationConstants.CUSTOMER_SUPPORT_PHONE);
                    }

                    @Override
                    public void onConfirmNo() {

                        sendEmail(DestinationConstants.CUSTOMER_SUPPORT_EMAIL, getString(R.string.book_the_package),
                                getString(R.string.format_book_the_package_message, mDestination.getTitle()));
                    }
                });
    }

    @Override
    protected void onSendScreenHit() {
        super.onSendScreenHit();
    }

}
