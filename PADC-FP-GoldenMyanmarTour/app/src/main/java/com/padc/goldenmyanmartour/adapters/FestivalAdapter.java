package com.padc.goldenmyanmartour.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.data.vo.FestivalVO;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;
import com.padc.goldenmyanmartour.views.holders.FestivalViewHolder;
import com.padc.goldenmyanmartour.views.holders.HotelViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/12/2016.
 */
public class FestivalAdapter extends RecyclerView.Adapter<FestivalViewHolder> {

    private List<FestivalVO> festivalVOList;
    private LayoutInflater inflater;
    private FestivalViewHolder.ControllerFestivalItem controllerFestivalItem;

    public FestivalAdapter(List<FestivalVO> festivalVOList, FestivalViewHolder.ControllerFestivalItem controllerFestivalItem) {
        inflater = LayoutInflater.from(GMTApp.getContext());
        if (festivalVOList != null) {
            this.festivalVOList = festivalVOList;
        } else {
            this.festivalVOList = new ArrayList<>();
        }
        this.controllerFestivalItem = controllerFestivalItem;
    }


    @Override
    public FestivalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.view_item_festivals, parent, false);
        return new FestivalViewHolder(itemView, controllerFestivalItem);
    }

    @Override
    public void onBindViewHolder(FestivalViewHolder holder, int position) {
        holder.bindData(festivalVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return festivalVOList.size();
    }

    public void setNewData(List<FestivalVO> newFestivalList) {
        festivalVOList = newFestivalList;
        notifyDataSetChanged();
    }

}
