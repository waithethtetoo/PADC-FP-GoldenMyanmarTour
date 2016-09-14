package com.padc.goldenmyanmartour.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.adapters.FestivalAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/14/2016.
 */
public class PLanOwnRouteFragment extends Fragment
        implements View.OnClickListener {

    @BindView(R.id.iv_place_one)
    ImageView ivPlaceOne;
    @BindView(R.id.iv_place_two)
    ImageView ivPlaceTwo;
    @BindView(R.id.iv_place_three)
    ImageView ivPlaceThree;
    @BindView(R.id.iv_place_four)
    ImageView ivPlaceFour;
    @BindView(R.id.iv_place_five)
    ImageView ivPlaceFive;

    @BindView(R.id.btn_calculate)
    Button btnCalculate;

    @BindView(R.id.gv_plan_route)
    GridView gvPlanRoute;

    public static PLanOwnRouteFragment newInstance() {
        PLanOwnRouteFragment fragment = new PLanOwnRouteFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planownroute, container, false);
        ButterKnife.bind(this, view);
        ivPlaceOne.setOnClickListener(this);
        ivPlaceTwo.setOnClickListener(this);
        ivPlaceThree.setOnClickListener(this);
        ivPlaceFour.setOnClickListener(this);
        ivPlaceFive.setOnClickListener(this);

        btnCalculate.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_place_one:

                break;
        }
    }
}
