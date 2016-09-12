package com.padc.goldenmyanmartour.views.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.FestivalDetailActivity;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WT on 9/12/2016.
 */
public class FestivalViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
    @BindView(R.id.tv_festival_name)
    TextView tvFestName;
    @BindView(R.id.tv_festival_location)
    TextView tvFestLocation;
    @BindView(R.id.tv_festival_month)
    TextView tvFestMonth;
    @BindView(R.id.tv_festival_date)
    TextView tvFestDate;
    @BindView(R.id.tv_festival_duration)
    TextView tvFestDuration;

    @BindView(R.id.iv_festival)
    ImageView ivFestival;

    private FestivalVO festivalVO;
    private ControllerFestivalItem mController;

    public FestivalViewHolder(View view, ControllerFestivalItem controllerFestivalItem) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
        mController = controllerFestivalItem;
    }

    public void bindData() {
        tvFestName.setText("Ananda Pagoda Festival");
        tvFestLocation.setText("Khamti, Lahe, Lashi in Sagaing");
        tvFestMonth.setText("January");
        tvFestDate.setText("Full Moon of Pyatho to 15th Waning of Pyatho");
        tvFestDuration.setText("15 Days");

        Glide.with(ivFestival.getContext())
                .load(R.drawable.ananda_pagoda_festival)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivFestival);
    }

    @Override
    public void onClick(View v) {
        Intent intentToFestival = FestivalDetailActivity.newIntent("Anada Pagoda Festival");
        intentToFestival.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GMTApp.getContext().startActivity(intentToFestival);
    }

    public interface ControllerFestivalItem {
        void onTapFestivals(FestivalVO festivalVO, ImageView iv_festival);
    }
}
