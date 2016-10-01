package com.padc.goldenmyanmartour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.AttractionPlacesVO;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.utils.DestinationConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/17/2016.
 */
public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<AttractionPlacesVO> attractionPlacesVOList;
    Context context;

    public ListViewAdapter(List<AttractionPlacesVO> attractionPlacesList, Context context) {
        if (attractionPlacesVOList != null) {
            this.attractionPlacesVOList = attractionPlacesList;
        } else {
            this.attractionPlacesVOList = new ArrayList<>();
        }
        this.context = context;
        inflater = LayoutInflater.from(GMTApp.getContext());
    }

    @Override
    public int getCount() {
        return attractionPlacesVOList.size();
    }


    @Override
    public AttractionPlacesVO getItem(int position) {
        return attractionPlacesVOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_design, parent, false);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.bindData(getItem(position));
        return convertView;
    }

    public void setNewData(List<AttractionPlacesVO> newAttractionPlace) {
        attractionPlacesVOList = newAttractionPlace;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private TextView tvDestTitle;
        private TextView tvDestOne;
        private ImageView ivDestOne;

        public void bindData(AttractionPlacesVO attractionPlacesVO) {
            tvDestTitle.setText(attractionPlacesVO.getTitle());
            tvDestOne.setText(attractionPlacesVO.getDescription());
            String imageUrl = DestinationConstants.ATTRACTION_IMAGE_ROOT_DIR + attractionPlacesVO.getImage()[0];
            Glide.with(ivDestOne.getContext())
                    .load(imageUrl)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(ivDestOne);
        }
    }
}
