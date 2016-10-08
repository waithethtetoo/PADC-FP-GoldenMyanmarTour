package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.database.Cursor;
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

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.adapters.PackageAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.Models.PackageModel;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.LocationVO;
import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.data.vo.TourCompanyVO;
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

    @BindView(R.id.tv_package_price)
    TextView tvPackagePrice;

    @BindView(R.id.tv_package_desc_title_one)
    TextView tvPackDescTitleOne;

    @BindView(R.id.tv_package_desc_one)
    TextView tvPackageDescOne;

    @BindView(R.id.tv_package_desc_title_two)
    TextView tvPackDescTitleTwo;

    @BindView(R.id.tv_package_desc_two)
    TextView tvPackDescTwo;

    @BindView(R.id.tv_package_desc_title_three)
    TextView tvPackDescTitleThree;

    @BindView(R.id.tv_package_desc_three)
    TextView tvPackDescThree;

    @BindView(R.id.tv_package_desc_title_four)
    TextView tvPackDescTitleFour;

    @BindView(R.id.tv_package_desc_four)
    TextView tvPackDescFour;

    @BindView(R.id.iv_one)
    ImageView ivOne;

    @BindView(R.id.iv_two_one)
    ImageView ivTwo;

    @BindView(R.id.iv_three_one)
    ImageView ivThree;

    @BindView(R.id.iv_four_one)
    ImageView ivFour;

    private String mPackageName;
    private PackageVO packageVO;
    private PackageAdapter packageAdapter;

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

        String packageName = getIntent().getStringExtra(IE_PACKAGE_NAME);
        packageVO = PackageModel.getInstance().getPackageByName(packageName);

        if (packageVO == null) {
            throw new RuntimeException("Can't find package with the title : " + packageName);
        } else {
            collapsingToolbar.setTitle(packageVO.getPackageName());

            piPackageImageSlider.setNumPage(packageVO.getPhotos().length);
            ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(packageVO.getPhotos());
            pagerPackageImages.setAdapter(pagerAdapter);

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

            tvPackagePrice.setText("Price : " + packageVO.getPrice());

            tvPackDescTitleOne.setText(packageVO.getDescTitleOne());
            tvPackageDescOne.setText(packageVO.getDescriptionOne());

            tvPackDescTitleTwo.setText(packageVO.getDescTitleTwo());
            tvPackDescTwo.setText(packageVO.getDescriptionTwo());

            tvPackDescTitleThree.setText(packageVO.getDescTitleThree());
            tvPackDescThree.setText(packageVO.getDescriptionThree());

            tvPackDescTitleFour.setText(packageVO.getDescTitleFour());
            tvPackDescFour.setText(packageVO.getDescriptionFour());

            Glide.with(ivOne.getContext())
                    .load(packageVO.getDestPhotoOne())
                    .centerCrop()
                    .placeholder(R.drawable.gmtiicon)
                    .error(R.drawable.gmtiicon)
                    .into(ivOne);

            Glide.with(ivTwo.getContext())
                    .load(packageVO.getDestPhotoTwo())
                    .centerCrop()
                    .placeholder(R.drawable.gmtiicon)
                    .error(R.drawable.gmtiicon)
                    .into(ivTwo);

            Glide.with(ivThree.getContext())
                    .load(packageVO.getDestPhotoThree())
                    .centerCrop()
                    .placeholder(R.drawable.gmtiicon)
                    .error(R.drawable.gmtiicon)
                    .into(ivThree);

            Glide.with(ivFour.getContext())
                    .load(packageVO.getDestPhotoFour())
                    .centerCrop()
                    .placeholder(R.drawable.gmtiicon)
                    .error(R.drawable.gmtiicon)
                    .into(ivFour);


//            if (packageVO.getPhotos() != null && packageVO.getPhotos()[0] != null && !packageVO.getPhotos()[0].isEmpty()) {
//                String imageUrl = packageVO.getPhotos()[0];
//                Glide.with(ivPackage.getContext())
//                        .load(imageUrl)
//                        .centerCrop()
//                        .placeholder(R.mipmap.ic_launcher)
//                        .error(R.mipmap.ic_launcher)
//                        .into(ivPackage);
//
//            } else {
//                Glide.with(ivPackage.getContext())
//                        .load(R.mipmap.ic_launcher)
//                        .centerCrop()
//                        .placeholder(R.mipmap.ic_launcher)
//                        .error(R.mipmap.ic_launcher)
//                        .into(ivPackage);
//            }
        }
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
        String imageUrl = packageVO.getPhotos()[0];
        sendViaShareIntent(packageVO.getPackageName() + "-" + imageUrl);
    }
}
