package com.padc.goldenmyanmartour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;
import com.padc.goldenmyanmartour.views.holders.DestinationViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WT on 9/5/2016.
 */
public class DestinationAdapter extends BaseAdapter {

    private List<DestinationVO> destinationList;
    private LayoutInflater inflater;
    private DestinationViewHolder.ControllerDestinationItem controllerDestinationItem;

    public DestinationAdapter(List<DestinationVO> destinationList, DestinationViewHolder.ControllerDestinationItem mController) {
        if (destinationList != null) {
            this.destinationList = destinationList;
        } else {
            destinationList = new ArrayList<>();
        }
        inflater = LayoutInflater.from(GMTApp.getContext());
        this.controllerDestinationItem = mController;
    }

    @Override
    public int getCount() {
        return 10;
    }

   /* @Override
    public DestinationVO getItem(int position) {
        return destinationList.get(position);
    } */

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DestinationViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_item_destinations, parent, false);
            viewHolder = new DestinationViewHolder(convertView, controllerDestinationItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DestinationViewHolder) convertView.getTag();
        }
//        viewHolder.bindData(getItem(position));
        viewHolder.bindData();
        return convertView;
    }
}
