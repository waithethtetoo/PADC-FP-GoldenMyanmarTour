package com.padc.goldenmyanmartour.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.HomeActivity;
import com.padc.goldenmyanmartour.activity.SearchActivity;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;

import com.padc.goldenmyanmartour.adapters.ImagesPagerAdapter;
import com.padc.goldenmyanmartour.components.PageIndicatorView;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.persistence.DestinationContract;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import com.padc.goldenmyanmartour.data.Models.DestinationModel;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.gv_destinations)
    GridView gvDestinations;

    @BindView(R.id.sp_fiterCity)
            Spinner spFilterCity;

    MenuItem destinationItemCity;


    private DestinationAdapter mAdapter;
    private DestinationViewHolder.ControllerDestinationItem mController;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = (DestinationViewHolder.ControllerDestinationItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DestinationAdapter(null, mController);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        List<DestinationVO> destinationVOList = DestinationModel.getInstance().getmDestList();
        Log.d("DataList", DestinationModel.getInstance().getmDestList().toString());
        mAdapter = new DestinationAdapter(destinationVOList, mController);
        gvDestinations.setAdapter(mAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.spinner_city_item_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spFilterCity.setAdapter(adapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(DestinationConstants.DESTINATION_LIST_LOADER_GRIDVIEW, null, this);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
//        inflater.inflate(R.menu.menu_type_filter,menu);
//
//
//
//        destinationItemCity = menu.findItem(R.id.spinner);
//
//        //destinationItemCity.setTitle("City");
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(destinationItemCity);
//        spinner.setDropDownVerticalOffset(-56);
//
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(GMTApp.getContext(),
//                 R.array.spinner_city_item_array, android.R.layout.simple_spinner_dropdown_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//
//        spinner.setAdapter(adapter);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.spinner:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_type_filter, menu);

        destinationItemCity = menu.findItem(R.id.spinner);
        destinationItemCity.setTitle("City");
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(destinationItemCity);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(GMTApp.getContext(),
                R.array.spinner_city_item_array, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        super.onCreateOptionsMenu(menu, inflater);
    }

    // search action according to different fragment
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
                Intent intentToSearch = SearchActivity.newIntent("Home Fragment");
                startActivity(intentToSearch);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                DestinationContract.DestinationEntry.CONTENT_URI,
                null,
                null,
                null,
                DestinationContract.DestinationEntry.COLUMN_TITLE + "ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<DestinationVO> destinationList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                DestinationVO destination = DestinationVO.parseFromCursor(data);
                destination.setDestination_photos(DestinationVO.loadDestinationImagesByTitle(destination.getTitle()));
                destinationList.add(destination);
            } while (data.moveToNext());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

//    @Override
//    protected void onSendScreenHit() {
//
//    }
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    public void onEventMainThread(DataEvent.DestinationDataLoaded event) {
        String extra = event.getMessage();
        Toast.makeText(getContext(), "Extra :" + extra, Toast.LENGTH_SHORT).show();

        List<DestinationVO> newDestinationList = DestinationModel.getInstance().getmDestList();
        mAdapter.setNewData(newDestinationList);
        mAdapter.notifyDataSetChanged();
    }
}
