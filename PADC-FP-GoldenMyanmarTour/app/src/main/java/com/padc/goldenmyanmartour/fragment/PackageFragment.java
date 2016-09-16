package com.padc.goldenmyanmartour.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
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
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.HomeActivity;
import com.padc.goldenmyanmartour.activity.PackageDetailActivity;
import com.padc.goldenmyanmartour.activity.SearchActivity;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.adapters.PackageAdapter;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.PackageViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/6/2016.
 */
public class PackageFragment extends Fragment {

    @BindView(R.id.gv_packages)
    GridView gvPackages;

    private PackageAdapter mAdapter;
    private PackageViewHolder.ControllerItem mController;

    MenuItem searchItem;
    MenuItem tourItem;
    private MenuItemCompat.OnActionExpandListener mOnActionExpandListener;

    public PackageFragment() {
        setHasOptionsMenu(true);
    }

    public static PackageFragment newInstance() {
        PackageFragment fragment = new PackageFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        mController = (DestinationViewHolder.ControllerDestinationItem) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new PackageAdapter(null, mController);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_packages, container, false);
        ButterKnife.bind(this, view);
        gvPackages.setAdapter(mAdapter);
        return view;
    }

    /*   @Override
       public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
           inflater.inflate(R.menu.menu_search, menu);
           searchItem = menu.findItem(R.id.action_search);
           MenuItemCompat.setOnActionExpandListener(searchItem, mOnActionExpandListener);

           tourItem = menu.findItem(R.id.action_tour_type_filter);
           Spinner spinner = (Spinner) MenuItemCompat.getActionView(tourItem);
           ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(GMTApp.getContext(),
                   R.array.spinner_list_item_array, android.R.layout.simple_spinner_dropdown_item);
           spinner.setAdapter(adapter);
           super.onCreateOptionsMenu(menu, inflater);
       }

       @Override
       public boolean onOptionsItemSelected(MenuItem item) {
           int id = item.getItemId();
           switch (id) {
               case R.id.action_search:
                   Toast.makeText(GMTApp.getContext(), "Action Search is clicked", Toast.LENGTH_SHORT).show();
                   return true;
               case R.id.action_tour_type_filter:
                   return true;
           }

           return super.onOptionsItemSelected(item);
       }
   */
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
                Toast.makeText(GMTApp.getContext(), "Package Fragment Search FAB Clicked", Toast.LENGTH_SHORT).show();
                Intent intentToSearch = SearchActivity.newIntent("Package Fragment");
                startActivity(intentToSearch);
            }
        });
    }
}
