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

    private List<DestinationVO> mDestinationList;
    private LayoutInflater inflater;
    private DestinationViewHolder.ControllerDestinationItem controllerDestinationItem;

    public DestinationAdapter(List<DestinationVO> destinationList, DestinationViewHolder.ControllerDestinationItem mController) {
        inflater = LayoutInflater.from(GMTApp.getContext());
        mDestinationList = destinationList;
        controllerDestinationItem = mController;
    }

    @Override
    public int getCount() {
        return mDestinationList.size();
    }

    @Override
    public DestinationVO getItem(int position) {
        return mDestinationList.get(position);
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
        viewHolder.bindData(getItem(position));
        return convertView;
    }

    public void setNewData(List<DestinationVO> newDestination) {
        mDestinationList = newDestination;
        notifyDataSetChanged();
    }
}
