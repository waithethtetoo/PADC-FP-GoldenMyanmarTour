package com.padc.goldenmyanmartour.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.DestinationAdapter;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/10/2016.
 */
public class FestivalFragment extends Fragment {
    @BindView(R.id.gv_festivals)
    GridView gvFestivals;

    private DestinationAdapter mAdapter;
    private DestinationViewHolder.ControllerDestinationItem mController;

    public FestivalFragment() {
    }

    public static FestivalFragment newInstance() {
        FestivalFragment fragment = new FestivalFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DestinationAdapter(null, mController);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_festival, container, false);
        ButterKnife.bind(this, view);
        gvFestivals.setAdapter(mAdapter);
        return view;
    }
}
