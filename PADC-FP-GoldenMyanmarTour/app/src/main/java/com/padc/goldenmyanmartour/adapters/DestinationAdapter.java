package com.padc.goldenmyanmartour.adapters;

import android.content.Context;
import android.util.Log;
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

    public DestinationAdapter(List<DestinationVO> destinationList, DestinationViewHolder.ControllerDestinationItem controllerDestinationItem) {
        if (destinationList != null) {
            this.destinationList = destinationList;
        } else {
            this.destinationList = new ArrayList<>();
        }
        inflater = LayoutInflater.from(GMTApp.getContext());
        this.controllerDestinationItem = controllerDestinationItem;
    }

    @Override
    public int getCount() {
        return destinationList.size();
    }

    @Override
    public DestinationVO getItem(int position) {
        return destinationList.get(position);
    }

    @Override
    public long getItemId(int i) {
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


    public List<DestinationVO> setNewDataBySearchText(List<DestinationVO> searchList, String searchText) {
        //Filter here
        List<DestinationVO> filterList = new ArrayList<>();
        for (DestinationVO searchDest : searchList) {
            if (searchDest.getTitle().equalsIgnoreCase(searchText)) {
                filterList.add(searchDest);
                Log.d(GMTApp.TAG, searchDest.getTitle());
            }
        }

        destinationList = filterList;
        notifyDataSetChanged();//framework method
        return filterList;
    }


    public void setNewData(List<DestinationVO> newDestinationList) {
        destinationList = newDestinationList;
        notifyDataSetChanged();
    }
}
