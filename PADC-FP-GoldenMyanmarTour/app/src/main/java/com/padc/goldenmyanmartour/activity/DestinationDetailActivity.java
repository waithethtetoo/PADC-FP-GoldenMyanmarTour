package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationDetailActivity extends AppCompatActivity {

    private static final String IE_DESTINATION_NAME = "IE_DESTINATION_NAME";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_dest_desc)
    TextView tv_dest_desc;

    @BindView(R.id.pager_destination_images)
    ViewPager pager_destination_images;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.pi_destination_image_slider)
    PageIndicatorView pi_destination_image_slider;

    private String mDestinationName;
    private DestinationVO mDestination;

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
        bindData(mDestination);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void bindData(DestinationVO destinationVO) {

        tv_dest_desc.setText("It is situated on the eastern bank of Ayeyarwaddy River and 688 km from Yangon. Bagan is one of the most remarkable archaeological sites in Asia with over 40000 ancient monuments built during 11- 13 century. It is also known as the centre of Myanmar Lacquer ware industry. Bagan was the capital of the first unified Empire of Anawrahta founded in 849 AD, and flourished from 1044 to 13th century.");
        pi_destination_image_slider.setCurrentPage(destinationVO.getImages().length);
        ImagesPagerAdapter adapter = new ImagesPagerAdapter(destinationVO.getImages());
        pager_destination_images.setAdapter(adapter);
        pager_destination_images.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pi_destination_image_slider.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        collapsing_toolbar.setTitle(mDestinationName);
    }
}
