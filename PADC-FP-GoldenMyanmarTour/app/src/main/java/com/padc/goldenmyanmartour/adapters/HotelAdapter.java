package com.padc.goldenmyanmartour.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.views.holders.HotelViewHolder;

import java.util.List;

/**
 * Created by hp user on 9/10/2016.
 */
public class HotelAdapter extends RecyclerView.Adapter<HotelViewHolder> {

    private LayoutInflater mInflater;
    private List<HotelVO> mHotelList;
    private HotelViewHolder.ControllerHotelItem mControllerHotelItem;


    public HotelAdapter(List<HotelVO> hotelList,HotelViewHolder.ControllerHotelItem controllerHotelItem ) {
        mInflater = LayoutInflater.from(GMTApp.getContext());
        mHotelList = hotelList;
        mControllerHotelItem = controllerHotelItem;
    }

    @Override
    public HotelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_hotel,parent,false);
        return new HotelViewHolder(itemView,mControllerHotelItem);
    }

    @Override
    public void onBindViewHolder(HotelViewHolder holder, int position) {
        holder.bindData();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return 6;
    }


}
