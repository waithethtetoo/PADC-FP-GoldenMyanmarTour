package com.padc.goldenmyanmartour.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.HomeActivity;
import com.padc.goldenmyanmartour.activity.SearchActivity;
import com.padc.goldenmyanmartour.adapters.HotelAdapter;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.data.vo.Models.HotelModel;
import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.views.holders.FestivalViewHolder;
import com.padc.goldenmyanmartour.views.holders.HotelViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/9/2016.
 */
public class HotelFragment extends BaseFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.rv_hotels)
    RecyclerView rvHotels;

    private HotelAdapter mHotelAdapter;
    private HotelViewHolder.ControllerHotelItem controllerHotelItem;

    private BroadcastReceiver mDataLoaded = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<HotelVO> newHotelList = HotelModel.getInstance().getHotelList();
            mHotelAdapter.setNewData(newHotelList);
        }
    };

    MenuItem hotelCityItem;
    MenuItem hotelPriceItem;

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
        mHotelAdapter = new HotelAdapter(null, controllerHotelItem);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hotel, container, false);
        ButterKnife.bind(this, rootView);

        List<HotelVO> hotelList = HotelModel.getInstance().getHotelList();
        rvHotels.setAdapter(mHotelAdapter);

        int gridColumnCount = getResources().getInteger(R.integer.attraction_list_grid);
        rvHotels.setLayoutManager(new GridLayoutManager(getContext(), gridColumnCount));

        return rootView;
    }

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(DestinationConstants.DESTINATION_LIST_LOADER_GRIDVIEW, null, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mDataLoaded, new IntentFilter(HotelModel.BROADCAST_DATA_LOADED));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mDataLoaded);
    }

    @Override
    protected void onSendScreenHit() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                DestinationContract.HotelEntry.CONTENT_URI,
                null,
                null,
                null,
                DestinationContract.HotelEntry.COLUMN_NAME + "DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<HotelVO> hotelList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                HotelVO hotel = HotelVO.parseFromCursor(data);
                hotel.setPhotos(HotelVO.loadHotelImagesByName(hotel.getHotelName()));
                hotelList.add(hotel);
            } while (data.moveToNext());
        }
        mHotelAdapter.setNewData(hotelList);
        HotelModel.getInstance().setStoredData(hotelList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_city_price_filter,menu);

        hotelPriceItem = menu.findItem(R.id.spinnerPrice);
        hotelPriceItem.setTitle("Price");



        hotelCityItem = menu.findItem(R.id.spinnerCity);
        hotelCityItem.setTitle("City");

        Spinner spinner = (Spinner) MenuItemCompat.getActionView(hotelCityItem);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(GMTApp.getContext(),
                R.array.spinner_city_item_array, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        Spinner spinnerPrice = (Spinner)MenuItemCompat.getActionView(hotelPriceItem);
        ArrayAdapter<CharSequence> adapterPrice = ArrayAdapter.createFromResource(GMTApp.getContext(),
                R.array.spinner_price_item_array, android.R.layout.simple_spinner_dropdown_item);
        spinnerPrice.setAdapter(adapterPrice);

        super.onCreateOptionsMenu(menu, inflater);
    }

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