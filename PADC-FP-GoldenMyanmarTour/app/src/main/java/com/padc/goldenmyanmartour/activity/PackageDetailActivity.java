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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PackageDetailActivity extends AppCompatActivity {

    private static final String IE_PACKAGE_NAME = "IE_PACKAGE_NAME";

    @BindView(R.id.pager_package_images)
    ViewPager pagerPackageImages;

    @BindView(R.id.pi_package_image_slider)
    PageIndicatorView piPackageImageSlider;

    @BindView(R.id.package_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_package_name)
    TextView tvPackageName;

    @BindView(R.id.tv_package_desc)
    TextView tvPackageDesc;

    @BindView(R.id.iv_book_the_package)
    ImageView ivBookPackage;

    @BindView(R.id.fab_package_book_mark)
    FloatingActionButton fabPackageBook;

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
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void bindData() {
        tvPackageName.setText("Kalaw-Chaung Pauk-Khaung Dine-Inle Lake");
//        tvPackagePrice.setText("20,000 Ks");
        tvPackageDesc.setText("Day 1:Kalaw-Changyi Pauk-Khaung Dine\\n Early morning drive to Thayepoo (appr. 1 hour), a Danu – Taung Yoe – Pa O village, the starting point for the 2 days trekking trip to Khaung Dine. Your local station tour guide Ko Paul will introduce you to the villagers along the way and will show you the traditional way of life. At Supan Inn a Danu and Taung Yoe Tribal Village you will stop for lunch. Only a 30 minutes away is a bathing place where you can have a bath and then proceed to Chaunggyi Pauk a typical Taung Yoe village. The people in this village are well known for their bamboo mattresses and handicrafts. Dinner and overnight at Chaungyi Pauk.\\n Day 2:Chaungyi Pauk-Khaung Dine-Inle Lake\\n From Chaungyi Pauk it is only a 2 ½ hours walk to the Hot Spa, the only place where you can go for a hot bath. Don't miss the change. Then proceed by bus to Khaung Dine at the north-western shore of Inle Lake. This Intha village is well known for the production of soybean Cakes and noodles. From here transfer by boat to your hotel.");

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

    @OnClick(R.id.fab_package_book_mark)
    public void clickOnPackageBookMark() {
        Toast.makeText(GMTApp.getContext(), "Your bookmark is recorded", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_book_the_package)
    public void clickOnBook() {
        Toast.makeText(GMTApp.getContext(), "Your booking is successful.", Toast.LENGTH_SHORT).show();
    }

}
