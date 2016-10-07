package com.padc.goldenmyanmartour.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.adapters.FestivalAdapter;
import com.padc.goldenmyanmartour.data.Models.FestivalModel;
import com.padc.goldenmyanmartour.data.persistence.DestinationContract;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.FestivalViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/10/2016.
 */
public class FestivalFragment extends Fragment {

    @BindView(R.id.rv_festivals)
    RecyclerView rvFestivals;

    private FestivalAdapter mAdapter;
    private FestivalViewHolder.ControllerFestivalItem mController;


    public static FestivalFragment newInstance() {
        FestivalFragment fragment = new FestivalFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mController = (FestivalViewHolder.ControllerFestivalItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new FestivalAdapter(FestivalModel.getInstance().getFestivalList(), mController);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_festival, container, false);
        ButterKnife.bind(this, view);

        rvFestivals.setAdapter(mAdapter);
        int gridColumnCount = getResources().getInteger(R.integer.attraction_list_grid);
        rvFestivals.setLayoutManager(new GridLayoutManager(getContext(), gridColumnCount));

        return view;
    }

}
