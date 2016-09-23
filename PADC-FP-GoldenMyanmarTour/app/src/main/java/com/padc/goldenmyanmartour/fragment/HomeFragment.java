package com.padc.goldenmyanmartour.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends BaseFragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.gv_destinations)
    GridView gvDestinations;

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
        mController = (DestinationViewHolder.ControllerDestinationItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DestinationAdapter(null, mController);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        gvDestinations.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(DestinationConstants.DESTINATION_LIST_LOADER_GRIDVIEW, null, this);
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

    @Override
    protected void onSendScreenHit() {

    }
}
