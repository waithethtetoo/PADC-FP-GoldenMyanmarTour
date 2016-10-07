package com.padc.goldenmyanmartour.views.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
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

    @BindView(R.id.tv_hotel_phone)
    TextView tvHotelPhone;

    @BindView(R.id.rb_star)
    RatingBar rbStar;

    private ControllerHotelItem mController;
    private HotelVO mHotel;

    public HotelViewHolder(View itemView, ControllerHotelItem controller) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mController = controller;
    }

    public void bindData(HotelVO hotelVO)//HotelVO hotel
    {
        mHotel = hotelVO;

        tvHotelName.setText(hotelVO.getHotelName());
        tvHotelAddress.setText(hotelVO.getAddress());
        rbStar.setRating(hotelVO.getRating());

        String phone = hotelVO.getPhoneNo()[0];
        tvHotelPhone.setText(phone);

        String imageUrl = hotelVO.getPhotos()[0];
        Glide.with(ivHotel.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.gmtiicon)
                .error(R.drawable.gmtiicon)
                .into(ivHotel);

    }

    @Override
    public void onClick(View view) {
        mController.onTapHotel(mHotel, ivHotel);
    }

    public interface ControllerHotelItem {
        void onTapHotel(HotelVO hotel, ImageView ivHotel);
    }
}
