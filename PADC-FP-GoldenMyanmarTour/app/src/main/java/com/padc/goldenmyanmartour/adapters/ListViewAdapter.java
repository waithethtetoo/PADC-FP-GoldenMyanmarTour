package com.padc.goldenmyanmartour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.goldenmyanmartour.GMTApp;
import com.padc.goldenmyanmartour.R;
import com.padc.goldenmyanmartour.data.vo.DestinationVO;

import java.util.List;

/**
 * Created by WT on 9/17/2016.
 */
public class ListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<DestinationVO> destinationList;
    Context context;

    public ListViewAdapter(List<DestinationVO> destinationList, Context context) {
        this.destinationList = destinationList;
        this.context = context;
        inflater = LayoutInflater.from(GMTApp.getContext());
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        convertView = inflater.inflate(R.layout.list_item_design, parent, false);
        holder.ivDestOne = (ImageView) convertView.findViewById(R.id.iv_dest_one);
        holder.ivDestTwo = (ImageView) convertView.findViewById(R.id.iv_dest_two);
        holder.tvDestOne = (TextView) convertView.findViewById(R.id.tv_dest_one);
        holder.tvDestTwo = (TextView) convertView.findViewById(R.id.tv_dest_two);

        holder.ivDestOne.setImageResource(R.drawable.bagan);
        holder.ivDestTwo.setImageResource(R.drawable.mandalay);
        holder.tvDestOne.setText("Bagan Bagan Bagna");
        holder.tvDestTwo.setText("Mandalay Mandalay Mandalay");

        return convertView;
    }

    private class ViewHolder {
        private TextView tvDestOne;
        private TextView tvDestTwo;
        private ImageView ivDestOne;
        private ImageView ivDestTwo;
    }
}
