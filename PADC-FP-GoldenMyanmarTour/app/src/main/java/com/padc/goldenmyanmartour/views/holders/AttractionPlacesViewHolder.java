package com.padc.goldenmyanmartour.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/26/2016.
 */
public class AttractionPlacesViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_attractionPlaces)
    ImageView ivAttractionPlaces;

    @BindView(R.id.tv_attractionPlace)
    TextView tvAttractionPlace;



    public AttractionPlacesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bindData(){
        tvAttractionPlace.setText("Bagan Museum");
        Glide.with(ivAttractionPlaces.getContext())
                .load(R.drawable.bagan_museum1)
                .centerCrop()
                .placeholder(R.drawable.bagan_museum1)
                .error(R.drawable.bagan_museum1)
                .into(ivAttractionPlaces);
    }
}
