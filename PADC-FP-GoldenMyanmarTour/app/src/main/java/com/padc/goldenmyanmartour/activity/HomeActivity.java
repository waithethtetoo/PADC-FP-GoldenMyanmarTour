package com.padc.goldenmyanmartour.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.fragment.FestivalFragment;
import com.padc.goldenmyanmartour.fragment.HomeFragment;
import com.padc.goldenmyanmartour.fragment.HotelFragment;
import com.padc.goldenmyanmartour.fragment.PLanOwnRouteFragment;
import com.padc.goldenmyanmartour.fragment.PackageFragment;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class HomeActivity extends BaseActivity
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
    public FloatingActionButton fab;


   @Nullable @BindView(R.id.spinner_filter)
   MenuItem spinnerFilter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Menu leftMenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);


        //spinner _ filter



//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                Intent intentToSearchActivity = SearchActivity.newIntent();
//                startActivity(intentToSearchActivity);
//
//            }
//        });

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
        getMenuInflater().inflate(R.menu.menu_actions_filter, menu);



        ButterKnife.bind(menu,this);


        Spinner spinner = (Spinner)MenuItemCompat.getActionView(spinnerFilter);

        if(spinner != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);

        }
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
        switch (item.getItemId()) {
            case R.id.destinations:
                fab.setVisibility(View.VISIBLE);
                navigateToHomeFragment();
                return true;
            case R.id.packages:
                fab.setVisibility(View.VISIBLE);
                navigateToPackageFragment();
                return true;
            case R.id.hotels:
                fab.setVisibility(View.VISIBLE);
                navigateToHotelFragment();
                return true;
            case R.id.festivals:
                fab.setVisibility(View.INVISIBLE);
                navigateToFestivalFragment();
                return true;
            case R.id.plan_own_route:
                fab.setVisibility(View.INVISIBLE);
                navigateToPlanOwnRoute();
                return true;
            case R.id.user_bookmark:
                fab.setVisibility(View.INVISIBLE);
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

    private void navigateToHotelFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, HotelFragment.newInstance())
                .commit();
    }

    private void navigateToFestivalFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, FestivalFragment.newInstance())
                .commit();
    }

    private void navigateToPlanOwnRoute() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, PLanOwnRouteFragment.newInstance())
                .commit();
    }

    @Override
    public void onTapDestination(DestinationVO destinationVO, ImageView iv_destination) {
        Intent intent = DestinationDetailActivity.newIntent(destinationVO.getTitle());
        startActivity(intent);
    }
}
