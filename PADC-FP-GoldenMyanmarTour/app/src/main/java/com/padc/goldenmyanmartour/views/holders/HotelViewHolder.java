package com.padc.goldenmyanmartour.views.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.activity.HotelDetailActivity;
import com.padc.goldenmyanmartour.data.vo.HotelVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/9/2016.
 */
public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_hotel)
    ImageView ivHotel;

    @BindView(R.id.tv_hotel_name)
    TextView tvHotelName;

    @BindView(R.id.tv_hotel_address)
    TextView tvHotelAddress;

//    @BindView(R.id.tv_hotel_desc)
//    TextView tvHotelDesc;

    private ControllerHotelItem mController;
    private HotelVO mHotel;

    public HotelViewHolder(View itemView,ControllerHotelItem controller) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData()//HotelVO hotel
    {
       // mHotel = hotel;
        tvHotelName.setText("Park Royal Yangon");
        tvHotelAddress.setText("33 Alan Pya Phaya Road, Dagon Township, Yangon, Myanmar");
        //tvHotelDesc.setText("Offering a unique blend of gentle Myanmar hospitality combined with first class service and facilities, the Grand Plaza Parkroyal");//hotel.getHotelDesc()

       // String imageUrl = hotel.getHotelImage()[0];

        Glide.with(ivHotel.getContext())
                .load(R.drawable.ygnhotel_1)
                .centerCrop()
                .placeholder(R.drawable.ygnhotel_1)
                .error(R.drawable.ygnhotel_1)
                .into(ivHotel);

    }

    @Override
    public void onClick(View view) {
       // mController.onTapHotel(mHotel,ivHotel);

        Intent intentToHotelDetail = HotelDetailActivity.newIntent("Yangon");
        intentToHotelDetail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        GMTApp.getContext().startActivity(intentToHotelDetail);


    }

    public interface ControllerHotelItem{
        void onTapHotel(HotelVO hotel,ImageView ivHotel);
    }
}
