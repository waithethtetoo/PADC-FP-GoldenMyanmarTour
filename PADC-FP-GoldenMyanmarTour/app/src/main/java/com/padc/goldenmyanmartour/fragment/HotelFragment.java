package com.padc.goldenmyanmartour.fragment;

import android.content.Context;
import android.content.Intent;
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

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.HomeActivity;
import com.padc.goldenmyanmartour.activity.SearchActivity;
import com.padc.goldenmyanmartour.adapters.HotelAdapter;
import com.padc.goldenmyanmartour.views.holders.HotelViewHolder;

import java.lang.reflect.Array;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/9/2016.
 */
public class HotelFragment extends Fragment {

    @BindView(R.id.rv_hotels)
    RecyclerView rvHotels;

    @BindView(R.id.sp_filterHotelCity)
    Spinner spFilterHotelCity;

    @BindView(R.id.sp_filterHotelPrice)
    Spinner spFilterHotelPrice;

    private HotelAdapter mHotelAdapter;
    private HotelViewHolder.ControllerHotelItem controllerHotelItem;


    public static HotelFragment newInstance() {
        HotelFragment fragment = new HotelFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //controllerHotelItem = (HotelViewHolder.ControllerHotelItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotelAdapter = new HotelAdapter(null, controllerHotelItem);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hotel, container, false);
        ButterKnife.bind(this, rootView);
        rvHotels.setAdapter(mHotelAdapter);

        rvHotels.setLayoutManager(new GridLayoutManager(getContext(), 1));


        ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_city_item_array, android.R.layout.simple_spinner_item);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spFilterHotelCity.setAdapter(adapterCity);


        ArrayAdapter<CharSequence> adapterPrice = ArrayAdapter.createFromResource(rootView.getContext(), R.array.spinner_price_item_array, android.R.layout.simple_spinner_item);
        adapterPrice.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spFilterHotelPrice.setAdapter(adapterPrice);

        return rootView;


    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.menu_city_price_filter,menu);
//
//        hotelPriceItem = menu.findItem(R.id.spinnerPrice);
//        hotelPriceItem.setTitle("Price");
//
//
//
//        hotelCityItem = menu.findItem(R.id.spinnerCity);
//        hotelCityItem.setTitle("City");
//
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(hotelCityItem);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(GMTApp.getContext(),
//                R.array.spinner_city_item_array, android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//
//
//
//        Spinner spinnerPrice = (Spinner)MenuItemCompat.getActionView(hotelPriceItem);
//        ArrayAdapter<CharSequence> adapterPrice = ArrayAdapter.createFromResource(GMTApp.getContext(),
//                R.array.spinner_price_item_array, android.R.layout.simple_spinner_dropdown_item);
//        spinnerPrice.setAdapter(adapterPrice);
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id= item.getItemId();
//        switch (id) {
//            case R.id.spinnerPrice:
//                break;
//            case R.id.spinnerCity:
//               break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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


    }
}