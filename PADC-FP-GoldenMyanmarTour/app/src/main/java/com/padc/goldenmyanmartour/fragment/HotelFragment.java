package com.padc.goldenmyanmartour.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;

import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.HomeActivity;
import com.padc.goldenmyanmartour.activity.SearchActivity;
import com.padc.goldenmyanmartour.adapters.HotelAdapter;
import com.padc.goldenmyanmartour.data.Models.HotelModel;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.views.holders.FestivalViewHolder;
import com.padc.goldenmyanmartour.views.holders.HotelViewHolder;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/9/2016.
 */
public class HotelFragment extends Fragment {

    @BindView(R.id.rv_hotels)
    RecyclerView rvHotels;

    private HotelAdapter mHotelAdapter;
    private HotelViewHolder.ControllerHotelItem controllerHotelItem;


    public static HotelFragment newInstance() {
        HotelFragment fragment = new HotelFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        controllerHotelItem = (HotelViewHolder.ControllerHotelItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotelAdapter = new HotelAdapter(HotelModel.getInstance().getHotelList(), controllerHotelItem);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hotel, container, false);
        ButterKnife.bind(this, rootView);

        rvHotels.setAdapter(mHotelAdapter);

        int gridColumnCount = getResources().getInteger(R.integer.attraction_list_grid);
        rvHotels.setLayoutManager(new GridLayoutManager(getContext(), gridColumnCount));

        return rootView;
    }
/*
    @Override
    public void setUserVisibleHint(boolean visible) {
        super.setUserVisibleHint(visible);
        if (visible && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }
        HomeActivity mainActivity = (HomeActivity) getActivity();
        mainActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do what you want
                Toast.makeText(GMTApp.getContext(), "Hotel Fragment Search FAB Clicked", Toast.LENGTH_SHORT).show();
                Intent intentToSearch = SearchActivity.newIntent("Hotel Fragment");
                startActivity(intentToSearch);
            }
        });
    }
    */
}