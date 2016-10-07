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

    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    @BindView(R.id.tv_end_date)
    TextView tvEndDate;

//    @BindView(R.id.tv_start_time)
//    TextView tvStartTime;
//
//    @BindView(R.id.tv_end_time)
//    TextView tvEndTime;

    @BindView(R.id.iv_festival)
    ImageView ivFestival;

    private FestivalVO mfestivalVO;
    private ControllerFestivalItem mController;

    public FestivalViewHolder(View view, ControllerFestivalItem controllerFestivalItem) {
        super(view);
        ButterKnife.bind(this, view);
        view.setOnClickListener(this);
        mController = controllerFestivalItem;
    }

    public void bindData(FestivalVO festivalVO) {
        mfestivalVO = festivalVO;
        tvFestName.setText(festivalVO.getFestivalName());
        tvFestLocation.setText(festivalVO.getCityName() + " , " + festivalVO.getStateName());
        tvStartDate.setText(festivalVO.getStartDate());
        tvEndDate.setText(festivalVO.getEndDate());
//        tvStartTime.setText(festivalVO.getStartTime());
//        tvEndTime.setText(festivalVO.getEndTime());


        String images = festivalVO.getPhotos()[0];

        Glide.with(ivFestival.getContext())
                .load(images)
                .centerCrop()
                .placeholder(R.drawable.gmtiicon)
                .error(R.drawable.gmtiicon)
                .into(ivFestival);
    }

    @Override
    public void onClick(View v) {
        mController.onTapFestivals(mfestivalVO, ivFestival);
    }

    public interface ControllerFestivalItem {
        void onTapFestivals(FestivalVO festivalVO, ImageView iv_festival);
    }
}
