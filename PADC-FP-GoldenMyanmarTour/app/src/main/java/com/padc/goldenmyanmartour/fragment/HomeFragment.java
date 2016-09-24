package com.padc.goldenmyanmartour.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
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

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.HomeActivity;
import com.padc.goldenmyanmartour.activity.SearchActivity;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.gv_destinations)
    GridView gvDestinations;

    @BindView(R.id.sp_fiterCity)
            Spinner spFilterCity;

    MenuItem destinationItemCity;


    private DestinationAdapter mAdapter;
    private DestinationViewHolder.ControllerDestinationItem mController;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mController = (DestinationViewHolder.ControllerDestinationItem) context;
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

        gvDestinations.setAdapter(mAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.spinner_city_item_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spFilterCity.setAdapter(adapter);


        return view;
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
        int id= item.getItemId();
        switch (id) {
            case R.id.spinner:
                return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
