package com.padc.goldenmyanmartour.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.fragment.HomeFragment;
import com.padc.goldenmyanmartour.fragment.PackageFragment;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DestinationViewHolder.ControllerDestinationItem {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.pager_images)
    ViewPager pagerImages;

    @BindView(R.id.pi_image_slider)
    PageIndicatorView piImageSlider;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(R.string.app_name);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Menu leftMenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (savedInstanceState == null) {
            navigateToHomeFragment();
        }

        piImageSlider.setNumPage(5);
        String[] images = {"R.drawable.bagan", "R.mipmap.ic_launcher", "R.drawable.bagan", "R.mipmap.ic_launcher",
                "R.drawable.bagan"};
        ImagesPagerAdapter pagerAdapter = new ImagesPagerAdapter(images);
        pagerImages.setAdapter(pagerAdapter);
        pagerImages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                piImageSlider.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        fab.setVisibility(View.VISIBLE);
        switch (item.getItemId()) {
            case R.id.destinations:
                navigateToHomeFragment();
                return true;
            case R.id.packages:
                navigateToPackageFragment();
                return true;
            case R.id.hotels:
                return true;
            case R.id.festivals:
                return true;
            case R.id.customize_tours:
                return true;
            case R.id.user_bookmark:
                return true;
        }
        return false;
    }

    private void navigateToHomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, HomeFragment.newInstance())
                .commit();
    }

    private void navigateToPackageFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, PackageFragment.newInstance())
                .commit();
    }

    @Override
    public void onTapDestination(DestinationVO destinationVO, ImageView iv_destination) {
        Intent intent = DestinationDetailActivity.newIntent("Bagan");
        startActivity(intent);
    }
}
