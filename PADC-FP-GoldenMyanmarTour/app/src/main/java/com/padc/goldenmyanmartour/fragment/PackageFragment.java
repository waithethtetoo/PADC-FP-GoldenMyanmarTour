package com.padc.goldenmyanmartour.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;

import android.support.v4.view.MenuItemCompat;
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
import com.padc.goldenmyanmartour.adapters.PackageAdapter;

import com.padc.goldenmyanmartour.data.Models.DestinationModel;
import com.padc.goldenmyanmartour.data.Models.PackageModel;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.HotelVO;

import com.padc.goldenmyanmartour.data.vo.PackageVO;
import com.padc.goldenmyanmartour.data.vo.TourCompanyVO;
import com.padc.goldenmyanmartour.events.DataEvent;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import com.padc.goldenmyanmartour.views.holders.PackageViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by WT on 9/6/2016.
 */
public class PackageFragment extends Fragment {

    @BindView(R.id.gv_packages)
    GridView gvPackages;

    private PackageAdapter mAdapter;
    private PackageViewHolder.ControllerItem mController;

    private MenuItemCompat.OnActionExpandListener mOnActionExpandListener;

    public static PackageFragment newInstance() {
        PackageFragment fragment = new PackageFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = (PackageViewHolder.ControllerItem) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new PackageAdapter(PackageModel.getInstance().getPackageList(), mController);
//        List<PackageVO> packageVOList = PackageModel.getInstance().getPackageList();
//        mAdapter = new PackageAdapter(packageVOList, mController);
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



/*

    // data retrieve from persistence layer
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                DestinationContract.PackageEntry.CONTENT_URI,
                null,
                null,
                null,
                DestinationContract.PackageEntry.COLUMN_NAME + " ASC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<PackageVO> packageVOList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                PackageVO packageVO = PackageVO.parseFromCursor(data);

                packageVO.setPhotos(PackageVO.loadPackageImageByName(packageVO.getPackageName()));

                packageVO.setDestinationVOs(DestinationVO.loadDestinationByPackageId(packageVO.getPackageId()));

                packageVO.setTourCompanyVO(TourCompanyVO.loadTourCompanyByPackageID(packageVO.getPackageId()));

                packageVOList.add(packageVO);

            } while (data.moveToNext());
        }

        mAdapter.setNewData(packageVOList);
        PackageModel.getInstance().setStoredData(packageVOList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
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

    public void onEventMainThread(DataEvent.PackageDataLoaded event) {
        String extra = event.getMessage();
        Toast.makeText(getContext(), "Extra :" + extra, Toast.LENGTH_SHORT).show();

        List<PackageVO> newPackageList = event.getPackageVOList();
        mAdapter.setNewData(newPackageList);
        mAdapter.notifyDataSetChanged();
    }
*/
}
