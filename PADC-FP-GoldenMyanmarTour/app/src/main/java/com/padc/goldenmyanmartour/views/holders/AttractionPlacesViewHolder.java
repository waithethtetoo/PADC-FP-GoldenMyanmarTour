package com.padc.goldenmyanmartour.views.holders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.HotelVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import org.w3c.dom.Attr;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hp user on 9/26/2016.
 */
public class AttractionPlacesViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private static final String IE_ATTRACTION_PLACES_NAME = "IE_ATTRACTION_PLACES_NAME";

    @BindView(R.id.iv_attractionPlaces)
    ImageView ivAttractionPlaces;

    @BindView(R.id.tv_attractionPlace)
    TextView tvAttractionPlace;

    private AttractionPlacesVO mAttractionPlaces;
    private ControllerAttractionPlaceItem controllerItem;

    public static Intent newIntent(String attractionPlaceName) {
        Intent intent = new Intent(GMTApp.getContext(), AttractionPlacesViewHolder.class);
        intent.putExtra(IE_ATTRACTION_PLACES_NAME, attractionPlaceName);
        return intent;
    }

    public AttractionPlacesViewHolder(View itemView, ControllerAttractionPlaceItem mControllerItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        controllerItem = mControllerItem;
    }

    public void bindData(AttractionPlacesVO attractionPlacesVO) {

        mAttractionPlaces = attractionPlacesVO;

        tvAttractionPlace.setText(attractionPlacesVO.getTitle());

//        String[] imageUrl = attractionPlacesVO.getImage();

//        if (!imageUrl.contains(DestinationConstants.ATTRACTION_IMAGE_ROOT_DIR)) {
//            imageUrl = DestinationConstants.ATTRACTION_IMAGE_ROOT_DIR + imageUrl;
//        }

//        Log.v("AttractionImageSize", String.valueOf(attractionPlacesVO.getImage().length));
/*
        Glide.with(ivAttractionPlaces.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.bagan_museum1)
                .error(R.drawable.bagan_museum1)
                .into(ivAttractionPlaces);
*/
    }

    @Override
    public void onClick(View v) {
        controllerItem.onTapAttractionPlace(mAttractionPlaces, ivAttractionPlaces);
    }

    public interface ControllerAttractionPlaceItem {
        void onTapAttractionPlace(AttractionPlacesVO attractionPlacesVO, ImageView ivHotel);
    }
}
