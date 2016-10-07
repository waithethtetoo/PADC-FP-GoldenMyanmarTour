package com.padc.goldenmyanmartour.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.views.holders.AttractionPlacesViewHolder;

import java.util.List;

/**
 * Created by hp user on 9/26/2016.
 */
public class AttractionPlacesAdapter extends RecyclerView.Adapter<AttractionPlacesViewHolder> {

    private LayoutInflater apInflater;
    private List<AttractionPlacesVO> attractionPlacesList;
    private AttractionPlacesViewHolder.ControllerAttractionPlaceItem controllerItem;

    public AttractionPlacesAdapter(List<AttractionPlacesVO> aPlacesList) {
        apInflater = LayoutInflater.from(GMTApp.getContext());
        attractionPlacesList = aPlacesList;
    }

    @Override
    public AttractionPlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = apInflater.inflate(R.layout.view_item_attractionplaces, parent, false);
        return new AttractionPlacesViewHolder(itemView, controllerItem);
    }

    @Override
    public void onBindViewHolder(AttractionPlacesViewHolder holder, int position) {
        holder.bindData(attractionPlacesList.get(position));
    }


    @Override
    public int getItemCount() {
        Log.v("AttractionPlaceListSize", String.valueOf(attractionPlacesList.size()));
        return attractionPlacesList.size();
    }


    public void setNewData(List<AttractionPlacesVO> newAttractionPlace) {
        attractionPlacesList = newAttractionPlace;
        notifyDataSetChanged();
    }
}
