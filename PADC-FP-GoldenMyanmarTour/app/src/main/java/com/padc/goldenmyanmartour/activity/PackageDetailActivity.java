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
    TextView tvPackaDescTitleTwo;

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


    private void bindData() {
//        tvPackageName.setText("Kalaw-Chaung Pauk-Khaung Dine-Inle Lake");
        tvPackagePrice.setText("Price : 20,000 Ks");
        tvPackDescTitleOne.setText("Day 1:Kalaw-Changyi Pauk-Khaung Dine");
        tvPackageDescOne.setText("Early morning drive to Thayepoo (appr. 1 hour), a Danu â€“ Taung Yoe â€“ Pa O village, \n" +
                "the starting point for the 2 days trekking trip to Khaung Dine. \n" +
                "Your local station tour guide Ko Paul will introduce you to the villagers along the way \n" +
                "and will show you the traditional way of life. \n" +
                "At Supan Inn a Danu and Taung Yoe Tribal Village you will stop for lunch. \n" +
                "Only a 30 minutes away is a bathing place where you can have a bath and \n" +
                "then proceed to Chaunggyi Pauk a typical Taung Yoe village.\n" +
                " The people in this village are well known for their bamboo mattresses and handicrafts. \n" +
                "Dinner and overnight at Chaungyi Pauk.");
        tvPackaDescTitleTwo.setText("Day 2:Chaungyi Pauk-Khaung Dine-Inle Lake");
        tvPackDescTwo.setText("From Chaungyi Pauk it is only a 2 Â½ hours walk to the Hot Spa, \n" +
                "the only place where you can go for a hot bath. Don't miss the change. \n" +
                "Then proceed by bus to Khaung Dine at the north-western shore of Inle Lake. \n" +
                "This Intha village is well known for the production of soybean Cakes and noodles. \n" +
                "From here transfer by boat to your hotel.");

        mPackageName = getIntent().getStringExtra(IE_PACKAGE_NAME);

        piPackageImageSlider.setNumPage(5);
        String[] images = {"R.drawable.bagan", "R.drawable.inle", "R.drawable.mandalay"};

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
        collapsingToolbar.setTitle(mPackageName);
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
