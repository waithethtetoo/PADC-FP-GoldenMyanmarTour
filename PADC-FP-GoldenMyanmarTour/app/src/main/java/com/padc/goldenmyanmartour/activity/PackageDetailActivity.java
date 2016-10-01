package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.dialog.SharedDialog;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PackageDetailActivity extends BaseActivity {

    private static final String IE_PACKAGE_NAME = "IE_PACKAGE_NAME";

    @BindView(R.id.pager_package_images)
    ViewPager pagerPackageImages;

    @BindView(R.id.pi_package_image_slider)
    PageIndicatorView piPackageImageSlider;

    @BindView(R.id.package_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.tv_package_name)
//    TextView tvPackageName;

    @BindView(R.id.tv_package_price)
    TextView tvPackagePrice;

    @BindView(R.id.tv_package_desc_title_one)
    TextView tvPackDescTitleOne;

    @BindView(R.id.tv_package_desc_one)
    TextView tvPackageDescOne;

    @BindView(R.id.tv_package_desc_title_two)
    TextView tvPackageDescTitleTwo;

    @BindView(R.id.tv_package_desc_two)
    TextView tvPackDescTwo;

//    @BindView(R.id.iv_book_the_package)
//    ImageView ivBookPackage;

//    @BindView(R.id.fab_package_book_mark)
//    FloatingActionButton fabPackageBook;

    private String mPackageName;
    private PackageVO packageVO;

    public static Intent newIntent(String name) {
        Intent intent = new Intent(GMTApp.getContext(), PackageDetailActivity.class);
        intent.putExtra(IE_PACKAGE_NAME, name);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        bindData();
        mPackageName = getIntent().getStringExtra(IE_PACKAGE_NAME);
        collapsingToolbar.setTitle(mPackageName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void bindData() {
//        tvPackageName.setText("Yangon-Bago-Thanlyin-Yangon");
        tvPackagePrice.setText("Price : 50000");
        tvPackDescTitleOne.setText("Day-1: Arrival Yangon");
        tvPackageDescOne.setText("Arrive at Yangon International Airport. Meet and greet our experienced guide. Transfer to hotel. After refreshment, sightseeing begins from Sule Pagoda , in the heart of the city. We'll explore the downtown area, where the ghost of the British Colonial influence still prevails From there we will visit the (64m)long massive reclining Buddha at Chaukhtagyi Paya. We've scheduled a late afternoon visit to most revered and most famous landmark, one of the World's wonder \\\" Shwedagon Pagoda \\\" at dusk, as the Sun begins to fall over the City. Overnight at hotel in Yangon.");
        tvPackageDescTitleTwo.setText("Day-2 :Yangon-Bago-Kyaikhtiyo");
        tvPackDescTwo.setText("After breakfast at hotel, drive to Kyaikhtiyo, known as Golden Rock. It is 200 km north east of Yangon, 5 hour drive. On the way visit Bago for its highlights: Kyaikpon Pagoda.4 Buddha Images, Shwe Thalyaung Pagoda and Shwemawdaw Pagoda. Proceed to Kyaikhtyo. Arrive Kimpun based camp and from there take open truck to drive up to Yahte camp. And then take trekking about 1 hour to the summit. (Sedan Chair Service is available at US $ 20 per pax per way). The Panoramic view from the pagoda platform is magnificent over Sittaung Valley. Overnight at hotel.");


        piPackageImageSlider.setNumPage(5);
        String[] images = {"http://mandalayholidays.com/images/tour-program/yangon1.jpg",
                "http://mandalayholidays.com/images/tour-program/yangon-01.jpg",
                "http://mandalayholidays.com/images/tour-program/bago-01.jpg",
                "http://mandalayholidays.com/images/destination/yangon-06.jpg",
                "http://mandalayholidays.com/images/destination/yangon-19.jpg"};

        ImagesPagerAdapter adapter = new ImagesPagerAdapter(images);
        pagerPackageImages.setAdapter(adapter);
        pagerPackageImages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                piPackageImageSlider.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick(R.id.fab_bookmark)
    public void clickOnPackageBookMark() {
        Toast.makeText(GMTApp.getContext(), "Your bookmark is recorded", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab_call)
    public void clickOnCall() {

        String msg = getString(R.string.format_contact_option_confirmation);
        SharedDialog.confirmYesNoWithTheme(this, msg,
                getString(R.string.booking_phone), getString(R.string.booking_email), new SharedDialog.YesNoConfirmDelegate() {
                    @Override
                    public void onConfirmYes() {
                        makeCall(DestinationConstants.CUSTOMER_SUPPORT_PHONE);
                    }

                    @Override
                    public void onConfirmNo() {
                        sendEmail(DestinationConstants.CUSTOMER_SUPPORT_EMAIL, getString(R.string.book_the_package),
                                getString(R.string.format_book_the_package_message));
                    }
                });
    }

    @OnClick(R.id.fab_share)
    public void clickOnShare() {
//        String imageUrl = DestinationConstants.IMAGE_ROOT_DIR + mDestination.getDestination_photos()[0];
//        sendViaShareIntent(mDestination.getTitle() + "-" + imageUrl);
        sendViaShareIntent("Package");
    }
}
