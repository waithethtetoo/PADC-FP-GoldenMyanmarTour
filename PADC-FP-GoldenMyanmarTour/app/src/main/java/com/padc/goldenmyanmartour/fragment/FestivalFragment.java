package com.padc.goldenmyanmartour.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.adapters.FestivalAdapter;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.FestivalViewHolder;

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

    public FestivalFragment() {
    }

    public static FestivalFragment newInstance() {
        FestivalFragment fragment = new FestivalFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new FestivalAdapter(null, mController);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_festival, container, false);
        ButterKnife.bind(this, view);
        rvFestivals.setAdapter(mAdapter);
        rvFestivals.setLayoutManager(new GridLayoutManager(getContext(), 1));
        return view;
    }
}
