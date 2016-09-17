package com.padc.goldenmyanmartour.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.HotelAdapter;
import com.padc.goldenmyanmartour.views.holders.HotelViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/9/2016.
 */
public class HotelFragment extends Fragment {

    @BindView(R.id.rv_hotels)
    RecyclerView rvHotels;

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
        rvHotels.setLayoutManager(new GridLayoutManager(getContext(),1));
        return rootView;
    }
}